package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    @EntityGraph(attributePaths = {"following", "followers"})

    boolean existsByEmail(String email);


    User findByUserId (int userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.followers LEFT JOIN FETCH u.following WHERE u.userId = :id")
    Optional<User> findByIdWithFollowersAndFollowing(@Param("id") int id);
}
