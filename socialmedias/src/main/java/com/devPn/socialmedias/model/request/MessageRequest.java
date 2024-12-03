package com.devPn.socialmedias.model.request;

import lombok.Data;

@Data
public class MessageRequest {
    private Integer roomId;
    private int senderId;
    private int receiverId;
    private String content;
}
