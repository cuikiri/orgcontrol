package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.AcompanhamentoAluno;
import br.com.jhisolution.ong.control.repository.AcompanhamentoAlunoRepository;
import br.com.jhisolution.ong.control.service.AcompanhamentoAlunoService;
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
 * Test class for the AcompanhamentoAlunoResource REST controller.
 *
 * @see AcompanhamentoAlunoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AcompanhamentoAlunoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private AcompanhamentoAlunoRepository acompanhamentoAlunoRepository;

    @Autowired
    private AcompanhamentoAlunoService acompanhamentoAlunoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAcompanhamentoAlunoMockMvc;

    private AcompanhamentoAluno acompanhamentoAluno;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcompanhamentoAlunoResource acompanhamentoAlunoResource = new AcompanhamentoAlunoResource(acompanhamentoAlunoService);
        this.restAcompanhamentoAlunoMockMvc = MockMvcBuilders.standaloneSetup(acompanhamentoAlunoResource)
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
    public static AcompanhamentoAluno createEntity(EntityManager em) {
        AcompanhamentoAluno acompanhamentoAluno = new AcompanhamentoAluno()
            .nome(DEFAULT_NOME)
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS);
        return acompanhamentoAluno;
    }

    @Before
    public void initTest() {
        acompanhamentoAluno = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcompanhamentoAluno() throws Exception {
        int databaseSizeBeforeCreate = acompanhamentoAlunoRepository.findAll().size();

        // Create the AcompanhamentoAluno
        restAcompanhamentoAlunoMockMvc.perform(post("/api/acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAluno)))
            .andExpect(status().isCreated());

        // Validate the AcompanhamentoAluno in the database
        List<AcompanhamentoAluno> acompanhamentoAlunoList = acompanhamentoAlunoRepository.findAll();
        assertThat(acompanhamentoAlunoList).hasSize(databaseSizeBeforeCreate + 1);
        AcompanhamentoAluno testAcompanhamentoAluno = acompanhamentoAlunoList.get(acompanhamentoAlunoList.size() - 1);
        assertThat(testAcompanhamentoAluno.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAcompanhamentoAluno.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testAcompanhamentoAluno.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createAcompanhamentoAlunoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = acompanhamentoAlunoRepository.findAll().size();

        // Create the AcompanhamentoAluno with an existing ID
        acompanhamentoAluno.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcompanhamentoAlunoMockMvc.perform(post("/api/acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        // Validate the AcompanhamentoAluno in the database
        List<AcompanhamentoAluno> acompanhamentoAlunoList = acompanhamentoAlunoRepository.findAll();
        assertThat(acompanhamentoAlunoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompanhamentoAlunoRepository.findAll().size();
        // set the field null
        acompanhamentoAluno.setNome(null);

        // Create the AcompanhamentoAluno, which fails.

        restAcompanhamentoAlunoMockMvc.perform(post("/api/acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        List<AcompanhamentoAluno> acompanhamentoAlunoList = acompanhamentoAlunoRepository.findAll();
        assertThat(acompanhamentoAlunoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompanhamentoAlunoRepository.findAll().size();
        // set the field null
        acompanhamentoAluno.setNumero(null);

        // Create the AcompanhamentoAluno, which fails.

        restAcompanhamentoAlunoMockMvc.perform(post("/api/acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        List<AcompanhamentoAluno> acompanhamentoAlunoList = acompanhamentoAlunoRepository.findAll();
        assertThat(acompanhamentoAlunoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcompanhamentoAlunos() throws Exception {
        // Initialize the database
        acompanhamentoAlunoRepository.saveAndFlush(acompanhamentoAluno);

        // Get all the acompanhamentoAlunoList
        restAcompanhamentoAlunoMockMvc.perform(get("/api/acompanhamento-alunos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acompanhamentoAluno.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getAcompanhamentoAluno() throws Exception {
        // Initialize the database
        acompanhamentoAlunoRepository.saveAndFlush(acompanhamentoAluno);

        // Get the acompanhamentoAluno
        restAcompanhamentoAlunoMockMvc.perform(get("/api/acompanhamento-alunos/{id}", acompanhamentoAluno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(acompanhamentoAluno.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAcompanhamentoAluno() throws Exception {
        // Get the acompanhamentoAluno
        restAcompanhamentoAlunoMockMvc.perform(get("/api/acompanhamento-alunos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcompanhamentoAluno() throws Exception {
        // Initialize the database
        acompanhamentoAlunoService.save(acompanhamentoAluno);

        int databaseSizeBeforeUpdate = acompanhamentoAlunoRepository.findAll().size();

        // Update the acompanhamentoAluno
        AcompanhamentoAluno updatedAcompanhamentoAluno = acompanhamentoAlunoRepository.findById(acompanhamentoAluno.getId()).get();
        // Disconnect from session so that the updates on updatedAcompanhamentoAluno are not directly saved in db
        em.detach(updatedAcompanhamentoAluno);
        updatedAcompanhamentoAluno
            .nome(UPDATED_NOME)
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS);

        restAcompanhamentoAlunoMockMvc.perform(put("/api/acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAcompanhamentoAluno)))
            .andExpect(status().isOk());

        // Validate the AcompanhamentoAluno in the database
        List<AcompanhamentoAluno> acompanhamentoAlunoList = acompanhamentoAlunoRepository.findAll();
        assertThat(acompanhamentoAlunoList).hasSize(databaseSizeBeforeUpdate);
        AcompanhamentoAluno testAcompanhamentoAluno = acompanhamentoAlunoList.get(acompanhamentoAlunoList.size() - 1);
        assertThat(testAcompanhamentoAluno.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAcompanhamentoAluno.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testAcompanhamentoAluno.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingAcompanhamentoAluno() throws Exception {
        int databaseSizeBeforeUpdate = acompanhamentoAlunoRepository.findAll().size();

        // Create the AcompanhamentoAluno

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcompanhamentoAlunoMockMvc.perform(put("/api/acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        // Validate the AcompanhamentoAluno in the database
        List<AcompanhamentoAluno> acompanhamentoAlunoList = acompanhamentoAlunoRepository.findAll();
        assertThat(acompanhamentoAlunoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAcompanhamentoAluno() throws Exception {
        // Initialize the database
        acompanhamentoAlunoService.save(acompanhamentoAluno);

        int databaseSizeBeforeDelete = acompanhamentoAlunoRepository.findAll().size();

        // Get the acompanhamentoAluno
        restAcompanhamentoAlunoMockMvc.perform(delete("/api/acompanhamento-alunos/{id}", acompanhamentoAluno.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcompanhamentoAluno> acompanhamentoAlunoList = acompanhamentoAlunoRepository.findAll();
        assertThat(acompanhamentoAlunoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcompanhamentoAluno.class);
        AcompanhamentoAluno acompanhamentoAluno1 = new AcompanhamentoAluno();
        acompanhamentoAluno1.setId(1L);
        AcompanhamentoAluno acompanhamentoAluno2 = new AcompanhamentoAluno();
        acompanhamentoAluno2.setId(acompanhamentoAluno1.getId());
        assertThat(acompanhamentoAluno1).isEqualTo(acompanhamentoAluno2);
        acompanhamentoAluno2.setId(2L);
        assertThat(acompanhamentoAluno1).isNotEqualTo(acompanhamentoAluno2);
        acompanhamentoAluno1.setId(null);
        assertThat(acompanhamentoAluno1).isNotEqualTo(acompanhamentoAluno2);
    }
}
