package org.example.lockerapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.lockerapp.domain.entity.Gender;
import org.example.lockerapp.domain.entity.Locker;

import java.time.LocalDateTime;

public record AssignmentDto(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime assignedAt,
        String employeeLastName,
        String employeeFirstName,
        String lockerNumber,
        Integer keyNumber,
        Gender roomGender
) {
}
