package org.example.lockerapp.controller;

import jakarta.validation.Valid;
import org.example.lockerapp.domain.CreateLockerRequest;
import org.example.lockerapp.domain.dto.LockerDto;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.mapper.Mapper;
import org.example.lockerapp.service.LockerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class LockerController {

    private final LockerService lockerService;
    private final Mapper mapper;

    public LockerController(LockerService lockerService, Mapper mapper) {
        this.lockerService = lockerService;
        this.mapper = mapper;
    }

    @PostMapping(path = "/rooms/{roomId}/lockers")
    public ResponseEntity<LockerDto> createLocker(
            @PathVariable Long roomId,
            @Valid @RequestBody CreateLockerRequest createLockerRequest
    ){
        Locker locker = lockerService.createLocker(roomId, createLockerRequest);
        LockerDto cretedLockerDto = mapper.toDto(locker);
        return ResponseEntity.status(HttpStatus.CREATED).body(cretedLockerDto);
    }

    @GetMapping(path = "/rooms/{roomId}/lockers")
    public ResponseEntity<List<LockerDto>> listLockersByRoom(@PathVariable Long roomId){
        List<Locker> lockers = lockerService.listLockers(roomId);

        List<LockerDto> lockerDtos = lockers.stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(lockerDtos);
    }

    @DeleteMapping(path = "/lockers/{lockerId}")
    public ResponseEntity<Void> deleteLocker(
            @PathVariable Long lockerId
    ){
        lockerService.deleteLocker(lockerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/lockers")
    public ResponseEntity<List<LockerDto>> listAllLockers(){
        List<Locker> lockers = lockerService.getAllLockers();
        List<LockerDto> lockerDtos = lockers.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(lockerDtos);
    }
}
