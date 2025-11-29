package com.internship.service;

import com.internship.dto.SupervisorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for Supervisor operations
 * 
 * @author Internship Management System
 */
public interface SupervisorService {
    
    SupervisorDTO createSupervisor(SupervisorDTO supervisorDTO);
    
    SupervisorDTO getSupervisorById(Long id);
    
    Page<SupervisorDTO> getAllSupervisors(Pageable pageable);
    
    Page<SupervisorDTO> getSupervisorsByCompany(String company, Pageable pageable);
    
    SupervisorDTO updateSupervisor(Long id, SupervisorDTO supervisorDTO);
    
    void deleteSupervisor(Long id);
}

