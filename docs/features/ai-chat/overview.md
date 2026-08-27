# Feature: AI Chat & Q&A

## Mục tiêu

Người dùng đặt câu hỏi tự nhiên bằng ngôn ngữ thường ngày. AI tìm kiếm trong tài liệu đã chọn, trả lời có trích dẫn nguồn cụ thể, response stream từng từ.

---

## Use Cases

| ID | Tên | File |
|----|-----|------|
| UC-08 | Tạo conversation mới | [uc-08-create-conversation.md](uc-08-create-conversation.md) |
| UC-09 | Chat hỏi đáp với AI | [uc-09-chat-qa.md](uc-09-chat-qa.md) |
| UC-10 | Xem & quản lý lịch sử chat | [uc-10-chat-history.md](uc-10-chat-history.md) |

---

## Nguyên tắc AI trong Epic này

| Nguyên tắc | Mô tả |
|-----------|-------|
| Grounded responses | AI chỉ trả lời dựa trên nội dung tài liệu đã chọn — không tự bịa |
| Cite sources | Mỗi câu trả lời đều kèm trích dẫn nguồn (tên file, đoạn văn) |
| Honest when unknown | Nếu không tìm thấy thông tin, nói thẳng thay vì hallucinate |
| Context memory | Nhớ lịch sử trong cùng 1 conversation |
