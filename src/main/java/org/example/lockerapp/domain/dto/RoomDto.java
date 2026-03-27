package org.example.lockerapp.domain.dto;

import org.example.lockerapp.domain.entity.Gender;

public record RoomDto(
        Long id,
        String name,
        Gender gender
) {
}
