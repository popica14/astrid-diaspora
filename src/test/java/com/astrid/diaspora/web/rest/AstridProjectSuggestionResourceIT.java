package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.ProjectsOverviewApp;
import com.astrid.diaspora.domain.AstridProjectSuggestion;
import com.astrid.diaspora.domain.User;
import com.astrid.diaspora.repository.AstridProjectSuggestionRepository;
import com.astrid.diaspora.service.AstridProjectSuggestionService;
import com.astrid.diaspora.service.dto.AstridProjectSuggestionDTO;
import com.astrid.diaspora.service.mapper.AstridProjectSuggestionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AstridProjectSuggestionResource} REST controller.
 */
@SpringBootTest(classes = ProjectsOverviewApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AstridProjectSuggestionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DOCUMENTATION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOCUMENTATION = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DOCUMENTATION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOCUMENTATION_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_GOAL = "AAAAAAAAAA";
    private static final String UPDATED_GOAL = "BBBBBBBBBB";

    @Autowired
    private AstridProjectSuggestionRepository astridProjectSuggestionRepository;

    @Autowired
    private AstridProjectSuggestionMapper astridProjectSuggestionMapper;

    @Autowired
    private AstridProjectSuggestionService astridProjectSuggestionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAstridProjectSuggestionMockMvc;

    private AstridProjectSuggestion astridProjectSuggestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AstridProjectSuggestion createEntity(EntityManager em) {
        AstridProjectSuggestion astridProjectSuggestion = new AstridProjectSuggestion()
            .name(DEFAULT_NAME)
            .shortDescription(DEFAULT_SHORT_DESCRIPTION)
            .documentation(DEFAULT_DOCUMENTATION)
            .documentationContentType(DEFAULT_DOCUMENTATION_CONTENT_TYPE)
            .goal(DEFAULT_GOAL);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        astridProjectSuggestion.setInitiator(user);
        return astridProjectSuggestion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AstridProjectSuggestion createUpdatedEntity(EntityManager em) {
        AstridProjectSuggestion astridProjectSuggestion = new AstridProjectSuggestion()
            .name(UPDATED_NAME)
            .shortDescription(UPDATED_SHORT_DESCRIPTION)
            .documentation(UPDATED_DOCUMENTATION)
            .documentationContentType(UPDATED_DOCUMENTATION_CONTENT_TYPE)
            .goal(UPDATED_GOAL);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        astridProjectSuggestion.setInitiator(user);
        return astridProjectSuggestion;
    }

    @BeforeEach
    public void initTest() {
        astridProjectSuggestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createAstridProjectSuggestion() throws Exception {
        int databaseSizeBeforeCreate = astridProjectSuggestionRepository.findAll().size();
        // Create the AstridProjectSuggestion
        AstridProjectSuggestionDTO astridProjectSuggestionDTO = astridProjectSuggestionMapper.toDto(astridProjectSuggestion);
        restAstridProjectSuggestionMockMvc.perform(post("/api/astrid-project-suggestions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectSuggestionDTO)))
            .andExpect(status().isCreated());

        // Validate the AstridProjectSuggestion in the database
        List<AstridProjectSuggestion> astridProjectSuggestionList = astridProjectSuggestionRepository.findAll();
        assertThat(astridProjectSuggestionList).hasSize(databaseSizeBeforeCreate + 1);
        AstridProjectSuggestion testAstridProjectSuggestion = astridProjectSuggestionList.get(astridProjectSuggestionList.size() - 1);
        assertThat(testAstridProjectSuggestion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAstridProjectSuggestion.getShortDescription()).isEqualTo(DEFAULT_SHORT_DESCRIPTION);
        assertThat(testAstridProjectSuggestion.getDocumentation()).isEqualTo(DEFAULT_DOCUMENTATION);
        assertThat(testAstridProjectSuggestion.getDocumentationContentType()).isEqualTo(DEFAULT_DOCUMENTATION_CONTENT_TYPE);
        assertThat(testAstridProjectSuggestion.getGoal()).isEqualTo(DEFAULT_GOAL);
    }

    @Test
    @Transactional
    public void createAstridProjectSuggestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = astridProjectSuggestionRepository.findAll().size();

        // Create the AstridProjectSuggestion with an existing ID
        astridProjectSuggestion.setId(1L);
        AstridProjectSuggestionDTO astridProjectSuggestionDTO = astridProjectSuggestionMapper.toDto(astridProjectSuggestion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAstridProjectSuggestionMockMvc.perform(post("/api/astrid-project-suggestions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectSuggestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AstridProjectSuggestion in the database
        List<AstridProjectSuggestion> astridProjectSuggestionList = astridProjectSuggestionRepository.findAll();
        assertThat(astridProjectSuggestionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = astridProjectSuggestionRepository.findAll().size();
        // set the field null
        astridProjectSuggestion.setName(null);

        // Create the AstridProjectSuggestion, which fails.
        AstridProjectSuggestionDTO astridProjectSuggestionDTO = astridProjectSuggestionMapper.toDto(astridProjectSuggestion);


        restAstridProjectSuggestionMockMvc.perform(post("/api/astrid-project-suggestions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectSuggestionDTO)))
            .andExpect(status().isBadRequest());

        List<AstridProjectSuggestion> astridProjectSuggestionList = astridProjectSuggestionRepository.findAll();
        assertThat(astridProjectSuggestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAstridProjectSuggestions() throws Exception {
        // Initialize the database
        astridProjectSuggestionRepository.saveAndFlush(astridProjectSuggestion);

        // Get all the astridProjectSuggestionList
        restAstridProjectSuggestionMockMvc.perform(get("/api/astrid-project-suggestions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(astridProjectSuggestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shortDescription").value(hasItem(DEFAULT_SHORT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].documentationContentType").value(hasItem(DEFAULT_DOCUMENTATION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentation").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENTATION))))
            .andExpect(jsonPath("$.[*].goal").value(hasItem(DEFAULT_GOAL)));
    }
    
    @Test
    @Transactional
    public void getAstridProjectSuggestion() throws Exception {
        // Initialize the database
        astridProjectSuggestionRepository.saveAndFlush(astridProjectSuggestion);

        // Get the astridProjectSuggestion
        restAstridProjectSuggestionMockMvc.perform(get("/api/astrid-project-suggestions/{id}", astridProjectSuggestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(astridProjectSuggestion.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.shortDescription").value(DEFAULT_SHORT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.documentationContentType").value(DEFAULT_DOCUMENTATION_CONTENT_TYPE))
            .andExpect(jsonPath("$.documentation").value(Base64Utils.encodeToString(DEFAULT_DOCUMENTATION)))
            .andExpect(jsonPath("$.goal").value(DEFAULT_GOAL));
    }
    @Test
    @Transactional
    public void getNonExistingAstridProjectSuggestion() throws Exception {
        // Get the astridProjectSuggestion
        restAstridProjectSuggestionMockMvc.perform(get("/api/astrid-project-suggestions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAstridProjectSuggestion() throws Exception {
        // Initialize the database
        astridProjectSuggestionRepository.saveAndFlush(astridProjectSuggestion);

        int databaseSizeBeforeUpdate = astridProjectSuggestionRepository.findAll().size();

        // Update the astridProjectSuggestion
        AstridProjectSuggestion updatedAstridProjectSuggestion = astridProjectSuggestionRepository.findById(astridProjectSuggestion.getId()).get();
        // Disconnect from session so that the updates on updatedAstridProjectSuggestion are not directly saved in db
        em.detach(updatedAstridProjectSuggestion);
        updatedAstridProjectSuggestion
            .name(UPDATED_NAME)
            .shortDescription(UPDATED_SHORT_DESCRIPTION)
            .documentation(UPDATED_DOCUMENTATION)
            .documentationContentType(UPDATED_DOCUMENTATION_CONTENT_TYPE)
            .goal(UPDATED_GOAL);
        AstridProjectSuggestionDTO astridProjectSuggestionDTO = astridProjectSuggestionMapper.toDto(updatedAstridProjectSuggestion);

        restAstridProjectSuggestionMockMvc.perform(put("/api/astrid-project-suggestions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectSuggestionDTO)))
            .andExpect(status().isOk());

        // Validate the AstridProjectSuggestion in the database
        List<AstridProjectSuggestion> astridProjectSuggestionList = astridProjectSuggestionRepository.findAll();
        assertThat(astridProjectSuggestionList).hasSize(databaseSizeBeforeUpdate);
        AstridProjectSuggestion testAstridProjectSuggestion = astridProjectSuggestionList.get(astridProjectSuggestionList.size() - 1);
        assertThat(testAstridProjectSuggestion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAstridProjectSuggestion.getShortDescription()).isEqualTo(UPDATED_SHORT_DESCRIPTION);
        assertThat(testAstridProjectSuggestion.getDocumentation()).isEqualTo(UPDATED_DOCUMENTATION);
        assertThat(testAstridProjectSuggestion.getDocumentationContentType()).isEqualTo(UPDATED_DOCUMENTATION_CONTENT_TYPE);
        assertThat(testAstridProjectSuggestion.getGoal()).isEqualTo(UPDATED_GOAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAstridProjectSuggestion() throws Exception {
        int databaseSizeBeforeUpdate = astridProjectSuggestionRepository.findAll().size();

        // Create the AstridProjectSuggestion
        AstridProjectSuggestionDTO astridProjectSuggestionDTO = astridProjectSuggestionMapper.toDto(astridProjectSuggestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAstridProjectSuggestionMockMvc.perform(put("/api/astrid-project-suggestions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridProjectSuggestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AstridProjectSuggestion in the database
        List<AstridProjectSuggestion> astridProjectSuggestionList = astridProjectSuggestionRepository.findAll();
        assertThat(astridProjectSuggestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAstridProjectSuggestion() throws Exception {
        // Initialize the database
        astridProjectSuggestionRepository.saveAndFlush(astridProjectSuggestion);

        int databaseSizeBeforeDelete = astridProjectSuggestionRepository.findAll().size();

        // Delete the astridProjectSuggestion
        restAstridProjectSuggestionMockMvc.perform(delete("/api/astrid-project-suggestions/{id}", astridProjectSuggestion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AstridProjectSuggestion> astridProjectSuggestionList = astridProjectSuggestionRepository.findAll();
        assertThat(astridProjectSuggestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
