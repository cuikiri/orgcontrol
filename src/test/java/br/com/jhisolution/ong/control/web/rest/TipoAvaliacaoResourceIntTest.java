package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoAvaliacao;
import br.com.jhisolution.ong.control.repository.TipoAvaliacaoRepository;
import br.com.jhisolution.ong.control.service.TipoAvaliacaoService;
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
 * Test class for the TipoAvaliacaoResource REST controller.
 *
 * @see TipoAvaliacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoAvaliacaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoAvaliacaoRepository tipoAvaliacaoRepository;

    @Autowired
    private TipoAvaliacaoService tipoAvaliacaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoAvaliacaoMockMvc;

    private TipoAvaliacao tipoAvaliacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoAvaliacaoResource tipoAvaliacaoResource = new TipoAvaliacaoResource(tipoAvaliacaoService);
        this.restTipoAvaliacaoMockMvc = MockMvcBuilders.standaloneSetup(tipoAvaliacaoResource)
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
    public static TipoAvaliacao createEntity(EntityManager em) {
        TipoAvaliacao tipoAvaliacao = new TipoAvaliacao()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoAvaliacao;
    }

    @Before
    public void initTest() {
        tipoAvaliacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoAvaliacao() throws Exception {
        int databaseSizeBeforeCreate = tipoAvaliacaoRepository.findAll().size();

        // Create the TipoAvaliacao
        restTipoAvaliacaoMockMvc.perform(post("/api/tipo-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacao)))
            .andExpect(status().isCreated());

        // Validate the TipoAvaliacao in the database
        List<TipoAvaliacao> tipoAvaliacaoList = tipoAvaliacaoRepository.findAll();
        assertThat(tipoAvaliacaoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoAvaliacao testTipoAvaliacao = tipoAvaliacaoList.get(tipoAvaliacaoList.size() - 1);
        assertThat(testTipoAvaliacao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoAvaliacao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoAvaliacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoAvaliacaoRepository.findAll().size();

        // Create the TipoAvaliacao with an existing ID
        tipoAvaliacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoAvaliacaoMockMvc.perform(post("/api/tipo-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacao)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAvaliacao in the database
        List<TipoAvaliacao> tipoAvaliacaoList = tipoAvaliacaoRepository.findAll();
        assertThat(tipoAvaliacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAvaliacaoRepository.findAll().size();
        // set the field null
        tipoAvaliacao.setNome(null);

        // Create the TipoAvaliacao, which fails.

        restTipoAvaliacaoMockMvc.perform(post("/api/tipo-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacao)))
            .andExpect(status().isBadRequest());

        List<TipoAvaliacao> tipoAvaliacaoList = tipoAvaliacaoRepository.findAll();
        assertThat(tipoAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoAvaliacaos() throws Exception {
        // Initialize the database
        tipoAvaliacaoRepository.saveAndFlush(tipoAvaliacao);

        // Get all the tipoAvaliacaoList
        restTipoAvaliacaoMockMvc.perform(get("/api/tipo-avaliacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAvaliacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoAvaliacao() throws Exception {
        // Initialize the database
        tipoAvaliacaoRepository.saveAndFlush(tipoAvaliacao);

        // Get the tipoAvaliacao
        restTipoAvaliacaoMockMvc.perform(get("/api/tipo-avaliacaos/{id}", tipoAvaliacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoAvaliacao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoAvaliacao() throws Exception {
        // Get the tipoAvaliacao
        restTipoAvaliacaoMockMvc.perform(get("/api/tipo-avaliacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoAvaliacao() throws Exception {
        // Initialize the database
        tipoAvaliacaoService.save(tipoAvaliacao);

        int databaseSizeBeforeUpdate = tipoAvaliacaoRepository.findAll().size();

        // Update the tipoAvaliacao
        TipoAvaliacao updatedTipoAvaliacao = tipoAvaliacaoRepository.findById(tipoAvaliacao.getId()).get();
        // Disconnect from session so that the updates on updatedTipoAvaliacao are not directly saved in db
        em.detach(updatedTipoAvaliacao);
        updatedTipoAvaliacao
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoAvaliacaoMockMvc.perform(put("/api/tipo-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoAvaliacao)))
            .andExpect(status().isOk());

        // Validate the TipoAvaliacao in the database
        List<TipoAvaliacao> tipoAvaliacaoList = tipoAvaliacaoRepository.findAll();
        assertThat(tipoAvaliacaoList).hasSize(databaseSizeBeforeUpdate);
        TipoAvaliacao testTipoAvaliacao = tipoAvaliacaoList.get(tipoAvaliacaoList.size() - 1);
        assertThat(testTipoAvaliacao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoAvaliacao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoAvaliacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoAvaliacaoRepository.findAll().size();

        // Create the TipoAvaliacao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoAvaliacaoMockMvc.perform(put("/api/tipo-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAvaliacao)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAvaliacao in the database
        List<TipoAvaliacao> tipoAvaliacaoList = tipoAvaliacaoRepository.findAll();
        assertThat(tipoAvaliacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoAvaliacao() throws Exception {
        // Initialize the database
        tipoAvaliacaoService.save(tipoAvaliacao);

        int databaseSizeBeforeDelete = tipoAvaliacaoRepository.findAll().size();

        // Get the tipoAvaliacao
        restTipoAvaliacaoMockMvc.perform(delete("/api/tipo-avaliacaos/{id}", tipoAvaliacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoAvaliacao> tipoAvaliacaoList = tipoAvaliacaoRepository.findAll();
        assertThat(tipoAvaliacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAvaliacao.class);
        TipoAvaliacao tipoAvaliacao1 = new TipoAvaliacao();
        tipoAvaliacao1.setId(1L);
        TipoAvaliacao tipoAvaliacao2 = new TipoAvaliacao();
        tipoAvaliacao2.setId(tipoAvaliacao1.getId());
        assertThat(tipoAvaliacao1).isEqualTo(tipoAvaliacao2);
        tipoAvaliacao2.setId(2L);
        assertThat(tipoAvaliacao1).isNotEqualTo(tipoAvaliacao2);
        tipoAvaliacao1.setId(null);
        assertThat(tipoAvaliacao1).isNotEqualTo(tipoAvaliacao2);
    }
}
