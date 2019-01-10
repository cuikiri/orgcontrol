package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAtividade;
import br.com.jhisolution.ong.control.repository.TipoAcompanhamentoAtividadeRepository;
import br.com.jhisolution.ong.control.service.TipoAcompanhamentoAtividadeService;
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
 * Test class for the TipoAcompanhamentoAtividadeResource REST controller.
 *
 * @see TipoAcompanhamentoAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoAcompanhamentoAtividadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoAcompanhamentoAtividadeRepository tipoAcompanhamentoAtividadeRepository;

    @Autowired
    private TipoAcompanhamentoAtividadeService tipoAcompanhamentoAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoAcompanhamentoAtividadeMockMvc;

    private TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoAcompanhamentoAtividadeResource tipoAcompanhamentoAtividadeResource = new TipoAcompanhamentoAtividadeResource(tipoAcompanhamentoAtividadeService);
        this.restTipoAcompanhamentoAtividadeMockMvc = MockMvcBuilders.standaloneSetup(tipoAcompanhamentoAtividadeResource)
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
    public static TipoAcompanhamentoAtividade createEntity(EntityManager em) {
        TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade = new TipoAcompanhamentoAtividade()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoAcompanhamentoAtividade;
    }

    @Before
    public void initTest() {
        tipoAcompanhamentoAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoAcompanhamentoAtividade() throws Exception {
        int databaseSizeBeforeCreate = tipoAcompanhamentoAtividadeRepository.findAll().size();

        // Create the TipoAcompanhamentoAtividade
        restTipoAcompanhamentoAtividadeMockMvc.perform(post("/api/tipo-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAtividade)))
            .andExpect(status().isCreated());

        // Validate the TipoAcompanhamentoAtividade in the database
        List<TipoAcompanhamentoAtividade> tipoAcompanhamentoAtividadeList = tipoAcompanhamentoAtividadeRepository.findAll();
        assertThat(tipoAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        TipoAcompanhamentoAtividade testTipoAcompanhamentoAtividade = tipoAcompanhamentoAtividadeList.get(tipoAcompanhamentoAtividadeList.size() - 1);
        assertThat(testTipoAcompanhamentoAtividade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoAcompanhamentoAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoAcompanhamentoAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoAcompanhamentoAtividadeRepository.findAll().size();

        // Create the TipoAcompanhamentoAtividade with an existing ID
        tipoAcompanhamentoAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoAcompanhamentoAtividadeMockMvc.perform(post("/api/tipo-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAcompanhamentoAtividade in the database
        List<TipoAcompanhamentoAtividade> tipoAcompanhamentoAtividadeList = tipoAcompanhamentoAtividadeRepository.findAll();
        assertThat(tipoAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAcompanhamentoAtividadeRepository.findAll().size();
        // set the field null
        tipoAcompanhamentoAtividade.setNome(null);

        // Create the TipoAcompanhamentoAtividade, which fails.

        restTipoAcompanhamentoAtividadeMockMvc.perform(post("/api/tipo-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<TipoAcompanhamentoAtividade> tipoAcompanhamentoAtividadeList = tipoAcompanhamentoAtividadeRepository.findAll();
        assertThat(tipoAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoAcompanhamentoAtividades() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAtividadeRepository.saveAndFlush(tipoAcompanhamentoAtividade);

        // Get all the tipoAcompanhamentoAtividadeList
        restTipoAcompanhamentoAtividadeMockMvc.perform(get("/api/tipo-acompanhamento-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAcompanhamentoAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAtividadeRepository.saveAndFlush(tipoAcompanhamentoAtividade);

        // Get the tipoAcompanhamentoAtividade
        restTipoAcompanhamentoAtividadeMockMvc.perform(get("/api/tipo-acompanhamento-atividades/{id}", tipoAcompanhamentoAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoAcompanhamentoAtividade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoAcompanhamentoAtividade() throws Exception {
        // Get the tipoAcompanhamentoAtividade
        restTipoAcompanhamentoAtividadeMockMvc.perform(get("/api/tipo-acompanhamento-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAtividadeService.save(tipoAcompanhamentoAtividade);

        int databaseSizeBeforeUpdate = tipoAcompanhamentoAtividadeRepository.findAll().size();

        // Update the tipoAcompanhamentoAtividade
        TipoAcompanhamentoAtividade updatedTipoAcompanhamentoAtividade = tipoAcompanhamentoAtividadeRepository.findById(tipoAcompanhamentoAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedTipoAcompanhamentoAtividade are not directly saved in db
        em.detach(updatedTipoAcompanhamentoAtividade);
        updatedTipoAcompanhamentoAtividade
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoAcompanhamentoAtividadeMockMvc.perform(put("/api/tipo-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoAcompanhamentoAtividade)))
            .andExpect(status().isOk());

        // Validate the TipoAcompanhamentoAtividade in the database
        List<TipoAcompanhamentoAtividade> tipoAcompanhamentoAtividadeList = tipoAcompanhamentoAtividadeRepository.findAll();
        assertThat(tipoAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
        TipoAcompanhamentoAtividade testTipoAcompanhamentoAtividade = tipoAcompanhamentoAtividadeList.get(tipoAcompanhamentoAtividadeList.size() - 1);
        assertThat(testTipoAcompanhamentoAtividade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoAcompanhamentoAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoAcompanhamentoAtividade() throws Exception {
        int databaseSizeBeforeUpdate = tipoAcompanhamentoAtividadeRepository.findAll().size();

        // Create the TipoAcompanhamentoAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoAcompanhamentoAtividadeMockMvc.perform(put("/api/tipo-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAcompanhamentoAtividade in the database
        List<TipoAcompanhamentoAtividade> tipoAcompanhamentoAtividadeList = tipoAcompanhamentoAtividadeRepository.findAll();
        assertThat(tipoAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAtividadeService.save(tipoAcompanhamentoAtividade);

        int databaseSizeBeforeDelete = tipoAcompanhamentoAtividadeRepository.findAll().size();

        // Get the tipoAcompanhamentoAtividade
        restTipoAcompanhamentoAtividadeMockMvc.perform(delete("/api/tipo-acompanhamento-atividades/{id}", tipoAcompanhamentoAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoAcompanhamentoAtividade> tipoAcompanhamentoAtividadeList = tipoAcompanhamentoAtividadeRepository.findAll();
        assertThat(tipoAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAcompanhamentoAtividade.class);
        TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade1 = new TipoAcompanhamentoAtividade();
        tipoAcompanhamentoAtividade1.setId(1L);
        TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade2 = new TipoAcompanhamentoAtividade();
        tipoAcompanhamentoAtividade2.setId(tipoAcompanhamentoAtividade1.getId());
        assertThat(tipoAcompanhamentoAtividade1).isEqualTo(tipoAcompanhamentoAtividade2);
        tipoAcompanhamentoAtividade2.setId(2L);
        assertThat(tipoAcompanhamentoAtividade1).isNotEqualTo(tipoAcompanhamentoAtividade2);
        tipoAcompanhamentoAtividade1.setId(null);
        assertThat(tipoAcompanhamentoAtividade1).isNotEqualTo(tipoAcompanhamentoAtividade2);
    }
}
