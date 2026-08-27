# UC-15 — Yêu cầu AI research một topic

## Mô tả
Người dùng nhập chủ đề muốn học. AI tự tìm kiếm web, lọc nguồn chất lượng, và đưa ra danh sách đề xuất kèm lý do.

## Actor
Người dùng

## Tiền điều kiện
- Có kết nối internet

---

## Luồng chính

1. User click "Research Topic" (trang riêng hoặc section trong sidebar)
2. User nhập topic kèm context:
   - Ví dụ: *"Golang concurrency — tôi đã biết goroutine cơ bản, muốn học channels và select"*
3. Hệ thống hỏi preferences:
   - **Loại tài liệu ưu tiên**: Official docs / Tutorial / Blog post / Video transcript
   - **Ngôn ngữ**: Tiếng Anh / Tiếng Việt / Cả hai
   - **Level**: Beginner / Intermediate / Advanced
4. User nhấn "Start Research"
5. Hệ thống hiện progress indicator với steps: "Đang tìm kiếm..." → "Đang đánh giá nguồn..." → "Đang tổng hợp..."
6. AI trả về:
   - Danh sách 5-10 nguồn gợi ý, mỗi nguồn gồm:
     - URL + Title
     - Tóm tắt 2-3 câu nội dung
     - Lý do AI chọn nguồn này
     - Level phù hợp (Beginner / Intermediate / Advanced)
   - **Learning Path**: thứ tự đọc gợi ý

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Topic quá chung chung ("lập trình") | AI hỏi clarify: "Bạn muốn tìm về ngôn ngữ/framework cụ thể nào?" — không research cho đến khi có thêm context |
| Không tìm được nguồn đủ chất lượng | Thông báo "Không tìm được nguồn phù hợp, thử đổi từ khóa hoặc mở rộng level?" |
| Topic tiếng Việt nhưng nguồn chủ yếu tiếng Anh | Thông báo "Tài liệu tiếng Việt về chủ đề này còn hạn chế, đã bổ sung nguồn tiếng Anh chất lượng." |

---

## Acceptance Criteria

```
AC-15-1: Research thành công
  GIVEN user nhập topic cụ thể với level rõ ràng
  WHEN AI research hoàn thành
  THEN hiển thị 5-10 nguồn với: URL, tóm tắt, lý do chọn, level phù hợp
   AND có Learning Path gợi ý thứ tự đọc

AC-15-2: Nguồn accessible
  GIVEN AI đề xuất danh sách
  THEN tất cả URL đều truy cập được (không 404, không yêu cầu login)

AC-15-3: Ưu tiên nguồn chất lượng
  GIVEN có nhiều nguồn về cùng topic
  THEN official docs và well-known resources được ưu tiên hơn random blog

AC-15-4: Topic quá rộng
  GIVEN user nhập "machine learning" không có context
  WHEN AI xử lý
  THEN AI hỏi làm rõ thay vì trả về kết quả generic

AC-15-5: Progress visible
  GIVEN research đang chạy
  THEN user thấy từng bước progress (không phải màn hình trắng chờ)
```
