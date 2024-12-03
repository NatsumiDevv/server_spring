package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Room;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.response.RoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "users", target = "members")
    RoomResponse roomToResponse(Room room);

    default Set<Integer> mapUsersToIds(Set<User> users) {
        return users.stream()
                .map(User::getUserId) // Assuming User has a getId() method
                .collect(Collectors.toSet());
    }
}