package org.example.lockerapp.domain.dto;

import org.example.lockerapp.domain.entity.LockerStatus;
import org.example.lockerapp.domain.entity.Room;

public record LockerDto(
        Long id,
        Integer lockerNumber,
        Integer keyNumber,
        LockerStatus status,
        Room room,
        Integer gridX,
        Integer gridY
) {
}
