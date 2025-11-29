package com.internship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Supervisor
 * 
 * @author Internship Management System
 */
@Entity
@Table(name = "supervisors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "company", nullable = false)
    private String company;
}

