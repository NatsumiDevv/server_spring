package com.devPn.socialmedias.service;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.NotificationMapper;
import com.devPn.socialmedias.model.Comment;
import com.devPn.socialmedias.model.Enum.NotificationType;
import com.devPn.socialmedias.model.Notification;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.response.NotificationResponse;
import com.devPn.socialmedias.model.response.Post.PostGetResponse;
import com.devPn.socialmedias.reponsitory.CommentRepository;
import com.devPn.socialmedias.reponsitory.NotificationRepository;
import com.devPn.socialmedias.reponsitory.PostRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public void sendNotification(List<User> receivers, NotificationType type, String message, Integer senderId, Integer postId, Integer commentId, Integer followId , Integer likeId) {
        List<Notification> notificationList = new ArrayList<>();
        for (User receiver : receivers) {
            log.info("User Name: "+ receiver.getFullName());
            Notification notification = new Notification();
            notification.setUser(receiver);
            notification.setMessage(message);
            notification.setType(type);
            notification.setRelatedPost(postId);
            notification.setRelatedComment(commentId);
            notification.setSenderId(senderId);
            notification.setRelatedFollow(followId);
            notification.setRelatedLike(likeId);
            notificationList.add(notification);
            log.info("ReciverId: " + receiver.getUserId());
            simpMessagingTemplate.convertAndSendToUser(
                    String.valueOf(receiver.getUserId()), "/queue/notifications", notificationMapper.notificationToNotificationResponse(notification)
            );
        }
        Notification refreshNotification = Notification.builder()
                .senderId(senderId)
                .type(type)
                .message(message)
                .build();
        simpMessagingTemplate.convertAndSend("/topic/refresh", notificationMapper.notificationToNotificationResponse(refreshNotification) );
        notificationRepository.saveAll(notificationList);
    }

    public List<NotificationResponse> getAllNotificationById(int userId) {
        User user = userRepository.findByUserId(userId);
        log.info("userid: " + userId);
        if (user != null) {
            List<Notification> listNotification = notificationRepository.findAllByUser_UserIdOrderByCreatedAtDesc(userId);
            return notificationMapper.ListNotificationToNotificationResponse(listNotification);
        }
        return null;
    }

    public void relatedDelete ( NotificationType type,String message,Integer senderId){
        Notification notification = Notification.builder()
                .senderId(senderId)
                .type(type)
                .message(message)
                .build();
        simpMessagingTemplate.convertAndSend(
                "/topic/notification/delete",notificationMapper.notificationToNotificationResponse(notification));

    }

}
