package com.internship.controller;

import com.internship.dto.ApiResponse;
import com.internship.dto.StudentDTO;
import com.internship.service.StudentService;
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
 * REST Controller for Student operations
 * 
 * @author Internship Management System
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "API for managing students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(ApiResponse.success("Student created successfully", createdStudent), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(ApiResponse.success(student));
    }

    @GetMapping
    @Operation(summary = "Get all students with pagination")
    public ResponseEntity<ApiResponse<Page<StudentDTO>>> getAllStudents(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<StudentDTO> students = studentService.getAllStudents(pageable);
        return ResponseEntity.ok(ApiResponse.success(students));
    }

    @GetMapping("/university/{university}")
    @Operation(summary = "Get students by university")
    public ResponseEntity<ApiResponse<Page<StudentDTO>>> getStudentsByUniversity(
            @PathVariable String university,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<StudentDTO> students = studentService.getStudentsByUniversity(university, pageable);
        return ResponseEntity.ok(ApiResponse.success(students));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(ApiResponse.success("Student updated successfully", updatedStudent));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.success("Student deleted successfully", null));
    }
}

