# UC-18 — Gắn tag cho tài liệu

## Mô tả
Người dùng gắn tag tự do để phân loại tài liệu theo chủ đề, môn học, hay bất kỳ nhóm nào người dùng muốn.

## Actor
Người dùng

---

## Luồng chính

1. User mở chi tiết document
2. User click "Add tag"
3. Hệ thống hiện input: gợi ý tags đã dùng trước đó, user có thể chọn hoặc gõ tag mới
4. User nhấn Enter để thêm tag
5. Tag xuất hiện ngay trên document
6. Trong trang danh sách, user có thể click vào tag để filter

---

## Luồng thay thế

| Tình huống | Xử lý |
|-----------|-------|
| User gắn tag trùng đã có trên document | Bỏ qua, không thêm duplicate |
| User xóa tag | Click vào icon X trên tag badge |

---

## Acceptance Criteria

```
AC-18-1: Thêm tag mới
  GIVEN user gõ "Golang" và nhấn Enter
  THEN tag "Golang" xuất hiện trên document ngay lập tức

AC-18-2: Gợi ý tag
  GIVEN user đã từng dùng tag "Java", "Algorithms"
  WHEN user click Add tag
  THEN hệ thống gợi ý các tag đã có để chọn nhanh

AC-18-3: Filter theo tag
  GIVEN user click tag "Golang" trong danh sách
  THEN chỉ hiển thị documents có gắn tag "Golang"

AC-18-4: Xóa tag
  GIVEN document có tag "Golang"
  WHEN user click X trên tag
  THEN tag bị xóa khỏi document ngay lập tức
```
