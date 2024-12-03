package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.Post;
import com.devPn.socialmedias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post>  findAllByOrderByCreatedAtDesc();
    List<Post> findAllByUser_UserIdOrderByIdDesc(int userId);

    User findUserById(Integer postId);
    void deleteById(int id);
}
