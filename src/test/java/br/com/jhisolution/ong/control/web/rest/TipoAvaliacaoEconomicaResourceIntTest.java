package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoAvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.TipoAvaliacaoEconomicaRepository;
import br.com.jhisolution.ong.control.service.TipoAvaliacaoEconomicaService;
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
 * Test class for the TipoAvaliacaoEconomicaResource REST controller.
 *
 * @see TipoAvaliacaoEconomicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoAvaliacaoEconomicaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoAvaliacaoEconomicaRepository tipoAvaliacaoEconomicaRepository;

    @Autowired
    private TipoAvaliacaoEconomicaService tipoAvaliacaoEconomicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoAvaliacaoEconomicaMockMvc;

    private TipoAvaliacaoEconomica tipoAvaliacaoEconomica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoAvaliacaoEconomicaResource tipoAvaliacaoEconomicaResource = new TipoAvaliacaoEconomicaResource(tipoAvaliacaoEconomicaService);
        this.restTipoAvaliacaoEconomicaMockMvc = MockMvcBuilders.standaloneSetup(tipoAvaliacaoEconomicaResource)
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
    public static TipoAvaliacaoEconomica createEntity(EntityManager em) {
        TipoAvaliacaoEconomica tipoAvaliacaoEconomica = new TipoAvaliacaoEconomica()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoAvaliacaoEconomica;
    }

    @Before
    public void initTest() {
        tipoAvaliacaoEconomica = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeCreate = tipoAvaliacaoEconomicaRepository.findAll().size();

        // Create the TipoAvaliacaoEconomica
        restTipoAvaliacaoEconomicaMockMvc.perform(post("/api/tipo-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacaoEconomica)))
            .andExpect(status().isCreated());

        // Validate the TipoAvaliacaoEconomica in the database
        List<TipoAvaliacaoEconomica> tipoAvaliacaoEconomicaList = tipoAvaliacaoEconomicaRepository.findAll();
        assertThat(tipoAvaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate + 1);
        TipoAvaliacaoEconomica testTipoAvaliacaoEconomica = tipoAvaliacaoEconomicaList.get(tipoAvaliacaoEconomicaList.size() - 1);
        assertThat(testTipoAvaliacaoEconomica.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoAvaliacaoEconomica.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoAvaliacaoEconomicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoAvaliacaoEconomicaRepository.findAll().size();

        // Create the TipoAvaliacaoEconomica with an existing ID
        tipoAvaliacaoEconomica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoAvaliacaoEconomicaMockMvc.perform(post("/api/tipo-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAvaliacaoEconomica in the database
        List<TipoAvaliacaoEconomica> tipoAvaliacaoEconomicaList = tipoAvaliacaoEconomicaRepository.findAll();
        assertThat(tipoAvaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAvaliacaoEconomicaRepository.findAll().size();
        // set the field null
        tipoAvaliacaoEconomica.setNome(null);

        // Create the TipoAvaliacaoEconomica, which fails.

        restTipoAvaliacaoEconomicaMockMvc.perform(post("/api/tipo-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<TipoAvaliacaoEconomica> tipoAvaliacaoEconomicaList = tipoAvaliacaoEconomicaRepository.findAll();
        assertThat(tipoAvaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoAvaliacaoEconomicas() throws Exception {
        // Initialize the database
        tipoAvaliacaoEconomicaRepository.saveAndFlush(tipoAvaliacaoEconomica);

        // Get all the tipoAvaliacaoEconomicaList
        restTipoAvaliacaoEconomicaMockMvc.perform(get("/api/tipo-avaliacao-economicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAvaliacaoEconomica.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoAvaliacaoEconomica() throws Exception {
        // Initialize the database
        tipoAvaliacaoEconomicaRepository.saveAndFlush(tipoAvaliacaoEconomica);

        // Get the tipoAvaliacaoEconomica
        restTipoAvaliacaoEconomicaMockMvc.perform(get("/api/tipo-avaliacao-economicas/{id}", tipoAvaliacaoEconomica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoAvaliacaoEconomica.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoAvaliacaoEconomica() throws Exception {
        // Get the tipoAvaliacaoEconomica
        restTipoAvaliacaoEconomicaMockMvc.perform(get("/api/tipo-avaliacao-economicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoAvaliacaoEconomica() throws Exception {
        // Initialize the database
        tipoAvaliacaoEconomicaService.save(tipoAvaliacaoEconomica);

        int databaseSizeBeforeUpdate = tipoAvaliacaoEconomicaRepository.findAll().size();

        // Update the tipoAvaliacaoEconomica
        TipoAvaliacaoEconomica updatedTipoAvaliacaoEconomica = tipoAvaliacaoEconomicaRepository.findById(tipoAvaliacaoEconomica.getId()).get();
        // Disconnect from session so that the updates on updatedTipoAvaliacaoEconomica are not directly saved in db
        em.detach(updatedTipoAvaliacaoEconomica);
        updatedTipoAvaliacaoEconomica
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoAvaliacaoEconomicaMockMvc.perform(put("/api/tipo-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoAvaliacaoEconomica)))
            .andExpect(status().isOk());

        // Validate the TipoAvaliacaoEconomica in the database
        List<TipoAvaliacaoEconomica> tipoAvaliacaoEconomicaList = tipoAvaliacaoEconomicaRepository.findAll();
        assertThat(tipoAvaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
        TipoAvaliacaoEconomica testTipoAvaliacaoEconomica = tipoAvaliacaoEconomicaList.get(tipoAvaliacaoEconomicaList.size() - 1);
        assertThat(testTipoAvaliacaoEconomica.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoAvaliacaoEconomica.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeUpdate = tipoAvaliacaoEconomicaRepository.findAll().size();

        // Create the TipoAvaliacaoEconomica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoAvaliacaoEconomicaMockMvc.perform(put("/api/tipo-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAvaliacaoEconomica in the database
        List<TipoAvaliacaoEconomica> tipoAvaliacaoEconomicaList = tipoAvaliacaoEconomicaRepository.findAll();
        assertThat(tipoAvaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoAvaliacaoEconomica() throws Exception {
        // Initialize the database
        tipoAvaliacaoEconomicaService.save(tipoAvaliacaoEconomica);

        int databaseSizeBeforeDelete = tipoAvaliacaoEconomicaRepository.findAll().size();

        // Get the tipoAvaliacaoEconomica
        restTipoAvaliacaoEconomicaMockMvc.perform(delete("/api/tipo-avaliacao-economicas/{id}", tipoAvaliacaoEconomica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoAvaliacaoEconomica> tipoAvaliacaoEconomicaList = tipoAvaliacaoEconomicaRepository.findAll();
        assertThat(tipoAvaliacaoEconomicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAvaliacaoEconomica.class);
        TipoAvaliacaoEconomica tipoAvaliacaoEconomica1 = new TipoAvaliacaoEconomica();
        tipoAvaliacaoEconomica1.setId(1L);
        TipoAvaliacaoEconomica tipoAvaliacaoEconomica2 = new TipoAvaliacaoEconomica();
        tipoAvaliacaoEconomica2.setId(tipoAvaliacaoEconomica1.getId());
        assertThat(tipoAvaliacaoEconomica1).isEqualTo(tipoAvaliacaoEconomica2);
        tipoAvaliacaoEconomica2.setId(2L);
        assertThat(tipoAvaliacaoEconomica1).isNotEqualTo(tipoAvaliacaoEconomica2);
        tipoAvaliacaoEconomica1.setId(null);
        assertThat(tipoAvaliacaoEconomica1).isNotEqualTo(tipoAvaliacaoEconomica2);
    }
}
