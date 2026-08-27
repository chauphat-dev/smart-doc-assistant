# UC-05 — Xem danh sách tài liệu

## Mô tả
Người dùng xem toàn bộ tài liệu đã thêm vào hệ thống, có thể lọc và sắp xếp.

## Actor
Người dùng

---

## Luồng chính

1. User mở trang Documents
2. Hệ thống hiển thị danh sách, mỗi item gồm:
   - Tên tài liệu
   - Loại (PDF / TXT / URL / Note) — icon badge
   - Status với màu sắc phân biệt (PROCESSING / READY / ERROR)
   - Ngày thêm vào
3. User có thể lọc theo: loại tài liệu, status
4. User có thể sắp xếp theo: ngày thêm (mới nhất / cũ nhất), tên (A-Z)
5. Documents đang PROCESSING tự động refresh status mà không cần reload trang

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Chưa có document nào | Hiện empty state: "Chưa có tài liệu nào. Thêm file, URL hoặc ghi chú để bắt đầu." + quick action buttons |
| Có document status ERROR | Hiện icon cảnh báo + tooltip lý do lỗi khi hover |

---

## Acceptance Criteria

```
AC-05-1: Hiển thị đầy đủ
  GIVEN có ≥ 1 document trong hệ thống
  WHEN user mở trang Documents
  THEN tất cả documents hiển thị với: tên, loại, status, ngày thêm

AC-05-2: Empty state
  GIVEN chưa có document nào
  WHEN user mở trang
  THEN hiển thị hướng dẫn và nút quick action để thêm tài liệu

AC-05-3: Filter theo status
  GIVEN có documents với nhiều status khác nhau
  WHEN user chọn filter "Error"
  THEN chỉ hiển thị documents có status ERROR

AC-05-4: Auto-refresh status
  GIVEN có document đang PROCESSING
  WHEN processing hoàn thành
  THEN status tự động cập nhật thành READY mà không cần user reload trang
```
