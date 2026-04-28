package org.example.lockerapp.domain;

import jakarta.validation.constraints.NotNull;

public record DeleteAssignmentRequest(
        @NotNull
        Long lockerId
) {
}
