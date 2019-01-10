package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativa;
import br.com.jhisolution.ong.control.repository.OpcaoRespAvalOptativaRepository;
import br.com.jhisolution.ong.control.service.OpcaoRespAvalOptativaService;
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
 * Test class for the OpcaoRespAvalOptativaResource REST controller.
 *
 * @see OpcaoRespAvalOptativaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class OpcaoRespAvalOptativaResourceIntTest {

    private static final String DEFAULT_OPCAO = "AAAAAAAAAA";
    private static final String UPDATED_OPCAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private OpcaoRespAvalOptativaRepository opcaoRespAvalOptativaRepository;

    @Autowired
    private OpcaoRespAvalOptativaService opcaoRespAvalOptativaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOpcaoRespAvalOptativaMockMvc;

    private OpcaoRespAvalOptativa opcaoRespAvalOptativa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OpcaoRespAvalOptativaResource opcaoRespAvalOptativaResource = new OpcaoRespAvalOptativaResource(opcaoRespAvalOptativaService);
        this.restOpcaoRespAvalOptativaMockMvc = MockMvcBuilders.standaloneSetup(opcaoRespAvalOptativaResource)
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
    public static OpcaoRespAvalOptativa createEntity(EntityManager em) {
        OpcaoRespAvalOptativa opcaoRespAvalOptativa = new OpcaoRespAvalOptativa()
            .opcao(DEFAULT_OPCAO)
            .obs(DEFAULT_OBS);
        return opcaoRespAvalOptativa;
    }

    @Before
    public void initTest() {
        opcaoRespAvalOptativa = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpcaoRespAvalOptativa() throws Exception {
        int databaseSizeBeforeCreate = opcaoRespAvalOptativaRepository.findAll().size();

        // Create the OpcaoRespAvalOptativa
        restOpcaoRespAvalOptativaMockMvc.perform(post("/api/opcao-resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativa)))
            .andExpect(status().isCreated());

        // Validate the OpcaoRespAvalOptativa in the database
        List<OpcaoRespAvalOptativa> opcaoRespAvalOptativaList = opcaoRespAvalOptativaRepository.findAll();
        assertThat(opcaoRespAvalOptativaList).hasSize(databaseSizeBeforeCreate + 1);
        OpcaoRespAvalOptativa testOpcaoRespAvalOptativa = opcaoRespAvalOptativaList.get(opcaoRespAvalOptativaList.size() - 1);
        assertThat(testOpcaoRespAvalOptativa.getOpcao()).isEqualTo(DEFAULT_OPCAO);
        assertThat(testOpcaoRespAvalOptativa.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createOpcaoRespAvalOptativaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opcaoRespAvalOptativaRepository.findAll().size();

        // Create the OpcaoRespAvalOptativa with an existing ID
        opcaoRespAvalOptativa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpcaoRespAvalOptativaMockMvc.perform(post("/api/opcao-resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativa)))
            .andExpect(status().isBadRequest());

        // Validate the OpcaoRespAvalOptativa in the database
        List<OpcaoRespAvalOptativa> opcaoRespAvalOptativaList = opcaoRespAvalOptativaRepository.findAll();
        assertThat(opcaoRespAvalOptativaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkOpcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = opcaoRespAvalOptativaRepository.findAll().size();
        // set the field null
        opcaoRespAvalOptativa.setOpcao(null);

        // Create the OpcaoRespAvalOptativa, which fails.

        restOpcaoRespAvalOptativaMockMvc.perform(post("/api/opcao-resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativa)))
            .andExpect(status().isBadRequest());

        List<OpcaoRespAvalOptativa> opcaoRespAvalOptativaList = opcaoRespAvalOptativaRepository.findAll();
        assertThat(opcaoRespAvalOptativaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOpcaoRespAvalOptativas() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaRepository.saveAndFlush(opcaoRespAvalOptativa);

        // Get all the opcaoRespAvalOptativaList
        restOpcaoRespAvalOptativaMockMvc.perform(get("/api/opcao-resp-aval-optativas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opcaoRespAvalOptativa.getId().intValue())))
            .andExpect(jsonPath("$.[*].opcao").value(hasItem(DEFAULT_OPCAO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getOpcaoRespAvalOptativa() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaRepository.saveAndFlush(opcaoRespAvalOptativa);

        // Get the opcaoRespAvalOptativa
        restOpcaoRespAvalOptativaMockMvc.perform(get("/api/opcao-resp-aval-optativas/{id}", opcaoRespAvalOptativa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(opcaoRespAvalOptativa.getId().intValue()))
            .andExpect(jsonPath("$.opcao").value(DEFAULT_OPCAO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOpcaoRespAvalOptativa() throws Exception {
        // Get the opcaoRespAvalOptativa
        restOpcaoRespAvalOptativaMockMvc.perform(get("/api/opcao-resp-aval-optativas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpcaoRespAvalOptativa() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaService.save(opcaoRespAvalOptativa);

        int databaseSizeBeforeUpdate = opcaoRespAvalOptativaRepository.findAll().size();

        // Update the opcaoRespAvalOptativa
        OpcaoRespAvalOptativa updatedOpcaoRespAvalOptativa = opcaoRespAvalOptativaRepository.findById(opcaoRespAvalOptativa.getId()).get();
        // Disconnect from session so that the updates on updatedOpcaoRespAvalOptativa are not directly saved in db
        em.detach(updatedOpcaoRespAvalOptativa);
        updatedOpcaoRespAvalOptativa
            .opcao(UPDATED_OPCAO)
            .obs(UPDATED_OBS);

        restOpcaoRespAvalOptativaMockMvc.perform(put("/api/opcao-resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOpcaoRespAvalOptativa)))
            .andExpect(status().isOk());

        // Validate the OpcaoRespAvalOptativa in the database
        List<OpcaoRespAvalOptativa> opcaoRespAvalOptativaList = opcaoRespAvalOptativaRepository.findAll();
        assertThat(opcaoRespAvalOptativaList).hasSize(databaseSizeBeforeUpdate);
        OpcaoRespAvalOptativa testOpcaoRespAvalOptativa = opcaoRespAvalOptativaList.get(opcaoRespAvalOptativaList.size() - 1);
        assertThat(testOpcaoRespAvalOptativa.getOpcao()).isEqualTo(UPDATED_OPCAO);
        assertThat(testOpcaoRespAvalOptativa.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingOpcaoRespAvalOptativa() throws Exception {
        int databaseSizeBeforeUpdate = opcaoRespAvalOptativaRepository.findAll().size();

        // Create the OpcaoRespAvalOptativa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcaoRespAvalOptativaMockMvc.perform(put("/api/opcao-resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAvalOptativa)))
            .andExpect(status().isBadRequest());

        // Validate the OpcaoRespAvalOptativa in the database
        List<OpcaoRespAvalOptativa> opcaoRespAvalOptativaList = opcaoRespAvalOptativaRepository.findAll();
        assertThat(opcaoRespAvalOptativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpcaoRespAvalOptativa() throws Exception {
        // Initialize the database
        opcaoRespAvalOptativaService.save(opcaoRespAvalOptativa);

        int databaseSizeBeforeDelete = opcaoRespAvalOptativaRepository.findAll().size();

        // Get the opcaoRespAvalOptativa
        restOpcaoRespAvalOptativaMockMvc.perform(delete("/api/opcao-resp-aval-optativas/{id}", opcaoRespAvalOptativa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OpcaoRespAvalOptativa> opcaoRespAvalOptativaList = opcaoRespAvalOptativaRepository.findAll();
        assertThat(opcaoRespAvalOptativaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcaoRespAvalOptativa.class);
        OpcaoRespAvalOptativa opcaoRespAvalOptativa1 = new OpcaoRespAvalOptativa();
        opcaoRespAvalOptativa1.setId(1L);
        OpcaoRespAvalOptativa opcaoRespAvalOptativa2 = new OpcaoRespAvalOptativa();
        opcaoRespAvalOptativa2.setId(opcaoRespAvalOptativa1.getId());
        assertThat(opcaoRespAvalOptativa1).isEqualTo(opcaoRespAvalOptativa2);
        opcaoRespAvalOptativa2.setId(2L);
        assertThat(opcaoRespAvalOptativa1).isNotEqualTo(opcaoRespAvalOptativa2);
        opcaoRespAvalOptativa1.setId(null);
        assertThat(opcaoRespAvalOptativa1).isNotEqualTo(opcaoRespAvalOptativa2);
    }
}
