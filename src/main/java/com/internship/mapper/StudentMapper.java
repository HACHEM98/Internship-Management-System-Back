package com.internship.mapper;

import com.internship.dto.StudentDTO;
import com.internship.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for Student entity and DTO conversion
 * 
 * @author Internship Management System
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "internships", ignore = true)
    Student toEntity(StudentDTO dto);

    StudentDTO toDTO(Student entity);

    List<StudentDTO> toDTOList(List<Student> entities);
}

