package org.example.lockerapp.repository;

import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.domain.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Assignment findByLocker(Locker locker);
}
