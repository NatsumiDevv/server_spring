package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Like;
import com.devPn.socialmedias.model.request.LikeRequest;
import com.devPn.socialmedias.model.response.LikeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "user.fullName",target = "fullName")
    @Mapping(source = "post.id",target = "postId")
    LikeResponse likeToPostLikeResponse(Like like);

    @Mapping(source = "postId",target = "post.id")
    @Mapping(source = "userId",target = "user.userId")
    Like requestToLike(LikeRequest likeRequest);

    List<LikeResponse> likesToLikeResponses(List<Like> likes);

}
