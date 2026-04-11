package org.example.lockerapp.domain.dto;

import org.example.lockerapp.domain.entity.Gender;
import org.example.lockerapp.domain.entity.LockerStatus;
import org.example.lockerapp.domain.entity.Room;

public record LockerDto(
        Long id,
        String lockerNumber,
        Integer keyNumber,
        LockerStatus status,
        Long roomId,
        String roomName,
        Gender roomGender,
        Integer gridX,
        Integer gridY
) {
}
