package org.example.lockerapp.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BackupService {

    /** DEV
    private static final String DB_PATH = "locker.db";
    private static final String DB_BACKUP_DIR = "backup/db/";
    private static final String CSV_BACKUP_DIR = "backup/csv/";
     **/

    //PROD
    private static final String BASE_DIR =
            System.getProperty("user.home") + "/LockerApp/";
    private static final String DB_PATH =
            BASE_DIR + "locker.db";

    private static final String DB_BACKUP_DIR =
            BASE_DIR + "backup/db/";

    private static final String CSV_BACKUP_DIR =
            BASE_DIR + "backup/csv/";

    private static final int MAX_FILES = 10;

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    // 🔥 MAIN METHOD
    @Async
    public void runFullBackup(List<String[]> csvData) {
        try {
            backupDatabase();
            exportCsv(csvData);
            cleanupOldFiles(DB_BACKUP_DIR);
            cleanupOldFiles(CSV_BACKUP_DIR);
        } catch (Exception e) {
            e.printStackTrace(); // replace with logger later
        }
    }

    // 🟩 DB BACKUP
    private void backupDatabase() throws IOException {
        String timestamp = LocalDateTime.now().format(FORMAT);
        Path source = Paths.get(DB_PATH);
        Path target = Paths.get(DB_BACKUP_DIR + "locker-" + timestamp + ".db");

        Files.createDirectories(target.getParent());

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("DB backup created: " + target);
    }

    // 🟩 CSV EXPORT
    private void exportCsv(List<String[]> rows) throws IOException {
        String timestamp = LocalDateTime.now().format(FORMAT);
        Path file = Paths.get(CSV_BACKUP_DIR + "lockers-" + timestamp + ".csv");

        Files.createDirectories(file.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            // header
            writer.write("pukukaappi_nro,avain_nro,etunimi,sukunimi,pvm,huone");
            writer.newLine();

            for (String[] row : rows) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }

        System.out.println("CSV exported: " + file);
    }

    // 🟩 CLEANUP (keep last 10)
    private void cleanupOldFiles(String dirPath) throws IOException {
        try (Stream<Path> files = Files.list(Paths.get(dirPath))) {

            List<Path> sorted = files
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparingLong(this::getFileTime).reversed())
                    .collect(Collectors.toList());

            if (sorted.size() <= MAX_FILES) return;

            for (int i = MAX_FILES; i < sorted.size(); i++) {
                Files.deleteIfExists(sorted.get(i));
            }
        }
    }

    private long getFileTime(Path path) {
        try {
            return Files.getLastModifiedTime(path).toMillis();
        } catch (IOException e) {
            return 0;
        }
    }
}