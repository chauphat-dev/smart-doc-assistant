import uuid
from pathlib import Path
import os
import re
import unicodedata
from pypdf import PdfReader
from dotenv import load_dotenv
from google import genai
from google.genai.types import EmbedContentConfig
import qdrant_client
from qdrant_client.models import VectorParams, Distance, PointStruct

load_dotenv()


COLLECTION_NAME = os.getenv("GEMINI_COLLECTION_NAME","spike_chunks")


def read_pdf(path: Path) -> dict[int, str]:
    reader = PdfReader(str(path))
    pages_text: dict[int, str] = {}
    for page_number, page in enumerate(reader.pages, start=1):
        text = page.extract_text() or ""
        pages_text[page_number] = text
    return pages_text


def clean_text(text: str) -> str:
    if not text:
        return ""
    normalized_text = unicodedata.normalize("NFKC", text)
    cleaned_text = re.sub(r"\s+", " ", normalized_text)
    return cleaned_text.strip()


def chunk_page(
    text: str, page_number: int, chunk_size: int, doc_name: str, overlap: int
) -> list[dict]:
    chunks = []
    if not text:
        return chunks

    start = 0
    chunk_index = 0
    text_length = len(text)
    step = chunk_size - overlap
    if step <= 0:
        raise ValueError("chunk_size must be strictly greater than overlap")

    while start < text_length:
        end = min(start + chunk_size, text_length)
        if end < text_length:
            last_space = text.rfind(" ", start, end)
            if last_space > start + overlap:
                end = last_space

        chunk_text = text[start:end].strip()
        if chunk_text:
            chunks.append({
                "doc_name": doc_name,
                "page_number": page_number,
                "chunk_index": chunk_index,
                "text": chunk_text,
            })
            chunk_index += 1

        if end >= text_length:
            break
        start += step

    return chunks


def embed_chunks(
    chunks: list[dict], batch_size: int = 50, doc_title: str | None = None
) -> list[list[float]]:
    client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))
    all_embeddings = []

    for i in range(0, len(chunks), batch_size):
        batch = chunks[i : i + batch_size]
        texts = [chunk["text"] for chunk in batch]

        response = client.models.embed_content(
            model="gemini-embedding-001",
            contents=texts,
            config=EmbedContentConfig(
                task_type="RETRIEVAL_DOCUMENT",
                title=doc_title,
                output_dimensionality=3072,
            ),
        )

        for embedding in response.embeddings:
            all_embeddings.append(embedding.values)

    return all_embeddings


def embed_query(query: str) -> list[float]:
    client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))
    response = client.models.embed_content(
        model="gemini-embedding-001",
        contents=query,
        config=EmbedContentConfig(
            task_type="RETRIEVAL_QUERY",
            output_dimensionality=3072,
        ),
    )
    return response.embeddings[0].values


def store_chunks(
    chunks: list[dict], embeddings: list[list[float]], collection_name: str
) -> qdrant_client.QdrantClient:
    if not chunks or not embeddings:
        raise ValueError("chunks or embeddings must not be empty")
    if len(chunks) != len(embeddings):
        raise ValueError("chunks and embeddings must have the same length")

    client = qdrant_client.QdrantClient(
        url=os.getenv("QDRANT_URL", "http://localhost:6333"),
        api_key=os.getenv("QDRANT_API_KEY"),
    )

    vector_size = len(embeddings[0])
    if not client.collection_exists(collection_name=collection_name):
        client.create_collection(
            collection_name=collection_name,
            vectors_config=VectorParams(
                size=vector_size,
                distance=Distance.COSINE,
            ),
        )

    points: list[PointStruct] = []
    for chunk, vector in zip(chunks, embeddings):
        raw_id = f"{chunk['doc_name']}_{chunk['page_number']}_{chunk['chunk_index']}"
        point_id = str(uuid.uuid5(uuid.NAMESPACE_DNS, raw_id))

        payload = {
            "doc_name": chunk["doc_name"],
            "page_number": chunk["page_number"],
            "chunk_index": chunk["chunk_index"],
            "text": chunk["text"],
        }

        points.append(
            PointStruct(
                id=point_id,
                vector=vector,
                payload=payload,
            )
        )

    client.upsert(
        collection_name=collection_name,
        points=points,
    )
    return client



def ask(client: qdrant_client.QdrantClient, question: str, k: int) -> str:
    vector_query = embed_query(question)
    responses = client.query_points(
        collection_name=COLLECTION_NAME,
        query=vector_query,
        limit=k,
        with_payload=True,
    )
    if not responses.points:
        return "Can't find any relevant results"

    context = []
    for pt in responses.points:
        pl = pt.payload or {}
        # In page, chunk và score của từng kết quả tìm được
        print(
            f"Page {pl.get('page_number')} | Chunk {pl.get('chunk_index')} | Score: {pt.score:.4f}"
        )
        # Bỏ "| Score: ..." khỏi context đưa vào prompt
        context.append(
            f"[Tài liệu: {pl.get('doc_name')} | Trang {pl.get('page_number')} | Chunk {pl.get('chunk_index')}]\n{pl.get('text')}"
        )

    context_text = "\n\n---\n\n".join(context)
    prompt = (
        "Dựa vào các đoạn trích xuất tài liệu dưới đây, hãy trả lời câu hỏi của người dùng. "
        "Chỉ sử dụng thông tin trong tài liệu và trích dẫn rõ số trang (ví dụ: [Trang X]). Nếu không có thì hãy trả lời 'Tôi không biết'\n\n"
        f"TÀI LIỆU TRÍCH XUẤT:\n{context_text}\n\n"
        f"CÂU HỎI: {question}"
    )
    genai_client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))

    answer = genai_client.models.generate_content(
        model="gemini-3.5-flash-lite",
        contents=prompt,
    )
    return answer.text





if __name__ == "__main__":
    pdf_path = Path(__file__).parent / "BLOCKCHAIN-AVALANCHE.pdf"
    doc_name = "BLOCKCHAIN-AVALANCHE"

    pages_text = read_pdf(pdf_path)

    all_chunks = []
    for page_number, text in pages_text.items():
        cleaned = clean_text(text)
        chunks = chunk_page(
            cleaned, page_number, chunk_size=800, doc_name=doc_name, overlap=150
        )
        all_chunks.extend(chunks)

    print(f"Total chunks: {len(all_chunks)}")
    if all_chunks:
        print(f"First chunk: {all_chunks[0]}")
    client = qdrant_client.QdrantClient(url="http://localhost:6333")

    exists = client.collection_exists(COLLECTION_NAME)
    if exists and client.count(COLLECTION_NAME).count > 0:
        print("Already stored, skipping embedding")
    else:
        embeddings = embed_chunks(all_chunks, batch_size=50, doc_title=doc_name)
        store_chunks(all_chunks, embeddings, COLLECTION_NAME)
    print(f"Stored {client.count(COLLECTION_NAME).count} points in '{COLLECTION_NAME}'")
    question1 = "Blockchain là gì?"
    print(f"\nQuestion: {question1}\n")
    print(ask(client=client, question=question1, k=3))
    question2 = "Bitcoin dùng cơ chế đồng thuận gì?"
    print(f"\nQuestion: {question2}\n")
    print(ask(client=client, question=question2, k=3))
    question3 = "Trong luồng x402 trên C-Chain, bước nào tốn gas và trả bằng token gì?"
    print(f"\nQuestion: {question3}\n")
    print(ask(client=client, question=question3, k=3))

