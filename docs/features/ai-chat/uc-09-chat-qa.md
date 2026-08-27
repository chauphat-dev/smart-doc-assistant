# UC-09 — Chat hỏi đáp với AI

## Mô tả
Người dùng gõ câu hỏi, AI tìm kiếm trong tài liệu đã chọn, trả lời có trích nguồn, response stream từng từ để user không phải đợi.

## Actor
Người dùng

## Tiền điều kiện
- Đang trong conversation có ít nhất 1 document READY

---

## Luồng chính

1. User gõ câu hỏi vào input box
2. User nhấn Send hoặc Enter
3. Message của user hiển thị ngay trong chat
4. Hệ thống hiện loading indicator (typing dots)
5. AI tìm kiếm chunks liên quan nhất trong vector store
6. AI generate câu trả lời dựa trên chunks tìm được + lịch sử chat
7. Response stream từng token — user thấy chữ xuất hiện dần
8. Khi stream xong, hiển thị section **"Nguồn tham khảo"** (collapsible):
   - Tên tài liệu
   - Đoạn văn được tham khảo (150 ký tự, truncated)

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| Không tìm thấy chunks liên quan | AI trả lời thẳng: "Tôi không tìm thấy thông tin này trong tài liệu bạn cung cấp." — không bịa |
| AI Service timeout | Hiện "Không nhận được phản hồi." + nút "Thử lại" |
| Input rỗng | Nút Send disabled |
| User gửi nhiều message liên tiếp nhanh | Queue messages, xử lý lần lượt, không drop |

---

## Acceptance Criteria

```
AC-09-1: Streaming response
  GIVEN user gửi câu hỏi có câu trả lời trong tài liệu
  WHEN AI bắt đầu generate
  THEN token đầu tiên xuất hiện trong < 2 giây
   AND response hiển thị dần từng từ (không hiện cả đoạn cùng lúc)

AC-09-2: Trích nguồn
  GIVEN AI trả lời dựa trên nội dung tài liệu
  WHEN response hoàn thành
  THEN section "Nguồn tham khảo" xuất hiện với: tên tài liệu + đoạn trích

AC-09-3: Không hallucinate
  GIVEN user hỏi về topic không có trong tài liệu đã chọn
  WHEN AI không tìm được chunks liên quan
  THEN AI thành thật nói không có thông tin, không tự bịa

AC-09-4: Context memory
  GIVEN user đã hỏi "Kể các design pattern" ở message trước
  WHEN user hỏi tiếp "Cái nào hay dùng nhất?"
  THEN AI hiểu "cái nào" = các design patterns đã nhắc, trả lời đúng ngữ cảnh

AC-09-5: Markdown rendering
  GIVEN AI trả lời có code block, bullet list, heading
  WHEN hiển thị trong chat
  THEN markdown được render đúng — không hiển thị ký tự **, ##, ```

AC-09-6: Nút Send disabled
  GIVEN input box đang rỗng hoặc AI đang generate
  THEN nút Send bị disabled
```
