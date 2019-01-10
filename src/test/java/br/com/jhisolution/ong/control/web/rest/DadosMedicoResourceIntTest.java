package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.DadosMedico;
import br.com.jhisolution.ong.control.repository.DadosMedicoRepository;
import br.com.jhisolution.ong.control.service.DadosMedicoService;
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
 * Test class for the DadosMedicoResource REST controller.
 *
 * @see DadosMedicoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class DadosMedicoResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private DadosMedicoRepository dadosMedicoRepository;

    @Autowired
    private DadosMedicoService dadosMedicoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDadosMedicoMockMvc;

    private DadosMedico dadosMedico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DadosMedicoResource dadosMedicoResource = new DadosMedicoResource(dadosMedicoService);
        this.restDadosMedicoMockMvc = MockMvcBuilders.standaloneSetup(dadosMedicoResource)
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
    public static DadosMedico createEntity(EntityManager em) {
        DadosMedico dadosMedico = new DadosMedico()
            .data(DEFAULT_DATA)
            .obs(DEFAULT_OBS);
        return dadosMedico;
    }

    @Before
    public void initTest() {
        dadosMedico = createEntity(em);
    }

    @Test
    @Transactional
    public void createDadosMedico() throws Exception {
        int databaseSizeBeforeCreate = dadosMedicoRepository.findAll().size();

        // Create the DadosMedico
        restDadosMedicoMockMvc.perform(post("/api/dados-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadosMedico)))
            .andExpect(status().isCreated());

        // Validate the DadosMedico in the database
        List<DadosMedico> dadosMedicoList = dadosMedicoRepository.findAll();
        assertThat(dadosMedicoList).hasSize(databaseSizeBeforeCreate + 1);
        DadosMedico testDadosMedico = dadosMedicoList.get(dadosMedicoList.size() - 1);
        assertThat(testDadosMedico.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testDadosMedico.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createDadosMedicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dadosMedicoRepository.findAll().size();

        // Create the DadosMedico with an existing ID
        dadosMedico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDadosMedicoMockMvc.perform(post("/api/dados-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadosMedico)))
            .andExpect(status().isBadRequest());

        // Validate the DadosMedico in the database
        List<DadosMedico> dadosMedicoList = dadosMedicoRepository.findAll();
        assertThat(dadosMedicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = dadosMedicoRepository.findAll().size();
        // set the field null
        dadosMedico.setData(null);

        // Create the DadosMedico, which fails.

        restDadosMedicoMockMvc.perform(post("/api/dados-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadosMedico)))
            .andExpect(status().isBadRequest());

        List<DadosMedico> dadosMedicoList = dadosMedicoRepository.findAll();
        assertThat(dadosMedicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDadosMedicos() throws Exception {
        // Initialize the database
        dadosMedicoRepository.saveAndFlush(dadosMedico);

        // Get all the dadosMedicoList
        restDadosMedicoMockMvc.perform(get("/api/dados-medicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dadosMedico.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getDadosMedico() throws Exception {
        // Initialize the database
        dadosMedicoRepository.saveAndFlush(dadosMedico);

        // Get the dadosMedico
        restDadosMedicoMockMvc.perform(get("/api/dados-medicos/{id}", dadosMedico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dadosMedico.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDadosMedico() throws Exception {
        // Get the dadosMedico
        restDadosMedicoMockMvc.perform(get("/api/dados-medicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDadosMedico() throws Exception {
        // Initialize the database
        dadosMedicoService.save(dadosMedico);

        int databaseSizeBeforeUpdate = dadosMedicoRepository.findAll().size();

        // Update the dadosMedico
        DadosMedico updatedDadosMedico = dadosMedicoRepository.findById(dadosMedico.getId()).get();
        // Disconnect from session so that the updates on updatedDadosMedico are not directly saved in db
        em.detach(updatedDadosMedico);
        updatedDadosMedico
            .data(UPDATED_DATA)
            .obs(UPDATED_OBS);

        restDadosMedicoMockMvc.perform(put("/api/dados-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDadosMedico)))
            .andExpect(status().isOk());

        // Validate the DadosMedico in the database
        List<DadosMedico> dadosMedicoList = dadosMedicoRepository.findAll();
        assertThat(dadosMedicoList).hasSize(databaseSizeBeforeUpdate);
        DadosMedico testDadosMedico = dadosMedicoList.get(dadosMedicoList.size() - 1);
        assertThat(testDadosMedico.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testDadosMedico.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingDadosMedico() throws Exception {
        int databaseSizeBeforeUpdate = dadosMedicoRepository.findAll().size();

        // Create the DadosMedico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDadosMedicoMockMvc.perform(put("/api/dados-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadosMedico)))
            .andExpect(status().isBadRequest());

        // Validate the DadosMedico in the database
        List<DadosMedico> dadosMedicoList = dadosMedicoRepository.findAll();
        assertThat(dadosMedicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDadosMedico() throws Exception {
        // Initialize the database
        dadosMedicoService.save(dadosMedico);

        int databaseSizeBeforeDelete = dadosMedicoRepository.findAll().size();

        // Get the dadosMedico
        restDadosMedicoMockMvc.perform(delete("/api/dados-medicos/{id}", dadosMedico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DadosMedico> dadosMedicoList = dadosMedicoRepository.findAll();
        assertThat(dadosMedicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DadosMedico.class);
        DadosMedico dadosMedico1 = new DadosMedico();
        dadosMedico1.setId(1L);
        DadosMedico dadosMedico2 = new DadosMedico();
        dadosMedico2.setId(dadosMedico1.getId());
        assertThat(dadosMedico1).isEqualTo(dadosMedico2);
        dadosMedico2.setId(2L);
        assertThat(dadosMedico1).isNotEqualTo(dadosMedico2);
        dadosMedico1.setId(null);
        assertThat(dadosMedico1).isNotEqualTo(dadosMedico2);
    }
}
