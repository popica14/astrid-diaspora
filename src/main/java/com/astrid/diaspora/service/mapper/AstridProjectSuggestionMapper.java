package com.astrid.diaspora.service.mapper;


import com.astrid.diaspora.domain.*;
import com.astrid.diaspora.service.dto.AstridProjectSuggestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AstridProjectSuggestion} and its DTO {@link AstridProjectSuggestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ProjectStatusMapper.class})
public interface AstridProjectSuggestionMapper extends EntityMapper<AstridProjectSuggestionDTO, AstridProjectSuggestion> {

    @Mapping(source = "initiator.id", target = "initiatorId")
    @Mapping(source = "initiator.login", target = "initiatorLogin")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "status.name", target = "statusName")
    AstridProjectSuggestionDTO toDto(AstridProjectSuggestion astridProjectSuggestion);

    @Mapping(source = "initiatorId", target = "initiator")
    @Mapping(source = "statusId", target = "status")
    AstridProjectSuggestion toEntity(AstridProjectSuggestionDTO astridProjectSuggestionDTO);

    default AstridProjectSuggestion fromId(Long id) {
        if (id == null) {
            return null;
        }
        AstridProjectSuggestion astridProjectSuggestion = new AstridProjectSuggestion();
        astridProjectSuggestion.setId(id);
        return astridProjectSuggestion;
    }
}
