package com.astrid.diaspora.service;

import com.astrid.diaspora.service.dto.ProjectStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.astrid.diaspora.domain.ProjectStatus}.
 */
public interface ProjectStatusService {

    /**
     * Save a projectStatus.
     *
     * @param projectStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ProjectStatusDTO save(ProjectStatusDTO projectStatusDTO);

    /**
     * Get all the projectStatuses.
     *
     * @return the list of entities.
     */
    List<ProjectStatusDTO> findAll();


    /**
     * Get the "id" projectStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectStatusDTO> findOne(Long id);

    /**
     * Delete the "id" projectStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
