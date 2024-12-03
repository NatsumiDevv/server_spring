package com.devPn.socialmedias.controller;


import com.devPn.socialmedias.model.PostImage;
import com.devPn.socialmedias.model.response.PostImageResponse;
import com.devPn.socialmedias.service.PostImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/postimages")
public class PostImageController {
    @Autowired
    PostImageService postImageService;
    @PostMapping("/upload")
    public ResponseEntity<PostImageResponse> upload(@RequestParam("image") MultipartFile file, @RequestParam int postId) throws IOException {
        log.info(postId+" "+ file);
        PostImageResponse postImageResponse = postImageService.upload(file,postId);
        return  ResponseEntity.status(HttpStatus.OK).body(postImageResponse);
    }

    @GetMapping("/download/{postId}")
    public ResponseEntity<?> download(@PathVariable int postId) throws IOException {
        byte[] image = postImageService.download(postId);
        if (image!=null){
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePostImage(@PathVariable int id) {
        postImageService.deletePostImage(id);
        return new ResponseEntity<>("PostImage deleted successfully", HttpStatus.OK);
    }

}
