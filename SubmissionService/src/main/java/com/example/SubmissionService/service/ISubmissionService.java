package com.example.SubmissionService.service;

import com.example.SubmissionService.dtos.SubmissionRequestDTO;
import com.example.SubmissionService.dtos.SubmissionResponseDTO;

import java.util.List;

public interface ISubmissionService {
    SubmissionResponseDTO createSubmission(SubmissionRequestDTO submissionRequestDTO) ;
    SubmissionResponseDTO getSubmissionById(Long id) ;
    List<SubmissionResponseDTO> getSubmissionsByUserId(Long userId) ;
}
