# Product Requirements Document — Smart Doc Assistant

> **Version**: 1.1 | **Status**: Draft | **Last updated**: 2026-08-27

---

## Product Vision

Smart Doc Assistant là kho kiến thức cá nhân có AI. Người dùng quăng vào bất kỳ tài liệu nào — file, link, ghi chú — và có thể học, ôn tập, hỏi đáp với toàn bộ kho đó bất cứ lúc nào. Giống NotebookLM nhưng tự host và có thêm tính năng research.

---

## User Persona

**"Ngan" — Sinh viên năm 1 SWE**

- Học nhiều môn cùng lúc, tự học thêm ngoài giờ
- Lưu PDF slide, copy link bài viết hay, ghi chú nhanh
- Vấn đề: tài liệu rải rác khắp nơi, không biết ôn gì, phải đọc lại từ đầu mỗi lần
- Mục tiêu: 1 nơi duy nhất để tra cứu + ôn tập nhanh

---

## Feature Map & Priority

| Epic | Tên | Priority | Ghi chú |
|------|-----|----------|---------|
| [Document Management](features/document-management/overview.md) | Quản lý tài liệu | P0 — Làm trước | Nền tảng của mọi thứ |
| [AI Chat & Q&A](features/ai-chat/overview.md) | Chat hỏi đáp với AI | P0 — Làm sau Document Management | Core value |
| [Content Generation](features/content-generation/overview.md) | AI tạo nội dung học tập | P1 — Làm sau AI Chat | Differentiator chính |
| [Web Research Agent](features/web-research-agent/overview.md) | AI tự research tài liệu | P2 — Advanced | Sau khi MVP ổn định |
| [Knowledge Organization](features/knowledge-organization/overview.md) | Tổ chức & tìm kiếm kho | P2 — Nice-to-have | Khi kho lớn dần |
| [Telegram Bot](features/telegram-bot/overview.md) | Chat & thêm tài liệu qua Telegram | P2 — Sau MVP web | Tiện hơn web trên mobile |

---

## Out of Scope

| Feature | Lý do không làm |
|---------|----------------|
| Multi-user / Authentication | Personal tool, 1 user |
| Video content (YouTube, MP4) | Cần Whisper transcription, quá phức tạp |
| Mobile app | Web responsive là đủ |
| OCR (scan ảnh thành text) | Phase sau nếu cần |
| Training / fine-tuning model | Không phải mục tiêu |

---

## Open Questions

Những câu hỏi cần chốt trước khi implement:

| ID | Câu hỏi | Impact |
|----|---------|--------|
| OQ-01 | Khi user hỏi ngoài tài liệu, AI có được dùng general knowledge không? | UX vs accuracy |
| OQ-02 | Có lưu generated content (quiz, cheat sheet) vào DB không? | Storage, UX |
| OQ-03 | Web research agent dùng search tool nào? (Tavily, SerpAPI, hay scraping) | Cost, complexity |
| OQ-04 | AI có auto-suggest tag sau khi ingest không? | UX |
| OQ-05 | Flashcard export: CSV đơn giản hay Anki .apkg? | Complexity |
