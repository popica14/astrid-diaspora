package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.service.AstridProjectSuggestionService;
import com.astrid.diaspora.web.rest.errors.BadRequestAlertException;
import com.astrid.diaspora.service.dto.AstridProjectSuggestionDTO;

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

/**
 * REST controller for managing {@link com.astrid.diaspora.domain.AstridProjectSuggestion}.
 */
@RestController
@RequestMapping("/api")
public class AstridProjectSuggestionResource {

    private final Logger log = LoggerFactory.getLogger(AstridProjectSuggestionResource.class);

    private static final String ENTITY_NAME = "astridProjectSuggestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AstridProjectSuggestionService astridProjectSuggestionService;

    public AstridProjectSuggestionResource(AstridProjectSuggestionService astridProjectSuggestionService) {
        this.astridProjectSuggestionService = astridProjectSuggestionService;
    }

    /**
     * {@code POST  /astrid-project-suggestions} : Create a new astridProjectSuggestion.
     *
     * @param astridProjectSuggestionDTO the astridProjectSuggestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new astridProjectSuggestionDTO, or with status {@code 400 (Bad Request)} if the astridProjectSuggestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/astrid-project-suggestions")
    public ResponseEntity<AstridProjectSuggestionDTO> createAstridProjectSuggestion(@Valid @RequestBody AstridProjectSuggestionDTO astridProjectSuggestionDTO) throws URISyntaxException {
        log.debug("REST request to save AstridProjectSuggestion : {}", astridProjectSuggestionDTO);
        if (astridProjectSuggestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new astridProjectSuggestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AstridProjectSuggestionDTO result = astridProjectSuggestionService.save(astridProjectSuggestionDTO);
        return ResponseEntity.created(new URI("/api/astrid-project-suggestions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /astrid-project-suggestions} : Updates an existing astridProjectSuggestion.
     *
     * @param astridProjectSuggestionDTO the astridProjectSuggestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated astridProjectSuggestionDTO,
     * or with status {@code 400 (Bad Request)} if the astridProjectSuggestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the astridProjectSuggestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/astrid-project-suggestions")
    public ResponseEntity<AstridProjectSuggestionDTO> updateAstridProjectSuggestion(@Valid @RequestBody AstridProjectSuggestionDTO astridProjectSuggestionDTO) throws URISyntaxException {
        log.debug("REST request to update AstridProjectSuggestion : {}", astridProjectSuggestionDTO);
        if (astridProjectSuggestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AstridProjectSuggestionDTO result = astridProjectSuggestionService.save(astridProjectSuggestionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, astridProjectSuggestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /astrid-project-suggestions} : get all the astridProjectSuggestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of astridProjectSuggestions in body.
     */
    @GetMapping("/astrid-project-suggestions")
    public List<AstridProjectSuggestionDTO> getAllAstridProjectSuggestions() {
        log.debug("REST request to get all AstridProjectSuggestions");
        return astridProjectSuggestionService.findAll();
    }

    /**
     * {@code GET  /astrid-project-suggestions/:id} : get the "id" astridProjectSuggestion.
     *
     * @param id the id of the astridProjectSuggestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the astridProjectSuggestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/astrid-project-suggestions/{id}")
    public ResponseEntity<AstridProjectSuggestionDTO> getAstridProjectSuggestion(@PathVariable Long id) {
        log.debug("REST request to get AstridProjectSuggestion : {}", id);
        Optional<AstridProjectSuggestionDTO> astridProjectSuggestionDTO = astridProjectSuggestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(astridProjectSuggestionDTO);
    }

    /**
     * {@code DELETE  /astrid-project-suggestions/:id} : delete the "id" astridProjectSuggestion.
     *
     * @param id the id of the astridProjectSuggestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/astrid-project-suggestions/{id}")
    public ResponseEntity<Void> deleteAstridProjectSuggestion(@PathVariable Long id) {
        log.debug("REST request to delete AstridProjectSuggestion : {}", id);
        astridProjectSuggestionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
