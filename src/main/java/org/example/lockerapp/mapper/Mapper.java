package org.example.lockerapp.mapper;

import org.example.lockerapp.domain.dto.AssignmentDto;
import org.example.lockerapp.domain.dto.LockerDto;
import org.example.lockerapp.domain.dto.RoomDto;
import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.domain.entity.Room;
import org.springframework.stereotype.Component;


public interface Mapper {
    RoomDto toDto(Room room);

    LockerDto toDto(Locker locker);

    AssignmentDto toDto(Assignment assignment);

}
