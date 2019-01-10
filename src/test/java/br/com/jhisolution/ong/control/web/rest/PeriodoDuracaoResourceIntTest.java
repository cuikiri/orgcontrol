package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.PeriodoDuracao;
import br.com.jhisolution.ong.control.repository.PeriodoDuracaoRepository;
import br.com.jhisolution.ong.control.service.PeriodoDuracaoService;
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
 * Test class for the PeriodoDuracaoResource REST controller.
 *
 * @see PeriodoDuracaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class PeriodoDuracaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_FIM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_FIM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private PeriodoDuracaoRepository periodoDuracaoRepository;

    @Autowired
    private PeriodoDuracaoService periodoDuracaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPeriodoDuracaoMockMvc;

    private PeriodoDuracao periodoDuracao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PeriodoDuracaoResource periodoDuracaoResource = new PeriodoDuracaoResource(periodoDuracaoService);
        this.restPeriodoDuracaoMockMvc = MockMvcBuilders.standaloneSetup(periodoDuracaoResource)
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
    public static PeriodoDuracao createEntity(EntityManager em) {
        PeriodoDuracao periodoDuracao = new PeriodoDuracao()
            .nome(DEFAULT_NOME)
            .dataInicio(DEFAULT_DATA_INICIO)
            .dataFim(DEFAULT_DATA_FIM)
            .obs(DEFAULT_OBS);
        return periodoDuracao;
    }

    @Before
    public void initTest() {
        periodoDuracao = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriodoDuracao() throws Exception {
        int databaseSizeBeforeCreate = periodoDuracaoRepository.findAll().size();

        // Create the PeriodoDuracao
        restPeriodoDuracaoMockMvc.perform(post("/api/periodo-duracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoDuracao)))
            .andExpect(status().isCreated());

        // Validate the PeriodoDuracao in the database
        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeCreate + 1);
        PeriodoDuracao testPeriodoDuracao = periodoDuracaoList.get(periodoDuracaoList.size() - 1);
        assertThat(testPeriodoDuracao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPeriodoDuracao.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testPeriodoDuracao.getDataFim()).isEqualTo(DEFAULT_DATA_FIM);
        assertThat(testPeriodoDuracao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createPeriodoDuracaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = periodoDuracaoRepository.findAll().size();

        // Create the PeriodoDuracao with an existing ID
        periodoDuracao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodoDuracaoMockMvc.perform(post("/api/periodo-duracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoDuracao)))
            .andExpect(status().isBadRequest());

        // Validate the PeriodoDuracao in the database
        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoDuracaoRepository.findAll().size();
        // set the field null
        periodoDuracao.setNome(null);

        // Create the PeriodoDuracao, which fails.

        restPeriodoDuracaoMockMvc.perform(post("/api/periodo-duracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoDuracao)))
            .andExpect(status().isBadRequest());

        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoDuracaoRepository.findAll().size();
        // set the field null
        periodoDuracao.setDataInicio(null);

        // Create the PeriodoDuracao, which fails.

        restPeriodoDuracaoMockMvc.perform(post("/api/periodo-duracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoDuracao)))
            .andExpect(status().isBadRequest());

        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataFimIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoDuracaoRepository.findAll().size();
        // set the field null
        periodoDuracao.setDataFim(null);

        // Create the PeriodoDuracao, which fails.

        restPeriodoDuracaoMockMvc.perform(post("/api/periodo-duracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoDuracao)))
            .andExpect(status().isBadRequest());

        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeriodoDuracaos() throws Exception {
        // Initialize the database
        periodoDuracaoRepository.saveAndFlush(periodoDuracao);

        // Get all the periodoDuracaoList
        restPeriodoDuracaoMockMvc.perform(get("/api/periodo-duracaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periodoDuracao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].dataInicio").value(hasItem(DEFAULT_DATA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].dataFim").value(hasItem(DEFAULT_DATA_FIM.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getPeriodoDuracao() throws Exception {
        // Initialize the database
        periodoDuracaoRepository.saveAndFlush(periodoDuracao);

        // Get the periodoDuracao
        restPeriodoDuracaoMockMvc.perform(get("/api/periodo-duracaos/{id}", periodoDuracao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(periodoDuracao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.dataInicio").value(DEFAULT_DATA_INICIO.toString()))
            .andExpect(jsonPath("$.dataFim").value(DEFAULT_DATA_FIM.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPeriodoDuracao() throws Exception {
        // Get the periodoDuracao
        restPeriodoDuracaoMockMvc.perform(get("/api/periodo-duracaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriodoDuracao() throws Exception {
        // Initialize the database
        periodoDuracaoService.save(periodoDuracao);

        int databaseSizeBeforeUpdate = periodoDuracaoRepository.findAll().size();

        // Update the periodoDuracao
        PeriodoDuracao updatedPeriodoDuracao = periodoDuracaoRepository.findById(periodoDuracao.getId()).get();
        // Disconnect from session so that the updates on updatedPeriodoDuracao are not directly saved in db
        em.detach(updatedPeriodoDuracao);
        updatedPeriodoDuracao
            .nome(UPDATED_NOME)
            .dataInicio(UPDATED_DATA_INICIO)
            .dataFim(UPDATED_DATA_FIM)
            .obs(UPDATED_OBS);

        restPeriodoDuracaoMockMvc.perform(put("/api/periodo-duracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeriodoDuracao)))
            .andExpect(status().isOk());

        // Validate the PeriodoDuracao in the database
        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeUpdate);
        PeriodoDuracao testPeriodoDuracao = periodoDuracaoList.get(periodoDuracaoList.size() - 1);
        assertThat(testPeriodoDuracao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPeriodoDuracao.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testPeriodoDuracao.getDataFim()).isEqualTo(UPDATED_DATA_FIM);
        assertThat(testPeriodoDuracao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingPeriodoDuracao() throws Exception {
        int databaseSizeBeforeUpdate = periodoDuracaoRepository.findAll().size();

        // Create the PeriodoDuracao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodoDuracaoMockMvc.perform(put("/api/periodo-duracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoDuracao)))
            .andExpect(status().isBadRequest());

        // Validate the PeriodoDuracao in the database
        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePeriodoDuracao() throws Exception {
        // Initialize the database
        periodoDuracaoService.save(periodoDuracao);

        int databaseSizeBeforeDelete = periodoDuracaoRepository.findAll().size();

        // Get the periodoDuracao
        restPeriodoDuracaoMockMvc.perform(delete("/api/periodo-duracaos/{id}", periodoDuracao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PeriodoDuracao> periodoDuracaoList = periodoDuracaoRepository.findAll();
        assertThat(periodoDuracaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PeriodoDuracao.class);
        PeriodoDuracao periodoDuracao1 = new PeriodoDuracao();
        periodoDuracao1.setId(1L);
        PeriodoDuracao periodoDuracao2 = new PeriodoDuracao();
        periodoDuracao2.setId(periodoDuracao1.getId());
        assertThat(periodoDuracao1).isEqualTo(periodoDuracao2);
        periodoDuracao2.setId(2L);
        assertThat(periodoDuracao1).isNotEqualTo(periodoDuracao2);
        periodoDuracao1.setId(null);
        assertThat(periodoDuracao1).isNotEqualTo(periodoDuracao2);
    }
}
