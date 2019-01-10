package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ConceitoAcompanhamento;
import br.com.jhisolution.ong.control.repository.ConceitoAcompanhamentoRepository;
import br.com.jhisolution.ong.control.service.ConceitoAcompanhamentoService;
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
 * Test class for the ConceitoAcompanhamentoResource REST controller.
 *
 * @see ConceitoAcompanhamentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ConceitoAcompanhamentoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Float DEFAULT_NOTA = 1F;
    private static final Float UPDATED_NOTA = 2F;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ConceitoAcompanhamentoRepository conceitoAcompanhamentoRepository;

    @Autowired
    private ConceitoAcompanhamentoService conceitoAcompanhamentoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConceitoAcompanhamentoMockMvc;

    private ConceitoAcompanhamento conceitoAcompanhamento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConceitoAcompanhamentoResource conceitoAcompanhamentoResource = new ConceitoAcompanhamentoResource(conceitoAcompanhamentoService);
        this.restConceitoAcompanhamentoMockMvc = MockMvcBuilders.standaloneSetup(conceitoAcompanhamentoResource)
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
    public static ConceitoAcompanhamento createEntity(EntityManager em) {
        ConceitoAcompanhamento conceitoAcompanhamento = new ConceitoAcompanhamento()
            .nome(DEFAULT_NOME)
            .nota(DEFAULT_NOTA)
            .obs(DEFAULT_OBS);
        return conceitoAcompanhamento;
    }

    @Before
    public void initTest() {
        conceitoAcompanhamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createConceitoAcompanhamento() throws Exception {
        int databaseSizeBeforeCreate = conceitoAcompanhamentoRepository.findAll().size();

        // Create the ConceitoAcompanhamento
        restConceitoAcompanhamentoMockMvc.perform(post("/api/conceito-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceitoAcompanhamento)))
            .andExpect(status().isCreated());

        // Validate the ConceitoAcompanhamento in the database
        List<ConceitoAcompanhamento> conceitoAcompanhamentoList = conceitoAcompanhamentoRepository.findAll();
        assertThat(conceitoAcompanhamentoList).hasSize(databaseSizeBeforeCreate + 1);
        ConceitoAcompanhamento testConceitoAcompanhamento = conceitoAcompanhamentoList.get(conceitoAcompanhamentoList.size() - 1);
        assertThat(testConceitoAcompanhamento.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testConceitoAcompanhamento.getNota()).isEqualTo(DEFAULT_NOTA);
        assertThat(testConceitoAcompanhamento.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createConceitoAcompanhamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conceitoAcompanhamentoRepository.findAll().size();

        // Create the ConceitoAcompanhamento with an existing ID
        conceitoAcompanhamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConceitoAcompanhamentoMockMvc.perform(post("/api/conceito-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceitoAcompanhamento)))
            .andExpect(status().isBadRequest());

        // Validate the ConceitoAcompanhamento in the database
        List<ConceitoAcompanhamento> conceitoAcompanhamentoList = conceitoAcompanhamentoRepository.findAll();
        assertThat(conceitoAcompanhamentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = conceitoAcompanhamentoRepository.findAll().size();
        // set the field null
        conceitoAcompanhamento.setNome(null);

        // Create the ConceitoAcompanhamento, which fails.

        restConceitoAcompanhamentoMockMvc.perform(post("/api/conceito-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceitoAcompanhamento)))
            .andExpect(status().isBadRequest());

        List<ConceitoAcompanhamento> conceitoAcompanhamentoList = conceitoAcompanhamentoRepository.findAll();
        assertThat(conceitoAcompanhamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = conceitoAcompanhamentoRepository.findAll().size();
        // set the field null
        conceitoAcompanhamento.setNota(null);

        // Create the ConceitoAcompanhamento, which fails.

        restConceitoAcompanhamentoMockMvc.perform(post("/api/conceito-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceitoAcompanhamento)))
            .andExpect(status().isBadRequest());

        List<ConceitoAcompanhamento> conceitoAcompanhamentoList = conceitoAcompanhamentoRepository.findAll();
        assertThat(conceitoAcompanhamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConceitoAcompanhamentos() throws Exception {
        // Initialize the database
        conceitoAcompanhamentoRepository.saveAndFlush(conceitoAcompanhamento);

        // Get all the conceitoAcompanhamentoList
        restConceitoAcompanhamentoMockMvc.perform(get("/api/conceito-acompanhamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conceitoAcompanhamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA.doubleValue())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getConceitoAcompanhamento() throws Exception {
        // Initialize the database
        conceitoAcompanhamentoRepository.saveAndFlush(conceitoAcompanhamento);

        // Get the conceitoAcompanhamento
        restConceitoAcompanhamentoMockMvc.perform(get("/api/conceito-acompanhamentos/{id}", conceitoAcompanhamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conceitoAcompanhamento.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA.doubleValue()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConceitoAcompanhamento() throws Exception {
        // Get the conceitoAcompanhamento
        restConceitoAcompanhamentoMockMvc.perform(get("/api/conceito-acompanhamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConceitoAcompanhamento() throws Exception {
        // Initialize the database
        conceitoAcompanhamentoService.save(conceitoAcompanhamento);

        int databaseSizeBeforeUpdate = conceitoAcompanhamentoRepository.findAll().size();

        // Update the conceitoAcompanhamento
        ConceitoAcompanhamento updatedConceitoAcompanhamento = conceitoAcompanhamentoRepository.findById(conceitoAcompanhamento.getId()).get();
        // Disconnect from session so that the updates on updatedConceitoAcompanhamento are not directly saved in db
        em.detach(updatedConceitoAcompanhamento);
        updatedConceitoAcompanhamento
            .nome(UPDATED_NOME)
            .nota(UPDATED_NOTA)
            .obs(UPDATED_OBS);

        restConceitoAcompanhamentoMockMvc.perform(put("/api/conceito-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConceitoAcompanhamento)))
            .andExpect(status().isOk());

        // Validate the ConceitoAcompanhamento in the database
        List<ConceitoAcompanhamento> conceitoAcompanhamentoList = conceitoAcompanhamentoRepository.findAll();
        assertThat(conceitoAcompanhamentoList).hasSize(databaseSizeBeforeUpdate);
        ConceitoAcompanhamento testConceitoAcompanhamento = conceitoAcompanhamentoList.get(conceitoAcompanhamentoList.size() - 1);
        assertThat(testConceitoAcompanhamento.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testConceitoAcompanhamento.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testConceitoAcompanhamento.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingConceitoAcompanhamento() throws Exception {
        int databaseSizeBeforeUpdate = conceitoAcompanhamentoRepository.findAll().size();

        // Create the ConceitoAcompanhamento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConceitoAcompanhamentoMockMvc.perform(put("/api/conceito-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceitoAcompanhamento)))
            .andExpect(status().isBadRequest());

        // Validate the ConceitoAcompanhamento in the database
        List<ConceitoAcompanhamento> conceitoAcompanhamentoList = conceitoAcompanhamentoRepository.findAll();
        assertThat(conceitoAcompanhamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConceitoAcompanhamento() throws Exception {
        // Initialize the database
        conceitoAcompanhamentoService.save(conceitoAcompanhamento);

        int databaseSizeBeforeDelete = conceitoAcompanhamentoRepository.findAll().size();

        // Get the conceitoAcompanhamento
        restConceitoAcompanhamentoMockMvc.perform(delete("/api/conceito-acompanhamentos/{id}", conceitoAcompanhamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConceitoAcompanhamento> conceitoAcompanhamentoList = conceitoAcompanhamentoRepository.findAll();
        assertThat(conceitoAcompanhamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConceitoAcompanhamento.class);
        ConceitoAcompanhamento conceitoAcompanhamento1 = new ConceitoAcompanhamento();
        conceitoAcompanhamento1.setId(1L);
        ConceitoAcompanhamento conceitoAcompanhamento2 = new ConceitoAcompanhamento();
        conceitoAcompanhamento2.setId(conceitoAcompanhamento1.getId());
        assertThat(conceitoAcompanhamento1).isEqualTo(conceitoAcompanhamento2);
        conceitoAcompanhamento2.setId(2L);
        assertThat(conceitoAcompanhamento1).isNotEqualTo(conceitoAcompanhamento2);
        conceitoAcompanhamento1.setId(null);
        assertThat(conceitoAcompanhamento1).isNotEqualTo(conceitoAcompanhamento2);
    }
}
