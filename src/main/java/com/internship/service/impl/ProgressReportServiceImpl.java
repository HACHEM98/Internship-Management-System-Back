package com.internship.service.impl;

import com.internship.dto.ProgressReportDTO;
import com.internship.entity.Internship;
import com.internship.entity.ProgressReport;
import com.internship.exception.ResourceNotFoundException;
import com.internship.mapper.ProgressReportMapper;
import com.internship.repository.InternshipRepository;
import com.internship.repository.ProgressReportRepository;
import com.internship.service.ProgressReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service implementation for ProgressReport operations
 * 
 * @author Internship Management System
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProgressReportServiceImpl implements ProgressReportService {

    private final ProgressReportRepository progressReportRepository;
    private final InternshipRepository internshipRepository;
    private final ProgressReportMapper progressReportMapper;

    @Override
    public ProgressReportDTO createProgressReport(ProgressReportDTO progressReportDTO) {
        Internship internship = internshipRepository.findById(progressReportDTO.getInternshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", progressReportDTO.getInternshipId()));
        
        ProgressReport progressReport = progressReportMapper.toEntity(progressReportDTO);
        progressReport.setInternship(internship);
        
        if (progressReport.getSubmittedAt() == null) {
            progressReport.setSubmittedAt(LocalDateTime.now());
        }
        
        ProgressReport savedReport = progressReportRepository.save(progressReport);
        return progressReportMapper.toDTO(savedReport);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgressReportDTO getProgressReportById(Long id) {
        ProgressReport progressReport = progressReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProgressReport", "id", id));
        return progressReportMapper.toDTO(progressReport);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProgressReportDTO> getAllProgressReports(Pageable pageable) {
        return progressReportRepository.findAll(pageable)
                .map(progressReportMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProgressReportDTO> getProgressReportsByInternshipId(Long internshipId, Pageable pageable) {
        return progressReportRepository.findByInternshipId(internshipId, pageable)
                .map(progressReportMapper::toDTO);
    }

    @Override
    public ProgressReportDTO updateProgressReport(Long id, ProgressReportDTO progressReportDTO) {
        ProgressReport progressReport = progressReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProgressReport", "id", id));
        
        // Update internship if changed
        if (!progressReport.getInternship().getId().equals(progressReportDTO.getInternshipId())) {
            Internship internship = internshipRepository.findById(progressReportDTO.getInternshipId())
                    .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", progressReportDTO.getInternshipId()));
            progressReport.setInternship(internship);
        }
        
        progressReport.setWeekNumber(progressReportDTO.getWeekNumber());
        progressReport.setTasksCompleted(progressReportDTO.getTasksCompleted());
        progressReport.setChallenges(progressReportDTO.getChallenges());
        progressReport.setNextWeekPlan(progressReportDTO.getNextWeekPlan());
        
        ProgressReport updatedReport = progressReportRepository.save(progressReport);
        return progressReportMapper.toDTO(updatedReport);
    }

    @Override
    public void deleteProgressReport(Long id) {
        ProgressReport progressReport = progressReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProgressReport", "id", id));
        progressReportRepository.delete(progressReport);
    }
}

