package com.astrid.diaspora.web.rest;

import com.astrid.diaspora.ProjectsOverviewApp;
import com.astrid.diaspora.domain.Beneficiary;
import com.astrid.diaspora.domain.User;
import com.astrid.diaspora.repository.BeneficiaryRepository;
import com.astrid.diaspora.service.BeneficiaryService;
import com.astrid.diaspora.service.dto.BeneficiaryDTO;
import com.astrid.diaspora.service.mapper.BeneficiaryMapper;

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

import com.astrid.diaspora.domain.enumeration.BeneficiaryType;
/**
 * Integration tests for the {@link BeneficiaryResource} REST controller.
 */
@SpringBootTest(classes = ProjectsOverviewApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BeneficiaryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BeneficiaryType DEFAULT_TYPE = BeneficiaryType.PRIVATE_PERSON;
    private static final BeneficiaryType UPDATED_TYPE = BeneficiaryType.PUBLIC_INSTITUTION;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private BeneficiaryMapper beneficiaryMapper;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBeneficiaryMockMvc;

    private Beneficiary beneficiary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Beneficiary createEntity(EntityManager em) {
        Beneficiary beneficiary = new Beneficiary()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .address(DEFAULT_ADDRESS)
            .contact(DEFAULT_CONTACT);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        beneficiary.setContactPerson(user);
        return beneficiary;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Beneficiary createUpdatedEntity(EntityManager em) {
        Beneficiary beneficiary = new Beneficiary()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .address(UPDATED_ADDRESS)
            .contact(UPDATED_CONTACT);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        beneficiary.setContactPerson(user);
        return beneficiary;
    }

    @BeforeEach
    public void initTest() {
        beneficiary = createEntity(em);
    }

    @Test
    @Transactional
    public void createBeneficiary() throws Exception {
        int databaseSizeBeforeCreate = beneficiaryRepository.findAll().size();
        // Create the Beneficiary
        BeneficiaryDTO beneficiaryDTO = beneficiaryMapper.toDto(beneficiary);
        restBeneficiaryMockMvc.perform(post("/api/beneficiaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beneficiaryDTO)))
            .andExpect(status().isCreated());

        // Validate the Beneficiary in the database
        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        assertThat(beneficiaryList).hasSize(databaseSizeBeforeCreate + 1);
        Beneficiary testBeneficiary = beneficiaryList.get(beneficiaryList.size() - 1);
        assertThat(testBeneficiary.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBeneficiary.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBeneficiary.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBeneficiary.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createBeneficiaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = beneficiaryRepository.findAll().size();

        // Create the Beneficiary with an existing ID
        beneficiary.setId(1L);
        BeneficiaryDTO beneficiaryDTO = beneficiaryMapper.toDto(beneficiary);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBeneficiaryMockMvc.perform(post("/api/beneficiaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beneficiaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Beneficiary in the database
        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        assertThat(beneficiaryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneficiaryRepository.findAll().size();
        // set the field null
        beneficiary.setName(null);

        // Create the Beneficiary, which fails.
        BeneficiaryDTO beneficiaryDTO = beneficiaryMapper.toDto(beneficiary);


        restBeneficiaryMockMvc.perform(post("/api/beneficiaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beneficiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        assertThat(beneficiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneficiaryRepository.findAll().size();
        // set the field null
        beneficiary.setType(null);

        // Create the Beneficiary, which fails.
        BeneficiaryDTO beneficiaryDTO = beneficiaryMapper.toDto(beneficiary);


        restBeneficiaryMockMvc.perform(post("/api/beneficiaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beneficiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        assertThat(beneficiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBeneficiaries() throws Exception {
        // Initialize the database
        beneficiaryRepository.saveAndFlush(beneficiary);

        // Get all the beneficiaryList
        restBeneficiaryMockMvc.perform(get("/api/beneficiaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beneficiary.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getBeneficiary() throws Exception {
        // Initialize the database
        beneficiaryRepository.saveAndFlush(beneficiary);

        // Get the beneficiary
        restBeneficiaryMockMvc.perform(get("/api/beneficiaries/{id}", beneficiary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(beneficiary.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingBeneficiary() throws Exception {
        // Get the beneficiary
        restBeneficiaryMockMvc.perform(get("/api/beneficiaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBeneficiary() throws Exception {
        // Initialize the database
        beneficiaryRepository.saveAndFlush(beneficiary);

        int databaseSizeBeforeUpdate = beneficiaryRepository.findAll().size();

        // Update the beneficiary
        Beneficiary updatedBeneficiary = beneficiaryRepository.findById(beneficiary.getId()).get();
        // Disconnect from session so that the updates on updatedBeneficiary are not directly saved in db
        em.detach(updatedBeneficiary);
        updatedBeneficiary
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .address(UPDATED_ADDRESS)
            .contact(UPDATED_CONTACT);
        BeneficiaryDTO beneficiaryDTO = beneficiaryMapper.toDto(updatedBeneficiary);

        restBeneficiaryMockMvc.perform(put("/api/beneficiaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beneficiaryDTO)))
            .andExpect(status().isOk());

        // Validate the Beneficiary in the database
        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        assertThat(beneficiaryList).hasSize(databaseSizeBeforeUpdate);
        Beneficiary testBeneficiary = beneficiaryList.get(beneficiaryList.size() - 1);
        assertThat(testBeneficiary.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBeneficiary.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBeneficiary.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBeneficiary.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingBeneficiary() throws Exception {
        int databaseSizeBeforeUpdate = beneficiaryRepository.findAll().size();

        // Create the Beneficiary
        BeneficiaryDTO beneficiaryDTO = beneficiaryMapper.toDto(beneficiary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeneficiaryMockMvc.perform(put("/api/beneficiaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beneficiaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Beneficiary in the database
        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        assertThat(beneficiaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBeneficiary() throws Exception {
        // Initialize the database
        beneficiaryRepository.saveAndFlush(beneficiary);

        int databaseSizeBeforeDelete = beneficiaryRepository.findAll().size();

        // Delete the beneficiary
        restBeneficiaryMockMvc.perform(delete("/api/beneficiaries/{id}", beneficiary.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        assertThat(beneficiaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
