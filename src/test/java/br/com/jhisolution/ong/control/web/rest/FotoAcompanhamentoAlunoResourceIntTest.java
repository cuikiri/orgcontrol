package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.FotoAcompanhamentoAluno;
import br.com.jhisolution.ong.control.repository.FotoAcompanhamentoAlunoRepository;
import br.com.jhisolution.ong.control.service.FotoAcompanhamentoAlunoService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FotoAcompanhamentoAlunoResource REST controller.
 *
 * @see FotoAcompanhamentoAlunoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class FotoAcompanhamentoAlunoResourceIntTest {

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private FotoAcompanhamentoAlunoRepository fotoAcompanhamentoAlunoRepository;

    @Autowired
    private FotoAcompanhamentoAlunoService fotoAcompanhamentoAlunoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFotoAcompanhamentoAlunoMockMvc;

    private FotoAcompanhamentoAluno fotoAcompanhamentoAluno;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FotoAcompanhamentoAlunoResource fotoAcompanhamentoAlunoResource = new FotoAcompanhamentoAlunoResource(fotoAcompanhamentoAlunoService);
        this.restFotoAcompanhamentoAlunoMockMvc = MockMvcBuilders.standaloneSetup(fotoAcompanhamentoAlunoResource)
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
    public static FotoAcompanhamentoAluno createEntity(EntityManager em) {
        FotoAcompanhamentoAluno fotoAcompanhamentoAluno = new FotoAcompanhamentoAluno()
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE);
        return fotoAcompanhamentoAluno;
    }

    @Before
    public void initTest() {
        fotoAcompanhamentoAluno = createEntity(em);
    }

    @Test
    @Transactional
    public void createFotoAcompanhamentoAluno() throws Exception {
        int databaseSizeBeforeCreate = fotoAcompanhamentoAlunoRepository.findAll().size();

        // Create the FotoAcompanhamentoAluno
        restFotoAcompanhamentoAlunoMockMvc.perform(post("/api/foto-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoAcompanhamentoAluno)))
            .andExpect(status().isCreated());

        // Validate the FotoAcompanhamentoAluno in the database
        List<FotoAcompanhamentoAluno> fotoAcompanhamentoAlunoList = fotoAcompanhamentoAlunoRepository.findAll();
        assertThat(fotoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeCreate + 1);
        FotoAcompanhamentoAluno testFotoAcompanhamentoAluno = fotoAcompanhamentoAlunoList.get(fotoAcompanhamentoAlunoList.size() - 1);
        assertThat(testFotoAcompanhamentoAluno.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testFotoAcompanhamentoAluno.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFotoAcompanhamentoAlunoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fotoAcompanhamentoAlunoRepository.findAll().size();

        // Create the FotoAcompanhamentoAluno with an existing ID
        fotoAcompanhamentoAluno.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFotoAcompanhamentoAlunoMockMvc.perform(post("/api/foto-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoAcompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        // Validate the FotoAcompanhamentoAluno in the database
        List<FotoAcompanhamentoAluno> fotoAcompanhamentoAlunoList = fotoAcompanhamentoAlunoRepository.findAll();
        assertThat(fotoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFotoAcompanhamentoAlunos() throws Exception {
        // Initialize the database
        fotoAcompanhamentoAlunoRepository.saveAndFlush(fotoAcompanhamentoAluno);

        // Get all the fotoAcompanhamentoAlunoList
        restFotoAcompanhamentoAlunoMockMvc.perform(get("/api/foto-acompanhamento-alunos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fotoAcompanhamentoAluno.getId().intValue())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));
    }
    
    @Test
    @Transactional
    public void getFotoAcompanhamentoAluno() throws Exception {
        // Initialize the database
        fotoAcompanhamentoAlunoRepository.saveAndFlush(fotoAcompanhamentoAluno);

        // Get the fotoAcompanhamentoAluno
        restFotoAcompanhamentoAlunoMockMvc.perform(get("/api/foto-acompanhamento-alunos/{id}", fotoAcompanhamentoAluno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fotoAcompanhamentoAluno.getId().intValue()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)));
    }

    @Test
    @Transactional
    public void getNonExistingFotoAcompanhamentoAluno() throws Exception {
        // Get the fotoAcompanhamentoAluno
        restFotoAcompanhamentoAlunoMockMvc.perform(get("/api/foto-acompanhamento-alunos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFotoAcompanhamentoAluno() throws Exception {
        // Initialize the database
        fotoAcompanhamentoAlunoService.save(fotoAcompanhamentoAluno);

        int databaseSizeBeforeUpdate = fotoAcompanhamentoAlunoRepository.findAll().size();

        // Update the fotoAcompanhamentoAluno
        FotoAcompanhamentoAluno updatedFotoAcompanhamentoAluno = fotoAcompanhamentoAlunoRepository.findById(fotoAcompanhamentoAluno.getId()).get();
        // Disconnect from session so that the updates on updatedFotoAcompanhamentoAluno are not directly saved in db
        em.detach(updatedFotoAcompanhamentoAluno);
        updatedFotoAcompanhamentoAluno
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);

        restFotoAcompanhamentoAlunoMockMvc.perform(put("/api/foto-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFotoAcompanhamentoAluno)))
            .andExpect(status().isOk());

        // Validate the FotoAcompanhamentoAluno in the database
        List<FotoAcompanhamentoAluno> fotoAcompanhamentoAlunoList = fotoAcompanhamentoAlunoRepository.findAll();
        assertThat(fotoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeUpdate);
        FotoAcompanhamentoAluno testFotoAcompanhamentoAluno = fotoAcompanhamentoAlunoList.get(fotoAcompanhamentoAlunoList.size() - 1);
        assertThat(testFotoAcompanhamentoAluno.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testFotoAcompanhamentoAluno.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFotoAcompanhamentoAluno() throws Exception {
        int databaseSizeBeforeUpdate = fotoAcompanhamentoAlunoRepository.findAll().size();

        // Create the FotoAcompanhamentoAluno

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFotoAcompanhamentoAlunoMockMvc.perform(put("/api/foto-acompanhamento-alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoAcompanhamentoAluno)))
            .andExpect(status().isBadRequest());

        // Validate the FotoAcompanhamentoAluno in the database
        List<FotoAcompanhamentoAluno> fotoAcompanhamentoAlunoList = fotoAcompanhamentoAlunoRepository.findAll();
        assertThat(fotoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFotoAcompanhamentoAluno() throws Exception {
        // Initialize the database
        fotoAcompanhamentoAlunoService.save(fotoAcompanhamentoAluno);

        int databaseSizeBeforeDelete = fotoAcompanhamentoAlunoRepository.findAll().size();

        // Get the fotoAcompanhamentoAluno
        restFotoAcompanhamentoAlunoMockMvc.perform(delete("/api/foto-acompanhamento-alunos/{id}", fotoAcompanhamentoAluno.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FotoAcompanhamentoAluno> fotoAcompanhamentoAlunoList = fotoAcompanhamentoAlunoRepository.findAll();
        assertThat(fotoAcompanhamentoAlunoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FotoAcompanhamentoAluno.class);
        FotoAcompanhamentoAluno fotoAcompanhamentoAluno1 = new FotoAcompanhamentoAluno();
        fotoAcompanhamentoAluno1.setId(1L);
        FotoAcompanhamentoAluno fotoAcompanhamentoAluno2 = new FotoAcompanhamentoAluno();
        fotoAcompanhamentoAluno2.setId(fotoAcompanhamentoAluno1.getId());
        assertThat(fotoAcompanhamentoAluno1).isEqualTo(fotoAcompanhamentoAluno2);
        fotoAcompanhamentoAluno2.setId(2L);
        assertThat(fotoAcompanhamentoAluno1).isNotEqualTo(fotoAcompanhamentoAluno2);
        fotoAcompanhamentoAluno1.setId(null);
        assertThat(fotoAcompanhamentoAluno1).isNotEqualTo(fotoAcompanhamentoAluno2);
    }
}
