package org.example.lockerapp.domain;

import jakarta.validation.constraints.NotNull;

public record CreateRoomSizeUpdateRequest(
        @NotNull
        Integer gridRows,
        @NotNull
        Integer gridCols
) {
}
