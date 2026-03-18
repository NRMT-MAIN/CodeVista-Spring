package com.example.demo.dtos;

import com.example.demo.models.Difficulty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterProblemResponse {
    private Long id ;
    private String slug ;
    private String title ;
    private Difficulty difficulty ;
}
