package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.PostImage;
import com.devPn.socialmedias.model.UserImage;
import com.devPn.socialmedias.model.response.PostImageResponse;
import com.devPn.socialmedias.model.response.UserImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserImageMapper {


    @Mapping(source = "user.userId",target = "userId")
    UserImageResponse imageToResponse(UserImage userImage);

}
