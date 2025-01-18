package com.one.forohub.controller;

import com.one.forohub.dto.TopicListData;
import com.one.forohub.dto.TopicRegistrationData;
import com.one.forohub.dto.TopicResponseData;
import com.one.forohub.dto.TopicUpdateData;
import com.one.forohub.model.Topic;
import com.one.forohub.repository.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<TopicResponseData> registerTopic(@RequestBody @Valid TopicRegistrationData topicRegistrationData,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(new Topic(topicRegistrationData));
        TopicResponseData topicResponseData = new TopicResponseData(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor(),
                topic.getCourse()
        );
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicResponseData);
    }

    @GetMapping
    public ResponseEntity<Page<TopicListData>> listTopics(@PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(topicRepository.findAll(pagination).map(TopicListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicResponseData> updateTopic(@RequestBody @Valid TopicUpdateData topicUpdateData) {
        Topic topic = topicRepository.getReferenceById(topicUpdateData.id());
        topic.updateTopic(topicUpdateData);
        return ResponseEntity.ok(new TopicResponseData(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor(),
                topic.getCourse()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.deactivateTopic();
        return ResponseEntity.noContent().build();
    }
}
