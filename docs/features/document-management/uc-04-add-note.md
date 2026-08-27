# UC-04 — Thêm plain text note

## Mô tả
Người dùng gõ hoặc paste trực tiếp một đoạn văn bản (ghi chú nhanh, transcript, tóm tắt tay) mà không cần file hay URL.

## Actor
Người dùng

## Tiền điều kiện
- Đang ở trang Documents

---

## Luồng chính

1. User click "Add Note"
2. Hệ thống hiện editor với: input title (optional) + textarea nội dung
3. User gõ hoặc paste nội dung
4. User nhấn "Save"
5. Hệ thống validate nội dung không rỗng
6. Hệ thống lưu document type = NOTE
7. Status → READY ngay lập tức (không cần async vì không có file)

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Nội dung rỗng | Nút Save bị disable |
| User không nhập title | Tự generate title = 50 ký tự đầu + "..." |
| Nội dung < 50 ký tự | Cảnh báo "Nội dung quá ngắn, AI có thể không hỗ trợ tốt" nhưng vẫn cho save |

---

## Acceptance Criteria

```
AC-04-1: Lưu note thành công
  GIVEN user nhập nội dung ≥ 50 ký tự
  WHEN user nhấn Save
  THEN document mới xuất hiện với badge type "Note"
   AND status = READY ngay lập tức (không có loading)

AC-04-2: Title tự động
  GIVEN user không nhập title
  WHEN save
  THEN title = 50 ký tự đầu của nội dung + "..."

AC-04-3: Nút Save disabled
  GIVEN textarea đang rỗng
  THEN nút Save bị disabled, không thể click
```
