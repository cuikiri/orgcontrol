package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoColaborador;
import br.com.jhisolution.ong.control.repository.TipoColaboradorRepository;
import br.com.jhisolution.ong.control.service.TipoColaboradorService;
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
 * Test class for the TipoColaboradorResource REST controller.
 *
 * @see TipoColaboradorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoColaboradorResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoColaboradorRepository tipoColaboradorRepository;

    @Autowired
    private TipoColaboradorService tipoColaboradorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoColaboradorMockMvc;

    private TipoColaborador tipoColaborador;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoColaboradorResource tipoColaboradorResource = new TipoColaboradorResource(tipoColaboradorService);
        this.restTipoColaboradorMockMvc = MockMvcBuilders.standaloneSetup(tipoColaboradorResource)
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
    public static TipoColaborador createEntity(EntityManager em) {
        TipoColaborador tipoColaborador = new TipoColaborador()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoColaborador;
    }

    @Before
    public void initTest() {
        tipoColaborador = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoColaborador() throws Exception {
        int databaseSizeBeforeCreate = tipoColaboradorRepository.findAll().size();

        // Create the TipoColaborador
        restTipoColaboradorMockMvc.perform(post("/api/tipo-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoColaborador)))
            .andExpect(status().isCreated());

        // Validate the TipoColaborador in the database
        List<TipoColaborador> tipoColaboradorList = tipoColaboradorRepository.findAll();
        assertThat(tipoColaboradorList).hasSize(databaseSizeBeforeCreate + 1);
        TipoColaborador testTipoColaborador = tipoColaboradorList.get(tipoColaboradorList.size() - 1);
        assertThat(testTipoColaborador.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoColaborador.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoColaboradorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoColaboradorRepository.findAll().size();

        // Create the TipoColaborador with an existing ID
        tipoColaborador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoColaboradorMockMvc.perform(post("/api/tipo-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoColaborador)))
            .andExpect(status().isBadRequest());

        // Validate the TipoColaborador in the database
        List<TipoColaborador> tipoColaboradorList = tipoColaboradorRepository.findAll();
        assertThat(tipoColaboradorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoColaboradorRepository.findAll().size();
        // set the field null
        tipoColaborador.setNome(null);

        // Create the TipoColaborador, which fails.

        restTipoColaboradorMockMvc.perform(post("/api/tipo-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoColaborador)))
            .andExpect(status().isBadRequest());

        List<TipoColaborador> tipoColaboradorList = tipoColaboradorRepository.findAll();
        assertThat(tipoColaboradorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoColaboradors() throws Exception {
        // Initialize the database
        tipoColaboradorRepository.saveAndFlush(tipoColaborador);

        // Get all the tipoColaboradorList
        restTipoColaboradorMockMvc.perform(get("/api/tipo-colaboradors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoColaborador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoColaborador() throws Exception {
        // Initialize the database
        tipoColaboradorRepository.saveAndFlush(tipoColaborador);

        // Get the tipoColaborador
        restTipoColaboradorMockMvc.perform(get("/api/tipo-colaboradors/{id}", tipoColaborador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoColaborador.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoColaborador() throws Exception {
        // Get the tipoColaborador
        restTipoColaboradorMockMvc.perform(get("/api/tipo-colaboradors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoColaborador() throws Exception {
        // Initialize the database
        tipoColaboradorService.save(tipoColaborador);

        int databaseSizeBeforeUpdate = tipoColaboradorRepository.findAll().size();

        // Update the tipoColaborador
        TipoColaborador updatedTipoColaborador = tipoColaboradorRepository.findById(tipoColaborador.getId()).get();
        // Disconnect from session so that the updates on updatedTipoColaborador are not directly saved in db
        em.detach(updatedTipoColaborador);
        updatedTipoColaborador
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoColaboradorMockMvc.perform(put("/api/tipo-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoColaborador)))
            .andExpect(status().isOk());

        // Validate the TipoColaborador in the database
        List<TipoColaborador> tipoColaboradorList = tipoColaboradorRepository.findAll();
        assertThat(tipoColaboradorList).hasSize(databaseSizeBeforeUpdate);
        TipoColaborador testTipoColaborador = tipoColaboradorList.get(tipoColaboradorList.size() - 1);
        assertThat(testTipoColaborador.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoColaborador.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoColaborador() throws Exception {
        int databaseSizeBeforeUpdate = tipoColaboradorRepository.findAll().size();

        // Create the TipoColaborador

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoColaboradorMockMvc.perform(put("/api/tipo-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoColaborador)))
            .andExpect(status().isBadRequest());

        // Validate the TipoColaborador in the database
        List<TipoColaborador> tipoColaboradorList = tipoColaboradorRepository.findAll();
        assertThat(tipoColaboradorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoColaborador() throws Exception {
        // Initialize the database
        tipoColaboradorService.save(tipoColaborador);

        int databaseSizeBeforeDelete = tipoColaboradorRepository.findAll().size();

        // Get the tipoColaborador
        restTipoColaboradorMockMvc.perform(delete("/api/tipo-colaboradors/{id}", tipoColaborador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoColaborador> tipoColaboradorList = tipoColaboradorRepository.findAll();
        assertThat(tipoColaboradorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoColaborador.class);
        TipoColaborador tipoColaborador1 = new TipoColaborador();
        tipoColaborador1.setId(1L);
        TipoColaborador tipoColaborador2 = new TipoColaborador();
        tipoColaborador2.setId(tipoColaborador1.getId());
        assertThat(tipoColaborador1).isEqualTo(tipoColaborador2);
        tipoColaborador2.setId(2L);
        assertThat(tipoColaborador1).isNotEqualTo(tipoColaborador2);
        tipoColaborador1.setId(null);
        assertThat(tipoColaborador1).isNotEqualTo(tipoColaborador2);
    }
}
