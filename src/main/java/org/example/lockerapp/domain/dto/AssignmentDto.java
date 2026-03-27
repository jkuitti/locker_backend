package org.example.lockerapp.domain.dto;

import java.time.LocalDateTime;

public record AssignmentDto(
        Long id,
        LocalDateTime assignedAt,
        String employeeLastName,
        String employeeFirstName,
        Long lockerId
) {
}
