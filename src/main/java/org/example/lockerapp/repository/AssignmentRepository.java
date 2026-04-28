package org.example.lockerapp.repository;

import org.example.lockerapp.domain.dto.BackupDto;
import org.example.lockerapp.domain.dto.LockerAssignmentDto;
import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.domain.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Assignment findByLocker(Locker locker);

    @Query("""
    SELECT new org.example.lockerapp.domain.dto.BackupDto(
        l.lockerNumber,
        l.keyNumber,
        a.employeeFirstName,
        a.employeeLastName,
        a.assignedAt,
        r.gender
    )
    FROM Locker l
    LEFT JOIN Assignment a ON a.locker = l
    LEFT JOIN Room r ON l.room = r
""")
    List<BackupDto> getBackupData();

}
