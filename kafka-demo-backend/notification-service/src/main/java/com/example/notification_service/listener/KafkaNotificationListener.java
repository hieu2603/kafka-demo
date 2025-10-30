package com.example.notification_service.listener;

import com.example.notification_service.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@Slf4j
public class KafkaNotificationListener {

    @KafkaListener(topics = "user-registered", groupId = "notification-service-group")
    public void consume(UserDto dto) throws InterruptedException {
        log.info("[Notification Service] Start processing at: {}", LocalTime.now());
        log.info("[Notification Service] Sending notification to {}", dto);
        Thread.sleep(1000);
        log.info("[Notification Service] Done at: {}", LocalTime.now());
        log.info("-------------------------------------");
    }
}
