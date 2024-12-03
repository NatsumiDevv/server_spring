package com.devPn.socialmedias.service;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.UserMapper;
import com.devPn.socialmedias.model.Follow;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.UserUpdateRequest;
import com.devPn.socialmedias.model.response.User.UserFollowerResponse;
import com.devPn.socialmedias.model.response.User.UserResponse;
import com.devPn.socialmedias.model.response.User.UserUpdateResponse;
import com.devPn.socialmedias.model.response.UserFollowingResponse;
import com.devPn.socialmedias.reponsitory.FollowRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

 @Autowired
     private  UserRepository repository;

 @Autowired
    private FollowRepository followRepository;
 @Autowired
    private UserMapper userMapper;


    public List<UserResponse> getAllUser(){
        List<User> users = repository.findAll();  // Lấy danh sách User từ repository
        return users.stream()                     // Sử dụng Stream API để ánh xạ từng User sang UserResponse
                .map(userMapper::userToResponse)
                .collect(Collectors.toList());
    }
     public UserResponse getUserById (int id){

         User user = repository.findByUserId(id);
         return userMapper.userToResponse(user);
     }

//    public Post getById(int id){
//        return userRe.findById(id).get();
//    }



     public User getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        return repository.findByEmail(name).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXITS));
     }

    public void delete(Integer id)

    {
        log.info("Id: "+id);
        repository.deleteById(id);
    }

    public boolean isFollowing(int userId,int followingId){
        log.info(""+userId+" "+ followingId);
        Optional<Follow> follow = followRepository.findByUser_UserIdAndFollowing_UserId(userId,followingId);
        return follow.isPresent();
    }


    public List<UserFollowingResponse> getUserFollowing(int userId) {
        // Lấy danh sách những người mà userId đang theo dõi
        List<Follow> followList = followRepository.findByUser_UserId(userId);

        // Map từ danh sách Follow sang UserFollowingResponse
        return userMapper.followsToFollowingResponses(followList);
    }


    public List<UserFollowerResponse> getUserFollowers(int userId) {
        // Lấy danh sách những người đang theo dõi userId
        List<Follow> followerList = followRepository.findByFollowing_UserId(userId);
        // Map từ danh sách Follow sang UserFollowerResponse
        return userMapper.followsToFollowerResponses(followerList);
    }

    public List<User>  getCombinedFollowersAndFollowing (int userId){
        // Lấy danh sách những người theo dõi userId
        List<Follow> followers = followRepository.findByFollowing_UserId(userId);
        List<User> followerList = followers.stream()
                .map(Follow::getUser)
                .toList();

        // Lấy danh sách những người mà userId đang theo dõi
        List<Follow> followings = followRepository.findByUser_UserId(userId);
        List<User> followingList = followings.stream()
                .map(Follow::getFollowing)
                .toList();

        // Kết hợp hai danh sách và loại bỏ trùng lặp
        Set<User> combinedSet = new HashSet<>();
        combinedSet.addAll(followerList);
        combinedSet.addAll(followingList);

        // Chuyển Set thành List và trả về
        return new ArrayList<>(combinedSet);
    }

    public UserUpdateResponse updateUser(UserUpdateRequest request) {
        Optional<User> existingUser = repository.findById(request.getUserId());
        log.info(request.getFullName());
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            log.info(user.getDescription());
            user.setFullName(request.getFullName());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setAddress(request.getAddress());
            user.setDescription(request.getDescription());
            user.setGender(request.getGender());
            User updatedUser = repository.save(user);
            log.info(user.getDescription());
            return userMapper.userUpdateToResponse(updatedUser);
        } else {
            throw new AppException(ErrorCode.USER_NOT_EXITS);
        }
    }










}
