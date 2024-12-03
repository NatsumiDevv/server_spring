package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    void deleteLikeById(int id);
    List<Like> findAllByPost_Id(int postId);
    List<Like> findAllByUser_UserId(int userId);
    Optional<Like> findByUser_UserIdAndPost_Id(int userId, int postId);
}
