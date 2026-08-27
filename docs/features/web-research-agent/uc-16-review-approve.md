# UC-16 — Review & approve tài liệu tìm được

## Mô tả
Sau khi AI research xong, người dùng xem qua từng nguồn đề xuất và quyết định cái nào được lưu vào kho.

## Actor
Người dùng

## Tiền điều kiện
- UC-15 đã hoàn thành với danh sách nguồn gợi ý

---

## Luồng chính

1. User xem danh sách nguồn AI đề xuất
2. Với mỗi nguồn, user có thể:
   - **"Lưu vào kho"** — đánh dấu để save
   - **"Xem trước"** — mở URL trong tab mới hoặc preview panel
   - **"Bỏ qua"** — loại khỏi danh sách
3. User nhấn **"Lưu tất cả đã chọn"**
4. Hệ thống ingest từng URL đã chọn (trigger UC-03 cho mỗi URL)
5. Progress bar hiện cho từng URL đang ingest
6. Kết quả: "X tài liệu đã lưu thành công, Y thất bại" kèm lý do lỗi nếu có

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Một số URL ingest thất bại | Hiển thị rõ URL nào lỗi, lý do, cho phép retry riêng từng URL |
| User muốn approve tất cả | Nút "Lưu tất cả" không cần check từng cái |

---

## Acceptance Criteria

```
AC-16-1: Batch save
  GIVEN user chọn 5 trong 8 nguồn được đề xuất
  WHEN user nhấn "Lưu tất cả đã chọn"
  THEN 5 documents được tạo từ 5 URL đó
   AND progress hiển thị cho từng URL
   AND kết quả cuối: X thành công, Y thất bại (với lý do cụ thể)

AC-16-2: Một phần thất bại không block phần còn lại
  GIVEN 1 trong 5 URL bị lỗi khi crawl
  WHEN ingest chạy
  THEN 4 URL còn lại vẫn được xử lý bình thường
   AND chỉ URL lỗi được báo riêng

AC-16-3: Không tự lưu
  GIVEN AI đã research xong
  THEN không có document nào được tạo tự động nếu user chưa approve
```
