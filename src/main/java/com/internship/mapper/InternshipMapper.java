package com.internship.mapper;

import com.internship.dto.InternshipDTO;
import com.internship.entity.Internship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for Internship entity and DTO conversion
 * 
 * @author Internship Management System
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface InternshipMapper {

    @Mapping(target = "studentId", source = "student.id")
    InternshipDTO toDTO(Internship entity);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "progressReports", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    Internship toEntity(InternshipDTO dto);

    List<InternshipDTO> toDTOList(List<Internship> entities);
}

