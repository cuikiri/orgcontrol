package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Chapa;
import br.com.jhisolution.ong.control.repository.ChapaRepository;
import br.com.jhisolution.ong.control.service.ChapaService;
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
 * Test class for the ChapaResource REST controller.
 *
 * @see ChapaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ChapaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_CADASTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_CADASTRO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TOTA_VOTOS = 1;
    private static final Integer UPDATED_TOTA_VOTOS = 2;

    private static final Boolean DEFAULT_VENCEDOR = false;
    private static final Boolean UPDATED_VENCEDOR = true;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ChapaRepository chapaRepository;

    @Autowired
    private ChapaService chapaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChapaMockMvc;

    private Chapa chapa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChapaResource chapaResource = new ChapaResource(chapaService);
        this.restChapaMockMvc = MockMvcBuilders.standaloneSetup(chapaResource)
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
    public static Chapa createEntity(EntityManager em) {
        Chapa chapa = new Chapa()
            .nome(DEFAULT_NOME)
            .dataCadastro(DEFAULT_DATA_CADASTRO)
            .totaVotos(DEFAULT_TOTA_VOTOS)
            .vencedor(DEFAULT_VENCEDOR)
            .obs(DEFAULT_OBS);
        return chapa;
    }

    @Before
    public void initTest() {
        chapa = createEntity(em);
    }

    @Test
    @Transactional
    public void createChapa() throws Exception {
        int databaseSizeBeforeCreate = chapaRepository.findAll().size();

        // Create the Chapa
        restChapaMockMvc.perform(post("/api/chapas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapa)))
            .andExpect(status().isCreated());

        // Validate the Chapa in the database
        List<Chapa> chapaList = chapaRepository.findAll();
        assertThat(chapaList).hasSize(databaseSizeBeforeCreate + 1);
        Chapa testChapa = chapaList.get(chapaList.size() - 1);
        assertThat(testChapa.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testChapa.getDataCadastro()).isEqualTo(DEFAULT_DATA_CADASTRO);
        assertThat(testChapa.getTotaVotos()).isEqualTo(DEFAULT_TOTA_VOTOS);
        assertThat(testChapa.isVencedor()).isEqualTo(DEFAULT_VENCEDOR);
        assertThat(testChapa.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createChapaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chapaRepository.findAll().size();

        // Create the Chapa with an existing ID
        chapa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChapaMockMvc.perform(post("/api/chapas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapa)))
            .andExpect(status().isBadRequest());

        // Validate the Chapa in the database
        List<Chapa> chapaList = chapaRepository.findAll();
        assertThat(chapaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapaRepository.findAll().size();
        // set the field null
        chapa.setNome(null);

        // Create the Chapa, which fails.

        restChapaMockMvc.perform(post("/api/chapas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapa)))
            .andExpect(status().isBadRequest());

        List<Chapa> chapaList = chapaRepository.findAll();
        assertThat(chapaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapaRepository.findAll().size();
        // set the field null
        chapa.setDataCadastro(null);

        // Create the Chapa, which fails.

        restChapaMockMvc.perform(post("/api/chapas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapa)))
            .andExpect(status().isBadRequest());

        List<Chapa> chapaList = chapaRepository.findAll();
        assertThat(chapaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChapas() throws Exception {
        // Initialize the database
        chapaRepository.saveAndFlush(chapa);

        // Get all the chapaList
        restChapaMockMvc.perform(get("/api/chapas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chapa.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].dataCadastro").value(hasItem(DEFAULT_DATA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].totaVotos").value(hasItem(DEFAULT_TOTA_VOTOS)))
            .andExpect(jsonPath("$.[*].vencedor").value(hasItem(DEFAULT_VENCEDOR.booleanValue())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getChapa() throws Exception {
        // Initialize the database
        chapaRepository.saveAndFlush(chapa);

        // Get the chapa
        restChapaMockMvc.perform(get("/api/chapas/{id}", chapa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chapa.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.dataCadastro").value(DEFAULT_DATA_CADASTRO.toString()))
            .andExpect(jsonPath("$.totaVotos").value(DEFAULT_TOTA_VOTOS))
            .andExpect(jsonPath("$.vencedor").value(DEFAULT_VENCEDOR.booleanValue()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChapa() throws Exception {
        // Get the chapa
        restChapaMockMvc.perform(get("/api/chapas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChapa() throws Exception {
        // Initialize the database
        chapaService.save(chapa);

        int databaseSizeBeforeUpdate = chapaRepository.findAll().size();

        // Update the chapa
        Chapa updatedChapa = chapaRepository.findById(chapa.getId()).get();
        // Disconnect from session so that the updates on updatedChapa are not directly saved in db
        em.detach(updatedChapa);
        updatedChapa
            .nome(UPDATED_NOME)
            .dataCadastro(UPDATED_DATA_CADASTRO)
            .totaVotos(UPDATED_TOTA_VOTOS)
            .vencedor(UPDATED_VENCEDOR)
            .obs(UPDATED_OBS);

        restChapaMockMvc.perform(put("/api/chapas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChapa)))
            .andExpect(status().isOk());

        // Validate the Chapa in the database
        List<Chapa> chapaList = chapaRepository.findAll();
        assertThat(chapaList).hasSize(databaseSizeBeforeUpdate);
        Chapa testChapa = chapaList.get(chapaList.size() - 1);
        assertThat(testChapa.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testChapa.getDataCadastro()).isEqualTo(UPDATED_DATA_CADASTRO);
        assertThat(testChapa.getTotaVotos()).isEqualTo(UPDATED_TOTA_VOTOS);
        assertThat(testChapa.isVencedor()).isEqualTo(UPDATED_VENCEDOR);
        assertThat(testChapa.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingChapa() throws Exception {
        int databaseSizeBeforeUpdate = chapaRepository.findAll().size();

        // Create the Chapa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChapaMockMvc.perform(put("/api/chapas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapa)))
            .andExpect(status().isBadRequest());

        // Validate the Chapa in the database
        List<Chapa> chapaList = chapaRepository.findAll();
        assertThat(chapaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChapa() throws Exception {
        // Initialize the database
        chapaService.save(chapa);

        int databaseSizeBeforeDelete = chapaRepository.findAll().size();

        // Get the chapa
        restChapaMockMvc.perform(delete("/api/chapas/{id}", chapa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Chapa> chapaList = chapaRepository.findAll();
        assertThat(chapaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chapa.class);
        Chapa chapa1 = new Chapa();
        chapa1.setId(1L);
        Chapa chapa2 = new Chapa();
        chapa2.setId(chapa1.getId());
        assertThat(chapa1).isEqualTo(chapa2);
        chapa2.setId(2L);
        assertThat(chapa1).isNotEqualTo(chapa2);
        chapa1.setId(null);
        assertThat(chapa1).isNotEqualTo(chapa2);
    }
}
