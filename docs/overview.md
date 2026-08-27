# Smart Doc Assistant — Product Overview

---

## Vấn đề

Người học ngày nay không thiếu tài liệu — họ thừa tài liệu. Slide bài giảng từ trường, bài viết hay bookmark trên Chrome, video YouTube ghi chú lại, link tài liệu official từ group chat, file PDF tự download... tất cả nằm rải rác ở những nơi khác nhau và không kết nối với nhau.

Kết quả là:
- **Tìm lại tốn thời gian**: "File đó mình lưu ở đâu rồi nhỉ?" là câu hỏi quen thuộc mỗi khi ôn thi.
- **Không có tổng quan**: Có 10 tài liệu về Design Patterns nhưng không biết mình đang thiếu kiến thức gì, biết gì rồi.
- **Ôn tập thụ động**: Đọc lại từ đầu mỗi lần — không có quiz, không có tóm tắt, không có cách nào biết mình nhớ được bao nhiêu.
- **Tìm tài liệu mất công**: Muốn học thêm về một topic, phải tự Google, tự đánh giá nguồn, tự lưu — mất hàng tiếng đồng hồ trước khi bắt đầu đọc.

---

## Giải pháp

**Smart Doc Assistant** là kho kiến thức cá nhân có AI. Người dùng đưa tài liệu vào — dù là file, link, hay ghi chú — và AI lo phần còn lại: lưu trữ, tổ chức, trả lời câu hỏi, tạo bài ôn tập, thậm chí tự đi tìm thêm tài liệu mới theo yêu cầu.

Không phải công cụ ghi chú thêm một cái nữa. Không phải search engine. Đây là **assistant thực sự** — biết nội dung tài liệu của bạn, có thể đặt câu hỏi về nó, và giúp bạn học từ nó.

---

## So sánh với các giải pháp hiện có

| | Smart Doc Assistant | NotebookLM | Notion AI | Google Drive |
|--|--|--|--|--|
| Upload file & chat | ✅ | ✅ | ✅ | ❌ |
| Thêm URL, tự crawl | ✅ | ✅ | ❌ | ❌ |
| Gen quiz / flashcard | ✅ | ❌ | ❌ | ❌ |
| AI tự research tài liệu | ✅ | ❌ | ❌ | ❌ |
| Dùng qua Telegram bot | ✅ | ❌ | ❌ | ❌ |
| Self-hosted, free | ✅ | ❌ | ❌ | ❌ |
| Video content | ❌ | ✅ | ❌ | ❌ |

Điểm khác biệt lớn nhất: **Gen nội dung học tập** (quiz, cheat sheet, flashcard) và **Research Agent** (AI tự đi tìm tài liệu) — đây là thứ NotebookLM không có.

---

## Người dùng mục tiêu

**"Ngan" — Sinh viên năm 1 ngành Software Engineering**

Ngan học 6 môn một lúc, đồng thời tự học thêm ngoài giờ để không bị tụt lại so với bạn bè. Mỗi tuần có thêm vài chục trang slide mới, vài bài viết đọc thêm, và một đống link từ thầy cô gửi trong group.

Vấn đề Ngan gặp hàng ngày:
- Tuần trước đọc bài về Singleton Pattern, hôm nay thầy hỏi thì không nhớ rõ
- Muốn ôn thi nhưng không biết bắt đầu từ đâu, đọc lại từ đầu thì không kịp
- Muốn học thêm về Golang nhưng không biết nên đọc nguồn nào, mất 2 tiếng tìm tài liệu trước khi học được gì

Ngan không cần một công cụ phức tạp. Ngan cần **một nơi dump tài liệu vào và hỏi bất cứ điều gì** — như có một người bạn đã đọc hết mọi thứ Ngan có và có thể trả lời câu hỏi bất cứ lúc nào.

---

## Hành trình người dùng điển hình

**Tình huống 1 — Sau buổi học:**
> Ngan vừa học xong buổi về Design Patterns. Có 2 file slide. Ngan upload lên Smart Doc Assistant, 30 giây sau status READY. Tối hôm đó hỏi: *"Tóm tắt lại phần Singleton và Factory Pattern"* — AI trả lời kèm trích nguồn từ đúng slide của trường.

**Tình huống 2 — Ôn thi:**
> 3 ngày trước thi, Ngan click "Tạo Quiz" trên folder các tài liệu môn học. AI gen 20 câu trắc nghiệm theo đúng nội dung đã học. Làm xong biết ngay chỗ nào còn yếu.

