package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.DiaSemana;
import br.com.jhisolution.ong.control.repository.DiaSemanaRepository;
import br.com.jhisolution.ong.control.service.DiaSemanaService;
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
 * Test class for the DiaSemanaResource REST controller.
 *
 * @see DiaSemanaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class DiaSemanaResourceIntTest {

    private static final String DEFAULT_ABREVIATURA = "AAAAAAAAAA";
    private static final String UPDATED_ABREVIATURA = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private DiaSemanaRepository diaSemanaRepository;

    @Autowired
    private DiaSemanaService diaSemanaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDiaSemanaMockMvc;

    private DiaSemana diaSemana;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiaSemanaResource diaSemanaResource = new DiaSemanaResource(diaSemanaService);
        this.restDiaSemanaMockMvc = MockMvcBuilders.standaloneSetup(diaSemanaResource)
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
    public static DiaSemana createEntity(EntityManager em) {
        DiaSemana diaSemana = new DiaSemana()
            .abreviatura(DEFAULT_ABREVIATURA)
            .nome(DEFAULT_NOME)
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS);
        return diaSemana;
    }

    @Before
    public void initTest() {
        diaSemana = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiaSemana() throws Exception {
        int databaseSizeBeforeCreate = diaSemanaRepository.findAll().size();

        // Create the DiaSemana
        restDiaSemanaMockMvc.perform(post("/api/dia-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaSemana)))
            .andExpect(status().isCreated());

        // Validate the DiaSemana in the database
        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeCreate + 1);
        DiaSemana testDiaSemana = diaSemanaList.get(diaSemanaList.size() - 1);
        assertThat(testDiaSemana.getAbreviatura()).isEqualTo(DEFAULT_ABREVIATURA);
        assertThat(testDiaSemana.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testDiaSemana.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testDiaSemana.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createDiaSemanaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diaSemanaRepository.findAll().size();

        // Create the DiaSemana with an existing ID
        diaSemana.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiaSemanaMockMvc.perform(post("/api/dia-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaSemana)))
            .andExpect(status().isBadRequest());

        // Validate the DiaSemana in the database
        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAbreviaturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = diaSemanaRepository.findAll().size();
        // set the field null
        diaSemana.setAbreviatura(null);

        // Create the DiaSemana, which fails.

        restDiaSemanaMockMvc.perform(post("/api/dia-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaSemana)))
            .andExpect(status().isBadRequest());

        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = diaSemanaRepository.findAll().size();
        // set the field null
        diaSemana.setNome(null);

        // Create the DiaSemana, which fails.

        restDiaSemanaMockMvc.perform(post("/api/dia-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaSemana)))
            .andExpect(status().isBadRequest());

        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = diaSemanaRepository.findAll().size();
        // set the field null
        diaSemana.setNumero(null);

        // Create the DiaSemana, which fails.

        restDiaSemanaMockMvc.perform(post("/api/dia-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaSemana)))
            .andExpect(status().isBadRequest());

        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDiaSemanas() throws Exception {
        // Initialize the database
        diaSemanaRepository.saveAndFlush(diaSemana);

        // Get all the diaSemanaList
        restDiaSemanaMockMvc.perform(get("/api/dia-semanas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diaSemana.getId().intValue())))
            .andExpect(jsonPath("$.[*].abreviatura").value(hasItem(DEFAULT_ABREVIATURA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getDiaSemana() throws Exception {
        // Initialize the database
        diaSemanaRepository.saveAndFlush(diaSemana);

        // Get the diaSemana
        restDiaSemanaMockMvc.perform(get("/api/dia-semanas/{id}", diaSemana.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(diaSemana.getId().intValue()))
            .andExpect(jsonPath("$.abreviatura").value(DEFAULT_ABREVIATURA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDiaSemana() throws Exception {
        // Get the diaSemana
        restDiaSemanaMockMvc.perform(get("/api/dia-semanas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiaSemana() throws Exception {
        // Initialize the database
        diaSemanaService.save(diaSemana);

        int databaseSizeBeforeUpdate = diaSemanaRepository.findAll().size();

        // Update the diaSemana
        DiaSemana updatedDiaSemana = diaSemanaRepository.findById(diaSemana.getId()).get();
        // Disconnect from session so that the updates on updatedDiaSemana are not directly saved in db
        em.detach(updatedDiaSemana);
        updatedDiaSemana
            .abreviatura(UPDATED_ABREVIATURA)
            .nome(UPDATED_NOME)
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS);

        restDiaSemanaMockMvc.perform(put("/api/dia-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDiaSemana)))
            .andExpect(status().isOk());

        // Validate the DiaSemana in the database
        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeUpdate);
        DiaSemana testDiaSemana = diaSemanaList.get(diaSemanaList.size() - 1);
        assertThat(testDiaSemana.getAbreviatura()).isEqualTo(UPDATED_ABREVIATURA);
        assertThat(testDiaSemana.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testDiaSemana.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testDiaSemana.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingDiaSemana() throws Exception {
        int databaseSizeBeforeUpdate = diaSemanaRepository.findAll().size();

        // Create the DiaSemana

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiaSemanaMockMvc.perform(put("/api/dia-semanas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaSemana)))
            .andExpect(status().isBadRequest());

        // Validate the DiaSemana in the database
        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDiaSemana() throws Exception {
        // Initialize the database
        diaSemanaService.save(diaSemana);

        int databaseSizeBeforeDelete = diaSemanaRepository.findAll().size();

        // Get the diaSemana
        restDiaSemanaMockMvc.perform(delete("/api/dia-semanas/{id}", diaSemana.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DiaSemana> diaSemanaList = diaSemanaRepository.findAll();
        assertThat(diaSemanaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiaSemana.class);
        DiaSemana diaSemana1 = new DiaSemana();
        diaSemana1.setId(1L);
        DiaSemana diaSemana2 = new DiaSemana();
        diaSemana2.setId(diaSemana1.getId());
        assertThat(diaSemana1).isEqualTo(diaSemana2);
        diaSemana2.setId(2L);
        assertThat(diaSemana1).isNotEqualTo(diaSemana2);
        diaSemana1.setId(null);
        assertThat(diaSemana1).isNotEqualTo(diaSemana2);
    }
}
