package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.EstadoCivil;
import br.com.jhisolution.ong.control.repository.EstadoCivilRepository;
import br.com.jhisolution.ong.control.service.EstadoCivilService;
import br.com.jhisolution.ong.control.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EstadoCivilResource REST controller.
 *
 * @see EstadoCivilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class EstadoCivilResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    @Autowired
    private EstadoCivilService estadoCivilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEstadoCivilMockMvc;

    private EstadoCivil estadoCivil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadoCivilResource estadoCivilResource = new EstadoCivilResource(estadoCivilService);
        this.restEstadoCivilMockMvc = MockMvcBuilders.standaloneSetup(estadoCivilResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadoCivil createEntity(EntityManager em) {
        EstadoCivil estadoCivil = new EstadoCivil()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return estadoCivil;
    }

    @Before
    public void initTest() {
        estadoCivil = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadoCivil() throws Exception {
        int databaseSizeBeforeCreate = estadoCivilRepository.findAll().size();

        // Create the EstadoCivil
        restEstadoCivilMockMvc.perform(post("/api/estado-civils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoCivil)))
            .andExpect(status().isCreated());

        // Validate the EstadoCivil in the database
        List<EstadoCivil> estadoCivilList = estadoCivilRepository.findAll();
        assertThat(estadoCivilList).hasSize(databaseSizeBeforeCreate + 1);
        EstadoCivil testEstadoCivil = estadoCivilList.get(estadoCivilList.size() - 1);
        assertThat(testEstadoCivil.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testEstadoCivil.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createEstadoCivilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadoCivilRepository.findAll().size();

        // Create the EstadoCivil with an existing ID
        estadoCivil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadoCivilMockMvc.perform(post("/api/estado-civils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoCivil)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoCivil in the database
        List<EstadoCivil> estadoCivilList = estadoCivilRepository.findAll();
        assertThat(estadoCivilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoCivilRepository.findAll().size();
        // set the field null
        estadoCivil.setNome(null);

        // Create the EstadoCivil, which fails.

        restEstadoCivilMockMvc.perform(post("/api/estado-civils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoCivil)))
            .andExpect(status().isBadRequest());

        List<EstadoCivil> estadoCivilList = estadoCivilRepository.findAll();
        assertThat(estadoCivilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadoCivils() throws Exception {
        // Initialize the database
        estadoCivilRepository.saveAndFlush(estadoCivil);

        // Get all the estadoCivilList
        restEstadoCivilMockMvc.perform(get("/api/estado-civils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoCivil.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getEstadoCivil() throws Exception {
        // Initialize the database
        estadoCivilRepository.saveAndFlush(estadoCivil);

        // Get the estadoCivil
        restEstadoCivilMockMvc.perform(get("/api/estado-civils/{id}", estadoCivil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadoCivil.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadoCivil() throws Exception {
        // Get the estadoCivil
        restEstadoCivilMockMvc.perform(get("/api/estado-civils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadoCivil() throws Exception {
        // Initialize the database
        estadoCivilService.save(estadoCivil);

        int databaseSizeBeforeUpdate = estadoCivilRepository.findAll().size();

        // Update the estadoCivil
        EstadoCivil updatedEstadoCivil = estadoCivilRepository.findById(estadoCivil.getId()).get();
        // Disconnect from session so that the updates on updatedEstadoCivil are not directly saved in db
        em.detach(updatedEstadoCivil);
        updatedEstadoCivil
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restEstadoCivilMockMvc.perform(put("/api/estado-civils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEstadoCivil)))
            .andExpect(status().isOk());

        // Validate the EstadoCivil in the database
        List<EstadoCivil> estadoCivilList = estadoCivilRepository.findAll();
        assertThat(estadoCivilList).hasSize(databaseSizeBeforeUpdate);
        EstadoCivil testEstadoCivil = estadoCivilList.get(estadoCivilList.size() - 1);
        assertThat(testEstadoCivil.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testEstadoCivil.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadoCivil() throws Exception {
        int databaseSizeBeforeUpdate = estadoCivilRepository.findAll().size();

        // Create the EstadoCivil

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadoCivilMockMvc.perform(put("/api/estado-civils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoCivil)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoCivil in the database
        List<EstadoCivil> estadoCivilList = estadoCivilRepository.findAll();
        assertThat(estadoCivilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadoCivil() throws Exception {
        // Initialize the database
        estadoCivilService.save(estadoCivil);

        int databaseSizeBeforeDelete = estadoCivilRepository.findAll().size();

        // Get the estadoCivil
        restEstadoCivilMockMvc.perform(delete("/api/estado-civils/{id}", estadoCivil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EstadoCivil> estadoCivilList = estadoCivilRepository.findAll();
        assertThat(estadoCivilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoCivil.class);
        EstadoCivil estadoCivil1 = new EstadoCivil();
        estadoCivil1.setId(1L);
        EstadoCivil estadoCivil2 = new EstadoCivil();
        estadoCivil2.setId(estadoCivil1.getId());
        assertThat(estadoCivil1).isEqualTo(estadoCivil2);
        estadoCivil2.setId(2L);
        assertThat(estadoCivil1).isNotEqualTo(estadoCivil2);
        estadoCivil1.setId(null);
        assertThat(estadoCivil1).isNotEqualTo(estadoCivil2);
    }
}
