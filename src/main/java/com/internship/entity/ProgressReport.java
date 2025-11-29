package com.internship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity class representing a Progress Report
 * 
 * @author Internship Management System
 */
@Entity
@Table(name = "progress_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    @Column(name = "week_number", nullable = false)
    private Integer weekNumber;

    @Column(name = "tasks_completed", columnDefinition = "TEXT")
    private String tasksCompleted;

    @Column(name = "challenges", columnDefinition = "TEXT")
    private String challenges;

    @Column(name = "next_week_plan", columnDefinition = "TEXT")
    private String nextWeekPlan;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
}

