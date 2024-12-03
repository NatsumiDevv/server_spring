package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.model.Message;
import com.devPn.socialmedias.model.request.RoomRequest;
import com.devPn.socialmedias.model.response.RoomResponse;
import com.devPn.socialmedias.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/conversation")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<RoomResponse> conversation (@RequestBody RoomRequest roomRequest) {
        for(Integer userId : roomRequest.getMembers()){
            log.info(userId+"");
        }
        RoomResponse roomResponse = roomService.conversations(roomRequest);
        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<RoomResponse>> getConversationsByUserId(@PathVariable Integer userId) {
        List<RoomResponse> rooms = roomService.getConversationsByUserId(userId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/getConversation")
    public ResponseEntity<RoomResponse> getConversationsByUsers (@RequestParam Integer senderId , @RequestParam Integer receiverId){
        RoomResponse roomResponse = roomService.getConversationsByUsers(senderId, receiverId);
        return ResponseEntity.status(HttpStatus.OK).body(roomResponse);
    }



}
