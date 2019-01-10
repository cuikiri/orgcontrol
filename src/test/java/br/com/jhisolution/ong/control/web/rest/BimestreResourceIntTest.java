package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Bimestre;
import br.com.jhisolution.ong.control.repository.BimestreRepository;
import br.com.jhisolution.ong.control.service.BimestreService;
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
 * Test class for the BimestreResource REST controller.
 *
 * @see BimestreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class BimestreResourceIntTest {

    private static final String DEFAULT_ABREVIATURA = "AAAAAAAAAA";
    private static final String UPDATED_ABREVIATURA = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPONENTE = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_FIM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_FIM = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final Integer DEFAULT_ATIVIDADES_PREVISTAS = 1;
    private static final Integer UPDATED_ATIVIDADES_PREVISTAS = 2;

    private static final Integer DEFAULT_ATIVIDADES_DADAS = 1;
    private static final Integer UPDATED_ATIVIDADES_DADAS = 2;

    private static final Integer DEFAULT_ATIVIDADES_REPOSTAS = 1;
    private static final Integer UPDATED_ATIVIDADES_REPOSTAS = 2;

    @Autowired
    private BimestreRepository bimestreRepository;

    @Autowired
    private BimestreService bimestreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBimestreMockMvc;

    private Bimestre bimestre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BimestreResource bimestreResource = new BimestreResource(bimestreService);
        this.restBimestreMockMvc = MockMvcBuilders.standaloneSetup(bimestreResource)
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
    public static Bimestre createEntity(EntityManager em) {
        Bimestre bimestre = new Bimestre()
            .abreviatura(DEFAULT_ABREVIATURA)
            .nome(DEFAULT_NOME)
            .componente(DEFAULT_COMPONENTE)
            .dataInicio(DEFAULT_DATA_INICIO)
            .dataFim(DEFAULT_DATA_FIM)
            .numero(DEFAULT_NUMERO)
            .atividadesPrevistas(DEFAULT_ATIVIDADES_PREVISTAS)
            .atividadesDadas(DEFAULT_ATIVIDADES_DADAS)
            .atividadesRepostas(DEFAULT_ATIVIDADES_REPOSTAS);
        return bimestre;
    }

    @Before
    public void initTest() {
        bimestre = createEntity(em);
    }

    @Test
    @Transactional
    public void createBimestre() throws Exception {
        int databaseSizeBeforeCreate = bimestreRepository.findAll().size();

        // Create the Bimestre
        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isCreated());

        // Validate the Bimestre in the database
        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeCreate + 1);
        Bimestre testBimestre = bimestreList.get(bimestreList.size() - 1);
        assertThat(testBimestre.getAbreviatura()).isEqualTo(DEFAULT_ABREVIATURA);
        assertThat(testBimestre.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testBimestre.getComponente()).isEqualTo(DEFAULT_COMPONENTE);
        assertThat(testBimestre.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testBimestre.getDataFim()).isEqualTo(DEFAULT_DATA_FIM);
        assertThat(testBimestre.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testBimestre.getAtividadesPrevistas()).isEqualTo(DEFAULT_ATIVIDADES_PREVISTAS);
        assertThat(testBimestre.getAtividadesDadas()).isEqualTo(DEFAULT_ATIVIDADES_DADAS);
        assertThat(testBimestre.getAtividadesRepostas()).isEqualTo(DEFAULT_ATIVIDADES_REPOSTAS);
    }

    @Test
    @Transactional
    public void createBimestreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bimestreRepository.findAll().size();

        // Create the Bimestre with an existing ID
        bimestre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        // Validate the Bimestre in the database
        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAbreviaturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setAbreviatura(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setNome(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setDataInicio(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataFimIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setDataFim(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setNumero(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAtividadesPrevistasIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setAtividadesPrevistas(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAtividadesDadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setAtividadesDadas(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAtividadesRepostasIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreRepository.findAll().size();
        // set the field null
        bimestre.setAtividadesRepostas(null);

        // Create the Bimestre, which fails.

        restBimestreMockMvc.perform(post("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBimestres() throws Exception {
        // Initialize the database
        bimestreRepository.saveAndFlush(bimestre);

        // Get all the bimestreList
        restBimestreMockMvc.perform(get("/api/bimestres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bimestre.getId().intValue())))
            .andExpect(jsonPath("$.[*].abreviatura").value(hasItem(DEFAULT_ABREVIATURA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].componente").value(hasItem(DEFAULT_COMPONENTE.toString())))
            .andExpect(jsonPath("$.[*].dataInicio").value(hasItem(DEFAULT_DATA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].dataFim").value(hasItem(DEFAULT_DATA_FIM.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].atividadesPrevistas").value(hasItem(DEFAULT_ATIVIDADES_PREVISTAS)))
            .andExpect(jsonPath("$.[*].atividadesDadas").value(hasItem(DEFAULT_ATIVIDADES_DADAS)))
            .andExpect(jsonPath("$.[*].atividadesRepostas").value(hasItem(DEFAULT_ATIVIDADES_REPOSTAS)));
    }
    
    @Test
    @Transactional
    public void getBimestre() throws Exception {
        // Initialize the database
        bimestreRepository.saveAndFlush(bimestre);

        // Get the bimestre
        restBimestreMockMvc.perform(get("/api/bimestres/{id}", bimestre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bimestre.getId().intValue()))
            .andExpect(jsonPath("$.abreviatura").value(DEFAULT_ABREVIATURA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.componente").value(DEFAULT_COMPONENTE.toString()))
            .andExpect(jsonPath("$.dataInicio").value(DEFAULT_DATA_INICIO.toString()))
            .andExpect(jsonPath("$.dataFim").value(DEFAULT_DATA_FIM.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.atividadesPrevistas").value(DEFAULT_ATIVIDADES_PREVISTAS))
            .andExpect(jsonPath("$.atividadesDadas").value(DEFAULT_ATIVIDADES_DADAS))
            .andExpect(jsonPath("$.atividadesRepostas").value(DEFAULT_ATIVIDADES_REPOSTAS));
    }

    @Test
    @Transactional
    public void getNonExistingBimestre() throws Exception {
        // Get the bimestre
        restBimestreMockMvc.perform(get("/api/bimestres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBimestre() throws Exception {
        // Initialize the database
        bimestreService.save(bimestre);

        int databaseSizeBeforeUpdate = bimestreRepository.findAll().size();

        // Update the bimestre
        Bimestre updatedBimestre = bimestreRepository.findById(bimestre.getId()).get();
        // Disconnect from session so that the updates on updatedBimestre are not directly saved in db
        em.detach(updatedBimestre);
        updatedBimestre
            .abreviatura(UPDATED_ABREVIATURA)
            .nome(UPDATED_NOME)
            .componente(UPDATED_COMPONENTE)
            .dataInicio(UPDATED_DATA_INICIO)
            .dataFim(UPDATED_DATA_FIM)
            .numero(UPDATED_NUMERO)
            .atividadesPrevistas(UPDATED_ATIVIDADES_PREVISTAS)
            .atividadesDadas(UPDATED_ATIVIDADES_DADAS)
            .atividadesRepostas(UPDATED_ATIVIDADES_REPOSTAS);

        restBimestreMockMvc.perform(put("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBimestre)))
            .andExpect(status().isOk());

        // Validate the Bimestre in the database
        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeUpdate);
        Bimestre testBimestre = bimestreList.get(bimestreList.size() - 1);
        assertThat(testBimestre.getAbreviatura()).isEqualTo(UPDATED_ABREVIATURA);
        assertThat(testBimestre.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testBimestre.getComponente()).isEqualTo(UPDATED_COMPONENTE);
        assertThat(testBimestre.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testBimestre.getDataFim()).isEqualTo(UPDATED_DATA_FIM);
        assertThat(testBimestre.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testBimestre.getAtividadesPrevistas()).isEqualTo(UPDATED_ATIVIDADES_PREVISTAS);
        assertThat(testBimestre.getAtividadesDadas()).isEqualTo(UPDATED_ATIVIDADES_DADAS);
        assertThat(testBimestre.getAtividadesRepostas()).isEqualTo(UPDATED_ATIVIDADES_REPOSTAS);
    }

    @Test
    @Transactional
    public void updateNonExistingBimestre() throws Exception {
        int databaseSizeBeforeUpdate = bimestreRepository.findAll().size();

        // Create the Bimestre

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBimestreMockMvc.perform(put("/api/bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestre)))
            .andExpect(status().isBadRequest());

        // Validate the Bimestre in the database
        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBimestre() throws Exception {
        // Initialize the database
        bimestreService.save(bimestre);

        int databaseSizeBeforeDelete = bimestreRepository.findAll().size();

        // Get the bimestre
        restBimestreMockMvc.perform(delete("/api/bimestres/{id}", bimestre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bimestre> bimestreList = bimestreRepository.findAll();
        assertThat(bimestreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bimestre.class);
        Bimestre bimestre1 = new Bimestre();
        bimestre1.setId(1L);
        Bimestre bimestre2 = new Bimestre();
        bimestre2.setId(bimestre1.getId());
        assertThat(bimestre1).isEqualTo(bimestre2);
        bimestre2.setId(2L);
        assertThat(bimestre1).isNotEqualTo(bimestre2);
        bimestre1.setId(null);
        assertThat(bimestre1).isNotEqualTo(bimestre2);
    }
}
