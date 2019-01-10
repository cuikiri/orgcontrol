package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.AcompanhamentoAtividade;
import br.com.jhisolution.ong.control.repository.AcompanhamentoAtividadeRepository;
import br.com.jhisolution.ong.control.service.AcompanhamentoAtividadeService;
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
 * Test class for the AcompanhamentoAtividadeResource REST controller.
 *
 * @see AcompanhamentoAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AcompanhamentoAtividadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TEMA = "AAAAAAAAAA";
    private static final String UPDATED_TEMA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_FIM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_FIM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private AcompanhamentoAtividadeRepository acompanhamentoAtividadeRepository;

    @Autowired
    private AcompanhamentoAtividadeService acompanhamentoAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAcompanhamentoAtividadeMockMvc;

    private AcompanhamentoAtividade acompanhamentoAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcompanhamentoAtividadeResource acompanhamentoAtividadeResource = new AcompanhamentoAtividadeResource(acompanhamentoAtividadeService);
        this.restAcompanhamentoAtividadeMockMvc = MockMvcBuilders.standaloneSetup(acompanhamentoAtividadeResource)
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
    public static AcompanhamentoAtividade createEntity(EntityManager em) {
        AcompanhamentoAtividade acompanhamentoAtividade = new AcompanhamentoAtividade()
            .nome(DEFAULT_NOME)
            .data(DEFAULT_DATA)
            .tema(DEFAULT_TEMA)
            .descricao(DEFAULT_DESCRICAO)
            .dataInicio(DEFAULT_DATA_INICIO)
            .dataFim(DEFAULT_DATA_FIM)
            .obs(DEFAULT_OBS);
        return acompanhamentoAtividade;
    }

    @Before
    public void initTest() {
        acompanhamentoAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcompanhamentoAtividade() throws Exception {
        int databaseSizeBeforeCreate = acompanhamentoAtividadeRepository.findAll().size();

        // Create the AcompanhamentoAtividade
        restAcompanhamentoAtividadeMockMvc.perform(post("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAtividade)))
            .andExpect(status().isCreated());

        // Validate the AcompanhamentoAtividade in the database
        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        AcompanhamentoAtividade testAcompanhamentoAtividade = acompanhamentoAtividadeList.get(acompanhamentoAtividadeList.size() - 1);
        assertThat(testAcompanhamentoAtividade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAcompanhamentoAtividade.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAcompanhamentoAtividade.getTema()).isEqualTo(DEFAULT_TEMA);
        assertThat(testAcompanhamentoAtividade.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testAcompanhamentoAtividade.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testAcompanhamentoAtividade.getDataFim()).isEqualTo(DEFAULT_DATA_FIM);
        assertThat(testAcompanhamentoAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createAcompanhamentoAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = acompanhamentoAtividadeRepository.findAll().size();

        // Create the AcompanhamentoAtividade with an existing ID
        acompanhamentoAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcompanhamentoAtividadeMockMvc.perform(post("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the AcompanhamentoAtividade in the database
        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompanhamentoAtividadeRepository.findAll().size();
        // set the field null
        acompanhamentoAtividade.setNome(null);

        // Create the AcompanhamentoAtividade, which fails.

        restAcompanhamentoAtividadeMockMvc.perform(post("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompanhamentoAtividadeRepository.findAll().size();
        // set the field null
        acompanhamentoAtividade.setData(null);

        // Create the AcompanhamentoAtividade, which fails.

        restAcompanhamentoAtividadeMockMvc.perform(post("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompanhamentoAtividadeRepository.findAll().size();
        // set the field null
        acompanhamentoAtividade.setDataInicio(null);

        // Create the AcompanhamentoAtividade, which fails.

        restAcompanhamentoAtividadeMockMvc.perform(post("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataFimIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompanhamentoAtividadeRepository.findAll().size();
        // set the field null
        acompanhamentoAtividade.setDataFim(null);

        // Create the AcompanhamentoAtividade, which fails.

        restAcompanhamentoAtividadeMockMvc.perform(post("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcompanhamentoAtividades() throws Exception {
        // Initialize the database
        acompanhamentoAtividadeRepository.saveAndFlush(acompanhamentoAtividade);

        // Get all the acompanhamentoAtividadeList
        restAcompanhamentoAtividadeMockMvc.perform(get("/api/acompanhamento-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acompanhamentoAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].tema").value(hasItem(DEFAULT_TEMA.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].dataInicio").value(hasItem(DEFAULT_DATA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].dataFim").value(hasItem(DEFAULT_DATA_FIM.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        acompanhamentoAtividadeRepository.saveAndFlush(acompanhamentoAtividade);

        // Get the acompanhamentoAtividade
        restAcompanhamentoAtividadeMockMvc.perform(get("/api/acompanhamento-atividades/{id}", acompanhamentoAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(acompanhamentoAtividade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.tema").value(DEFAULT_TEMA.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.dataInicio").value(DEFAULT_DATA_INICIO.toString()))
            .andExpect(jsonPath("$.dataFim").value(DEFAULT_DATA_FIM.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAcompanhamentoAtividade() throws Exception {
        // Get the acompanhamentoAtividade
        restAcompanhamentoAtividadeMockMvc.perform(get("/api/acompanhamento-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        acompanhamentoAtividadeService.save(acompanhamentoAtividade);

        int databaseSizeBeforeUpdate = acompanhamentoAtividadeRepository.findAll().size();

        // Update the acompanhamentoAtividade
        AcompanhamentoAtividade updatedAcompanhamentoAtividade = acompanhamentoAtividadeRepository.findById(acompanhamentoAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedAcompanhamentoAtividade are not directly saved in db
        em.detach(updatedAcompanhamentoAtividade);
        updatedAcompanhamentoAtividade
            .nome(UPDATED_NOME)
            .data(UPDATED_DATA)
            .tema(UPDATED_TEMA)
            .descricao(UPDATED_DESCRICAO)
            .dataInicio(UPDATED_DATA_INICIO)
            .dataFim(UPDATED_DATA_FIM)
            .obs(UPDATED_OBS);

        restAcompanhamentoAtividadeMockMvc.perform(put("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAcompanhamentoAtividade)))
            .andExpect(status().isOk());

        // Validate the AcompanhamentoAtividade in the database
        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
        AcompanhamentoAtividade testAcompanhamentoAtividade = acompanhamentoAtividadeList.get(acompanhamentoAtividadeList.size() - 1);
        assertThat(testAcompanhamentoAtividade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAcompanhamentoAtividade.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAcompanhamentoAtividade.getTema()).isEqualTo(UPDATED_TEMA);
        assertThat(testAcompanhamentoAtividade.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testAcompanhamentoAtividade.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testAcompanhamentoAtividade.getDataFim()).isEqualTo(UPDATED_DATA_FIM);
        assertThat(testAcompanhamentoAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingAcompanhamentoAtividade() throws Exception {
        int databaseSizeBeforeUpdate = acompanhamentoAtividadeRepository.findAll().size();

        // Create the AcompanhamentoAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcompanhamentoAtividadeMockMvc.perform(put("/api/acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the AcompanhamentoAtividade in the database
        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        acompanhamentoAtividadeService.save(acompanhamentoAtividade);

        int databaseSizeBeforeDelete = acompanhamentoAtividadeRepository.findAll().size();

        // Get the acompanhamentoAtividade
        restAcompanhamentoAtividadeMockMvc.perform(delete("/api/acompanhamento-atividades/{id}", acompanhamentoAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcompanhamentoAtividade> acompanhamentoAtividadeList = acompanhamentoAtividadeRepository.findAll();
        assertThat(acompanhamentoAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcompanhamentoAtividade.class);
        AcompanhamentoAtividade acompanhamentoAtividade1 = new AcompanhamentoAtividade();
        acompanhamentoAtividade1.setId(1L);
        AcompanhamentoAtividade acompanhamentoAtividade2 = new AcompanhamentoAtividade();
        acompanhamentoAtividade2.setId(acompanhamentoAtividade1.getId());
        assertThat(acompanhamentoAtividade1).isEqualTo(acompanhamentoAtividade2);
        acompanhamentoAtividade2.setId(2L);
        assertThat(acompanhamentoAtividade1).isNotEqualTo(acompanhamentoAtividade2);
        acompanhamentoAtividade1.setId(null);
        assertThat(acompanhamentoAtividade1).isNotEqualTo(acompanhamentoAtividade2);
    }
}
