package com.devPn.socialmedias.service;


import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.PostMapper;

import com.devPn.socialmedias.model.Enum.NotificationType;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.PostAddRequest;
import com.devPn.socialmedias.model.response.Post.PostGetResponse;

import com.devPn.socialmedias.model.response.UserFollowingResponse;
import com.devPn.socialmedias.reponsitory.PostImageRepository;
import com.devPn.socialmedias.reponsitory.PostRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import com.devPn.socialmedias.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PostImageService postImageService;


    @Autowired
    PostMapper postMapper;

    public PostService( PostMapper postMapper) {

        this.postMapper = postMapper;

    }

    public List<PostGetResponse> getAll(){
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        return  postMapper.postsToGetResponses(posts);
    }


    public PostGetResponse getResponseById(int id){
        Post post = postRepository.findById(id).orElse(null);
        return postMapper.postToGetResponse(post);
    }


    public List<PostGetResponse> getByUserFollowing(int userId){
        List<UserFollowingResponse> follows = userService.getUserFollowing(userId);
        List<Post> set = new ArrayList<>();
        for(UserFollowingResponse user : follows){
            set.addAll(postRepository.findAllByUser_UserIdOrderByIdDesc(user.getUserId()));
            log.info(""+postMapper.postsToGetResponses(postRepository.findAllByUser_UserIdOrderByIdDesc(user.getUserId())));

        }
        set.sort(Comparator.comparing(Post::getId).reversed());
        return postMapper.postsToGetResponses(set);
    }

    public List<PostGetResponse> getByUser(int userId) {
        List<Post> userPosts = postRepository.findAllByUser_UserIdOrderByIdDesc(userId);
        return postMapper.postsToGetResponses(userPosts);
    }





    public Post getById(int id){
        return postRepository.findById(id).get();
    }

    public PostGetResponse addPost(PostAddRequest postAddRequest) {
        User user = userRepository.findById(postAddRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITS));
        Post post = postMapper.postAddRequestToPost(postAddRequest);
        post.setUser(user);
        PostGetResponse postGetResponse = postMapper.postToGetResponse( postRepository.save(post));
        List<User> receivers = userService.getCombinedFollowersAndFollowing(postAddRequest.getUserId());
        String message = " đã đăng bài mới với tiêu đề: ";
        notificationService.sendNotification(receivers, NotificationType.NEW_POST, message, postAddRequest.getUserId(), postGetResponse.getId(), null, null, null );
        return postGetResponse;
    }

    public PostGetResponse delete(int id){
        log.info(id+" post Id");
        Post post = postRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND));
        postImageService.deletePostImage(id);
        postRepository.deleteById(id);
        return postMapper.postToGetResponse(post);

    }



}
