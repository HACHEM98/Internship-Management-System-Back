package com.internship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing an Internship
 * 
 * @author Internship Management System
 */
@Entity
@Table(name = "internships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "supervisor_name", nullable = false)
    private String supervisorName;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InternshipStatus status = InternshipStatus.ACTIVE;

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgressReport> progressReports = new ArrayList<>();

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluation> evaluations = new ArrayList<>();

    /**
     * Enum for Internship Status
     */
    public enum InternshipStatus {
        ACTIVE, COMPLETED, CANCELLED, ON_HOLD
    }
}

