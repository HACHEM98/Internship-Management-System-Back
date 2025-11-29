package com.internship.service.impl;

import com.internship.dto.InternshipDTO;
import com.internship.entity.Internship;
import com.internship.entity.Student;
import com.internship.exception.ResourceNotFoundException;
import com.internship.mapper.InternshipMapper;
import com.internship.repository.InternshipRepository;
import com.internship.repository.StudentRepository;
import com.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for Internship operations
 * 
 * @author Internship Management System
 */
@Service
@RequiredArgsConstructor
@Transactional
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final StudentRepository studentRepository;
    private final InternshipMapper internshipMapper;

    @Override
    public InternshipDTO createInternship(InternshipDTO internshipDTO) {
        Student student = studentRepository.findById(internshipDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", internshipDTO.getStudentId()));
        
        Internship internship = internshipMapper.toEntity(internshipDTO);
        internship.setStudent(student);
        
        if (internship.getStatus() == null) {
            internship.setStatus(Internship.InternshipStatus.ACTIVE);
        }
        
        Internship savedInternship = internshipRepository.save(internship);
        return internshipMapper.toDTO(savedInternship);
    }

    @Override
    @Transactional(readOnly = true)
    public InternshipDTO getInternshipById(Long id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", id));
        return internshipMapper.toDTO(internship);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InternshipDTO> getAllInternships(Pageable pageable) {
        return internshipRepository.findAll(pageable)
                .map(internshipMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InternshipDTO> getInternshipsByStudentId(Long studentId, Pageable pageable) {
        return internshipRepository.findByStudentId(studentId, pageable)
                .map(internshipMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InternshipDTO> getInternshipsByCompany(String companyName, Pageable pageable) {
        return internshipRepository.findByCompanyNameContainingIgnoreCase(companyName, pageable)
                .map(internshipMapper::toDTO);
    }

    @Override
    public InternshipDTO updateInternship(Long id, InternshipDTO internshipDTO) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", id));
        
        // Update student if changed
        if (!internship.getStudent().getId().equals(internshipDTO.getStudentId())) {
            Student student = studentRepository.findById(internshipDTO.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student", "id", internshipDTO.getStudentId()));
            internship.setStudent(student);
        }
        
        internship.setCompanyName(internshipDTO.getCompanyName());
        internship.setSupervisorName(internshipDTO.getSupervisorName());
        internship.setStartDate(internshipDTO.getStartDate());
        internship.setEndDate(internshipDTO.getEndDate());
        if (internshipDTO.getStatus() != null) {
            internship.setStatus(internshipDTO.getStatus());
        }
        
        Internship updatedInternship = internshipRepository.save(internship);
        return internshipMapper.toDTO(updatedInternship);
    }

    @Override
    public void deleteInternship(Long id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", id));
        internshipRepository.delete(internship);
    }
}

