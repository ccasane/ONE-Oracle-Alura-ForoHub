package com.one.forohub.dto;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateData(
        @NotNull
        Long id,
        String title,
        String message,
        String course
) {
}
