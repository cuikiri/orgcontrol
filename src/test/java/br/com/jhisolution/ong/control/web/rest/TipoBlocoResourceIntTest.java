package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoBloco;
import br.com.jhisolution.ong.control.repository.TipoBlocoRepository;
import br.com.jhisolution.ong.control.service.TipoBlocoService;
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
 * Test class for the TipoBlocoResource REST controller.
 *
 * @see TipoBlocoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoBlocoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private TipoBlocoRepository tipoBlocoRepository;

    @Autowired
    private TipoBlocoService tipoBlocoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoBlocoMockMvc;

    private TipoBloco tipoBloco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoBlocoResource tipoBlocoResource = new TipoBlocoResource(tipoBlocoService);
        this.restTipoBlocoMockMvc = MockMvcBuilders.standaloneSetup(tipoBlocoResource)
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
    public static TipoBloco createEntity(EntityManager em) {
        TipoBloco tipoBloco = new TipoBloco()
            .nome(DEFAULT_NOME);
        return tipoBloco;
    }

    @Before
    public void initTest() {
        tipoBloco = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoBloco() throws Exception {
        int databaseSizeBeforeCreate = tipoBlocoRepository.findAll().size();

        // Create the TipoBloco
        restTipoBlocoMockMvc.perform(post("/api/tipo-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBloco)))
            .andExpect(status().isCreated());

        // Validate the TipoBloco in the database
        List<TipoBloco> tipoBlocoList = tipoBlocoRepository.findAll();
        assertThat(tipoBlocoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoBloco testTipoBloco = tipoBlocoList.get(tipoBlocoList.size() - 1);
        assertThat(testTipoBloco.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createTipoBlocoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoBlocoRepository.findAll().size();

        // Create the TipoBloco with an existing ID
        tipoBloco.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoBlocoMockMvc.perform(post("/api/tipo-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBloco)))
            .andExpect(status().isBadRequest());

        // Validate the TipoBloco in the database
        List<TipoBloco> tipoBlocoList = tipoBlocoRepository.findAll();
        assertThat(tipoBlocoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoBlocoRepository.findAll().size();
        // set the field null
        tipoBloco.setNome(null);

        // Create the TipoBloco, which fails.

        restTipoBlocoMockMvc.perform(post("/api/tipo-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBloco)))
            .andExpect(status().isBadRequest());

        List<TipoBloco> tipoBlocoList = tipoBlocoRepository.findAll();
        assertThat(tipoBlocoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoBlocos() throws Exception {
        // Initialize the database
        tipoBlocoRepository.saveAndFlush(tipoBloco);

        // Get all the tipoBlocoList
        restTipoBlocoMockMvc.perform(get("/api/tipo-blocos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoBloco.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoBloco() throws Exception {
        // Initialize the database
        tipoBlocoRepository.saveAndFlush(tipoBloco);

        // Get the tipoBloco
        restTipoBlocoMockMvc.perform(get("/api/tipo-blocos/{id}", tipoBloco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoBloco.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoBloco() throws Exception {
        // Get the tipoBloco
        restTipoBlocoMockMvc.perform(get("/api/tipo-blocos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoBloco() throws Exception {
        // Initialize the database
        tipoBlocoService.save(tipoBloco);

        int databaseSizeBeforeUpdate = tipoBlocoRepository.findAll().size();

        // Update the tipoBloco
        TipoBloco updatedTipoBloco = tipoBlocoRepository.findById(tipoBloco.getId()).get();
        // Disconnect from session so that the updates on updatedTipoBloco are not directly saved in db
        em.detach(updatedTipoBloco);
        updatedTipoBloco
            .nome(UPDATED_NOME);

        restTipoBlocoMockMvc.perform(put("/api/tipo-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoBloco)))
            .andExpect(status().isOk());

        // Validate the TipoBloco in the database
        List<TipoBloco> tipoBlocoList = tipoBlocoRepository.findAll();
        assertThat(tipoBlocoList).hasSize(databaseSizeBeforeUpdate);
        TipoBloco testTipoBloco = tipoBlocoList.get(tipoBlocoList.size() - 1);
        assertThat(testTipoBloco.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoBloco() throws Exception {
        int databaseSizeBeforeUpdate = tipoBlocoRepository.findAll().size();

        // Create the TipoBloco

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoBlocoMockMvc.perform(put("/api/tipo-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoBloco)))
            .andExpect(status().isBadRequest());

        // Validate the TipoBloco in the database
        List<TipoBloco> tipoBlocoList = tipoBlocoRepository.findAll();
        assertThat(tipoBlocoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoBloco() throws Exception {
        // Initialize the database
        tipoBlocoService.save(tipoBloco);

        int databaseSizeBeforeDelete = tipoBlocoRepository.findAll().size();

        // Get the tipoBloco
        restTipoBlocoMockMvc.perform(delete("/api/tipo-blocos/{id}", tipoBloco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoBloco> tipoBlocoList = tipoBlocoRepository.findAll();
        assertThat(tipoBlocoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoBloco.class);
        TipoBloco tipoBloco1 = new TipoBloco();
        tipoBloco1.setId(1L);
        TipoBloco tipoBloco2 = new TipoBloco();
        tipoBloco2.setId(tipoBloco1.getId());
        assertThat(tipoBloco1).isEqualTo(tipoBloco2);
        tipoBloco2.setId(2L);
        assertThat(tipoBloco1).isNotEqualTo(tipoBloco2);
        tipoBloco1.setId(null);
        assertThat(tipoBloco1).isNotEqualTo(tipoBloco2);
    }
}
