package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespostaAvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.RespostaAvaliacaoEconomicaRepository;
import br.com.jhisolution.ong.control.service.RespostaAvaliacaoEconomicaService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RespostaAvaliacaoEconomicaResource REST controller.
 *
 * @see RespostaAvaliacaoEconomicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespostaAvaliacaoEconomicaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private RespostaAvaliacaoEconomicaRepository respostaAvaliacaoEconomicaRepository;

    @Autowired
    private RespostaAvaliacaoEconomicaService respostaAvaliacaoEconomicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespostaAvaliacaoEconomicaMockMvc;

    private RespostaAvaliacaoEconomica respostaAvaliacaoEconomica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespostaAvaliacaoEconomicaResource respostaAvaliacaoEconomicaResource = new RespostaAvaliacaoEconomicaResource(respostaAvaliacaoEconomicaService);
        this.restRespostaAvaliacaoEconomicaMockMvc = MockMvcBuilders.standaloneSetup(respostaAvaliacaoEconomicaResource)
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
    public static RespostaAvaliacaoEconomica createEntity(EntityManager em) {
        RespostaAvaliacaoEconomica respostaAvaliacaoEconomica = new RespostaAvaliacaoEconomica()
            .data(DEFAULT_DATA)
            .obs(DEFAULT_OBS);
        return respostaAvaliacaoEconomica;
    }

    @Before
    public void initTest() {
        respostaAvaliacaoEconomica = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespostaAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeCreate = respostaAvaliacaoEconomicaRepository.findAll().size();

        // Create the RespostaAvaliacaoEconomica
        restRespostaAvaliacaoEconomicaMockMvc.perform(post("/api/resposta-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacaoEconomica)))
            .andExpect(status().isCreated());

        // Validate the RespostaAvaliacaoEconomica in the database
        List<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicaList = respostaAvaliacaoEconomicaRepository.findAll();
        assertThat(respostaAvaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate + 1);
        RespostaAvaliacaoEconomica testRespostaAvaliacaoEconomica = respostaAvaliacaoEconomicaList.get(respostaAvaliacaoEconomicaList.size() - 1);
        assertThat(testRespostaAvaliacaoEconomica.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testRespostaAvaliacaoEconomica.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createRespostaAvaliacaoEconomicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respostaAvaliacaoEconomicaRepository.findAll().size();

        // Create the RespostaAvaliacaoEconomica with an existing ID
        respostaAvaliacaoEconomica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespostaAvaliacaoEconomicaMockMvc.perform(post("/api/resposta-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the RespostaAvaliacaoEconomica in the database
        List<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicaList = respostaAvaliacaoEconomicaRepository.findAll();
        assertThat(respostaAvaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respostaAvaliacaoEconomicaRepository.findAll().size();
        // set the field null
        respostaAvaliacaoEconomica.setData(null);

        // Create the RespostaAvaliacaoEconomica, which fails.

        restRespostaAvaliacaoEconomicaMockMvc.perform(post("/api/resposta-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicaList = respostaAvaliacaoEconomicaRepository.findAll();
        assertThat(respostaAvaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespostaAvaliacaoEconomicas() throws Exception {
        // Initialize the database
        respostaAvaliacaoEconomicaRepository.saveAndFlush(respostaAvaliacaoEconomica);

        // Get all the respostaAvaliacaoEconomicaList
        restRespostaAvaliacaoEconomicaMockMvc.perform(get("/api/resposta-avaliacao-economicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respostaAvaliacaoEconomica.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getRespostaAvaliacaoEconomica() throws Exception {
        // Initialize the database
        respostaAvaliacaoEconomicaRepository.saveAndFlush(respostaAvaliacaoEconomica);

        // Get the respostaAvaliacaoEconomica
        restRespostaAvaliacaoEconomicaMockMvc.perform(get("/api/resposta-avaliacao-economicas/{id}", respostaAvaliacaoEconomica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respostaAvaliacaoEconomica.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespostaAvaliacaoEconomica() throws Exception {
        // Get the respostaAvaliacaoEconomica
        restRespostaAvaliacaoEconomicaMockMvc.perform(get("/api/resposta-avaliacao-economicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespostaAvaliacaoEconomica() throws Exception {
        // Initialize the database
        respostaAvaliacaoEconomicaService.save(respostaAvaliacaoEconomica);

        int databaseSizeBeforeUpdate = respostaAvaliacaoEconomicaRepository.findAll().size();

        // Update the respostaAvaliacaoEconomica
        RespostaAvaliacaoEconomica updatedRespostaAvaliacaoEconomica = respostaAvaliacaoEconomicaRepository.findById(respostaAvaliacaoEconomica.getId()).get();
        // Disconnect from session so that the updates on updatedRespostaAvaliacaoEconomica are not directly saved in db
        em.detach(updatedRespostaAvaliacaoEconomica);
        updatedRespostaAvaliacaoEconomica
            .data(UPDATED_DATA)
            .obs(UPDATED_OBS);

        restRespostaAvaliacaoEconomicaMockMvc.perform(put("/api/resposta-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespostaAvaliacaoEconomica)))
            .andExpect(status().isOk());

        // Validate the RespostaAvaliacaoEconomica in the database
        List<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicaList = respostaAvaliacaoEconomicaRepository.findAll();
        assertThat(respostaAvaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
        RespostaAvaliacaoEconomica testRespostaAvaliacaoEconomica = respostaAvaliacaoEconomicaList.get(respostaAvaliacaoEconomicaList.size() - 1);
        assertThat(testRespostaAvaliacaoEconomica.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testRespostaAvaliacaoEconomica.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingRespostaAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeUpdate = respostaAvaliacaoEconomicaRepository.findAll().size();

        // Create the RespostaAvaliacaoEconomica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespostaAvaliacaoEconomicaMockMvc.perform(put("/api/resposta-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the RespostaAvaliacaoEconomica in the database
        List<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicaList = respostaAvaliacaoEconomicaRepository.findAll();
        assertThat(respostaAvaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespostaAvaliacaoEconomica() throws Exception {
        // Initialize the database
        respostaAvaliacaoEconomicaService.save(respostaAvaliacaoEconomica);

        int databaseSizeBeforeDelete = respostaAvaliacaoEconomicaRepository.findAll().size();

        // Get the respostaAvaliacaoEconomica
        restRespostaAvaliacaoEconomicaMockMvc.perform(delete("/api/resposta-avaliacao-economicas/{id}", respostaAvaliacaoEconomica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicaList = respostaAvaliacaoEconomicaRepository.findAll();
        assertThat(respostaAvaliacaoEconomicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespostaAvaliacaoEconomica.class);
        RespostaAvaliacaoEconomica respostaAvaliacaoEconomica1 = new RespostaAvaliacaoEconomica();
        respostaAvaliacaoEconomica1.setId(1L);
        RespostaAvaliacaoEconomica respostaAvaliacaoEconomica2 = new RespostaAvaliacaoEconomica();
        respostaAvaliacaoEconomica2.setId(respostaAvaliacaoEconomica1.getId());
        assertThat(respostaAvaliacaoEconomica1).isEqualTo(respostaAvaliacaoEconomica2);
        respostaAvaliacaoEconomica2.setId(2L);
        assertThat(respostaAvaliacaoEconomica1).isNotEqualTo(respostaAvaliacaoEconomica2);
        respostaAvaliacaoEconomica1.setId(null);
        assertThat(respostaAvaliacaoEconomica1).isNotEqualTo(respostaAvaliacaoEconomica2);
    }
}
