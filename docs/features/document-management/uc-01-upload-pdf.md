# UC-01 — Upload file PDF

## Mô tả
Người dùng tải file PDF từ máy tính lên. Hệ thống lưu file, xử lý nội dung và chuẩn bị để sẵn sàng chat.

## Actor
Người dùng

## Tiền điều kiện
- Đang ở trang Documents
- Có file PDF trên máy tính

---

## Luồng chính

1. User nhấn "Upload" hoặc kéo thả file vào vùng upload
2. Hệ thống validate: đúng định dạng PDF, kích thước ≤ 20MB
3. Hệ thống hiển thị tên file và progress indicator
4. Hệ thống nhận file, trả về ngay status **UPLOADING → PROCESSING**
5. Hệ thống xử lý nền: parse → chunk → tạo embeddings → lưu vector
6. Status tự động cập nhật thành **READY**
7. Document xuất hiện trong danh sách với badge "Ready"

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| File > 20MB | Báo lỗi ngay phía client "File quá lớn (tối đa 20MB)", không upload |
| Sai định dạng (không phải PDF) | Báo lỗi "Chỉ hỗ trợ PDF và TXT" |
| PDF bị password protect | Status → ERROR, message "Không đọc được file. Vui lòng kiểm tra lại." |
| PDF bị corrupt | Status → ERROR, message tương tự |
| AI service timeout (> 30s) | Status → ERROR, message "Xử lý thất bại, thử lại sau" |
| Mất kết nối giữa chừng | Báo lỗi kết nối, cho phép retry |

---

## Acceptance Criteria

```
AC-01-1: Upload thành công
  GIVEN user chọn file PDF hợp lệ ≤ 20MB
  WHEN upload hoàn thành
  THEN document xuất hiện với status PROCESSING
   AND sau khi xử lý xong, status chuyển thành READY

AC-01-2: Validate kích thước — phía client
  GIVEN user chọn file > 20MB
  WHEN file được chọn (trước khi upload)
  THEN hiển thị lỗi ngay lập tức, không gửi file lên server

AC-01-3: Validate định dạng
  GIVEN user chọn file .docx, .jpg, hoặc định dạng không hỗ trợ
  WHEN file được chọn
  THEN hiển thị "Chỉ hỗ trợ PDF và TXT"

AC-01-4: PDF có password
  GIVEN user upload PDF được bảo vệ bằng password
  WHEN hệ thống cố parse
  THEN status → ERROR với message rõ ràng

AC-01-5: Upload nhiều file cùng lúc
  GIVEN user chọn nhiều file PDF
  WHEN upload bắt đầu
  THEN mỗi file xử lý độc lập
   AND progress hiển thị riêng cho từng file
```
