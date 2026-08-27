# UC-13 — Gen cheat sheet

## Mô tả
AI tổng hợp những thông tin quan trọng nhất từ tài liệu thành một trang tham khảo nhanh — khái niệm, công thức, syntax, bảng so sánh.

## Actor
Người dùng

## Tiền điều kiện
- Document có status READY

---

## Luồng chính

1. User chọn "Tạo Cheat Sheet" từ document detail hoặc chat
2. Hệ thống hỏi config:
   - **Chủ đề tập trung** (optional): để trống = toàn bộ tài liệu
   - **Format ưu tiên**: Bullet points / Bảng so sánh / Code snippets + giải thích / Hỗn hợp
3. AI generate cheat sheet dưới dạng markdown có cấu trúc
4. Render đẹp trong UI
5. Nút: Copy / Download .md / Lưu thành Note

---

## Luồng thay thế

*(không có luồng thay thế đặc biệt — handle như các UC generation khác)*

---

## Acceptance Criteria

```
AC-13-1: Cấu trúc rõ ràng
  GIVEN user request cheat sheet từ tài liệu về Design Patterns
  WHEN AI generate
  THEN output có headings (H2/H3) phân chia rõ ràng
   AND mỗi phần ngắn gọn, súc tích
   AND không quá 2 trang A4 khi in (với độ dài "Vừa")

AC-13-2: Code snippets
  GIVEN tài liệu có ví dụ code
  WHEN format "Code snippets" được chọn
  THEN cheat sheet bao gồm code blocks với syntax highlight đúng ngôn ngữ

AC-13-3: Download
  GIVEN user click "Download .md"
  THEN file .md được download với tên "cheatsheet-[tên doc]-[ngày].md"
```