**Tình huống 3 — Tự học trên điện thoại:**
> Ngan đang trên xe buýt, thấy bài viết hay về Go concurrency trên Twitter. Copy link, paste vào Telegram bot của Smart Doc. Bot tự crawl và lưu vào kho. Tối về nhà mở web app, tài liệu đã ở đó sẵn sàng để chat.

**Tình huống 4 — Research:**
> Ngan muốn bắt đầu học Kubernetes nhưng không biết đọc gì trước. Gõ vào Research Agent: *"Tôi đã biết Docker cơ bản, muốn học Kubernetes — bắt đầu từ đâu?"* — AI trả về 7 nguồn curated kèm thứ tự đọc. Ngan chọn 5 cái, bot tự lưu vào kho.

---

## Các tính năng

### Document Management
Nền tảng của mọi thứ. Người dùng đưa tài liệu vào theo nhiều cách: upload file PDF/TXT, paste URL để bot tự crawl, hoặc gõ thẳng ghi chú nhanh. Hệ thống xử lý tự động — parse, chia nhỏ, tạo embeddings — và thông báo khi sẵn sàng. Người dùng không cần biết gì về quá trình phía sau.

→ [Chi tiết feature](features/document-management/overview.md)

### AI Chat & Q&A
Hỏi bất cứ điều gì về tài liệu đã lưu. AI tìm kiếm trong kho, trả lời có trích dẫn nguồn cụ thể (tên file, đoạn văn), và nhớ ngữ cảnh trong suốt cuộc hội thoại. Không hallucinate — nếu không có trong tài liệu thì nói thẳng là không có.

→ [Chi tiết feature](features/ai-chat/overview.md)

### Content Generation
Thay vì tự ngồi làm tóm tắt hay bộ câu hỏi, user yêu cầu AI tạo: tóm tắt ngắn/dài, quiz trắc nghiệm/tự luận, cheat sheet một trang, hay flashcards để lật thẻ. Output có thể lưu lại, download, hoặc tiếp tục dùng trong hệ thống.

→ [Chi tiết feature](features/content-generation/overview.md)

### Web Research Agent
User giao topic, AI tự làm phần còn lại: tìm kiếm web, đánh giá chất lượng nguồn, lọc ra những cái đáng đọc, và trình bày để user review trước khi lưu vào kho. User giữ quyền quyết định cuối cùng — AI không tự lưu gì mà chưa được approve.

→ [Chi tiết feature](features/web-research-agent/overview.md)

### Telegram Bot
Kho kiến thức không chỉ dùng được trên web. Qua Telegram bot, user có thể hỏi đáp, thêm tài liệu (gửi file hoặc paste link), và chạy quick commands như `/summary`, `/quiz` ngay trên điện thoại — tiện nhất khi đang di chuyển hoặc không mở laptop.

→ [Chi tiết feature](features/telegram-bot/overview.md)

### Knowledge Organization
Khi kho lớn lên đến vài chục tài liệu, user cần cách phân loại và tìm lại nhanh. Tags tự do để nhóm theo môn học hay chủ đề, cộng với tìm kiếm semantic — tìm theo nghĩa thay vì exact keyword.

→ [Chi tiết feature](features/knowledge-organization/overview.md)

---

## Thứ tự ưu tiên

```
MVP (làm trước)
├── Document Management     ← không có cái này, mọi thứ đều vô nghĩa
└── AI Chat & Q&A           ← core value proposition

Phase 2
└── Content Generation      ← differentiator chính so với các tool khác

Phase 3 (sau khi MVP ổn định)
├── Web Research Agent      ← advanced, cần MVP hoạt động tốt trước
├── Telegram Bot            ← convenience layer, không phải core
└── Knowledge Organization  ← chỉ cần thiết khi kho đủ lớn
```

---

## Tiêu chí thành công

Sản phẩm được coi là thành công khi:

- User có thể upload tài liệu và nhận câu trả lời chính xác trong vòng **dưới 3 phút** từ lần đầu mở app
- User **thực sự dùng** để ôn tập — không phải chỉ upload rồi bỏ đó
- Câu trả lời của AI **không hallucinate** — khi không có thông tin, nói thẳng là không có
- Telegram bot đủ tiện để user **không cần mở web** cho các tác vụ nhanh

---

## Tài liệu liên quan

| Tài liệu | Mô tả |
|---------|-------|
| [Product Requirements](product-requirements.md) | User persona chi tiết, feature priority, open questions |
| [Implementation Plan](../implementation_plan.md) | Tech stack, architecture, sprint roadmap |
