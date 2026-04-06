package com.example.SubmissionService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Long userId ;
    private Long problemId ;

    @Lob
    private String code ;

    private String language ;

    private String status ;

    private Long runtime ;
    private Long memory ;

    private LocalDateTime createdAt ;
}
