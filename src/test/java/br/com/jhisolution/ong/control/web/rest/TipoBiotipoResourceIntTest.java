package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoBiotipo;
import br.com.jhisolution.ong.control.repository.TipoBiotipoRepository;
import br.com.jhisolution.ong.control.service.TipoBiotipoService;
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
 * Test class for the TipoBiotipoResource REST controller.
 *
 * @see TipoBiotipoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoBiotipoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoBiotipoRepository tipoBiotipoRepository;

    @Autowired
    private TipoBiotipoService tipoBiotipoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoBiotipoMockMvc;

    private TipoBiotipo tipoBiotipo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoBiotipoResource tipoBiotipoResource = new TipoBiotipoResource(tipoBiotipoService);
        this.restTipoBiotipoMockMvc = MockMvcBuilders.standaloneSetup(tipoBiotipoResource)
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
    public static TipoBiotipo createEntity(EntityManager em) {
        TipoBiotipo tipoBiotipo = new TipoBiotipo()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoBiotipo;
    }

    @Before
    public void initTest() {
        tipoBiotipo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoBiotipo() throws Exception {
        int databaseSizeBeforeCreate = tipoBiotipoRepository.findAll().size();

        // Create the TipoBiotipo
        restTipoBiotipoMockMvc.perform(post("/api/tipo-biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBiotipo)))
            .andExpect(status().isCreated());

        // Validate the TipoBiotipo in the database
        List<TipoBiotipo> tipoBiotipoList = tipoBiotipoRepository.findAll();
        assertThat(tipoBiotipoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoBiotipo testTipoBiotipo = tipoBiotipoList.get(tipoBiotipoList.size() - 1);
        assertThat(testTipoBiotipo.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoBiotipo.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoBiotipoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoBiotipoRepository.findAll().size();

        // Create the TipoBiotipo with an existing ID
        tipoBiotipo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoBiotipoMockMvc.perform(post("/api/tipo-biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBiotipo)))
            .andExpect(status().isBadRequest());

        // Validate the TipoBiotipo in the database
        List<TipoBiotipo> tipoBiotipoList = tipoBiotipoRepository.findAll();
        assertThat(tipoBiotipoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoBiotipoRepository.findAll().size();
        // set the field null
        tipoBiotipo.setNome(null);

        // Create the TipoBiotipo, which fails.

        restTipoBiotipoMockMvc.perform(post("/api/tipo-biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBiotipo)))
            .andExpect(status().isBadRequest());

        List<TipoBiotipo> tipoBiotipoList = tipoBiotipoRepository.findAll();
        assertThat(tipoBiotipoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoBiotipos() throws Exception {
        // Initialize the database
        tipoBiotipoRepository.saveAndFlush(tipoBiotipo);

        // Get all the tipoBiotipoList
        restTipoBiotipoMockMvc.perform(get("/api/tipo-biotipos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoBiotipo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoBiotipo() throws Exception {
        // Initialize the database
        tipoBiotipoRepository.saveAndFlush(tipoBiotipo);

        // Get the tipoBiotipo
        restTipoBiotipoMockMvc.perform(get("/api/tipo-biotipos/{id}", tipoBiotipo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoBiotipo.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoBiotipo() throws Exception {
        // Get the tipoBiotipo
        restTipoBiotipoMockMvc.perform(get("/api/tipo-biotipos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoBiotipo() throws Exception {
        // Initialize the database
        tipoBiotipoService.save(tipoBiotipo);

        int databaseSizeBeforeUpdate = tipoBiotipoRepository.findAll().size();

        // Update the tipoBiotipo
        TipoBiotipo updatedTipoBiotipo = tipoBiotipoRepository.findById(tipoBiotipo.getId()).get();
        // Disconnect from session so that the updates on updatedTipoBiotipo are not directly saved in db
        em.detach(updatedTipoBiotipo);
        updatedTipoBiotipo
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoBiotipoMockMvc.perform(put("/api/tipo-biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoBiotipo)))
            .andExpect(status().isOk());

        // Validate the TipoBiotipo in the database
        List<TipoBiotipo> tipoBiotipoList = tipoBiotipoRepository.findAll();
        assertThat(tipoBiotipoList).hasSize(databaseSizeBeforeUpdate);
        TipoBiotipo testTipoBiotipo = tipoBiotipoList.get(tipoBiotipoList.size() - 1);
        assertThat(testTipoBiotipo.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoBiotipo.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoBiotipo() throws Exception {
        int databaseSizeBeforeUpdate = tipoBiotipoRepository.findAll().size();

        // Create the TipoBiotipo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoBiotipoMockMvc.perform(put("/api/tipo-biotipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBiotipo)))
            .andExpect(status().isBadRequest());

        // Validate the TipoBiotipo in the database
        List<TipoBiotipo> tipoBiotipoList = tipoBiotipoRepository.findAll();
        assertThat(tipoBiotipoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoBiotipo() throws Exception {
        // Initialize the database
        tipoBiotipoService.save(tipoBiotipo);

        int databaseSizeBeforeDelete = tipoBiotipoRepository.findAll().size();

        // Get the tipoBiotipo
        restTipoBiotipoMockMvc.perform(delete("/api/tipo-biotipos/{id}", tipoBiotipo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoBiotipo> tipoBiotipoList = tipoBiotipoRepository.findAll();
        assertThat(tipoBiotipoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoBiotipo.class);
        TipoBiotipo tipoBiotipo1 = new TipoBiotipo();
        tipoBiotipo1.setId(1L);
        TipoBiotipo tipoBiotipo2 = new TipoBiotipo();
        tipoBiotipo2.setId(tipoBiotipo1.getId());
        assertThat(tipoBiotipo1).isEqualTo(tipoBiotipo2);
        tipoBiotipo2.setId(2L);
        assertThat(tipoBiotipo1).isNotEqualTo(tipoBiotipo2);
        tipoBiotipo1.setId(null);
        assertThat(tipoBiotipo1).isNotEqualTo(tipoBiotipo2);
    }
}
