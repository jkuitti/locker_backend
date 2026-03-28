package org.example.lockerapp.controller;

import org.example.lockerapp.domain.dto.AssignmentDto;
import org.example.lockerapp.mapper.Mapper;
import org.example.lockerapp.service.AssignmentService;
import org.example.lockerapp.service.LockerService;
import org.example.lockerapp.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/rooms/{roomId}/lockers/{lockerId}")
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final Mapper mapper;
    private final LockerService lockerService;
    private final RoomService roomService;

    public AssignmentController(AssignmentService assignmentService, Mapper mapper, LockerService lockerService, RoomService roomService) {
        this.assignmentService = assignmentService;
        this.mapper = mapper;
        this.lockerService = lockerService;
        this.roomService = roomService;
    }

    @GetMapping(path = "/{assignmentId}")
    public ResponseEntity<AssignmentDto> getById(
            @PathVariable Long roomId,
            @PathVariable Long lockerId
    ){
        lockerService.findById(lockerId);




    }

}
