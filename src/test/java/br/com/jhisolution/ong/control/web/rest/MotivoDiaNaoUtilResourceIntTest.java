package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.MotivoDiaNaoUtil;
import br.com.jhisolution.ong.control.repository.MotivoDiaNaoUtilRepository;
import br.com.jhisolution.ong.control.service.MotivoDiaNaoUtilService;
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
 * Test class for the MotivoDiaNaoUtilResource REST controller.
 *
 * @see MotivoDiaNaoUtilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class MotivoDiaNaoUtilResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private MotivoDiaNaoUtilRepository motivoDiaNaoUtilRepository;

    @Autowired
    private MotivoDiaNaoUtilService motivoDiaNaoUtilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMotivoDiaNaoUtilMockMvc;

    private MotivoDiaNaoUtil motivoDiaNaoUtil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MotivoDiaNaoUtilResource motivoDiaNaoUtilResource = new MotivoDiaNaoUtilResource(motivoDiaNaoUtilService);
        this.restMotivoDiaNaoUtilMockMvc = MockMvcBuilders.standaloneSetup(motivoDiaNaoUtilResource)
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
    public static MotivoDiaNaoUtil createEntity(EntityManager em) {
        MotivoDiaNaoUtil motivoDiaNaoUtil = new MotivoDiaNaoUtil()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return motivoDiaNaoUtil;
    }

    @Before
    public void initTest() {
        motivoDiaNaoUtil = createEntity(em);
    }

    @Test
    @Transactional
    public void createMotivoDiaNaoUtil() throws Exception {
        int databaseSizeBeforeCreate = motivoDiaNaoUtilRepository.findAll().size();

        // Create the MotivoDiaNaoUtil
        restMotivoDiaNaoUtilMockMvc.perform(post("/api/motivo-dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motivoDiaNaoUtil)))
            .andExpect(status().isCreated());

        // Validate the MotivoDiaNaoUtil in the database
        List<MotivoDiaNaoUtil> motivoDiaNaoUtilList = motivoDiaNaoUtilRepository.findAll();
        assertThat(motivoDiaNaoUtilList).hasSize(databaseSizeBeforeCreate + 1);
        MotivoDiaNaoUtil testMotivoDiaNaoUtil = motivoDiaNaoUtilList.get(motivoDiaNaoUtilList.size() - 1);
        assertThat(testMotivoDiaNaoUtil.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testMotivoDiaNaoUtil.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createMotivoDiaNaoUtilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = motivoDiaNaoUtilRepository.findAll().size();

        // Create the MotivoDiaNaoUtil with an existing ID
        motivoDiaNaoUtil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotivoDiaNaoUtilMockMvc.perform(post("/api/motivo-dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motivoDiaNaoUtil)))
            .andExpect(status().isBadRequest());

        // Validate the MotivoDiaNaoUtil in the database
        List<MotivoDiaNaoUtil> motivoDiaNaoUtilList = motivoDiaNaoUtilRepository.findAll();
        assertThat(motivoDiaNaoUtilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = motivoDiaNaoUtilRepository.findAll().size();
        // set the field null
        motivoDiaNaoUtil.setNome(null);

        // Create the MotivoDiaNaoUtil, which fails.

        restMotivoDiaNaoUtilMockMvc.perform(post("/api/motivo-dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motivoDiaNaoUtil)))
            .andExpect(status().isBadRequest());

        List<MotivoDiaNaoUtil> motivoDiaNaoUtilList = motivoDiaNaoUtilRepository.findAll();
        assertThat(motivoDiaNaoUtilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMotivoDiaNaoUtils() throws Exception {
        // Initialize the database
        motivoDiaNaoUtilRepository.saveAndFlush(motivoDiaNaoUtil);

        // Get all the motivoDiaNaoUtilList
        restMotivoDiaNaoUtilMockMvc.perform(get("/api/motivo-dia-nao-utils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motivoDiaNaoUtil.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getMotivoDiaNaoUtil() throws Exception {
        // Initialize the database
        motivoDiaNaoUtilRepository.saveAndFlush(motivoDiaNaoUtil);

        // Get the motivoDiaNaoUtil
        restMotivoDiaNaoUtilMockMvc.perform(get("/api/motivo-dia-nao-utils/{id}", motivoDiaNaoUtil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(motivoDiaNaoUtil.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMotivoDiaNaoUtil() throws Exception {
        // Get the motivoDiaNaoUtil
        restMotivoDiaNaoUtilMockMvc.perform(get("/api/motivo-dia-nao-utils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMotivoDiaNaoUtil() throws Exception {
        // Initialize the database
        motivoDiaNaoUtilService.save(motivoDiaNaoUtil);

        int databaseSizeBeforeUpdate = motivoDiaNaoUtilRepository.findAll().size();

        // Update the motivoDiaNaoUtil
        MotivoDiaNaoUtil updatedMotivoDiaNaoUtil = motivoDiaNaoUtilRepository.findById(motivoDiaNaoUtil.getId()).get();
        // Disconnect from session so that the updates on updatedMotivoDiaNaoUtil are not directly saved in db
        em.detach(updatedMotivoDiaNaoUtil);
        updatedMotivoDiaNaoUtil
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restMotivoDiaNaoUtilMockMvc.perform(put("/api/motivo-dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMotivoDiaNaoUtil)))
            .andExpect(status().isOk());

        // Validate the MotivoDiaNaoUtil in the database
        List<MotivoDiaNaoUtil> motivoDiaNaoUtilList = motivoDiaNaoUtilRepository.findAll();
        assertThat(motivoDiaNaoUtilList).hasSize(databaseSizeBeforeUpdate);
        MotivoDiaNaoUtil testMotivoDiaNaoUtil = motivoDiaNaoUtilList.get(motivoDiaNaoUtilList.size() - 1);
        assertThat(testMotivoDiaNaoUtil.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testMotivoDiaNaoUtil.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingMotivoDiaNaoUtil() throws Exception {
        int databaseSizeBeforeUpdate = motivoDiaNaoUtilRepository.findAll().size();

        // Create the MotivoDiaNaoUtil

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMotivoDiaNaoUtilMockMvc.perform(put("/api/motivo-dia-nao-utils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motivoDiaNaoUtil)))
            .andExpect(status().isBadRequest());

        // Validate the MotivoDiaNaoUtil in the database
        List<MotivoDiaNaoUtil> motivoDiaNaoUtilList = motivoDiaNaoUtilRepository.findAll();
        assertThat(motivoDiaNaoUtilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMotivoDiaNaoUtil() throws Exception {
        // Initialize the database
        motivoDiaNaoUtilService.save(motivoDiaNaoUtil);

        int databaseSizeBeforeDelete = motivoDiaNaoUtilRepository.findAll().size();

        // Get the motivoDiaNaoUtil
        restMotivoDiaNaoUtilMockMvc.perform(delete("/api/motivo-dia-nao-utils/{id}", motivoDiaNaoUtil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MotivoDiaNaoUtil> motivoDiaNaoUtilList = motivoDiaNaoUtilRepository.findAll();
        assertThat(motivoDiaNaoUtilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotivoDiaNaoUtil.class);
        MotivoDiaNaoUtil motivoDiaNaoUtil1 = new MotivoDiaNaoUtil();
        motivoDiaNaoUtil1.setId(1L);
        MotivoDiaNaoUtil motivoDiaNaoUtil2 = new MotivoDiaNaoUtil();
        motivoDiaNaoUtil2.setId(motivoDiaNaoUtil1.getId());
        assertThat(motivoDiaNaoUtil1).isEqualTo(motivoDiaNaoUtil2);
        motivoDiaNaoUtil2.setId(2L);
        assertThat(motivoDiaNaoUtil1).isNotEqualTo(motivoDiaNaoUtil2);
        motivoDiaNaoUtil1.setId(null);
        assertThat(motivoDiaNaoUtil1).isNotEqualTo(motivoDiaNaoUtil2);
    }
}
