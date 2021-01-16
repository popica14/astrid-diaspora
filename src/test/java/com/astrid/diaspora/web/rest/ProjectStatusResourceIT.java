package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.ProjectsOverviewApp;
import com.astrid.diaspora.domain.ProjectStatus;
import com.astrid.diaspora.repository.ProjectStatusRepository;
import com.astrid.diaspora.service.ProjectStatusService;
import com.astrid.diaspora.service.dto.ProjectStatusDTO;
import com.astrid.diaspora.service.mapper.ProjectStatusMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProjectStatusResource} REST controller.
 */
@SpringBootTest(classes = ProjectsOverviewApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DAYS_TO_NOTIFICATION = 1;
    private static final Integer UPDATED_DAYS_TO_NOTIFICATION = 2;

    private static final Integer DEFAULT_ORDER = 1;
    private static final Integer UPDATED_ORDER = 2;

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @Autowired
    private ProjectStatusMapper projectStatusMapper;

    @Autowired
    private ProjectStatusService projectStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectStatusMockMvc;

    private ProjectStatus projectStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectStatus createEntity(EntityManager em) {
        ProjectStatus projectStatus = new ProjectStatus()
            .name(DEFAULT_NAME)
            .daysToNotification(DEFAULT_DAYS_TO_NOTIFICATION)
            .order(DEFAULT_ORDER);
        return projectStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectStatus createUpdatedEntity(EntityManager em) {
        ProjectStatus projectStatus = new ProjectStatus()
            .name(UPDATED_NAME)
            .daysToNotification(UPDATED_DAYS_TO_NOTIFICATION)
            .order(UPDATED_ORDER);
        return projectStatus;
    }

    @BeforeEach
    public void initTest() {
        projectStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectStatus() throws Exception {
        int databaseSizeBeforeCreate = projectStatusRepository.findAll().size();
        // Create the ProjectStatus
        ProjectStatusDTO projectStatusDTO = projectStatusMapper.toDto(projectStatus);
        restProjectStatusMockMvc.perform(post("/api/project-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectStatus in the database
        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectStatus testProjectStatus = projectStatusList.get(projectStatusList.size() - 1);
        assertThat(testProjectStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProjectStatus.getDaysToNotification()).isEqualTo(DEFAULT_DAYS_TO_NOTIFICATION);
        assertThat(testProjectStatus.getOrder()).isEqualTo(DEFAULT_ORDER);
    }

    @Test
    @Transactional
    public void createProjectStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectStatusRepository.findAll().size();

        // Create the ProjectStatus with an existing ID
        projectStatus.setId(1L);
        ProjectStatusDTO projectStatusDTO = projectStatusMapper.toDto(projectStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectStatusMockMvc.perform(post("/api/project-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectStatus in the database
        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectStatusRepository.findAll().size();
        // set the field null
        projectStatus.setName(null);

        // Create the ProjectStatus, which fails.
        ProjectStatusDTO projectStatusDTO = projectStatusMapper.toDto(projectStatus);


        restProjectStatusMockMvc.perform(post("/api/project-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDaysToNotificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectStatusRepository.findAll().size();
        // set the field null
        projectStatus.setDaysToNotification(null);

        // Create the ProjectStatus, which fails.
        ProjectStatusDTO projectStatusDTO = projectStatusMapper.toDto(projectStatus);


        restProjectStatusMockMvc.perform(post("/api/project-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectStatusRepository.findAll().size();
        // set the field null
        projectStatus.setOrder(null);

        // Create the ProjectStatus, which fails.
        ProjectStatusDTO projectStatusDTO = projectStatusMapper.toDto(projectStatus);


        restProjectStatusMockMvc.perform(post("/api/project-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectStatuses() throws Exception {
        // Initialize the database
        projectStatusRepository.saveAndFlush(projectStatus);

        // Get all the projectStatusList
        restProjectStatusMockMvc.perform(get("/api/project-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].daysToNotification").value(hasItem(DEFAULT_DAYS_TO_NOTIFICATION)))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)));
    }
    
    @Test
    @Transactional
    public void getProjectStatus() throws Exception {
        // Initialize the database
        projectStatusRepository.saveAndFlush(projectStatus);

        // Get the projectStatus
        restProjectStatusMockMvc.perform(get("/api/project-statuses/{id}", projectStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.daysToNotification").value(DEFAULT_DAYS_TO_NOTIFICATION))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingProjectStatus() throws Exception {
        // Get the projectStatus
        restProjectStatusMockMvc.perform(get("/api/project-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectStatus() throws Exception {
        // Initialize the database
        projectStatusRepository.saveAndFlush(projectStatus);

        int databaseSizeBeforeUpdate = projectStatusRepository.findAll().size();

        // Update the projectStatus
        ProjectStatus updatedProjectStatus = projectStatusRepository.findById(projectStatus.getId()).get();
        // Disconnect from session so that the updates on updatedProjectStatus are not directly saved in db
        em.detach(updatedProjectStatus);
        updatedProjectStatus
            .name(UPDATED_NAME)
            .daysToNotification(UPDATED_DAYS_TO_NOTIFICATION)
            .order(UPDATED_ORDER);
        ProjectStatusDTO projectStatusDTO = projectStatusMapper.toDto(updatedProjectStatus);

        restProjectStatusMockMvc.perform(put("/api/project-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectStatus in the database
        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeUpdate);
        ProjectStatus testProjectStatus = projectStatusList.get(projectStatusList.size() - 1);
        assertThat(testProjectStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProjectStatus.getDaysToNotification()).isEqualTo(UPDATED_DAYS_TO_NOTIFICATION);
        assertThat(testProjectStatus.getOrder()).isEqualTo(UPDATED_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectStatus() throws Exception {
        int databaseSizeBeforeUpdate = projectStatusRepository.findAll().size();

        // Create the ProjectStatus
        ProjectStatusDTO projectStatusDTO = projectStatusMapper.toDto(projectStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectStatusMockMvc.perform(put("/api/project-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectStatus in the database
        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectStatus() throws Exception {
        // Initialize the database
        projectStatusRepository.saveAndFlush(projectStatus);

        int databaseSizeBeforeDelete = projectStatusRepository.findAll().size();

        // Delete the projectStatus
        restProjectStatusMockMvc.perform(delete("/api/project-statuses/{id}", projectStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectStatus> projectStatusList = projectStatusRepository.findAll();
        assertThat(projectStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
