package org.example.lockerapp.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public record CreateLockerRequest(
        @NotBlank(message = ERROR_MESSAGE_LOCKERNUMBER)
        @Min(value = 0, message = ERROR_MESSAGE_MIN )
        @Max(value = 2000, message = ERROR_MESSAGE_MAX)
        Integer lockerNumber,

        @NotBlank(message = ERROR_MESSAGE_KEYNUMBER)
        @Min(value = 0, message = ERROR_MESSAGE_MIN)
        @Max(value = 2000, message = ERROR_MESSAGE_MAX)
        Integer keyNumber,

        @NotBlank(message = ERROR_MESSAGE_MIN)
        @Min(value = 0, message = ERROR_MESSAGE_MIN)
        Integer gridX,

        @NotBlank(message = ERROR_MESSAGE_MIN)
        @Min(value = 0, message = ERROR_MESSAGE_MIN)
        Integer gridY
) {

    private static final String ERROR_MESSAGE_LOCKERNUMBER =
            "Please provide locker number";
    private static final String ERROR_MESSAGE_MIN =
            "Min value 0";
    private static final String ERROR_MESSAGE_MAX =
            "Max value 2000";
    private static final String ERROR_MESSAGE_KEYNUMBER =
            "Please provide key number";

}
