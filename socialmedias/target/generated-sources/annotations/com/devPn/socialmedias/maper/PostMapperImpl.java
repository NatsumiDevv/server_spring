package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.PostAddRequest;
import com.devPn.socialmedias.model.response.Post.PostGetResponse;
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
public class PostMapperImpl implements PostMapper {

    @Override
    public PostGetResponse postToGetResponse(Post post) {
        if ( post == null ) {
            return null;
        }

        PostGetResponse postGetResponse = new PostGetResponse();

        Integer userId = postUserUserId( post );
        if ( userId != null ) {
            postGetResponse.setUserId( userId );
        }
        postGetResponse.setFullName( postUserFullName( post ) );
        postGetResponse.setCreatedAt( post.getCreatedAt() );
        postGetResponse.setId( post.getId() );
        postGetResponse.setCaption( post.getCaption() );

        return postGetResponse;
    }

    @Override
    public Post postAddRequestToPost(PostAddRequest postAddRequest) {
        if ( postAddRequest == null ) {
            return null;
        }

        Post post = new Post();

        post.setUser( postAddRequestToUser( postAddRequest ) );
        post.setCaption( postAddRequest.getCaption() );

        return post;
    }

    @Override
    public List<PostGetResponse> postsToGetResponses(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostGetResponse> list = new ArrayList<PostGetResponse>( posts.size() );
        for ( Post post : posts ) {
            list.add( postToGetResponse( post ) );
        }

        return list;
    }

    private Integer postUserUserId(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String postUserFullName(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String fullName = user.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    protected User postAddRequestToUser(PostAddRequest postAddRequest) {
        if ( postAddRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( postAddRequest.getUserId() );

        return user;
    }
}
