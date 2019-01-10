package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.PlanejamentoInstituicao;
import br.com.jhisolution.ong.control.repository.PlanejamentoInstituicaoRepository;
import br.com.jhisolution.ong.control.service.PlanejamentoInstituicaoService;
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
 * Test class for the PlanejamentoInstituicaoResource REST controller.
 *
 * @see PlanejamentoInstituicaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class PlanejamentoInstituicaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBJETIVO = "AAAAAAAAAA";
    private static final String UPDATED_OBJETIVO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private PlanejamentoInstituicaoRepository planejamentoInstituicaoRepository;

    @Autowired
    private PlanejamentoInstituicaoService planejamentoInstituicaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlanejamentoInstituicaoMockMvc;

    private PlanejamentoInstituicao planejamentoInstituicao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanejamentoInstituicaoResource planejamentoInstituicaoResource = new PlanejamentoInstituicaoResource(planejamentoInstituicaoService);
        this.restPlanejamentoInstituicaoMockMvc = MockMvcBuilders.standaloneSetup(planejamentoInstituicaoResource)
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
    public static PlanejamentoInstituicao createEntity(EntityManager em) {
        PlanejamentoInstituicao planejamentoInstituicao = new PlanejamentoInstituicao()
            .nome(DEFAULT_NOME)
            .objetivo(DEFAULT_OBJETIVO)
            .obs(DEFAULT_OBS);
        return planejamentoInstituicao;
    }

    @Before
    public void initTest() {
        planejamentoInstituicao = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanejamentoInstituicao() throws Exception {
        int databaseSizeBeforeCreate = planejamentoInstituicaoRepository.findAll().size();

        // Create the PlanejamentoInstituicao
        restPlanejamentoInstituicaoMockMvc.perform(post("/api/planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoInstituicao)))
            .andExpect(status().isCreated());

        // Validate the PlanejamentoInstituicao in the database
        List<PlanejamentoInstituicao> planejamentoInstituicaoList = planejamentoInstituicaoRepository.findAll();
        assertThat(planejamentoInstituicaoList).hasSize(databaseSizeBeforeCreate + 1);
        PlanejamentoInstituicao testPlanejamentoInstituicao = planejamentoInstituicaoList.get(planejamentoInstituicaoList.size() - 1);
        assertThat(testPlanejamentoInstituicao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPlanejamentoInstituicao.getObjetivo()).isEqualTo(DEFAULT_OBJETIVO);
        assertThat(testPlanejamentoInstituicao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createPlanejamentoInstituicaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planejamentoInstituicaoRepository.findAll().size();

        // Create the PlanejamentoInstituicao with an existing ID
        planejamentoInstituicao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanejamentoInstituicaoMockMvc.perform(post("/api/planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoInstituicao)))
            .andExpect(status().isBadRequest());

        // Validate the PlanejamentoInstituicao in the database
        List<PlanejamentoInstituicao> planejamentoInstituicaoList = planejamentoInstituicaoRepository.findAll();
        assertThat(planejamentoInstituicaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = planejamentoInstituicaoRepository.findAll().size();
        // set the field null
        planejamentoInstituicao.setNome(null);

        // Create the PlanejamentoInstituicao, which fails.

        restPlanejamentoInstituicaoMockMvc.perform(post("/api/planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoInstituicao)))
            .andExpect(status().isBadRequest());

        List<PlanejamentoInstituicao> planejamentoInstituicaoList = planejamentoInstituicaoRepository.findAll();
        assertThat(planejamentoInstituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanejamentoInstituicaos() throws Exception {
        // Initialize the database
        planejamentoInstituicaoRepository.saveAndFlush(planejamentoInstituicao);

        // Get all the planejamentoInstituicaoList
        restPlanejamentoInstituicaoMockMvc.perform(get("/api/planejamento-instituicaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planejamentoInstituicao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].objetivo").value(hasItem(DEFAULT_OBJETIVO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanejamentoInstituicao() throws Exception {
        // Initialize the database
        planejamentoInstituicaoRepository.saveAndFlush(planejamentoInstituicao);

        // Get the planejamentoInstituicao
        restPlanejamentoInstituicaoMockMvc.perform(get("/api/planejamento-instituicaos/{id}", planejamentoInstituicao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planejamentoInstituicao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.objetivo").value(DEFAULT_OBJETIVO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanejamentoInstituicao() throws Exception {
        // Get the planejamentoInstituicao
        restPlanejamentoInstituicaoMockMvc.perform(get("/api/planejamento-instituicaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanejamentoInstituicao() throws Exception {
        // Initialize the database
        planejamentoInstituicaoService.save(planejamentoInstituicao);

        int databaseSizeBeforeUpdate = planejamentoInstituicaoRepository.findAll().size();

        // Update the planejamentoInstituicao
        PlanejamentoInstituicao updatedPlanejamentoInstituicao = planejamentoInstituicaoRepository.findById(planejamentoInstituicao.getId()).get();
        // Disconnect from session so that the updates on updatedPlanejamentoInstituicao are not directly saved in db
        em.detach(updatedPlanejamentoInstituicao);
        updatedPlanejamentoInstituicao
            .nome(UPDATED_NOME)
            .objetivo(UPDATED_OBJETIVO)
            .obs(UPDATED_OBS);

        restPlanejamentoInstituicaoMockMvc.perform(put("/api/planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlanejamentoInstituicao)))
            .andExpect(status().isOk());

        // Validate the PlanejamentoInstituicao in the database
        List<PlanejamentoInstituicao> planejamentoInstituicaoList = planejamentoInstituicaoRepository.findAll();
        assertThat(planejamentoInstituicaoList).hasSize(databaseSizeBeforeUpdate);
        PlanejamentoInstituicao testPlanejamentoInstituicao = planejamentoInstituicaoList.get(planejamentoInstituicaoList.size() - 1);
        assertThat(testPlanejamentoInstituicao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPlanejamentoInstituicao.getObjetivo()).isEqualTo(UPDATED_OBJETIVO);
        assertThat(testPlanejamentoInstituicao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanejamentoInstituicao() throws Exception {
        int databaseSizeBeforeUpdate = planejamentoInstituicaoRepository.findAll().size();

        // Create the PlanejamentoInstituicao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanejamentoInstituicaoMockMvc.perform(put("/api/planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoInstituicao)))
            .andExpect(status().isBadRequest());

        // Validate the PlanejamentoInstituicao in the database
        List<PlanejamentoInstituicao> planejamentoInstituicaoList = planejamentoInstituicaoRepository.findAll();
        assertThat(planejamentoInstituicaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanejamentoInstituicao() throws Exception {
        // Initialize the database
        planejamentoInstituicaoService.save(planejamentoInstituicao);

        int databaseSizeBeforeDelete = planejamentoInstituicaoRepository.findAll().size();

        // Get the planejamentoInstituicao
        restPlanejamentoInstituicaoMockMvc.perform(delete("/api/planejamento-instituicaos/{id}", planejamentoInstituicao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanejamentoInstituicao> planejamentoInstituicaoList = planejamentoInstituicaoRepository.findAll();
        assertThat(planejamentoInstituicaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanejamentoInstituicao.class);
        PlanejamentoInstituicao planejamentoInstituicao1 = new PlanejamentoInstituicao();
        planejamentoInstituicao1.setId(1L);
        PlanejamentoInstituicao planejamentoInstituicao2 = new PlanejamentoInstituicao();
        planejamentoInstituicao2.setId(planejamentoInstituicao1.getId());
        assertThat(planejamentoInstituicao1).isEqualTo(planejamentoInstituicao2);
        planejamentoInstituicao2.setId(2L);
        assertThat(planejamentoInstituicao1).isNotEqualTo(planejamentoInstituicao2);
        planejamentoInstituicao1.setId(null);
        assertThat(planejamentoInstituicao1).isNotEqualTo(planejamentoInstituicao2);
    }
}
