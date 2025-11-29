package com.internship.service;

import com.internship.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for Student operations
 * 
 * @author Internship Management System
 */
public interface StudentService {
    
    StudentDTO createStudent(StudentDTO studentDTO);
    
    StudentDTO getStudentById(Long id);
    
    Page<StudentDTO> getAllStudents(Pageable pageable);
    
    Page<StudentDTO> getStudentsByUniversity(String university, Pageable pageable);
    
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    
    void deleteStudent(Long id);
}

