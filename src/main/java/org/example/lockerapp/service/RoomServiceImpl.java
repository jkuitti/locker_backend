package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateRoomRequest;
import org.example.lockerapp.domain.CreateRoomSizeUpdateRequest;
import org.example.lockerapp.domain.entity.Room;
import org.example.lockerapp.exeption.RoomNotFoundException;
import org.example.lockerapp.repository.LockerRepository;
import org.example.lockerapp.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final LockerRepository lockerRepository;

    public RoomServiceImpl(RoomRepository roomRepository, LockerRepository lockerRepository) {
        this.roomRepository = roomRepository;
        this.lockerRepository = lockerRepository;
    }


    @Override
    public Room createRoom(CreateRoomRequest createRoomRequest) {
        Room room = new Room(
                null,
                createRoomRequest.name(),
                createRoomRequest.gender(),
                10,
                10
        );
        return roomRepository.save(room);
    }

    @Override
    public List<Room> listRooms() {
        return roomRepository.findAll();
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepository.findById(roomId).orElseThrow(()->
                new RoomNotFoundException(roomId));

        boolean hasLockers = lockerRepository.existsByRoomId(roomId);
        System.out.println(hasLockers);
        if (hasLockers) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Huoneen poisto epäonnistui, koska huoneessa on pukukaappeja"
            );
        }
        roomRepository.deleteById(roomId);
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
    }

    @Override
    public Room resizeRoom(Long roomId, CreateRoomSizeUpdateRequest createRoomSizeUpdateRequest) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
        room.setGridRows(createRoomSizeUpdateRequest.gridRows());
        room.setGridCols(createRoomSizeUpdateRequest.gridCols());
        return roomRepository.save(room);
    }
}
