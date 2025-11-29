package com.internship.repository;

import com.internship.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Student entity
 * 
 * @author Internship Management System
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByEmail(String email);
    
    Page<Student> findByUniversity(String university, Pageable pageable);
    
    boolean existsByEmail(String email);
}

