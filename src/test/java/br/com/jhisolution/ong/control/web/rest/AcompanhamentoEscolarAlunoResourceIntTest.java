package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.AcompanhamentoEscolarAluno;
import br.com.jhisolution.ong.control.repository.AcompanhamentoEscolarAlunoRepository;
import br.com.jhisolution.ong.control.service.AcompanhamentoEscolarAlunoService;
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
 * Test class for the AcompanhamentoEscolarAlunoResource REST controller.
 *
 * @see AcompanhamentoEscolarAlunoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AcompanhamentoEscolarAlunoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private AcompanhamentoEscolarAlunoRepository acompanhamentoEscolarAlunoRepository;

    @Autowired
    private AcompanhamentoEscolarAlunoService acompanhamentoEscolarAlunoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAcompanhamentoEscolarAlunoMockMvc;

    private AcompanhamentoEscolarAluno acompanhamentoEscolarAluno;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcompanhamentoEscolarAlunoResource acompanhamentoEscolarAlunoResource = new AcompanhamentoEscolarAlunoResource(acompanhamentoEscolarAlunoService);
        this.restAcompanhamentoEscolarAlunoMockMvc = MockMvcBuilders.standaloneSetup(acompanhamentoEscolarAlunoResource)
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
    public static AcompanhamentoEscolarAluno createEntity(EntityManager em) {
        AcompanhamentoEscolarAluno acompanhamentoEscolarAluno = new AcompanhamentoEscolarAluno()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return acompanhamentoEscolarAluno;
    }

    @Before
    public void initTest() {
        acompanhamentoEscolarAluno = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcompanhamentoEscolarAluno() throws Exception {
        int databaseSizeBeforeCreate = acompanhamentoEscolarAlunoRepository.findAll().size();

        // Create the AcompanhamentoEscolarAluno
        restAcompanhamentoEscolarAlunoMockMvc.perform(post("/api/acompanhamento-escolar-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoEscolarAluno)))
            .andExpect(status().isCreated());

        // Validate the AcompanhamentoEscolarAluno in the database
        List<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunoList = acompanhamentoEscolarAlunoRepository.findAll();
        assertThat(acompanhamentoEscolarAlunoList).hasSize(databaseSizeBeforeCreate + 1);
        AcompanhamentoEscolarAluno testAcompanhamentoEscolarAluno = acompanhamentoEscolarAlunoList.get(acompanhamentoEscolarAlunoList.size() - 1);
        assertThat(testAcompanhamentoEscolarAluno.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAcompanhamentoEscolarAluno.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createAcompanhamentoEscolarAlunoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = acompanhamentoEscolarAlunoRepository.findAll().size();

        // Create the AcompanhamentoEscolarAluno with an existing ID
        acompanhamentoEscolarAluno.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcompanhamentoEscolarAlunoMockMvc.perform(post("/api/acompanhamento-escolar-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoEscolarAluno)))
            .andExpect(status().isBadRequest());

        // Validate the AcompanhamentoEscolarAluno in the database
        List<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunoList = acompanhamentoEscolarAlunoRepository.findAll();
        assertThat(acompanhamentoEscolarAlunoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompanhamentoEscolarAlunoRepository.findAll().size();
        // set the field null
        acompanhamentoEscolarAluno.setNome(null);

        // Create the AcompanhamentoEscolarAluno, which fails.

        restAcompanhamentoEscolarAlunoMockMvc.perform(post("/api/acompanhamento-escolar-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoEscolarAluno)))
            .andExpect(status().isBadRequest());

        List<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunoList = acompanhamentoEscolarAlunoRepository.findAll();
        assertThat(acompanhamentoEscolarAlunoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcompanhamentoEscolarAlunos() throws Exception {
        // Initialize the database
        acompanhamentoEscolarAlunoRepository.saveAndFlush(acompanhamentoEscolarAluno);

        // Get all the acompanhamentoEscolarAlunoList
        restAcompanhamentoEscolarAlunoMockMvc.perform(get("/api/acompanhamento-escolar-alunos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acompanhamentoEscolarAluno.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getAcompanhamentoEscolarAluno() throws Exception {
        // Initialize the database
        acompanhamentoEscolarAlunoRepository.saveAndFlush(acompanhamentoEscolarAluno);

        // Get the acompanhamentoEscolarAluno
        restAcompanhamentoEscolarAlunoMockMvc.perform(get("/api/acompanhamento-escolar-alunos/{id}", acompanhamentoEscolarAluno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(acompanhamentoEscolarAluno.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAcompanhamentoEscolarAluno() throws Exception {
        // Get the acompanhamentoEscolarAluno
        restAcompanhamentoEscolarAlunoMockMvc.perform(get("/api/acompanhamento-escolar-alunos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcompanhamentoEscolarAluno() throws Exception {
        // Initialize the database
        acompanhamentoEscolarAlunoService.save(acompanhamentoEscolarAluno);

        int databaseSizeBeforeUpdate = acompanhamentoEscolarAlunoRepository.findAll().size();

        // Update the acompanhamentoEscolarAluno
        AcompanhamentoEscolarAluno updatedAcompanhamentoEscolarAluno = acompanhamentoEscolarAlunoRepository.findById(acompanhamentoEscolarAluno.getId()).get();
        // Disconnect from session so that the updates on updatedAcompanhamentoEscolarAluno are not directly saved in db
        em.detach(updatedAcompanhamentoEscolarAluno);
        updatedAcompanhamentoEscolarAluno
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restAcompanhamentoEscolarAlunoMockMvc.perform(put("/api/acompanhamento-escolar-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAcompanhamentoEscolarAluno)))
            .andExpect(status().isOk());

        // Validate the AcompanhamentoEscolarAluno in the database
        List<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunoList = acompanhamentoEscolarAlunoRepository.findAll();
        assertThat(acompanhamentoEscolarAlunoList).hasSize(databaseSizeBeforeUpdate);
        AcompanhamentoEscolarAluno testAcompanhamentoEscolarAluno = acompanhamentoEscolarAlunoList.get(acompanhamentoEscolarAlunoList.size() - 1);
        assertThat(testAcompanhamentoEscolarAluno.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAcompanhamentoEscolarAluno.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingAcompanhamentoEscolarAluno() throws Exception {
        int databaseSizeBeforeUpdate = acompanhamentoEscolarAlunoRepository.findAll().size();

        // Create the AcompanhamentoEscolarAluno

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcompanhamentoEscolarAlunoMockMvc.perform(put("/api/acompanhamento-escolar-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoEscolarAluno)))
            .andExpect(status().isBadRequest());

        // Validate the AcompanhamentoEscolarAluno in the database
        List<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunoList = acompanhamentoEscolarAlunoRepository.findAll();
        assertThat(acompanhamentoEscolarAlunoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAcompanhamentoEscolarAluno() throws Exception {
        // Initialize the database
        acompanhamentoEscolarAlunoService.save(acompanhamentoEscolarAluno);

        int databaseSizeBeforeDelete = acompanhamentoEscolarAlunoRepository.findAll().size();

        // Get the acompanhamentoEscolarAluno
        restAcompanhamentoEscolarAlunoMockMvc.perform(delete("/api/acompanhamento-escolar-alunos/{id}", acompanhamentoEscolarAluno.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunoList = acompanhamentoEscolarAlunoRepository.findAll();
        assertThat(acompanhamentoEscolarAlunoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcompanhamentoEscolarAluno.class);
        AcompanhamentoEscolarAluno acompanhamentoEscolarAluno1 = new AcompanhamentoEscolarAluno();
        acompanhamentoEscolarAluno1.setId(1L);
        AcompanhamentoEscolarAluno acompanhamentoEscolarAluno2 = new AcompanhamentoEscolarAluno();
        acompanhamentoEscolarAluno2.setId(acompanhamentoEscolarAluno1.getId());
        assertThat(acompanhamentoEscolarAluno1).isEqualTo(acompanhamentoEscolarAluno2);
        acompanhamentoEscolarAluno2.setId(2L);
        assertThat(acompanhamentoEscolarAluno1).isNotEqualTo(acompanhamentoEscolarAluno2);
        acompanhamentoEscolarAluno1.setId(null);
        assertThat(acompanhamentoEscolarAluno1).isNotEqualTo(acompanhamentoEscolarAluno2);
    }
}
