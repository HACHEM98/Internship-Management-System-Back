package com.internship.repository;

import com.internship.entity.Internship;
import com.internship.entity.Internship.InternshipStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Internship entity
 * 
 * @author Internship Management System
 */
@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    
    Page<Internship> findByStudentId(Long studentId, Pageable pageable);
    
    List<Internship> findByStatus(InternshipStatus status);
    
    Page<Internship> findByCompanyNameContainingIgnoreCase(String companyName, Pageable pageable);
}

