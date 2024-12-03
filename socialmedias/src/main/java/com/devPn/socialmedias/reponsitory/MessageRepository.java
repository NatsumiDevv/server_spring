package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByRoom_Id(Integer roomId);
}
