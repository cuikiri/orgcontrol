package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Eleicao;
import br.com.jhisolution.ong.control.repository.EleicaoRepository;
import br.com.jhisolution.ong.control.service.EleicaoService;
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
 * Test class for the EleicaoResource REST controller.
 *
 * @see EleicaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class EleicaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_CADASTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_CADASTRO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ANO_ELEICAO = 1;
    private static final Integer UPDATED_ANO_ELEICAO = 2;

    private static final LocalDate DEFAULT_DATA_PLEITO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_PLEITO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TOTAL_ELEITORES = 1;
    private static final Integer UPDATED_TOTAL_ELEITORES = 2;

    private static final Integer DEFAULT_TOTA_VOTOS = 1;
    private static final Integer UPDATED_TOTA_VOTOS = 2;

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    private static final String DEFAULT_HORA = "AAAAA";
    private static final String UPDATED_HORA = "BBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private EleicaoRepository eleicaoRepository;

    @Autowired
    private EleicaoService eleicaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEleicaoMockMvc;

    private Eleicao eleicao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EleicaoResource eleicaoResource = new EleicaoResource(eleicaoService);
        this.restEleicaoMockMvc = MockMvcBuilders.standaloneSetup(eleicaoResource)
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
    public static Eleicao createEntity(EntityManager em) {
        Eleicao eleicao = new Eleicao()
            .nome(DEFAULT_NOME)
            .dataCadastro(DEFAULT_DATA_CADASTRO)
            .anoEleicao(DEFAULT_ANO_ELEICAO)
            .dataPleito(DEFAULT_DATA_PLEITO)
            .totalEleitores(DEFAULT_TOTAL_ELEITORES)
            .totaVotos(DEFAULT_TOTA_VOTOS)
            .local(DEFAULT_LOCAL)
            .hora(DEFAULT_HORA)
            .obs(DEFAULT_OBS);
        return eleicao;
    }

    @Before
    public void initTest() {
        eleicao = createEntity(em);
    }

    @Test
    @Transactional
    public void createEleicao() throws Exception {
        int databaseSizeBeforeCreate = eleicaoRepository.findAll().size();

        // Create the Eleicao
        restEleicaoMockMvc.perform(post("/api/eleicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eleicao)))
            .andExpect(status().isCreated());

        // Validate the Eleicao in the database
        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeCreate + 1);
        Eleicao testEleicao = eleicaoList.get(eleicaoList.size() - 1);
        assertThat(testEleicao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testEleicao.getDataCadastro()).isEqualTo(DEFAULT_DATA_CADASTRO);
        assertThat(testEleicao.getAnoEleicao()).isEqualTo(DEFAULT_ANO_ELEICAO);
        assertThat(testEleicao.getDataPleito()).isEqualTo(DEFAULT_DATA_PLEITO);
        assertThat(testEleicao.getTotalEleitores()).isEqualTo(DEFAULT_TOTAL_ELEITORES);
        assertThat(testEleicao.getTotaVotos()).isEqualTo(DEFAULT_TOTA_VOTOS);
        assertThat(testEleicao.getLocal()).isEqualTo(DEFAULT_LOCAL);
        assertThat(testEleicao.getHora()).isEqualTo(DEFAULT_HORA);
        assertThat(testEleicao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createEleicaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eleicaoRepository.findAll().size();

        // Create the Eleicao with an existing ID
        eleicao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEleicaoMockMvc.perform(post("/api/eleicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eleicao)))
            .andExpect(status().isBadRequest());

        // Validate the Eleicao in the database
        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eleicaoRepository.findAll().size();
        // set the field null
        eleicao.setNome(null);

        // Create the Eleicao, which fails.

        restEleicaoMockMvc.perform(post("/api/eleicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eleicao)))
            .andExpect(status().isBadRequest());

        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = eleicaoRepository.findAll().size();
        // set the field null
        eleicao.setDataCadastro(null);

        // Create the Eleicao, which fails.

        restEleicaoMockMvc.perform(post("/api/eleicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eleicao)))
            .andExpect(status().isBadRequest());

        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnoEleicaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = eleicaoRepository.findAll().size();
        // set the field null
        eleicao.setAnoEleicao(null);

        // Create the Eleicao, which fails.

        restEleicaoMockMvc.perform(post("/api/eleicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eleicao)))
            .andExpect(status().isBadRequest());

        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEleicaos() throws Exception {
        // Initialize the database
        eleicaoRepository.saveAndFlush(eleicao);

        // Get all the eleicaoList
        restEleicaoMockMvc.perform(get("/api/eleicaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eleicao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].dataCadastro").value(hasItem(DEFAULT_DATA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].anoEleicao").value(hasItem(DEFAULT_ANO_ELEICAO)))
            .andExpect(jsonPath("$.[*].dataPleito").value(hasItem(DEFAULT_DATA_PLEITO.toString())))
            .andExpect(jsonPath("$.[*].totalEleitores").value(hasItem(DEFAULT_TOTAL_ELEITORES)))
            .andExpect(jsonPath("$.[*].totaVotos").value(hasItem(DEFAULT_TOTA_VOTOS)))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL.toString())))
            .andExpect(jsonPath("$.[*].hora").value(hasItem(DEFAULT_HORA.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getEleicao() throws Exception {
        // Initialize the database
        eleicaoRepository.saveAndFlush(eleicao);

        // Get the eleicao
        restEleicaoMockMvc.perform(get("/api/eleicaos/{id}", eleicao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eleicao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.dataCadastro").value(DEFAULT_DATA_CADASTRO.toString()))
            .andExpect(jsonPath("$.anoEleicao").value(DEFAULT_ANO_ELEICAO))
            .andExpect(jsonPath("$.dataPleito").value(DEFAULT_DATA_PLEITO.toString()))
            .andExpect(jsonPath("$.totalEleitores").value(DEFAULT_TOTAL_ELEITORES))
            .andExpect(jsonPath("$.totaVotos").value(DEFAULT_TOTA_VOTOS))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL.toString()))
            .andExpect(jsonPath("$.hora").value(DEFAULT_HORA.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEleicao() throws Exception {
        // Get the eleicao
        restEleicaoMockMvc.perform(get("/api/eleicaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEleicao() throws Exception {
        // Initialize the database
        eleicaoService.save(eleicao);

        int databaseSizeBeforeUpdate = eleicaoRepository.findAll().size();

        // Update the eleicao
        Eleicao updatedEleicao = eleicaoRepository.findById(eleicao.getId()).get();
        // Disconnect from session so that the updates on updatedEleicao are not directly saved in db
        em.detach(updatedEleicao);
        updatedEleicao
            .nome(UPDATED_NOME)
            .dataCadastro(UPDATED_DATA_CADASTRO)
            .anoEleicao(UPDATED_ANO_ELEICAO)
            .dataPleito(UPDATED_DATA_PLEITO)
            .totalEleitores(UPDATED_TOTAL_ELEITORES)
            .totaVotos(UPDATED_TOTA_VOTOS)
            .local(UPDATED_LOCAL)
            .hora(UPDATED_HORA)
            .obs(UPDATED_OBS);

        restEleicaoMockMvc.perform(put("/api/eleicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEleicao)))
            .andExpect(status().isOk());

        // Validate the Eleicao in the database
        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeUpdate);
        Eleicao testEleicao = eleicaoList.get(eleicaoList.size() - 1);
        assertThat(testEleicao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testEleicao.getDataCadastro()).isEqualTo(UPDATED_DATA_CADASTRO);
        assertThat(testEleicao.getAnoEleicao()).isEqualTo(UPDATED_ANO_ELEICAO);
        assertThat(testEleicao.getDataPleito()).isEqualTo(UPDATED_DATA_PLEITO);
        assertThat(testEleicao.getTotalEleitores()).isEqualTo(UPDATED_TOTAL_ELEITORES);
        assertThat(testEleicao.getTotaVotos()).isEqualTo(UPDATED_TOTA_VOTOS);
        assertThat(testEleicao.getLocal()).isEqualTo(UPDATED_LOCAL);
        assertThat(testEleicao.getHora()).isEqualTo(UPDATED_HORA);
        assertThat(testEleicao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingEleicao() throws Exception {
        int databaseSizeBeforeUpdate = eleicaoRepository.findAll().size();

        // Create the Eleicao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEleicaoMockMvc.perform(put("/api/eleicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eleicao)))
            .andExpect(status().isBadRequest());

        // Validate the Eleicao in the database
        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEleicao() throws Exception {
        // Initialize the database
        eleicaoService.save(eleicao);

        int databaseSizeBeforeDelete = eleicaoRepository.findAll().size();

        // Get the eleicao
        restEleicaoMockMvc.perform(delete("/api/eleicaos/{id}", eleicao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        assertThat(eleicaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Eleicao.class);
        Eleicao eleicao1 = new Eleicao();
        eleicao1.setId(1L);
        Eleicao eleicao2 = new Eleicao();
        eleicao2.setId(eleicao1.getId());
        assertThat(eleicao1).isEqualTo(eleicao2);
        eleicao2.setId(2L);
        assertThat(eleicao1).isNotEqualTo(eleicao2);
        eleicao1.setId(null);
        assertThat(eleicao1).isNotEqualTo(eleicao2);
    }
}
