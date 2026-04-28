package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateAssignmentRequest;
import org.example.lockerapp.domain.DeleteAssignmentRequest;
import org.example.lockerapp.domain.entity.Assignment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface AssignmentService {
    Assignment createAssignment(Long lockerId, CreateAssignmentRequest createAssignmentRequest);
    List<Assignment> listAssignments();
    Assignment findByAssignmentId(Long assignmentId);
    Assignment findByLockerId(Long lockerId);
    void deleteAssignment(Long assignmentId, DeleteAssignmentRequest deleteAssignmentRequest);
    List<String[]> buildCsvData();

}
