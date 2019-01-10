package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Ensino;
import br.com.jhisolution.ong.control.repository.EnsinoRepository;
import br.com.jhisolution.ong.control.service.EnsinoService;
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
 * Test class for the EnsinoResource REST controller.
 *
 * @see EnsinoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class EnsinoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private EnsinoRepository ensinoRepository;

    @Autowired
    private EnsinoService ensinoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnsinoMockMvc;

    private Ensino ensino;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnsinoResource ensinoResource = new EnsinoResource(ensinoService);
        this.restEnsinoMockMvc = MockMvcBuilders.standaloneSetup(ensinoResource)
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
    public static Ensino createEntity(EntityManager em) {
        Ensino ensino = new Ensino()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return ensino;
    }

    @Before
    public void initTest() {
        ensino = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnsino() throws Exception {
        int databaseSizeBeforeCreate = ensinoRepository.findAll().size();

        // Create the Ensino
        restEnsinoMockMvc.perform(post("/api/ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ensino)))
            .andExpect(status().isCreated());

        // Validate the Ensino in the database
        List<Ensino> ensinoList = ensinoRepository.findAll();
        assertThat(ensinoList).hasSize(databaseSizeBeforeCreate + 1);
        Ensino testEnsino = ensinoList.get(ensinoList.size() - 1);
        assertThat(testEnsino.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testEnsino.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createEnsinoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ensinoRepository.findAll().size();

        // Create the Ensino with an existing ID
        ensino.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnsinoMockMvc.perform(post("/api/ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ensino)))
            .andExpect(status().isBadRequest());

        // Validate the Ensino in the database
        List<Ensino> ensinoList = ensinoRepository.findAll();
        assertThat(ensinoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = ensinoRepository.findAll().size();
        // set the field null
        ensino.setNome(null);

        // Create the Ensino, which fails.

        restEnsinoMockMvc.perform(post("/api/ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ensino)))
            .andExpect(status().isBadRequest());

        List<Ensino> ensinoList = ensinoRepository.findAll();
        assertThat(ensinoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnsinos() throws Exception {
        // Initialize the database
        ensinoRepository.saveAndFlush(ensino);

        // Get all the ensinoList
        restEnsinoMockMvc.perform(get("/api/ensinos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ensino.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getEnsino() throws Exception {
        // Initialize the database
        ensinoRepository.saveAndFlush(ensino);

        // Get the ensino
        restEnsinoMockMvc.perform(get("/api/ensinos/{id}", ensino.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ensino.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnsino() throws Exception {
        // Get the ensino
        restEnsinoMockMvc.perform(get("/api/ensinos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnsino() throws Exception {
        // Initialize the database
        ensinoService.save(ensino);

        int databaseSizeBeforeUpdate = ensinoRepository.findAll().size();

        // Update the ensino
        Ensino updatedEnsino = ensinoRepository.findById(ensino.getId()).get();
        // Disconnect from session so that the updates on updatedEnsino are not directly saved in db
        em.detach(updatedEnsino);
        updatedEnsino
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restEnsinoMockMvc.perform(put("/api/ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnsino)))
            .andExpect(status().isOk());

        // Validate the Ensino in the database
        List<Ensino> ensinoList = ensinoRepository.findAll();
        assertThat(ensinoList).hasSize(databaseSizeBeforeUpdate);
        Ensino testEnsino = ensinoList.get(ensinoList.size() - 1);
        assertThat(testEnsino.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testEnsino.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingEnsino() throws Exception {
        int databaseSizeBeforeUpdate = ensinoRepository.findAll().size();

        // Create the Ensino

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnsinoMockMvc.perform(put("/api/ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ensino)))
            .andExpect(status().isBadRequest());

        // Validate the Ensino in the database
        List<Ensino> ensinoList = ensinoRepository.findAll();
        assertThat(ensinoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnsino() throws Exception {
        // Initialize the database
        ensinoService.save(ensino);

        int databaseSizeBeforeDelete = ensinoRepository.findAll().size();

        // Get the ensino
        restEnsinoMockMvc.perform(delete("/api/ensinos/{id}", ensino.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ensino> ensinoList = ensinoRepository.findAll();
        assertThat(ensinoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ensino.class);
        Ensino ensino1 = new Ensino();
        ensino1.setId(1L);
        Ensino ensino2 = new Ensino();
        ensino2.setId(ensino1.getId());
        assertThat(ensino1).isEqualTo(ensino2);
        ensino2.setId(2L);
        assertThat(ensino1).isNotEqualTo(ensino2);
        ensino1.setId(null);
        assertThat(ensino1).isNotEqualTo(ensino2);
    }
}
