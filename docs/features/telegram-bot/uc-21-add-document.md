# UC-21 — Thêm tài liệu qua Telegram

## Mô tả
Người dùng gửi file hoặc link thẳng vào Telegram, bot tự động lưu vào kho — không cần mở web app.

## Actor
Người dùng

## Tiền điều kiện
- Bot đã được setup (UC-19)

---

## Luồng chính — Gửi file

1. User forward hoặc gửi file PDF / TXT vào chat bot
2. Bot xác nhận nhận được: *"Đã nhận '[tên file]'. Đang xử lý..."*
3. Bot chuyển file sang hệ thống, trigger ingest pipeline
4. Khi xong, bot thông báo: *"Đã lưu '[tên file]' vào kho. X chunks được tạo."*

## Luồng chính — Gửi link

1. User paste URL vào chat bot (không cần lệnh, bot nhận diện URL tự động)
2. Bot xác nhận: *"Đã nhận link. Đang crawl nội dung..."*
3. Bot trigger crawl và ingest
4. Khi xong: *"Đã lưu '[title trang]' vào kho."*

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| File sai định dạng (ảnh, video,...) | Bot báo "Hiện chỉ hỗ trợ file PDF và TXT." |
| File > 20MB | Bot báo "File quá lớn (tối đa 20MB)." |
| URL không truy cập được | Bot báo "Không crawl được trang này: [lý do]." |
| Ingest thất bại | Bot báo lỗi cụ thể và gợi ý thử lại |

---

## Acceptance Criteria

```
AC-21-1: Gửi file PDF thành công
  GIVEN user gửi file PDF hợp lệ ≤ 20MB
  WHEN ingest hoàn thành
  THEN bot thông báo thành công kèm số chunks
   AND document xuất hiện trong kho trên web app

AC-21-2: Gửi URL thành công
  GIVEN user paste URL vào chat
  WHEN bot nhận diện là URL và crawl xong
  THEN bot thông báo tên trang đã lưu

AC-21-3: Nhận diện URL tự động
  GIVEN user nhắn một đoạn text có chứa URL (https://...)
  THEN bot nhận diện và hỏi: "Bạn muốn lưu link này vào kho không?" [Có] [Không]
```
