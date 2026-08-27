# Feature: Telegram Bot

## Tại sao cần?

Web app không phải lúc nào cũng tiện — phải mở browser, đăng nhập, tìm đúng conversation. Trên điện thoại thì Telegram luôn mở sẵn. Telegram bot cho phép user tương tác với toàn bộ kho kiến thức ngay trong app chat quen thuộc, nhanh hơn và tiện hơn nhiều.

**Lý do chọn Telegram thay vì Messenger / Zalo:**
- Telegram BotFather: tạo bot trong 1 phút, không cần approval
- Telegram Bot API miễn phí, tài liệu tốt
- Hỗ trợ gửi file, markdown, inline buttons — đủ để làm UI đơn giản

---

## Use Cases

| ID | Tên | File |
|----|-----|------|
| UC-19 | Setup & kết nối bot | [uc-19-setup.md](uc-19-setup.md) |
| UC-20 | Hỏi đáp qua Telegram | [uc-20-chat.md](uc-20-chat.md) |
| UC-21 | Thêm tài liệu qua Telegram | [uc-21-add-document.md](uc-21-add-document.md) |
| UC-22 | Quick commands | [uc-22-commands.md](uc-22-commands.md) |

---

## Nguyên tắc thiết kế bot

| Nguyên tắc | Mô tả |
|-----------|-------|
| Personal bot | 1 user duy nhất — không cần auth phức tạp, chỉ cần whitelist chat ID |
| Stateless commands | Mỗi lệnh tự đủ nghĩa, không yêu cầu user nhớ flow |
| Graceful fallback | Khi không hiểu lệnh, hướng dẫn rõ ràng thay vì im lặng |
| Không duplicate web app | Bot làm tốt những gì nhanh và tiện — không cố làm tất cả mọi thứ như web |
