package com.one.forohub.dto;

import jakarta.validation.constraints.NotBlank;


public record TopicRegistrationData(
    @NotBlank
    String title,

    @NotBlank
    String message,

    @NotBlank
    String author,

    @NotBlank
    String course,
    String response
) {
}
