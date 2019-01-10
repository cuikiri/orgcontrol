package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ProblemaFisico;
import br.com.jhisolution.ong.control.repository.ProblemaFisicoRepository;
import br.com.jhisolution.ong.control.service.ProblemaFisicoService;
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
 * Test class for the ProblemaFisicoResource REST controller.
 *
 * @see ProblemaFisicoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ProblemaFisicoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ProblemaFisicoRepository problemaFisicoRepository;

    @Autowired
    private ProblemaFisicoService problemaFisicoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProblemaFisicoMockMvc;

    private ProblemaFisico problemaFisico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProblemaFisicoResource problemaFisicoResource = new ProblemaFisicoResource(problemaFisicoService);
        this.restProblemaFisicoMockMvc = MockMvcBuilders.standaloneSetup(problemaFisicoResource)
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
    public static ProblemaFisico createEntity(EntityManager em) {
        ProblemaFisico problemaFisico = new ProblemaFisico()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return problemaFisico;
    }

    @Before
    public void initTest() {
        problemaFisico = createEntity(em);
    }

    @Test
    @Transactional
    public void createProblemaFisico() throws Exception {
        int databaseSizeBeforeCreate = problemaFisicoRepository.findAll().size();

        // Create the ProblemaFisico
        restProblemaFisicoMockMvc.perform(post("/api/problema-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(problemaFisico)))
            .andExpect(status().isCreated());

        // Validate the ProblemaFisico in the database
        List<ProblemaFisico> problemaFisicoList = problemaFisicoRepository.findAll();
        assertThat(problemaFisicoList).hasSize(databaseSizeBeforeCreate + 1);
        ProblemaFisico testProblemaFisico = problemaFisicoList.get(problemaFisicoList.size() - 1);
        assertThat(testProblemaFisico.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testProblemaFisico.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createProblemaFisicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = problemaFisicoRepository.findAll().size();

        // Create the ProblemaFisico with an existing ID
        problemaFisico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProblemaFisicoMockMvc.perform(post("/api/problema-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(problemaFisico)))
            .andExpect(status().isBadRequest());

        // Validate the ProblemaFisico in the database
        List<ProblemaFisico> problemaFisicoList = problemaFisicoRepository.findAll();
        assertThat(problemaFisicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = problemaFisicoRepository.findAll().size();
        // set the field null
        problemaFisico.setNome(null);

        // Create the ProblemaFisico, which fails.

        restProblemaFisicoMockMvc.perform(post("/api/problema-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(problemaFisico)))
            .andExpect(status().isBadRequest());

        List<ProblemaFisico> problemaFisicoList = problemaFisicoRepository.findAll();
        assertThat(problemaFisicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProblemaFisicos() throws Exception {
        // Initialize the database
        problemaFisicoRepository.saveAndFlush(problemaFisico);

        // Get all the problemaFisicoList
        restProblemaFisicoMockMvc.perform(get("/api/problema-fisicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(problemaFisico.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getProblemaFisico() throws Exception {
        // Initialize the database
        problemaFisicoRepository.saveAndFlush(problemaFisico);

        // Get the problemaFisico
        restProblemaFisicoMockMvc.perform(get("/api/problema-fisicos/{id}", problemaFisico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(problemaFisico.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProblemaFisico() throws Exception {
        // Get the problemaFisico
        restProblemaFisicoMockMvc.perform(get("/api/problema-fisicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProblemaFisico() throws Exception {
        // Initialize the database
        problemaFisicoService.save(problemaFisico);

        int databaseSizeBeforeUpdate = problemaFisicoRepository.findAll().size();

        // Update the problemaFisico
        ProblemaFisico updatedProblemaFisico = problemaFisicoRepository.findById(problemaFisico.getId()).get();
        // Disconnect from session so that the updates on updatedProblemaFisico are not directly saved in db
        em.detach(updatedProblemaFisico);
        updatedProblemaFisico
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restProblemaFisicoMockMvc.perform(put("/api/problema-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProblemaFisico)))
            .andExpect(status().isOk());

        // Validate the ProblemaFisico in the database
        List<ProblemaFisico> problemaFisicoList = problemaFisicoRepository.findAll();
        assertThat(problemaFisicoList).hasSize(databaseSizeBeforeUpdate);
        ProblemaFisico testProblemaFisico = problemaFisicoList.get(problemaFisicoList.size() - 1);
        assertThat(testProblemaFisico.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testProblemaFisico.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingProblemaFisico() throws Exception {
        int databaseSizeBeforeUpdate = problemaFisicoRepository.findAll().size();

        // Create the ProblemaFisico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProblemaFisicoMockMvc.perform(put("/api/problema-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(problemaFisico)))
            .andExpect(status().isBadRequest());

        // Validate the ProblemaFisico in the database
        List<ProblemaFisico> problemaFisicoList = problemaFisicoRepository.findAll();
        assertThat(problemaFisicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProblemaFisico() throws Exception {
        // Initialize the database
        problemaFisicoService.save(problemaFisico);

        int databaseSizeBeforeDelete = problemaFisicoRepository.findAll().size();

        // Get the problemaFisico
        restProblemaFisicoMockMvc.perform(delete("/api/problema-fisicos/{id}", problemaFisico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProblemaFisico> problemaFisicoList = problemaFisicoRepository.findAll();
        assertThat(problemaFisicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProblemaFisico.class);
        ProblemaFisico problemaFisico1 = new ProblemaFisico();
        problemaFisico1.setId(1L);
        ProblemaFisico problemaFisico2 = new ProblemaFisico();
        problemaFisico2.setId(problemaFisico1.getId());
        assertThat(problemaFisico1).isEqualTo(problemaFisico2);
        problemaFisico2.setId(2L);
        assertThat(problemaFisico1).isNotEqualTo(problemaFisico2);
        problemaFisico1.setId(null);
        assertThat(problemaFisico1).isNotEqualTo(problemaFisico2);
    }
}
