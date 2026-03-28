package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateAssignmentRequest;
import org.example.lockerapp.domain.entity.Assignment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentService {
    Assignment createAssignment(Long lockerId, CreateAssignmentRequest createAssignmentRequest);
    List<Assignment> listAssignments();
    Assignment findByAssignmentId(Long assignmentId);
    Assignment findByLockerId(Long lockerId);
}
