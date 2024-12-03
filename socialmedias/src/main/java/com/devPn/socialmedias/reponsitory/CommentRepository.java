package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment deleteById(int id);
    List<Comment> findAllByUser_UserId(int userId);
    List<Comment> findAllByPost_IdOrderByCreatedAtDesc(int postId);
}
