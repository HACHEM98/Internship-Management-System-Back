package com.internship.service;

import com.internship.dto.ProgressReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for ProgressReport operations
 * 
 * @author Internship Management System
 */
public interface ProgressReportService {
    
    ProgressReportDTO createProgressReport(ProgressReportDTO progressReportDTO);
    
    ProgressReportDTO getProgressReportById(Long id);
    
    Page<ProgressReportDTO> getAllProgressReports(Pageable pageable);
    
    Page<ProgressReportDTO> getProgressReportsByInternshipId(Long internshipId, Pageable pageable);
    
    ProgressReportDTO updateProgressReport(Long id, ProgressReportDTO progressReportDTO);
    
    void deleteProgressReport(Long id);
}

