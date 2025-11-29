package com.internship.service.impl;

import com.internship.dto.StudentDTO;
import com.internship.entity.Student;
import com.internship.exception.DuplicateResourceException;
import com.internship.exception.ResourceNotFoundException;
import com.internship.mapper.StudentMapper;
import com.internship.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for StudentServiceImpl
 * 
 * @author Internship Management System
 */
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@university.edu");
        student.setUniversity("Test University");
        student.setStartDate(LocalDate.of(2024, 1, 1));
        student.setEndDate(LocalDate.of(2024, 12, 31));

        studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setEmail("john.doe@university.edu");
        studentDTO.setUniversity("Test University");
        studentDTO.setStartDate(LocalDate.of(2024, 1, 1));
        studentDTO.setEndDate(LocalDate.of(2024, 12, 31));
    }

    @Test
    void testCreateStudent_Success() {
        when(studentRepository.existsByEmail(studentDTO.getEmail())).thenReturn(false);
        when(studentMapper.toEntity(studentDTO)).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.createStudent(studentDTO);

        assertNotNull(result);
        assertEquals(studentDTO.getEmail(), result.getEmail());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testCreateStudent_DuplicateEmail() {
        when(studentRepository.existsByEmail(studentDTO.getEmail())).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> {
            studentService.createStudent(studentDTO);
        });

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void testGetStudentById_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudentById(1L);
        });
    }

    @Test
    void testGetAllStudents() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Student> students = Arrays.asList(student);
        Page<Student> studentPage = new PageImpl<>(students, pageable, 1);

        when(studentRepository.findAll(pageable)).thenReturn(studentPage);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        Page<StudentDTO> result = studentService.getAllStudents(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(studentRepository, times(1)).findAll(pageable);
    }

    @Test
    void testUpdateStudent_Success() {
        StudentDTO updatedDTO = new StudentDTO();
        updatedDTO.setFirstName("Jane");
        updatedDTO.setLastName("Smith");
        updatedDTO.setEmail("john.doe@university.edu");
        updatedDTO.setUniversity("Test University");
        updatedDTO.setStartDate(LocalDate.of(2024, 1, 1));

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.existsByEmail("john.doe@university.edu")).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(updatedDTO);

        StudentDTO result = studentService.updateStudent(1L, updatedDTO);

        assertNotNull(result);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testDeleteStudent_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).delete(student);

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void testDeleteStudent_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.deleteStudent(1L);
        });
    }
}

