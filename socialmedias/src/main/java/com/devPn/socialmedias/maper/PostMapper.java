package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.request.PostAddRequest;
import com.devPn.socialmedias.model.response.Post.PostGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "createdAt", target = "createdAt")
    PostGetResponse postToGetResponse(Post post);

    @Mapping(source = "userId",target = "user.userId")
    Post postAddRequestToPost(PostAddRequest postAddRequest);

    List<PostGetResponse> postsToGetResponses(List<Post> posts);

}
