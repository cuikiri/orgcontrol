package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespAvalOptativaEconomica;
import br.com.jhisolution.ong.control.repository.RespAvalOptativaEconomicaRepository;
import br.com.jhisolution.ong.control.service.RespAvalOptativaEconomicaService;
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
 * Test class for the RespAvalOptativaEconomicaResource REST controller.
 *
 * @see RespAvalOptativaEconomicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespAvalOptativaEconomicaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RespAvalOptativaEconomicaRepository respAvalOptativaEconomicaRepository;

    @Autowired
    private RespAvalOptativaEconomicaService respAvalOptativaEconomicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespAvalOptativaEconomicaMockMvc;

    private RespAvalOptativaEconomica respAvalOptativaEconomica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespAvalOptativaEconomicaResource respAvalOptativaEconomicaResource = new RespAvalOptativaEconomicaResource(respAvalOptativaEconomicaService);
        this.restRespAvalOptativaEconomicaMockMvc = MockMvcBuilders.standaloneSetup(respAvalOptativaEconomicaResource)
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
    public static RespAvalOptativaEconomica createEntity(EntityManager em) {
        RespAvalOptativaEconomica respAvalOptativaEconomica = new RespAvalOptativaEconomica()
            .data(DEFAULT_DATA);
        return respAvalOptativaEconomica;
    }

    @Before
    public void initTest() {
        respAvalOptativaEconomica = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespAvalOptativaEconomica() throws Exception {
        int databaseSizeBeforeCreate = respAvalOptativaEconomicaRepository.findAll().size();

        // Create the RespAvalOptativaEconomica
        restRespAvalOptativaEconomicaMockMvc.perform(post("/api/resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativaEconomica)))
            .andExpect(status().isCreated());

        // Validate the RespAvalOptativaEconomica in the database
        List<RespAvalOptativaEconomica> respAvalOptativaEconomicaList = respAvalOptativaEconomicaRepository.findAll();
        assertThat(respAvalOptativaEconomicaList).hasSize(databaseSizeBeforeCreate + 1);
        RespAvalOptativaEconomica testRespAvalOptativaEconomica = respAvalOptativaEconomicaList.get(respAvalOptativaEconomicaList.size() - 1);
        assertThat(testRespAvalOptativaEconomica.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createRespAvalOptativaEconomicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respAvalOptativaEconomicaRepository.findAll().size();

        // Create the RespAvalOptativaEconomica with an existing ID
        respAvalOptativaEconomica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespAvalOptativaEconomicaMockMvc.perform(post("/api/resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativaEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalOptativaEconomica in the database
        List<RespAvalOptativaEconomica> respAvalOptativaEconomicaList = respAvalOptativaEconomicaRepository.findAll();
        assertThat(respAvalOptativaEconomicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAvalOptativaEconomicaRepository.findAll().size();
        // set the field null
        respAvalOptativaEconomica.setData(null);

        // Create the RespAvalOptativaEconomica, which fails.

        restRespAvalOptativaEconomicaMockMvc.perform(post("/api/resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativaEconomica)))
            .andExpect(status().isBadRequest());

        List<RespAvalOptativaEconomica> respAvalOptativaEconomicaList = respAvalOptativaEconomicaRepository.findAll();
        assertThat(respAvalOptativaEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespAvalOptativaEconomicas() throws Exception {
        // Initialize the database
        respAvalOptativaEconomicaRepository.saveAndFlush(respAvalOptativaEconomica);

        // Get all the respAvalOptativaEconomicaList
        restRespAvalOptativaEconomicaMockMvc.perform(get("/api/resp-aval-optativa-economicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respAvalOptativaEconomica.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getRespAvalOptativaEconomica() throws Exception {
        // Initialize the database
        respAvalOptativaEconomicaRepository.saveAndFlush(respAvalOptativaEconomica);

        // Get the respAvalOptativaEconomica
        restRespAvalOptativaEconomicaMockMvc.perform(get("/api/resp-aval-optativa-economicas/{id}", respAvalOptativaEconomica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respAvalOptativaEconomica.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespAvalOptativaEconomica() throws Exception {
        // Get the respAvalOptativaEconomica
        restRespAvalOptativaEconomicaMockMvc.perform(get("/api/resp-aval-optativa-economicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespAvalOptativaEconomica() throws Exception {
        // Initialize the database
        respAvalOptativaEconomicaService.save(respAvalOptativaEconomica);

        int databaseSizeBeforeUpdate = respAvalOptativaEconomicaRepository.findAll().size();

        // Update the respAvalOptativaEconomica
        RespAvalOptativaEconomica updatedRespAvalOptativaEconomica = respAvalOptativaEconomicaRepository.findById(respAvalOptativaEconomica.getId()).get();
        // Disconnect from session so that the updates on updatedRespAvalOptativaEconomica are not directly saved in db
        em.detach(updatedRespAvalOptativaEconomica);
        updatedRespAvalOptativaEconomica
            .data(UPDATED_DATA);

        restRespAvalOptativaEconomicaMockMvc.perform(put("/api/resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespAvalOptativaEconomica)))
            .andExpect(status().isOk());

        // Validate the RespAvalOptativaEconomica in the database
        List<RespAvalOptativaEconomica> respAvalOptativaEconomicaList = respAvalOptativaEconomicaRepository.findAll();
        assertThat(respAvalOptativaEconomicaList).hasSize(databaseSizeBeforeUpdate);
        RespAvalOptativaEconomica testRespAvalOptativaEconomica = respAvalOptativaEconomicaList.get(respAvalOptativaEconomicaList.size() - 1);
        assertThat(testRespAvalOptativaEconomica.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingRespAvalOptativaEconomica() throws Exception {
        int databaseSizeBeforeUpdate = respAvalOptativaEconomicaRepository.findAll().size();

        // Create the RespAvalOptativaEconomica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespAvalOptativaEconomicaMockMvc.perform(put("/api/resp-aval-optativa-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativaEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalOptativaEconomica in the database
        List<RespAvalOptativaEconomica> respAvalOptativaEconomicaList = respAvalOptativaEconomicaRepository.findAll();
        assertThat(respAvalOptativaEconomicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespAvalOptativaEconomica() throws Exception {
        // Initialize the database
        respAvalOptativaEconomicaService.save(respAvalOptativaEconomica);

        int databaseSizeBeforeDelete = respAvalOptativaEconomicaRepository.findAll().size();

        // Get the respAvalOptativaEconomica
        restRespAvalOptativaEconomicaMockMvc.perform(delete("/api/resp-aval-optativa-economicas/{id}", respAvalOptativaEconomica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespAvalOptativaEconomica> respAvalOptativaEconomicaList = respAvalOptativaEconomicaRepository.findAll();
        assertThat(respAvalOptativaEconomicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespAvalOptativaEconomica.class);
        RespAvalOptativaEconomica respAvalOptativaEconomica1 = new RespAvalOptativaEconomica();
        respAvalOptativaEconomica1.setId(1L);
        RespAvalOptativaEconomica respAvalOptativaEconomica2 = new RespAvalOptativaEconomica();
        respAvalOptativaEconomica2.setId(respAvalOptativaEconomica1.getId());
        assertThat(respAvalOptativaEconomica1).isEqualTo(respAvalOptativaEconomica2);
        respAvalOptativaEconomica2.setId(2L);
        assertThat(respAvalOptativaEconomica1).isNotEqualTo(respAvalOptativaEconomica2);
        respAvalOptativaEconomica1.setId(null);
        assertThat(respAvalOptativaEconomica1).isNotEqualTo(respAvalOptativaEconomica2);
    }
}
