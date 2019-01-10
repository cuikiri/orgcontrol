package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Advertencia;
import br.com.jhisolution.ong.control.repository.AdvertenciaRepository;
import br.com.jhisolution.ong.control.service.AdvertenciaService;
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
 * Test class for the AdvertenciaResource REST controller.
 *
 * @see AdvertenciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AdvertenciaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_ADVERTENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_ADVERTENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_HORA_ADVERTENCIA = "AAAAA";
    private static final String UPDATED_HORA_ADVERTENCIA = "BBBBB";

    private static final String DEFAULT_RESUMO = "AAAAAAAAAA";
    private static final String UPDATED_RESUMO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private AdvertenciaRepository advertenciaRepository;

    @Autowired
    private AdvertenciaService advertenciaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdvertenciaMockMvc;

    private Advertencia advertencia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdvertenciaResource advertenciaResource = new AdvertenciaResource(advertenciaService);
        this.restAdvertenciaMockMvc = MockMvcBuilders.standaloneSetup(advertenciaResource)
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
    public static Advertencia createEntity(EntityManager em) {
        Advertencia advertencia = new Advertencia()
            .nome(DEFAULT_NOME)
            .data(DEFAULT_DATA)
            .dataAdvertencia(DEFAULT_DATA_ADVERTENCIA)
            .horaAdvertencia(DEFAULT_HORA_ADVERTENCIA)
            .resumo(DEFAULT_RESUMO)
            .obs(DEFAULT_OBS);
        return advertencia;
    }

    @Before
    public void initTest() {
        advertencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdvertencia() throws Exception {
        int databaseSizeBeforeCreate = advertenciaRepository.findAll().size();

        // Create the Advertencia
        restAdvertenciaMockMvc.perform(post("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isCreated());

        // Validate the Advertencia in the database
        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Advertencia testAdvertencia = advertenciaList.get(advertenciaList.size() - 1);
        assertThat(testAdvertencia.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAdvertencia.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAdvertencia.getDataAdvertencia()).isEqualTo(DEFAULT_DATA_ADVERTENCIA);
        assertThat(testAdvertencia.getHoraAdvertencia()).isEqualTo(DEFAULT_HORA_ADVERTENCIA);
        assertThat(testAdvertencia.getResumo()).isEqualTo(DEFAULT_RESUMO);
        assertThat(testAdvertencia.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createAdvertenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = advertenciaRepository.findAll().size();

        // Create the Advertencia with an existing ID
        advertencia.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdvertenciaMockMvc.perform(post("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isBadRequest());

        // Validate the Advertencia in the database
        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertenciaRepository.findAll().size();
        // set the field null
        advertencia.setNome(null);

        // Create the Advertencia, which fails.

        restAdvertenciaMockMvc.perform(post("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isBadRequest());

        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertenciaRepository.findAll().size();
        // set the field null
        advertencia.setData(null);

        // Create the Advertencia, which fails.

        restAdvertenciaMockMvc.perform(post("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isBadRequest());

        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataAdvertenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertenciaRepository.findAll().size();
        // set the field null
        advertencia.setDataAdvertencia(null);

        // Create the Advertencia, which fails.

        restAdvertenciaMockMvc.perform(post("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isBadRequest());

        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraAdvertenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertenciaRepository.findAll().size();
        // set the field null
        advertencia.setHoraAdvertencia(null);

        // Create the Advertencia, which fails.

        restAdvertenciaMockMvc.perform(post("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isBadRequest());

        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResumoIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertenciaRepository.findAll().size();
        // set the field null
        advertencia.setResumo(null);

        // Create the Advertencia, which fails.

        restAdvertenciaMockMvc.perform(post("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isBadRequest());

        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdvertencias() throws Exception {
        // Initialize the database
        advertenciaRepository.saveAndFlush(advertencia);

        // Get all the advertenciaList
        restAdvertenciaMockMvc.perform(get("/api/advertencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(advertencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].dataAdvertencia").value(hasItem(DEFAULT_DATA_ADVERTENCIA.toString())))
            .andExpect(jsonPath("$.[*].horaAdvertencia").value(hasItem(DEFAULT_HORA_ADVERTENCIA.toString())))
            .andExpect(jsonPath("$.[*].resumo").value(hasItem(DEFAULT_RESUMO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getAdvertencia() throws Exception {
        // Initialize the database
        advertenciaRepository.saveAndFlush(advertencia);

        // Get the advertencia
        restAdvertenciaMockMvc.perform(get("/api/advertencias/{id}", advertencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(advertencia.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.dataAdvertencia").value(DEFAULT_DATA_ADVERTENCIA.toString()))
            .andExpect(jsonPath("$.horaAdvertencia").value(DEFAULT_HORA_ADVERTENCIA.toString()))
            .andExpect(jsonPath("$.resumo").value(DEFAULT_RESUMO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdvertencia() throws Exception {
        // Get the advertencia
        restAdvertenciaMockMvc.perform(get("/api/advertencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdvertencia() throws Exception {
        // Initialize the database
        advertenciaService.save(advertencia);

        int databaseSizeBeforeUpdate = advertenciaRepository.findAll().size();

        // Update the advertencia
        Advertencia updatedAdvertencia = advertenciaRepository.findById(advertencia.getId()).get();
        // Disconnect from session so that the updates on updatedAdvertencia are not directly saved in db
        em.detach(updatedAdvertencia);
        updatedAdvertencia
            .nome(UPDATED_NOME)
            .data(UPDATED_DATA)
            .dataAdvertencia(UPDATED_DATA_ADVERTENCIA)
            .horaAdvertencia(UPDATED_HORA_ADVERTENCIA)
            .resumo(UPDATED_RESUMO)
            .obs(UPDATED_OBS);

        restAdvertenciaMockMvc.perform(put("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdvertencia)))
            .andExpect(status().isOk());

        // Validate the Advertencia in the database
        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeUpdate);
        Advertencia testAdvertencia = advertenciaList.get(advertenciaList.size() - 1);
        assertThat(testAdvertencia.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAdvertencia.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAdvertencia.getDataAdvertencia()).isEqualTo(UPDATED_DATA_ADVERTENCIA);
        assertThat(testAdvertencia.getHoraAdvertencia()).isEqualTo(UPDATED_HORA_ADVERTENCIA);
        assertThat(testAdvertencia.getResumo()).isEqualTo(UPDATED_RESUMO);
        assertThat(testAdvertencia.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingAdvertencia() throws Exception {
        int databaseSizeBeforeUpdate = advertenciaRepository.findAll().size();

        // Create the Advertencia

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdvertenciaMockMvc.perform(put("/api/advertencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertencia)))
            .andExpect(status().isBadRequest());

        // Validate the Advertencia in the database
        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdvertencia() throws Exception {
        // Initialize the database
        advertenciaService.save(advertencia);

        int databaseSizeBeforeDelete = advertenciaRepository.findAll().size();

        // Get the advertencia
        restAdvertenciaMockMvc.perform(delete("/api/advertencias/{id}", advertencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Advertencia> advertenciaList = advertenciaRepository.findAll();
        assertThat(advertenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Advertencia.class);
        Advertencia advertencia1 = new Advertencia();
        advertencia1.setId(1L);
        Advertencia advertencia2 = new Advertencia();
        advertencia2.setId(advertencia1.getId());
        assertThat(advertencia1).isEqualTo(advertencia2);
        advertencia2.setId(2L);
        assertThat(advertencia1).isNotEqualTo(advertencia2);
        advertencia1.setId(null);
        assertThat(advertencia1).isNotEqualTo(advertencia2);
    }
}
