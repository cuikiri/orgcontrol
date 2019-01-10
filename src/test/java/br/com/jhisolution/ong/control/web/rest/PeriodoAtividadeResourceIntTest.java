package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.PeriodoAtividade;
import br.com.jhisolution.ong.control.repository.PeriodoAtividadeRepository;
import br.com.jhisolution.ong.control.service.PeriodoAtividadeService;
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
 * Test class for the PeriodoAtividadeResource REST controller.
 *
 * @see PeriodoAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class PeriodoAtividadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_HORA_INICIO = "AAAAA";
    private static final String UPDATED_HORA_INICIO = "BBBBB";

    private static final String DEFAULT_HORA_FIM = "AAAAA";
    private static final String UPDATED_HORA_FIM = "BBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private PeriodoAtividadeRepository periodoAtividadeRepository;

    @Autowired
    private PeriodoAtividadeService periodoAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPeriodoAtividadeMockMvc;

    private PeriodoAtividade periodoAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PeriodoAtividadeResource periodoAtividadeResource = new PeriodoAtividadeResource(periodoAtividadeService);
        this.restPeriodoAtividadeMockMvc = MockMvcBuilders.standaloneSetup(periodoAtividadeResource)
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
    public static PeriodoAtividade createEntity(EntityManager em) {
        PeriodoAtividade periodoAtividade = new PeriodoAtividade()
            .nome(DEFAULT_NOME)
            .horaInicio(DEFAULT_HORA_INICIO)
            .horaFim(DEFAULT_HORA_FIM)
            .obs(DEFAULT_OBS);
        return periodoAtividade;
    }

    @Before
    public void initTest() {
        periodoAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriodoAtividade() throws Exception {
        int databaseSizeBeforeCreate = periodoAtividadeRepository.findAll().size();

        // Create the PeriodoAtividade
        restPeriodoAtividadeMockMvc.perform(post("/api/periodo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoAtividade)))
            .andExpect(status().isCreated());

        // Validate the PeriodoAtividade in the database
        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        PeriodoAtividade testPeriodoAtividade = periodoAtividadeList.get(periodoAtividadeList.size() - 1);
        assertThat(testPeriodoAtividade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPeriodoAtividade.getHoraInicio()).isEqualTo(DEFAULT_HORA_INICIO);
        assertThat(testPeriodoAtividade.getHoraFim()).isEqualTo(DEFAULT_HORA_FIM);
        assertThat(testPeriodoAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createPeriodoAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = periodoAtividadeRepository.findAll().size();

        // Create the PeriodoAtividade with an existing ID
        periodoAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodoAtividadeMockMvc.perform(post("/api/periodo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the PeriodoAtividade in the database
        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoAtividadeRepository.findAll().size();
        // set the field null
        periodoAtividade.setNome(null);

        // Create the PeriodoAtividade, which fails.

        restPeriodoAtividadeMockMvc.perform(post("/api/periodo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoAtividade)))
            .andExpect(status().isBadRequest());

        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoAtividadeRepository.findAll().size();
        // set the field null
        periodoAtividade.setHoraInicio(null);

        // Create the PeriodoAtividade, which fails.

        restPeriodoAtividadeMockMvc.perform(post("/api/periodo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoAtividade)))
            .andExpect(status().isBadRequest());

        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraFimIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoAtividadeRepository.findAll().size();
        // set the field null
        periodoAtividade.setHoraFim(null);

        // Create the PeriodoAtividade, which fails.

        restPeriodoAtividadeMockMvc.perform(post("/api/periodo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoAtividade)))
            .andExpect(status().isBadRequest());

        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeriodoAtividades() throws Exception {
        // Initialize the database
        periodoAtividadeRepository.saveAndFlush(periodoAtividade);

        // Get all the periodoAtividadeList
        restPeriodoAtividadeMockMvc.perform(get("/api/periodo-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periodoAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].horaInicio").value(hasItem(DEFAULT_HORA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].horaFim").value(hasItem(DEFAULT_HORA_FIM.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getPeriodoAtividade() throws Exception {
        // Initialize the database
        periodoAtividadeRepository.saveAndFlush(periodoAtividade);

        // Get the periodoAtividade
        restPeriodoAtividadeMockMvc.perform(get("/api/periodo-atividades/{id}", periodoAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(periodoAtividade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.horaInicio").value(DEFAULT_HORA_INICIO.toString()))
            .andExpect(jsonPath("$.horaFim").value(DEFAULT_HORA_FIM.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPeriodoAtividade() throws Exception {
        // Get the periodoAtividade
        restPeriodoAtividadeMockMvc.perform(get("/api/periodo-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriodoAtividade() throws Exception {
        // Initialize the database
        periodoAtividadeService.save(periodoAtividade);

        int databaseSizeBeforeUpdate = periodoAtividadeRepository.findAll().size();

        // Update the periodoAtividade
        PeriodoAtividade updatedPeriodoAtividade = periodoAtividadeRepository.findById(periodoAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedPeriodoAtividade are not directly saved in db
        em.detach(updatedPeriodoAtividade);
        updatedPeriodoAtividade
            .nome(UPDATED_NOME)
            .horaInicio(UPDATED_HORA_INICIO)
            .horaFim(UPDATED_HORA_FIM)
            .obs(UPDATED_OBS);

        restPeriodoAtividadeMockMvc.perform(put("/api/periodo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeriodoAtividade)))
            .andExpect(status().isOk());

        // Validate the PeriodoAtividade in the database
        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeUpdate);
        PeriodoAtividade testPeriodoAtividade = periodoAtividadeList.get(periodoAtividadeList.size() - 1);
        assertThat(testPeriodoAtividade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPeriodoAtividade.getHoraInicio()).isEqualTo(UPDATED_HORA_INICIO);
        assertThat(testPeriodoAtividade.getHoraFim()).isEqualTo(UPDATED_HORA_FIM);
        assertThat(testPeriodoAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingPeriodoAtividade() throws Exception {
        int databaseSizeBeforeUpdate = periodoAtividadeRepository.findAll().size();

        // Create the PeriodoAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodoAtividadeMockMvc.perform(put("/api/periodo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the PeriodoAtividade in the database
        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePeriodoAtividade() throws Exception {
        // Initialize the database
        periodoAtividadeService.save(periodoAtividade);

        int databaseSizeBeforeDelete = periodoAtividadeRepository.findAll().size();

        // Get the periodoAtividade
        restPeriodoAtividadeMockMvc.perform(delete("/api/periodo-atividades/{id}", periodoAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PeriodoAtividade> periodoAtividadeList = periodoAtividadeRepository.findAll();
        assertThat(periodoAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PeriodoAtividade.class);
        PeriodoAtividade periodoAtividade1 = new PeriodoAtividade();
        periodoAtividade1.setId(1L);
        PeriodoAtividade periodoAtividade2 = new PeriodoAtividade();
        periodoAtividade2.setId(periodoAtividade1.getId());
        assertThat(periodoAtividade1).isEqualTo(periodoAtividade2);
        periodoAtividade2.setId(2L);
        assertThat(periodoAtividade1).isNotEqualTo(periodoAtividade2);
        periodoAtividade1.setId(null);
        assertThat(periodoAtividade1).isNotEqualTo(periodoAtividade2);
    }
}
