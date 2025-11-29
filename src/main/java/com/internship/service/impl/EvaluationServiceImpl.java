package com.internship.service.impl;

import com.internship.dto.EvaluationDTO;
import com.internship.entity.Evaluation;
import com.internship.entity.Internship;
import com.internship.exception.ResourceNotFoundException;
import com.internship.mapper.EvaluationMapper;
import com.internship.repository.EvaluationRepository;
import com.internship.repository.InternshipRepository;
import com.internship.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service implementation for Evaluation operations
 * 
 * @author Internship Management System
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final InternshipRepository internshipRepository;
    private final EvaluationMapper evaluationMapper;

    @Override
    public EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO) {
        Internship internship = internshipRepository.findById(evaluationDTO.getInternshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", evaluationDTO.getInternshipId()));
        
        Evaluation evaluation = evaluationMapper.toEntity(evaluationDTO);
        evaluation.setInternship(internship);
        
        if (evaluation.getCreatedAt() == null) {
            evaluation.setCreatedAt(LocalDateTime.now());
        }
        
        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        return evaluationMapper.toDTO(savedEvaluation);
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluationDTO getEvaluationById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation", "id", id));
        return evaluationMapper.toDTO(evaluation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EvaluationDTO> getAllEvaluations(Pageable pageable) {
        return evaluationRepository.findAll(pageable)
                .map(evaluationMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EvaluationDTO> getEvaluationsByInternshipId(Long internshipId, Pageable pageable) {
        return evaluationRepository.findByInternshipId(internshipId, pageable)
                .map(evaluationMapper::toDTO);
    }

    @Override
    public EvaluationDTO updateEvaluation(Long id, EvaluationDTO evaluationDTO) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation", "id", id));
        
        // Update internship if changed
        if (!evaluation.getInternship().getId().equals(evaluationDTO.getInternshipId())) {
            Internship internship = internshipRepository.findById(evaluationDTO.getInternshipId())
                    .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", evaluationDTO.getInternshipId()));
            evaluation.setInternship(internship);
        }
        
        evaluation.setTechnicalScore(evaluationDTO.getTechnicalScore());
        evaluation.setSoftSkillsScore(evaluationDTO.getSoftSkillsScore());
        evaluation.setComments(evaluationDTO.getComments());
        
        Evaluation updatedEvaluation = evaluationRepository.save(evaluation);
        return evaluationMapper.toDTO(updatedEvaluation);
    }

    @Override
    public void deleteEvaluation(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation", "id", id));
        evaluationRepository.delete(evaluation);
    }
}

