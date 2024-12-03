package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.model.ApiResponse;
import com.devPn.socialmedias.model.ResponseObject;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.UserUpdateRequest;
import com.devPn.socialmedias.model.response.User.UserResponse;
import com.devPn.socialmedias.model.response.User.UserUpdateResponse;
import com.devPn.socialmedias.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable int id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

   @GetMapping("/info")
    public ApiResponse<User> getInfo(){
        return ApiResponse.<User>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @DeleteMapping(path = ("/delete/{id}"))
    public ApiResponse<String> deleteUser(@PathVariable("id") Integer id){
        log.info("Id: "+ id);
        userService.delete(id);
        return ApiResponse.<String>builder()
                .message("Delete successfully !!")
                .build();
    }

    @PutMapping("/update")
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest request) {
        try {
            UserUpdateResponse response = userService.updateUser(request);
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }




    @GetMapping("/isfollowing")
    public ResponseEntity<Boolean> isFollowing(@RequestParam int userId,@RequestParam int followingId){
        return new ResponseEntity<>(userService.isFollowing(userId,followingId),HttpStatus.OK);
    }












}
