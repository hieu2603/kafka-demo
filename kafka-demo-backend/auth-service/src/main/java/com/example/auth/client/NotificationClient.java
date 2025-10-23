package com.example.auth.client;

import com.example.auth.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service", url = "http://notification-service:8083/api/notification/send")
public interface NotificationClient {

    @PostMapping
    void sendNotification(UserDto dto);
}
