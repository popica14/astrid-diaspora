package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.service.EntityLastModificationService;
import com.astrid.diaspora.web.rest.errors.BadRequestAlertException;
import com.astrid.diaspora.service.dto.EntityLastModificationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.astrid.diaspora.domain.EntityLastModification}.
 */
@RestController
@RequestMapping("/api")
public class EntityLastModificationResource {

    private final Logger log = LoggerFactory.getLogger(EntityLastModificationResource.class);

    private static final String ENTITY_NAME = "entityLastModification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityLastModificationService entityLastModificationService;

    public EntityLastModificationResource(EntityLastModificationService entityLastModificationService) {
        this.entityLastModificationService = entityLastModificationService;
    }

    /**
     * {@code POST  /entity-last-modifications} : Create a new entityLastModification.
     *
     * @param entityLastModificationDTO the entityLastModificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityLastModificationDTO, or with status {@code 400 (Bad Request)} if the entityLastModification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-last-modifications")
    public ResponseEntity<EntityLastModificationDTO> createEntityLastModification(@Valid @RequestBody EntityLastModificationDTO entityLastModificationDTO) throws URISyntaxException {
        log.debug("REST request to save EntityLastModification : {}", entityLastModificationDTO);
        if (entityLastModificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityLastModification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityLastModificationDTO result = entityLastModificationService.save(entityLastModificationDTO);
        return ResponseEntity.created(new URI("/api/entity-last-modifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-last-modifications} : Updates an existing entityLastModification.
     *
     * @param entityLastModificationDTO the entityLastModificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityLastModificationDTO,
     * or with status {@code 400 (Bad Request)} if the entityLastModificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityLastModificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-last-modifications")
    public ResponseEntity<EntityLastModificationDTO> updateEntityLastModification(@Valid @RequestBody EntityLastModificationDTO entityLastModificationDTO) throws URISyntaxException {
        log.debug("REST request to update EntityLastModification : {}", entityLastModificationDTO);
        if (entityLastModificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityLastModificationDTO result = entityLastModificationService.save(entityLastModificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityLastModificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entity-last-modifications} : get all the entityLastModifications.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityLastModifications in body.
     */
    @GetMapping("/entity-last-modifications")
    public List<EntityLastModificationDTO> getAllEntityLastModifications(@RequestParam(required = false) String filter) {
        if ("astridproject-is-null".equals(filter)) {
            log.debug("REST request to get all EntityLastModifications where astridProject is null");
            return entityLastModificationService.findAllWhereAstridProjectIsNull();
        }
        log.debug("REST request to get all EntityLastModifications");
        return entityLastModificationService.findAll();
    }

    /**
     * {@code GET  /entity-last-modifications/:id} : get the "id" entityLastModification.
     *
     * @param id the id of the entityLastModificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityLastModificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-last-modifications/{id}")
    public ResponseEntity<EntityLastModificationDTO> getEntityLastModification(@PathVariable Long id) {
        log.debug("REST request to get EntityLastModification : {}", id);
        Optional<EntityLastModificationDTO> entityLastModificationDTO = entityLastModificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityLastModificationDTO);
    }

    /**
     * {@code DELETE  /entity-last-modifications/:id} : delete the "id" entityLastModification.
     *
     * @param id the id of the entityLastModificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-last-modifications/{id}")
    public ResponseEntity<Void> deleteEntityLastModification(@PathVariable Long id) {
        log.debug("REST request to delete EntityLastModification : {}", id);
        entityLastModificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
