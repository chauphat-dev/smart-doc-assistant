# UC-08 — Tạo conversation mới

## Mô tả
Người dùng tạo phiên chat mới và chọn tài liệu nào AI sẽ tham khảo khi trả lời.

## Actor
Người dùng

## Tiền điều kiện
- Có ít nhất 1 document với status READY

---

## Luồng chính

1. User click "New Chat" trên sidebar
2. Hệ thống hiện modal chọn tài liệu:
   - Danh sách documents READY với checkbox
   - Option "Tất cả tài liệu"
3. User chọn documents và đặt tên conversation (optional)
4. User nhấn "Bắt đầu chat"
5. Hệ thống tạo conversation, chuyển đến giao diện chat
6. AI gửi greeting: *"Xin chào! Tôi đã sẵn sàng với [X tài liệu]. Bạn muốn hỏi gì?"*

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Không có document READY nào | Hiện message "Bạn cần upload ít nhất 1 tài liệu trước." + link đến trang upload |
| User không chọn document nào | Nút "Bắt đầu chat" bị disabled |
| User không nhập tên | Tự generate tên = "Chat [DD/MM/YYYY HH:mm]" |

---

## Acceptance Criteria

```
AC-08-1: Tạo thành công
  GIVEN user chọn ≥ 1 document READY và nhấn Bắt đầu
  WHEN conversation được tạo
  THEN user được chuyển đến trang chat
   AND AI gửi greeting message ngay

AC-08-2: Không có tài liệu READY
  GIVEN tất cả documents đều đang PROCESSING hoặc ERROR
  WHEN user click New Chat
  THEN hiển thị hướng dẫn thêm / chờ tài liệu

AC-08-3: Nút disabled khi chưa chọn
  GIVEN modal đang mở, chưa chọn document nào
  THEN nút "Bắt đầu chat" bị disabled
```
