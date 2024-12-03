package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Comment;
import com.devPn.socialmedias.model.request.CommentAddRequest;
import com.devPn.socialmedias.model.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "user.fullName",target = "fullName")
    CommentResponse commentToResponse(Comment comment);
    List<CommentResponse> commentsToResponses(List<Comment> comments);
    @Mapping(source = "userId",target = "user.userId")
    @Mapping(source = "postId",target = "post.id")
    @Mapping(source = "content",target = "content")
    Comment addRequestToComment(CommentAddRequest commentAddRequest);
}
