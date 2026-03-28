package org.example.lockerapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LockerNotFoundException extends RuntimeException {

    public LockerNotFoundException(Long id) {
        super("Locker not found with id: " + id);
    }
}
