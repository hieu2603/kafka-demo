package com.example.email_service.controller;

import com.example.email_service.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/email/send")
@Slf4j
public class EmailController {

    @PostMapping
    public ResponseEntity<String> sendMail(@RequestBody UserDto dto) throws InterruptedException {
        log.info("[Email Service] Start sending at: {}", LocalTime.now());
        log.info("[Email Service] Sending email to {}", dto.getEmail());
        Thread.sleep(3000);
        log.info("[Email Service] Done at: {}", LocalTime.now());
        log.info("-------------------------------------");

        return ResponseEntity.ok("Email sent!");
    }
}
