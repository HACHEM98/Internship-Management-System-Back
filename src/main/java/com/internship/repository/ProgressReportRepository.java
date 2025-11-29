package com.internship.repository;

import com.internship.entity.ProgressReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for ProgressReport entity
 * 
 * @author Internship Management System
 */
@Repository
public interface ProgressReportRepository extends JpaRepository<ProgressReport, Long> {
    
    Page<ProgressReport> findByInternshipId(Long internshipId, Pageable pageable);
    
    List<ProgressReport> findByInternshipIdOrderByWeekNumberDesc(Long internshipId);
}

