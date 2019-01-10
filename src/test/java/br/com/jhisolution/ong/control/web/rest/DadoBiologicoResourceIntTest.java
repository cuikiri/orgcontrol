package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.DadoBiologico;
import br.com.jhisolution.ong.control.repository.DadoBiologicoRepository;
import br.com.jhisolution.ong.control.service.DadoBiologicoService;
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
 * Test class for the DadoBiologicoResource REST controller.
 *
 * @see DadoBiologicoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class DadoBiologicoResourceIntTest {

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private DadoBiologicoRepository dadoBiologicoRepository;

    @Autowired
    private DadoBiologicoService dadoBiologicoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDadoBiologicoMockMvc;

    private DadoBiologico dadoBiologico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DadoBiologicoResource dadoBiologicoResource = new DadoBiologicoResource(dadoBiologicoService);
        this.restDadoBiologicoMockMvc = MockMvcBuilders.standaloneSetup(dadoBiologicoResource)
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
    public static DadoBiologico createEntity(EntityManager em) {
        DadoBiologico dadoBiologico = new DadoBiologico()
            .valor(DEFAULT_VALOR)
            .obs(DEFAULT_OBS);
        return dadoBiologico;
    }

    @Before
    public void initTest() {
        dadoBiologico = createEntity(em);
    }

    @Test
    @Transactional
    public void createDadoBiologico() throws Exception {
        int databaseSizeBeforeCreate = dadoBiologicoRepository.findAll().size();

        // Create the DadoBiologico
        restDadoBiologicoMockMvc.perform(post("/api/dado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadoBiologico)))
            .andExpect(status().isCreated());

        // Validate the DadoBiologico in the database
        List<DadoBiologico> dadoBiologicoList = dadoBiologicoRepository.findAll();
        assertThat(dadoBiologicoList).hasSize(databaseSizeBeforeCreate + 1);
        DadoBiologico testDadoBiologico = dadoBiologicoList.get(dadoBiologicoList.size() - 1);
        assertThat(testDadoBiologico.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testDadoBiologico.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createDadoBiologicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dadoBiologicoRepository.findAll().size();

        // Create the DadoBiologico with an existing ID
        dadoBiologico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDadoBiologicoMockMvc.perform(post("/api/dado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadoBiologico)))
            .andExpect(status().isBadRequest());

        // Validate the DadoBiologico in the database
        List<DadoBiologico> dadoBiologicoList = dadoBiologicoRepository.findAll();
        assertThat(dadoBiologicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = dadoBiologicoRepository.findAll().size();
        // set the field null
        dadoBiologico.setValor(null);

        // Create the DadoBiologico, which fails.

        restDadoBiologicoMockMvc.perform(post("/api/dado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadoBiologico)))
            .andExpect(status().isBadRequest());

        List<DadoBiologico> dadoBiologicoList = dadoBiologicoRepository.findAll();
        assertThat(dadoBiologicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDadoBiologicos() throws Exception {
        // Initialize the database
        dadoBiologicoRepository.saveAndFlush(dadoBiologico);

        // Get all the dadoBiologicoList
        restDadoBiologicoMockMvc.perform(get("/api/dado-biologicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dadoBiologico.getId().intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getDadoBiologico() throws Exception {
        // Initialize the database
        dadoBiologicoRepository.saveAndFlush(dadoBiologico);

        // Get the dadoBiologico
        restDadoBiologicoMockMvc.perform(get("/api/dado-biologicos/{id}", dadoBiologico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dadoBiologico.getId().intValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDadoBiologico() throws Exception {
        // Get the dadoBiologico
        restDadoBiologicoMockMvc.perform(get("/api/dado-biologicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDadoBiologico() throws Exception {
        // Initialize the database
        dadoBiologicoService.save(dadoBiologico);

        int databaseSizeBeforeUpdate = dadoBiologicoRepository.findAll().size();

        // Update the dadoBiologico
        DadoBiologico updatedDadoBiologico = dadoBiologicoRepository.findById(dadoBiologico.getId()).get();
        // Disconnect from session so that the updates on updatedDadoBiologico are not directly saved in db
        em.detach(updatedDadoBiologico);
        updatedDadoBiologico
            .valor(UPDATED_VALOR)
            .obs(UPDATED_OBS);

        restDadoBiologicoMockMvc.perform(put("/api/dado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDadoBiologico)))
            .andExpect(status().isOk());

        // Validate the DadoBiologico in the database
        List<DadoBiologico> dadoBiologicoList = dadoBiologicoRepository.findAll();
        assertThat(dadoBiologicoList).hasSize(databaseSizeBeforeUpdate);
        DadoBiologico testDadoBiologico = dadoBiologicoList.get(dadoBiologicoList.size() - 1);
        assertThat(testDadoBiologico.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testDadoBiologico.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingDadoBiologico() throws Exception {
        int databaseSizeBeforeUpdate = dadoBiologicoRepository.findAll().size();

        // Create the DadoBiologico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDadoBiologicoMockMvc.perform(put("/api/dado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dadoBiologico)))
            .andExpect(status().isBadRequest());

        // Validate the DadoBiologico in the database
        List<DadoBiologico> dadoBiologicoList = dadoBiologicoRepository.findAll();
        assertThat(dadoBiologicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDadoBiologico() throws Exception {
        // Initialize the database
        dadoBiologicoService.save(dadoBiologico);

        int databaseSizeBeforeDelete = dadoBiologicoRepository.findAll().size();

        // Get the dadoBiologico
        restDadoBiologicoMockMvc.perform(delete("/api/dado-biologicos/{id}", dadoBiologico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DadoBiologico> dadoBiologicoList = dadoBiologicoRepository.findAll();
        assertThat(dadoBiologicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DadoBiologico.class);
        DadoBiologico dadoBiologico1 = new DadoBiologico();
        dadoBiologico1.setId(1L);
        DadoBiologico dadoBiologico2 = new DadoBiologico();
        dadoBiologico2.setId(dadoBiologico1.getId());
        assertThat(dadoBiologico1).isEqualTo(dadoBiologico2);
        dadoBiologico2.setId(2L);
        assertThat(dadoBiologico1).isNotEqualTo(dadoBiologico2);
        dadoBiologico1.setId(null);
        assertThat(dadoBiologico1).isNotEqualTo(dadoBiologico2);
    }
}
