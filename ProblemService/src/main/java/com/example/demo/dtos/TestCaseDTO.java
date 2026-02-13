package com.example.demo.dtos;

import lombok.Data;

@Data
public class TestCaseDTO {
    private String input;
    private String expectedOutput;
    private Boolean isSample;
}
