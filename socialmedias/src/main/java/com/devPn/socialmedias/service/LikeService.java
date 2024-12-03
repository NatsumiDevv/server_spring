package com.devPn.socialmedias.service;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.LikeMapper;
import com.devPn.socialmedias.model.Enum.NotificationType;
import com.devPn.socialmedias.model.Like;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.LikeRequest;
import com.devPn.socialmedias.model.response.LikeResponse;
import com.devPn.socialmedias.reponsitory.LikeRepository;
import com.devPn.socialmedias.reponsitory.PostRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LikeService {

    @Autowired
    private final LikeRepository likeRepository;
    @Autowired
    private final LikeMapper likeMapper;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    public List<LikeResponse> getAllByPost(int postId){
        List<Like> likes = likeRepository.findAllByPost_Id(postId);
        return likeMapper.likesToLikeResponses(likes);
    }

    public List<LikeResponse> getAllByUser(int userId){
        List<Like> likes = likeRepository.findAllByUser_UserId(userId);
        return likeMapper.likesToLikeResponses(likes);
    }

    public boolean isLiked(int userId,int postId){
        Optional<Like> like = likeRepository.findByUser_UserIdAndPost_Id(userId,postId);
        return like.isPresent();
    }

    public boolean add(LikeRequest likeRequest){
        log.info("Post id: "+likeRequest.getPostId() +likeRequest.getUserId() +" User id");
        if( !postRepository.findById(likeRequest.getPostId()).isPresent())
        {
            throw new AppException(ErrorCode.POST_NOT_FOUND);
        }
        Post post = postRepository.findById(likeRequest.getPostId()).orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND));
        if (isLiked(likeRequest.getUserId(), likeRequest.getPostId())){
            return false;
        }

        Like like = likeMapper.requestToLike(likeRequest);
        likeRepository.save(like);
        String message = " đã thả like cho bài đăng có tiêu đề: ";
        notificationService.sendNotification(List.of(post.getUser()), NotificationType.LIKE_POST , message, likeRequest.getUserId(), likeRequest.getPostId(), null, null , null );
        return true;
    }

    public boolean delete(LikeRequest likeRequest){
        Optional<Like> likes = likeRepository.findByUser_UserIdAndPost_Id(likeRequest.getUserId(),likeRequest.getPostId());
        if( !postRepository.findById(likeRequest.getPostId()).isPresent())
        {
            throw new AppException(ErrorCode.POST_NOT_FOUND);
        }
        Post post = postRepository.findById(likeRequest.getPostId()).orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND));
        String message = post.getUser().getFullName()+" đã unfollow bạn";
        notificationService.relatedDelete( NotificationType.DELETE_LIKE,message,likeRequest.getUserId() );
        if (!likes.isPresent()) {
            return false;
        }

        // Xóa Like
        likeRepository.delete(likes.get());
        return true;
    }
}
