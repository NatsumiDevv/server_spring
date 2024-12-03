package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Message;
import com.devPn.socialmedias.model.Notification;
import com.devPn.socialmedias.model.response.MessageResponse;
import com.devPn.socialmedias.model.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "senderId",target = "senderId")
    @Mapping(source = "type",target = "type")
    @Mapping(source = "relatedPost",target = "postId")
    @Mapping(source = "relatedComment",target = "commentId")
    @Mapping(source = "message",target = "message")
    @Mapping(source = "createdAt",target = "createdAt")
    NotificationResponse notificationToNotificationResponse(Notification notification);

    List<NotificationResponse> ListNotificationToNotificationResponse(List<Notification> notificationList);
}
