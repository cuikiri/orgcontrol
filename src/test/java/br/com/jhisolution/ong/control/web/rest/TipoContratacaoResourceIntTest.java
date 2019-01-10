package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoContratacao;
import br.com.jhisolution.ong.control.repository.TipoContratacaoRepository;
import br.com.jhisolution.ong.control.service.TipoContratacaoService;
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
 * Test class for the TipoContratacaoResource REST controller.
 *
 * @see TipoContratacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoContratacaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoContratacaoRepository tipoContratacaoRepository;

    @Autowired
    private TipoContratacaoService tipoContratacaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoContratacaoMockMvc;

    private TipoContratacao tipoContratacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoContratacaoResource tipoContratacaoResource = new TipoContratacaoResource(tipoContratacaoService);
        this.restTipoContratacaoMockMvc = MockMvcBuilders.standaloneSetup(tipoContratacaoResource)
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
    public static TipoContratacao createEntity(EntityManager em) {
        TipoContratacao tipoContratacao = new TipoContratacao()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoContratacao;
    }

    @Before
    public void initTest() {
        tipoContratacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoContratacao() throws Exception {
        int databaseSizeBeforeCreate = tipoContratacaoRepository.findAll().size();

        // Create the TipoContratacao
        restTipoContratacaoMockMvc.perform(post("/api/tipo-contratacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoContratacao)))
            .andExpect(status().isCreated());

        // Validate the TipoContratacao in the database
        List<TipoContratacao> tipoContratacaoList = tipoContratacaoRepository.findAll();
        assertThat(tipoContratacaoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoContratacao testTipoContratacao = tipoContratacaoList.get(tipoContratacaoList.size() - 1);
        assertThat(testTipoContratacao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoContratacao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoContratacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoContratacaoRepository.findAll().size();

        // Create the TipoContratacao with an existing ID
        tipoContratacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoContratacaoMockMvc.perform(post("/api/tipo-contratacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoContratacao)))
            .andExpect(status().isBadRequest());

        // Validate the TipoContratacao in the database
        List<TipoContratacao> tipoContratacaoList = tipoContratacaoRepository.findAll();
        assertThat(tipoContratacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoContratacaoRepository.findAll().size();
        // set the field null
        tipoContratacao.setNome(null);

        // Create the TipoContratacao, which fails.

        restTipoContratacaoMockMvc.perform(post("/api/tipo-contratacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoContratacao)))
            .andExpect(status().isBadRequest());

        List<TipoContratacao> tipoContratacaoList = tipoContratacaoRepository.findAll();
        assertThat(tipoContratacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoContratacaos() throws Exception {
        // Initialize the database
        tipoContratacaoRepository.saveAndFlush(tipoContratacao);

        // Get all the tipoContratacaoList
        restTipoContratacaoMockMvc.perform(get("/api/tipo-contratacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoContratacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoContratacao() throws Exception {
        // Initialize the database
        tipoContratacaoRepository.saveAndFlush(tipoContratacao);

        // Get the tipoContratacao
        restTipoContratacaoMockMvc.perform(get("/api/tipo-contratacaos/{id}", tipoContratacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoContratacao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoContratacao() throws Exception {
        // Get the tipoContratacao
        restTipoContratacaoMockMvc.perform(get("/api/tipo-contratacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoContratacao() throws Exception {
        // Initialize the database
        tipoContratacaoService.save(tipoContratacao);

        int databaseSizeBeforeUpdate = tipoContratacaoRepository.findAll().size();

        // Update the tipoContratacao
        TipoContratacao updatedTipoContratacao = tipoContratacaoRepository.findById(tipoContratacao.getId()).get();
        // Disconnect from session so that the updates on updatedTipoContratacao are not directly saved in db
        em.detach(updatedTipoContratacao);
        updatedTipoContratacao
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoContratacaoMockMvc.perform(put("/api/tipo-contratacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoContratacao)))
            .andExpect(status().isOk());

        // Validate the TipoContratacao in the database
        List<TipoContratacao> tipoContratacaoList = tipoContratacaoRepository.findAll();
        assertThat(tipoContratacaoList).hasSize(databaseSizeBeforeUpdate);
        TipoContratacao testTipoContratacao = tipoContratacaoList.get(tipoContratacaoList.size() - 1);
        assertThat(testTipoContratacao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoContratacao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoContratacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoContratacaoRepository.findAll().size();

        // Create the TipoContratacao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoContratacaoMockMvc.perform(put("/api/tipo-contratacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoContratacao)))
            .andExpect(status().isBadRequest());

        // Validate the TipoContratacao in the database
        List<TipoContratacao> tipoContratacaoList = tipoContratacaoRepository.findAll();
        assertThat(tipoContratacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoContratacao() throws Exception {
        // Initialize the database
        tipoContratacaoService.save(tipoContratacao);

        int databaseSizeBeforeDelete = tipoContratacaoRepository.findAll().size();

        // Get the tipoContratacao
        restTipoContratacaoMockMvc.perform(delete("/api/tipo-contratacaos/{id}", tipoContratacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoContratacao> tipoContratacaoList = tipoContratacaoRepository.findAll();
        assertThat(tipoContratacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoContratacao.class);
        TipoContratacao tipoContratacao1 = new TipoContratacao();
        tipoContratacao1.setId(1L);
        TipoContratacao tipoContratacao2 = new TipoContratacao();
        tipoContratacao2.setId(tipoContratacao1.getId());
        assertThat(tipoContratacao1).isEqualTo(tipoContratacao2);
        tipoContratacao2.setId(2L);
        assertThat(tipoContratacao1).isNotEqualTo(tipoContratacao2);
        tipoContratacao1.setId(null);
        assertThat(tipoContratacao1).isNotEqualTo(tipoContratacao2);
    }
}
