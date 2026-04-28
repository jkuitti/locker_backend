package org.example.lockerapp.controller;

import jakarta.validation.Valid;
import org.example.lockerapp.domain.CreateAssignmentRequest;
import org.example.lockerapp.domain.DeleteAssignmentRequest;
import org.example.lockerapp.domain.dto.AssignmentDto;
import org.example.lockerapp.domain.dto.LockerAssignmentDto;
import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.mapper.Mapper;
import org.example.lockerapp.service.AssignmentService;
import org.example.lockerapp.service.LockerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final LockerService lockerService;
    private final Mapper mapper;

    public AssignmentController(AssignmentService assignmentService, LockerService lockerService, Mapper mapper) {
        this.assignmentService = assignmentService;
        this.lockerService = lockerService;
        this.mapper = mapper;

    }

    @GetMapping(path = "/assignments/{assignmentId}")
    public ResponseEntity<AssignmentDto> getByAssignmentId(
            @PathVariable Long assignmentId
    ){
        Assignment assignment = assignmentService.findByAssignmentId(assignmentId);
        AssignmentDto assignmentDto = mapper.toDto(assignment);
        return ResponseEntity.ok(assignmentDto);
    }

    @GetMapping(path = "/assignments")
    public ResponseEntity<List<AssignmentDto>> listAssignments(){
        List<Assignment> assignments = assignmentService.listAssignments();
        List<AssignmentDto> assignmentDtos = assignments.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(assignmentDtos);
    }

    @PostMapping(path = "/lockers/{lockerId}/assignment")
    public ResponseEntity<AssignmentDto> createAssignment(
            @PathVariable Long lockerId,
        @Valid @RequestBody CreateAssignmentRequest createAssignmentRequest
    ){
        Assignment assignment = assignmentService.createAssignment(lockerId, createAssignmentRequest);
        AssignmentDto assignmentDto = mapper.toDto(assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentDto);
    }

    @GetMapping(path = "/lockers/{lockerId}/assignment")
    public ResponseEntity<AssignmentDto> getByLockerId(
            @PathVariable Long lockerId
    ){
        Assignment assignment = assignmentService.findByLockerId(lockerId);
        AssignmentDto assignmentDto = mapper.toDto(assignment);
        return ResponseEntity.ok(assignmentDto);
    }


    @DeleteMapping(path = "/assignments/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(
            @PathVariable Long assignmentId,
            @Valid @RequestBody DeleteAssignmentRequest deleteAssignmentRequest
    ){
        assignmentService.deleteAssignment(assignmentId, deleteAssignmentRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rooms/{roomId}/assignments")
    public ResponseEntity <List<LockerAssignmentDto>> listRoomAssignments(
            @PathVariable Long roomId
    ) {
        List<LockerAssignmentDto> lockerAssignmentDtos = lockerService.listLockerAssigment(roomId);
        return  ResponseEntity.ok(lockerAssignmentDtos);
    }




}
