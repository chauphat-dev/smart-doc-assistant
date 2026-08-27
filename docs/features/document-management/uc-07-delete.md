# UC-07 — Xóa tài liệu

## Mô tả
Người dùng xóa một tài liệu khỏi kho. Hệ thống xóa toàn bộ dữ liệu liên quan (file, metadata, vectors).

## Actor
Người dùng

## Tiền điều kiện
- Document đang ở status READY hoặc ERROR

---

## Luồng chính

1. User click icon xóa trên document
2. Hệ thống hiện confirmation dialog: *"Xóa '[tên tài liệu]'? Hành động này không thể hoàn tác."*
3. User nhấn "Xóa"
4. Hệ thống xóa: file gốc + metadata + vectors trong vector store
5. Document biến mất khỏi danh sách

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| User nhấn Cancel | Đóng dialog, không thay đổi gì |
| Document đang PROCESSING | Nút xóa disabled, tooltip "Đợi xử lý xong mới có thể xóa" |
| Document đang được dùng trong conversation | Cảnh báo "Tài liệu này đang dùng trong [X] conversation. Xóa sẽ ảnh hưởng đến các chat đó." — vẫn cho phép xóa |

---

## Acceptance Criteria

```
AC-07-1: Xóa thành công
  GIVEN user xác nhận xóa document READY hoặc ERROR
  WHEN xóa hoàn thành
  THEN document không còn trong danh sách
   AND vectors liên quan đã bị xóa khỏi vector store
   AND file gốc đã bị xóa khỏi storage

AC-07-2: Cancel không xóa
  GIVEN confirmation dialog đang mở
  WHEN user nhấn Cancel hoặc click ra ngoài
  THEN dialog đóng, document vẫn tồn tại

AC-07-3: Không xóa được khi PROCESSING
  GIVEN document có status PROCESSING
  THEN nút xóa bị disabled
   AND tooltip giải thích lý do

AC-07-4: Xóa không ảnh hưởng conversation cũ
  GIVEN document đã bị xóa
  WHEN user mở conversation cũ có dùng document này
  THEN lịch sử chat vẫn hiển thị bình thường
   AND chỉ các câu hỏi mới không còn tham chiếu được document đó
```
