package com.internship.service;

import com.internship.dto.EvaluationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for Evaluation operations
 * 
 * @author Internship Management System
 */
public interface EvaluationService {
    
    EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO);
    
    EvaluationDTO getEvaluationById(Long id);
    
    Page<EvaluationDTO> getAllEvaluations(Pageable pageable);
    
    Page<EvaluationDTO> getEvaluationsByInternshipId(Long internshipId, Pageable pageable);
    
    EvaluationDTO updateEvaluation(Long id, EvaluationDTO evaluationDTO);
    
    void deleteEvaluation(Long id);
}

