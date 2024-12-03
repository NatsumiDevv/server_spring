package com.devPn.socialmedias.reponsitory;

import com.devPn.socialmedias.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r JOIN r.users u WHERE u.userId IN :userIds GROUP BY r HAVING COUNT(u.userId) = 2")
    Optional<Room> findRoomByUsers(@Param("userIds") Set<Integer> userIds);

}
