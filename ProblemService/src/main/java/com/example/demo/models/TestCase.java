package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_cases")
public class TestCase {
    @Id
    private Long id ;

    @Column(columnDefinition = "TINYTEXT" , nullable = false)
    private String input;

    @Column(name = "expected_output", columnDefinition = "TINYTEXT", nullable = false)
    private String expectedOutput;

    @Column(name = "is_sample")
    private Boolean isSample = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;
}
