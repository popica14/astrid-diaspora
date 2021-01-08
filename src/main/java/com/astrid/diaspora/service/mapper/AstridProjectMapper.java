package com.astrid.diaspora.service.mapper;


import com.astrid.diaspora.domain.*;
import com.astrid.diaspora.service.dto.AstridProjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AstridProject} and its DTO {@link AstridProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AstridProjectMapper extends EntityMapper<AstridProjectDTO, AstridProject> {

    @Mapping(source = "responsible.id", target = "responsibleId")
    @Mapping(source = "responsible.login", target = "responsibleLogin")
    AstridProjectDTO toDto(AstridProject astridProject);

    @Mapping(source = "responsibleId", target = "responsible")
    @Mapping(target = "removeImplementationTeam", ignore = true)
    AstridProject toEntity(AstridProjectDTO astridProjectDTO);

    default AstridProject fromId(Long id) {
        if (id == null) {
            return null;
        }
        AstridProject astridProject = new AstridProject();
        astridProject.setId(id);
        return astridProject;
    }
}
