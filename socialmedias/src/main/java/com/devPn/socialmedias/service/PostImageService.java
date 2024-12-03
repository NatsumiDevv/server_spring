package com.devPn.socialmedias.service;


import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.PostImageMapper;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.PostImage;
import com.devPn.socialmedias.model.response.PostImageResponse;
import com.devPn.socialmedias.reponsitory.PostImageRepository;
import com.devPn.socialmedias.reponsitory.PostRepository;
import com.devPn.socialmedias.utils.ImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostImageService {
    @Autowired
    private PostImageRepository postImageRepository;

    @Autowired
    private PostImageMapper postImageMapper;

    @Autowired
    private PostRepository postRepository;


    public PostImageResponse upload(MultipartFile file, int postId) throws  IOException {
        Optional<Post> post = Optional.ofNullable(postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND)));
        if (post.isPresent()) {
            PostImage postImage = PostImage.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .data(file.getBytes()) // Lưu dữ liệu ảnh trực tiếp
                    .post(post.get())
                    .build();

            postImage = postImageRepository.save(postImage);

            return postImageMapper.imageToResponse(postImage);

        }
        return null;

    }

    public byte[] download(int id) throws IOException {
        Optional<PostImage> postImage = postImageRepository.findPostImageByPost_Id(id);
        if(postImage.isPresent()){
           return  postImage.get().getData();
        }
        return null;
    }
    public void deletePostImage(int postId) {
        PostImage postImage = postImageRepository.findPostImageByPost_Id(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_IMAGE_NOT_FOUND));

        postImageRepository.delete(postImage); // Chỉ xóa bản ghi khỏi database
    }

}
