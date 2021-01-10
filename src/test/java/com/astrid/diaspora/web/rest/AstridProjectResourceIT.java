package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.ProjectsOverviewApp;
import com.astrid.diaspora.domain.AstridProject;
import com.astrid.diaspora.domain.User;
import com.astrid.diaspora.repository.AstridProjectRepository;
import com.astrid.diaspora.service.AstridProjectService;
import com.astrid.diaspora.service.dto.AstridProjectDTO;
import com.astrid.diaspora.service.mapper.AstridProjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.astrid.diaspora.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AstridProjectResource} REST controller.
 */
@SpringBootTest(classes = ProjectsOverviewApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class AstridProjectResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DOCUMENTATION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOCUMENTATION = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DOCUMENTATION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOCUMENTATION_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NEEDED_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_NEEDED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUPPORTERS = 1;
    private static final Integer UPDATED_SUPPORTERS = 2;

    private static final String DEFAULT_GOAL = "AAAAAAAAAA";
    private static final String UPDATED_GOAL = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_REASON = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_REASON = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_STATUS_DEADLINE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_STATUS_DEADLINE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private AstridProjectRepository astridProjectRepository;

    @Mock
    private AstridProjectRepository astridProjectRepositoryMock;

    @Autowired
    private AstridProjectMapper astridProjectMapper;

    @Mock
    private AstridProjectService astridProjectServiceMock;

    @Autowired
    private AstridProjectService astridProjectService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAstridProjectMockMvc;

    private AstridProject astridProject;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AstridProject createEntity(EntityManager em) {
        AstridProject astridProject = new AstridProject()
            .name(DEFAULT_NAME)
            .shortDescription(DEFAULT_SHORT_DESCRIPTION)
            .documentation(DEFAULT_DOCUMENTATION)
            .documentationContentType(DEFAULT_DOCUMENTATION_CONTENT_TYPE)
            .neededAmount(DEFAULT_NEEDED_AMOUNT)
            .currentAmount(DEFAULT_CURRENT_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .supporters(DEFAULT_SUPPORTERS)
            .goal(DEFAULT_GOAL)
            .statusReason(DEFAULT_STATUS_REASON)
            .statusDeadline(DEFAULT_STATUS_DEADLINE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        astridProject.setResponsible(user);
        return astridProject;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AstridProject createUpdatedEntity(EntityManager em) {
        AstridProject astridProject = new AstridProject()
            .name(UPDATED_NAME)
            .shortDescription(UPDATED_SHORT_DESCRIPTION)
            .documentation(UPDATED_DOCUMENTATION)
            .documentationContentType(UPDATED_DOCUMENTATION_CONTENT_TYPE)
            .neededAmount(UPDATED_NEEDED_AMOUNT)
            .currentAmount(UPDATED_CURRENT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .supporters(UPDATED_SUPPORTERS)
            .goal(UPDATED_GOAL)
            .statusReason(UPDATED_STATUS_REASON)
            .statusDeadline(UPDATED_STATUS_DEADLINE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        astridProject.setResponsible(user);
        return astridProject;
    }

    @BeforeEach
    public void initTest() {
        astridProject = createEntity(em);
    }

    @Test
    @Transactional
    public void createAstridProject() throws Exception {
        int databaseSizeBeforeCreate = astridProjectRepository.findAll().size();
        // Create the AstridProject
        AstridProjectDTO astridProjectDTO = astridProjectMapper.toDto(astridProject);
        restAstridProjectMockMvc.perform(post("/api/astrid-projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectDTO)))
            .andExpect(status().isCreated());

        // Validate the AstridProject in the database
        List<AstridProject> astridProjectList = astridProjectRepository.findAll();
        assertThat(astridProjectList).hasSize(databaseSizeBeforeCreate + 1);
        AstridProject testAstridProject = astridProjectList.get(astridProjectList.size() - 1);
        assertThat(testAstridProject.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAstridProject.getShortDescription()).isEqualTo(DEFAULT_SHORT_DESCRIPTION);
        assertThat(testAstridProject.getDocumentation()).isEqualTo(DEFAULT_DOCUMENTATION);
        assertThat(testAstridProject.getDocumentationContentType()).isEqualTo(DEFAULT_DOCUMENTATION_CONTENT_TYPE);
        assertThat(testAstridProject.getNeededAmount()).isEqualTo(DEFAULT_NEEDED_AMOUNT);
        assertThat(testAstridProject.getCurrentAmount()).isEqualTo(DEFAULT_CURRENT_AMOUNT);
        assertThat(testAstridProject.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testAstridProject.getSupporters()).isEqualTo(DEFAULT_SUPPORTERS);
        assertThat(testAstridProject.getGoal()).isEqualTo(DEFAULT_GOAL);
        assertThat(testAstridProject.getStatusReason()).isEqualTo(DEFAULT_STATUS_REASON);
        assertThat(testAstridProject.getStatusDeadline()).isEqualTo(DEFAULT_STATUS_DEADLINE);
    }

    @Test
    @Transactional
    public void createAstridProjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = astridProjectRepository.findAll().size();

        // Create the AstridProject with an existing ID
        astridProject.setId(1L);
        AstridProjectDTO astridProjectDTO = astridProjectMapper.toDto(astridProject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAstridProjectMockMvc.perform(post("/api/astrid-projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AstridProject in the database
        List<AstridProject> astridProjectList = astridProjectRepository.findAll();
        assertThat(astridProjectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = astridProjectRepository.findAll().size();
        // set the field null
        astridProject.setName(null);

        // Create the AstridProject, which fails.
        AstridProjectDTO astridProjectDTO = astridProjectMapper.toDto(astridProject);


        restAstridProjectMockMvc.perform(post("/api/astrid-projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectDTO)))
            .andExpect(status().isBadRequest());

        List<AstridProject> astridProjectList = astridProjectRepository.findAll();
        assertThat(astridProjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAstridProjects() throws Exception {
        // Initialize the database
        astridProjectRepository.saveAndFlush(astridProject);

        // Get all the astridProjectList
        restAstridProjectMockMvc.perform(get("/api/astrid-projects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(astridProject.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shortDescription").value(hasItem(DEFAULT_SHORT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].documentationContentType").value(hasItem(DEFAULT_DOCUMENTATION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentation").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENTATION))))
            .andExpect(jsonPath("$.[*].neededAmount").value(hasItem(DEFAULT_NEEDED_AMOUNT)))
            .andExpect(jsonPath("$.[*].currentAmount").value(hasItem(DEFAULT_CURRENT_AMOUNT)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].supporters").value(hasItem(DEFAULT_SUPPORTERS)))
            .andExpect(jsonPath("$.[*].goal").value(hasItem(DEFAULT_GOAL)))
            .andExpect(jsonPath("$.[*].statusReason").value(hasItem(DEFAULT_STATUS_REASON)))
            .andExpect(jsonPath("$.[*].statusDeadline").value(hasItem(sameInstant(DEFAULT_STATUS_DEADLINE))));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllAstridProjectsWithEagerRelationshipsIsEnabled() throws Exception {
        when(astridProjectServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAstridProjectMockMvc.perform(get("/api/astrid-projects?eagerload=true"))
            .andExpect(status().isOk());

        verify(astridProjectServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllAstridProjectsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(astridProjectServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAstridProjectMockMvc.perform(get("/api/astrid-projects?eagerload=true"))
            .andExpect(status().isOk());

        verify(astridProjectServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAstridProject() throws Exception {
        // Initialize the database
        astridProjectRepository.saveAndFlush(astridProject);

        // Get the astridProject
        restAstridProjectMockMvc.perform(get("/api/astrid-projects/{id}", astridProject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(astridProject.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.shortDescription").value(DEFAULT_SHORT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.documentationContentType").value(DEFAULT_DOCUMENTATION_CONTENT_TYPE))
            .andExpect(jsonPath("$.documentation").value(Base64Utils.encodeToString(DEFAULT_DOCUMENTATION)))
            .andExpect(jsonPath("$.neededAmount").value(DEFAULT_NEEDED_AMOUNT))
            .andExpect(jsonPath("$.currentAmount").value(DEFAULT_CURRENT_AMOUNT))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.supporters").value(DEFAULT_SUPPORTERS))
            .andExpect(jsonPath("$.goal").value(DEFAULT_GOAL))
            .andExpect(jsonPath("$.statusReason").value(DEFAULT_STATUS_REASON))
            .andExpect(jsonPath("$.statusDeadline").value(sameInstant(DEFAULT_STATUS_DEADLINE)));
    }
    @Test
    @Transactional
    public void getNonExistingAstridProject() throws Exception {
        // Get the astridProject
        restAstridProjectMockMvc.perform(get("/api/astrid-projects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAstridProject() throws Exception {
        // Initialize the database
        astridProjectRepository.saveAndFlush(astridProject);

        int databaseSizeBeforeUpdate = astridProjectRepository.findAll().size();

        // Update the astridProject
        AstridProject updatedAstridProject = astridProjectRepository.findById(astridProject.getId()).get();
        // Disconnect from session so that the updates on updatedAstridProject are not directly saved in db
        em.detach(updatedAstridProject);
        updatedAstridProject
            .name(UPDATED_NAME)
            .shortDescription(UPDATED_SHORT_DESCRIPTION)
            .documentation(UPDATED_DOCUMENTATION)
            .documentationContentType(UPDATED_DOCUMENTATION_CONTENT_TYPE)
            .neededAmount(UPDATED_NEEDED_AMOUNT)
            .currentAmount(UPDATED_CURRENT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .supporters(UPDATED_SUPPORTERS)
            .goal(UPDATED_GOAL)
            .statusReason(UPDATED_STATUS_REASON)
            .statusDeadline(UPDATED_STATUS_DEADLINE);
        AstridProjectDTO astridProjectDTO = astridProjectMapper.toDto(updatedAstridProject);

        restAstridProjectMockMvc.perform(put("/api/astrid-projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectDTO)))
            .andExpect(status().isOk());

        // Validate the AstridProject in the database
        List<AstridProject> astridProjectList = astridProjectRepository.findAll();
        assertThat(astridProjectList).hasSize(databaseSizeBeforeUpdate);
        AstridProject testAstridProject = astridProjectList.get(astridProjectList.size() - 1);
        assertThat(testAstridProject.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAstridProject.getShortDescription()).isEqualTo(UPDATED_SHORT_DESCRIPTION);
        assertThat(testAstridProject.getDocumentation()).isEqualTo(UPDATED_DOCUMENTATION);
        assertThat(testAstridProject.getDocumentationContentType()).isEqualTo(UPDATED_DOCUMENTATION_CONTENT_TYPE);
        assertThat(testAstridProject.getNeededAmount()).isEqualTo(UPDATED_NEEDED_AMOUNT);
        assertThat(testAstridProject.getCurrentAmount()).isEqualTo(UPDATED_CURRENT_AMOUNT);
        assertThat(testAstridProject.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testAstridProject.getSupporters()).isEqualTo(UPDATED_SUPPORTERS);
        assertThat(testAstridProject.getGoal()).isEqualTo(UPDATED_GOAL);
        assertThat(testAstridProject.getStatusReason()).isEqualTo(UPDATED_STATUS_REASON);
        assertThat(testAstridProject.getStatusDeadline()).isEqualTo(UPDATED_STATUS_DEADLINE);
    }

    @Test
    @Transactional
    public void updateNonExistingAstridProject() throws Exception {
        int databaseSizeBeforeUpdate = astridProjectRepository.findAll().size();

        // Create the AstridProject
        AstridProjectDTO astridProjectDTO = astridProjectMapper.toDto(astridProject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAstridProjectMockMvc.perform(put("/api/astrid-projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AstridProject in the database
        List<AstridProject> astridProjectList = astridProjectRepository.findAll();
        assertThat(astridProjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAstridProject() throws Exception {
        // Initialize the database
        astridProjectRepository.saveAndFlush(astridProject);

        int databaseSizeBeforeDelete = astridProjectRepository.findAll().size();

        // Delete the astridProject
        restAstridProjectMockMvc.perform(delete("/api/astrid-projects/{id}", astridProject.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AstridProject> astridProjectList = astridProjectRepository.findAll();
        assertThat(astridProjectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
