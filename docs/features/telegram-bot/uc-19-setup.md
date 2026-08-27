# UC-19 — Setup & kết nối bot

## Mô tả
Người dùng tạo bot trên Telegram và cấu hình để hệ thống biết nhận lệnh từ đúng người.

## Actor
Người dùng (1 lần duy nhất, lúc setup)

---

## Luồng chính

1. User tạo bot qua @BotFather trên Telegram → nhận Bot Token
2. User lấy Telegram Chat ID của bản thân
3. User điền Bot Token và Chat ID vào file config của hệ thống
4. Hệ thống khởi động bot, gửi message test: *"Smart Doc Assistant bot đã sẵn sàng!"*
5. Từ đây user có thể nhắn tin cho bot bất cứ lúc nào

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Bot Token sai | Bot không khởi động được, log lỗi rõ ràng |
| Người lạ nhắn tin cho bot | Bot từ chối với message "Bot này là cá nhân, không phục vụ công khai." |

---

## Acceptance Criteria

```
AC-19-1: Setup thành công
  GIVEN user điền đúng Bot Token và Chat ID
  WHEN hệ thống khởi động
  THEN bot gửi message xác nhận vào Telegram của user

AC-19-2: Chặn người lạ
  GIVEN ai đó không phải user nhắn tin cho bot
  WHEN bot nhận tin nhắn
  THEN bot không xử lý và không trả lời (hoặc trả lời từ chối lịch sự)
```
