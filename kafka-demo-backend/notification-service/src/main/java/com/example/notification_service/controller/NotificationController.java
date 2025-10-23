package com.example.notification_service.controller;

import com.example.notification_service.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/notification/send")
@Slf4j
public class NotificationController {

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody UserDto dto) throws InterruptedException {
        log.info("[Notification Service] Start processing at: {}", LocalTime.now());
        log.info("[Notification Service] Sending notification to {}", dto.getEmail());
        Thread.sleep(1000);
        log.info("[Notification Service] Done at: {}", LocalTime.now());
        log.info("-------------------------------------");

        return ResponseEntity.ok("Notification sent!");
    }
}
