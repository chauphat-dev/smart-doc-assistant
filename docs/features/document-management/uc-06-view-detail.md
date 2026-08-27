# UC-06 — Xem chi tiết tài liệu

## Mô tả
Người dùng click vào một tài liệu để xem thông tin đầy đủ và có thể thực hiện các action liên quan.

## Actor
Người dùng

---

## Luồng chính

1. User click vào tên tài liệu trong danh sách
2. Hệ thống hiển thị panel/trang chi tiết với:
   - Tên, loại, ngày thêm
   - Status hiện tại
   - Số chunks đã tạo (nếu READY)
   - Source URL gốc nếu là loại URL (link clickable)
   - Preview 300-500 ký tự đầu của nội dung
3. Các quick action:
   - "Chat với tài liệu này" → tạo conversation mới với doc này
   - "Tạo bài ôn tập" → shortcut đến feature Content Generation
   - "Xóa" → UC-07

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Document đang PROCESSING | Ẩn/disable các quick action, hiện progress spinner |
| Document status ERROR | Hiện message lỗi chi tiết + nút "Thử lại" để re-process |

---

## Acceptance Criteria

```
AC-06-1: Hiển thị đủ thông tin
  GIVEN document có status READY
  WHEN user click vào document
  THEN hiển thị: tên, loại, ngày, số chunks, preview nội dung
   AND các quick action đều clickable

AC-06-2: Document PROCESSING
  GIVEN document có status PROCESSING
  WHEN user mở chi tiết
  THEN quick action "Chat" và "Tạo bài ôn tập" bị disabled
   AND hiển thị progress indicator

AC-06-3: Document ERROR
  GIVEN document có status ERROR
  WHEN user mở chi tiết
  THEN hiển thị message lỗi đầy đủ
   AND có nút "Thử lại" để re-trigger processing
```
