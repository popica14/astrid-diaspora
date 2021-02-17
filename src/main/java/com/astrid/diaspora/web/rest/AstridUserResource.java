package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.service.AstridUserService;
import com.astrid.diaspora.web.rest.errors.BadRequestAlertException;
import com.astrid.diaspora.service.dto.AstridUserDTO;

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
 * REST controller for managing {@link com.astrid.diaspora.domain.AstridUser}.
 */
@RestController
@RequestMapping("/api")
public class AstridUserResource {

    private final Logger log = LoggerFactory.getLogger(AstridUserResource.class);

    private static final String ENTITY_NAME = "astridUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AstridUserService astridUserService;

    public AstridUserResource(AstridUserService astridUserService) {
        this.astridUserService = astridUserService;
    }

    /**
     * {@code POST  /astrid-users} : Create a new astridUser.
     *
     * @param astridUserDTO the astridUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new astridUserDTO, or with status {@code 400 (Bad Request)} if the astridUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/astrid-users")
    public ResponseEntity<AstridUserDTO> createAstridUser(@Valid @RequestBody AstridUserDTO astridUserDTO) throws URISyntaxException {
        log.debug("REST request to save AstridUser : {}", astridUserDTO);
        if (astridUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new astridUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AstridUserDTO result = astridUserService.save(astridUserDTO);
        return ResponseEntity.created(new URI("/api/astrid-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /astrid-users} : Updates an existing astridUser.
     *
     * @param astridUserDTO the astridUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated astridUserDTO,
     * or with status {@code 400 (Bad Request)} if the astridUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the astridUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/astrid-users")
    public ResponseEntity<AstridUserDTO> updateAstridUser(@Valid @RequestBody AstridUserDTO astridUserDTO) throws URISyntaxException {
        log.debug("REST request to update AstridUser : {}", astridUserDTO);
        if (astridUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AstridUserDTO result = astridUserService.save(astridUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, astridUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /astrid-users} : get all the astridUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of astridUsers in body.
     */
    @GetMapping("/astrid-users")
    public List<AstridUserDTO> getAllAstridUsers() {
        log.debug("REST request to get all AstridUsers");
        return astridUserService.findAll();
    }

    /**
     * {@code GET  /astrid-users/:id} : get the "id" astridUser.
     *
     * @param id the id of the astridUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the astridUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/astrid-users/{id}")
    public ResponseEntity<AstridUserDTO> getAstridUser(@PathVariable Long id) {
        log.debug("REST request to get AstridUser : {}", id);
        Optional<AstridUserDTO> astridUserDTO = astridUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(astridUserDTO);
    }

    /**
     * {@code GET  /astrid-users/:id} : get the "id" astridUser.
     *
     * @param id the id of the astridUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the astridUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/astrid-users/byUserId/{id}")
    public ResponseEntity<AstridUserDTO> getAstridUserByUserId(@PathVariable Long id) {
        log.debug("REST request to get AstridUser : {}", id);
        Optional<AstridUserDTO> astridUserDTO = astridUserService.findOneByUserId(id);
        return ResponseUtil.wrapOrNotFound(astridUserDTO);
    }

    /**
     * {@code DELETE  /astrid-users/:id} : delete the "id" astridUser.
     *
     * @param id the id of the astridUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/astrid-users/{id}")
    public ResponseEntity<Void> deleteAstridUser(@PathVariable Long id) {
        log.debug("REST request to delete AstridUser : {}", id);
        astridUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
