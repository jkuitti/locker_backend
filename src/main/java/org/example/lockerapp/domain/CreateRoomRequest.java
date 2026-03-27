package org.example.lockerapp.domain;

import jakarta.validation.constraints.NotBlank;
import org.example.lockerapp.domain.entity.Gender;
import org.hibernate.validator.constraints.Length;

public record CreateRoomRequest(
        @NotBlank(message = ERROR_MESSAGE_NAME)
        @Length(max = 100, message = ERROR_MESSAGE_NAME_LENGTH)
        String name,

        @NotBlank(message = ERROR_MESSAGE_GENDER)
        Gender gender
) {

    private static final String ERROR_MESSAGE_NAME =
            "Please provide room name";
    private static final String ERROR_MESSAGE_NAME_LENGTH =
            "Max length 100 characters";
    private static final String ERROR_MESSAGE_GENDER =
            "Select gender";
}
