# Feature: Content Generation

## Mục tiêu

AI tự động tạo nội dung học tập từ tài liệu — tóm tắt, quiz, cheat sheet, flashcard. Người dùng không cần tự ngồi làm, chỉ cần chọn loại nội dung muốn tạo.

---

## Use Cases

| ID | Tên | File |
|----|-----|------|
| UC-11 | Tóm tắt tài liệu | [uc-11-summarize.md](uc-11-summarize.md) |
| UC-12 | Gen quiz / câu hỏi ôn tập | [uc-12-quiz.md](uc-12-quiz.md) |
| UC-13 | Gen cheat sheet | [uc-13-cheat-sheet.md](uc-13-cheat-sheet.md) |
| UC-14 | Gen flashcards | [uc-14-flashcards.md](uc-14-flashcards.md) |

---

## Trigger Points

Tất cả UC trong epic này có thể được trigger từ:
- Quick action trong trang chi tiết document
- Lệnh trong chat (VD: "tóm tắt tài liệu này cho tôi")
- Menu riêng "Generate" trên document

---

## Output chung

Tất cả generated content đều có thể:
- **Copy** nội dung
- **Lưu thành Note** mới trong kho (tạo document type NOTE)
- **Download** file .md
