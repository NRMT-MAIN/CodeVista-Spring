package com.example.SubmissionService.service;

import com.example.SubmissionService.client.ProblemServiceClient;
import com.example.SubmissionService.dtos.SubmissionRequestDTO;
import com.example.SubmissionService.dtos.SubmissionResponseDTO;
import com.example.SubmissionService.entity.Submission;
import com.example.SubmissionService.producer.SubmissionProducer;
import com.example.SubmissionService.respository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements ISubmissionService {
    private final SubmissionProducer submissionProducer ;
    private final SubmissionRepository submissionRepository ;
    private final ProblemServiceClient problemServiceClient ;

    @Override
    public SubmissionResponseDTO createSubmission(SubmissionRequestDTO submissionRequestDTO) {
        // Valid problemId
        var testCases = problemServiceClient.getTestCases(submissionRequestDTO.getProblemId()) ;
        if (testCases.isEmpty()) {
            throw new IllegalArgumentException("Invalid problemId: " + submissionRequestDTO.getProblemId()) ;
        }

        // Save submission to DB
        Submission submission = Submission.builder()
                .userId(submissionRequestDTO.getUserId())
                .problemId(submissionRequestDTO.getProblemId())
                .code(submissionRequestDTO.getCode())
                .language(submissionRequestDTO.getLanguage())
                .status("PENDING")
                .build() ;

        submission = submissionRepository.save(submission) ;

        // Send message to Kafka for processing
        submissionProducer.sendSubmissionMessage(submission) ;

        // Return response
        return SubmissionResponseDTO.builder()
                .id(submission.getId())
                .problemId(submission.getProblemId())
                .memory(submission.getMemory())
                .runtime(submission.getRuntime())
                .status(submission.getStatus())
                .build() ;
    }

    @Override
    public List<SubmissionResponseDTO> getSubmissionsByUserId(Long userId) {
        List<Submission> submissions = submissionRepository.findByUserId(userId) ;

        return submissions.stream()
                .map(submission -> SubmissionResponseDTO.builder()
                        .id(submission.getId())
                        .problemId(submission.getProblemId())
                        .memory(submission.getMemory())
                        .runtime(submission.getRuntime())
                        .status(submission.getStatus())
                        .build())
                .toList() ;
    }

    @Override
    public SubmissionResponseDTO getSubmissionById(Long id) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found with id: " + id)) ;

        return SubmissionResponseDTO.builder()
                .id(submission.getId())
                .problemId(submission.getProblemId())
                .memory(submission.getMemory())
                .runtime(submission.getRuntime())
                .status(submission.getStatus())
                .build() ;
    }
}
