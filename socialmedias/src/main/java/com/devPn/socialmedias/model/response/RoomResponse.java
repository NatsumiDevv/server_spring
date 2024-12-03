package com.devPn.socialmedias.model.response;

import com.devPn.socialmedias.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class RoomResponse {
    private int id;
    private String name;
    private Set<Integer> members;// lấy id của user
}
