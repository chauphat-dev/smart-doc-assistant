# UC-10 — Xem & quản lý lịch sử chat

## Mô tả
Người dùng xem lại các conversation cũ, mở tiếp, hoặc xóa khi không cần.

## Actor
Người dùng

---

## Luồng chính

1. Sidebar hiển thị danh sách conversations, sắp xếp theo ngày gần nhất
2. User click vào conversation
3. Hệ thống load toàn bộ messages của conversation đó
4. User cuộn đọc lại lịch sử
5. User có thể tiếp tục chat trong conversation này

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| User muốn xóa conversation | Hiện confirmation → xóa conversation + messages, không ảnh hưởng documents |
| Conversation dùng document đã bị xóa | Lịch sử chat cũ vẫn hiển thị, chỉ các câu hỏi mới không còn truy cập được document đó |

---

## Acceptance Criteria

```
AC-10-1: Load lịch sử
  GIVEN conversation có nhiều messages
  WHEN user click vào conversation
  THEN tất cả messages hiển thị đúng thứ tự (cũ → mới)
   AND tự cuộn xuống message mới nhất

AC-10-2: Xóa conversation
  GIVEN user xác nhận xóa
  WHEN xóa hoàn thành
  THEN conversation biến mất khỏi sidebar
   AND documents liên quan không bị ảnh hưởng

AC-10-3: Tiếp tục chat
  GIVEN user đang xem conversation cũ
  WHEN user gõ message mới
  THEN AI vẫn nhớ context của toàn bộ conversation đó
```
