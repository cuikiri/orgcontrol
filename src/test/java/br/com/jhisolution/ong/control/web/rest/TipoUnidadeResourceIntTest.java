package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoUnidade;
import br.com.jhisolution.ong.control.repository.TipoUnidadeRepository;
import br.com.jhisolution.ong.control.service.TipoUnidadeService;
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
 * Test class for the TipoUnidadeResource REST controller.
 *
 * @see TipoUnidadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoUnidadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoUnidadeRepository tipoUnidadeRepository;

    @Autowired
    private TipoUnidadeService tipoUnidadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoUnidadeMockMvc;

    private TipoUnidade tipoUnidade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoUnidadeResource tipoUnidadeResource = new TipoUnidadeResource(tipoUnidadeService);
        this.restTipoUnidadeMockMvc = MockMvcBuilders.standaloneSetup(tipoUnidadeResource)
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
    public static TipoUnidade createEntity(EntityManager em) {
        TipoUnidade tipoUnidade = new TipoUnidade()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoUnidade;
    }

    @Before
    public void initTest() {
        tipoUnidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoUnidade() throws Exception {
        int databaseSizeBeforeCreate = tipoUnidadeRepository.findAll().size();

        // Create the TipoUnidade
        restTipoUnidadeMockMvc.perform(post("/api/tipo-unidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUnidade)))
            .andExpect(status().isCreated());

        // Validate the TipoUnidade in the database
        List<TipoUnidade> tipoUnidadeList = tipoUnidadeRepository.findAll();
        assertThat(tipoUnidadeList).hasSize(databaseSizeBeforeCreate + 1);
        TipoUnidade testTipoUnidade = tipoUnidadeList.get(tipoUnidadeList.size() - 1);
        assertThat(testTipoUnidade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoUnidade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoUnidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoUnidadeRepository.findAll().size();

        // Create the TipoUnidade with an existing ID
        tipoUnidade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoUnidadeMockMvc.perform(post("/api/tipo-unidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUnidade)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUnidade in the database
        List<TipoUnidade> tipoUnidadeList = tipoUnidadeRepository.findAll();
        assertThat(tipoUnidadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoUnidadeRepository.findAll().size();
        // set the field null
        tipoUnidade.setNome(null);

        // Create the TipoUnidade, which fails.

        restTipoUnidadeMockMvc.perform(post("/api/tipo-unidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUnidade)))
            .andExpect(status().isBadRequest());

        List<TipoUnidade> tipoUnidadeList = tipoUnidadeRepository.findAll();
        assertThat(tipoUnidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoUnidades() throws Exception {
        // Initialize the database
        tipoUnidadeRepository.saveAndFlush(tipoUnidade);

        // Get all the tipoUnidadeList
        restTipoUnidadeMockMvc.perform(get("/api/tipo-unidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoUnidade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoUnidade() throws Exception {
        // Initialize the database
        tipoUnidadeRepository.saveAndFlush(tipoUnidade);

        // Get the tipoUnidade
        restTipoUnidadeMockMvc.perform(get("/api/tipo-unidades/{id}", tipoUnidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoUnidade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoUnidade() throws Exception {
        // Get the tipoUnidade
        restTipoUnidadeMockMvc.perform(get("/api/tipo-unidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoUnidade() throws Exception {
        // Initialize the database
        tipoUnidadeService.save(tipoUnidade);

        int databaseSizeBeforeUpdate = tipoUnidadeRepository.findAll().size();

        // Update the tipoUnidade
        TipoUnidade updatedTipoUnidade = tipoUnidadeRepository.findById(tipoUnidade.getId()).get();
        // Disconnect from session so that the updates on updatedTipoUnidade are not directly saved in db
        em.detach(updatedTipoUnidade);
        updatedTipoUnidade
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoUnidadeMockMvc.perform(put("/api/tipo-unidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoUnidade)))
            .andExpect(status().isOk());

        // Validate the TipoUnidade in the database
        List<TipoUnidade> tipoUnidadeList = tipoUnidadeRepository.findAll();
        assertThat(tipoUnidadeList).hasSize(databaseSizeBeforeUpdate);
        TipoUnidade testTipoUnidade = tipoUnidadeList.get(tipoUnidadeList.size() - 1);
        assertThat(testTipoUnidade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoUnidade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoUnidade() throws Exception {
        int databaseSizeBeforeUpdate = tipoUnidadeRepository.findAll().size();

        // Create the TipoUnidade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoUnidadeMockMvc.perform(put("/api/tipo-unidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUnidade)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUnidade in the database
        List<TipoUnidade> tipoUnidadeList = tipoUnidadeRepository.findAll();
        assertThat(tipoUnidadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoUnidade() throws Exception {
        // Initialize the database
        tipoUnidadeService.save(tipoUnidade);

        int databaseSizeBeforeDelete = tipoUnidadeRepository.findAll().size();

        // Get the tipoUnidade
        restTipoUnidadeMockMvc.perform(delete("/api/tipo-unidades/{id}", tipoUnidade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoUnidade> tipoUnidadeList = tipoUnidadeRepository.findAll();
        assertThat(tipoUnidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoUnidade.class);
        TipoUnidade tipoUnidade1 = new TipoUnidade();
        tipoUnidade1.setId(1L);
        TipoUnidade tipoUnidade2 = new TipoUnidade();
        tipoUnidade2.setId(tipoUnidade1.getId());
        assertThat(tipoUnidade1).isEqualTo(tipoUnidade2);
        tipoUnidade2.setId(2L);
        assertThat(tipoUnidade1).isNotEqualTo(tipoUnidade2);
        tipoUnidade1.setId(null);
        assertThat(tipoUnidade1).isNotEqualTo(tipoUnidade2);
    }
}
