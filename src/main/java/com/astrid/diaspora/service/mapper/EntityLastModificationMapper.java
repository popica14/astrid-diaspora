package com.astrid.diaspora.service.mapper;


import com.astrid.diaspora.domain.*;
import com.astrid.diaspora.service.dto.EntityLastModificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntityLastModification} and its DTO {@link EntityLastModificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EntityLastModificationMapper extends EntityMapper<EntityLastModificationDTO, EntityLastModification> {

    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedById")
    @Mapping(source = "lastModifiedBy.login", target = "lastModifiedByLogin")
    EntityLastModificationDTO toDto(EntityLastModification entityLastModification);

    @Mapping(source = "lastModifiedById", target = "lastModifiedBy")
    @Mapping(target = "astridProject", ignore = true)
    EntityLastModification toEntity(EntityLastModificationDTO entityLastModificationDTO);

    default EntityLastModification fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityLastModification entityLastModification = new EntityLastModification();
        entityLastModification.setId(id);
        return entityLastModification;
    }
}
