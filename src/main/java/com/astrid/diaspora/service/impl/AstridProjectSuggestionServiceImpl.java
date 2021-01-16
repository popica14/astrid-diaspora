package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.domain.ProjectStatus;
import com.astrid.diaspora.repository.ProjectStatusRepository;
import com.astrid.diaspora.service.AstridProjectSuggestionService;
import com.astrid.diaspora.domain.AstridProjectSuggestion;
import com.astrid.diaspora.repository.AstridProjectSuggestionRepository;
import com.astrid.diaspora.service.ProjectStatusService;
import com.astrid.diaspora.service.dto.AstridProjectSuggestionDTO;
import com.astrid.diaspora.service.dto.ProjectStatusDTO;
import com.astrid.diaspora.service.mapper.AstridProjectSuggestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AstridProjectSuggestion}.
 */
@Service
@Transactional
public class AstridProjectSuggestionServiceImpl implements AstridProjectSuggestionService {

    private final Logger log = LoggerFactory.getLogger(AstridProjectSuggestionServiceImpl.class);

    private final AstridProjectSuggestionRepository astridProjectSuggestionRepository;

    private final AstridProjectSuggestionMapper astridProjectSuggestionMapper;

    private final ProjectStatusRepository projectStatusRepository;

    public AstridProjectSuggestionServiceImpl(AstridProjectSuggestionRepository astridProjectSuggestionRepository,
                                              AstridProjectSuggestionMapper astridProjectSuggestionMapper,
                                              ProjectStatusRepository projectStatusRepository) {
        this.astridProjectSuggestionRepository = astridProjectSuggestionRepository;
        this.astridProjectSuggestionMapper = astridProjectSuggestionMapper;
        this.projectStatusRepository = projectStatusRepository;
    }

    @Override
    public AstridProjectSuggestionDTO save(AstridProjectSuggestionDTO astridProjectSuggestionDTO) {
        log.debug("Request to save AstridProjectSuggestion : {}", astridProjectSuggestionDTO);
        AstridProjectSuggestion astridProjectSuggestion = astridProjectSuggestionMapper.toEntity(astridProjectSuggestionDTO);
        if (astridProjectSuggestion.getId() == null) {
            ProjectStatus newProjectStatus = projectStatusRepository.findByOrder(1);
            astridProjectSuggestion.setStatus(newProjectStatus);
        }
        astridProjectSuggestion = astridProjectSuggestionRepository.save(astridProjectSuggestion);
        return astridProjectSuggestionMapper.toDto(astridProjectSuggestion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AstridProjectSuggestionDTO> findAll() {
        log.debug("Request to get all AstridProjectSuggestions");
        return astridProjectSuggestionRepository.findAll().stream()
            .map(astridProjectSuggestionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AstridProjectSuggestionDTO> findOne(Long id) {
        log.debug("Request to get AstridProjectSuggestion : {}", id);
        return astridProjectSuggestionRepository.findById(id)
            .map(astridProjectSuggestionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AstridProjectSuggestion : {}", id);
        astridProjectSuggestionRepository.deleteById(id);
    }
}
