# UC-12 — Gen quiz / câu hỏi ôn tập

## Mô tả
AI tạo bộ câu hỏi từ nội dung tài liệu. Người dùng trả lời tương tác và nhận kết quả + giải thích.

## Actor
Người dùng

## Tiền điều kiện
- Document có status READY

---

## Luồng chính

1. User chọn "Tạo Quiz" từ document detail hoặc chat
2. Hệ thống hỏi config:
   - **Số câu**: 5 / 10 / 20
   - **Loại**: Trắc nghiệm 4 đáp án / Tự luận / Đúng-Sai / Hỗn hợp
   - **Độ khó**: Cơ bản / Trung bình / Nâng cao
   - **Chủ đề** (optional): VD "chỉ hỏi về chapter 3"
3. User nhấn "Tạo Quiz"
4. AI generate câu hỏi, hiển thị dưới dạng quiz tương tác (1 câu 1 màn hình hoặc all-in-one)
5. User chọn/nhập đáp án từng câu
6. Sau khi hoàn thành, hiện kết quả: số câu đúng/sai + giải thích từng câu sai kèm trích đoạn từ tài liệu

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Tài liệu không đủ nội dung để tạo đủ số câu | AI tạo số câu có thể, thông báo "Chỉ tạo được X câu từ nội dung tài liệu" |
| User muốn sinh lại quiz khác (khác câu hỏi) | Nút "Tạo lại" — generate bộ câu hỏi mới từ tài liệu |

---

## Acceptance Criteria

```
AC-12-1: Câu trắc nghiệm hợp lệ
  GIVEN user chọn 10 câu trắc nghiệm
  WHEN AI generate xong
  THEN có đúng 10 câu (hoặc thông báo nếu ít hơn)
   AND mỗi câu có 4 đáp án, đúng 1 đáp án đúng
   AND đáp án đúng có cơ sở trong nội dung tài liệu

AC-12-2: Giải thích sau khi trả lời
  GIVEN user hoàn thành quiz
  WHEN xem kết quả
  THEN mỗi câu sai có: giải thích tại sao sai + trích đoạn từ tài liệu

AC-12-3: Chủ đề cụ thể
  GIVEN user nhập "chỉ hỏi về Singleton Pattern"
  WHEN AI generate
  THEN tất cả câu hỏi đều liên quan đến Singleton

AC-12-4: Lưu quiz
  GIVEN user muốn làm lại sau
  WHEN user click "Lưu"
  THEN quiz được lưu và có thể access lại từ trang chi tiết document
```
