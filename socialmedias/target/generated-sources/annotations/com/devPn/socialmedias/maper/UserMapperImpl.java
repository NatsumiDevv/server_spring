package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Follow;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.UserImage;
import com.devPn.socialmedias.model.request.UserUpdateRequest;
import com.devPn.socialmedias.model.response.User.UserFollowerResponse;
import com.devPn.socialmedias.model.response.User.UserResponse;
import com.devPn.socialmedias.model.response.User.UserUpdateResponse;
import com.devPn.socialmedias.model.response.UserFollowingResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T14:52:02+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserFollowerResponse followToFollowerResponse(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        UserFollowerResponse userFollowerResponse = new UserFollowerResponse();

        Integer userId = followUserUserId( follow );
        if ( userId != null ) {
            userFollowerResponse.setUserId( userId );
        }
        userFollowerResponse.setFullName( followUserFullName( follow ) );
        userFollowerResponse.setAvatar( followUserAvatarImageName( follow ) );

        return userFollowerResponse;
    }

    @Override
    public UserFollowingResponse followToFollowingResponse(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        UserFollowingResponse userFollowingResponse = new UserFollowingResponse();

        Integer userId = followFollowingUserId( follow );
        if ( userId != null ) {
            userFollowingResponse.setUserId( userId );
        }
        userFollowingResponse.setFullName( followFollowingFullName( follow ) );
        userFollowingResponse.setAvatar( followFollowingAvatarImageName( follow ) );

        return userFollowingResponse;
    }

    @Override
    public UserResponse userToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setAvatar( userAvatarImageName( user ) );
        userResponse.setEmail( user.getEmail() );
        userResponse.setFollowers( followSetToUserFollowerResponseList( user.getFollowers() ) );
        userResponse.setFollowing( followSetToUserFollowingResponseList( user.getFollowing() ) );
        if ( user.getUserId() != null ) {
            userResponse.setUserId( user.getUserId() );
        }
        userResponse.setPassword( user.getPassword() );
        userResponse.setFullName( user.getFullName() );
        userResponse.setPhoneNumber( user.getPhoneNumber() );
        userResponse.setEnable( user.isEnable() );
        userResponse.setAddress( user.getAddress() );
        userResponse.setDescription( user.getDescription() );
        userResponse.setGender( user.getGender() );

        return userResponse;
    }

    @Override
    public UserUpdateResponse userUpdateToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();

        if ( user.getUserId() != null ) {
            userUpdateResponse.setUserId( user.getUserId() );
        }
        userUpdateResponse.setFullName( user.getFullName() );
        userUpdateResponse.setPhoneNumber( user.getPhoneNumber() );
        userUpdateResponse.setAddress( user.getAddress() );
        userUpdateResponse.setDescription( user.getDescription() );

        return userUpdateResponse;
    }

    @Override
    public User updateUserFromRequest(UserUpdateRequest request, User user) {
        if ( request == null ) {
            return user;
        }

        user.setUserId( request.getUserId() );
        user.setFullName( request.getFullName() );
        user.setDescription( request.getDescription() );
        user.setAddress( request.getAddress() );
        user.setPhoneNumber( request.getPhoneNumber() );
        user.setGender( request.getGender() );

        return user;
    }

    @Override
    public List<UserFollowingResponse> followsToFollowingResponses(List<Follow> follows) {
        if ( follows == null ) {
            return null;
        }

        List<UserFollowingResponse> list = new ArrayList<UserFollowingResponse>( follows.size() );
        for ( Follow follow : follows ) {
            list.add( followToFollowingResponse( follow ) );
        }

        return list;
    }

    @Override
    public List<UserFollowerResponse> followsToFollowerResponses(List<Follow> follows) {
        if ( follows == null ) {
            return null;
        }

        List<UserFollowerResponse> list = new ArrayList<UserFollowerResponse>( follows.size() );
        for ( Follow follow : follows ) {
            list.add( followToFollowerResponse( follow ) );
        }

        return list;
    }

    private Integer followUserUserId(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User user = follow.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String followUserFullName(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User user = follow.getUser();
        if ( user == null ) {
            return null;
        }
        String fullName = user.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private String followUserAvatarImageName(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User user = follow.getUser();
        if ( user == null ) {
            return null;
        }
        UserImage avatarImage = user.getAvatarImage();
        if ( avatarImage == null ) {
            return null;
        }
        String name = avatarImage.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer followFollowingUserId(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User following = follow.getFollowing();
        if ( following == null ) {
            return null;
        }
        Integer userId = following.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String followFollowingFullName(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User following = follow.getFollowing();
        if ( following == null ) {
            return null;
        }
        String fullName = following.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private String followFollowingAvatarImageName(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User following = follow.getFollowing();
        if ( following == null ) {
            return null;
        }
        UserImage avatarImage = following.getAvatarImage();
        if ( avatarImage == null ) {
            return null;
        }
        String name = avatarImage.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String userAvatarImageName(User user) {
        if ( user == null ) {
            return null;
        }
        UserImage avatarImage = user.getAvatarImage();
        if ( avatarImage == null ) {
            return null;
        }
        String name = avatarImage.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected List<UserFollowerResponse> followSetToUserFollowerResponseList(Set<Follow> set) {
        if ( set == null ) {
            return null;
        }

        List<UserFollowerResponse> list = new ArrayList<UserFollowerResponse>( set.size() );
        for ( Follow follow : set ) {
            list.add( followToFollowerResponse( follow ) );
        }

        return list;
    }

    protected com.devPn.socialmedias.model.response.User.UserFollowingResponse followToUserFollowingResponse(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        com.devPn.socialmedias.model.response.User.UserFollowingResponse userFollowingResponse = new com.devPn.socialmedias.model.response.User.UserFollowingResponse();

        return userFollowingResponse;
    }

    protected List<com.devPn.socialmedias.model.response.User.UserFollowingResponse> followSetToUserFollowingResponseList(Set<Follow> set) {
        if ( set == null ) {
            return null;
        }

        List<com.devPn.socialmedias.model.response.User.UserFollowingResponse> list = new ArrayList<com.devPn.socialmedias.model.response.User.UserFollowingResponse>( set.size() );
        for ( Follow follow : set ) {
            list.add( followToUserFollowingResponse( follow ) );
        }

        return list;
    }
}
