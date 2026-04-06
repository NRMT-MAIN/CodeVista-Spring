package com.example.SubmissionService.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate ;

    private static final String TOPIC = "submission-topic" ;

    public void sendSubmissionMessage(Object message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
