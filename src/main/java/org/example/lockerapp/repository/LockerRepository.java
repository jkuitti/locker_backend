package org.example.lockerapp.repository;

import org.example.lockerapp.domain.dto.LockerAssignmentDto;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {
    List<Locker> findAllByRoomId(Long roomId);

    boolean existsByRoomId(Long roomId);

    @Query("""
    SELECT new org.example.lockerapp.domain.dto.LockerAssignmentDto(
        l.id,
        l.lockerNumber,
        l.status,
        l.keyNumber,
        a.employeeFirstName,
        a.employeeLastName,
        a.assignedAt,
        l.room.id
    )
    FROM Locker l
    LEFT JOIN Assignment a ON a.locker = l
    WHERE l.room.id = :roomId
""")
    List<LockerAssignmentDto> findLockersAndAssignments(Long roomId);


}
