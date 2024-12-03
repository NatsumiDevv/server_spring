package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.model.request.CommentAddRequest;
import com.devPn.socialmedias.model.response.CommentResponse;
import com.devPn.socialmedias.service.CommentService;
import com.devPn.socialmedias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired


    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CommentResponse>> getAll(){
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getallbypost/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllByPost(@PathVariable int postId){
        return new ResponseEntity<>(commentService.getAllByPost(postId),HttpStatus.OK);
    }

    @GetMapping("/getallbyuser/{userId}")
    public ResponseEntity<List<CommentResponse>> getAllByUser(@PathVariable int userId){
        return new ResponseEntity<>(commentService.getAllByUser(userId),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CommentResponse> add(@RequestBody CommentAddRequest commentAddRequest){

        return new ResponseEntity<>( commentService.add(commentAddRequest),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommentResponse> delete(@RequestParam int id){
        return new ResponseEntity<>(commentService.delete(id),HttpStatus.OK);
    }
}
