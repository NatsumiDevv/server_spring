package com.devPn.socialmedias.service;


import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.RoomMapper;
import com.devPn.socialmedias.model.Message;
import com.devPn.socialmedias.model.Room;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.RoomRequest;
import com.devPn.socialmedias.model.response.RoomResponse;
import com.devPn.socialmedias.reponsitory.MessageRepository;
import com.devPn.socialmedias.reponsitory.RoomRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private UserRepository userRepository;





    public RoomResponse conversations(RoomRequest roomRequest) {
        Set<User> users = new HashSet<>();
        if(roomRequest.getMembers().size()<2) {
            throw new AppException(ErrorCode.SIZE_MEMBER);
        }
        Optional<Room> existingRoom   = roomRepository.findRoomByUsers(roomRequest.getMembers());
        if (existingRoom.isPresent()) {
            throw new AppException(ErrorCode.ROOM_EXISTING);
        }
        for(Integer userId: roomRequest.getMembers()){
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new RuntimeException("User not found with ID: " + userId));
            users.add(user);
        }

        Room room = new Room();
        room.setUsers(users);
        return roomMapper.roomToResponse(roomRepository.save(room));

    }

    public List<RoomResponse> getConversationsByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found with ID: " + userId));
        Set<Room> rooms = user.getRooms();
        return rooms.stream()
                .map(roomMapper::roomToResponse)
                .collect(Collectors.toList());
    }

    public RoomResponse getConversationsByUsers(Integer senderId, Integer receiverId) {

        if (senderId == null || receiverId == null) {
            throw new IllegalArgumentException("Both senderId and receiverId must be provided.");
        }

        // Tạo danh sách userIds từ senderId và receiverId
        Set<Integer> userIds = new HashSet<>(Arrays.asList(senderId, receiverId));

        // Gọi RoomRepository để tìm Room phù hợp với hai userId
        Room room = roomRepository.findRoomByUsers(userIds).orElseThrow(()-> new AppException(ErrorCode.ROOM_NOT_FOUND));

        log.info(room.getId()+"");


        // Chuyển đổi Room thành RoomResponse và trả về
        return roomMapper.roomToResponse(room);
    }

}
