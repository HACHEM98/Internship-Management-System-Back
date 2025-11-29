package com.internship.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for ProgressReport entity
 * 
 * @author Internship Management System
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressReportDTO {

    private Long id;

    @NotNull(message = "Internship ID is required")
    private Long internshipId;

    @NotNull(message = "Week number is required")
    private Integer weekNumber;

    private String tasksCompleted;

    private String challenges;

    private String nextWeekPlan;

    private LocalDateTime submittedAt;
}

