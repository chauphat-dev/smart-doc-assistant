# UC-17 — Tìm kiếm trong kho tài liệu

## Mô tả
Người dùng tìm kiếm tài liệu hoặc nội dung cụ thể trong toàn bộ kho bằng từ khóa hoặc câu hỏi tự nhiên.

## Actor
Người dùng

---

## Luồng chính

1. User gõ vào search bar (global, ở top của app)
2. Hệ thống tìm kiếm song song theo 2 mode:
   - **Keyword**: tìm exact match trong tên document và metadata
   - **Semantic**: tìm theo nghĩa trong nội dung (vector search)
3. Hiển thị kết quả với 2 section rõ ràng: "Tài liệu khớp tên" và "Nội dung liên quan"
4. Mỗi kết quả semantic hiển thị đoạn trích nội dung liên quan (highlight từ khóa)
5. User click vào kết quả → mở chi tiết document

---

## Acceptance Criteria

```
AC-17-1: Keyword search
  GIVEN user gõ "singleton"
  THEN tất cả documents có "singleton" trong tên hoặc nội dung xuất hiện trong kết quả

AC-17-2: Semantic search
  GIVEN user gõ "cách tạo object duy nhất trong hệ thống"
  THEN document về Singleton Pattern xuất hiện dù không có exact match từ "singleton"

AC-17-3: Kết quả rỗng
  GIVEN từ khóa không có kết quả nào
  THEN hiện "Không tìm thấy tài liệu phù hợp."

AC-17-4: Tốc độ
  GIVEN kho có 50 documents
  WHEN user gõ và dừng gõ
  THEN kết quả xuất hiện trong < 1 giây
```
