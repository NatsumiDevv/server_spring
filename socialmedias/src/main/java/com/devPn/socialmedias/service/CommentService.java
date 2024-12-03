package com.devPn.socialmedias.service;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.maper.CommentMapper;
import com.devPn.socialmedias.model.Comment;
import com.devPn.socialmedias.model.Enum.NotificationType;
import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.CommentAddRequest;
import com.devPn.socialmedias.model.request.CommentUpdateRequest;
import com.devPn.socialmedias.model.response.CommentResponse;
import com.devPn.socialmedias.reponsitory.CommentRepository;
import com.devPn.socialmedias.reponsitory.PostRepository;
import com.devPn.socialmedias.reponsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private NotificationService notificationService;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentResponse add(CommentAddRequest commentAddRequest){
        log.info("User id: "+commentAddRequest.getUserId()+ " Post id : "+ commentAddRequest.getPostId());
        User user = userRepository.findById(commentAddRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITS));
        Post post = postRepository.findById(commentAddRequest.getPostId())
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND));
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(commentAddRequest.getContent())
                .build();
        CommentResponse commentResponse = commentMapper.commentToResponse(commentRepository.save(comment));
        String message =  " đã bình luận bài viết có tiêu đề: ";
        notificationService.sendNotification(
                List.of(post.getUser()),
                NotificationType.NEW_COMMENT ,
                message,
                commentAddRequest.getUserId()
                ,commentAddRequest.getPostId(),
                commentResponse.getId()  ,
                null,
                null  );
        return commentResponse;
    }

    public List<CommentResponse> getAll(){
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.commentsToResponses(comments);
    }

    public CommentResponse getById(int id){
        Comment comment = commentRepository.findById(id).orElse(null);
        return  commentMapper.commentToResponse(comment);
    }

    public List<CommentResponse> getAllByPost(int postId){
        List<Comment> comments = commentRepository.findAllByPost_IdOrderByCreatedAtDesc(postId);
        return commentMapper.commentsToResponses(comments);
    }

    public List<CommentResponse> getAllByUser(int userId){
        List<Comment> comments = commentRepository.findAllByUser_UserId(userId);
        return commentMapper.commentsToResponses(comments);
    }
    public void update(int id, CommentUpdateRequest commentUpdateRequest){
        Comment commentToUpdate = commentRepository.findById(id).orElse(null);
        if (commentToUpdate!=null){
            commentToUpdate.setContent(commentUpdateRequest.getContent());
        }
    }

    public CommentResponse delete(int id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.deleteById(id);
        return commentMapper.commentToResponse(comment);
    }
}
