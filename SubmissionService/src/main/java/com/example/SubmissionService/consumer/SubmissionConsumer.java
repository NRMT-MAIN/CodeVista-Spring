package com.example.SubmissionService.consumer;

import com.example.SubmissionService.entity.Submission;
import com.example.SubmissionService.service.JudgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionConsumer {

    private final JudgeService judgeService;

    @KafkaListener(topics = "submission-topic", groupId = "submission-group")
    public void consume(Submission submission) {
        judgeService.processSubmission(submission);
    }
}