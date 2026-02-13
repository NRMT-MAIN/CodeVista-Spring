package com.example.demo.repository;

import com.example.demo.models.Problem;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem , Long> {
    Optional<Problem> findBySlug(String slug);

    @Query("""
        SELECT DISTINCT p FROM Problem p
        JOIN p.problemTags pt
        JOIN pt.tag t
        WHERE (:difficulty IS NULL OR p.difficulty = :difficulty)
          AND (:tag IS NULL OR t.name = :tag)
    """)
    List<Problem> filterProblems(
            @Param("difficulty") String difficulty,
            @Param("tag") String tag
    );
}
