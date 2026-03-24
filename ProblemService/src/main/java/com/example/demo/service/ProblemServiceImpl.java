package com.example.demo.service;

import com.example.demo.dtos.CreateProblemRequestDTO;
import com.example.demo.dtos.CreateProblemResponseDTO;
import com.example.demo.dtos.FilterProblemResponse;
import com.example.demo.mapper.ProblemMapper;
import com.example.demo.models.Difficulty;
import com.example.demo.models.Problem;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.ProblemTagRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemServiceImpl implements IProblemService{

    private final ProblemRepository problemRepo ;
    private final TagRepository tagRepo ;
    private final TestCaseRepository testCaseRepo ;
    private final ProblemTagRepository problemTagRepo ;

    @Override
    public CreateProblemResponseDTO createProblem(CreateProblemRequestDTO problemRequestDTO) {
        Problem problem = ProblemMapper.mapToProblemEntity(problemRequestDTO) ;

        Problem createdProblem = problemRepo.save(problem) ;
        log.info("Problem saved with id : " + createdProblem.getId());
        return  ProblemMapper.mapToProblemResponse(createdProblem) ;
    }

    @Override
    public CreateProblemResponseDTO fetchProblemById(Long id) throws Exception {
        if(!problemRepo.existsById(id)) {
            log.warn("Problem not existed with id : " + id);
            throw new Exception("Problem with id doesn't exists") ;
        }
        Problem problem = problemRepo.getReferenceById(id) ;

        return ProblemMapper.mapToProblemResponse(problem) ;
    }

    @Override
    public CreateProblemResponseDTO updateProblem(Long id , CreateProblemRequestDTO problemRequestDTO) throws Exception {
        if(!problemRepo.existsById(id)) {
            log.warn("Problem not existed with id : " + id);
            throw new Exception("Problem with id doesn't exists") ;
        }

        Problem problem  = ProblemMapper.mapToProblemEntity(problemRequestDTO) ;

        Problem updatedProblem = problemRepo.save(problem) ;

        return ProblemMapper.mapToProblemResponse(updatedProblem) ;
    }

    @Override
    public String deleteProblem(Long id) throws Exception {
        if(!problemRepo.existsById(id)) {
            log.warn("Problem not existed with id : " + id);
            throw new Exception("Problem with id doesn't exists") ;
        }
        problemRepo.deleteById(id);
        return "Problem deleted successfully with id : " + id;
    }

    @Override
    public List<FilterProblemResponse> filterProblems(Difficulty difficulty , String tags) {
        List<Problem> problems = problemRepo.filterProblems(difficulty , tags) ;
        log.info(problems.size() + "Problem filterd successfully!");

        List<FilterProblemResponse> problemResponseList = problems.stream()
                .map(ProblemMapper::toFilterProblemResponseDTO)
                .toList() ;

        return problemResponseList ;
    }
}
