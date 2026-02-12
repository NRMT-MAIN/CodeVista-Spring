package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags")
public class Tag {
    @Id
    private Long id ;

    @Column(nullable = false , unique = true)
    private String name ;

    @OneToMany(mappedBy = "tag")
    private List<ProblemTag> problemTags ;
}
