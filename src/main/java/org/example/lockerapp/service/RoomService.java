package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateRoomRequest;
import org.example.lockerapp.domain.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    Room createRoom(CreateRoomRequest createRoomRequest);
    List<Room> listRooms();
}
