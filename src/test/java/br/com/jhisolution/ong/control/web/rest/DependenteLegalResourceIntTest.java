package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.DependenteLegal;
import br.com.jhisolution.ong.control.repository.DependenteLegalRepository;
import br.com.jhisolution.ong.control.service.DependenteLegalService;
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
 * Test class for the DependenteLegalResource REST controller.
 *
 * @see DependenteLegalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class DependenteLegalResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_NASCIMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCIMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private DependenteLegalRepository dependenteLegalRepository;

    @Autowired
    private DependenteLegalService dependenteLegalService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDependenteLegalMockMvc;

    private DependenteLegal dependenteLegal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DependenteLegalResource dependenteLegalResource = new DependenteLegalResource(dependenteLegalService);
        this.restDependenteLegalMockMvc = MockMvcBuilders.standaloneSetup(dependenteLegalResource)
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
    public static DependenteLegal createEntity(EntityManager em) {
        DependenteLegal dependenteLegal = new DependenteLegal()
            .nome(DEFAULT_NOME)
            .dataNascimento(DEFAULT_DATA_NASCIMENTO)
            .obs(DEFAULT_OBS);
        return dependenteLegal;
    }

    @Before
    public void initTest() {
        dependenteLegal = createEntity(em);
    }

    @Test
    @Transactional
    public void createDependenteLegal() throws Exception {
        int databaseSizeBeforeCreate = dependenteLegalRepository.findAll().size();

        // Create the DependenteLegal
        restDependenteLegalMockMvc.perform(post("/api/dependente-legals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependenteLegal)))
            .andExpect(status().isCreated());

        // Validate the DependenteLegal in the database
        List<DependenteLegal> dependenteLegalList = dependenteLegalRepository.findAll();
        assertThat(dependenteLegalList).hasSize(databaseSizeBeforeCreate + 1);
        DependenteLegal testDependenteLegal = dependenteLegalList.get(dependenteLegalList.size() - 1);
        assertThat(testDependenteLegal.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testDependenteLegal.getDataNascimento()).isEqualTo(DEFAULT_DATA_NASCIMENTO);
        assertThat(testDependenteLegal.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createDependenteLegalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dependenteLegalRepository.findAll().size();

        // Create the DependenteLegal with an existing ID
        dependenteLegal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDependenteLegalMockMvc.perform(post("/api/dependente-legals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependenteLegal)))
            .andExpect(status().isBadRequest());

        // Validate the DependenteLegal in the database
        List<DependenteLegal> dependenteLegalList = dependenteLegalRepository.findAll();
        assertThat(dependenteLegalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenteLegalRepository.findAll().size();
        // set the field null
        dependenteLegal.setNome(null);

        // Create the DependenteLegal, which fails.

        restDependenteLegalMockMvc.perform(post("/api/dependente-legals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependenteLegal)))
            .andExpect(status().isBadRequest());

        List<DependenteLegal> dependenteLegalList = dependenteLegalRepository.findAll();
        assertThat(dependenteLegalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataNascimentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenteLegalRepository.findAll().size();
        // set the field null
        dependenteLegal.setDataNascimento(null);

        // Create the DependenteLegal, which fails.

        restDependenteLegalMockMvc.perform(post("/api/dependente-legals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependenteLegal)))
            .andExpect(status().isBadRequest());

        List<DependenteLegal> dependenteLegalList = dependenteLegalRepository.findAll();
        assertThat(dependenteLegalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDependenteLegals() throws Exception {
        // Initialize the database
        dependenteLegalRepository.saveAndFlush(dependenteLegal);

        // Get all the dependenteLegalList
        restDependenteLegalMockMvc.perform(get("/api/dependente-legals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dependenteLegal.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].dataNascimento").value(hasItem(DEFAULT_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getDependenteLegal() throws Exception {
        // Initialize the database
        dependenteLegalRepository.saveAndFlush(dependenteLegal);

        // Get the dependenteLegal
        restDependenteLegalMockMvc.perform(get("/api/dependente-legals/{id}", dependenteLegal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dependenteLegal.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.dataNascimento").value(DEFAULT_DATA_NASCIMENTO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDependenteLegal() throws Exception {
        // Get the dependenteLegal
        restDependenteLegalMockMvc.perform(get("/api/dependente-legals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDependenteLegal() throws Exception {
        // Initialize the database
        dependenteLegalService.save(dependenteLegal);

        int databaseSizeBeforeUpdate = dependenteLegalRepository.findAll().size();

        // Update the dependenteLegal
        DependenteLegal updatedDependenteLegal = dependenteLegalRepository.findById(dependenteLegal.getId()).get();
        // Disconnect from session so that the updates on updatedDependenteLegal are not directly saved in db
        em.detach(updatedDependenteLegal);
        updatedDependenteLegal
            .nome(UPDATED_NOME)
            .dataNascimento(UPDATED_DATA_NASCIMENTO)
            .obs(UPDATED_OBS);

        restDependenteLegalMockMvc.perform(put("/api/dependente-legals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDependenteLegal)))
            .andExpect(status().isOk());

        // Validate the DependenteLegal in the database
        List<DependenteLegal> dependenteLegalList = dependenteLegalRepository.findAll();
        assertThat(dependenteLegalList).hasSize(databaseSizeBeforeUpdate);
        DependenteLegal testDependenteLegal = dependenteLegalList.get(dependenteLegalList.size() - 1);
        assertThat(testDependenteLegal.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testDependenteLegal.getDataNascimento()).isEqualTo(UPDATED_DATA_NASCIMENTO);
        assertThat(testDependenteLegal.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingDependenteLegal() throws Exception {
        int databaseSizeBeforeUpdate = dependenteLegalRepository.findAll().size();

        // Create the DependenteLegal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDependenteLegalMockMvc.perform(put("/api/dependente-legals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependenteLegal)))
            .andExpect(status().isBadRequest());

        // Validate the DependenteLegal in the database
        List<DependenteLegal> dependenteLegalList = dependenteLegalRepository.findAll();
        assertThat(dependenteLegalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDependenteLegal() throws Exception {
        // Initialize the database
        dependenteLegalService.save(dependenteLegal);

        int databaseSizeBeforeDelete = dependenteLegalRepository.findAll().size();

        // Get the dependenteLegal
        restDependenteLegalMockMvc.perform(delete("/api/dependente-legals/{id}", dependenteLegal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DependenteLegal> dependenteLegalList = dependenteLegalRepository.findAll();
        assertThat(dependenteLegalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DependenteLegal.class);
        DependenteLegal dependenteLegal1 = new DependenteLegal();
        dependenteLegal1.setId(1L);
        DependenteLegal dependenteLegal2 = new DependenteLegal();
        dependenteLegal2.setId(dependenteLegal1.getId());
        assertThat(dependenteLegal1).isEqualTo(dependenteLegal2);
        dependenteLegal2.setId(2L);
        assertThat(dependenteLegal1).isNotEqualTo(dependenteLegal2);
        dependenteLegal1.setId(null);
        assertThat(dependenteLegal1).isNotEqualTo(dependenteLegal2);
    }
}
