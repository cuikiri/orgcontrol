package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoCurso;
import br.com.jhisolution.ong.control.repository.TipoCursoRepository;
import br.com.jhisolution.ong.control.service.TipoCursoService;
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
 * Test class for the TipoCursoResource REST controller.
 *
 * @see TipoCursoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoCursoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoCursoRepository tipoCursoRepository;

    @Autowired
    private TipoCursoService tipoCursoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoCursoMockMvc;

    private TipoCurso tipoCurso;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoCursoResource tipoCursoResource = new TipoCursoResource(tipoCursoService);
        this.restTipoCursoMockMvc = MockMvcBuilders.standaloneSetup(tipoCursoResource)
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
    public static TipoCurso createEntity(EntityManager em) {
        TipoCurso tipoCurso = new TipoCurso()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoCurso;
    }

    @Before
    public void initTest() {
        tipoCurso = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoCurso() throws Exception {
        int databaseSizeBeforeCreate = tipoCursoRepository.findAll().size();

        // Create the TipoCurso
        restTipoCursoMockMvc.perform(post("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoCurso)))
            .andExpect(status().isCreated());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoCurso testTipoCurso = tipoCursoList.get(tipoCursoList.size() - 1);
        assertThat(testTipoCurso.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoCurso.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoCursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoCursoRepository.findAll().size();

        // Create the TipoCurso with an existing ID
        tipoCurso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoCursoMockMvc.perform(post("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoCurso)))
            .andExpect(status().isBadRequest());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoCursoRepository.findAll().size();
        // set the field null
        tipoCurso.setNome(null);

        // Create the TipoCurso, which fails.

        restTipoCursoMockMvc.perform(post("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoCurso)))
            .andExpect(status().isBadRequest());

        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoCursos() throws Exception {
        // Initialize the database
        tipoCursoRepository.saveAndFlush(tipoCurso);

        // Get all the tipoCursoList
        restTipoCursoMockMvc.perform(get("/api/tipo-cursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoCurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoCurso() throws Exception {
        // Initialize the database
        tipoCursoRepository.saveAndFlush(tipoCurso);

        // Get the tipoCurso
        restTipoCursoMockMvc.perform(get("/api/tipo-cursos/{id}", tipoCurso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoCurso.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoCurso() throws Exception {
        // Get the tipoCurso
        restTipoCursoMockMvc.perform(get("/api/tipo-cursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoCurso() throws Exception {
        // Initialize the database
        tipoCursoService.save(tipoCurso);

        int databaseSizeBeforeUpdate = tipoCursoRepository.findAll().size();

        // Update the tipoCurso
        TipoCurso updatedTipoCurso = tipoCursoRepository.findById(tipoCurso.getId()).get();
        // Disconnect from session so that the updates on updatedTipoCurso are not directly saved in db
        em.detach(updatedTipoCurso);
        updatedTipoCurso
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoCursoMockMvc.perform(put("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoCurso)))
            .andExpect(status().isOk());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeUpdate);
        TipoCurso testTipoCurso = tipoCursoList.get(tipoCursoList.size() - 1);
        assertThat(testTipoCurso.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoCurso.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoCurso() throws Exception {
        int databaseSizeBeforeUpdate = tipoCursoRepository.findAll().size();

        // Create the TipoCurso

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoCursoMockMvc.perform(put("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoCurso)))
            .andExpect(status().isBadRequest());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoCurso() throws Exception {
        // Initialize the database
        tipoCursoService.save(tipoCurso);

        int databaseSizeBeforeDelete = tipoCursoRepository.findAll().size();

        // Get the tipoCurso
        restTipoCursoMockMvc.perform(delete("/api/tipo-cursos/{id}", tipoCurso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoCurso.class);
        TipoCurso tipoCurso1 = new TipoCurso();
        tipoCurso1.setId(1L);
        TipoCurso tipoCurso2 = new TipoCurso();
        tipoCurso2.setId(tipoCurso1.getId());
        assertThat(tipoCurso1).isEqualTo(tipoCurso2);
        tipoCurso2.setId(2L);
        assertThat(tipoCurso1).isNotEqualTo(tipoCurso2);
        tipoCurso1.setId(null);
        assertThat(tipoCurso1).isNotEqualTo(tipoCurso2);
    }
}
