package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.PostImage;
import com.devPn.socialmedias.model.response.PostImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostImageMapper {

    @Mapping(source = "post.id",target = "postId")
    PostImageResponse imageToResponse(PostImage postImage);

}