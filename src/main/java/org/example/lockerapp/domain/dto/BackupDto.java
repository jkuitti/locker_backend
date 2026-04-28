package org.example.lockerapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.lockerapp.domain.entity.Gender;

import java.time.LocalDateTime;

public record BackupDto(
        String lockerNumber,
        Integer keyNumber,
        String employeeFirstName,
        String employeeLastName,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime assignedAt,
        Gender gender
) {
}
