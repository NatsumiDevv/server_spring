package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByUser_UserId(int userId);
    List<Follow> findByFollowing_UserId(int followingId);
    Optional<Follow> findByUser_UserIdAndFollowing_UserId(int userId, int followingId);


}
