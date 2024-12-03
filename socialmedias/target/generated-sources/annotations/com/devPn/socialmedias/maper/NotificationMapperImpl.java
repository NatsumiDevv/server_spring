package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Notification;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.response.NotificationResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T18:36:27+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationResponse notificationToNotificationResponse(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationResponse notificationResponse = new NotificationResponse();

        Integer userId = notificationUserUserId( notification );
        if ( userId != null ) {
            notificationResponse.setUserId( userId );
        }
        notificationResponse.setSenderId( notification.getSenderId() );
        notificationResponse.setType( notification.getType() );
        notificationResponse.setPostId( notification.getRelatedPost() );
        notificationResponse.setCommentId( notification.getRelatedComment() );
        notificationResponse.setMessage( notification.getMessage() );
        notificationResponse.setCreatedAt( notification.getCreatedAt() );
        notificationResponse.setId( (int) notification.getId() );

        return notificationResponse;
    }

    @Override
    public List<NotificationResponse> ListNotificationToNotificationResponse(List<Notification> notificationList) {
        if ( notificationList == null ) {
            return null;
        }

        List<NotificationResponse> list = new ArrayList<NotificationResponse>( notificationList.size() );
        for ( Notification notification : notificationList ) {
            list.add( notificationToNotificationResponse( notification ) );
        }

        return list;
    }

    private Integer notificationUserUserId(Notification notification) {
        if ( notification == null ) {
            return null;
        }
        User user = notification.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
