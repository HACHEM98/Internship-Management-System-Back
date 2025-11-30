package com.internship.mapper;

import com.internship.dto.ProgressReportDTO;
import com.internship.entity.ProgressReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for ProgressReport entity and DTO conversion
 * 
 * @author Internship Management System
 */
@Mapper(componentModel = "spring")
public interface ProgressReportMapper {

    @Mapping(target = "internshipId", source = "internship.id")
    ProgressReportDTO toDTO(ProgressReport entity);

    @Mapping(target = "internship", ignore = true)
    ProgressReport toEntity(ProgressReportDTO dto);

    List<ProgressReportDTO> toDTOList(List<ProgressReport> entities);
}

