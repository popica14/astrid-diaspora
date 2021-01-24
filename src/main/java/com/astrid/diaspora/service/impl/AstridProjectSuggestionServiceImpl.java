package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.domain.User;
import com.astrid.diaspora.repository.ProjectStatusRepository;
import com.astrid.diaspora.security.SecurityUtils;
import com.astrid.diaspora.service.AstridProjectSuggestionService;
import com.astrid.diaspora.domain.AstridProjectSuggestion;
import com.astrid.diaspora.repository.AstridProjectSuggestionRepository;
import com.astrid.diaspora.service.UserService;
import com.astrid.diaspora.service.dto.AstridProjectSuggestionDTO;
import com.astrid.diaspora.service.mapper.AstridProjectSuggestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final UserService userService;

    public AstridProjectSuggestionServiceImpl(AstridProjectSuggestionRepository astridProjectSuggestionRepository,
                                              AstridProjectSuggestionMapper astridProjectSuggestionMapper,
                                              ProjectStatusRepository projectStatusRepository,
                                              UserService userService) {
        this.astridProjectSuggestionRepository = astridProjectSuggestionRepository;
        this.astridProjectSuggestionMapper = astridProjectSuggestionMapper;
        this.userService = userService;
    }

    @Override
    public AstridProjectSuggestionDTO save(AstridProjectSuggestionDTO astridProjectSuggestionDTO) {
        log.debug("Request to save AstridProjectSuggestion : {}", astridProjectSuggestionDTO);
        prepareProjectBeforeSaving(astridProjectSuggestionDTO);
        AstridProjectSuggestion astridProjectSuggestion = astridProjectSuggestionMapper.toEntity(astridProjectSuggestionDTO);
        astridProjectSuggestion = astridProjectSuggestionRepository.save(astridProjectSuggestion);
        return astridProjectSuggestionMapper.toDto(astridProjectSuggestion);
    }

    private void prepareProjectBeforeSaving(AstridProjectSuggestionDTO astridProjectSuggestionDTO) {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();

        if (!currentUserLogin.isPresent()) {
            throw new UsernameNotFoundException("NO_LOGIN");
        } else {
            User loggedUser = userService.getUserWithAuthoritiesByLogin(currentUserLogin.get()).get();
            if (astridProjectSuggestionDTO.getId() == null) {
                setNewProjectStatus(astridProjectSuggestionDTO);
                setInitiator(astridProjectSuggestionDTO, loggedUser);
            }
        }
    }

    private void setInitiator(AstridProjectSuggestionDTO astridProjectSuggestionDTO, User loggedUser) {
        astridProjectSuggestionDTO.setInitiatorId(loggedUser.getId());
        astridProjectSuggestionDTO.setInitiatorLogin(loggedUser.getLogin());
    }

    private void setNewProjectStatus(AstridProjectSuggestionDTO astridProjectSuggestionDTO) {
        astridProjectSuggestionDTO.setStatusId(1L);
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
