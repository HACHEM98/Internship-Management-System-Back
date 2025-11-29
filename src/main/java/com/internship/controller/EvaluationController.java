package com.internship.controller;

import com.internship.dto.ApiResponse;
import com.internship.dto.EvaluationDTO;
import com.internship.service.EvaluationService;
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
 * REST Controller for Evaluation operations
 * 
 * @author Internship Management System
 */
@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
@Tag(name = "Evaluations", description = "API for managing evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping
    @Operation(summary = "Create a new evaluation")
    public ResponseEntity<ApiResponse<EvaluationDTO>> createEvaluation(@Valid @RequestBody EvaluationDTO evaluationDTO) {
        EvaluationDTO createdEvaluation = evaluationService.createEvaluation(evaluationDTO);
        return new ResponseEntity<>(ApiResponse.success("Evaluation created successfully", createdEvaluation), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get evaluation by ID")
    public ResponseEntity<ApiResponse<EvaluationDTO>> getEvaluationById(@PathVariable Long id) {
        EvaluationDTO evaluation = evaluationService.getEvaluationById(id);
        return ResponseEntity.ok(ApiResponse.success(evaluation));
    }

    @GetMapping
    @Operation(summary = "Get all evaluations with pagination")
    public ResponseEntity<ApiResponse<Page<EvaluationDTO>>> getAllEvaluations(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<EvaluationDTO> evaluations = evaluationService.getAllEvaluations(pageable);
        return ResponseEntity.ok(ApiResponse.success(evaluations));
    }

    @GetMapping("/internship/{internshipId}")
    @Operation(summary = "Get evaluations by internship ID")
    public ResponseEntity<ApiResponse<Page<EvaluationDTO>>> getEvaluationsByInternshipId(
            @PathVariable Long internshipId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<EvaluationDTO> evaluations = evaluationService.getEvaluationsByInternshipId(internshipId, pageable);
        return ResponseEntity.ok(ApiResponse.success(evaluations));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update evaluation")
    public ResponseEntity<ApiResponse<EvaluationDTO>> updateEvaluation(
            @PathVariable Long id,
            @Valid @RequestBody EvaluationDTO evaluationDTO) {
        EvaluationDTO updatedEvaluation = evaluationService.updateEvaluation(id, evaluationDTO);
        return ResponseEntity.ok(ApiResponse.success("Evaluation updated successfully", updatedEvaluation));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete evaluation")
    public ResponseEntity<ApiResponse<Void>> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.ok(ApiResponse.success("Evaluation deleted successfully", null));
    }
}

