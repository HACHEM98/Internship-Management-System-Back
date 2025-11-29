package com.internship.mapper;

import com.internship.dto.SupervisorDTO;
import com.internship.entity.Supervisor;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for Supervisor entity and DTO conversion
 * 
 * @author Internship Management System
 */
@Mapper(componentModel = "spring")
public interface SupervisorMapper {

    SupervisorDTO toDTO(Supervisor entity);

    Supervisor toEntity(SupervisorDTO dto);

    List<SupervisorDTO> toDTOList(List<Supervisor> entities);
}

