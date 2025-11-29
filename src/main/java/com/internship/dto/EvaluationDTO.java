package com.internship.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for Evaluation entity
 * 
 * @author Internship Management System
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {

    private Long id;

    @NotNull(message = "Internship ID is required")
    private Long internshipId;

    @NotNull(message = "Technical score is required")
    @Min(value = 0, message = "Technical score must be between 0 and 100")
    @Max(value = 100, message = "Technical score must be between 0 and 100")
    private Integer technicalScore;

    @NotNull(message = "Soft skills score is required")
    @Min(value = 0, message = "Soft skills score must be between 0 and 100")
    @Max(value = 100, message = "Soft skills score must be between 0 and 100")
    private Integer softSkillsScore;

    private String comments;

    private LocalDateTime createdAt;
}

