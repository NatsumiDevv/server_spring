package com.devPn.socialmedias.model.request;

import lombok.Data;

import java.util.Set;

@Data
public class RoomRequest {
    private Set<Integer> members;
}
