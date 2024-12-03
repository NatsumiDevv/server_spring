package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.model.Follow;
import com.devPn.socialmedias.model.request.Follow.FollowRequest;
import com.devPn.socialmedias.model.response.Follow.FollowDTO;
import com.devPn.socialmedias.model.response.User.UserFollowerResponse;
import com.devPn.socialmedias.model.response.UserFollowingResponse;
import com.devPn.socialmedias.service.FollowService;
import com.devPn.socialmedias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    private final FollowService followService;
    @Autowired
    private UserService userService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody FollowRequest followRequest){
        if (userService.isFollowing(followRequest.getUserId(), followRequest.getFollowingId())){
            return new ResponseEntity<>(followRequest.getUserId()+" đã follow "+ followRequest.getFollowingId(),HttpStatus.OK);
        }
        FollowDTO follow = followService.add(followRequest);
        return new ResponseEntity<>(follow, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody FollowRequest  followRequest){
         FollowDTO followDTO = followService.delete(followRequest);
        return ResponseEntity.status(HttpStatus.OK).body(followDTO);
    }


    @GetMapping("/getUserFollowing/{userId}")
    public ResponseEntity<?> listUserFollowing (@PathVariable int userId ){
        List<UserFollowingResponse> listUserFollowing = userService.getUserFollowing(userId);

        if (listUserFollowing.size()==0 ){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUserFollowing, HttpStatus.OK);
    }
    @GetMapping("/getUserFollower/{userId}")
    public ResponseEntity<?> listUserFollower (@PathVariable int userId ){
        List<UserFollowerResponse> listUserFollower = userService.getUserFollowers(userId);

        if (listUserFollower.size()==0 ){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUserFollower, HttpStatus.OK);
    }
}