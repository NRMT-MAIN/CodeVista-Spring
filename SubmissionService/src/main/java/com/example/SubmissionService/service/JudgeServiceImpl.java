package com.example.SubmissionService.service;


import com.example.SubmissionService.client.AWSS3ServiceClient;
import com.example.SubmissionService.client.ProblemServiceClient;
import com.example.SubmissionService.dtos.TestCaseDTO;
import com.example.SubmissionService.entity.Submission;
import com.example.SubmissionService.executor.CodeExecutor;
import com.example.SubmissionService.respository.SubmissionRepository;
import com.example.SubmissionService.utility.TestCaseParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {

    private final ProblemServiceClient problemClient;
    private final SubmissionRepository repository;
    private final CodeExecutor executor;
    private final AWSS3ServiceClient s3Client;
    private final TestCaseParser testCaseParser;

    public void processSubmission(Submission submission) {
        // 1. Mark RUNNING
        submission.setStatus("RUNNING");
        repository.save(submission);

        Long problemId = submission.getProblemId();
        // 2. Fetch test cases - Fetch both files ONCE
        String inputKey = "problems/" + problemId + "/input.txt";
        String outputKey = "problems/" + problemId + "/output.txt";

        // 3. Fetch test cases from S3
        String fullInput = s3Client.getFileContent(inputKey);
        String fullOutput = s3Client.getFileContent(outputKey);

        // 4. Parse into test cases
        List<TestCaseDTO> testCases = testCaseParser.parse(fullInput, fullOutput);
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