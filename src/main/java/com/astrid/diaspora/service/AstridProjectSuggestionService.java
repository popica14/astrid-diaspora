package com.astrid.diaspora.service;

import com.astrid.diaspora.service.dto.AstridProjectSuggestionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.astrid.diaspora.domain.AstridProjectSuggestion}.
 */
public interface AstridProjectSuggestionService {

    /**
     * Save a astridProjectSuggestion.
     *
     * @param astridProjectSuggestionDTO the entity to save.
     * @return the persisted entity.
     */
    AstridProjectSuggestionDTO save(AstridProjectSuggestionDTO astridProjectSuggestionDTO);

    /**
     * Get all the astridProjectSuggestions.
     *
     * @return the list of entities.
     */
    List<AstridProjectSuggestionDTO> findAll();


    /**
     * Get the "id" astridProjectSuggestion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AstridProjectSuggestionDTO> findOne(Long id);

    /**
     * Delete the "id" astridProjectSuggestion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
