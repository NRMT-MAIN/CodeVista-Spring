package com.example.demo.controller;


import com.example.demo.dtos.CreateProblemRequestDTO;
import com.example.demo.dtos.CreateProblemResponseDTO;
import com.example.demo.service.IProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final IProblemService problemService;

    @PostMapping
    public ResponseEntity<CreateProblemResponseDTO> createProblem(@RequestBody CreateProblemRequestDTO dto) {
        CreateProblemResponseDTO responseDTO = problemService.createProblem(dto) ;

        return new ResponseEntity<>(responseDTO , HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateProblemResponseDTO> getProblemById(@PathVariable Long id) throws Exception {
        CreateProblemResponseDTO responseDTO = problemService.fetchProblemById(id) ;

        return new ResponseEntity<>(responseDTO , HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateProblemResponseDTO> updateProblem(@PathVariable Long id , @RequestBody CreateProblemRequestDTO dto) throws Exception {
        CreateProblemResponseDTO responseDTO = problemService.updateProblem(id, dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProblem(@PathVariable Long id) throws Exception {
        String response = problemService.deleteProblem(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filter/{difficulty}/{tags}")
    public ResponseEntity<?> filterProblems(@PathVariable String difficulty , @PathVariable String tags) {
        return new ResponseEntity<>(problemService.filterProblems(difficulty, tags), HttpStatus.OK) ;
    }
}
