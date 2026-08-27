# Feature: Document Management

## Mục tiêu

Người dùng có thể đưa tài liệu vào hệ thống dưới nhiều dạng khác nhau, theo dõi trạng thái xử lý, xem lại, và xóa khi không cần.

---

## Use Cases

| ID | Tên | File |
|----|-----|------|
| UC-01 | Upload file PDF | [uc-01-upload-pdf.md](uc-01-upload-pdf.md) |
| UC-02 | Upload file TXT | [uc-02-upload-txt.md](uc-02-upload-txt.md) |
| UC-03 | Thêm URL / web link | [uc-03-add-url.md](uc-03-add-url.md) |
| UC-04 | Thêm plain text note | [uc-04-add-note.md](uc-04-add-note.md) |
| UC-05 | Xem danh sách tài liệu | [uc-05-view-list.md](uc-05-view-list.md) |
| UC-06 | Xem chi tiết tài liệu | [uc-06-view-detail.md](uc-06-view-detail.md) |
| UC-07 | Xóa tài liệu | [uc-07-delete.md](uc-07-delete.md) |

---

## Document Status Flow

```
UPLOADING → PROCESSING → READY
                      ↘ ERROR
```

| Status | Mô tả |
|--------|-------|
| UPLOADING | Đang nhận file từ user |
| PROCESSING | Đang parse, chunk, embed |
| READY | Sẵn sàng để chat |
| ERROR | Xử lý thất bại |

---

## Loại tài liệu hỗ trợ (MVP)

| Loại | Input | Ghi chú |
|------|-------|---------|
| PDF | Upload file | Max 20MB |
| TXT | Upload file | UTF-8 |
| URL | Paste link | Crawl nội dung tự động |
| Note | Gõ/paste text | Không cần file |
