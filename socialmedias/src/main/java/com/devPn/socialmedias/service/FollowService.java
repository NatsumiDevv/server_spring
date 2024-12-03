package com.devPn.socialmedias.service;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.FollowMapper;
import com.devPn.socialmedias.model.Enum.NotificationType;
import com.devPn.socialmedias.model.Follow;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.Follow.FollowRequest;
import com.devPn.socialmedias.model.response.Follow.FollowDTO;
import com.devPn.socialmedias.reponsitory.FollowRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import com.devPn.socialmedias.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private  UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;

    public FollowDTO add(FollowRequest followAddRequest){
        if (userService.isFollowing(followAddRequest.getUserId(), followAddRequest.getFollowingId())){
            throw  new AppException(ErrorCode.IS_FOLLOWING);
        }
        User userFollower = userRepository.findByUserId(followAddRequest.getUserId());
        User userFollowing = userRepository.findByUserId(followAddRequest.getFollowingId());
        Follow follow = Follow.builder()
                .user(userFollower)
                .following(userFollowing)
                .build();
        Follow followResponse = followRepository.save(follow);
        String message =" đã bắt đầu theo dõi bạn.";
        notificationService.sendNotification(
                List.of(userFollowing),
                NotificationType.FOLLOW_USER,
                message,
                userFollower.getUserId(),
                null,
                null,
                followResponse.getId(),
                null
        );

        return followMapper.followToFollowDTO(followResponse);
    }

    public  FollowDTO delete(FollowRequest followRequest){
        Follow follow = followRepository.findByUser_UserIdAndFollowing_UserId(followRequest.getUserId(), followRequest.getFollowingId()).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_FOLLOW));
        User userFollower = userRepository.findByUserId(followRequest.getUserId());
        String message = userFollower.getFullName()+" đã unfollow bạn";
        notificationService.relatedDelete(NotificationType.DELETE_FOLLOW,message,userFollower.getUserId() );
        followRepository.delete(follow);
        return followMapper.followToFollowDTO(follow);
    }



}
