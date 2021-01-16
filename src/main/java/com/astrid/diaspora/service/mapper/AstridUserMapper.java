package com.astrid.diaspora.service.mapper;


import com.astrid.diaspora.domain.*;
import com.astrid.diaspora.service.dto.AstridUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AstridUser} and its DTO {@link AstridUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AstridUserMapper extends EntityMapper<AstridUserDTO, AstridUser> {

    @Mapping(source = "user.id", target = "userId")
    AstridUserDTO toDto(AstridUser astridUser);

    @Mapping(source = "userId", target = "user")
    AstridUser toEntity(AstridUserDTO astridUserDTO);

    default AstridUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        AstridUser astridUser = new AstridUser();
        astridUser.setId(id);
        return astridUser;
    }
}
