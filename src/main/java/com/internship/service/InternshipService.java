package com.internship.service;

import com.internship.dto.InternshipDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for Internship operations
 * 
 * @author Internship Management System
 */
public interface InternshipService {
    
    InternshipDTO createInternship(InternshipDTO internshipDTO);
    
    InternshipDTO getInternshipById(Long id);
    
    Page<InternshipDTO> getAllInternships(Pageable pageable);
    
    Page<InternshipDTO> getInternshipsByStudentId(Long studentId, Pageable pageable);
    
    Page<InternshipDTO> getInternshipsByCompany(String companyName, Pageable pageable);
    
    InternshipDTO updateInternship(Long id, InternshipDTO internshipDTO);
    
    void deleteInternship(Long id);
}

