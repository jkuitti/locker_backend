package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateAssignmentRequest;
import org.example.lockerapp.domain.DeleteAssignmentRequest;
import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.domain.entity.Locker;
import org.example.lockerapp.domain.entity.LockerStatus;
import org.example.lockerapp.exeption.AssignmentNotFoundException;
import org.example.lockerapp.exeption.LockerNotFoundException;
import org.example.lockerapp.exeption.RoomNotFoundException;
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
    private final BackupService backupService;

    public AssignmentSerciveImpl(AssignmentRepository assignmentRepository, LockerRepository lockerRepository, BackupService backupService) {
        this.assignmentRepository = assignmentRepository;
        this.lockerRepository = lockerRepository;
        this.backupService = backupService;
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

        Assignment saved = assignmentRepository.save(assignment);

        List<String[]> rows = buildCsvData();
        backupService.runFullBackup(rows);

        return saved;
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


    @Transactional
    @Override
    public void deleteAssignment(Long assignmentId, DeleteAssignmentRequest deleteAssignmentRequest) {
        Locker locker = lockerRepository.findById(deleteAssignmentRequest.lockerId()).orElseThrow(()->new AssignmentNotFoundException(deleteAssignmentRequest.lockerId()));
        assignmentRepository.findById(assignmentId).orElseThrow(()->
                new AssignmentNotFoundException(assignmentId));
        assignmentRepository.deleteById(assignmentId);
        locker.setStatus(LockerStatus.FREE);
        lockerRepository.save(locker);

        List<String[]> rows = buildCsvData();
        backupService.runFullBackup(rows);
    }

    @Override
    public Assignment findByAssignmentId(Long assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow(()-> new AssignmentNotFoundException(assignmentId));
    }

    @Override
    public List<String[]> buildCsvData() {
        return assignmentRepository.getBackupData().stream()
                .map(a -> new String[]{
                        a.lockerNumber(),
                        a.keyNumber() != null ? a.keyNumber().toString() : "",
                        a.employeeFirstName() != null ? a.employeeFirstName() : "",
                        a.employeeLastName() != null ? a.employeeLastName() : "",
                        a.assignedAt() != null ? a.assignedAt().toString() : "",
                        a.gender() != null ? a.gender().toString() : ""
                })
                .toList();
    }
}
