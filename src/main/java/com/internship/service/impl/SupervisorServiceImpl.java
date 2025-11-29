package com.internship.service.impl;

import com.internship.dto.SupervisorDTO;
import com.internship.entity.Supervisor;
import com.internship.exception.DuplicateResourceException;
import com.internship.exception.ResourceNotFoundException;
import com.internship.mapper.SupervisorMapper;
import com.internship.repository.SupervisorRepository;
import com.internship.service.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for Supervisor operations
 * 
 * @author Internship Management System
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;
    private final SupervisorMapper supervisorMapper;

    @Override
    public SupervisorDTO createSupervisor(SupervisorDTO supervisorDTO) {
        if (supervisorRepository.existsByEmail(supervisorDTO.getEmail())) {
            throw new DuplicateResourceException("Supervisor with email " + supervisorDTO.getEmail() + " already exists");
        }
        
        Supervisor supervisor = supervisorMapper.toEntity(supervisorDTO);
        Supervisor savedSupervisor = supervisorRepository.save(supervisor);
        return supervisorMapper.toDTO(savedSupervisor);
    }

    @Override
    @Transactional(readOnly = true)
    public SupervisorDTO getSupervisorById(Long id) {
        Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));
        return supervisorMapper.toDTO(supervisor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SupervisorDTO> getAllSupervisors(Pageable pageable) {
        return supervisorRepository.findAll(pageable)
                .map(supervisorMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SupervisorDTO> getSupervisorsByCompany(String company, Pageable pageable) {
        return supervisorRepository.findByCompany(company, pageable)
                .map(supervisorMapper::toDTO);
    }

    @Override
    public SupervisorDTO updateSupervisor(Long id, SupervisorDTO supervisorDTO) {
        Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));
        
        // Check if email is being changed and if new email already exists
        if (!supervisor.getEmail().equals(supervisorDTO.getEmail()) && 
            supervisorRepository.existsByEmail(supervisorDTO.getEmail())) {
            throw new DuplicateResourceException("Supervisor with email " + supervisorDTO.getEmail() + " already exists");
        }
        
        supervisor.setName(supervisorDTO.getName());
        supervisor.setEmail(supervisorDTO.getEmail());
        supervisor.setCompany(supervisorDTO.getCompany());
        
        Supervisor updatedSupervisor = supervisorRepository.save(supervisor);
        return supervisorMapper.toDTO(updatedSupervisor);
    }

    @Override
    public void deleteSupervisor(Long id) {
        Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));
        supervisorRepository.delete(supervisor);
    }
}

