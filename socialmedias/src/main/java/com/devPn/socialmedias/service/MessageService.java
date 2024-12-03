package com.devPn.socialmedias.service;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.MessageMapper;
import com.devPn.socialmedias.model.Message;
import com.devPn.socialmedias.model.Room;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.MessageRequest;
import com.devPn.socialmedias.model.response.MessageResponse;
import com.devPn.socialmedias.reponsitory.MessageRepository;
import com.devPn.socialmedias.reponsitory.RoomRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageMapper messageMapper;

    public MessageResponse addMessageToRoom(MessageRequest messageRequest) {
        Room room = roomRepository.findById(messageRequest.getRoomId()).orElseThrow(
                ()->new AppException(ErrorCode.ROOM_NOT_FOUND));
        User sender = userRepository.findById(messageRequest.getSenderId()).orElseThrow(
                () -> new RuntimeException("Sender not found with ID: " + messageRequest.getSenderId()));
        User receiver = userRepository.findById(messageRequest.getReceiverId()).orElseThrow(
                () -> new RuntimeException("Receiver not found with ID: " + messageRequest.getReceiverId()));
        Message message = new Message();
        message.setRoom(room);
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageRequest.getContent());

        return messageMapper.messageToMessageResponse(messageRepository.save(message));
    }

    public List<MessageResponse> getMessages (Integer roomId){
        roomRepository.findById(roomId).orElseThrow(()->new AppException(ErrorCode.ROOM_NOT_FOUND));
        List<Message> messages = messageRepository.findByRoom_Id(roomId);
        return  messages.stream()
                .map(messageMapper::messageToMessageResponse)
                .collect(Collectors.toList());

    }



}
