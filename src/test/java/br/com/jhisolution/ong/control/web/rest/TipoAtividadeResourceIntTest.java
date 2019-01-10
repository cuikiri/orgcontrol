package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoAtividade;
import br.com.jhisolution.ong.control.repository.TipoAtividadeRepository;
import br.com.jhisolution.ong.control.service.TipoAtividadeService;
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
 * Test class for the TipoAtividadeResource REST controller.
 *
 * @see TipoAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoAtividadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private TipoAtividadeRepository tipoAtividadeRepository;

    @Autowired
    private TipoAtividadeService tipoAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoAtividadeMockMvc;

    private TipoAtividade tipoAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoAtividadeResource tipoAtividadeResource = new TipoAtividadeResource(tipoAtividadeService);
        this.restTipoAtividadeMockMvc = MockMvcBuilders.standaloneSetup(tipoAtividadeResource)
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
    public static TipoAtividade createEntity(EntityManager em) {
        TipoAtividade tipoAtividade = new TipoAtividade()
            .nome(DEFAULT_NOME);
        return tipoAtividade;
    }

    @Before
    public void initTest() {
        tipoAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoAtividade() throws Exception {
        int databaseSizeBeforeCreate = tipoAtividadeRepository.findAll().size();

        // Create the TipoAtividade
        restTipoAtividadeMockMvc.perform(post("/api/tipo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAtividade)))
            .andExpect(status().isCreated());

        // Validate the TipoAtividade in the database
        List<TipoAtividade> tipoAtividadeList = tipoAtividadeRepository.findAll();
        assertThat(tipoAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        TipoAtividade testTipoAtividade = tipoAtividadeList.get(tipoAtividadeList.size() - 1);
        assertThat(testTipoAtividade.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createTipoAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoAtividadeRepository.findAll().size();

        // Create the TipoAtividade with an existing ID
        tipoAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoAtividadeMockMvc.perform(post("/api/tipo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAtividade in the database
        List<TipoAtividade> tipoAtividadeList = tipoAtividadeRepository.findAll();
        assertThat(tipoAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAtividadeRepository.findAll().size();
        // set the field null
        tipoAtividade.setNome(null);

        // Create the TipoAtividade, which fails.

        restTipoAtividadeMockMvc.perform(post("/api/tipo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAtividade)))
            .andExpect(status().isBadRequest());

        List<TipoAtividade> tipoAtividadeList = tipoAtividadeRepository.findAll();
        assertThat(tipoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoAtividades() throws Exception {
        // Initialize the database
        tipoAtividadeRepository.saveAndFlush(tipoAtividade);

        // Get all the tipoAtividadeList
        restTipoAtividadeMockMvc.perform(get("/api/tipo-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoAtividade() throws Exception {
        // Initialize the database
        tipoAtividadeRepository.saveAndFlush(tipoAtividade);

        // Get the tipoAtividade
        restTipoAtividadeMockMvc.perform(get("/api/tipo-atividades/{id}", tipoAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoAtividade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoAtividade() throws Exception {
        // Get the tipoAtividade
        restTipoAtividadeMockMvc.perform(get("/api/tipo-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoAtividade() throws Exception {
        // Initialize the database
        tipoAtividadeService.save(tipoAtividade);

        int databaseSizeBeforeUpdate = tipoAtividadeRepository.findAll().size();

        // Update the tipoAtividade
        TipoAtividade updatedTipoAtividade = tipoAtividadeRepository.findById(tipoAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedTipoAtividade are not directly saved in db
        em.detach(updatedTipoAtividade);
        updatedTipoAtividade
            .nome(UPDATED_NOME);

        restTipoAtividadeMockMvc.perform(put("/api/tipo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoAtividade)))
            .andExpect(status().isOk());

        // Validate the TipoAtividade in the database
        List<TipoAtividade> tipoAtividadeList = tipoAtividadeRepository.findAll();
        assertThat(tipoAtividadeList).hasSize(databaseSizeBeforeUpdate);
        TipoAtividade testTipoAtividade = tipoAtividadeList.get(tipoAtividadeList.size() - 1);
        assertThat(testTipoAtividade.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoAtividade() throws Exception {
        int databaseSizeBeforeUpdate = tipoAtividadeRepository.findAll().size();

        // Create the TipoAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoAtividadeMockMvc.perform(put("/api/tipo-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAtividade in the database
        List<TipoAtividade> tipoAtividadeList = tipoAtividadeRepository.findAll();
        assertThat(tipoAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoAtividade() throws Exception {
        // Initialize the database
        tipoAtividadeService.save(tipoAtividade);

        int databaseSizeBeforeDelete = tipoAtividadeRepository.findAll().size();

        // Get the tipoAtividade
        restTipoAtividadeMockMvc.perform(delete("/api/tipo-atividades/{id}", tipoAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoAtividade> tipoAtividadeList = tipoAtividadeRepository.findAll();
        assertThat(tipoAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAtividade.class);
        TipoAtividade tipoAtividade1 = new TipoAtividade();
        tipoAtividade1.setId(1L);
        TipoAtividade tipoAtividade2 = new TipoAtividade();
        tipoAtividade2.setId(tipoAtividade1.getId());
        assertThat(tipoAtividade1).isEqualTo(tipoAtividade2);
        tipoAtividade2.setId(2L);
        assertThat(tipoAtividade1).isNotEqualTo(tipoAtividade2);
        tipoAtividade1.setId(null);
        assertThat(tipoAtividade1).isNotEqualTo(tipoAtividade2);
    }
}
