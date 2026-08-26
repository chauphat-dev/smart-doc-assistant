# 🎓 Learning Roadmap — Từ Zero đến Junior SWE

> Đây là bản đồ học tập đi kèm [implementation_plan.md](file:///Users/crocsa/.gemini/antigravity/brain/c3ee662f-7de3-4182-ad65-6c620afd13a3/implementation_plan.md). Mỗi sprint trong plan, bạn sẽ biết **cần học gì trước, học ở đâu, và thực hành thế nào**.

---

## 🧭 Cách dùng Roadmap này

```
1. Đọc phần "Cần học" của sprint hiện tại
2. Học từ tài nguyên được gợi ý (chọn 1, đừng học hết)
3. Tự code theo plan — ĐỪNG copy paste
4. Khi bí, hỏi mình — mình sẽ gợi ý hướng, KHÔNG đưa code
5. Xong sprint → tự review bằng checklist → qua sprint tiếp
```

> [!IMPORTANT]
> **Quy tắc vàng**: Mỗi khái niệm mới, hãy tự viết 1 đoạn code nhỏ để test riêng trước khi đưa vào project. Đừng cố hiểu mọi thứ cùng lúc.

---

## 📚 Pre-requisites — Học trước khi bắt đầu project

### Nếu bạn chưa biết Java

> Dành **2-4 tuần** học Java cơ bản trước khi bắt đầu Sprint 0.

| Chủ đề | Mức độ | Tài nguyên |
|--------|--------|-----------|
| Java syntax, OOP cơ bản | Bắt buộc | [Java Tutorial — W3Schools](https://www.w3schools.com/java/) hoặc [Codecademy Java](https://www.codecademy.com/learn/learn-java) |
| Collections (List, Map, Set) | Bắt buộc | Oracle Java Tutorials — Collections |
| Exception handling (try/catch) | Bắt buộc | Học kèm trong tutorial trên |
| Generics (`List<String>`) | Bắt buộc | Oracle Generics Tutorial |
| Lambda & Stream API | Nên biết | Baeldung — Java Streams |
| Records, Optional | Nên biết | Baeldung — Java Records |

**Bài tập tự kiểm tra**: Viết 1 chương trình Java đọc file CSV → lưu vào `List<Map<String, String>>` → filter theo điều kiện → in kết quả. Nếu làm được = đủ trình bắt đầu project.

### Nếu bạn chưa biết Git

> Dành **2-3 ngày**. Đây là kỹ năng BẮT BUỘC — không biết Git = không đi làm được.

| Chủ đề | Tài nguyên |
|--------|-----------|
| Git cơ bản (init, add, commit, push, pull) | [Git — The Simple Guide](https://rogerdudler.github.io/git-guide/) |
| Branching & Merging | [Learn Git Branching](https://learngitbranching.js.org/) (interactive) |
| GitHub workflow | Tạo repo trên GitHub, push code lên |

### Nếu bạn chưa biết SQL cơ bản

> Dành **3-5 ngày**.

| Chủ đề | Tài nguyên |
|--------|-----------|
| SELECT, INSERT, UPDATE, DELETE | [SQLBolt](https://sqlbolt.com/) (interactive) |
| JOIN, GROUP BY | SQLBolt tiếp |
| CREATE TABLE, constraints | W3Schools SQL |

### Nếu bạn chưa biết Docker

> Dành **2-3 ngày**.

| Chủ đề | Tài nguyên |
|--------|-----------|
| Docker là gì, container vs VM | [Docker Get Started](https://docs.docker.com/get-started/) |
| Dockerfile, docker compose | Docker official tutorial |
| Chạy PostgreSQL bằng Docker | Thực hành trong Sprint 0 |

---

## Sprint 0: Environment Setup — Học gì?

### Kỹ năng cần có
- [x] Biết dùng Terminal/Command Line cơ bản
- [ ] Cài đặt và quản lý tools bằng **Homebrew** (macOS)
- [ ] Hiểu **Docker Compose** — đọc file YAML, biết `up`, `down`, `logs`
- [ ] Biết dùng IDE (IntelliJ IDEA)

### Cách tiếp cận
```
1. Cài Homebrew → dùng nó cài mọi thứ (JDK, Docker, Git...)
2. Tạo GitHub repo "smart-doc-assistant"
3. Viết docker-compose.yml (tham khảo plan, ĐỪNG copy — tự gõ)
4. Chạy docker compose up → kết nối PostgreSQL bằng CLI hoặc DBeaver
5. Tạo 1 table test, INSERT vài row, SELECT ra xem
```

### Tài nguyên
| Chủ đề | Link |
|--------|------|
| Homebrew | https://brew.sh |
| SDKMAN (quản lý JDK versions) | https://sdkman.io |
| Docker Compose docs | https://docs.docker.com/compose/ |
| DBeaver (DB GUI tool) | https://dbeaver.io |

> [!TIP]
> **Mẹo SWE**: Luôn ghi lại steps setup vào `README.md`. 3 tháng sau bạn sẽ quên hết. Viết README giống như viết cho "tương lai mình" đọc.

---

## Sprint 1: Java Backend — Học gì?

### Kỹ năng cần có (học theo thứ tự)

| # | Chủ đề | Thời gian | Tài nguyên |
|---|--------|-----------|-----------|
| 1 | **Spring Boot là gì, tạo project** | 1 ngày | [Spring Boot Quickstart](https://spring.io/quickstart) |
| 2 | **Spring Web — REST Controller** | 2 ngày | [Building REST Services (Spring Guide)](https://spring.io/guides/tutorials/rest) |
| 3 | **Spring Data JPA — Entity, Repository** | 2 ngày | [Accessing Data with JPA (Spring Guide)](https://spring.io/guides/gs/accessing-data-jpa) |
| 4 | **DTO pattern — tách Entity và API response** | 1 ngày | Baeldung "Entity to DTO" |
| 5 | **Flyway — Database migration** | 0.5 ngày | [Flyway + Spring Boot](https://www.baeldung.com/database-migrations-with-flyway) |
| 6 | **File Upload với Spring** | 0.5 ngày | [Spring File Upload Guide](https://spring.io/guides/gs/uploading-files) |
| 7 | **Exception Handling** | 0.5 ngày | Baeldung "Error Handling for REST" |
| 8 | **Pagination** | 0.5 ngày | Baeldung "Spring Data JPA Pagination" |

### Cách tiếp cận từng bước
```
Ngày 1-2:
  → Tạo Spring Boot project từ start.spring.io
  → Viết 1 endpoint GET /api/hello → trả "Hello World"
  → Chạy app, test bằng browser hoặc curl
  → Viết endpoint GET /api/greet?name=Tuan → trả "Hello Tuan"

Ngày 3-4:
  → Tạo Entity class Document (id, filename, status...)
  → Tạo DocumentRepository interface
  → Viết migration SQL tạo table documents
  → Chạy app → check DB có table chưa
  → Tạo DocumentService → save, findAll, findById, delete
  → Tạo DocumentController → gọi service → trả JSON

Ngày 5-6:
  → Thêm file upload endpoint
  → Thêm DTO classes (tách riêng với Entity)
  → Thêm validation (@NotNull, @Size...)
  → Thêm global exception handler
  → Thêm pagination cho GET /documents

Ngày 7:
  → Viết unit tests cho DocumentService
  → Refactor code nếu cần
  → Commit & push
```

### Concepts quan trọng cần hiểu sâu

> [!IMPORTANT]
> Đừng chỉ biết **dùng**, hãy hiểu **tại sao**. Đi phỏng vấn người ta hỏi "tại sao" chứ không hỏi "làm thế nào".

| Concept | Câu hỏi bạn phải trả lời được |
|---------|------------------------------|
| **Dependency Injection** | Tại sao Spring tự tạo objects cho mình? Lợi ích là gì? |
| **Layered Architecture** | Tại sao tách Controller → Service → Repository? Nếu gộp lại thì sao? |
| **DTO vs Entity** | Tại sao không trả Entity trực tiếp qua API? |
| **Repository Pattern** | Interface không có implementation mà sao chạy được? (Spring Data magic) |
| **HTTP Status Codes** | Khi nào trả 200, 201, 202, 400, 404, 500? |

---

## Sprint 2: Python AI Service — Học gì?

### Kỹ năng cần có

| # | Chủ đề | Thời gian | Tài nguyên |
|---|--------|-----------|-----------|
| 1 | **Python cơ bản** (nếu chưa biết) | 3-5 ngày | [Python.org Tutorial](https://docs.python.org/3/tutorial/) |
| 2 | **FastAPI basics** | 1 ngày | [FastAPI Tutorial](https://fastapi.tiangolo.com/tutorial/) |
| 3 | **RAG concept** — hiểu lý thuyết | 1 ngày | YouTube: "RAG explained" |
| 4 | **LangChain basics** | 2 ngày | [LangChain Python Docs](https://python.langchain.com/docs/tutorials/) |
| 5 | **LangGraph basics** | 2 ngày | [LangGraph Tutorial](https://langchain-ai.github.io/langgraph/tutorials/) |
| 6 | **Qdrant Python client** | 0.5 ngày | [Qdrant Python Docs](https://qdrant.tech/documentation/) |
| 7 | **Google Gemini API** | 0.5 ngày | [Google AI Studio](https://aistudio.google.com/) — lấy API key miễn phí |

### Cách tiếp cận
```
Ngày 1-2:
  → Tạo FastAPI app đơn giản
  → Viết script Python: đọc 1 file TXT → chia chunks → in ra

Ngày 3-4:
  → Lấy Google API key từ AI Studio
  → Viết script: gửi 1 câu hỏi tới Gemini → in response
  → Viết script: tạo embedding cho 1 đoạn text → in vector

Ngày 5-6:
  → Kết nối Qdrant → upsert vectors → search
  → Ghép lại: file → chunks → embeddings → Qdrant → search → Gemini → answer

Ngày 7:
  → Đưa logic vào FastAPI endpoints
  → Thêm LangGraph agent (bắt đầu đơn giản: 2 nodes)
```

---

## Sprint 3-5: Tự tin hơn rồi

> Đến đây bạn đã có nền tảng. Mình sẽ guide ít hơn, bạn tự research nhiều hơn. Đó là cách SWE giỏi phát triển — **học cách tự học**.

---

## 🛠️ Kỹ năng đi làm thực tế

> [!IMPORTANT]
> Code chỉ là **30-40%** công việc SWE. Đây là những kỹ năng còn lại mà trường/course không dạy:

### Git Workflow thực tế

```
main (production code — KHÔNG BAO GIỜ code trực tiếp ở đây)
  └── develop (code development — merge vào đây)
       ├── feature/upload-document (1 feature = 1 branch)
       ├── feature/chat-api
       └── fix/upload-error
```

**Quy trình**:
```bash
# 1. Tạo branch mới cho mỗi feature
git checkout -b feature/upload-document

# 2. Code, commit THƯỜNG XUYÊN (mỗi bước nhỏ hoàn thành)
git add .
git commit -m "feat: add document upload endpoint"

# 3. Push lên GitHub
git push origin feature/upload-document

# 4. Tạo Pull Request trên GitHub
# 5. (Trong team) → đồng nghiệp review → approve → merge
# 6. (Cá nhân) → tự review → merge vào develop
```

### Commit Message Convention

```
feat: thêm tính năng mới         (feature)
fix: sửa bug                      (bug fix)  
refactor: sửa code nhưng không đổi behavior
docs: cập nhật documentation
test: thêm/sửa tests
chore: cập nhật config, dependencies
```

Ví dụ:
```
feat: add POST /api/v1/documents endpoint for file upload
fix: handle null filename in document upload
refactor: extract file validation to separate method
docs: add API documentation for document endpoints
test: add unit tests for DocumentService
```

### Debugging — Cách SWE tìm bug

```
1. ĐỌC error message — 80% bug nằm ngay trong error message
2. Google error message (copy paste dòng lỗi quan trọng nhất)
3. Thêm log/print để xem data ở mỗi bước
4. Dùng debugger (IntelliJ: click vào số dòng → đặt breakpoint → Debug)
5. Rubber duck debugging: giải thích code cho con vịt (hoặc cho mình)
6. Đọc documentation chính thức (KHÔNG phải blog random)
```

### Code Review — Tự review code mình

Trước khi commit, hỏi bản thân:
```
□ Method này có làm QUÁ NHIỀU việc không? (Nên chia nhỏ)
□ Tên biến/method có rõ nghĩa không? (documentService vs ds)
□ Có duplicate code không? (DRY — Don't Repeat Yourself)
□ Nếu input sai thì sao? (Validation, null check)
□ Có test chưa?
```

---

## 📊 Self-Assessment Checklist

> Sau mỗi sprint, tự đánh giá bằng checklist này:

### Sau Sprint 0
- [ ] Tôi biết dùng terminal để navigate, chạy commands
- [ ] Tôi biết dùng Git: init, add, commit, push, pull, branch, merge
- [ ] Docker compose up/down chạy được PostgreSQL + Qdrant
- [ ] Tôi kết nối được PostgreSQL và chạy SQL queries

### Sau Sprint 1
- [ ] Tôi hiểu Spring Boot tự cấu hình thế nào
- [ ] Tôi viết được REST API: GET, POST, DELETE
- [ ] Tôi hiểu Dependency Injection và tại sao nó hữu ích
- [ ] Tôi biết tạo Entity → Repository → Service → Controller
- [ ] Tôi biết viết unit test với JUnit + Mockito
- [ ] Tôi giải thích được tại sao tách DTO và Entity

### Sau Sprint 2
- [ ] Tôi hiểu RAG pipeline: chunk → embed → store → retrieve → generate
- [ ] Tôi viết được FastAPI endpoints
- [ ] Tôi kết nối được Qdrant và thực hiện vector search
- [ ] Tôi gọi được Gemini API

### Sau Sprint 3
- [ ] Tôi biết 2 services giao tiếp HTTP với nhau thế nào
- [ ] Tôi hiểu async processing và tại sao cần nó
- [ ] Tôi hiểu SSE streaming

### Sau Sprint 4-5
- [ ] Tôi tạo được React app kết nối API
- [ ] App chạy end-to-end: upload → chat → nhận answer
- [ ] Tôi tự tin giải thích toàn bộ kiến trúc cho người khác

---

## 💡 Lời khuyên cuối

> **"The only way to learn to code is to code."**

1. **Đừng cầu toàn** — Code xấu chạy được > code đẹp chưa viết. Refactor sau.
2. **Đừng so sánh** — Ai cũng bắt đầu từ zero. Senior cũng từng Google "how to center a div".
3. **Commit thường xuyên** — Mỗi bước nhỏ hoàn thành = 1 commit. Đừng để 3 ngày mới commit.
4. **Đọc docs gốc** — Spring Docs, LangChain Docs > blog/tutorial random. Docs gốc luôn đúng nhất.
5. **Khi bí quá 30 phút** — Hỏi. Hỏi mình, hỏi StackOverflow, hỏi ChatGPT. Đừng stuck 3 tiếng.
6. **Ghi chép** — Viết TIL (Today I Learned) mỗi ngày. 3 tháng sau đọc lại sẽ thấy mình tiến bộ cực nhiều.

---

> Khi nào bạn sẵn sàng bắt đầu Sprint 0, cứ gọi mình. Mình sẽ hướng dẫn từng bước! 🚀
