# Document Management — Tasks

> **Status**: Placeholder — điền vào khi bắt đầu Sprint 1

---

## Hướng dẫn dùng file này

Mỗi UC trong feature này sẽ được chia thành các task nhỏ khi implement. Ghi vào đây để track tiến độ và không bỏ sót.

---

## UC-01: Upload PDF

- [ ] Tạo upload endpoint nhận multipart file
- [ ] Validate kích thước và định dạng phía server
- [ ] Lưu file vào storage
- [ ] Lưu metadata vào DB với status UPLOADING
- [ ] Gọi AI service để ingest (async)
- [ ] Cập nhật status sau khi AI service trả về

## UC-02: Upload TXT

- [ ] Tái sử dụng upload endpoint từ UC-01
- [ ] Xử lý đọc UTF-8, detect encoding sai

## UC-03: Add URL

- [ ] Tạo endpoint nhận URL
- [ ] Crawl HTML, strip boilerplate
- [ ] Extract title từ `<title>` tag
- [ ] Lưu source_url vào metadata
- [ ] Ingest nội dung như file thông thường

## UC-04: Add Note

- [ ] Tạo endpoint nhận plain text
- [ ] Auto-generate title nếu không có
- [ ] Ingest ngay (sync, không cần async vì không có file)

## UC-05: View List

- [ ] Endpoint GET danh sách documents với pagination
- [ ] Filter theo loại, status
- [ ] Frontend: auto-refresh status documents đang PROCESSING

## UC-06: View Detail

- [ ] Endpoint GET chi tiết 1 document
- [ ] Trả về preview nội dung (500 ký tự đầu)

## UC-07: Delete

- [ ] Endpoint DELETE document
- [ ] Xóa file khỏi storage
- [ ] Xóa vectors khỏi Qdrant
- [ ] Xóa record trong DB
