package com.devPn.socialmedias.reponsitory;


import com.devPn.socialmedias.model.PostImage;
import com.devPn.socialmedias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Integer> {
    Optional<PostImage> findPostImageByPost_Id(int postId);

}
