# UC-14 — Gen flashcards

## Mô tả
AI tạo bộ thẻ ghi nhớ format Khái niệm — Định nghĩa để học theo phương pháp lật thẻ. Có thể export để dùng với app Anki.

## Actor
Người dùng

## Tiền điều kiện
- Document có status READY

---

## Luồng chính

1. User chọn "Tạo Flashcards" từ document detail hoặc chat
2. Hệ thống hỏi config:
   - **Số thẻ**: 10 / 20 / 50
   - **Chủ đề** (optional)
3. AI generate bộ thẻ: mặt trước (khái niệm/câu hỏi) — mặt sau (định nghĩa/giải thích)
4. Hiển thị giao diện lật thẻ tương tác
5. User lật từng thẻ, đánh dấu "Đã nhớ" hoặc "Cần xem lại"
6. Nút export: Download CSV

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| User muốn làm lại (shuffle thẻ) | Nút "Xáo trộn" — hiển thị lại cùng bộ thẻ nhưng ngẫu nhiên thứ tự |

---

## Acceptance Criteria

```
AC-14-1: Flashcard hợp lệ
  GIVEN user request 20 flashcards từ tài liệu Algorithms
  WHEN AI generate
  THEN mặt trước: khái niệm/câu hỏi ngắn (≤ 15 từ)
   AND mặt sau: giải thích súc tích, đủ hiểu

AC-14-2: Tương tác lật thẻ
  GIVEN bộ flashcards đã generate
  WHEN user click vào thẻ
  THEN thẻ lật animate hiện mặt sau

AC-14-3: Tracking tiến độ
  GIVEN user đánh dấu từng thẻ Đã nhớ / Cần xem lại
  THEN hiển thị progress: X/Y thẻ đã nhớ

AC-14-4: Export CSV
  GIVEN user click "Download CSV"
  THEN file .csv được download với 2 cột: front, back
   AND format tương thích import vào Anki
```
