package com.devPn.socialmedias.model.response.Follow;

import com.devPn.socialmedias.model.response.User.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FollowDTO {
    private int id;
    private UserDTO user;
    private UserDTO following;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}