package org.example.lockerapp.controller;

import jakarta.validation.Valid;
import org.example.lockerapp.domain.CreateRoomRequest;
import org.example.lockerapp.domain.CreateRoomSizeUpdateRequest;
import org.example.lockerapp.domain.dto.RoomDto;
import org.example.lockerapp.domain.entity.Room;
import org.example.lockerapp.mapper.Mapper;
import org.example.lockerapp.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;
    private final Mapper mapper;

    public RoomController(RoomService roomService, Mapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom (@Valid @RequestBody CreateRoomRequest createRoomRequest){
        Room room = roomService.createRoom(createRoomRequest);
        RoomDto createdRoomDto = mapper.toDto(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomDto);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> listRooms(){
        List<Room> rooms = roomService.listRooms();
        List<RoomDto> roomDtos = rooms.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(roomDtos);
    }

    @DeleteMapping(path = "/{roomId}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable Long roomId
    ){
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(
            @PathVariable Long roomId
    ){
        Room room = roomService.getRoomById(roomId);
        RoomDto roomDto = mapper.toDto(room);
        return ResponseEntity.ok(roomDto);
    }

    @PutMapping(path = "/{roomId}")
    public ResponseEntity<RoomDto> updateRoomSize(
            @PathVariable Long roomId,
            @Valid @RequestBody CreateRoomSizeUpdateRequest createRoomSizeUpdateRequest)
    {
        Room room = roomService.resizeRoom(roomId, createRoomSizeUpdateRequest);
        RoomDto roomDto = mapper.toDto(room);
        return ResponseEntity.ok(roomDto);
    }
}
