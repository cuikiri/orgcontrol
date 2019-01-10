package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.PlanejamentoAtividade;
import br.com.jhisolution.ong.control.repository.PlanejamentoAtividadeRepository;
import br.com.jhisolution.ong.control.service.PlanejamentoAtividadeService;
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
 * Test class for the PlanejamentoAtividadeResource REST controller.
 *
 * @see PlanejamentoAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class PlanejamentoAtividadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBJETIVO = "AAAAAAAAAA";
    private static final String UPDATED_OBJETIVO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private PlanejamentoAtividadeRepository planejamentoAtividadeRepository;

    @Autowired
    private PlanejamentoAtividadeService planejamentoAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlanejamentoAtividadeMockMvc;

    private PlanejamentoAtividade planejamentoAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanejamentoAtividadeResource planejamentoAtividadeResource = new PlanejamentoAtividadeResource(planejamentoAtividadeService);
        this.restPlanejamentoAtividadeMockMvc = MockMvcBuilders.standaloneSetup(planejamentoAtividadeResource)
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
    public static PlanejamentoAtividade createEntity(EntityManager em) {
        PlanejamentoAtividade planejamentoAtividade = new PlanejamentoAtividade()
            .nome(DEFAULT_NOME)
            .objetivo(DEFAULT_OBJETIVO)
            .obs(DEFAULT_OBS);
        return planejamentoAtividade;
    }

    @Before
    public void initTest() {
        planejamentoAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanejamentoAtividade() throws Exception {
        int databaseSizeBeforeCreate = planejamentoAtividadeRepository.findAll().size();

        // Create the PlanejamentoAtividade
        restPlanejamentoAtividadeMockMvc.perform(post("/api/planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoAtividade)))
            .andExpect(status().isCreated());

        // Validate the PlanejamentoAtividade in the database
        List<PlanejamentoAtividade> planejamentoAtividadeList = planejamentoAtividadeRepository.findAll();
        assertThat(planejamentoAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        PlanejamentoAtividade testPlanejamentoAtividade = planejamentoAtividadeList.get(planejamentoAtividadeList.size() - 1);
        assertThat(testPlanejamentoAtividade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPlanejamentoAtividade.getObjetivo()).isEqualTo(DEFAULT_OBJETIVO);
        assertThat(testPlanejamentoAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createPlanejamentoAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planejamentoAtividadeRepository.findAll().size();

        // Create the PlanejamentoAtividade with an existing ID
        planejamentoAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanejamentoAtividadeMockMvc.perform(post("/api/planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the PlanejamentoAtividade in the database
        List<PlanejamentoAtividade> planejamentoAtividadeList = planejamentoAtividadeRepository.findAll();
        assertThat(planejamentoAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = planejamentoAtividadeRepository.findAll().size();
        // set the field null
        planejamentoAtividade.setNome(null);

        // Create the PlanejamentoAtividade, which fails.

        restPlanejamentoAtividadeMockMvc.perform(post("/api/planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<PlanejamentoAtividade> planejamentoAtividadeList = planejamentoAtividadeRepository.findAll();
        assertThat(planejamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanejamentoAtividades() throws Exception {
        // Initialize the database
        planejamentoAtividadeRepository.saveAndFlush(planejamentoAtividade);

        // Get all the planejamentoAtividadeList
        restPlanejamentoAtividadeMockMvc.perform(get("/api/planejamento-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planejamentoAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].objetivo").value(hasItem(DEFAULT_OBJETIVO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanejamentoAtividade() throws Exception {
        // Initialize the database
        planejamentoAtividadeRepository.saveAndFlush(planejamentoAtividade);

        // Get the planejamentoAtividade
        restPlanejamentoAtividadeMockMvc.perform(get("/api/planejamento-atividades/{id}", planejamentoAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planejamentoAtividade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.objetivo").value(DEFAULT_OBJETIVO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanejamentoAtividade() throws Exception {
        // Get the planejamentoAtividade
        restPlanejamentoAtividadeMockMvc.perform(get("/api/planejamento-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanejamentoAtividade() throws Exception {
        // Initialize the database
        planejamentoAtividadeService.save(planejamentoAtividade);

        int databaseSizeBeforeUpdate = planejamentoAtividadeRepository.findAll().size();

        // Update the planejamentoAtividade
        PlanejamentoAtividade updatedPlanejamentoAtividade = planejamentoAtividadeRepository.findById(planejamentoAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedPlanejamentoAtividade are not directly saved in db
        em.detach(updatedPlanejamentoAtividade);
        updatedPlanejamentoAtividade
            .nome(UPDATED_NOME)
            .objetivo(UPDATED_OBJETIVO)
            .obs(UPDATED_OBS);

        restPlanejamentoAtividadeMockMvc.perform(put("/api/planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlanejamentoAtividade)))
            .andExpect(status().isOk());

        // Validate the PlanejamentoAtividade in the database
        List<PlanejamentoAtividade> planejamentoAtividadeList = planejamentoAtividadeRepository.findAll();
        assertThat(planejamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
        PlanejamentoAtividade testPlanejamentoAtividade = planejamentoAtividadeList.get(planejamentoAtividadeList.size() - 1);
        assertThat(testPlanejamentoAtividade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPlanejamentoAtividade.getObjetivo()).isEqualTo(UPDATED_OBJETIVO);
        assertThat(testPlanejamentoAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanejamentoAtividade() throws Exception {
        int databaseSizeBeforeUpdate = planejamentoAtividadeRepository.findAll().size();

        // Create the PlanejamentoAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanejamentoAtividadeMockMvc.perform(put("/api/planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planejamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the PlanejamentoAtividade in the database
        List<PlanejamentoAtividade> planejamentoAtividadeList = planejamentoAtividadeRepository.findAll();
        assertThat(planejamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanejamentoAtividade() throws Exception {
        // Initialize the database
        planejamentoAtividadeService.save(planejamentoAtividade);

        int databaseSizeBeforeDelete = planejamentoAtividadeRepository.findAll().size();

        // Get the planejamentoAtividade
        restPlanejamentoAtividadeMockMvc.perform(delete("/api/planejamento-atividades/{id}", planejamentoAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanejamentoAtividade> planejamentoAtividadeList = planejamentoAtividadeRepository.findAll();
        assertThat(planejamentoAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanejamentoAtividade.class);
        PlanejamentoAtividade planejamentoAtividade1 = new PlanejamentoAtividade();
        planejamentoAtividade1.setId(1L);
        PlanejamentoAtividade planejamentoAtividade2 = new PlanejamentoAtividade();
        planejamentoAtividade2.setId(planejamentoAtividade1.getId());
        assertThat(planejamentoAtividade1).isEqualTo(planejamentoAtividade2);
        planejamentoAtividade2.setId(2L);
        assertThat(planejamentoAtividade1).isNotEqualTo(planejamentoAtividade2);
        planejamentoAtividade1.setId(null);
        assertThat(planejamentoAtividade1).isNotEqualTo(planejamentoAtividade2);
    }
}
