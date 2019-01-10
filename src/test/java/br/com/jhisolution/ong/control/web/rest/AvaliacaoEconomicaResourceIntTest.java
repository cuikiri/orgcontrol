package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.AvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.AvaliacaoEconomicaRepository;
import br.com.jhisolution.ong.control.service.AvaliacaoEconomicaService;
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
 * Test class for the AvaliacaoEconomicaResource REST controller.
 *
 * @see AvaliacaoEconomicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AvaliacaoEconomicaResourceIntTest {

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
    private AvaliacaoEconomicaRepository avaliacaoEconomicaRepository;

    @Autowired
    private AvaliacaoEconomicaService avaliacaoEconomicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAvaliacaoEconomicaMockMvc;

    private AvaliacaoEconomica avaliacaoEconomica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AvaliacaoEconomicaResource avaliacaoEconomicaResource = new AvaliacaoEconomicaResource(avaliacaoEconomicaService);
        this.restAvaliacaoEconomicaMockMvc = MockMvcBuilders.standaloneSetup(avaliacaoEconomicaResource)
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
    public static AvaliacaoEconomica createEntity(EntityManager em) {
        AvaliacaoEconomica avaliacaoEconomica = new AvaliacaoEconomica()
            .nome(DEFAULT_NOME)
            .data(DEFAULT_DATA)
            .tema(DEFAULT_TEMA)
            .descricao(DEFAULT_DESCRICAO)
            .dataInicio(DEFAULT_DATA_INICIO)
            .dataFim(DEFAULT_DATA_FIM)
            .obs(DEFAULT_OBS);
        return avaliacaoEconomica;
    }

    @Before
    public void initTest() {
        avaliacaoEconomica = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeCreate = avaliacaoEconomicaRepository.findAll().size();

        // Create the AvaliacaoEconomica
        restAvaliacaoEconomicaMockMvc.perform(post("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoEconomica)))
            .andExpect(status().isCreated());

        // Validate the AvaliacaoEconomica in the database
        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate + 1);
        AvaliacaoEconomica testAvaliacaoEconomica = avaliacaoEconomicaList.get(avaliacaoEconomicaList.size() - 1);
        assertThat(testAvaliacaoEconomica.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAvaliacaoEconomica.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAvaliacaoEconomica.getTema()).isEqualTo(DEFAULT_TEMA);
        assertThat(testAvaliacaoEconomica.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testAvaliacaoEconomica.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testAvaliacaoEconomica.getDataFim()).isEqualTo(DEFAULT_DATA_FIM);
        assertThat(testAvaliacaoEconomica.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createAvaliacaoEconomicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avaliacaoEconomicaRepository.findAll().size();

        // Create the AvaliacaoEconomica with an existing ID
        avaliacaoEconomica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvaliacaoEconomicaMockMvc.perform(post("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the AvaliacaoEconomica in the database
        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoEconomicaRepository.findAll().size();
        // set the field null
        avaliacaoEconomica.setNome(null);

        // Create the AvaliacaoEconomica, which fails.

        restAvaliacaoEconomicaMockMvc.perform(post("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoEconomicaRepository.findAll().size();
        // set the field null
        avaliacaoEconomica.setData(null);

        // Create the AvaliacaoEconomica, which fails.

        restAvaliacaoEconomicaMockMvc.perform(post("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoEconomicaRepository.findAll().size();
        // set the field null
        avaliacaoEconomica.setDataInicio(null);

        // Create the AvaliacaoEconomica, which fails.

        restAvaliacaoEconomicaMockMvc.perform(post("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataFimIsRequired() throws Exception {
        int databaseSizeBeforeTest = avaliacaoEconomicaRepository.findAll().size();
        // set the field null
        avaliacaoEconomica.setDataFim(null);

        // Create the AvaliacaoEconomica, which fails.

        restAvaliacaoEconomicaMockMvc.perform(post("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvaliacaoEconomicas() throws Exception {
        // Initialize the database
        avaliacaoEconomicaRepository.saveAndFlush(avaliacaoEconomica);

        // Get all the avaliacaoEconomicaList
        restAvaliacaoEconomicaMockMvc.perform(get("/api/avaliacao-economicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avaliacaoEconomica.getId().intValue())))
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
    public void getAvaliacaoEconomica() throws Exception {
        // Initialize the database
        avaliacaoEconomicaRepository.saveAndFlush(avaliacaoEconomica);

        // Get the avaliacaoEconomica
        restAvaliacaoEconomicaMockMvc.perform(get("/api/avaliacao-economicas/{id}", avaliacaoEconomica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(avaliacaoEconomica.getId().intValue()))
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
    public void getNonExistingAvaliacaoEconomica() throws Exception {
        // Get the avaliacaoEconomica
        restAvaliacaoEconomicaMockMvc.perform(get("/api/avaliacao-economicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvaliacaoEconomica() throws Exception {
        // Initialize the database
        avaliacaoEconomicaService.save(avaliacaoEconomica);

        int databaseSizeBeforeUpdate = avaliacaoEconomicaRepository.findAll().size();

        // Update the avaliacaoEconomica
        AvaliacaoEconomica updatedAvaliacaoEconomica = avaliacaoEconomicaRepository.findById(avaliacaoEconomica.getId()).get();
        // Disconnect from session so that the updates on updatedAvaliacaoEconomica are not directly saved in db
        em.detach(updatedAvaliacaoEconomica);
        updatedAvaliacaoEconomica
            .nome(UPDATED_NOME)
            .data(UPDATED_DATA)
            .tema(UPDATED_TEMA)
            .descricao(UPDATED_DESCRICAO)
            .dataInicio(UPDATED_DATA_INICIO)
            .dataFim(UPDATED_DATA_FIM)
            .obs(UPDATED_OBS);

        restAvaliacaoEconomicaMockMvc.perform(put("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAvaliacaoEconomica)))
            .andExpect(status().isOk());

        // Validate the AvaliacaoEconomica in the database
        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
        AvaliacaoEconomica testAvaliacaoEconomica = avaliacaoEconomicaList.get(avaliacaoEconomicaList.size() - 1);
        assertThat(testAvaliacaoEconomica.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAvaliacaoEconomica.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAvaliacaoEconomica.getTema()).isEqualTo(UPDATED_TEMA);
        assertThat(testAvaliacaoEconomica.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testAvaliacaoEconomica.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testAvaliacaoEconomica.getDataFim()).isEqualTo(UPDATED_DATA_FIM);
        assertThat(testAvaliacaoEconomica.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeUpdate = avaliacaoEconomicaRepository.findAll().size();

        // Create the AvaliacaoEconomica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvaliacaoEconomicaMockMvc.perform(put("/api/avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the AvaliacaoEconomica in the database
        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvaliacaoEconomica() throws Exception {
        // Initialize the database
        avaliacaoEconomicaService.save(avaliacaoEconomica);

        int databaseSizeBeforeDelete = avaliacaoEconomicaRepository.findAll().size();

        // Get the avaliacaoEconomica
        restAvaliacaoEconomicaMockMvc.perform(delete("/api/avaliacao-economicas/{id}", avaliacaoEconomica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AvaliacaoEconomica> avaliacaoEconomicaList = avaliacaoEconomicaRepository.findAll();
        assertThat(avaliacaoEconomicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvaliacaoEconomica.class);
        AvaliacaoEconomica avaliacaoEconomica1 = new AvaliacaoEconomica();
        avaliacaoEconomica1.setId(1L);
        AvaliacaoEconomica avaliacaoEconomica2 = new AvaliacaoEconomica();
        avaliacaoEconomica2.setId(avaliacaoEconomica1.getId());
        assertThat(avaliacaoEconomica1).isEqualTo(avaliacaoEconomica2);
        avaliacaoEconomica2.setId(2L);
        assertThat(avaliacaoEconomica1).isNotEqualTo(avaliacaoEconomica2);
        avaliacaoEconomica1.setId(null);
        assertThat(avaliacaoEconomica1).isNotEqualTo(avaliacaoEconomica2);
    }
}
