package org.example.lockerapp.domain;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateAssignmentRequest(
        @NotBlank(message = ERROR_MESSAGE_NAME)
        @Length(max = 50, message = ERROR_MESSAGE_NAME_LENGTH)
        String employeeLastName,

        @NotBlank(message = ERROR_MESSAGE_NAME)
        @Length(max = 50, message = ERROR_MESSAGE_NAME_LENGTH)
        String employeeFirstName
) {

    private static final String ERROR_MESSAGE_NAME =
            "Please provide locker number";
    private static final String ERROR_MESSAGE_NAME_LENGTH =
            "Max name legth 50 characters";

}
