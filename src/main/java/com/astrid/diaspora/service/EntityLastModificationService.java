package com.astrid.diaspora.service;

import com.astrid.diaspora.service.dto.EntityLastModificationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.astrid.diaspora.domain.EntityLastModification}.
 */
public interface EntityLastModificationService {

    /**
     * Save a entityLastModification.
     *
     * @param entityLastModificationDTO the entity to save.
     * @return the persisted entity.
     */
    EntityLastModificationDTO save(EntityLastModificationDTO entityLastModificationDTO);

    /**
     * Get all the entityLastModifications.
     *
     * @return the list of entities.
     */
    List<EntityLastModificationDTO> findAll();
    /**
     * Get all the EntityLastModificationDTO where AstridProject is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<EntityLastModificationDTO> findAllWhereAstridProjectIsNull();


    /**
     * Get the "id" entityLastModification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityLastModificationDTO> findOne(Long id);

    /**
     * Delete the "id" entityLastModification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
