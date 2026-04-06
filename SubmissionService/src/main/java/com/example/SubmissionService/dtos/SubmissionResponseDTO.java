package com.example.SubmissionService.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubmissionResponseDTO {
    private Long id ;
    private String status ;
    private Long runtime ;
    private Long memory ;
}
