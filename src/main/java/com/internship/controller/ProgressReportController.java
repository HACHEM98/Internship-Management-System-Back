package com.internship.controller;

import com.internship.dto.ApiResponse;
import com.internship.dto.ProgressReportDTO;
import com.internship.service.ProgressReportService;
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
 * REST Controller for ProgressReport operations
 * 
 * @author Internship Management System
 */
@RestController
@RequestMapping("/api/progress-reports")
@RequiredArgsConstructor
@Tag(name = "Progress Reports", description = "API for managing progress reports")
public class ProgressReportController {

    private final ProgressReportService progressReportService;

    @PostMapping
    @Operation(summary = "Create a new progress report")
    public ResponseEntity<ApiResponse<ProgressReportDTO>> createProgressReport(
            @Valid @RequestBody ProgressReportDTO progressReportDTO) {
        ProgressReportDTO createdReport = progressReportService.createProgressReport(progressReportDTO);
        return new ResponseEntity<>(ApiResponse.success("Progress report created successfully", createdReport), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get progress report by ID")
    public ResponseEntity<ApiResponse<ProgressReportDTO>> getProgressReportById(@PathVariable Long id) {
        ProgressReportDTO report = progressReportService.getProgressReportById(id);
        return ResponseEntity.ok(ApiResponse.success(report));
    }

    @GetMapping
    @Operation(summary = "Get all progress reports with pagination")
    public ResponseEntity<ApiResponse<Page<ProgressReportDTO>>> getAllProgressReports(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ProgressReportDTO> reports = progressReportService.getAllProgressReports(pageable);
        return ResponseEntity.ok(ApiResponse.success(reports));
    }

    @GetMapping("/internship/{internshipId}")
    @Operation(summary = "Get progress reports by internship ID")
    public ResponseEntity<ApiResponse<Page<ProgressReportDTO>>> getProgressReportsByInternshipId(
            @PathVariable Long internshipId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ProgressReportDTO> reports = progressReportService.getProgressReportsByInternshipId(internshipId, pageable);
        return ResponseEntity.ok(ApiResponse.success(reports));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update progress report")
    public ResponseEntity<ApiResponse<ProgressReportDTO>> updateProgressReport(
            @PathVariable Long id,
            @Valid @RequestBody ProgressReportDTO progressReportDTO) {
        ProgressReportDTO updatedReport = progressReportService.updateProgressReport(id, progressReportDTO);
        return ResponseEntity.ok(ApiResponse.success("Progress report updated successfully", updatedReport));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete progress report")
    public ResponseEntity<ApiResponse<Void>> deleteProgressReport(@PathVariable Long id) {
        progressReportService.deleteProgressReport(id);
        return ResponseEntity.ok(ApiResponse.success("Progress report deleted successfully", null));
    }
}

