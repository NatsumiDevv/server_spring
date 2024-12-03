package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Follow;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.Follow.FollowRequest;
import com.devPn.socialmedias.model.response.Follow.FollowDTO;
import com.devPn.socialmedias.model.response.Follow.FollowResponse;
import com.devPn.socialmedias.model.response.User.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    @Mapping(source = "userId",target = "user.userId")
    @Mapping(source = "followingId",target = "following.userId")
    Follow addRequestToFollow(FollowRequest followAddRequest);

    List<FollowResponse> followsToResponses(List<Follow> follows);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarImage.filePath", target = "avatar" )
    UserDTO userToUserDTO(User user);

    // Mapping Follow to FollowDTO
    @Mapping(source = "user", target = "user")
    @Mapping(source = "following", target = "following")
    @Mapping(source = "createdAt", target = "createdAt")
    FollowDTO followToFollowDTO(Follow follow);
}