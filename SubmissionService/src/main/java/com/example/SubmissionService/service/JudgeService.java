package com.example.SubmissionService.service;

import com.example.SubmissionService.entity.Submission;

public interface JudgeService {
    void processSubmission(Submission submission) ;
    void updateSubmission(Submission submission , String status, Long runtime) ;
}
