package com.devPn.socialmedias.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserFollowingResponse {
    private int userId;
    private String fullName;
    private String avatar;
}