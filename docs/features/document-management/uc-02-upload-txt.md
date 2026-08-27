# UC-02 — Upload file TXT

## Mô tả
Người dùng upload file văn bản thuần (.txt). Hệ thống đọc nội dung, chunk và lưu vào vector store.

## Actor
Người dùng

## Tiền điều kiện
- Đang ở trang Documents
- Có file .txt trên máy tính

---

## Luồng chính

1. User nhấn "Upload" hoặc kéo thả file .txt
2. Hệ thống validate: đúng định dạng TXT, kích thước ≤ 20MB
3. Hệ thống nhận file, status → PROCESSING
4. Hệ thống đọc nội dung trực tiếp (không cần parse như PDF)
5. Chunk → embed → lưu vector
6. Status → READY

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| File encoding không phải UTF-8 | Status → ERROR, message "File không đọc được do encoding. Lưu lại với UTF-8 và thử lại." |
| File rỗng (0 bytes) | Báo lỗi "File trống, không có nội dung để xử lý." |
| File > 20MB | Báo lỗi ngay phía client |

---

## Acceptance Criteria

```
AC-02-1: Upload thành công
  GIVEN user upload file .txt UTF-8, có nội dung, ≤ 20MB
  WHEN xử lý xong
  THEN status = READY, document xuất hiện trong danh sách

AC-02-2: File rỗng
  GIVEN user upload file .txt không có nội dung
  WHEN hệ thống xử lý
  THEN status → ERROR với message phù hợp

AC-02-3: Encoding sai
  GIVEN user upload file .txt không phải UTF-8
  WHEN hệ thống đọc file
  THEN status → ERROR với hướng dẫn cụ thể
```
