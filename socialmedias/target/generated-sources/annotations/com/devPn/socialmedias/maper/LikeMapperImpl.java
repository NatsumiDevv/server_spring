package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Like;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.LikeRequest;
import com.devPn.socialmedias.model.response.LikeResponse;
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
public class LikeMapperImpl implements LikeMapper {

    @Override
    public LikeResponse likeToPostLikeResponse(Like like) {
        if ( like == null ) {
            return null;
        }

        LikeResponse likeResponse = new LikeResponse();

        Integer userId = likeUserUserId( like );
        if ( userId != null ) {
            likeResponse.setUserId( userId );
        }
        likeResponse.setFullName( likeUserFullName( like ) );
        likeResponse.setPostId( likePostId( like ) );
        if ( like.getId() != null ) {
            likeResponse.setId( like.getId() );
        }

        return likeResponse;
    }

    @Override
    public Like requestToLike(LikeRequest likeRequest) {
        if ( likeRequest == null ) {
            return null;
        }

        Like like = new Like();

        like.setPost( likeRequestToPost( likeRequest ) );
        like.setUser( likeRequestToUser( likeRequest ) );

        return like;
    }

    @Override
    public List<LikeResponse> likesToLikeResponses(List<Like> likes) {
        if ( likes == null ) {
            return null;
        }

        List<LikeResponse> list = new ArrayList<LikeResponse>( likes.size() );
        for ( Like like : likes ) {
            list.add( likeToPostLikeResponse( like ) );
        }

        return list;
    }

    private Integer likeUserUserId(Like like) {
        if ( like == null ) {
            return null;
        }
        User user = like.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String likeUserFullName(Like like) {
        if ( like == null ) {
            return null;
        }
        User user = like.getUser();
        if ( user == null ) {
            return null;
        }
        String fullName = user.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private int likePostId(Like like) {
        if ( like == null ) {
            return 0;
        }
        Post post = like.getPost();
        if ( post == null ) {
            return 0;
        }
        int id = post.getId();
        return id;
    }

    protected Post likeRequestToPost(LikeRequest likeRequest) {
        if ( likeRequest == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( likeRequest.getPostId() );

        return post;
    }

    protected User likeRequestToUser(LikeRequest likeRequest) {
        if ( likeRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( likeRequest.getUserId() );

        return user;
    }
}
