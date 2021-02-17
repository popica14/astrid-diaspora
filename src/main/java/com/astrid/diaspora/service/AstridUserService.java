package com.astrid.diaspora.service;

import com.astrid.diaspora.service.dto.AstridUserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.astrid.diaspora.domain.AstridUser}.
 */
public interface AstridUserService {

    /**
     * Save a astridUser.
     *
     * @param astridUserDTO the entity to save.
     * @return the persisted entity.
     */
    AstridUserDTO save(AstridUserDTO astridUserDTO);

    /**
     * Get all the astridUsers.
     *
     * @return the list of entities.
     */
    List<AstridUserDTO> findAll();


    /**
     * Get the "id" astridUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AstridUserDTO> findOne(Long id);

    /**
     * Get the "id" astridUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AstridUserDTO> findOneByUserId(Long id);

    /**
     * Delete the "id" astridUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
