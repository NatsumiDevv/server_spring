package com.devPn.socialmedias.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserImageResponse {
    private int id;
    private String name;
    private String type;
    private String filePath;
    private int userId;
}