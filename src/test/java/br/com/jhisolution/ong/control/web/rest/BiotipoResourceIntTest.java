package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Biotipo;
import br.com.jhisolution.ong.control.repository.BiotipoRepository;
import br.com.jhisolution.ong.control.service.BiotipoService;
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
 * Test class for the BiotipoResource REST controller.
 *
 * @see BiotipoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class BiotipoResourceIntTest {

    private static final Float DEFAULT_VALOR = 1F;
    private static final Float UPDATED_VALOR = 2F;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private BiotipoRepository biotipoRepository;

    @Autowired
    private BiotipoService biotipoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBiotipoMockMvc;

    private Biotipo biotipo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BiotipoResource biotipoResource = new BiotipoResource(biotipoService);
        this.restBiotipoMockMvc = MockMvcBuilders.standaloneSetup(biotipoResource)
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
    public static Biotipo createEntity(EntityManager em) {
        Biotipo biotipo = new Biotipo()
            .valor(DEFAULT_VALOR)
            .obs(DEFAULT_OBS);
        return biotipo;
    }

    @Before
    public void initTest() {
        biotipo = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiotipo() throws Exception {
        int databaseSizeBeforeCreate = biotipoRepository.findAll().size();

        // Create the Biotipo
        restBiotipoMockMvc.perform(post("/api/biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(biotipo)))
            .andExpect(status().isCreated());

        // Validate the Biotipo in the database
        List<Biotipo> biotipoList = biotipoRepository.findAll();
        assertThat(biotipoList).hasSize(databaseSizeBeforeCreate + 1);
        Biotipo testBiotipo = biotipoList.get(biotipoList.size() - 1);
        assertThat(testBiotipo.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testBiotipo.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createBiotipoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biotipoRepository.findAll().size();

        // Create the Biotipo with an existing ID
        biotipo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiotipoMockMvc.perform(post("/api/biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(biotipo)))
            .andExpect(status().isBadRequest());

        // Validate the Biotipo in the database
        List<Biotipo> biotipoList = biotipoRepository.findAll();
        assertThat(biotipoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = biotipoRepository.findAll().size();
        // set the field null
        biotipo.setValor(null);

        // Create the Biotipo, which fails.

        restBiotipoMockMvc.perform(post("/api/biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(biotipo)))
            .andExpect(status().isBadRequest());

        List<Biotipo> biotipoList = biotipoRepository.findAll();
        assertThat(biotipoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBiotipos() throws Exception {
        // Initialize the database
        biotipoRepository.saveAndFlush(biotipo);

        // Get all the biotipoList
        restBiotipoMockMvc.perform(get("/api/biotipos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biotipo.getId().intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getBiotipo() throws Exception {
        // Initialize the database
        biotipoRepository.saveAndFlush(biotipo);

        // Get the biotipo
        restBiotipoMockMvc.perform(get("/api/biotipos/{id}", biotipo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(biotipo.getId().intValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBiotipo() throws Exception {
        // Get the biotipo
        restBiotipoMockMvc.perform(get("/api/biotipos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiotipo() throws Exception {
        // Initialize the database
        biotipoService.save(biotipo);

        int databaseSizeBeforeUpdate = biotipoRepository.findAll().size();

        // Update the biotipo
        Biotipo updatedBiotipo = biotipoRepository.findById(biotipo.getId()).get();
        // Disconnect from session so that the updates on updatedBiotipo are not directly saved in db
        em.detach(updatedBiotipo);
        updatedBiotipo
            .valor(UPDATED_VALOR)
            .obs(UPDATED_OBS);

        restBiotipoMockMvc.perform(put("/api/biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBiotipo)))
            .andExpect(status().isOk());

        // Validate the Biotipo in the database
        List<Biotipo> biotipoList = biotipoRepository.findAll();
        assertThat(biotipoList).hasSize(databaseSizeBeforeUpdate);
        Biotipo testBiotipo = biotipoList.get(biotipoList.size() - 1);
        assertThat(testBiotipo.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testBiotipo.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingBiotipo() throws Exception {
        int databaseSizeBeforeUpdate = biotipoRepository.findAll().size();

        // Create the Biotipo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiotipoMockMvc.perform(put("/api/biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(biotipo)))
            .andExpect(status().isBadRequest());

        // Validate the Biotipo in the database
        List<Biotipo> biotipoList = biotipoRepository.findAll();
        assertThat(biotipoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiotipo() throws Exception {
        // Initialize the database
        biotipoService.save(biotipo);

        int databaseSizeBeforeDelete = biotipoRepository.findAll().size();

        // Get the biotipo
        restBiotipoMockMvc.perform(delete("/api/biotipos/{id}", biotipo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Biotipo> biotipoList = biotipoRepository.findAll();
        assertThat(biotipoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Biotipo.class);
        Biotipo biotipo1 = new Biotipo();
        biotipo1.setId(1L);
        Biotipo biotipo2 = new Biotipo();
        biotipo2.setId(biotipo1.getId());
        assertThat(biotipo1).isEqualTo(biotipo2);
        biotipo2.setId(2L);
        assertThat(biotipo1).isNotEqualTo(biotipo2);
        biotipo1.setId(null);
        assertThat(biotipo1).isNotEqualTo(biotipo2);
    }
}
