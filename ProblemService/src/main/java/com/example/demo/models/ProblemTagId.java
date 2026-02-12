package com.example.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProblemTagId implements Serializable {

    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "tag_id")
    private Long tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProblemTagId)) return false;
        ProblemTagId that = (ProblemTagId) o;
        return Objects.equals(problemId, that.problemId)
                && Objects.equals(tagId, that.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemId, tagId);
    }
}
