package org.example.lockerapp.mapper;

import org.example.lockerapp.domain.dto.AssignmentDto;
import org.example.lockerapp.domain.dto.LockerDto;
import org.example.lockerapp.domain.dto.RoomDto;
import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.domain.entity.Room;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl implements Mapper {

    @Override
    public RoomDto toDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getGender(),
                room.getGridRows(),
                room.getGridCols()
        );
    }

    @Override
    public LockerDto toDto(Locker locker) {
        return new LockerDto(
                locker.getId(),
                locker.getLockerNumber(),
                locker.getKeyNumber(),
                locker.getStatus(),
                locker.getRoom().getId(),
                locker.getRoom().getName(),
                locker.getRoom().getGender(),
                locker.getGridX(),
                locker.getGridY()
        );
    }

    @Override
    public AssignmentDto toDto(Assignment assignment) {
        return new AssignmentDto(
                assignment.getId(),
                assignment.getAssignedAt(),
                assignment.getEmployeeLastName(),
                assignment.getEmployeeFirstName(),
                assignment.getLocker().getLockerNumber(),
                assignment.getLocker().getKeyNumber(),
                assignment.getLocker().getRoom().getGender()
        );
    }
}
