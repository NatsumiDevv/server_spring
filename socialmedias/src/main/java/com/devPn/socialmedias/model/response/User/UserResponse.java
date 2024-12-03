package com.devPn.socialmedias.model.response.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userId;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private boolean enable;
    private String avatar;
    private String address;
    private String description;
    private String gender;
    private List<UserFollowerResponse> followers;
    private List<UserFollowingResponse> following;
}