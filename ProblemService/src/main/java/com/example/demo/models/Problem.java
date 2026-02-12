package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false , unique = true)
    private String slug ;

    @Column(nullable = false , unique = true)
    private String title ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty ;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String description ;

    @Column(name = "constraints")
    private String constraints ;

    @Column(nullable = false)
    private String status ;

    @Column(name = "created_at" , updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at" , updatable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "problem" , cascade = CascadeType.ALL)
    private List<ProblemTag> problemTags ;

    @OneToMany(mappedBy = "problem" , cascade = CascadeType.ALL)
    private List<TestCase> testCases ;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now() ;
        this.updatedAt = LocalDateTime.now() ;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now() ;
    }
}
