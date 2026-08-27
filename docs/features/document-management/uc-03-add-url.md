# UC-03 — Thêm URL / Web link

## Mô tả
Người dùng paste một URL (bài viết, tài liệu online, blog). Hệ thống tự crawl nội dung và lưu vào kho — không cần user tự download file.

## Actor
Người dùng

## Tiền điều kiện
- Có URL muốn lưu

---

## Luồng chính

1. User click "Add URL"
2. Hệ thống hiện input để user paste URL
3. User paste URL và nhấn "Add"
4. Hệ thống validate format URL hợp lệ
5. Hệ thống tạo document với status → PROCESSING
6. Hệ thống crawl URL: fetch HTML → bỏ nav/footer/ads → lấy nội dung chính
7. Lấy title của trang làm tên document, lưu URL gốc làm metadata
8. Chunk → embed → lưu vector
9. Status → READY

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| URL format sai (thiếu https://) | Báo lỗi "URL không hợp lệ" ngay khi user nhập |
| URL trả về 404 | Status → ERROR, "Không truy cập được trang này (404)." |
| URL yêu cầu đăng nhập (403) | Status → ERROR, "Trang yêu cầu đăng nhập, không thể tải nội dung." |
| Trang JS-heavy (React SPA) | Crawl phần text extract được, cảnh báo "Nội dung có thể không đầy đủ" |
| URL đã tồn tại trong kho | Cảnh báo "URL này đã được thêm trước đó ([tên doc])", cho phép thêm lại hoặc hủy |
| Timeout khi crawl (> 15s) | Status → ERROR, "Không tải được trang, thử lại sau." |

---

## Acceptance Criteria

```
AC-03-1: Crawl thành công
  GIVEN user nhập URL bài viết tĩnh, accessible
  WHEN crawl hoàn thành
  THEN document lưu với title = <title> của trang
   AND source URL được lưu kèm để user tham chiếu sau
   AND status = READY

AC-03-2: URL không truy cập được
  GIVEN user nhập URL trả về lỗi (404, 403, timeout)
  WHEN hệ thống cố crawl
  THEN status = ERROR với message mô tả lý do cụ thể

AC-03-3: Duplicate URL
  GIVEN URL đã có trong kho
  WHEN user nhập lại URL đó
  THEN hệ thống cảnh báo với tên document đã lưu
   AND user tự quyết định: thêm lại hoặc hủy

AC-03-4: Hiển thị nguồn
  GIVEN document được tạo từ URL
  THEN trong chi tiết document luôn hiển thị URL gốc dưới dạng link clickable
```
