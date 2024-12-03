package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Follow;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.UserImage;
import com.devPn.socialmedias.model.request.Follow.FollowRequest;
import com.devPn.socialmedias.model.response.Follow.FollowDTO;
import com.devPn.socialmedias.model.response.Follow.FollowResponse;
import com.devPn.socialmedias.model.response.User.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T18:36:27+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class FollowMapperImpl implements FollowMapper {

    @Override
    public Follow addRequestToFollow(FollowRequest followAddRequest) {
        if ( followAddRequest == null ) {
            return null;
        }

        Follow.FollowBuilder follow = Follow.builder();

        follow.user( followRequestToUser( followAddRequest ) );
        follow.following( followRequestToUser1( followAddRequest ) );

        return follow.build();
    }

    @Override
    public List<FollowResponse> followsToResponses(List<Follow> follows) {
        if ( follows == null ) {
            return null;
        }

        List<FollowResponse> list = new ArrayList<FollowResponse>( follows.size() );
        for ( Follow follow : follows ) {
            list.add( followToFollowResponse( follow ) );
        }

        return list;
    }

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        if ( user.getUserId() != null ) {
            userDTO.setUserId( user.getUserId() );
        }
        userDTO.setFullName( user.getFullName() );
        userDTO.setAvatar( userAvatarImageFilePath( user ) );

        return userDTO;
    }

    @Override
    public FollowDTO followToFollowDTO(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        FollowDTO followDTO = new FollowDTO();

        followDTO.setUser( userToUserDTO( follow.getUser() ) );
        followDTO.setFollowing( userToUserDTO( follow.getFollowing() ) );
        followDTO.setCreatedAt( follow.getCreatedAt() );
        followDTO.setId( follow.getId() );

        return followDTO;
    }

    protected User followRequestToUser(FollowRequest followRequest) {
        if ( followRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( followRequest.getUserId() );

        return user;
    }

    protected User followRequestToUser1(FollowRequest followRequest) {
        if ( followRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( followRequest.getFollowingId() );

        return user;
    }

    protected FollowResponse followToFollowResponse(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        FollowResponse followResponse = new FollowResponse();

        followResponse.setId( follow.getId() );

        return followResponse;
    }

    private String userAvatarImageFilePath(User user) {
        if ( user == null ) {
            return null;
        }
        UserImage avatarImage = user.getAvatarImage();
        if ( avatarImage == null ) {
            return null;
        }
        String filePath = avatarImage.getFilePath();
        if ( filePath == null ) {
            return null;
        }
        return filePath;
    }
}
