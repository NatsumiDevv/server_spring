package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification>findAllByUser_UserIdOrderByCreatedAtDesc(Integer userId);
}

