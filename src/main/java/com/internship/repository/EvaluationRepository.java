package com.internship.repository;

import com.internship.entity.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Evaluation entity
 * 
 * @author Internship Management System
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    
    Page<Evaluation> findByInternshipId(Long internshipId, Pageable pageable);
    
    List<Evaluation> findByInternshipIdOrderByCreatedAtDesc(Long internshipId);
}

