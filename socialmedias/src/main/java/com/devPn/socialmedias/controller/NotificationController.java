package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.model.Notification;
import com.devPn.socialmedias.model.response.NotificationResponse;
import com.devPn.socialmedias.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<List<NotificationResponse>> getAllById(@PathVariable int id){
        List<NotificationResponse>  notificationList = notificationService.getAllNotificationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(notificationList);
    }
}
