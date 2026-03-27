package org.example.lockerapp.domain.dto;

import org.example.lockerapp.domain.entity.LockerStatus;

public record LockerDto(
        Long id,
        Integer lockerNumber,
        Integer keyNumber,
        LockerStatus status,
        Integer gridX,
        Integer gridY
) {
}
