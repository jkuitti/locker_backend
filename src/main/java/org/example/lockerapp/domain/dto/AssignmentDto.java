package org.example.lockerapp.domain.dto;

import org.example.lockerapp.domain.entity.Locker;

import java.time.LocalDateTime;

public record AssignmentDto(
        Long id,
        LocalDateTime assignedAt,
        String employeeLastName,
        String employeeFirstName,
        Locker locker
) {
}
