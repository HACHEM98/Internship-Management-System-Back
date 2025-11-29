package com.internship.dto;

import com.internship.entity.Internship.InternshipStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Internship entity
 * 
 * @author Internship Management System
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipDTO {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Supervisor name is required")
    private String supervisorName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    private InternshipStatus status;
}

