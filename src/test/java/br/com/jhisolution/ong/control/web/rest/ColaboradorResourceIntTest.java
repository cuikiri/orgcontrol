package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Colaborador;
import br.com.jhisolution.ong.control.repository.ColaboradorRepository;
import br.com.jhisolution.ong.control.service.ColaboradorService;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ColaboradorResource REST controller.
 *
 * @see ColaboradorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ColaboradorResourceIntTest {

    private static final LocalDate DEFAULT_DATA_CADASTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_CADASTRO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_ADMISSAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_ADMISSAO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_SALARIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALARIO = new BigDecimal(2);

    private static final String DEFAULT_PAI = "AAAAAAAAAA";
    private static final String UPDATED_PAI = "BBBBBBBBBB";

    private static final String DEFAULT_MAE = "AAAAAAAAAA";
    private static final String UPDATED_MAE = "BBBBBBBBBB";

    private static final String DEFAULT_CONJUGE = "AAAAAAAAAA";
    private static final String UPDATED_CONJUGE = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restColaboradorMockMvc;

    private Colaborador colaborador;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ColaboradorResource colaboradorResource = new ColaboradorResource(colaboradorService);
        this.restColaboradorMockMvc = MockMvcBuilders.standaloneSetup(colaboradorResource)
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
    public static Colaborador createEntity(EntityManager em) {
        Colaborador colaborador = new Colaborador()
            .dataCadastro(DEFAULT_DATA_CADASTRO)
            .dataAdmissao(DEFAULT_DATA_ADMISSAO)
            .salario(DEFAULT_SALARIO)
            .pai(DEFAULT_PAI)
            .mae(DEFAULT_MAE)
            .conjuge(DEFAULT_CONJUGE)
            .obs(DEFAULT_OBS);
        return colaborador;
    }

    @Before
    public void initTest() {
        colaborador = createEntity(em);
    }

    @Test
    @Transactional
    public void createColaborador() throws Exception {
        int databaseSizeBeforeCreate = colaboradorRepository.findAll().size();

        // Create the Colaborador
        restColaboradorMockMvc.perform(post("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isCreated());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeCreate + 1);
        Colaborador testColaborador = colaboradorList.get(colaboradorList.size() - 1);
        assertThat(testColaborador.getDataCadastro()).isEqualTo(DEFAULT_DATA_CADASTRO);
        assertThat(testColaborador.getDataAdmissao()).isEqualTo(DEFAULT_DATA_ADMISSAO);
        assertThat(testColaborador.getSalario()).isEqualTo(DEFAULT_SALARIO);
        assertThat(testColaborador.getPai()).isEqualTo(DEFAULT_PAI);
        assertThat(testColaborador.getMae()).isEqualTo(DEFAULT_MAE);
        assertThat(testColaborador.getConjuge()).isEqualTo(DEFAULT_CONJUGE);
        assertThat(testColaborador.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createColaboradorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = colaboradorRepository.findAll().size();

        // Create the Colaborador with an existing ID
        colaborador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restColaboradorMockMvc.perform(post("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isBadRequest());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = colaboradorRepository.findAll().size();
        // set the field null
        colaborador.setDataCadastro(null);

        // Create the Colaborador, which fails.

        restColaboradorMockMvc.perform(post("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isBadRequest());

        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataAdmissaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = colaboradorRepository.findAll().size();
        // set the field null
        colaborador.setDataAdmissao(null);

        // Create the Colaborador, which fails.

        restColaboradorMockMvc.perform(post("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isBadRequest());

        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllColaboradors() throws Exception {
        // Initialize the database
        colaboradorRepository.saveAndFlush(colaborador);

        // Get all the colaboradorList
        restColaboradorMockMvc.perform(get("/api/colaboradors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(colaborador.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataCadastro").value(hasItem(DEFAULT_DATA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].dataAdmissao").value(hasItem(DEFAULT_DATA_ADMISSAO.toString())))
            .andExpect(jsonPath("$.[*].salario").value(hasItem(DEFAULT_SALARIO.intValue())))
            .andExpect(jsonPath("$.[*].pai").value(hasItem(DEFAULT_PAI.toString())))
            .andExpect(jsonPath("$.[*].mae").value(hasItem(DEFAULT_MAE.toString())))
            .andExpect(jsonPath("$.[*].conjuge").value(hasItem(DEFAULT_CONJUGE.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getColaborador() throws Exception {
        // Initialize the database
        colaboradorRepository.saveAndFlush(colaborador);

        // Get the colaborador
        restColaboradorMockMvc.perform(get("/api/colaboradors/{id}", colaborador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(colaborador.getId().intValue()))
            .andExpect(jsonPath("$.dataCadastro").value(DEFAULT_DATA_CADASTRO.toString()))
            .andExpect(jsonPath("$.dataAdmissao").value(DEFAULT_DATA_ADMISSAO.toString()))
            .andExpect(jsonPath("$.salario").value(DEFAULT_SALARIO.intValue()))
            .andExpect(jsonPath("$.pai").value(DEFAULT_PAI.toString()))
            .andExpect(jsonPath("$.mae").value(DEFAULT_MAE.toString()))
            .andExpect(jsonPath("$.conjuge").value(DEFAULT_CONJUGE.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingColaborador() throws Exception {
        // Get the colaborador
        restColaboradorMockMvc.perform(get("/api/colaboradors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateColaborador() throws Exception {
        // Initialize the database
        colaboradorService.save(colaborador);

        int databaseSizeBeforeUpdate = colaboradorRepository.findAll().size();

        // Update the colaborador
        Colaborador updatedColaborador = colaboradorRepository.findById(colaborador.getId()).get();
        // Disconnect from session so that the updates on updatedColaborador are not directly saved in db
        em.detach(updatedColaborador);
        updatedColaborador
            .dataCadastro(UPDATED_DATA_CADASTRO)
            .dataAdmissao(UPDATED_DATA_ADMISSAO)
            .salario(UPDATED_SALARIO)
            .pai(UPDATED_PAI)
            .mae(UPDATED_MAE)
            .conjuge(UPDATED_CONJUGE)
            .obs(UPDATED_OBS);

        restColaboradorMockMvc.perform(put("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedColaborador)))
            .andExpect(status().isOk());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeUpdate);
        Colaborador testColaborador = colaboradorList.get(colaboradorList.size() - 1);
        assertThat(testColaborador.getDataCadastro()).isEqualTo(UPDATED_DATA_CADASTRO);
        assertThat(testColaborador.getDataAdmissao()).isEqualTo(UPDATED_DATA_ADMISSAO);
        assertThat(testColaborador.getSalario()).isEqualTo(UPDATED_SALARIO);
        assertThat(testColaborador.getPai()).isEqualTo(UPDATED_PAI);
        assertThat(testColaborador.getMae()).isEqualTo(UPDATED_MAE);
        assertThat(testColaborador.getConjuge()).isEqualTo(UPDATED_CONJUGE);
        assertThat(testColaborador.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingColaborador() throws Exception {
        int databaseSizeBeforeUpdate = colaboradorRepository.findAll().size();

        // Create the Colaborador

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColaboradorMockMvc.perform(put("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isBadRequest());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteColaborador() throws Exception {
        // Initialize the database
        colaboradorService.save(colaborador);

        int databaseSizeBeforeDelete = colaboradorRepository.findAll().size();

        // Get the colaborador
        restColaboradorMockMvc.perform(delete("/api/colaboradors/{id}", colaborador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Colaborador.class);
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setId(1L);
        Colaborador colaborador2 = new Colaborador();
        colaborador2.setId(colaborador1.getId());
        assertThat(colaborador1).isEqualTo(colaborador2);
        colaborador2.setId(2L);
        assertThat(colaborador1).isNotEqualTo(colaborador2);
        colaborador1.setId(null);
        assertThat(colaborador1).isNotEqualTo(colaborador2);
    }
}
