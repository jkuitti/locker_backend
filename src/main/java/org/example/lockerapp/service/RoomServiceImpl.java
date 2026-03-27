package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateRoomRequest;
import org.example.lockerapp.domain.entity.Room;
import org.example.lockerapp.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public Room createRoom(CreateRoomRequest createRoomRequest) {
        Room room = new Room(
                null,
                createRoomRequest.name(),
                createRoomRequest.gender()
        );
        return roomRepository.save(room);
    }

    @Override
    public List<Room> listRooms() {
        return roomRepository.findAll();
    }
}
