package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Matricula;
import br.com.jhisolution.ong.control.repository.MatriculaRepository;
import br.com.jhisolution.ong.control.service.MatriculaService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MatriculaResource REST controller.
 *
 * @see MatriculaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class MatriculaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMatriculaMockMvc;

    private Matricula matricula;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MatriculaResource matriculaResource = new MatriculaResource(matriculaService);
        this.restMatriculaMockMvc = MockMvcBuilders.standaloneSetup(matriculaResource)
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
    public static Matricula createEntity(EntityManager em) {
        Matricula matricula = new Matricula()
            .data(DEFAULT_DATA)
            .obs(DEFAULT_OBS);
        return matricula;
    }

    @Before
    public void initTest() {
        matricula = createEntity(em);
    }

    @Test
    @Transactional
    public void createMatricula() throws Exception {
        int databaseSizeBeforeCreate = matriculaRepository.findAll().size();

        // Create the Matricula
        restMatriculaMockMvc.perform(post("/api/matriculas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matricula)))
            .andExpect(status().isCreated());

        // Validate the Matricula in the database
        List<Matricula> matriculaList = matriculaRepository.findAll();
        assertThat(matriculaList).hasSize(databaseSizeBeforeCreate + 1);
        Matricula testMatricula = matriculaList.get(matriculaList.size() - 1);
        assertThat(testMatricula.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testMatricula.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createMatriculaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = matriculaRepository.findAll().size();

        // Create the Matricula with an existing ID
        matricula.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMatriculaMockMvc.perform(post("/api/matriculas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matricula)))
            .andExpect(status().isBadRequest());

        // Validate the Matricula in the database
        List<Matricula> matriculaList = matriculaRepository.findAll();
        assertThat(matriculaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = matriculaRepository.findAll().size();
        // set the field null
        matricula.setData(null);

        // Create the Matricula, which fails.

        restMatriculaMockMvc.perform(post("/api/matriculas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matricula)))
            .andExpect(status().isBadRequest());

        List<Matricula> matriculaList = matriculaRepository.findAll();
        assertThat(matriculaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMatriculas() throws Exception {
        // Initialize the database
        matriculaRepository.saveAndFlush(matricula);

        // Get all the matriculaList
        restMatriculaMockMvc.perform(get("/api/matriculas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(matricula.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getMatricula() throws Exception {
        // Initialize the database
        matriculaRepository.saveAndFlush(matricula);

        // Get the matricula
        restMatriculaMockMvc.perform(get("/api/matriculas/{id}", matricula.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(matricula.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMatricula() throws Exception {
        // Get the matricula
        restMatriculaMockMvc.perform(get("/api/matriculas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMatricula() throws Exception {
        // Initialize the database
        matriculaService.save(matricula);

        int databaseSizeBeforeUpdate = matriculaRepository.findAll().size();

        // Update the matricula
        Matricula updatedMatricula = matriculaRepository.findById(matricula.getId()).get();
        // Disconnect from session so that the updates on updatedMatricula are not directly saved in db
        em.detach(updatedMatricula);
        updatedMatricula
            .data(UPDATED_DATA)
            .obs(UPDATED_OBS);

        restMatriculaMockMvc.perform(put("/api/matriculas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMatricula)))
            .andExpect(status().isOk());

        // Validate the Matricula in the database
        List<Matricula> matriculaList = matriculaRepository.findAll();
        assertThat(matriculaList).hasSize(databaseSizeBeforeUpdate);
        Matricula testMatricula = matriculaList.get(matriculaList.size() - 1);
        assertThat(testMatricula.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testMatricula.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingMatricula() throws Exception {
        int databaseSizeBeforeUpdate = matriculaRepository.findAll().size();

        // Create the Matricula

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMatriculaMockMvc.perform(put("/api/matriculas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matricula)))
            .andExpect(status().isBadRequest());

        // Validate the Matricula in the database
        List<Matricula> matriculaList = matriculaRepository.findAll();
        assertThat(matriculaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMatricula() throws Exception {
        // Initialize the database
        matriculaService.save(matricula);

        int databaseSizeBeforeDelete = matriculaRepository.findAll().size();

        // Get the matricula
        restMatriculaMockMvc.perform(delete("/api/matriculas/{id}", matricula.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Matricula> matriculaList = matriculaRepository.findAll();
        assertThat(matriculaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Matricula.class);
        Matricula matricula1 = new Matricula();
        matricula1.setId(1L);
        Matricula matricula2 = new Matricula();
        matricula2.setId(matricula1.getId());
        assertThat(matricula1).isEqualTo(matricula2);
        matricula2.setId(2L);
        assertThat(matricula1).isNotEqualTo(matricula2);
        matricula1.setId(null);
        assertThat(matricula1).isNotEqualTo(matricula2);
    }
}
