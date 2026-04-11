package org.example.lockerapp.repository;

import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {
    List<Locker> findAllByRoomId(Long roomId);

    boolean existsByRoomId(Long roomId);
}
