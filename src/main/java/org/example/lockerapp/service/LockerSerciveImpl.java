package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateLockerRequest;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.mapper.Mapper;
import org.example.lockerapp.repository.LockerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockerSerciveImpl implements LockerService{
    private final LockerRepository lockerRepository;

    public LockerSerciveImpl(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }


    @Override
    public Locker createLocker(Long roomId, CreateLockerRequest createLockerRequest) {
        return new Locker();
    }

    @Override
    public List<Locker> listLockers(Long roomId) {
        return List.of();
    }
}
