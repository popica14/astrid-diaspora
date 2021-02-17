package com.astrid.diaspora.service.impl;

import com.astrid.diaspora.service.AstridUserService;
import com.astrid.diaspora.domain.AstridUser;
import com.astrid.diaspora.repository.AstridUserRepository;
import com.astrid.diaspora.service.dto.AstridUserDTO;
import com.astrid.diaspora.service.mapper.AstridUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AstridUser}.
 */
@Service
@Transactional
public class AstridUserServiceImpl implements AstridUserService {

    private final Logger log = LoggerFactory.getLogger(AstridUserServiceImpl.class);

    private final AstridUserRepository astridUserRepository;

    private final AstridUserMapper astridUserMapper;

    public AstridUserServiceImpl(AstridUserRepository astridUserRepository, AstridUserMapper astridUserMapper) {
        this.astridUserRepository = astridUserRepository;
        this.astridUserMapper = astridUserMapper;
    }

    @Override
    public AstridUserDTO save(AstridUserDTO astridUserDTO) {
        log.debug("Request to save AstridUser : {}", astridUserDTO);
        AstridUser astridUser = astridUserMapper.toEntity(astridUserDTO);
        astridUser = astridUserRepository.save(astridUser);
        return astridUserMapper.toDto(astridUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AstridUserDTO> findAll() {
        log.debug("Request to get all AstridUsers");
        return astridUserRepository.findAll().stream()
            .map(astridUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AstridUserDTO> findOne(Long id) {
        log.debug("Request to get AstridUser : {}", id);
        return astridUserRepository.findById(id)
            .map(astridUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AstridUserDTO> findOneByUserId(Long id) {
        log.debug("Request to get AstridUser : {}", id);
        return astridUserRepository.findByUserId(id)
            .map(astridUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AstridUser : {}", id);
        astridUserRepository.deleteById(id);
    }
}
