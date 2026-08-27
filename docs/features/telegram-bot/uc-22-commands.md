# UC-22 — Quick commands

## Mô tả
Bot hỗ trợ các lệnh nhanh với prefix `/` để thực hiện những tác vụ phổ biến mà không cần gõ câu đầy đủ.

## Actor
Người dùng

---

## Danh sách commands

| Command | Mô tả | Ví dụ |
|---------|-------|-------|
| `/start` | Giới thiệu bot và danh sách lệnh | `/start` |
| `/list` | Xem danh sách tài liệu trong kho | `/list` |
| `/summary [tên file]` | Tóm tắt nhanh một tài liệu | `/summary golang-tour.pdf` |
| `/quiz [tên file]` | Gen 5 câu hỏi nhanh từ tài liệu | `/quiz design-patterns.pdf` |
| `/search [từ khóa]` | Tìm kiếm trong kho | `/search singleton pattern` |
| `/help` | Hiện lại danh sách lệnh | `/help` |

---

## Luồng chính — `/list`

1. User gõ `/list`
2. Bot trả về danh sách tài liệu theo format:
   ```
   📚 Kho của bạn (5 tài liệu):
   1. golang-tour.pdf — Ready
   2. design-patterns.pdf — Ready
   3. spring-boot-notes.txt — Ready
   4. refactoring.pdf — Processing...
   5. https://go.dev/doc — Ready
   ```

## Luồng chính — `/summary`

1. User gõ `/summary [tên]`
2. Bot tìm tài liệu khớp tên (fuzzy match)
3. Nếu tìm thấy: bot trả về tóm tắt ngắn (3-5 điểm chính)
4. Nếu không tìm thấy: bot gợi ý tài liệu có tên gần giống

## Luồng chính — `/quiz`

1. User gõ `/quiz [tên]`
2. Bot gen 5 câu trắc nghiệm, gửi từng câu một
3. User reply số đáp án (1/2/3/4)
4. Bot chấm và giải thích ngay sau mỗi câu

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Lệnh không hợp lệ | Bot trả về danh sách lệnh hợp lệ: "Lệnh không hợp lệ. Gõ /help để xem hướng dẫn." |
| `/summary` không có tên file | Bot hỏi lại: "Bạn muốn tóm tắt tài liệu nào? Gõ /list để xem danh sách." |
| Tài liệu đang PROCESSING | Bot báo "Tài liệu đang xử lý, thử lại sau ít phút nhé." |

---

## Acceptance Criteria

```
AC-22-1: /list trả về đúng
  GIVEN kho có 5 documents
  WHEN user gõ /list
  THEN bot liệt kê đủ 5 tài liệu với tên và status

AC-22-2: /summary fuzzy match
  GIVEN kho có file "golang-tour.pdf"
  WHEN user gõ /summary golang
  THEN bot tìm đúng file và trả về tóm tắt

AC-22-3: /quiz tương tác
  GIVEN user gõ /quiz với tên file hợp lệ
  WHEN bot gen câu hỏi
  THEN bot gửi từng câu một, chờ user trả lời trước khi gửi câu tiếp theo

AC-22-4: /help luôn hoạt động
  GIVEN bất kỳ lúc nào
  WHEN user gõ /help
  THEN bot trả về danh sách đầy đủ commands với mô tả ngắn
```
