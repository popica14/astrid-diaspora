package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.service.EntityLastModificationService;
import com.astrid.diaspora.domain.EntityLastModification;
import com.astrid.diaspora.repository.EntityLastModificationRepository;
import com.astrid.diaspora.service.dto.EntityLastModificationDTO;
import com.astrid.diaspora.service.mapper.EntityLastModificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link EntityLastModification}.
 */
@Service
@Transactional
public class EntityLastModificationServiceImpl implements EntityLastModificationService {

    private final Logger log = LoggerFactory.getLogger(EntityLastModificationServiceImpl.class);

    private final EntityLastModificationRepository entityLastModificationRepository;

    private final EntityLastModificationMapper entityLastModificationMapper;

    public EntityLastModificationServiceImpl(EntityLastModificationRepository entityLastModificationRepository, EntityLastModificationMapper entityLastModificationMapper) {
        this.entityLastModificationRepository = entityLastModificationRepository;
        this.entityLastModificationMapper = entityLastModificationMapper;
    }

    @Override
    public EntityLastModificationDTO save(EntityLastModificationDTO entityLastModificationDTO) {
        log.debug("Request to save EntityLastModification : {}", entityLastModificationDTO);
        EntityLastModification entityLastModification = entityLastModificationMapper.toEntity(entityLastModificationDTO);
        entityLastModification = entityLastModificationRepository.save(entityLastModification);
        return entityLastModificationMapper.toDto(entityLastModification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntityLastModificationDTO> findAll() {
        log.debug("Request to get all EntityLastModifications");
        return entityLastModificationRepository.findAll().stream()
            .map(entityLastModificationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the entityLastModifications where AstridProject is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EntityLastModificationDTO> findAllWhereAstridProjectIsNull() {
        log.debug("Request to get all entityLastModifications where AstridProject is null");
        return StreamSupport
            .stream(entityLastModificationRepository.findAll().spliterator(), false)
            .filter(entityLastModification -> entityLastModification.getAstridProject() == null)
            .map(entityLastModificationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EntityLastModificationDTO> findOne(Long id) {
        log.debug("Request to get EntityLastModification : {}", id);
        return entityLastModificationRepository.findById(id)
            .map(entityLastModificationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityLastModification : {}", id);
        entityLastModificationRepository.deleteById(id);
    }
}
