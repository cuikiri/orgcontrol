package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoParteBloco;
import br.com.jhisolution.ong.control.repository.TipoParteBlocoRepository;
import br.com.jhisolution.ong.control.service.TipoParteBlocoService;
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
 * Test class for the TipoParteBlocoResource REST controller.
 *
 * @see TipoParteBlocoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoParteBlocoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private TipoParteBlocoRepository tipoParteBlocoRepository;

    @Autowired
    private TipoParteBlocoService tipoParteBlocoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoParteBlocoMockMvc;

    private TipoParteBloco tipoParteBloco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoParteBlocoResource tipoParteBlocoResource = new TipoParteBlocoResource(tipoParteBlocoService);
        this.restTipoParteBlocoMockMvc = MockMvcBuilders.standaloneSetup(tipoParteBlocoResource)
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
    public static TipoParteBloco createEntity(EntityManager em) {
        TipoParteBloco tipoParteBloco = new TipoParteBloco()
            .nome(DEFAULT_NOME);
        return tipoParteBloco;
    }

    @Before
    public void initTest() {
        tipoParteBloco = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoParteBloco() throws Exception {
        int databaseSizeBeforeCreate = tipoParteBlocoRepository.findAll().size();

        // Create the TipoParteBloco
        restTipoParteBlocoMockMvc.perform(post("/api/tipo-parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoParteBloco)))
            .andExpect(status().isCreated());

        // Validate the TipoParteBloco in the database
        List<TipoParteBloco> tipoParteBlocoList = tipoParteBlocoRepository.findAll();
        assertThat(tipoParteBlocoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoParteBloco testTipoParteBloco = tipoParteBlocoList.get(tipoParteBlocoList.size() - 1);
        assertThat(testTipoParteBloco.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createTipoParteBlocoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoParteBlocoRepository.findAll().size();

        // Create the TipoParteBloco with an existing ID
        tipoParteBloco.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoParteBlocoMockMvc.perform(post("/api/tipo-parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoParteBloco)))
            .andExpect(status().isBadRequest());

        // Validate the TipoParteBloco in the database
        List<TipoParteBloco> tipoParteBlocoList = tipoParteBlocoRepository.findAll();
        assertThat(tipoParteBlocoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoParteBlocoRepository.findAll().size();
        // set the field null
        tipoParteBloco.setNome(null);

        // Create the TipoParteBloco, which fails.

        restTipoParteBlocoMockMvc.perform(post("/api/tipo-parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoParteBloco)))
            .andExpect(status().isBadRequest());

        List<TipoParteBloco> tipoParteBlocoList = tipoParteBlocoRepository.findAll();
        assertThat(tipoParteBlocoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoParteBlocos() throws Exception {
        // Initialize the database
        tipoParteBlocoRepository.saveAndFlush(tipoParteBloco);

        // Get all the tipoParteBlocoList
        restTipoParteBlocoMockMvc.perform(get("/api/tipo-parte-blocos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoParteBloco.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoParteBloco() throws Exception {
        // Initialize the database
        tipoParteBlocoRepository.saveAndFlush(tipoParteBloco);

        // Get the tipoParteBloco
        restTipoParteBlocoMockMvc.perform(get("/api/tipo-parte-blocos/{id}", tipoParteBloco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoParteBloco.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoParteBloco() throws Exception {
        // Get the tipoParteBloco
        restTipoParteBlocoMockMvc.perform(get("/api/tipo-parte-blocos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoParteBloco() throws Exception {
        // Initialize the database
        tipoParteBlocoService.save(tipoParteBloco);

        int databaseSizeBeforeUpdate = tipoParteBlocoRepository.findAll().size();

        // Update the tipoParteBloco
        TipoParteBloco updatedTipoParteBloco = tipoParteBlocoRepository.findById(tipoParteBloco.getId()).get();
        // Disconnect from session so that the updates on updatedTipoParteBloco are not directly saved in db
        em.detach(updatedTipoParteBloco);
        updatedTipoParteBloco
            .nome(UPDATED_NOME);

        restTipoParteBlocoMockMvc.perform(put("/api/tipo-parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoParteBloco)))
            .andExpect(status().isOk());

        // Validate the TipoParteBloco in the database
        List<TipoParteBloco> tipoParteBlocoList = tipoParteBlocoRepository.findAll();
        assertThat(tipoParteBlocoList).hasSize(databaseSizeBeforeUpdate);
        TipoParteBloco testTipoParteBloco = tipoParteBlocoList.get(tipoParteBlocoList.size() - 1);
        assertThat(testTipoParteBloco.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoParteBloco() throws Exception {
        int databaseSizeBeforeUpdate = tipoParteBlocoRepository.findAll().size();

        // Create the TipoParteBloco

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoParteBlocoMockMvc.perform(put("/api/tipo-parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoParteBloco)))
            .andExpect(status().isBadRequest());

        // Validate the TipoParteBloco in the database
        List<TipoParteBloco> tipoParteBlocoList = tipoParteBlocoRepository.findAll();
        assertThat(tipoParteBlocoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoParteBloco() throws Exception {
        // Initialize the database
        tipoParteBlocoService.save(tipoParteBloco);

        int databaseSizeBeforeDelete = tipoParteBlocoRepository.findAll().size();

        // Get the tipoParteBloco
        restTipoParteBlocoMockMvc.perform(delete("/api/tipo-parte-blocos/{id}", tipoParteBloco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoParteBloco> tipoParteBlocoList = tipoParteBlocoRepository.findAll();
        assertThat(tipoParteBlocoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoParteBloco.class);
        TipoParteBloco tipoParteBloco1 = new TipoParteBloco();
        tipoParteBloco1.setId(1L);
        TipoParteBloco tipoParteBloco2 = new TipoParteBloco();
        tipoParteBloco2.setId(tipoParteBloco1.getId());
        assertThat(tipoParteBloco1).isEqualTo(tipoParteBloco2);
        tipoParteBloco2.setId(2L);
        assertThat(tipoParteBloco1).isNotEqualTo(tipoParteBloco2);
        tipoParteBloco1.setId(null);
        assertThat(tipoParteBloco1).isNotEqualTo(tipoParteBloco2);
    }
}
