package com.devPn.socialmedias.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
        private int userId;
        private String fullName;
        private String phoneNumber;
        private String address;
        private String description;
        private String gender;
}
