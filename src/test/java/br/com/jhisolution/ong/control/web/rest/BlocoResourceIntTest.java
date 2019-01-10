package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Bloco;
import br.com.jhisolution.ong.control.repository.BlocoRepository;
import br.com.jhisolution.ong.control.service.BlocoService;
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
 * Test class for the BlocoResource REST controller.
 *
 * @see BlocoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class BlocoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private BlocoRepository blocoRepository;

    @Autowired
    private BlocoService blocoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBlocoMockMvc;

    private Bloco bloco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlocoResource blocoResource = new BlocoResource(blocoService);
        this.restBlocoMockMvc = MockMvcBuilders.standaloneSetup(blocoResource)
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
    public static Bloco createEntity(EntityManager em) {
        Bloco bloco = new Bloco()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO)
            .obs(DEFAULT_OBS);
        return bloco;
    }

    @Before
    public void initTest() {
        bloco = createEntity(em);
    }

    @Test
    @Transactional
    public void createBloco() throws Exception {
        int databaseSizeBeforeCreate = blocoRepository.findAll().size();

        // Create the Bloco
        restBlocoMockMvc.perform(post("/api/blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloco)))
            .andExpect(status().isCreated());

        // Validate the Bloco in the database
        List<Bloco> blocoList = blocoRepository.findAll();
        assertThat(blocoList).hasSize(databaseSizeBeforeCreate + 1);
        Bloco testBloco = blocoList.get(blocoList.size() - 1);
        assertThat(testBloco.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testBloco.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testBloco.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createBlocoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blocoRepository.findAll().size();

        // Create the Bloco with an existing ID
        bloco.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlocoMockMvc.perform(post("/api/blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloco)))
            .andExpect(status().isBadRequest());

        // Validate the Bloco in the database
        List<Bloco> blocoList = blocoRepository.findAll();
        assertThat(blocoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = blocoRepository.findAll().size();
        // set the field null
        bloco.setNome(null);

        // Create the Bloco, which fails.

        restBlocoMockMvc.perform(post("/api/blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloco)))
            .andExpect(status().isBadRequest());

        List<Bloco> blocoList = blocoRepository.findAll();
        assertThat(blocoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBlocos() throws Exception {
        // Initialize the database
        blocoRepository.saveAndFlush(bloco);

        // Get all the blocoList
        restBlocoMockMvc.perform(get("/api/blocos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bloco.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getBloco() throws Exception {
        // Initialize the database
        blocoRepository.saveAndFlush(bloco);

        // Get the bloco
        restBlocoMockMvc.perform(get("/api/blocos/{id}", bloco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bloco.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBloco() throws Exception {
        // Get the bloco
        restBlocoMockMvc.perform(get("/api/blocos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBloco() throws Exception {
        // Initialize the database
        blocoService.save(bloco);

        int databaseSizeBeforeUpdate = blocoRepository.findAll().size();

        // Update the bloco
        Bloco updatedBloco = blocoRepository.findById(bloco.getId()).get();
        // Disconnect from session so that the updates on updatedBloco are not directly saved in db
        em.detach(updatedBloco);
        updatedBloco
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO)
            .obs(UPDATED_OBS);

        restBlocoMockMvc.perform(put("/api/blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBloco)))
            .andExpect(status().isOk());

        // Validate the Bloco in the database
        List<Bloco> blocoList = blocoRepository.findAll();
        assertThat(blocoList).hasSize(databaseSizeBeforeUpdate);
        Bloco testBloco = blocoList.get(blocoList.size() - 1);
        assertThat(testBloco.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testBloco.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testBloco.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingBloco() throws Exception {
        int databaseSizeBeforeUpdate = blocoRepository.findAll().size();

        // Create the Bloco

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlocoMockMvc.perform(put("/api/blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloco)))
            .andExpect(status().isBadRequest());

        // Validate the Bloco in the database
        List<Bloco> blocoList = blocoRepository.findAll();
        assertThat(blocoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBloco() throws Exception {
        // Initialize the database
        blocoService.save(bloco);

        int databaseSizeBeforeDelete = blocoRepository.findAll().size();

        // Get the bloco
        restBlocoMockMvc.perform(delete("/api/blocos/{id}", bloco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bloco> blocoList = blocoRepository.findAll();
        assertThat(blocoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bloco.class);
        Bloco bloco1 = new Bloco();
        bloco1.setId(1L);
        Bloco bloco2 = new Bloco();
        bloco2.setId(bloco1.getId());
        assertThat(bloco1).isEqualTo(bloco2);
        bloco2.setId(2L);
        assertThat(bloco1).isNotEqualTo(bloco2);
        bloco1.setId(null);
        assertThat(bloco1).isNotEqualTo(bloco2);
    }
}
