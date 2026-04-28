package org.example.lockerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;

@EnableAsync
@SpringBootApplication
public class LockerApp {

    public static void main(String[] args) {
        String baseDir =
                System.getProperty("user.home") + "/LockerApp/";

        new File(baseDir).mkdirs();
        new File(baseDir + "backup/db/").mkdirs();
        new File(baseDir + "backup/csv/").mkdirs();

        System.setProperty("app.base-dir", baseDir);


        SpringApplication.run(LockerApp.class, args);
    }

}
