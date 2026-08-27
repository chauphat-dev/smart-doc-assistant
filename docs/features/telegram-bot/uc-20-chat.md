# UC-20 — Hỏi đáp qua Telegram

## Mô tả
Người dùng nhắn câu hỏi tự nhiên cho bot, bot tìm trong kho tài liệu và trả lời — giống hệt AI Chat trên web nhưng ngay trong Telegram.

## Actor
Người dùng

## Tiền điều kiện
- Bot đã được setup (UC-19)
- Có ít nhất 1 document READY trong kho

---

## Luồng chính

1. User nhắn câu hỏi bất kỳ cho bot (không cần lệnh đặc biệt)
2. Bot hiện *"Đang tìm..."* để user biết đang xử lý
3. Bot tìm kiếm trong toàn bộ kho (mặc định) hoặc kho được chỉ định
4. Bot trả lời kèm tên tài liệu nguồn ở cuối message

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Không tìm thấy thông tin liên quan | Bot trả lời thẳng "Không tìm thấy thông tin này trong kho tài liệu của bạn." |
| Kho chưa có tài liệu nào | Bot nhắc "Kho của bạn đang trống. Gửi file hoặc link để thêm tài liệu nhé." |
| Câu trả lời quá dài cho 1 message Telegram | Bot chia thành nhiều message liên tiếp hoặc tóm gọn lại |

---

## Acceptance Criteria

```
AC-20-1: Trả lời đúng
  GIVEN user hỏi về nội dung có trong tài liệu
  WHEN bot xử lý xong
  THEN bot trả lời đúng nội dung kèm tên tài liệu nguồn

AC-20-2: Không cần lệnh đặc biệt
  GIVEN user nhắn câu hỏi tự nhiên (không có / ở đầu)
  THEN bot hiểu đây là câu hỏi và xử lý như chat Q&A

AC-20-3: Phản hồi trong thời gian hợp lý
  GIVEN user gửi câu hỏi
  THEN bot bắt đầu phản hồi (ít nhất "Đang tìm...") trong vòng 2 giây
```
