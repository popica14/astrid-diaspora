package com.astrid.diaspora.service.mapper;


import com.astrid.diaspora.domain.*;
import com.astrid.diaspora.service.dto.ProjectStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProjectStatus} and its DTO {@link ProjectStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectStatusMapper extends EntityMapper<ProjectStatusDTO, ProjectStatus> {



    default ProjectStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setId(id);
        return projectStatus;
    }
}
