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

Mở 4 terminal và chạy lần lượt từng lệnh:

```bash
  docker-compose up --build -d
  docker logs -f auth-service
  docker logs -f email-service
  docker logs -f notification-service
```

Truy cập http://localhost:3000 để chạy demo.

Xem kết quả trong các terminal.
