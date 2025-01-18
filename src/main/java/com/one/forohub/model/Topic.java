package com.one.forohub.model;

import com.one.forohub.dto.TopicRegistrationData;
import com.one.forohub.dto.TopicUpdateData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Boolean status;
    private String author;
    private String course;
    private String response;

    public Topic() {
    }

    public Topic(TopicRegistrationData topicRegistrationData) {
        this.title = topicRegistrationData.title();
        this.message = topicRegistrationData.message();
        this.status = true;
        this.author = topicRegistrationData.author();
        this.course = topicRegistrationData.course();
        this.creationDate = LocalDateTime.now();
        this.response = topicRegistrationData.response();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public String getCourse() {
        return course;
    }

    public void updateTopic(TopicUpdateData topicUpdateData) {

        if (topicUpdateData.title() != null) {
            this.title = topicUpdateData.title();
        }
        if (topicUpdateData.message() != null) {
            this.message = topicUpdateData.message();
        }
        if (topicUpdateData.course() != null) {
            this.course = topicUpdateData.course();
        }
    }

    public void deactivateTopic() {
        this.status = false;
    }
}
