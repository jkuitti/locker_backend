package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateLockerRequest;
import org.example.lockerapp.domain.dto.LockerAssignmentDto;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.domain.entity.LockerStatus;
import org.example.lockerapp.domain.entity.Room;
import org.example.lockerapp.exeption.LockerNotFoundException;
import org.example.lockerapp.exeption.RoomNotFoundException;
import org.example.lockerapp.mapper.Mapper;
import org.example.lockerapp.repository.LockerRepository;
import org.example.lockerapp.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockerSerciveImpl implements LockerService{
    private final LockerRepository lockerRepository;
    private final RoomRepository roomRepository;

    public LockerSerciveImpl(LockerRepository lockerRepository, RoomRepository roomRepository) {
        this.lockerRepository = lockerRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public Locker createLocker(Long roomId, CreateLockerRequest createLockerRequest) {
        Room room = roomRepository.findById(roomId).orElseThrow(()-> new RoomNotFoundException(roomId));
        Locker locker = new Locker(
                null,
                createLockerRequest.lockerNumber(),
                createLockerRequest.keyNumber(),
                LockerStatus.FREE,
                room,
                createLockerRequest.gridX(),
                createLockerRequest.gridY()
        );

        return lockerRepository.save(locker);
    }

    @Override
    public List<Locker> listLockers(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));

        return lockerRepository.findAllByRoomId(roomId);
    }

    @Override
    public Locker findById(Long lockerId) {
        return lockerRepository.findById(lockerId)
                .orElseThrow(() -> new LockerNotFoundException(lockerId));
    }

    @Override
    public List<Locker> getAllLockers() {
        return lockerRepository.findAll();
    }

    @Override
    public void deleteLocker(Long lockerId) {
        lockerRepository.findById(lockerId).orElseThrow(()->
                new LockerNotFoundException(lockerId));
        lockerRepository.deleteById(lockerId);
    }

    @Override
    public List<LockerAssignmentDto> listLockerAssigment(Long roomId) {
        return lockerRepository.findLockersAndAssignments(roomId);
    }
}
