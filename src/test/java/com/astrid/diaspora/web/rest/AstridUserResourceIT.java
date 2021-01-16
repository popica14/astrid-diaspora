package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.ProjectsOverviewApp;
import com.astrid.diaspora.domain.AstridUser;
import com.astrid.diaspora.repository.AstridUserRepository;
import com.astrid.diaspora.service.AstridUserService;
import com.astrid.diaspora.service.dto.AstridUserDTO;
import com.astrid.diaspora.service.mapper.AstridUserMapper;

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
 * Integration tests for the {@link AstridUserResource} REST controller.
 */
@SpringBootTest(classes = ProjectsOverviewApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AstridUserResourceIT {

    private static final String DEFAULT_PHONE_NUMBER = "+18 689) 128-5516";
    private static final String UPDATED_PHONE_NUMBER = "(489-981-6614";

    @Autowired
    private AstridUserRepository astridUserRepository;

    @Autowired
    private AstridUserMapper astridUserMapper;

    @Autowired
    private AstridUserService astridUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAstridUserMockMvc;

    private AstridUser astridUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AstridUser createEntity(EntityManager em) {
        AstridUser astridUser = new AstridUser()
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return astridUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AstridUser createUpdatedEntity(EntityManager em) {
        AstridUser astridUser = new AstridUser()
            .phoneNumber(UPDATED_PHONE_NUMBER);
        return astridUser;
    }

    @BeforeEach
    public void initTest() {
        astridUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createAstridUser() throws Exception {
        int databaseSizeBeforeCreate = astridUserRepository.findAll().size();
        // Create the AstridUser
        AstridUserDTO astridUserDTO = astridUserMapper.toDto(astridUser);
        restAstridUserMockMvc.perform(post("/api/astrid-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridUserDTO)))
            .andExpect(status().isCreated());

        // Validate the AstridUser in the database
        List<AstridUser> astridUserList = astridUserRepository.findAll();
        assertThat(astridUserList).hasSize(databaseSizeBeforeCreate + 1);
        AstridUser testAstridUser = astridUserList.get(astridUserList.size() - 1);
        assertThat(testAstridUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void createAstridUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = astridUserRepository.findAll().size();

        // Create the AstridUser with an existing ID
        astridUser.setId(1L);
        AstridUserDTO astridUserDTO = astridUserMapper.toDto(astridUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAstridUserMockMvc.perform(post("/api/astrid-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AstridUser in the database
        List<AstridUser> astridUserList = astridUserRepository.findAll();
        assertThat(astridUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = astridUserRepository.findAll().size();
        // set the field null
        astridUser.setPhoneNumber(null);

        // Create the AstridUser, which fails.
        AstridUserDTO astridUserDTO = astridUserMapper.toDto(astridUser);


        restAstridUserMockMvc.perform(post("/api/astrid-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridUserDTO)))
            .andExpect(status().isBadRequest());

        List<AstridUser> astridUserList = astridUserRepository.findAll();
        assertThat(astridUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAstridUsers() throws Exception {
        // Initialize the database
        astridUserRepository.saveAndFlush(astridUser);

        // Get all the astridUserList
        restAstridUserMockMvc.perform(get("/api/astrid-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(astridUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getAstridUser() throws Exception {
        // Initialize the database
        astridUserRepository.saveAndFlush(astridUser);

        // Get the astridUser
        restAstridUserMockMvc.perform(get("/api/astrid-users/{id}", astridUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(astridUser.getId().intValue()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingAstridUser() throws Exception {
        // Get the astridUser
        restAstridUserMockMvc.perform(get("/api/astrid-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAstridUser() throws Exception {
        // Initialize the database
        astridUserRepository.saveAndFlush(astridUser);

        int databaseSizeBeforeUpdate = astridUserRepository.findAll().size();

        // Update the astridUser
        AstridUser updatedAstridUser = astridUserRepository.findById(astridUser.getId()).get();
        // Disconnect from session so that the updates on updatedAstridUser are not directly saved in db
        em.detach(updatedAstridUser);
        updatedAstridUser
            .phoneNumber(UPDATED_PHONE_NUMBER);
        AstridUserDTO astridUserDTO = astridUserMapper.toDto(updatedAstridUser);

        restAstridUserMockMvc.perform(put("/api/astrid-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridUserDTO)))
            .andExpect(status().isOk());

        // Validate the AstridUser in the database
        List<AstridUser> astridUserList = astridUserRepository.findAll();
        assertThat(astridUserList).hasSize(databaseSizeBeforeUpdate);
        AstridUser testAstridUser = astridUserList.get(astridUserList.size() - 1);
        assertThat(testAstridUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingAstridUser() throws Exception {
        int databaseSizeBeforeUpdate = astridUserRepository.findAll().size();

        // Create the AstridUser
        AstridUserDTO astridUserDTO = astridUserMapper.toDto(astridUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAstridUserMockMvc.perform(put("/api/astrid-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(astridUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AstridUser in the database
        List<AstridUser> astridUserList = astridUserRepository.findAll();
        assertThat(astridUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAstridUser() throws Exception {
        // Initialize the database
        astridUserRepository.saveAndFlush(astridUser);

        int databaseSizeBeforeDelete = astridUserRepository.findAll().size();

        // Delete the astridUser
        restAstridUserMockMvc.perform(delete("/api/astrid-users/{id}", astridUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AstridUser> astridUserList = astridUserRepository.findAll();
        assertThat(astridUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
