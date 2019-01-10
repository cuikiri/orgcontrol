package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ObservacaoProfessor;
import br.com.jhisolution.ong.control.repository.ObservacaoProfessorRepository;
import br.com.jhisolution.ong.control.service.ObservacaoProfessorService;
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
 * Test class for the ObservacaoProfessorResource REST controller.
 *
 * @see ObservacaoProfessorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ObservacaoProfessorResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ObservacaoProfessorRepository observacaoProfessorRepository;

    @Autowired
    private ObservacaoProfessorService observacaoProfessorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObservacaoProfessorMockMvc;

    private ObservacaoProfessor observacaoProfessor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ObservacaoProfessorResource observacaoProfessorResource = new ObservacaoProfessorResource(observacaoProfessorService);
        this.restObservacaoProfessorMockMvc = MockMvcBuilders.standaloneSetup(observacaoProfessorResource)
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
    public static ObservacaoProfessor createEntity(EntityManager em) {
        ObservacaoProfessor observacaoProfessor = new ObservacaoProfessor()
            .data(DEFAULT_DATA)
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return observacaoProfessor;
    }

    @Before
    public void initTest() {
        observacaoProfessor = createEntity(em);
    }

    @Test
    @Transactional
    public void createObservacaoProfessor() throws Exception {
        int databaseSizeBeforeCreate = observacaoProfessorRepository.findAll().size();

        // Create the ObservacaoProfessor
        restObservacaoProfessorMockMvc.perform(post("/api/observacao-professors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoProfessor)))
            .andExpect(status().isCreated());

        // Validate the ObservacaoProfessor in the database
        List<ObservacaoProfessor> observacaoProfessorList = observacaoProfessorRepository.findAll();
        assertThat(observacaoProfessorList).hasSize(databaseSizeBeforeCreate + 1);
        ObservacaoProfessor testObservacaoProfessor = observacaoProfessorList.get(observacaoProfessorList.size() - 1);
        assertThat(testObservacaoProfessor.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testObservacaoProfessor.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testObservacaoProfessor.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createObservacaoProfessorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = observacaoProfessorRepository.findAll().size();

        // Create the ObservacaoProfessor with an existing ID
        observacaoProfessor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObservacaoProfessorMockMvc.perform(post("/api/observacao-professors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoProfessor)))
            .andExpect(status().isBadRequest());

        // Validate the ObservacaoProfessor in the database
        List<ObservacaoProfessor> observacaoProfessorList = observacaoProfessorRepository.findAll();
        assertThat(observacaoProfessorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = observacaoProfessorRepository.findAll().size();
        // set the field null
        observacaoProfessor.setData(null);

        // Create the ObservacaoProfessor, which fails.

        restObservacaoProfessorMockMvc.perform(post("/api/observacao-professors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoProfessor)))
            .andExpect(status().isBadRequest());

        List<ObservacaoProfessor> observacaoProfessorList = observacaoProfessorRepository.findAll();
        assertThat(observacaoProfessorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = observacaoProfessorRepository.findAll().size();
        // set the field null
        observacaoProfessor.setNome(null);

        // Create the ObservacaoProfessor, which fails.

        restObservacaoProfessorMockMvc.perform(post("/api/observacao-professors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoProfessor)))
            .andExpect(status().isBadRequest());

        List<ObservacaoProfessor> observacaoProfessorList = observacaoProfessorRepository.findAll();
        assertThat(observacaoProfessorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllObservacaoProfessors() throws Exception {
        // Initialize the database
        observacaoProfessorRepository.saveAndFlush(observacaoProfessor);

        // Get all the observacaoProfessorList
        restObservacaoProfessorMockMvc.perform(get("/api/observacao-professors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(observacaoProfessor.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getObservacaoProfessor() throws Exception {
        // Initialize the database
        observacaoProfessorRepository.saveAndFlush(observacaoProfessor);

        // Get the observacaoProfessor
        restObservacaoProfessorMockMvc.perform(get("/api/observacao-professors/{id}", observacaoProfessor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(observacaoProfessor.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingObservacaoProfessor() throws Exception {
        // Get the observacaoProfessor
        restObservacaoProfessorMockMvc.perform(get("/api/observacao-professors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObservacaoProfessor() throws Exception {
        // Initialize the database
        observacaoProfessorService.save(observacaoProfessor);

        int databaseSizeBeforeUpdate = observacaoProfessorRepository.findAll().size();

        // Update the observacaoProfessor
        ObservacaoProfessor updatedObservacaoProfessor = observacaoProfessorRepository.findById(observacaoProfessor.getId()).get();
        // Disconnect from session so that the updates on updatedObservacaoProfessor are not directly saved in db
        em.detach(updatedObservacaoProfessor);
        updatedObservacaoProfessor
            .data(UPDATED_DATA)
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restObservacaoProfessorMockMvc.perform(put("/api/observacao-professors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedObservacaoProfessor)))
            .andExpect(status().isOk());

        // Validate the ObservacaoProfessor in the database
        List<ObservacaoProfessor> observacaoProfessorList = observacaoProfessorRepository.findAll();
        assertThat(observacaoProfessorList).hasSize(databaseSizeBeforeUpdate);
        ObservacaoProfessor testObservacaoProfessor = observacaoProfessorList.get(observacaoProfessorList.size() - 1);
        assertThat(testObservacaoProfessor.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testObservacaoProfessor.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testObservacaoProfessor.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingObservacaoProfessor() throws Exception {
        int databaseSizeBeforeUpdate = observacaoProfessorRepository.findAll().size();

        // Create the ObservacaoProfessor

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObservacaoProfessorMockMvc.perform(put("/api/observacao-professors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoProfessor)))
            .andExpect(status().isBadRequest());

        // Validate the ObservacaoProfessor in the database
        List<ObservacaoProfessor> observacaoProfessorList = observacaoProfessorRepository.findAll();
        assertThat(observacaoProfessorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObservacaoProfessor() throws Exception {
        // Initialize the database
        observacaoProfessorService.save(observacaoProfessor);

        int databaseSizeBeforeDelete = observacaoProfessorRepository.findAll().size();

        // Get the observacaoProfessor
        restObservacaoProfessorMockMvc.perform(delete("/api/observacao-professors/{id}", observacaoProfessor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ObservacaoProfessor> observacaoProfessorList = observacaoProfessorRepository.findAll();
        assertThat(observacaoProfessorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObservacaoProfessor.class);
        ObservacaoProfessor observacaoProfessor1 = new ObservacaoProfessor();
        observacaoProfessor1.setId(1L);
        ObservacaoProfessor observacaoProfessor2 = new ObservacaoProfessor();
        observacaoProfessor2.setId(observacaoProfessor1.getId());
        assertThat(observacaoProfessor1).isEqualTo(observacaoProfessor2);
        observacaoProfessor2.setId(2L);
        assertThat(observacaoProfessor1).isNotEqualTo(observacaoProfessor2);
        observacaoProfessor1.setId(null);
        assertThat(observacaoProfessor1).isNotEqualTo(observacaoProfessor2);
    }
}
