package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespAvalDescritivaEconomica;
import br.com.jhisolution.ong.control.repository.RespAvalDescritivaEconomicaRepository;
import br.com.jhisolution.ong.control.service.RespAvalDescritivaEconomicaService;
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
 * Test class for the RespAvalDescritivaEconomicaResource REST controller.
 *
 * @see RespAvalDescritivaEconomicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespAvalDescritivaEconomicaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_RESPOSTA = "BBBBBBBBBB";

    @Autowired
    private RespAvalDescritivaEconomicaRepository respAvalDescritivaEconomicaRepository;

    @Autowired
    private RespAvalDescritivaEconomicaService respAvalDescritivaEconomicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespAvalDescritivaEconomicaMockMvc;

    private RespAvalDescritivaEconomica respAvalDescritivaEconomica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespAvalDescritivaEconomicaResource respAvalDescritivaEconomicaResource = new RespAvalDescritivaEconomicaResource(respAvalDescritivaEconomicaService);
        this.restRespAvalDescritivaEconomicaMockMvc = MockMvcBuilders.standaloneSetup(respAvalDescritivaEconomicaResource)
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
    public static RespAvalDescritivaEconomica createEntity(EntityManager em) {
        RespAvalDescritivaEconomica respAvalDescritivaEconomica = new RespAvalDescritivaEconomica()
            .data(DEFAULT_DATA)
            .resposta(DEFAULT_RESPOSTA);
        return respAvalDescritivaEconomica;
    }

    @Before
    public void initTest() {
        respAvalDescritivaEconomica = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespAvalDescritivaEconomica() throws Exception {
        int databaseSizeBeforeCreate = respAvalDescritivaEconomicaRepository.findAll().size();

        // Create the RespAvalDescritivaEconomica
        restRespAvalDescritivaEconomicaMockMvc.perform(post("/api/resp-aval-descritiva-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritivaEconomica)))
            .andExpect(status().isCreated());

        // Validate the RespAvalDescritivaEconomica in the database
        List<RespAvalDescritivaEconomica> respAvalDescritivaEconomicaList = respAvalDescritivaEconomicaRepository.findAll();
        assertThat(respAvalDescritivaEconomicaList).hasSize(databaseSizeBeforeCreate + 1);
        RespAvalDescritivaEconomica testRespAvalDescritivaEconomica = respAvalDescritivaEconomicaList.get(respAvalDescritivaEconomicaList.size() - 1);
        assertThat(testRespAvalDescritivaEconomica.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testRespAvalDescritivaEconomica.getResposta()).isEqualTo(DEFAULT_RESPOSTA);
    }

    @Test
    @Transactional
    public void createRespAvalDescritivaEconomicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respAvalDescritivaEconomicaRepository.findAll().size();

        // Create the RespAvalDescritivaEconomica with an existing ID
        respAvalDescritivaEconomica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespAvalDescritivaEconomicaMockMvc.perform(post("/api/resp-aval-descritiva-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritivaEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalDescritivaEconomica in the database
        List<RespAvalDescritivaEconomica> respAvalDescritivaEconomicaList = respAvalDescritivaEconomicaRepository.findAll();
        assertThat(respAvalDescritivaEconomicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAvalDescritivaEconomicaRepository.findAll().size();
        // set the field null
        respAvalDescritivaEconomica.setData(null);

        // Create the RespAvalDescritivaEconomica, which fails.

        restRespAvalDescritivaEconomicaMockMvc.perform(post("/api/resp-aval-descritiva-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritivaEconomica)))
            .andExpect(status().isBadRequest());

        List<RespAvalDescritivaEconomica> respAvalDescritivaEconomicaList = respAvalDescritivaEconomicaRepository.findAll();
        assertThat(respAvalDescritivaEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAvalDescritivaEconomicaRepository.findAll().size();
        // set the field null
        respAvalDescritivaEconomica.setResposta(null);

        // Create the RespAvalDescritivaEconomica, which fails.

        restRespAvalDescritivaEconomicaMockMvc.perform(post("/api/resp-aval-descritiva-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritivaEconomica)))
            .andExpect(status().isBadRequest());

        List<RespAvalDescritivaEconomica> respAvalDescritivaEconomicaList = respAvalDescritivaEconomicaRepository.findAll();
        assertThat(respAvalDescritivaEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespAvalDescritivaEconomicas() throws Exception {
        // Initialize the database
        respAvalDescritivaEconomicaRepository.saveAndFlush(respAvalDescritivaEconomica);

        // Get all the respAvalDescritivaEconomicaList
        restRespAvalDescritivaEconomicaMockMvc.perform(get("/api/resp-aval-descritiva-economicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respAvalDescritivaEconomica.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].resposta").value(hasItem(DEFAULT_RESPOSTA.toString())));
    }
    
    @Test
    @Transactional
    public void getRespAvalDescritivaEconomica() throws Exception {
        // Initialize the database
        respAvalDescritivaEconomicaRepository.saveAndFlush(respAvalDescritivaEconomica);

        // Get the respAvalDescritivaEconomica
        restRespAvalDescritivaEconomicaMockMvc.perform(get("/api/resp-aval-descritiva-economicas/{id}", respAvalDescritivaEconomica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respAvalDescritivaEconomica.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.resposta").value(DEFAULT_RESPOSTA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespAvalDescritivaEconomica() throws Exception {
        // Get the respAvalDescritivaEconomica
        restRespAvalDescritivaEconomicaMockMvc.perform(get("/api/resp-aval-descritiva-economicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespAvalDescritivaEconomica() throws Exception {
        // Initialize the database
        respAvalDescritivaEconomicaService.save(respAvalDescritivaEconomica);

        int databaseSizeBeforeUpdate = respAvalDescritivaEconomicaRepository.findAll().size();

        // Update the respAvalDescritivaEconomica
        RespAvalDescritivaEconomica updatedRespAvalDescritivaEconomica = respAvalDescritivaEconomicaRepository.findById(respAvalDescritivaEconomica.getId()).get();
        // Disconnect from session so that the updates on updatedRespAvalDescritivaEconomica are not directly saved in db
        em.detach(updatedRespAvalDescritivaEconomica);
        updatedRespAvalDescritivaEconomica
            .data(UPDATED_DATA)
            .resposta(UPDATED_RESPOSTA);

        restRespAvalDescritivaEconomicaMockMvc.perform(put("/api/resp-aval-descritiva-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespAvalDescritivaEconomica)))
            .andExpect(status().isOk());

        // Validate the RespAvalDescritivaEconomica in the database
        List<RespAvalDescritivaEconomica> respAvalDescritivaEconomicaList = respAvalDescritivaEconomicaRepository.findAll();
        assertThat(respAvalDescritivaEconomicaList).hasSize(databaseSizeBeforeUpdate);
        RespAvalDescritivaEconomica testRespAvalDescritivaEconomica = respAvalDescritivaEconomicaList.get(respAvalDescritivaEconomicaList.size() - 1);
        assertThat(testRespAvalDescritivaEconomica.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testRespAvalDescritivaEconomica.getResposta()).isEqualTo(UPDATED_RESPOSTA);
    }

    @Test
    @Transactional
    public void updateNonExistingRespAvalDescritivaEconomica() throws Exception {
        int databaseSizeBeforeUpdate = respAvalDescritivaEconomicaRepository.findAll().size();

        // Create the RespAvalDescritivaEconomica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespAvalDescritivaEconomicaMockMvc.perform(put("/api/resp-aval-descritiva-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritivaEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalDescritivaEconomica in the database
        List<RespAvalDescritivaEconomica> respAvalDescritivaEconomicaList = respAvalDescritivaEconomicaRepository.findAll();
        assertThat(respAvalDescritivaEconomicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespAvalDescritivaEconomica() throws Exception {
        // Initialize the database
        respAvalDescritivaEconomicaService.save(respAvalDescritivaEconomica);

        int databaseSizeBeforeDelete = respAvalDescritivaEconomicaRepository.findAll().size();

        // Get the respAvalDescritivaEconomica
        restRespAvalDescritivaEconomicaMockMvc.perform(delete("/api/resp-aval-descritiva-economicas/{id}", respAvalDescritivaEconomica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespAvalDescritivaEconomica> respAvalDescritivaEconomicaList = respAvalDescritivaEconomicaRepository.findAll();
        assertThat(respAvalDescritivaEconomicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespAvalDescritivaEconomica.class);
        RespAvalDescritivaEconomica respAvalDescritivaEconomica1 = new RespAvalDescritivaEconomica();
        respAvalDescritivaEconomica1.setId(1L);
        RespAvalDescritivaEconomica respAvalDescritivaEconomica2 = new RespAvalDescritivaEconomica();
        respAvalDescritivaEconomica2.setId(respAvalDescritivaEconomica1.getId());
        assertThat(respAvalDescritivaEconomica1).isEqualTo(respAvalDescritivaEconomica2);
        respAvalDescritivaEconomica2.setId(2L);
        assertThat(respAvalDescritivaEconomica1).isNotEqualTo(respAvalDescritivaEconomica2);
        respAvalDescritivaEconomica1.setId(null);
        assertThat(respAvalDescritivaEconomica1).isNotEqualTo(respAvalDescritivaEconomica2);
    }
}
