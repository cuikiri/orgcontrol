package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Diario;
import br.com.jhisolution.ong.control.repository.DiarioRepository;
import br.com.jhisolution.ong.control.service.DiarioService;
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
 * Test class for the DiarioResource REST controller.
 *
 * @see DiarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class DiarioResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private DiarioRepository diarioRepository;

    @Autowired
    private DiarioService diarioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDiarioMockMvc;

    private Diario diario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiarioResource diarioResource = new DiarioResource(diarioService);
        this.restDiarioMockMvc = MockMvcBuilders.standaloneSetup(diarioResource)
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
    public static Diario createEntity(EntityManager em) {
        Diario diario = new Diario()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return diario;
    }

    @Before
    public void initTest() {
        diario = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiario() throws Exception {
        int databaseSizeBeforeCreate = diarioRepository.findAll().size();

        // Create the Diario
        restDiarioMockMvc.perform(post("/api/diarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diario)))
            .andExpect(status().isCreated());

        // Validate the Diario in the database
        List<Diario> diarioList = diarioRepository.findAll();
        assertThat(diarioList).hasSize(databaseSizeBeforeCreate + 1);
        Diario testDiario = diarioList.get(diarioList.size() - 1);
        assertThat(testDiario.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testDiario.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createDiarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diarioRepository.findAll().size();

        // Create the Diario with an existing ID
        diario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiarioMockMvc.perform(post("/api/diarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diario)))
            .andExpect(status().isBadRequest());

        // Validate the Diario in the database
        List<Diario> diarioList = diarioRepository.findAll();
        assertThat(diarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = diarioRepository.findAll().size();
        // set the field null
        diario.setNome(null);

        // Create the Diario, which fails.

        restDiarioMockMvc.perform(post("/api/diarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diario)))
            .andExpect(status().isBadRequest());

        List<Diario> diarioList = diarioRepository.findAll();
        assertThat(diarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDiarios() throws Exception {
        // Initialize the database
        diarioRepository.saveAndFlush(diario);

        // Get all the diarioList
        restDiarioMockMvc.perform(get("/api/diarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diario.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getDiario() throws Exception {
        // Initialize the database
        diarioRepository.saveAndFlush(diario);

        // Get the diario
        restDiarioMockMvc.perform(get("/api/diarios/{id}", diario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(diario.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDiario() throws Exception {
        // Get the diario
        restDiarioMockMvc.perform(get("/api/diarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiario() throws Exception {
        // Initialize the database
        diarioService.save(diario);

        int databaseSizeBeforeUpdate = diarioRepository.findAll().size();

        // Update the diario
        Diario updatedDiario = diarioRepository.findById(diario.getId()).get();
        // Disconnect from session so that the updates on updatedDiario are not directly saved in db
        em.detach(updatedDiario);
        updatedDiario
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restDiarioMockMvc.perform(put("/api/diarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDiario)))
            .andExpect(status().isOk());

        // Validate the Diario in the database
        List<Diario> diarioList = diarioRepository.findAll();
        assertThat(diarioList).hasSize(databaseSizeBeforeUpdate);
        Diario testDiario = diarioList.get(diarioList.size() - 1);
        assertThat(testDiario.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testDiario.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingDiario() throws Exception {
        int databaseSizeBeforeUpdate = diarioRepository.findAll().size();

        // Create the Diario

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiarioMockMvc.perform(put("/api/diarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diario)))
            .andExpect(status().isBadRequest());

        // Validate the Diario in the database
        List<Diario> diarioList = diarioRepository.findAll();
        assertThat(diarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDiario() throws Exception {
        // Initialize the database
        diarioService.save(diario);

        int databaseSizeBeforeDelete = diarioRepository.findAll().size();

        // Get the diario
        restDiarioMockMvc.perform(delete("/api/diarios/{id}", diario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Diario> diarioList = diarioRepository.findAll();
        assertThat(diarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Diario.class);
        Diario diario1 = new Diario();
        diario1.setId(1L);
        Diario diario2 = new Diario();
        diario2.setId(diario1.getId());
        assertThat(diario1).isEqualTo(diario2);
        diario2.setId(2L);
        assertThat(diario1).isNotEqualTo(diario2);
        diario1.setId(null);
        assertThat(diario1).isNotEqualTo(diario2);
    }
}
