package com.astrid.diaspora.service;

import com.astrid.diaspora.service.dto.EntityCreationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.astrid.diaspora.domain.EntityCreation}.
 */
public interface EntityCreationService {

    /**
     * Save a entityCreation.
     *
     * @param entityCreationDTO the entity to save.
     * @return the persisted entity.
     */
    EntityCreationDTO save(EntityCreationDTO entityCreationDTO);

    /**
     * Get all the entityCreations.
     *
     * @return the list of entities.
     */
    List<EntityCreationDTO> findAll();
    /**
     * Get all the EntityCreationDTO where AstridProject is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<EntityCreationDTO> findAllWhereAstridProjectIsNull();


    /**
     * Get the "id" entityCreation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityCreationDTO> findOne(Long id);

    /**
     * Delete the "id" entityCreation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
