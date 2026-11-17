# Smart Doc Assistant

Kho tài liệu cá nhân có AI: upload PDF/TXT → hỏi đáp streaming kèm trích dẫn nguồn.

## Quick start (dev)

```bash
# 1. Hạ tầng: Postgres 18 + Qdrant (cần Docker Desktop đang chạy)
cd docker && docker compose up -d && cd ..

# 2. Cấu hình local (dev defaults đã dùng được, tùy chọn chỉnh .env)
cp backend-api/.env.example backend-api/.env

# 3. Chạy backend
cd backend-api && ./mvnw spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui.html


