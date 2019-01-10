package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativaEconomica;
import br.com.jhisolution.ong.control.repository.OpcaoRespAvalOptativaEconomicaRepository;
import br.com.jhisolution.ong.control.service.OpcaoRespAvalOptativaEconomicaService;
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
 * Test class for the OpcaoRespAvalOptativaEconomicaResource REST controller.
 *
 * @see OpcaoRespAvalOptativaEconomicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class OpcaoRespAvalOptativaEconomicaResourceIntTest {

    private static final String DEFAULT_OPCAO = "AAAAAAAAAA";
    private static final String UPDATED_OPCAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private OpcaoRespAvalOptativaEconomicaRepository opcaoRespAvalOptativaEconomicaRepository;

    @Autowired
    private OpcaoRespAvalOptativaEconomicaService opcaoRespAvalOptativaEconomicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOpcaoRespAvalOptativaEconomicaMockMvc;

    private OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OpcaoRespAvalOptativaEconomicaResource opcaoRespAvalOptativaEconomicaResource = new OpcaoRespAvalOptativaEconomicaResource(opcaoRespAvalOptativaEconomicaService);
        this.restOpcaoRespAvalOptativaEconomicaMockMvc = MockMvcBuilders.standaloneSetup(opcaoRespAvalOptativaEconomicaResource)
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
    public static OpcaoRespAvalOptativaEconomica createEntity(EntityManager em) {
        OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica = new OpcaoRespAvalOptativaEconomica()
            .opcao(DEFAULT_OPCAO)
            .obs(DEFAULT_OBS);
        return opcaoRespAvalOptativaEconomica;
    }

    @Before
    public void initTest() {
        opcaoRespAvalOptativaEconomica = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpcaoRespAvalOptativaEconomica() throws Exception {
        int databaseSizeBeforeCreate = opcaoRespAvalOptativaEconomicaRepository.findAll().size();

        // Create the OpcaoRespAvalOptativaEconomica
        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(post("/api/opcao-resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativaEconomica)))
            .andExpect(status().isCreated());

        // Validate the OpcaoRespAvalOptativaEconomica in the database
        List<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicaList = opcaoRespAvalOptativaEconomicaRepository.findAll();
        assertThat(opcaoRespAvalOptativaEconomicaList).hasSize(databaseSizeBeforeCreate + 1);
        OpcaoRespAvalOptativaEconomica testOpcaoRespAvalOptativaEconomica = opcaoRespAvalOptativaEconomicaList.get(opcaoRespAvalOptativaEconomicaList.size() - 1);
        assertThat(testOpcaoRespAvalOptativaEconomica.getOpcao()).isEqualTo(DEFAULT_OPCAO);
        assertThat(testOpcaoRespAvalOptativaEconomica.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createOpcaoRespAvalOptativaEconomicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opcaoRespAvalOptativaEconomicaRepository.findAll().size();

        // Create the OpcaoRespAvalOptativaEconomica with an existing ID
        opcaoRespAvalOptativaEconomica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(post("/api/opcao-resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativaEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the OpcaoRespAvalOptativaEconomica in the database
        List<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicaList = opcaoRespAvalOptativaEconomicaRepository.findAll();
        assertThat(opcaoRespAvalOptativaEconomicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkOpcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = opcaoRespAvalOptativaEconomicaRepository.findAll().size();
        // set the field null
        opcaoRespAvalOptativaEconomica.setOpcao(null);

        // Create the OpcaoRespAvalOptativaEconomica, which fails.

        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(post("/api/opcao-resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativaEconomica)))
            .andExpect(status().isBadRequest());

        List<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicaList = opcaoRespAvalOptativaEconomicaRepository.findAll();
        assertThat(opcaoRespAvalOptativaEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOpcaoRespAvalOptativaEconomicas() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaEconomicaRepository.saveAndFlush(opcaoRespAvalOptativaEconomica);

        // Get all the opcaoRespAvalOptativaEconomicaList
        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(get("/api/opcao-resp-aval-optativa-economicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opcaoRespAvalOptativaEconomica.getId().intValue())))
            .andExpect(jsonPath("$.[*].opcao").value(hasItem(DEFAULT_OPCAO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getOpcaoRespAvalOptativaEconomica() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaEconomicaRepository.saveAndFlush(opcaoRespAvalOptativaEconomica);

        // Get the opcaoRespAvalOptativaEconomica
        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(get("/api/opcao-resp-aval-optativa-economicas/{id}", opcaoRespAvalOptativaEconomica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(opcaoRespAvalOptativaEconomica.getId().intValue()))
            .andExpect(jsonPath("$.opcao").value(DEFAULT_OPCAO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOpcaoRespAvalOptativaEconomica() throws Exception {
        // Get the opcaoRespAvalOptativaEconomica
        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(get("/api/opcao-resp-aval-optativa-economicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpcaoRespAvalOptativaEconomica() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaEconomicaService.save(opcaoRespAvalOptativaEconomica);

        int databaseSizeBeforeUpdate = opcaoRespAvalOptativaEconomicaRepository.findAll().size();

        // Update the opcaoRespAvalOptativaEconomica
        OpcaoRespAvalOptativaEconomica updatedOpcaoRespAvalOptativaEconomica = opcaoRespAvalOptativaEconomicaRepository.findById(opcaoRespAvalOptativaEconomica.getId()).get();
        // Disconnect from session so that the updates on updatedOpcaoRespAvalOptativaEconomica are not directly saved in db
        em.detach(updatedOpcaoRespAvalOptativaEconomica);
        updatedOpcaoRespAvalOptativaEconomica
            .opcao(UPDATED_OPCAO)
            .obs(UPDATED_OBS);

        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(put("/api/opcao-resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOpcaoRespAvalOptativaEconomica)))
            .andExpect(status().isOk());

        // Validate the OpcaoRespAvalOptativaEconomica in the database
        List<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicaList = opcaoRespAvalOptativaEconomicaRepository.findAll();
        assertThat(opcaoRespAvalOptativaEconomicaList).hasSize(databaseSizeBeforeUpdate);
        OpcaoRespAvalOptativaEconomica testOpcaoRespAvalOptativaEconomica = opcaoRespAvalOptativaEconomicaList.get(opcaoRespAvalOptativaEconomicaList.size() - 1);
        assertThat(testOpcaoRespAvalOptativaEconomica.getOpcao()).isEqualTo(UPDATED_OPCAO);
        assertThat(testOpcaoRespAvalOptativaEconomica.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingOpcaoRespAvalOptativaEconomica() throws Exception {
        int databaseSizeBeforeUpdate = opcaoRespAvalOptativaEconomicaRepository.findAll().size();

        // Create the OpcaoRespAvalOptativaEconomica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(put("/api/opcao-resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativaEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the OpcaoRespAvalOptativaEconomica in the database
        List<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicaList = opcaoRespAvalOptativaEconomicaRepository.findAll();
        assertThat(opcaoRespAvalOptativaEconomicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpcaoRespAvalOptativaEconomica() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaEconomicaService.save(opcaoRespAvalOptativaEconomica);

        int databaseSizeBeforeDelete = opcaoRespAvalOptativaEconomicaRepository.findAll().size();

        // Get the opcaoRespAvalOptativaEconomica
        restOpcaoRespAvalOptativaEconomicaMockMvc.perform(delete("/api/opcao-resp-aval-optativa-economicas/{id}", opcaoRespAvalOptativaEconomica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicaList = opcaoRespAvalOptativaEconomicaRepository.findAll();
        assertThat(opcaoRespAvalOptativaEconomicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcaoRespAvalOptativaEconomica.class);
        OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica1 = new OpcaoRespAvalOptativaEconomica();
        opcaoRespAvalOptativaEconomica1.setId(1L);
        OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica2 = new OpcaoRespAvalOptativaEconomica();
        opcaoRespAvalOptativaEconomica2.setId(opcaoRespAvalOptativaEconomica1.getId());
        assertThat(opcaoRespAvalOptativaEconomica1).isEqualTo(opcaoRespAvalOptativaEconomica2);
        opcaoRespAvalOptativaEconomica2.setId(2L);
        assertThat(opcaoRespAvalOptativaEconomica1).isNotEqualTo(opcaoRespAvalOptativaEconomica2);
        opcaoRespAvalOptativaEconomica1.setId(null);
        assertThat(opcaoRespAvalOptativaEconomica1).isNotEqualTo(opcaoRespAvalOptativaEconomica2);
    }
}
