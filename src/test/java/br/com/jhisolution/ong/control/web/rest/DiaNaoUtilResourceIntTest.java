package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.DiaNaoUtil;
import br.com.jhisolution.ong.control.repository.DiaNaoUtilRepository;
import br.com.jhisolution.ong.control.service.DiaNaoUtilService;
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
 * Test class for the DiaNaoUtilResource REST controller.
 *
 * @see DiaNaoUtilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class DiaNaoUtilResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DiaNaoUtilRepository diaNaoUtilRepository;

    @Autowired
    private DiaNaoUtilService diaNaoUtilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDiaNaoUtilMockMvc;

    private DiaNaoUtil diaNaoUtil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiaNaoUtilResource diaNaoUtilResource = new DiaNaoUtilResource(diaNaoUtilService);
        this.restDiaNaoUtilMockMvc = MockMvcBuilders.standaloneSetup(diaNaoUtilResource)
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
    public static DiaNaoUtil createEntity(EntityManager em) {
        DiaNaoUtil diaNaoUtil = new DiaNaoUtil()
            .nome(DEFAULT_NOME)
            .data(DEFAULT_DATA);
        return diaNaoUtil;
    }

    @Before
    public void initTest() {
        diaNaoUtil = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiaNaoUtil() throws Exception {
        int databaseSizeBeforeCreate = diaNaoUtilRepository.findAll().size();

        // Create the DiaNaoUtil
        restDiaNaoUtilMockMvc.perform(post("/api/dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaNaoUtil)))
            .andExpect(status().isCreated());

        // Validate the DiaNaoUtil in the database
        List<DiaNaoUtil> diaNaoUtilList = diaNaoUtilRepository.findAll();
        assertThat(diaNaoUtilList).hasSize(databaseSizeBeforeCreate + 1);
        DiaNaoUtil testDiaNaoUtil = diaNaoUtilList.get(diaNaoUtilList.size() - 1);
        assertThat(testDiaNaoUtil.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testDiaNaoUtil.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createDiaNaoUtilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diaNaoUtilRepository.findAll().size();

        // Create the DiaNaoUtil with an existing ID
        diaNaoUtil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiaNaoUtilMockMvc.perform(post("/api/dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaNaoUtil)))
            .andExpect(status().isBadRequest());

        // Validate the DiaNaoUtil in the database
        List<DiaNaoUtil> diaNaoUtilList = diaNaoUtilRepository.findAll();
        assertThat(diaNaoUtilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = diaNaoUtilRepository.findAll().size();
        // set the field null
        diaNaoUtil.setNome(null);

        // Create the DiaNaoUtil, which fails.

        restDiaNaoUtilMockMvc.perform(post("/api/dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaNaoUtil)))
            .andExpect(status().isBadRequest());

        List<DiaNaoUtil> diaNaoUtilList = diaNaoUtilRepository.findAll();
        assertThat(diaNaoUtilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = diaNaoUtilRepository.findAll().size();
        // set the field null
        diaNaoUtil.setData(null);

        // Create the DiaNaoUtil, which fails.

        restDiaNaoUtilMockMvc.perform(post("/api/dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaNaoUtil)))
            .andExpect(status().isBadRequest());

        List<DiaNaoUtil> diaNaoUtilList = diaNaoUtilRepository.findAll();
        assertThat(diaNaoUtilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDiaNaoUtils() throws Exception {
        // Initialize the database
        diaNaoUtilRepository.saveAndFlush(diaNaoUtil);

        // Get all the diaNaoUtilList
        restDiaNaoUtilMockMvc.perform(get("/api/dia-nao-utils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diaNaoUtil.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getDiaNaoUtil() throws Exception {
        // Initialize the database
        diaNaoUtilRepository.saveAndFlush(diaNaoUtil);

        // Get the diaNaoUtil
        restDiaNaoUtilMockMvc.perform(get("/api/dia-nao-utils/{id}", diaNaoUtil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(diaNaoUtil.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDiaNaoUtil() throws Exception {
        // Get the diaNaoUtil
        restDiaNaoUtilMockMvc.perform(get("/api/dia-nao-utils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiaNaoUtil() throws Exception {
        // Initialize the database
        diaNaoUtilService.save(diaNaoUtil);

        int databaseSizeBeforeUpdate = diaNaoUtilRepository.findAll().size();

        // Update the diaNaoUtil
        DiaNaoUtil updatedDiaNaoUtil = diaNaoUtilRepository.findById(diaNaoUtil.getId()).get();
        // Disconnect from session so that the updates on updatedDiaNaoUtil are not directly saved in db
        em.detach(updatedDiaNaoUtil);
        updatedDiaNaoUtil
            .nome(UPDATED_NOME)
            .data(UPDATED_DATA);

        restDiaNaoUtilMockMvc.perform(put("/api/dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDiaNaoUtil)))
            .andExpect(status().isOk());

        // Validate the DiaNaoUtil in the database
        List<DiaNaoUtil> diaNaoUtilList = diaNaoUtilRepository.findAll();
        assertThat(diaNaoUtilList).hasSize(databaseSizeBeforeUpdate);
        DiaNaoUtil testDiaNaoUtil = diaNaoUtilList.get(diaNaoUtilList.size() - 1);
        assertThat(testDiaNaoUtil.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testDiaNaoUtil.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingDiaNaoUtil() throws Exception {
        int databaseSizeBeforeUpdate = diaNaoUtilRepository.findAll().size();

        // Create the DiaNaoUtil

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiaNaoUtilMockMvc.perform(put("/api/dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaNaoUtil)))
            .andExpect(status().isBadRequest());

        // Validate the DiaNaoUtil in the database
        List<DiaNaoUtil> diaNaoUtilList = diaNaoUtilRepository.findAll();
        assertThat(diaNaoUtilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDiaNaoUtil() throws Exception {
        // Initialize the database
        diaNaoUtilService.save(diaNaoUtil);

        int databaseSizeBeforeDelete = diaNaoUtilRepository.findAll().size();

        // Get the diaNaoUtil
        restDiaNaoUtilMockMvc.perform(delete("/api/dia-nao-utils/{id}", diaNaoUtil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DiaNaoUtil> diaNaoUtilList = diaNaoUtilRepository.findAll();
        assertThat(diaNaoUtilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiaNaoUtil.class);
        DiaNaoUtil diaNaoUtil1 = new DiaNaoUtil();
        diaNaoUtil1.setId(1L);
        DiaNaoUtil diaNaoUtil2 = new DiaNaoUtil();
        diaNaoUtil2.setId(diaNaoUtil1.getId());
        assertThat(diaNaoUtil1).isEqualTo(diaNaoUtil2);
        diaNaoUtil2.setId(2L);
        assertThat(diaNaoUtil1).isNotEqualTo(diaNaoUtil2);
        diaNaoUtil1.setId(null);
        assertThat(diaNaoUtil1).isNotEqualTo(diaNaoUtil2);
    }
}
