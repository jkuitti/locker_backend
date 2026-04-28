package org.example.lockerapp.startup;


import org.example.lockerapp.service.AssignmentService;
import org.example.lockerapp.service.BackupService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    private final BackupService backupService;
    private final AssignmentService assignmentService;

    public StartupRunner(BackupService backupService,
                         AssignmentService assignmentService) {
        this.backupService = backupService;
        this.assignmentService = assignmentService;
    }

    @Override
    public void run(String... args) {
        List<String[]> rows = assignmentService.buildCsvData();
        backupService.runFullBackup(rows);
    }
}