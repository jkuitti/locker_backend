package org.example.lockerapp.service;

import org.example.lockerapp.domain.CreateAssignmentRequest;
import org.example.lockerapp.domain.entity.Assignment;
import org.example.lockerapp.exeption.AssignmentNotFoundException;
import org.example.lockerapp.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentSerciveImpl implements AssignmentService{

    private final AssignmentRepository assignmentRepository;

    public AssignmentSerciveImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }


    @Override
    public Assignment createAssignment(Long lockerId, CreateAssignmentRequest createAssignmentRequest) {
        return null;
    }

    @Override
    public List<Assignment> listAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment findById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow(()-> new AssignmentNotFoundException(assignmentId));
    }
}
