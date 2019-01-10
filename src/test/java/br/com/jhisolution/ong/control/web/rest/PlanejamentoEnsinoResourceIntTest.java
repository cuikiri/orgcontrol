package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.PlanejamentoEnsino;
import br.com.jhisolution.ong.control.repository.PlanejamentoEnsinoRepository;
import br.com.jhisolution.ong.control.service.PlanejamentoEnsinoService;
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
 * Test class for the PlanejamentoEnsinoResource REST controller.
 *
 * @see PlanejamentoEnsinoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class PlanejamentoEnsinoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBJETIVO = "AAAAAAAAAA";
    private static final String UPDATED_OBJETIVO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private PlanejamentoEnsinoRepository planejamentoEnsinoRepository;

    @Autowired
    private PlanejamentoEnsinoService planejamentoEnsinoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlanejamentoEnsinoMockMvc;

    private PlanejamentoEnsino planejamentoEnsino;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanejamentoEnsinoResource planejamentoEnsinoResource = new PlanejamentoEnsinoResource(planejamentoEnsinoService);
        this.restPlanejamentoEnsinoMockMvc = MockMvcBuilders.standaloneSetup(planejamentoEnsinoResource)
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
    public static PlanejamentoEnsino createEntity(EntityManager em) {
        PlanejamentoEnsino planejamentoEnsino = new PlanejamentoEnsino()
            .nome(DEFAULT_NOME)
            .objetivo(DEFAULT_OBJETIVO)
            .obs(DEFAULT_OBS);
        return planejamentoEnsino;
    }

    @Before
    public void initTest() {
        planejamentoEnsino = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanejamentoEnsino() throws Exception {
        int databaseSizeBeforeCreate = planejamentoEnsinoRepository.findAll().size();

        // Create the PlanejamentoEnsino
        restPlanejamentoEnsinoMockMvc.perform(post("/api/planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoEnsino)))
            .andExpect(status().isCreated());

        // Validate the PlanejamentoEnsino in the database
        List<PlanejamentoEnsino> planejamentoEnsinoList = planejamentoEnsinoRepository.findAll();
        assertThat(planejamentoEnsinoList).hasSize(databaseSizeBeforeCreate + 1);
        PlanejamentoEnsino testPlanejamentoEnsino = planejamentoEnsinoList.get(planejamentoEnsinoList.size() - 1);
        assertThat(testPlanejamentoEnsino.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPlanejamentoEnsino.getObjetivo()).isEqualTo(DEFAULT_OBJETIVO);
        assertThat(testPlanejamentoEnsino.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createPlanejamentoEnsinoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planejamentoEnsinoRepository.findAll().size();

        // Create the PlanejamentoEnsino with an existing ID
        planejamentoEnsino.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanejamentoEnsinoMockMvc.perform(post("/api/planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoEnsino)))
            .andExpect(status().isBadRequest());

        // Validate the PlanejamentoEnsino in the database
        List<PlanejamentoEnsino> planejamentoEnsinoList = planejamentoEnsinoRepository.findAll();
        assertThat(planejamentoEnsinoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = planejamentoEnsinoRepository.findAll().size();
        // set the field null
        planejamentoEnsino.setNome(null);

        // Create the PlanejamentoEnsino, which fails.

        restPlanejamentoEnsinoMockMvc.perform(post("/api/planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoEnsino)))
            .andExpect(status().isBadRequest());

        List<PlanejamentoEnsino> planejamentoEnsinoList = planejamentoEnsinoRepository.findAll();
        assertThat(planejamentoEnsinoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanejamentoEnsinos() throws Exception {
        // Initialize the database
        planejamentoEnsinoRepository.saveAndFlush(planejamentoEnsino);

        // Get all the planejamentoEnsinoList
        restPlanejamentoEnsinoMockMvc.perform(get("/api/planejamento-ensinos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planejamentoEnsino.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].objetivo").value(hasItem(DEFAULT_OBJETIVO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanejamentoEnsino() throws Exception {
        // Initialize the database
        planejamentoEnsinoRepository.saveAndFlush(planejamentoEnsino);

        // Get the planejamentoEnsino
        restPlanejamentoEnsinoMockMvc.perform(get("/api/planejamento-ensinos/{id}", planejamentoEnsino.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planejamentoEnsino.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.objetivo").value(DEFAULT_OBJETIVO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanejamentoEnsino() throws Exception {
        // Get the planejamentoEnsino
        restPlanejamentoEnsinoMockMvc.perform(get("/api/planejamento-ensinos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanejamentoEnsino() throws Exception {
        // Initialize the database
        planejamentoEnsinoService.save(planejamentoEnsino);

        int databaseSizeBeforeUpdate = planejamentoEnsinoRepository.findAll().size();

        // Update the planejamentoEnsino
        PlanejamentoEnsino updatedPlanejamentoEnsino = planejamentoEnsinoRepository.findById(planejamentoEnsino.getId()).get();
        // Disconnect from session so that the updates on updatedPlanejamentoEnsino are not directly saved in db
        em.detach(updatedPlanejamentoEnsino);
        updatedPlanejamentoEnsino
            .nome(UPDATED_NOME)
            .objetivo(UPDATED_OBJETIVO)
            .obs(UPDATED_OBS);

        restPlanejamentoEnsinoMockMvc.perform(put("/api/planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlanejamentoEnsino)))
            .andExpect(status().isOk());

        // Validate the PlanejamentoEnsino in the database
        List<PlanejamentoEnsino> planejamentoEnsinoList = planejamentoEnsinoRepository.findAll();
        assertThat(planejamentoEnsinoList).hasSize(databaseSizeBeforeUpdate);
        PlanejamentoEnsino testPlanejamentoEnsino = planejamentoEnsinoList.get(planejamentoEnsinoList.size() - 1);
        assertThat(testPlanejamentoEnsino.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPlanejamentoEnsino.getObjetivo()).isEqualTo(UPDATED_OBJETIVO);
        assertThat(testPlanejamentoEnsino.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanejamentoEnsino() throws Exception {
        int databaseSizeBeforeUpdate = planejamentoEnsinoRepository.findAll().size();

        // Create the PlanejamentoEnsino

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanejamentoEnsinoMockMvc.perform(put("/api/planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoEnsino)))
            .andExpect(status().isBadRequest());

        // Validate the PlanejamentoEnsino in the database
        List<PlanejamentoEnsino> planejamentoEnsinoList = planejamentoEnsinoRepository.findAll();
        assertThat(planejamentoEnsinoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanejamentoEnsino() throws Exception {
        // Initialize the database
        planejamentoEnsinoService.save(planejamentoEnsino);

        int databaseSizeBeforeDelete = planejamentoEnsinoRepository.findAll().size();

        // Get the planejamentoEnsino
        restPlanejamentoEnsinoMockMvc.perform(delete("/api/planejamento-ensinos/{id}", planejamentoEnsino.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanejamentoEnsino> planejamentoEnsinoList = planejamentoEnsinoRepository.findAll();
        assertThat(planejamentoEnsinoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanejamentoEnsino.class);
        PlanejamentoEnsino planejamentoEnsino1 = new PlanejamentoEnsino();
        planejamentoEnsino1.setId(1L);
        PlanejamentoEnsino planejamentoEnsino2 = new PlanejamentoEnsino();
        planejamentoEnsino2.setId(planejamentoEnsino1.getId());
        assertThat(planejamentoEnsino1).isEqualTo(planejamentoEnsino2);
        planejamentoEnsino2.setId(2L);
        assertThat(planejamentoEnsino1).isNotEqualTo(planejamentoEnsino2);
        planejamentoEnsino1.setId(null);
        assertThat(planejamentoEnsino1).isNotEqualTo(planejamentoEnsino2);
    }
}
