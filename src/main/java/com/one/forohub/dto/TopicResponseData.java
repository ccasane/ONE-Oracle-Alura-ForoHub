package com.one.forohub.dto;

import java.time.LocalDateTime;

public record TopicResponseData(
    Long id,
    String title,
    String message,
    LocalDateTime creationDate,
    Boolean status,
    String author,
    String course
) {
}
