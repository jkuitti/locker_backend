package org.example.lockerapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.lockerapp.domain.entity.LockerStatus;

import java.time.LocalDateTime;

public record LockerAssignmentDto(
    Long id,
    String lockerNumber,
    LockerStatus status,
    Integer keyNumber,
    String employeeFirstName,
    String employeeLastName,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime assignedAt,
    Long roomId
) {
}
