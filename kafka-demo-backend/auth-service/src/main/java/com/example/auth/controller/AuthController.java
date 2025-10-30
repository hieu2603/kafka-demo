package com.example.auth.controller;

import com.example.auth.client.EmailClient;
import com.example.auth.client.NotificationClient;
import com.example.auth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    private final EmailClient emailClient;
    private final NotificationClient notificationClient;

    @PostMapping("/register-sync")
    public ResponseEntity<String> registerSync(@RequestBody UserDto dto) {
        long start = System.currentTimeMillis();
        log.info("[Auth Service] Registering user (SYNC): {}", dto.getEmail());
        log.info("[Auth Service] Start time: {}", LocalTime.now());

        // Gửi email
        emailClient.sendEmail(dto);

        // Gửi notification
        notificationClient.sendNotification(dto);

        long end = System.currentTimeMillis();
        log.info("[Auth Service] Finish time: {}", LocalTime.now());
        double totalTimeSeconds = (end - start) / 1000.0;
        log.info("[Auth Service] Total time: {}s", String.format("%.3f", totalTimeSeconds));
        log.info("-------------------------------------");

        return ResponseEntity.ok("User registered (SYNC)");
    }

    @PostMapping("/register-async")
    public ResponseEntity<String> registerAsync(@RequestBody UserDto dto) {
        long start = System.currentTimeMillis();
        log.info("[Auth Service] Registering user (ASYNC): {}", dto.getEmail());
        log.info("[Auth Service] Start time: {}", LocalTime.now());

        kafkaTemplate.send("user-registered", UUID.randomUUID().toString(), dto);
        log.info("[Auth Service] Published message to Kafka topic 'user-registered'");

        long end = System.currentTimeMillis();
        log.info("[Auth Service] Finish time: {}", LocalTime.now());
        double totalTimeSeconds = (end - start) / 1000.0;
        log.info("[Auth Service] Total time: {}s", String.format("%.3f", totalTimeSeconds));
        log.info("-------------------------------------");

        return ResponseEntity.ok("User registered (ASYNC)");
    }
}
