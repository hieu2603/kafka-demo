package com.example.auth.client;

import com.example.auth.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "http://email-service:8082/api/email/send")
public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody UserDto dto);
}
