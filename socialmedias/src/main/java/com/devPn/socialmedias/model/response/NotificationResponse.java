package com.devPn.socialmedias.model.response;

import com.devPn.socialmedias.model.Enum.NotificationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private Integer id;
    private long userId;
    private Integer senderId;
    private Integer postId;
    private Integer commentId;
    private String message;
    private NotificationType type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
