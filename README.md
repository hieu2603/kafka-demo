# Demo Spring Boot with Apache Kafka

## Cách chạy demo

Clone dự án trên github:

```bash
  git clone https://github.com/hieu2603/kafka-demo.git
```

Di chuyển đến thư mục của dự án:

```bash
  cd kafka-demo
```

Mở terminal và chạy lệnh:

```bash
  docker-compose up --build -d && docker-compose logs -f auth-service email-service notification-service
```

Truy cập http://localhost:3000 để chạy demo.
Truy cập http://localhost:8080 để xem Kafka UI.

Xem kết quả trong terminal
