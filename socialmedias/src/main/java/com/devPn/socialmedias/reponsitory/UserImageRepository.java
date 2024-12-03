package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.PostImage;
import com.devPn.socialmedias.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    Optional<UserImage> findUserImageByUser_UserId(int userId);
}
