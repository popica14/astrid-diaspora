package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.ProjectsOverviewApp;
import com.astrid.diaspora.domain.EntityLastModification;
import com.astrid.diaspora.domain.User;
import com.astrid.diaspora.repository.EntityLastModificationRepository;
import com.astrid.diaspora.service.EntityLastModificationService;
import com.astrid.diaspora.service.dto.EntityLastModificationDTO;
import com.astrid.diaspora.service.mapper.EntityLastModificationMapper;

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
 * Integration tests for the {@link EntityLastModificationResource} REST controller.
 */
@SpringBootTest(classes = ProjectsOverviewApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntityLastModificationResourceIT {

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFIED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private EntityLastModificationRepository entityLastModificationRepository;

    @Autowired
    private EntityLastModificationMapper entityLastModificationMapper;

    @Autowired
    private EntityLastModificationService entityLastModificationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityLastModificationMockMvc;

    private EntityLastModification entityLastModification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityLastModification createEntity(EntityManager em) {
        EntityLastModification entityLastModification = new EntityLastModification()
            .lastModified(DEFAULT_LAST_MODIFIED);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        entityLastModification.setLastModifiedBy(user);
        return entityLastModification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityLastModification createUpdatedEntity(EntityManager em) {
        EntityLastModification entityLastModification = new EntityLastModification()
            .lastModified(UPDATED_LAST_MODIFIED);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        entityLastModification.setLastModifiedBy(user);
        return entityLastModification;
    }

    @BeforeEach
    public void initTest() {
        entityLastModification = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityLastModification() throws Exception {
        int databaseSizeBeforeCreate = entityLastModificationRepository.findAll().size();
        // Create the EntityLastModification
        EntityLastModificationDTO entityLastModificationDTO = entityLastModificationMapper.toDto(entityLastModification);
        restEntityLastModificationMockMvc.perform(post("/api/entity-last-modifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityLastModificationDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityLastModification in the database
        List<EntityLastModification> entityLastModificationList = entityLastModificationRepository.findAll();
        assertThat(entityLastModificationList).hasSize(databaseSizeBeforeCreate + 1);
        EntityLastModification testEntityLastModification = entityLastModificationList.get(entityLastModificationList.size() - 1);
        assertThat(testEntityLastModification.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
    }

    @Test
    @Transactional
    public void createEntityLastModificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityLastModificationRepository.findAll().size();

        // Create the EntityLastModification with an existing ID
        entityLastModification.setId(1L);
        EntityLastModificationDTO entityLastModificationDTO = entityLastModificationMapper.toDto(entityLastModification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityLastModificationMockMvc.perform(post("/api/entity-last-modifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityLastModificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityLastModification in the database
        List<EntityLastModification> entityLastModificationList = entityLastModificationRepository.findAll();
        assertThat(entityLastModificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntityLastModifications() throws Exception {
        // Initialize the database
        entityLastModificationRepository.saveAndFlush(entityLastModification);

        // Get all the entityLastModificationList
        restEntityLastModificationMockMvc.perform(get("/api/entity-last-modifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityLastModification.getId().intValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED))));
    }
    
    @Test
    @Transactional
    public void getEntityLastModification() throws Exception {
        // Initialize the database
        entityLastModificationRepository.saveAndFlush(entityLastModification);

        // Get the entityLastModification
        restEntityLastModificationMockMvc.perform(get("/api/entity-last-modifications/{id}", entityLastModification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityLastModification.getId().intValue()))
            .andExpect(jsonPath("$.lastModified").value(sameInstant(DEFAULT_LAST_MODIFIED)));
    }
    @Test
    @Transactional
    public void getNonExistingEntityLastModification() throws Exception {
        // Get the entityLastModification
        restEntityLastModificationMockMvc.perform(get("/api/entity-last-modifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityLastModification() throws Exception {
        // Initialize the database
        entityLastModificationRepository.saveAndFlush(entityLastModification);

        int databaseSizeBeforeUpdate = entityLastModificationRepository.findAll().size();

        // Update the entityLastModification
        EntityLastModification updatedEntityLastModification = entityLastModificationRepository.findById(entityLastModification.getId()).get();
        // Disconnect from session so that the updates on updatedEntityLastModification are not directly saved in db
        em.detach(updatedEntityLastModification);
        updatedEntityLastModification
            .lastModified(UPDATED_LAST_MODIFIED);
        EntityLastModificationDTO entityLastModificationDTO = entityLastModificationMapper.toDto(updatedEntityLastModification);

        restEntityLastModificationMockMvc.perform(put("/api/entity-last-modifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityLastModificationDTO)))
            .andExpect(status().isOk());

        // Validate the EntityLastModification in the database
        List<EntityLastModification> entityLastModificationList = entityLastModificationRepository.findAll();
        assertThat(entityLastModificationList).hasSize(databaseSizeBeforeUpdate);
        EntityLastModification testEntityLastModification = entityLastModificationList.get(entityLastModificationList.size() - 1);
        assertThat(testEntityLastModification.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityLastModification() throws Exception {
        int databaseSizeBeforeUpdate = entityLastModificationRepository.findAll().size();

        // Create the EntityLastModification
        EntityLastModificationDTO entityLastModificationDTO = entityLastModificationMapper.toDto(entityLastModification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityLastModificationMockMvc.perform(put("/api/entity-last-modifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityLastModificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityLastModification in the database
        List<EntityLastModification> entityLastModificationList = entityLastModificationRepository.findAll();
        assertThat(entityLastModificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityLastModification() throws Exception {
        // Initialize the database
        entityLastModificationRepository.saveAndFlush(entityLastModification);

        int databaseSizeBeforeDelete = entityLastModificationRepository.findAll().size();

        // Delete the entityLastModification
        restEntityLastModificationMockMvc.perform(delete("/api/entity-last-modifications/{id}", entityLastModification.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityLastModification> entityLastModificationList = entityLastModificationRepository.findAll();
        assertThat(entityLastModificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
