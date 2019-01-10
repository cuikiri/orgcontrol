package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Candidato;
import br.com.jhisolution.ong.control.repository.CandidatoRepository;
import br.com.jhisolution.ong.control.service.CandidatoService;
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
 * Test class for the CandidatoResource REST controller.
 *
 * @see CandidatoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class CandidatoResourceIntTest {

    private static final String DEFAULT_APELIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELIDO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_CADASTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_CADASTRO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCandidatoMockMvc;

    private Candidato candidato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CandidatoResource candidatoResource = new CandidatoResource(candidatoService);
        this.restCandidatoMockMvc = MockMvcBuilders.standaloneSetup(candidatoResource)
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
    public static Candidato createEntity(EntityManager em) {
        Candidato candidato = new Candidato()
            .apelido(DEFAULT_APELIDO)
            .dataCadastro(DEFAULT_DATA_CADASTRO)
            .obs(DEFAULT_OBS);
        return candidato;
    }

    @Before
    public void initTest() {
        candidato = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidato() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();

        // Create the Candidato
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isCreated());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate + 1);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getApelido()).isEqualTo(DEFAULT_APELIDO);
        assertThat(testCandidato.getDataCadastro()).isEqualTo(DEFAULT_DATA_CADASTRO);
        assertThat(testCandidato.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createCandidatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();

        // Create the Candidato with an existing ID
        candidato.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkApelidoIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidatoRepository.findAll().size();
        // set the field null
        candidato.setApelido(null);

        // Create the Candidato, which fails.

        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isBadRequest());

        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidatoRepository.findAll().size();
        // set the field null
        candidato.setDataCadastro(null);

        // Create the Candidato, which fails.

        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isBadRequest());

        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCandidatoes() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList
        restCandidatoMockMvc.perform(get("/api/candidatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidato.getId().intValue())))
            .andExpect(jsonPath("$.[*].apelido").value(hasItem(DEFAULT_APELIDO.toString())))
            .andExpect(jsonPath("$.[*].dataCadastro").value(hasItem(DEFAULT_DATA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", candidato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(candidato.getId().intValue()))
            .andExpect(jsonPath("$.apelido").value(DEFAULT_APELIDO.toString()))
            .andExpect(jsonPath("$.dataCadastro").value(DEFAULT_DATA_CADASTRO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCandidato() throws Exception {
        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidato() throws Exception {
        // Initialize the database
        candidatoService.save(candidato);

        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // Update the candidato
        Candidato updatedCandidato = candidatoRepository.findById(candidato.getId()).get();
        // Disconnect from session so that the updates on updatedCandidato are not directly saved in db
        em.detach(updatedCandidato);
        updatedCandidato
            .apelido(UPDATED_APELIDO)
            .dataCadastro(UPDATED_DATA_CADASTRO)
            .obs(UPDATED_OBS);

        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCandidato)))
            .andExpect(status().isOk());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getApelido()).isEqualTo(UPDATED_APELIDO);
        assertThat(testCandidato.getDataCadastro()).isEqualTo(UPDATED_DATA_CADASTRO);
        assertThat(testCandidato.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidato() throws Exception {
        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // Create the Candidato

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCandidato() throws Exception {
        // Initialize the database
        candidatoService.save(candidato);

        int databaseSizeBeforeDelete = candidatoRepository.findAll().size();

        // Get the candidato
        restCandidatoMockMvc.perform(delete("/api/candidatoes/{id}", candidato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Candidato.class);
        Candidato candidato1 = new Candidato();
        candidato1.setId(1L);
        Candidato candidato2 = new Candidato();
        candidato2.setId(candidato1.getId());
        assertThat(candidato1).isEqualTo(candidato2);
        candidato2.setId(2L);
        assertThat(candidato1).isNotEqualTo(candidato2);
        candidato1.setId(null);
        assertThat(candidato1).isNotEqualTo(candidato2);
    }
}
