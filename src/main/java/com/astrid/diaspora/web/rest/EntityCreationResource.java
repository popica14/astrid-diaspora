package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.service.EntityCreationService;
import com.astrid.diaspora.web.rest.errors.BadRequestAlertException;
import com.astrid.diaspora.service.dto.EntityCreationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.astrid.diaspora.domain.EntityCreation}.
 */
@RestController
@RequestMapping("/api")
public class EntityCreationResource {

    private final Logger log = LoggerFactory.getLogger(EntityCreationResource.class);

    private static final String ENTITY_NAME = "entityCreation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityCreationService entityCreationService;

    public EntityCreationResource(EntityCreationService entityCreationService) {
        this.entityCreationService = entityCreationService;
    }

    /**
     * {@code POST  /entity-creations} : Create a new entityCreation.
     *
     * @param entityCreationDTO the entityCreationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCreationDTO, or with status {@code 400 (Bad Request)} if the entityCreation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-creations")
    public ResponseEntity<EntityCreationDTO> createEntityCreation(@RequestBody EntityCreationDTO entityCreationDTO) throws URISyntaxException {
        log.debug("REST request to save EntityCreation : {}", entityCreationDTO);
        if (entityCreationDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityCreation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityCreationDTO result = entityCreationService.save(entityCreationDTO);
        return ResponseEntity.created(new URI("/api/entity-creations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-creations} : Updates an existing entityCreation.
     *
     * @param entityCreationDTO the entityCreationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCreationDTO,
     * or with status {@code 400 (Bad Request)} if the entityCreationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCreationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-creations")
    public ResponseEntity<EntityCreationDTO> updateEntityCreation(@RequestBody EntityCreationDTO entityCreationDTO) throws URISyntaxException {
        log.debug("REST request to update EntityCreation : {}", entityCreationDTO);
        if (entityCreationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityCreationDTO result = entityCreationService.save(entityCreationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCreationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entity-creations} : get all the entityCreations.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCreations in body.
     */
    @GetMapping("/entity-creations")
    public List<EntityCreationDTO> getAllEntityCreations(@RequestParam(required = false) String filter) {
        if ("astridproject-is-null".equals(filter)) {
            log.debug("REST request to get all EntityCreations where astridProject is null");
            return entityCreationService.findAllWhereAstridProjectIsNull();
        }
        log.debug("REST request to get all EntityCreations");
        return entityCreationService.findAll();
    }

    /**
     * {@code GET  /entity-creations/:id} : get the "id" entityCreation.
     *
     * @param id the id of the entityCreationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCreationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-creations/{id}")
    public ResponseEntity<EntityCreationDTO> getEntityCreation(@PathVariable Long id) {
        log.debug("REST request to get EntityCreation : {}", id);
        Optional<EntityCreationDTO> entityCreationDTO = entityCreationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityCreationDTO);
    }

    /**
     * {@code DELETE  /entity-creations/:id} : delete the "id" entityCreation.
     *
     * @param id the id of the entityCreationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-creations/{id}")
    public ResponseEntity<Void> deleteEntityCreation(@PathVariable Long id) {
        log.debug("REST request to delete EntityCreation : {}", id);
        entityCreationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
