package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.PeriodoSemana;
import br.com.jhisolution.ong.control.repository.PeriodoSemanaRepository;
import br.com.jhisolution.ong.control.service.PeriodoSemanaService;
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
 * Test class for the PeriodoSemanaResource REST controller.
 *
 * @see PeriodoSemanaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class PeriodoSemanaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private PeriodoSemanaRepository periodoSemanaRepository;

    @Autowired
    private PeriodoSemanaService periodoSemanaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPeriodoSemanaMockMvc;

    private PeriodoSemana periodoSemana;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PeriodoSemanaResource periodoSemanaResource = new PeriodoSemanaResource(periodoSemanaService);
        this.restPeriodoSemanaMockMvc = MockMvcBuilders.standaloneSetup(periodoSemanaResource)
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
    public static PeriodoSemana createEntity(EntityManager em) {
        PeriodoSemana periodoSemana = new PeriodoSemana()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return periodoSemana;
    }

    @Before
    public void initTest() {
        periodoSemana = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriodoSemana() throws Exception {
        int databaseSizeBeforeCreate = periodoSemanaRepository.findAll().size();

        // Create the PeriodoSemana
        restPeriodoSemanaMockMvc.perform(post("/api/periodo-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoSemana)))
            .andExpect(status().isCreated());

        // Validate the PeriodoSemana in the database
        List<PeriodoSemana> periodoSemanaList = periodoSemanaRepository.findAll();
        assertThat(periodoSemanaList).hasSize(databaseSizeBeforeCreate + 1);
        PeriodoSemana testPeriodoSemana = periodoSemanaList.get(periodoSemanaList.size() - 1);
        assertThat(testPeriodoSemana.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPeriodoSemana.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createPeriodoSemanaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = periodoSemanaRepository.findAll().size();

        // Create the PeriodoSemana with an existing ID
        periodoSemana.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodoSemanaMockMvc.perform(post("/api/periodo-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoSemana)))
            .andExpect(status().isBadRequest());

        // Validate the PeriodoSemana in the database
        List<PeriodoSemana> periodoSemanaList = periodoSemanaRepository.findAll();
        assertThat(periodoSemanaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoSemanaRepository.findAll().size();
        // set the field null
        periodoSemana.setNome(null);

        // Create the PeriodoSemana, which fails.

        restPeriodoSemanaMockMvc.perform(post("/api/periodo-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoSemana)))
            .andExpect(status().isBadRequest());

        List<PeriodoSemana> periodoSemanaList = periodoSemanaRepository.findAll();
        assertThat(periodoSemanaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeriodoSemanas() throws Exception {
        // Initialize the database
        periodoSemanaRepository.saveAndFlush(periodoSemana);

        // Get all the periodoSemanaList
        restPeriodoSemanaMockMvc.perform(get("/api/periodo-semanas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periodoSemana.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getPeriodoSemana() throws Exception {
        // Initialize the database
        periodoSemanaRepository.saveAndFlush(periodoSemana);

        // Get the periodoSemana
        restPeriodoSemanaMockMvc.perform(get("/api/periodo-semanas/{id}", periodoSemana.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(periodoSemana.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPeriodoSemana() throws Exception {
        // Get the periodoSemana
        restPeriodoSemanaMockMvc.perform(get("/api/periodo-semanas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriodoSemana() throws Exception {
        // Initialize the database
        periodoSemanaService.save(periodoSemana);

        int databaseSizeBeforeUpdate = periodoSemanaRepository.findAll().size();

        // Update the periodoSemana
        PeriodoSemana updatedPeriodoSemana = periodoSemanaRepository.findById(periodoSemana.getId()).get();
        // Disconnect from session so that the updates on updatedPeriodoSemana are not directly saved in db
        em.detach(updatedPeriodoSemana);
        updatedPeriodoSemana
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restPeriodoSemanaMockMvc.perform(put("/api/periodo-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeriodoSemana)))
            .andExpect(status().isOk());

        // Validate the PeriodoSemana in the database
        List<PeriodoSemana> periodoSemanaList = periodoSemanaRepository.findAll();
        assertThat(periodoSemanaList).hasSize(databaseSizeBeforeUpdate);
        PeriodoSemana testPeriodoSemana = periodoSemanaList.get(periodoSemanaList.size() - 1);
        assertThat(testPeriodoSemana.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPeriodoSemana.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingPeriodoSemana() throws Exception {
        int databaseSizeBeforeUpdate = periodoSemanaRepository.findAll().size();

        // Create the PeriodoSemana

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodoSemanaMockMvc.perform(put("/api/periodo-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoSemana)))
            .andExpect(status().isBadRequest());

        // Validate the PeriodoSemana in the database
        List<PeriodoSemana> periodoSemanaList = periodoSemanaRepository.findAll();
        assertThat(periodoSemanaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePeriodoSemana() throws Exception {
        // Initialize the database
        periodoSemanaService.save(periodoSemana);

        int databaseSizeBeforeDelete = periodoSemanaRepository.findAll().size();

        // Get the periodoSemana
        restPeriodoSemanaMockMvc.perform(delete("/api/periodo-semanas/{id}", periodoSemana.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PeriodoSemana> periodoSemanaList = periodoSemanaRepository.findAll();
        assertThat(periodoSemanaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PeriodoSemana.class);
        PeriodoSemana periodoSemana1 = new PeriodoSemana();
        periodoSemana1.setId(1L);
        PeriodoSemana periodoSemana2 = new PeriodoSemana();
        periodoSemana2.setId(periodoSemana1.getId());
        assertThat(periodoSemana1).isEqualTo(periodoSemana2);
        periodoSemana2.setId(2L);
        assertThat(periodoSemana1).isNotEqualTo(periodoSemana2);
        periodoSemana1.setId(null);
        assertThat(periodoSemana1).isNotEqualTo(periodoSemana2);
    }
}
