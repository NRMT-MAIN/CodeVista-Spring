package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseDTO {
    private String input;
    private String expectedOutput;
    private Boolean isSample;
}
