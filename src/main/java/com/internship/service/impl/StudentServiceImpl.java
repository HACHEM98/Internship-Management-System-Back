package com.internship.service.impl;

import com.internship.dto.StudentDTO;
import com.internship.entity.Student;
import com.internship.exception.DuplicateResourceException;
import com.internship.exception.ResourceNotFoundException;
import com.internship.mapper.StudentMapper;
import com.internship.repository.StudentRepository;
import com.internship.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for Student operations
 * 
 * @author Internship Management System
 */
@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new DuplicateResourceException("Student with email " + studentDTO.getEmail() + " already exists");
        }
        
        Student student = studentMapper.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return studentMapper.toDTO(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(studentMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentDTO> getStudentsByUniversity(String university, Pageable pageable) {
        return studentRepository.findByUniversity(university, pageable)
                .map(studentMapper::toDTO);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        
        // Check if email is being changed and if new email already exists
        if (!student.getEmail().equals(studentDTO.getEmail()) && 
            studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new DuplicateResourceException("Student with email " + studentDTO.getEmail() + " already exists");
        }
        
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setUniversity(studentDTO.getUniversity());
        student.setStartDate(studentDTO.getStartDate());
        student.setEndDate(studentDTO.getEndDate());
        
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        studentRepository.delete(student);
    }
}

