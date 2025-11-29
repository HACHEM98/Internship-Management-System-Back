package com.internship.controller;

import com.internship.dto.ApiResponse;
import com.internship.dto.InternshipDTO;
import com.internship.service.InternshipService;
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
 * REST Controller for Internship operations
 * 
 * @author Internship Management System
 */
@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
@Tag(name = "Internships", description = "API for managing internships")
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping
    @Operation(summary = "Create a new internship")
    public ResponseEntity<ApiResponse<InternshipDTO>> createInternship(@Valid @RequestBody InternshipDTO internshipDTO) {
        InternshipDTO createdInternship = internshipService.createInternship(internshipDTO);
        return new ResponseEntity<>(ApiResponse.success("Internship created successfully", createdInternship), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get internship by ID")
    public ResponseEntity<ApiResponse<InternshipDTO>> getInternshipById(@PathVariable Long id) {
        InternshipDTO internship = internshipService.getInternshipById(id);
        return ResponseEntity.ok(ApiResponse.success(internship));
    }

    @GetMapping
    @Operation(summary = "Get all internships with pagination")
    public ResponseEntity<ApiResponse<Page<InternshipDTO>>> getAllInternships(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<InternshipDTO> internships = internshipService.getAllInternships(pageable);
        return ResponseEntity.ok(ApiResponse.success(internships));
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get internships by student ID")
    public ResponseEntity<ApiResponse<Page<InternshipDTO>>> getInternshipsByStudentId(
            @PathVariable Long studentId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<InternshipDTO> internships = internshipService.getInternshipsByStudentId(studentId, pageable);
        return ResponseEntity.ok(ApiResponse.success(internships));
    }

    @GetMapping("/company")
    @Operation(summary = "Get internships by company name")
    public ResponseEntity<ApiResponse<Page<InternshipDTO>>> getInternshipsByCompany(
            @RequestParam String companyName,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<InternshipDTO> internships = internshipService.getInternshipsByCompany(companyName, pageable);
        return ResponseEntity.ok(ApiResponse.success(internships));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update internship")
    public ResponseEntity<ApiResponse<InternshipDTO>> updateInternship(
            @PathVariable Long id,
            @Valid @RequestBody InternshipDTO internshipDTO) {
        InternshipDTO updatedInternship = internshipService.updateInternship(id, internshipDTO);
        return ResponseEntity.ok(ApiResponse.success("Internship updated successfully", updatedInternship));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete internship")
    public ResponseEntity<ApiResponse<Void>> deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.ok(ApiResponse.success("Internship deleted successfully", null));
    }
}

