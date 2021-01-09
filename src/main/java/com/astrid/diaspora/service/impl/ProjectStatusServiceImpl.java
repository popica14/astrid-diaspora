package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.service.ProjectStatusService;
import com.astrid.diaspora.domain.ProjectStatus;
import com.astrid.diaspora.repository.ProjectStatusRepository;
import com.astrid.diaspora.service.dto.ProjectStatusDTO;
import com.astrid.diaspora.service.mapper.ProjectStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProjectStatus}.
 */
@Service
@Transactional
public class ProjectStatusServiceImpl implements ProjectStatusService {

    private final Logger log = LoggerFactory.getLogger(ProjectStatusServiceImpl.class);

    private final ProjectStatusRepository projectStatusRepository;

    private final ProjectStatusMapper projectStatusMapper;

    public ProjectStatusServiceImpl(ProjectStatusRepository projectStatusRepository, ProjectStatusMapper projectStatusMapper) {
        this.projectStatusRepository = projectStatusRepository;
        this.projectStatusMapper = projectStatusMapper;
    }

    @Override
    public ProjectStatusDTO save(ProjectStatusDTO projectStatusDTO) {
        log.debug("Request to save ProjectStatus : {}", projectStatusDTO);
        ProjectStatus projectStatus = projectStatusMapper.toEntity(projectStatusDTO);
        projectStatus = projectStatusRepository.save(projectStatus);
        return projectStatusMapper.toDto(projectStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectStatusDTO> findAll() {
        log.debug("Request to get all ProjectStatuses");
        return projectStatusRepository.findAll().stream()
            .map(projectStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectStatusDTO> findOne(Long id) {
        log.debug("Request to get ProjectStatus : {}", id);
        return projectStatusRepository.findById(id)
            .map(projectStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectStatus : {}", id);
        projectStatusRepository.deleteById(id);
    }
}
