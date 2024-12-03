package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.UserImage;
import com.devPn.socialmedias.model.response.UserImageResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T18:36:27+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserImageMapperImpl implements UserImageMapper {

    @Override
    public UserImageResponse imageToResponse(UserImage userImage) {
        if ( userImage == null ) {
            return null;
        }

        UserImageResponse userImageResponse = new UserImageResponse();

        Integer userId = userImageUserUserId( userImage );
        if ( userId != null ) {
            userImageResponse.setUserId( userId );
        }
        userImageResponse.setId( userImage.getId() );
        userImageResponse.setName( userImage.getName() );
        userImageResponse.setType( userImage.getType() );
        userImageResponse.setFilePath( userImage.getFilePath() );

        return userImageResponse;
    }

    private Integer userImageUserUserId(UserImage userImage) {
        if ( userImage == null ) {
            return null;
        }
        User user = userImage.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
