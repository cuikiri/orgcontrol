package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ObservacaoCoordenador;
import br.com.jhisolution.ong.control.repository.ObservacaoCoordenadorRepository;
import br.com.jhisolution.ong.control.service.ObservacaoCoordenadorService;
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
 * Test class for the ObservacaoCoordenadorResource REST controller.
 *
 * @see ObservacaoCoordenadorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ObservacaoCoordenadorResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ObservacaoCoordenadorRepository observacaoCoordenadorRepository;

    @Autowired
    private ObservacaoCoordenadorService observacaoCoordenadorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObservacaoCoordenadorMockMvc;

    private ObservacaoCoordenador observacaoCoordenador;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ObservacaoCoordenadorResource observacaoCoordenadorResource = new ObservacaoCoordenadorResource(observacaoCoordenadorService);
        this.restObservacaoCoordenadorMockMvc = MockMvcBuilders.standaloneSetup(observacaoCoordenadorResource)
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
    public static ObservacaoCoordenador createEntity(EntityManager em) {
        ObservacaoCoordenador observacaoCoordenador = new ObservacaoCoordenador()
            .data(DEFAULT_DATA)
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return observacaoCoordenador;
    }

    @Before
    public void initTest() {
        observacaoCoordenador = createEntity(em);
    }

    @Test
    @Transactional
    public void createObservacaoCoordenador() throws Exception {
        int databaseSizeBeforeCreate = observacaoCoordenadorRepository.findAll().size();

        // Create the ObservacaoCoordenador
        restObservacaoCoordenadorMockMvc.perform(post("/api/observacao-coordenadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoCoordenador)))
            .andExpect(status().isCreated());

        // Validate the ObservacaoCoordenador in the database
        List<ObservacaoCoordenador> observacaoCoordenadorList = observacaoCoordenadorRepository.findAll();
        assertThat(observacaoCoordenadorList).hasSize(databaseSizeBeforeCreate + 1);
        ObservacaoCoordenador testObservacaoCoordenador = observacaoCoordenadorList.get(observacaoCoordenadorList.size() - 1);
        assertThat(testObservacaoCoordenador.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testObservacaoCoordenador.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testObservacaoCoordenador.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createObservacaoCoordenadorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = observacaoCoordenadorRepository.findAll().size();

        // Create the ObservacaoCoordenador with an existing ID
        observacaoCoordenador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObservacaoCoordenadorMockMvc.perform(post("/api/observacao-coordenadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoCoordenador)))
            .andExpect(status().isBadRequest());

        // Validate the ObservacaoCoordenador in the database
        List<ObservacaoCoordenador> observacaoCoordenadorList = observacaoCoordenadorRepository.findAll();
        assertThat(observacaoCoordenadorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = observacaoCoordenadorRepository.findAll().size();
        // set the field null
        observacaoCoordenador.setData(null);

        // Create the ObservacaoCoordenador, which fails.

        restObservacaoCoordenadorMockMvc.perform(post("/api/observacao-coordenadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoCoordenador)))
            .andExpect(status().isBadRequest());

        List<ObservacaoCoordenador> observacaoCoordenadorList = observacaoCoordenadorRepository.findAll();
        assertThat(observacaoCoordenadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllObservacaoCoordenadors() throws Exception {
        // Initialize the database
        observacaoCoordenadorRepository.saveAndFlush(observacaoCoordenador);

        // Get all the observacaoCoordenadorList
        restObservacaoCoordenadorMockMvc.perform(get("/api/observacao-coordenadors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(observacaoCoordenador.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getObservacaoCoordenador() throws Exception {
        // Initialize the database
        observacaoCoordenadorRepository.saveAndFlush(observacaoCoordenador);

        // Get the observacaoCoordenador
        restObservacaoCoordenadorMockMvc.perform(get("/api/observacao-coordenadors/{id}", observacaoCoordenador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(observacaoCoordenador.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingObservacaoCoordenador() throws Exception {
        // Get the observacaoCoordenador
        restObservacaoCoordenadorMockMvc.perform(get("/api/observacao-coordenadors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObservacaoCoordenador() throws Exception {
        // Initialize the database
        observacaoCoordenadorService.save(observacaoCoordenador);

        int databaseSizeBeforeUpdate = observacaoCoordenadorRepository.findAll().size();

        // Update the observacaoCoordenador
        ObservacaoCoordenador updatedObservacaoCoordenador = observacaoCoordenadorRepository.findById(observacaoCoordenador.getId()).get();
        // Disconnect from session so that the updates on updatedObservacaoCoordenador are not directly saved in db
        em.detach(updatedObservacaoCoordenador);
        updatedObservacaoCoordenador
            .data(UPDATED_DATA)
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restObservacaoCoordenadorMockMvc.perform(put("/api/observacao-coordenadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedObservacaoCoordenador)))
            .andExpect(status().isOk());

        // Validate the ObservacaoCoordenador in the database
        List<ObservacaoCoordenador> observacaoCoordenadorList = observacaoCoordenadorRepository.findAll();
        assertThat(observacaoCoordenadorList).hasSize(databaseSizeBeforeUpdate);
        ObservacaoCoordenador testObservacaoCoordenador = observacaoCoordenadorList.get(observacaoCoordenadorList.size() - 1);
        assertThat(testObservacaoCoordenador.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testObservacaoCoordenador.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testObservacaoCoordenador.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingObservacaoCoordenador() throws Exception {
        int databaseSizeBeforeUpdate = observacaoCoordenadorRepository.findAll().size();

        // Create the ObservacaoCoordenador

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObservacaoCoordenadorMockMvc.perform(put("/api/observacao-coordenadors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(observacaoCoordenador)))
            .andExpect(status().isBadRequest());

        // Validate the ObservacaoCoordenador in the database
        List<ObservacaoCoordenador> observacaoCoordenadorList = observacaoCoordenadorRepository.findAll();
        assertThat(observacaoCoordenadorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObservacaoCoordenador() throws Exception {
        // Initialize the database
        observacaoCoordenadorService.save(observacaoCoordenador);

        int databaseSizeBeforeDelete = observacaoCoordenadorRepository.findAll().size();

        // Get the observacaoCoordenador
        restObservacaoCoordenadorMockMvc.perform(delete("/api/observacao-coordenadors/{id}", observacaoCoordenador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ObservacaoCoordenador> observacaoCoordenadorList = observacaoCoordenadorRepository.findAll();
        assertThat(observacaoCoordenadorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObservacaoCoordenador.class);
        ObservacaoCoordenador observacaoCoordenador1 = new ObservacaoCoordenador();
        observacaoCoordenador1.setId(1L);
        ObservacaoCoordenador observacaoCoordenador2 = new ObservacaoCoordenador();
        observacaoCoordenador2.setId(observacaoCoordenador1.getId());
        assertThat(observacaoCoordenador1).isEqualTo(observacaoCoordenador2);
        observacaoCoordenador2.setId(2L);
        assertThat(observacaoCoordenador1).isNotEqualTo(observacaoCoordenador2);
        observacaoCoordenador1.setId(null);
        assertThat(observacaoCoordenador1).isNotEqualTo(observacaoCoordenador2);
    }
}
