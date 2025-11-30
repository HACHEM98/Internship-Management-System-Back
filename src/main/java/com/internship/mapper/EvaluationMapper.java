package com.internship.mapper;

import com.internship.dto.EvaluationDTO;
import com.internship.entity.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for Evaluation entity and DTO conversion
 * 
 * @author Internship Management System
 */
@Mapper(componentModel = "spring")
public interface EvaluationMapper {

    @Mapping(target = "internshipId", source = "internship.id")
    EvaluationDTO toDTO(Evaluation entity);

    @Mapping(target = "internship", ignore = true)
    Evaluation toEntity(EvaluationDTO dto);

    List<EvaluationDTO> toDTOList(List<Evaluation> entities);
}

