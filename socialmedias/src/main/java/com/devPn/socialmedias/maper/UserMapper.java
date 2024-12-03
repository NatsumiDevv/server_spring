package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Follow;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.UserUpdateRequest;
import com.devPn.socialmedias.model.response.User.UserFollowerResponse;
import com.devPn.socialmedias.model.response.User.UserResponse;
import com.devPn.socialmedias.model.response.User.UserUpdateResponse;
import com.devPn.socialmedias.model.response.UserFollowingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "user.fullName",target = "fullName")
    @Mapping(source = "user.avatarImage.name", target = "avatar")
    UserFollowerResponse followToFollowerResponse(Follow follow);
    @Mapping(source = "following.userId",target = "userId")
    @Mapping(source = "following.fullName",target = "fullName")
    @Mapping(source = "following.avatarImage.name", target = "avatar")
    UserFollowingResponse followToFollowingResponse(Follow follow);



    @Mapping(source = "user.avatarImage.name", target = "avatar")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "followers",target = "followers")
    @Mapping(source = "following",target = "following")
    UserResponse userToResponse(User user);


    @Mapping(source = "user.userId" , target = "userId")
    UserUpdateResponse userUpdateToResponse(User user);

    @Mapping(source = "request.userId", target = "userId")
    User updateUserFromRequest(UserUpdateRequest request, @MappingTarget User user);

    List<UserFollowingResponse> followsToFollowingResponses(List<Follow> follows);

    List<UserFollowerResponse> followsToFollowerResponses(List<Follow> follows);



}
