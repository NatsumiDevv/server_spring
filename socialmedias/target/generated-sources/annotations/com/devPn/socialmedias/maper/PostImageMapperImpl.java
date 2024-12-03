package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.PostImage;
import com.devPn.socialmedias.model.response.PostImageResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T21:06:28+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class PostImageMapperImpl implements PostImageMapper {

    @Override
    public PostImageResponse imageToResponse(PostImage postImage) {
        if ( postImage == null ) {
            return null;
        }

        PostImageResponse postImageResponse = new PostImageResponse();

        postImageResponse.setPostId( postImagePostId( postImage ) );
        postImageResponse.setId( postImage.getId() );
        postImageResponse.setName( postImage.getName() );
        postImageResponse.setType( postImage.getType() );

        return postImageResponse;
    }

    private int postImagePostId(PostImage postImage) {
        if ( postImage == null ) {
            return 0;
        }
        Post post = postImage.getPost();
        if ( post == null ) {
            return 0;
        }
        int id = post.getId();
        return id;
    }
}
