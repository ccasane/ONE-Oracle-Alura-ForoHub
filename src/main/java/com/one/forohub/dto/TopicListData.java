package com.one.forohub.dto;

import com.one.forohub.model.Topic;

import java.time.LocalDateTime;

public record TopicListData(
        Long id,
        String title,
        String message,
        Boolean status,
        String author,
        String course,
        LocalDateTime creationDate) {
    public TopicListData(Topic topic) {
        this(
            topic.getId(),
            topic.getTitle(),
            topic.getMessage(),
            topic.getStatus(),
            topic.getAuthor(),
            topic.getCourse(),
            topic.getCreationDate()
        );
    }
}
