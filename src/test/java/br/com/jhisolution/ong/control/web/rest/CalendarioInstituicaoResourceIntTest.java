package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.CalendarioInstituicao;
import br.com.jhisolution.ong.control.repository.CalendarioInstituicaoRepository;
import br.com.jhisolution.ong.control.service.CalendarioInstituicaoService;
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
 * Test class for the CalendarioInstituicaoResource REST controller.
 *
 * @see CalendarioInstituicaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class CalendarioInstituicaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private CalendarioInstituicaoRepository calendarioInstituicaoRepository;

    @Autowired
    private CalendarioInstituicaoService calendarioInstituicaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCalendarioInstituicaoMockMvc;

    private CalendarioInstituicao calendarioInstituicao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CalendarioInstituicaoResource calendarioInstituicaoResource = new CalendarioInstituicaoResource(calendarioInstituicaoService);
        this.restCalendarioInstituicaoMockMvc = MockMvcBuilders.standaloneSetup(calendarioInstituicaoResource)
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
    public static CalendarioInstituicao createEntity(EntityManager em) {
        CalendarioInstituicao calendarioInstituicao = new CalendarioInstituicao()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return calendarioInstituicao;
    }

    @Before
    public void initTest() {
        calendarioInstituicao = createEntity(em);
    }

    @Test
    @Transactional
    public void createCalendarioInstituicao() throws Exception {
        int databaseSizeBeforeCreate = calendarioInstituicaoRepository.findAll().size();

        // Create the CalendarioInstituicao
        restCalendarioInstituicaoMockMvc.perform(post("/api/calendario-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioInstituicao)))
            .andExpect(status().isCreated());

        // Validate the CalendarioInstituicao in the database
        List<CalendarioInstituicao> calendarioInstituicaoList = calendarioInstituicaoRepository.findAll();
        assertThat(calendarioInstituicaoList).hasSize(databaseSizeBeforeCreate + 1);
        CalendarioInstituicao testCalendarioInstituicao = calendarioInstituicaoList.get(calendarioInstituicaoList.size() - 1);
        assertThat(testCalendarioInstituicao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCalendarioInstituicao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createCalendarioInstituicaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = calendarioInstituicaoRepository.findAll().size();

        // Create the CalendarioInstituicao with an existing ID
        calendarioInstituicao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCalendarioInstituicaoMockMvc.perform(post("/api/calendario-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioInstituicao)))
            .andExpect(status().isBadRequest());

        // Validate the CalendarioInstituicao in the database
        List<CalendarioInstituicao> calendarioInstituicaoList = calendarioInstituicaoRepository.findAll();
        assertThat(calendarioInstituicaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioInstituicaoRepository.findAll().size();
        // set the field null
        calendarioInstituicao.setNome(null);

        // Create the CalendarioInstituicao, which fails.

        restCalendarioInstituicaoMockMvc.perform(post("/api/calendario-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioInstituicao)))
            .andExpect(status().isBadRequest());

        List<CalendarioInstituicao> calendarioInstituicaoList = calendarioInstituicaoRepository.findAll();
        assertThat(calendarioInstituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCalendarioInstituicaos() throws Exception {
        // Initialize the database
        calendarioInstituicaoRepository.saveAndFlush(calendarioInstituicao);

        // Get all the calendarioInstituicaoList
        restCalendarioInstituicaoMockMvc.perform(get("/api/calendario-instituicaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(calendarioInstituicao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getCalendarioInstituicao() throws Exception {
        // Initialize the database
        calendarioInstituicaoRepository.saveAndFlush(calendarioInstituicao);

        // Get the calendarioInstituicao
        restCalendarioInstituicaoMockMvc.perform(get("/api/calendario-instituicaos/{id}", calendarioInstituicao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(calendarioInstituicao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCalendarioInstituicao() throws Exception {
        // Get the calendarioInstituicao
        restCalendarioInstituicaoMockMvc.perform(get("/api/calendario-instituicaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCalendarioInstituicao() throws Exception {
        // Initialize the database
        calendarioInstituicaoService.save(calendarioInstituicao);

        int databaseSizeBeforeUpdate = calendarioInstituicaoRepository.findAll().size();

        // Update the calendarioInstituicao
        CalendarioInstituicao updatedCalendarioInstituicao = calendarioInstituicaoRepository.findById(calendarioInstituicao.getId()).get();
        // Disconnect from session so that the updates on updatedCalendarioInstituicao are not directly saved in db
        em.detach(updatedCalendarioInstituicao);
        updatedCalendarioInstituicao
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restCalendarioInstituicaoMockMvc.perform(put("/api/calendario-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCalendarioInstituicao)))
            .andExpect(status().isOk());

        // Validate the CalendarioInstituicao in the database
        List<CalendarioInstituicao> calendarioInstituicaoList = calendarioInstituicaoRepository.findAll();
        assertThat(calendarioInstituicaoList).hasSize(databaseSizeBeforeUpdate);
        CalendarioInstituicao testCalendarioInstituicao = calendarioInstituicaoList.get(calendarioInstituicaoList.size() - 1);
        assertThat(testCalendarioInstituicao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCalendarioInstituicao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingCalendarioInstituicao() throws Exception {
        int databaseSizeBeforeUpdate = calendarioInstituicaoRepository.findAll().size();

        // Create the CalendarioInstituicao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCalendarioInstituicaoMockMvc.perform(put("/api/calendario-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendarioInstituicao)))
            .andExpect(status().isBadRequest());

        // Validate the CalendarioInstituicao in the database
        List<CalendarioInstituicao> calendarioInstituicaoList = calendarioInstituicaoRepository.findAll();
        assertThat(calendarioInstituicaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCalendarioInstituicao() throws Exception {
        // Initialize the database
        calendarioInstituicaoService.save(calendarioInstituicao);

        int databaseSizeBeforeDelete = calendarioInstituicaoRepository.findAll().size();

        // Get the calendarioInstituicao
        restCalendarioInstituicaoMockMvc.perform(delete("/api/calendario-instituicaos/{id}", calendarioInstituicao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CalendarioInstituicao> calendarioInstituicaoList = calendarioInstituicaoRepository.findAll();
        assertThat(calendarioInstituicaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CalendarioInstituicao.class);
        CalendarioInstituicao calendarioInstituicao1 = new CalendarioInstituicao();
        calendarioInstituicao1.setId(1L);
        CalendarioInstituicao calendarioInstituicao2 = new CalendarioInstituicao();
        calendarioInstituicao2.setId(calendarioInstituicao1.getId());
        assertThat(calendarioInstituicao1).isEqualTo(calendarioInstituicao2);
        calendarioInstituicao2.setId(2L);
        assertThat(calendarioInstituicao1).isNotEqualTo(calendarioInstituicao2);
        calendarioInstituicao1.setId(null);
        assertThat(calendarioInstituicao1).isNotEqualTo(calendarioInstituicao2);
    }
}
