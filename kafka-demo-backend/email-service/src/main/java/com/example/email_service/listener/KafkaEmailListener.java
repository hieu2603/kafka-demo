package com.example.email_service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@Slf4j
public class KafkaEmailListener {

    @KafkaListener(topics = "user-registered", groupId = "email-group")
    public void consume(String email) throws InterruptedException {
        log.info("[Email Service] Start sending at: {}", LocalTime.now());
        log.info("[Email Service] Sending email to {}", email);
        Thread.sleep(3000);
        log.info("[Email Service] Done at: {}", LocalTime.now());
        log.info("-------------------------------------");
    }
}
