# Feature: Web Research Agent

## Mục tiêu

Thay vì tự đi tìm tài liệu, người dùng "giao việc" cho AI: *"Tôi muốn học Golang concurrency"* — AI tự research web, đánh giá chất lượng nguồn, trình bày kết quả để user review và chọn lưu vào kho.

---

## Use Cases

| ID | Tên | File |
|----|-----|------|
| UC-15 | Yêu cầu AI research một topic | [uc-15-research-topic.md](uc-15-research-topic.md) |
| UC-16 | Review & approve tài liệu tìm được | [uc-16-review-approve.md](uc-16-review-approve.md) |

---

## Luồng tổng quan

```
User nhập topic
      ↓
AI search web → evaluate quality → filter
      ↓
AI trình bày danh sách nguồn gợi ý + lý do
      ↓
User review từng nguồn → approve / reject
      ↓
Hệ thống ingest các nguồn được approve (UC-03)
```

---

## Nguyên tắc của Research Agent

| Nguyên tắc | Mô tả |
|-----------|-------|
| Chất lượng trên số lượng | Ưu tiên 5 nguồn tốt hơn 20 nguồn rác |
| Transparent | Luôn giải thích tại sao chọn nguồn này |
| User làm chủ | AI chỉ đề xuất, user quyết định lưu cái gì |
| Không tự lưu | Không tự động lưu mà không có approval của user |
