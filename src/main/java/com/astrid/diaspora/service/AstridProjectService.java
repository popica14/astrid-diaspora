package com.astrid.diaspora.service;

import com.astrid.diaspora.service.dto.AstridProjectDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.astrid.diaspora.domain.AstridProject}.
 */
public interface AstridProjectService {

    /**
     * Save a astridProject.
     *
     * @param astridProjectDTO the entity to save.
     * @return the persisted entity.
     */
    AstridProjectDTO save(AstridProjectDTO astridProjectDTO);

    /**
     * Get all the astridProjects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AstridProjectDTO> findAll(Pageable pageable);

    /**
     * Get all the astridProjects with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<AstridProjectDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" astridProject.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AstridProjectDTO> findOne(Long id);

    /**
     * Delete the "id" astridProject.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
