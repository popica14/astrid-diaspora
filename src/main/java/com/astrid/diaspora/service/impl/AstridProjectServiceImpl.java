package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.service.AstridProjectService;
import com.astrid.diaspora.domain.AstridProject;
import com.astrid.diaspora.repository.AstridProjectRepository;
import com.astrid.diaspora.service.dto.AstridProjectDTO;
import com.astrid.diaspora.service.mapper.AstridProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AstridProject}.
 */
@Service
@Transactional
public class AstridProjectServiceImpl implements AstridProjectService {

    private final Logger log = LoggerFactory.getLogger(AstridProjectServiceImpl.class);

    private final AstridProjectRepository astridProjectRepository;

    private final AstridProjectMapper astridProjectMapper;

    public AstridProjectServiceImpl(AstridProjectRepository astridProjectRepository, AstridProjectMapper astridProjectMapper) {
        this.astridProjectRepository = astridProjectRepository;
        this.astridProjectMapper = astridProjectMapper;
    }

    @Override
    public AstridProjectDTO save(AstridProjectDTO astridProjectDTO) {
        log.debug("Request to save AstridProject : {}", astridProjectDTO);
        AstridProject astridProject = astridProjectMapper.toEntity(astridProjectDTO);
        astridProject = astridProjectRepository.save(astridProject);
        return astridProjectMapper.toDto(astridProject);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AstridProjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AstridProjects");
        return astridProjectRepository.findAll(pageable)
            .map(astridProjectMapper::toDto);
    }


    public Page<AstridProjectDTO> findAllWithEagerRelationships(Pageable pageable) {
        return astridProjectRepository.findAllWithEagerRelationships(pageable).map(astridProjectMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AstridProjectDTO> findOne(Long id) {
        log.debug("Request to get AstridProject : {}", id);
        return astridProjectRepository.findOneWithEagerRelationships(id)
            .map(astridProjectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AstridProject : {}", id);
        astridProjectRepository.deleteById(id);
    }
}
