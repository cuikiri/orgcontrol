package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAluno;
import br.com.jhisolution.ong.control.repository.TipoAcompanhamentoAlunoRepository;
import br.com.jhisolution.ong.control.service.TipoAcompanhamentoAlunoService;
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
 * Test class for the TipoAcompanhamentoAlunoResource REST controller.
 *
 * @see TipoAcompanhamentoAlunoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoAcompanhamentoAlunoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private TipoAcompanhamentoAlunoRepository tipoAcompanhamentoAlunoRepository;

    @Autowired
    private TipoAcompanhamentoAlunoService tipoAcompanhamentoAlunoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoAcompanhamentoAlunoMockMvc;

    private TipoAcompanhamentoAluno tipoAcompanhamentoAluno;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoAcompanhamentoAlunoResource tipoAcompanhamentoAlunoResource = new TipoAcompanhamentoAlunoResource(tipoAcompanhamentoAlunoService);
        this.restTipoAcompanhamentoAlunoMockMvc = MockMvcBuilders.standaloneSetup(tipoAcompanhamentoAlunoResource)
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
    public static TipoAcompanhamentoAluno createEntity(EntityManager em) {
        TipoAcompanhamentoAluno tipoAcompanhamentoAluno = new TipoAcompanhamentoAluno()
            .nome(DEFAULT_NOME);
        return tipoAcompanhamentoAluno;
    }

    @Before
    public void initTest() {
        tipoAcompanhamentoAluno = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoAcompanhamentoAluno() throws Exception {
        int databaseSizeBeforeCreate = tipoAcompanhamentoAlunoRepository.findAll().size();

        // Create the TipoAcompanhamentoAluno
        restTipoAcompanhamentoAlunoMockMvc.perform(post("/api/tipo-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAluno)))
            .andExpect(status().isCreated());

        // Validate the TipoAcompanhamentoAluno in the database
        List<TipoAcompanhamentoAluno> tipoAcompanhamentoAlunoList = tipoAcompanhamentoAlunoRepository.findAll();
        assertThat(tipoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoAcompanhamentoAluno testTipoAcompanhamentoAluno = tipoAcompanhamentoAlunoList.get(tipoAcompanhamentoAlunoList.size() - 1);
        assertThat(testTipoAcompanhamentoAluno.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createTipoAcompanhamentoAlunoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoAcompanhamentoAlunoRepository.findAll().size();

        // Create the TipoAcompanhamentoAluno with an existing ID
        tipoAcompanhamentoAluno.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoAcompanhamentoAlunoMockMvc.perform(post("/api/tipo-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAcompanhamentoAluno in the database
        List<TipoAcompanhamentoAluno> tipoAcompanhamentoAlunoList = tipoAcompanhamentoAlunoRepository.findAll();
        assertThat(tipoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAcompanhamentoAlunoRepository.findAll().size();
        // set the field null
        tipoAcompanhamentoAluno.setNome(null);

        // Create the TipoAcompanhamentoAluno, which fails.

        restTipoAcompanhamentoAlunoMockMvc.perform(post("/api/tipo-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        List<TipoAcompanhamentoAluno> tipoAcompanhamentoAlunoList = tipoAcompanhamentoAlunoRepository.findAll();
        assertThat(tipoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoAcompanhamentoAlunos() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAlunoRepository.saveAndFlush(tipoAcompanhamentoAluno);

        // Get all the tipoAcompanhamentoAlunoList
        restTipoAcompanhamentoAlunoMockMvc.perform(get("/api/tipo-acompanhamento-alunos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAcompanhamentoAluno.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoAcompanhamentoAluno() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAlunoRepository.saveAndFlush(tipoAcompanhamentoAluno);

        // Get the tipoAcompanhamentoAluno
        restTipoAcompanhamentoAlunoMockMvc.perform(get("/api/tipo-acompanhamento-alunos/{id}", tipoAcompanhamentoAluno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoAcompanhamentoAluno.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoAcompanhamentoAluno() throws Exception {
        // Get the tipoAcompanhamentoAluno
        restTipoAcompanhamentoAlunoMockMvc.perform(get("/api/tipo-acompanhamento-alunos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoAcompanhamentoAluno() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAlunoService.save(tipoAcompanhamentoAluno);

        int databaseSizeBeforeUpdate = tipoAcompanhamentoAlunoRepository.findAll().size();

        // Update the tipoAcompanhamentoAluno
        TipoAcompanhamentoAluno updatedTipoAcompanhamentoAluno = tipoAcompanhamentoAlunoRepository.findById(tipoAcompanhamentoAluno.getId()).get();
        // Disconnect from session so that the updates on updatedTipoAcompanhamentoAluno are not directly saved in db
        em.detach(updatedTipoAcompanhamentoAluno);
        updatedTipoAcompanhamentoAluno
            .nome(UPDATED_NOME);

        restTipoAcompanhamentoAlunoMockMvc.perform(put("/api/tipo-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoAcompanhamentoAluno)))
            .andExpect(status().isOk());

        // Validate the TipoAcompanhamentoAluno in the database
        List<TipoAcompanhamentoAluno> tipoAcompanhamentoAlunoList = tipoAcompanhamentoAlunoRepository.findAll();
        assertThat(tipoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeUpdate);
        TipoAcompanhamentoAluno testTipoAcompanhamentoAluno = tipoAcompanhamentoAlunoList.get(tipoAcompanhamentoAlunoList.size() - 1);
        assertThat(testTipoAcompanhamentoAluno.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoAcompanhamentoAluno() throws Exception {
        int databaseSizeBeforeUpdate = tipoAcompanhamentoAlunoRepository.findAll().size();

        // Create the TipoAcompanhamentoAluno

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoAcompanhamentoAlunoMockMvc.perform(put("/api/tipo-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAcompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAcompanhamentoAluno in the database
        List<TipoAcompanhamentoAluno> tipoAcompanhamentoAlunoList = tipoAcompanhamentoAlunoRepository.findAll();
        assertThat(tipoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoAcompanhamentoAluno() throws Exception {
        // Initialize the database
        tipoAcompanhamentoAlunoService.save(tipoAcompanhamentoAluno);

        int databaseSizeBeforeDelete = tipoAcompanhamentoAlunoRepository.findAll().size();

        // Get the tipoAcompanhamentoAluno
        restTipoAcompanhamentoAlunoMockMvc.perform(delete("/api/tipo-acompanhamento-alunos/{id}", tipoAcompanhamentoAluno.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoAcompanhamentoAluno> tipoAcompanhamentoAlunoList = tipoAcompanhamentoAlunoRepository.findAll();
        assertThat(tipoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAcompanhamentoAluno.class);
        TipoAcompanhamentoAluno tipoAcompanhamentoAluno1 = new TipoAcompanhamentoAluno();
        tipoAcompanhamentoAluno1.setId(1L);
        TipoAcompanhamentoAluno tipoAcompanhamentoAluno2 = new TipoAcompanhamentoAluno();
        tipoAcompanhamentoAluno2.setId(tipoAcompanhamentoAluno1.getId());
        assertThat(tipoAcompanhamentoAluno1).isEqualTo(tipoAcompanhamentoAluno2);
        tipoAcompanhamentoAluno2.setId(2L);
        assertThat(tipoAcompanhamentoAluno1).isNotEqualTo(tipoAcompanhamentoAluno2);
        tipoAcompanhamentoAluno1.setId(null);
        assertThat(tipoAcompanhamentoAluno1).isNotEqualTo(tipoAcompanhamentoAluno2);
    }
}
