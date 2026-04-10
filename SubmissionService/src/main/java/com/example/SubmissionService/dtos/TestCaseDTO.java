package com.example.SubmissionService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseDTO {
    private String input ;
    private String expectedOutput ;
}
