## Yêu cầu môi trường
- **JDK**: 17
- **Docker**: đã cài đặt

## Khởi tạo PostgreSQL Database bằng Docker

Chạy lệnh sau để tạo container PostgreSQL:

```bash
docker run -d \
  --name postgresqldb \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=1234 \
  -e POSTGRES_DB=postgres \
  -p 5432:5432 \
  postgres:15

## Sau khi chạy ứng dụng, mở trình duyệt và truy cập:

http://localhost:8088/gira/swagger-ui/index.html


