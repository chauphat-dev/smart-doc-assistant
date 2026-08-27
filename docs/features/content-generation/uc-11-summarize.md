# UC-11 — Tóm tắt tài liệu

## Mô tả
AI đọc toàn bộ tài liệu và tạo bản tóm tắt theo độ chi tiết mà user chọn.

## Actor
Người dùng

## Tiền điều kiện
- Document có status READY

---

## Luồng chính

1. User chọn "Tóm tắt tài liệu" từ document detail hoặc chat
2. Hệ thống hỏi config:
   - **Độ dài**: Ngắn (3-5 điểm chính) / Vừa (~300 từ) / Dài (~1000 từ)
   - **Tập trung vào** (optional): user có thể nhập chủ đề cụ thể, VD "chỉ tóm tắt phần về concurrency"
3. AI generate và stream tóm tắt
4. Hiển thị kèm các nút: Copy / Lưu thành Note / Download .md

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Tài liệu quá ngắn (< 200 từ) | AI thông báo "Tài liệu quá ngắn để cần tóm tắt." và hiển thị nguyên văn |

---

## Acceptance Criteria

```
AC-11-1: Tóm tắt ngắn
  GIVEN user chọn độ dài "Ngắn"
  WHEN AI generate xong
  THEN output là 3-5 bullet points, mỗi bullet ≤ 20 từ
   AND toàn bộ nội dung đến từ tài liệu, không bịa

AC-11-2: Tập trung chủ đề
  GIVEN user nhập "chỉ tóm tắt phần về concurrency"
  WHEN AI generate
  THEN tóm tắt chỉ bao gồm nội dung liên quan đến concurrency trong tài liệu

AC-11-3: Lưu thành Note
  GIVEN user click "Lưu thành Note"
  WHEN lưu xong
  THEN document mới type NOTE xuất hiện trong kho
   AND title = "Tóm tắt: [tên tài liệu gốc]"
```
