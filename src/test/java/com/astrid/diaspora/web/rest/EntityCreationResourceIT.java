package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.ProjectsOverviewApp;
import com.astrid.diaspora.domain.EntityCreation;
import com.astrid.diaspora.repository.EntityCreationRepository;
import com.astrid.diaspora.service.EntityCreationService;
import com.astrid.diaspora.service.dto.EntityCreationDTO;
import com.astrid.diaspora.service.mapper.EntityCreationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.astrid.diaspora.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EntityCreationResource} REST controller.
 */
@SpringBootTest(classes = ProjectsOverviewApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntityCreationResourceIT {

    private static final ZonedDateTime DEFAULT_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private EntityCreationRepository entityCreationRepository;

    @Autowired
    private EntityCreationMapper entityCreationMapper;

    @Autowired
    private EntityCreationService entityCreationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCreationMockMvc;

    private EntityCreation entityCreation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCreation createEntity(EntityManager em) {
        EntityCreation entityCreation = new EntityCreation()
            .created(DEFAULT_CREATED);
        return entityCreation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCreation createUpdatedEntity(EntityManager em) {
        EntityCreation entityCreation = new EntityCreation()
            .created(UPDATED_CREATED);
        return entityCreation;
    }

    @BeforeEach
    public void initTest() {
        entityCreation = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityCreation() throws Exception {
        int databaseSizeBeforeCreate = entityCreationRepository.findAll().size();
        // Create the EntityCreation
        EntityCreationDTO entityCreationDTO = entityCreationMapper.toDto(entityCreation);
        restEntityCreationMockMvc.perform(post("/api/entity-creations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityCreationDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityCreation in the database
        List<EntityCreation> entityCreationList = entityCreationRepository.findAll();
        assertThat(entityCreationList).hasSize(databaseSizeBeforeCreate + 1);
        EntityCreation testEntityCreation = entityCreationList.get(entityCreationList.size() - 1);
        assertThat(testEntityCreation.getCreated()).isEqualTo(DEFAULT_CREATED);
    }

    @Test
    @Transactional
    public void createEntityCreationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityCreationRepository.findAll().size();

        // Create the EntityCreation with an existing ID
        entityCreation.setId(1L);
        EntityCreationDTO entityCreationDTO = entityCreationMapper.toDto(entityCreation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCreationMockMvc.perform(post("/api/entity-creations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityCreationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCreation in the database
        List<EntityCreation> entityCreationList = entityCreationRepository.findAll();
        assertThat(entityCreationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntityCreations() throws Exception {
        // Initialize the database
        entityCreationRepository.saveAndFlush(entityCreation);

        // Get all the entityCreationList
        restEntityCreationMockMvc.perform(get("/api/entity-creations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityCreation.getId().intValue())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(sameInstant(DEFAULT_CREATED))));
    }
    
    @Test
    @Transactional
    public void getEntityCreation() throws Exception {
        // Initialize the database
        entityCreationRepository.saveAndFlush(entityCreation);

        // Get the entityCreation
        restEntityCreationMockMvc.perform(get("/api/entity-creations/{id}", entityCreation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityCreation.getId().intValue()))
            .andExpect(jsonPath("$.created").value(sameInstant(DEFAULT_CREATED)));
    }
    @Test
    @Transactional
    public void getNonExistingEntityCreation() throws Exception {
        // Get the entityCreation
        restEntityCreationMockMvc.perform(get("/api/entity-creations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityCreation() throws Exception {
        // Initialize the database
        entityCreationRepository.saveAndFlush(entityCreation);

        int databaseSizeBeforeUpdate = entityCreationRepository.findAll().size();

        // Update the entityCreation
        EntityCreation updatedEntityCreation = entityCreationRepository.findById(entityCreation.getId()).get();
        // Disconnect from session so that the updates on updatedEntityCreation are not directly saved in db
        em.detach(updatedEntityCreation);
        updatedEntityCreation
            .created(UPDATED_CREATED);
        EntityCreationDTO entityCreationDTO = entityCreationMapper.toDto(updatedEntityCreation);

        restEntityCreationMockMvc.perform(put("/api/entity-creations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityCreationDTO)))
            .andExpect(status().isOk());

        // Validate the EntityCreation in the database
        List<EntityCreation> entityCreationList = entityCreationRepository.findAll();
        assertThat(entityCreationList).hasSize(databaseSizeBeforeUpdate);
        EntityCreation testEntityCreation = entityCreationList.get(entityCreationList.size() - 1);
        assertThat(testEntityCreation.getCreated()).isEqualTo(UPDATED_CREATED);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityCreation() throws Exception {
        int databaseSizeBeforeUpdate = entityCreationRepository.findAll().size();

        // Create the EntityCreation
        EntityCreationDTO entityCreationDTO = entityCreationMapper.toDto(entityCreation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCreationMockMvc.perform(put("/api/entity-creations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityCreationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCreation in the database
        List<EntityCreation> entityCreationList = entityCreationRepository.findAll();
        assertThat(entityCreationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityCreation() throws Exception {
        // Initialize the database
        entityCreationRepository.saveAndFlush(entityCreation);

        int databaseSizeBeforeDelete = entityCreationRepository.findAll().size();

        // Delete the entityCreation
        restEntityCreationMockMvc.perform(delete("/api/entity-creations/{id}", entityCreation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityCreation> entityCreationList = entityCreationRepository.findAll();
        assertThat(entityCreationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
