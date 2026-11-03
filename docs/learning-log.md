# 📓 Learning Log — Smart Doc Assistant

> Trả lời các câu 🧠 trong plan bằng **lời của chính em** (3–5 dòng/câu). Mentor chấm từ file này; sau này đây là tài liệu ôn phỏng vấn.
>
> Mỗi câu gồm: **Câu hỏi** → **Trả lời lần 1** (nguyên văn từ buổi review) → **Feedback mentor** → **Trả lời cuối** (em tự viết lại sau khi hiểu).

---

## S1-3 — DELETE document

### Q1. Contract của DELETE là gì?

**Trả lời lần 1:** DELETE "/api/v1/documents/{id}". 204 because empty body, 404 id not found, 400 bad request id type mismatch. Empty body.

**Feedback:** ✅ Đúng. 400 cho UUID sai format đã có sẵn trong `GlobalExceptionHandler`. Sau Q2, contract có thêm **409**.

**Trả lời cuối:**
DELETE "/api/v1/documents/{id}". 204 because empty body, 404 id not found, 400 bad request id type mismatch. Empty body. If try to delete a PROCESSING file, it will return 409 


---

### Q2. Document đang `PROCESSING` — cho xóa hay chặn?

**Trả lời lần 1:** I choose (b) 409 Conflict.

**Feedback:** 🟡 Lựa chọn tốt nhưng chưa có lý do. Nghĩ về: background job `save(READY)` sau khi record đã bị xóa → chuyện gì xảy ra? Vectors Python vừa ghi vào Qdrant → ai xóa?

**Trả lời cuối:**

409 because block DELETE method if the file is processing. Or save to stack and will be executed later
If the delete is allowed while the document is PROCESSING, the background job will call save(READY) after the record is gone, and as a result, file doesnt have document and become orphan."
The vectors Python just wrote to Qdrant will be orphan, because there is no document.
---

### Q3. Hai DELETE cùng id cùng lúc — mỗi bên nhận gì? Có vi phạm idempotency không?

**Trả lời lần 1:**
- Tuần tự: First DELETE return 204 and empty body. Second one return 404 not found.
- Đồng thời (`deleteById` khi id không còn): silently ignored.
- Idempotency: it is not a bug because its idempotency is defined by the state on the server which empty body (meet DELETE).

**Feedback:** 🟡 Kết luận đúng (`deleteById` không ném lỗi; 2 lần 204 không phải bug). Lý do lệch: idempotency **không liên quan body**. Định nghĩa (RFC 9110 §9.2.2): sau 1 hay N request, **trạng thái server như nhau**. Response được phép khác nhau (204 rồi 404). Từ khóa: *check-then-act race condition*.

**Trả lời cuối:**
Still get 204, deleteById will be silently ignored. Idempotency will not be violated, because it is defined by the state of the server 


---

### Q4. `@Transactional` bảo vệ gì? Xóa DB và xóa file — thứ tự nào an toàn hơn?

**Trả lời lần 1:**
- Transactional should put in service and protect the real object, it doesn't protect record.
- The second order is safer because after file is saved if DB record failed

**Feedback:** ❌ → 🟡 Phần đầu ngược: `@Transactional` bảo vệ **thao tác DB** (record) — commit hết hoặc rollback hết; **không** bảo vệ file disk, HTTP call, vectors Qdrant. Kết luận "thứ tự 2 an toàn hơn" đúng, nhưng câu lý do bị đứt. Trả lời 2 tình huống:
- Thứ tự 1 (xóa file → DB fail, rollback): `GET /documents/{id}` trả gì? User thấy document bình thường hay hỏng?
- Thứ tự 2 (DB commit → xóa file fail): user có thấy file rác không? Ai dọn được?

Nghiên cứu thêm: `TransactionSynchronization#afterCommit`, `@TransactionalEventListener(phase = AFTER_COMMIT)`.

**Trả lời cuối:**
Transactional should put in service and protect the DB record, doesnt protect file disk,...
First order, 200 because DB rollback, users still see files but if try to interact will have 500. Cant be restored unless backup
Second order, user cant see the junks and should have a logic to check if the file related to decide deleting. 
---

### Test cases S1-3 (liệt kê trước khi code)

> Format: *given … when DELETE … then status … và DB …* — ghi rõ tầng test (controller / service / DB). Ít nhất 4 case.

Service:
  S1. given repository findById returns a PROCESSING document, when the service's delete is called, then it throws <your 409 exception> and the repository delete is never called.
  S2. findById is empty → throw NotFoundException -> repository delete is never called
  S3. a READY document → repository delete is called
  S4. an ERROR document → repository delete is called

Controller:
  C1. given the service throws ConflictException, when DELETE /api/v1/documents/{id}, then status 409 and the body is a ProblemDetail with status = 409
  C2. service throws NotFoundException → give 404 status a Problem Detail with status = 404
  C3. service completes normally → 204 status and empty body
  C4. id = "abc" → convert fail to uuid -> give MethodArgumentTypeMismatchException and service is never called then give 400 status with a Problem Detail with status = 400 
