package com.internship.repository;

import com.internship.entity.Supervisor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Supervisor entity
 * 
 * @author Internship Management System
 */
@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    
    Optional<Supervisor> findByEmail(String email);
    
    Page<Supervisor> findByCompany(String company, Pageable pageable);
    
    boolean existsByEmail(String email);
}

