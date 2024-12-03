package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Comment;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.CommentAddRequest;
import com.devPn.socialmedias.model.response.CommentResponse;
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
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        Integer userId = commentUserUserId( comment );
        if ( userId != null ) {
            commentResponse.setUserId( userId );
        }
        commentResponse.setPostId( commentPostId( comment ) );
        commentResponse.setFullName( commentUserFullName( comment ) );
        commentResponse.setId( comment.getId() );
        commentResponse.setContent( comment.getContent() );
        commentResponse.setCreatedAt( comment.getCreatedAt() );
        commentResponse.setUpdatedAt( comment.getUpdatedAt() );

        return commentResponse;
    }

    @Override
    public List<CommentResponse> commentsToResponses(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentResponse> list = new ArrayList<CommentResponse>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToResponse( comment ) );
        }

        return list;
    }

    @Override
    public Comment addRequestToComment(CommentAddRequest commentAddRequest) {
        if ( commentAddRequest == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setUser( commentAddRequestToUser( commentAddRequest ) );
        comment.setPost( commentAddRequestToPost( commentAddRequest ) );
        comment.setContent( commentAddRequest.getContent() );

        return comment;
    }

    private Integer commentUserUserId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private int commentPostId(Comment comment) {
        if ( comment == null ) {
            return 0;
        }
        Post post = comment.getPost();
        if ( post == null ) {
            return 0;
        }
        int id = post.getId();
        return id;
    }

    private String commentUserFullName(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        String fullName = user.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    protected User commentAddRequestToUser(CommentAddRequest commentAddRequest) {
        if ( commentAddRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( commentAddRequest.getUserId() );

        return user;
    }

    protected Post commentAddRequestToPost(CommentAddRequest commentAddRequest) {
        if ( commentAddRequest == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( commentAddRequest.getPostId() );

        return post;
    }
}
