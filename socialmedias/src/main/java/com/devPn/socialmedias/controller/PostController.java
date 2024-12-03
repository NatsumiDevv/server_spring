package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.maper.PostMapper;
import com.devPn.socialmedias.model.ApiResponse;
import com.devPn.socialmedias.model.request.PostAddRequest;
import com.devPn.socialmedias.model.response.Post.PostGetResponse;
import com.devPn.socialmedias.service.PostImageService;
import com.devPn.socialmedias.service.PostService;
import com.devPn.socialmedias.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostImageService postImageService;

    @GetMapping("/getall")
    public ResponseEntity<List<PostGetResponse>> getAll(){
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<PostGetResponse> getById(@PathVariable int id){
        return new ResponseEntity<>(postService.getResponseById(id),HttpStatus.OK);
    }

    @GetMapping("/getbyuserfollowing/{userId}")
    public ResponseEntity<List<PostGetResponse>> getAllByUserFollowing(@PathVariable int userId){
        return new ResponseEntity<>(postService.getByUserFollowing(userId),HttpStatus.OK);
    }
    @GetMapping("/getbyuser/{userId}")
    public ResponseEntity<List<PostGetResponse>> getByUser(@PathVariable int userId){
        log.info(userId+"");
        return ResponseEntity.status(HttpStatus.OK).body(postService.getByUser(userId));
    }



    @PostMapping("/add")
    public ApiResponse<?> add(@RequestBody PostAddRequest postAddRequest){
        return ApiResponse.builder()
                .result(postService.addPost(postAddRequest))
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PostGetResponse> delete(@RequestParam int id){
        PostGetResponse postGetResponse = postService.delete(id);
        return new ResponseEntity<>(postGetResponse, HttpStatus.OK);
    }


}
