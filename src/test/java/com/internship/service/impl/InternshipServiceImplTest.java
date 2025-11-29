package com.internship.service.impl;

import com.internship.dto.InternshipDTO;
import com.internship.entity.Internship;
import com.internship.entity.Student;
import com.internship.exception.ResourceNotFoundException;
import com.internship.mapper.InternshipMapper;
import com.internship.repository.InternshipRepository;
import com.internship.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for InternshipServiceImpl
 * 
 * @author Internship Management System
 */
@ExtendWith(MockitoExtension.class)
class InternshipServiceImplTest {

    @Mock
    private InternshipRepository internshipRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private InternshipMapper internshipMapper;

    @InjectMocks
    private InternshipServiceImpl internshipService;

    private Student student;
    private Internship internship;
    private InternshipDTO internshipDTO;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");

        internship = new Internship();
        internship.setId(1L);
        internship.setStudent(student);
        internship.setCompanyName("Tech Corp");
        internship.setSupervisorName("Jane Supervisor");
        internship.setStartDate(LocalDate.of(2024, 1, 1));
        internship.setStatus(Internship.InternshipStatus.ACTIVE);

        internshipDTO = new InternshipDTO();
        internshipDTO.setId(1L);
        internshipDTO.setStudentId(1L);
        internshipDTO.setCompanyName("Tech Corp");
        internshipDTO.setSupervisorName("Jane Supervisor");
        internshipDTO.setStartDate(LocalDate.of(2024, 1, 1));
    }

    @Test
    void testCreateInternship_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(internshipMapper.toEntity(internshipDTO)).thenReturn(internship);
        when(internshipRepository.save(any(Internship.class))).thenReturn(internship);
        when(internshipMapper.toDTO(internship)).thenReturn(internshipDTO);

        InternshipDTO result = internshipService.createInternship(internshipDTO);

        assertNotNull(result);
        verify(internshipRepository, times(1)).save(any(Internship.class));
    }

    @Test
    void testCreateInternship_StudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            internshipService.createInternship(internshipDTO);
        });

        verify(internshipRepository, never()).save(any(Internship.class));
    }

    @Test
    void testGetInternshipById_Success() {
        when(internshipRepository.findById(1L)).thenReturn(Optional.of(internship));
        when(internshipMapper.toDTO(internship)).thenReturn(internshipDTO);

        InternshipDTO result = internshipService.getInternshipById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetInternshipById_NotFound() {
        when(internshipRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            internshipService.getInternshipById(1L);
        });
    }

    @Test
    void testDeleteInternship_Success() {
        when(internshipRepository.findById(1L)).thenReturn(Optional.of(internship));
        doNothing().when(internshipRepository).delete(internship);

        internshipService.deleteInternship(1L);

        verify(internshipRepository, times(1)).delete(internship);
    }
}

