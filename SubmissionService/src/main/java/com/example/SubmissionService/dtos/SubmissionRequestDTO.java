package com.example.SubmissionService.dtos;

import lombok.Data;

@Data
public class SubmissionRequestDTO {
    Long userId ;
    Long problemId ;
    String code ;
    String language ;
}
