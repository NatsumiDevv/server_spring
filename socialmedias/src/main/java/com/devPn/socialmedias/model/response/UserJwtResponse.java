package com.devPn.socialmedias.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserJwtResponse {
    Integer userId;
    String email;
    String fullName;
}
