package com.example.SubmissionService.service;


import com.example.SubmissionService.client.ProblemServiceClient;
import com.example.SubmissionService.dtos.TestCaseDTO;
import com.example.SubmissionService.entity.Submission;
import com.example.SubmissionService.executor.CodeExecutor;
import com.example.SubmissionService.respository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {

    private final ProblemServiceClient problemClient;
    private final SubmissionRepository repository;
    private final CodeExecutor executor;

    public void processSubmission(Submission submission) {
        // 1. Mark RUNNING
        submission.setStatus("RUNNING");
        repository.save(submission);

        // 2. Fetch test cases
        List<TestCaseDTO> testCases = problemClient.getTestCases(submission.getProblemId());

        long startTime = System.currentTimeMillis();

        for (TestCaseDTO tc : testCases) {

            String output = executor.execute(
                    submission.getCode(),
                    submission.getLanguage(),
                    tc.getInput()
            );

            if ("ERROR".equals(output)) {
                updateSubmission(submission, "RUNTIME_ERROR", 0L);
                return;
            }

            if (!output.trim().equals(tc.getExpectedOutput().trim())) {
                updateSubmission(submission, "WRONG_ANSWER", 0L);
                return;
            }
        }

        long runtime = System.currentTimeMillis() - startTime;

        updateSubmission(submission, "ACCEPTED", runtime);
    }

    public void updateSubmission(Submission submission, String status, Long runtime) {
        submission.setStatus(status);
        submission.setRuntime(runtime);
        repository.save(submission);
    }
}