package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoadoBiologico;
import br.com.jhisolution.ong.control.repository.TipoadoBiologicoRepository;
import br.com.jhisolution.ong.control.service.TipoadoBiologicoService;
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
 * Test class for the TipoadoBiologicoResource REST controller.
 *
 * @see TipoadoBiologicoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoadoBiologicoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoadoBiologicoRepository tipoadoBiologicoRepository;

    @Autowired
    private TipoadoBiologicoService tipoadoBiologicoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoadoBiologicoMockMvc;

    private TipoadoBiologico tipoadoBiologico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoadoBiologicoResource tipoadoBiologicoResource = new TipoadoBiologicoResource(tipoadoBiologicoService);
        this.restTipoadoBiologicoMockMvc = MockMvcBuilders.standaloneSetup(tipoadoBiologicoResource)
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
    public static TipoadoBiologico createEntity(EntityManager em) {
        TipoadoBiologico tipoadoBiologico = new TipoadoBiologico()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoadoBiologico;
    }

    @Before
    public void initTest() {
        tipoadoBiologico = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoadoBiologico() throws Exception {
        int databaseSizeBeforeCreate = tipoadoBiologicoRepository.findAll().size();

        // Create the TipoadoBiologico
        restTipoadoBiologicoMockMvc.perform(post("/api/tipoado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadoBiologico)))
            .andExpect(status().isCreated());

        // Validate the TipoadoBiologico in the database
        List<TipoadoBiologico> tipoadoBiologicoList = tipoadoBiologicoRepository.findAll();
        assertThat(tipoadoBiologicoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoadoBiologico testTipoadoBiologico = tipoadoBiologicoList.get(tipoadoBiologicoList.size() - 1);
        assertThat(testTipoadoBiologico.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoadoBiologico.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoadoBiologicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoadoBiologicoRepository.findAll().size();

        // Create the TipoadoBiologico with an existing ID
        tipoadoBiologico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoadoBiologicoMockMvc.perform(post("/api/tipoado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadoBiologico)))
            .andExpect(status().isBadRequest());

        // Validate the TipoadoBiologico in the database
        List<TipoadoBiologico> tipoadoBiologicoList = tipoadoBiologicoRepository.findAll();
        assertThat(tipoadoBiologicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoadoBiologicoRepository.findAll().size();
        // set the field null
        tipoadoBiologico.setNome(null);

        // Create the TipoadoBiologico, which fails.

        restTipoadoBiologicoMockMvc.perform(post("/api/tipoado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadoBiologico)))
            .andExpect(status().isBadRequest());

        List<TipoadoBiologico> tipoadoBiologicoList = tipoadoBiologicoRepository.findAll();
        assertThat(tipoadoBiologicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoadoBiologicos() throws Exception {
        // Initialize the database
        tipoadoBiologicoRepository.saveAndFlush(tipoadoBiologico);

        // Get all the tipoadoBiologicoList
        restTipoadoBiologicoMockMvc.perform(get("/api/tipoado-biologicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoadoBiologico.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoadoBiologico() throws Exception {
        // Initialize the database
        tipoadoBiologicoRepository.saveAndFlush(tipoadoBiologico);

        // Get the tipoadoBiologico
        restTipoadoBiologicoMockMvc.perform(get("/api/tipoado-biologicos/{id}", tipoadoBiologico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoadoBiologico.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoadoBiologico() throws Exception {
        // Get the tipoadoBiologico
        restTipoadoBiologicoMockMvc.perform(get("/api/tipoado-biologicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoadoBiologico() throws Exception {
        // Initialize the database
        tipoadoBiologicoService.save(tipoadoBiologico);

        int databaseSizeBeforeUpdate = tipoadoBiologicoRepository.findAll().size();

        // Update the tipoadoBiologico
        TipoadoBiologico updatedTipoadoBiologico = tipoadoBiologicoRepository.findById(tipoadoBiologico.getId()).get();
        // Disconnect from session so that the updates on updatedTipoadoBiologico are not directly saved in db
        em.detach(updatedTipoadoBiologico);
        updatedTipoadoBiologico
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoadoBiologicoMockMvc.perform(put("/api/tipoado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoadoBiologico)))
            .andExpect(status().isOk());

        // Validate the TipoadoBiologico in the database
        List<TipoadoBiologico> tipoadoBiologicoList = tipoadoBiologicoRepository.findAll();
        assertThat(tipoadoBiologicoList).hasSize(databaseSizeBeforeUpdate);
        TipoadoBiologico testTipoadoBiologico = tipoadoBiologicoList.get(tipoadoBiologicoList.size() - 1);
        assertThat(testTipoadoBiologico.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoadoBiologico.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoadoBiologico() throws Exception {
        int databaseSizeBeforeUpdate = tipoadoBiologicoRepository.findAll().size();

        // Create the TipoadoBiologico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoadoBiologicoMockMvc.perform(put("/api/tipoado-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadoBiologico)))
            .andExpect(status().isBadRequest());

        // Validate the TipoadoBiologico in the database
        List<TipoadoBiologico> tipoadoBiologicoList = tipoadoBiologicoRepository.findAll();
        assertThat(tipoadoBiologicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoadoBiologico() throws Exception {
        // Initialize the database
        tipoadoBiologicoService.save(tipoadoBiologico);

        int databaseSizeBeforeDelete = tipoadoBiologicoRepository.findAll().size();

        // Get the tipoadoBiologico
        restTipoadoBiologicoMockMvc.perform(delete("/api/tipoado-biologicos/{id}", tipoadoBiologico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoadoBiologico> tipoadoBiologicoList = tipoadoBiologicoRepository.findAll();
        assertThat(tipoadoBiologicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoadoBiologico.class);
        TipoadoBiologico tipoadoBiologico1 = new TipoadoBiologico();
        tipoadoBiologico1.setId(1L);
        TipoadoBiologico tipoadoBiologico2 = new TipoadoBiologico();
        tipoadoBiologico2.setId(tipoadoBiologico1.getId());
        assertThat(tipoadoBiologico1).isEqualTo(tipoadoBiologico2);
        tipoadoBiologico2.setId(2L);
        assertThat(tipoadoBiologico1).isNotEqualTo(tipoadoBiologico2);
        tipoadoBiologico1.setId(null);
        assertThat(tipoadoBiologico1).isNotEqualTo(tipoadoBiologico2);
    }
}
