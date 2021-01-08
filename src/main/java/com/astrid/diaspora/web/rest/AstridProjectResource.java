package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.service.AstridProjectService;
import com.astrid.diaspora.web.rest.errors.BadRequestAlertException;
import com.astrid.diaspora.service.dto.AstridProjectDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.astrid.diaspora.domain.AstridProject}.
 */
@RestController
@RequestMapping("/api")
public class AstridProjectResource {

    private final Logger log = LoggerFactory.getLogger(AstridProjectResource.class);

    private static final String ENTITY_NAME = "astridProject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AstridProjectService astridProjectService;

    public AstridProjectResource(AstridProjectService astridProjectService) {
        this.astridProjectService = astridProjectService;
    }

    /**
     * {@code POST  /astrid-projects} : Create a new astridProject.
     *
     * @param astridProjectDTO the astridProjectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new astridProjectDTO, or with status {@code 400 (Bad Request)} if the astridProject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/astrid-projects")
    public ResponseEntity<AstridProjectDTO> createAstridProject(@RequestBody AstridProjectDTO astridProjectDTO) throws URISyntaxException {
        log.debug("REST request to save AstridProject : {}", astridProjectDTO);
        if (astridProjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new astridProject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AstridProjectDTO result = astridProjectService.save(astridProjectDTO);
        return ResponseEntity.created(new URI("/api/astrid-projects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /astrid-projects} : Updates an existing astridProject.
     *
     * @param astridProjectDTO the astridProjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated astridProjectDTO,
     * or with status {@code 400 (Bad Request)} if the astridProjectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the astridProjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/astrid-projects")
    public ResponseEntity<AstridProjectDTO> updateAstridProject(@RequestBody AstridProjectDTO astridProjectDTO) throws URISyntaxException {
        log.debug("REST request to update AstridProject : {}", astridProjectDTO);
        if (astridProjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AstridProjectDTO result = astridProjectService.save(astridProjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, astridProjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /astrid-projects} : get all the astridProjects.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of astridProjects in body.
     */
    @GetMapping("/astrid-projects")
    public ResponseEntity<List<AstridProjectDTO>> getAllAstridProjects(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of AstridProjects");
        Page<AstridProjectDTO> page;
        if (eagerload) {
            page = astridProjectService.findAllWithEagerRelationships(pageable);
        } else {
            page = astridProjectService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /astrid-projects/:id} : get the "id" astridProject.
     *
     * @param id the id of the astridProjectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the astridProjectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/astrid-projects/{id}")
    public ResponseEntity<AstridProjectDTO> getAstridProject(@PathVariable Long id) {
        log.debug("REST request to get AstridProject : {}", id);
        Optional<AstridProjectDTO> astridProjectDTO = astridProjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(astridProjectDTO);
    }

    /**
     * {@code DELETE  /astrid-projects/:id} : delete the "id" astridProject.
     *
     * @param id the id of the astridProjectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/astrid-projects/{id}")
    public ResponseEntity<Void> deleteAstridProject(@PathVariable Long id) {
        log.debug("REST request to delete AstridProject : {}", id);
        astridProjectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
