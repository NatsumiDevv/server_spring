package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.maper.MessageMapper;
import com.devPn.socialmedias.model.Message;
import com.devPn.socialmedias.model.request.MessageRequest;
import com.devPn.socialmedias.model.response.MessageResponse;
import com.devPn.socialmedias.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageMapper messageMapper;

    @PostMapping("/addMessage")
    public ResponseEntity<MessageResponse> addMessageToRoom(@RequestBody MessageRequest messageRequest) {
        MessageResponse message = messageService.addMessageToRoom(messageRequest);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


    @MessageMapping("/chat/private")
    public void addMessageToRoomByWesocket(@Payload MessageRequest messageRequest) {
        try {
            simpMessagingTemplate.convertAndSendToUser(
                    String.valueOf(messageRequest.getReceiverId()),
                    "/queue/message",
                    messageRequest
            );
        } catch (Exception e) {
            log.error("Error processing WebSocket message: " + e.getMessage(), e);
        }
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<List<MessageResponse>> getMessages(@PathVariable("roomId") Integer roomId) {
        List<MessageResponse> messages = messageService.getMessages(roomId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @MessageMapping("/chat/test")
    public void sendTestMessage() {
        String testMessage = "This is a test message!";
        simpMessagingTemplate.convertAndSend("/topic/test", testMessage);
    }
}
