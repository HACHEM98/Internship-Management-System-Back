package com.internship.controller;

import com.internship.dto.ApiResponse;
import com.internship.dto.SupervisorDTO;
import com.internship.service.SupervisorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Supervisor operations
 * 
 * @author Internship Management System
 */
@RestController
@RequestMapping("/api/supervisors")
@RequiredArgsConstructor
@Tag(name = "Supervisors", description = "API for managing supervisors")
public class SupervisorController {

    private final SupervisorService supervisorService;

    @PostMapping
    @Operation(summary = "Create a new supervisor")
    public ResponseEntity<ApiResponse<SupervisorDTO>> createSupervisor(@Valid @RequestBody SupervisorDTO supervisorDTO) {
        SupervisorDTO createdSupervisor = supervisorService.createSupervisor(supervisorDTO);
        return new ResponseEntity<>(ApiResponse.success("Supervisor created successfully", createdSupervisor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supervisor by ID")
    public ResponseEntity<ApiResponse<SupervisorDTO>> getSupervisorById(@PathVariable Long id) {
        SupervisorDTO supervisor = supervisorService.getSupervisorById(id);
        return ResponseEntity.ok(ApiResponse.success(supervisor));
    }

    @GetMapping
    @Operation(summary = "Get all supervisors with pagination")
    public ResponseEntity<ApiResponse<Page<SupervisorDTO>>> getAllSupervisors(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<SupervisorDTO> supervisors = supervisorService.getAllSupervisors(pageable);
        return ResponseEntity.ok(ApiResponse.success(supervisors));
    }

    @GetMapping("/company/{company}")
    @Operation(summary = "Get supervisors by company")
    public ResponseEntity<ApiResponse<Page<SupervisorDTO>>> getSupervisorsByCompany(
            @PathVariable String company,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<SupervisorDTO> supervisors = supervisorService.getSupervisorsByCompany(company, pageable);
        return ResponseEntity.ok(ApiResponse.success(supervisors));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update supervisor")
    public ResponseEntity<ApiResponse<SupervisorDTO>> updateSupervisor(
            @PathVariable Long id,
            @Valid @RequestBody SupervisorDTO supervisorDTO) {
        SupervisorDTO updatedSupervisor = supervisorService.updateSupervisor(id, supervisorDTO);
        return ResponseEntity.ok(ApiResponse.success("Supervisor updated successfully", updatedSupervisor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete supervisor")
    public ResponseEntity<ApiResponse<Void>> deleteSupervisor(@PathVariable Long id) {
        supervisorService.deleteSupervisor(id);
        return ResponseEntity.ok(ApiResponse.success("Supervisor deleted successfully", null));
    }
}

