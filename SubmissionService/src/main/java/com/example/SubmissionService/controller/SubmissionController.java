package com.example.SubmissionService.controller;

import com.example.SubmissionService.dtos.SubmissionRequestDTO;
import com.example.SubmissionService.dtos.SubmissionResponseDTO;
import com.example.SubmissionService.service.SubmissionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionServiceImpl submissionService;

    @PostMapping
    public ResponseEntity<SubmissionResponseDTO> create(@RequestBody SubmissionRequestDTO dto) {

        return ResponseEntity.ok(submissionService.createSubmission(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.getSubmissionById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getUserSubmissions(@PathVariable Long userId) {

        return ResponseEntity.ok(submissionService.getSubmissionsByUserId(userId));
    }
}