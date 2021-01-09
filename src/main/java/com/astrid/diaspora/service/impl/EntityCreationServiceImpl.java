package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.service.EntityCreationService;
import com.astrid.diaspora.domain.EntityCreation;
import com.astrid.diaspora.repository.EntityCreationRepository;
import com.astrid.diaspora.service.dto.EntityCreationDTO;
import com.astrid.diaspora.service.mapper.EntityCreationMapper;
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
 * Service Implementation for managing {@link EntityCreation}.
 */
@Service
@Transactional
public class EntityCreationServiceImpl implements EntityCreationService {

    private final Logger log = LoggerFactory.getLogger(EntityCreationServiceImpl.class);

    private final EntityCreationRepository entityCreationRepository;

    private final EntityCreationMapper entityCreationMapper;

    public EntityCreationServiceImpl(EntityCreationRepository entityCreationRepository, EntityCreationMapper entityCreationMapper) {
        this.entityCreationRepository = entityCreationRepository;
        this.entityCreationMapper = entityCreationMapper;
    }

    @Override
    public EntityCreationDTO save(EntityCreationDTO entityCreationDTO) {
        log.debug("Request to save EntityCreation : {}", entityCreationDTO);
        EntityCreation entityCreation = entityCreationMapper.toEntity(entityCreationDTO);
        entityCreation = entityCreationRepository.save(entityCreation);
        return entityCreationMapper.toDto(entityCreation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntityCreationDTO> findAll() {
        log.debug("Request to get all EntityCreations");
        return entityCreationRepository.findAll().stream()
            .map(entityCreationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the entityCreations where AstridProject is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EntityCreationDTO> findAllWhereAstridProjectIsNull() {
        log.debug("Request to get all entityCreations where AstridProject is null");
        return StreamSupport
            .stream(entityCreationRepository.findAll().spliterator(), false)
            .filter(entityCreation -> entityCreation.getAstridProject() == null)
            .map(entityCreationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EntityCreationDTO> findOne(Long id) {
        log.debug("Request to get EntityCreation : {}", id);
        return entityCreationRepository.findById(id)
            .map(entityCreationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityCreation : {}", id);
        entityCreationRepository.deleteById(id);
    }
}
