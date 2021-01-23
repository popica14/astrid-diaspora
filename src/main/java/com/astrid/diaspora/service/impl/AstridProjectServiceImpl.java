package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.domain.User;
import com.astrid.diaspora.security.SecurityUtils;
import com.astrid.diaspora.service.AstridProjectService;
import com.astrid.diaspora.domain.AstridProject;
import com.astrid.diaspora.repository.AstridProjectRepository;
import com.astrid.diaspora.service.EntityCreationService;
import com.astrid.diaspora.service.EntityLastModificationService;
import com.astrid.diaspora.service.UserService;
import com.astrid.diaspora.service.dto.AstridProjectDTO;
import com.astrid.diaspora.service.dto.EntityCreationDTO;
import com.astrid.diaspora.service.dto.EntityLastModificationDTO;
import com.astrid.diaspora.service.mapper.AstridProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
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

    private final EntityCreationService entityCreationService;
    private final EntityLastModificationService entityLastModificationService;
    private final UserService userService;

    public AstridProjectServiceImpl(AstridProjectRepository astridProjectRepository, AstridProjectMapper astridProjectMapper,
                                    EntityCreationService entityCreationService,
                                    EntityLastModificationService entityLastModificationService,
                                    UserService userService) {
        this.astridProjectRepository = astridProjectRepository;
        this.astridProjectMapper = astridProjectMapper;
        this.entityCreationService = entityCreationService;
        this.entityLastModificationService = entityLastModificationService;
        this.userService = userService;
    }

    @Override
    public AstridProjectDTO save(AstridProjectDTO astridProjectDTO) {
        log.debug("Request to save AstridProject : {}", astridProjectDTO);
        setEntityCreationAndModification(astridProjectDTO);
        AstridProject astridProject = astridProjectMapper.toEntity(astridProjectDTO);

        astridProject = astridProjectRepository.save(astridProject);
        return astridProjectMapper.toDto(astridProject);
    }

    private void setEntityCreationAndModification(AstridProjectDTO astridProject) {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();

        if (!currentUserLogin.isPresent()) {
            throw new UsernameNotFoundException("NO_LOGIN");
        } else {
            ZonedDateTime now = ZonedDateTime.now();
            Optional<User> optionalUser = userService.getUserWithAuthoritiesByLogin(currentUserLogin.get());
            if (optionalUser.isPresent()) {
                User loggedUser = optionalUser.get();
                if (astridProject.getId() == null) {
                    EntityCreationDTO entityCreationDTO = new EntityCreationDTO();
                    entityCreationDTO.setCreated(now);
                    entityCreationDTO.setCreatedByLogin(loggedUser.getLogin());
                    entityCreationDTO.setCreatedById(loggedUser.getId());
                    EntityCreationDTO created = entityCreationService.save(entityCreationDTO);

                    astridProject.setEntityCreationId(created.getId());
                }
                EntityLastModificationDTO entityLastModificationDTO = new EntityLastModificationDTO();
                entityLastModificationDTO.setLastModified(now);
                entityLastModificationDTO.setLastModifiedByLogin(loggedUser.getLogin());
                entityLastModificationDTO.setLastModifiedById(loggedUser.getId());
                EntityLastModificationDTO modified = entityLastModificationService.save(entityLastModificationDTO);
                astridProject.setEntityLastModificationId(modified.getId());
            }
        }
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
