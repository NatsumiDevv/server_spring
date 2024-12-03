package com.devPn.socialmedias.model.response.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {
    private int userId;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String description;
}
