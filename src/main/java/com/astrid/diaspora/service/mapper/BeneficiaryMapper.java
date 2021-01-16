package com.astrid.diaspora.service.mapper;


import com.astrid.diaspora.domain.*;
import com.astrid.diaspora.service.dto.BeneficiaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Beneficiary} and its DTO {@link BeneficiaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BeneficiaryMapper extends EntityMapper<BeneficiaryDTO, Beneficiary> {


    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "removeProjects", ignore = true)
    Beneficiary toEntity(BeneficiaryDTO beneficiaryDTO);

    default Beneficiary fromId(Long id) {
        if (id == null) {
            return null;
        }
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(id);
        return beneficiary;
    }
}
