package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateLockerRequest;
import org.example.lockerapp.domain.entity.Locker;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LockerService {
    Locker createLocker(Long roomId, CreateLockerRequest createLockerRequest);
    List<Locker> listLockers(Long roomId);
    Locker findById(Long lockerId);
    List<Locker> getAllLockers();
    void deleteLocker(Long lockerId);

}
