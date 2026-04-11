package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateAssignmentRequest;
import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.domain.entity.LockerStatus;
import org.example.lockerapp.exeption.AssignmentNotFoundException;
import org.example.lockerapp.exeption.LockerNotFoundException;
import org.example.lockerapp.repository.AssignmentRepository;
import org.example.lockerapp.repository.LockerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentSerciveImpl implements AssignmentService{

    private final AssignmentRepository assignmentRepository;
    private final LockerRepository lockerRepository;

    public AssignmentSerciveImpl(AssignmentRepository assignmentRepository, LockerRepository lockerRepository) {
        this.assignmentRepository = assignmentRepository;
        this.lockerRepository = lockerRepository;
    }

    @Transactional
    @Override
    public Assignment createAssignment(Long lockerId, CreateAssignmentRequest createAssignmentRequest){
        Locker locker = lockerRepository.findById(lockerId).orElseThrow(()-> new LockerNotFoundException(lockerId));

        LocalDateTime dateTime = LocalDateTime.now().withNano(0);
        Assignment assignment = new Assignment(
                null,
                dateTime,
                createAssignmentRequest.employeeLastName(),
                createAssignmentRequest.employeeFirstName(),
                locker
        );
        locker.setStatus(LockerStatus.OCCUPIED);
        lockerRepository.save(locker);

        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> listAssignments() {
        return assignmentRepository.findAll();
    }


    @Override
    public Assignment findByLockerId(Long lockerId) {
        Locker locker = lockerRepository.findById(lockerId).orElseThrow(()-> new LockerNotFoundException(lockerId));
        return assignmentRepository.findByLocker(locker);
    }

    @Override
    public Assignment findByAssignmentId(Long assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow(()-> new AssignmentNotFoundException(assignmentId));
    }
}
