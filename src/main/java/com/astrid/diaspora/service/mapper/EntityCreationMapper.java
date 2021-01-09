package com.astrid.diaspora.service.mapper;


import com.astrid.diaspora.domain.*;
import com.astrid.diaspora.service.dto.EntityCreationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntityCreation} and its DTO {@link EntityCreationDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EntityCreationMapper extends EntityMapper<EntityCreationDTO, EntityCreation> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.login", target = "createdByLogin")
    EntityCreationDTO toDto(EntityCreation entityCreation);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(target = "astridProject", ignore = true)
    EntityCreation toEntity(EntityCreationDTO entityCreationDTO);

    default EntityCreation fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityCreation entityCreation = new EntityCreation();
        entityCreation.setId(id);
        return entityCreation;
    }
}
