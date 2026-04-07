package com.example.SubmissionService.dtos;

import lombok.Data;

@Data
public class SubmissionRequestDTO {
    private Long userId ;
    private Long problemId ;
    private String code ;
    private String language ;
}
