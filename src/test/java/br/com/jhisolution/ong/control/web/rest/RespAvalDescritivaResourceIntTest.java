package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespAvalDescritiva;
import br.com.jhisolution.ong.control.repository.RespAvalDescritivaRepository;
import br.com.jhisolution.ong.control.service.RespAvalDescritivaService;
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
 * Test class for the RespAvalDescritivaResource REST controller.
 *
 * @see RespAvalDescritivaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespAvalDescritivaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_RESPOSTA = "BBBBBBBBBB";

    @Autowired
    private RespAvalDescritivaRepository respAvalDescritivaRepository;

    @Autowired
    private RespAvalDescritivaService respAvalDescritivaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespAvalDescritivaMockMvc;

    private RespAvalDescritiva respAvalDescritiva;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespAvalDescritivaResource respAvalDescritivaResource = new RespAvalDescritivaResource(respAvalDescritivaService);
        this.restRespAvalDescritivaMockMvc = MockMvcBuilders.standaloneSetup(respAvalDescritivaResource)
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
    public static RespAvalDescritiva createEntity(EntityManager em) {
        RespAvalDescritiva respAvalDescritiva = new RespAvalDescritiva()
            .data(DEFAULT_DATA)
            .resposta(DEFAULT_RESPOSTA);
        return respAvalDescritiva;
    }

    @Before
    public void initTest() {
        respAvalDescritiva = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespAvalDescritiva() throws Exception {
        int databaseSizeBeforeCreate = respAvalDescritivaRepository.findAll().size();

        // Create the RespAvalDescritiva
        restRespAvalDescritivaMockMvc.perform(post("/api/resp-aval-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritiva)))
            .andExpect(status().isCreated());

        // Validate the RespAvalDescritiva in the database
        List<RespAvalDescritiva> respAvalDescritivaList = respAvalDescritivaRepository.findAll();
        assertThat(respAvalDescritivaList).hasSize(databaseSizeBeforeCreate + 1);
        RespAvalDescritiva testRespAvalDescritiva = respAvalDescritivaList.get(respAvalDescritivaList.size() - 1);
        assertThat(testRespAvalDescritiva.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testRespAvalDescritiva.getResposta()).isEqualTo(DEFAULT_RESPOSTA);
    }

    @Test
    @Transactional
    public void createRespAvalDescritivaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respAvalDescritivaRepository.findAll().size();

        // Create the RespAvalDescritiva with an existing ID
        respAvalDescritiva.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespAvalDescritivaMockMvc.perform(post("/api/resp-aval-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritiva)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalDescritiva in the database
        List<RespAvalDescritiva> respAvalDescritivaList = respAvalDescritivaRepository.findAll();
        assertThat(respAvalDescritivaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAvalDescritivaRepository.findAll().size();
        // set the field null
        respAvalDescritiva.setData(null);

        // Create the RespAvalDescritiva, which fails.

        restRespAvalDescritivaMockMvc.perform(post("/api/resp-aval-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritiva)))
            .andExpect(status().isBadRequest());

        List<RespAvalDescritiva> respAvalDescritivaList = respAvalDescritivaRepository.findAll();
        assertThat(respAvalDescritivaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAvalDescritivaRepository.findAll().size();
        // set the field null
        respAvalDescritiva.setResposta(null);

        // Create the RespAvalDescritiva, which fails.

        restRespAvalDescritivaMockMvc.perform(post("/api/resp-aval-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritiva)))
            .andExpect(status().isBadRequest());

        List<RespAvalDescritiva> respAvalDescritivaList = respAvalDescritivaRepository.findAll();
        assertThat(respAvalDescritivaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespAvalDescritivas() throws Exception {
        // Initialize the database
        respAvalDescritivaRepository.saveAndFlush(respAvalDescritiva);

        // Get all the respAvalDescritivaList
        restRespAvalDescritivaMockMvc.perform(get("/api/resp-aval-descritivas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respAvalDescritiva.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].resposta").value(hasItem(DEFAULT_RESPOSTA.toString())));
    }
    
    @Test
    @Transactional
    public void getRespAvalDescritiva() throws Exception {
        // Initialize the database
        respAvalDescritivaRepository.saveAndFlush(respAvalDescritiva);

        // Get the respAvalDescritiva
        restRespAvalDescritivaMockMvc.perform(get("/api/resp-aval-descritivas/{id}", respAvalDescritiva.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respAvalDescritiva.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.resposta").value(DEFAULT_RESPOSTA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespAvalDescritiva() throws Exception {
        // Get the respAvalDescritiva
        restRespAvalDescritivaMockMvc.perform(get("/api/resp-aval-descritivas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespAvalDescritiva() throws Exception {
        // Initialize the database
        respAvalDescritivaService.save(respAvalDescritiva);

        int databaseSizeBeforeUpdate = respAvalDescritivaRepository.findAll().size();

        // Update the respAvalDescritiva
        RespAvalDescritiva updatedRespAvalDescritiva = respAvalDescritivaRepository.findById(respAvalDescritiva.getId()).get();
        // Disconnect from session so that the updates on updatedRespAvalDescritiva are not directly saved in db
        em.detach(updatedRespAvalDescritiva);
        updatedRespAvalDescritiva
            .data(UPDATED_DATA)
            .resposta(UPDATED_RESPOSTA);

        restRespAvalDescritivaMockMvc.perform(put("/api/resp-aval-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespAvalDescritiva)))
            .andExpect(status().isOk());

        // Validate the RespAvalDescritiva in the database
        List<RespAvalDescritiva> respAvalDescritivaList = respAvalDescritivaRepository.findAll();
        assertThat(respAvalDescritivaList).hasSize(databaseSizeBeforeUpdate);
        RespAvalDescritiva testRespAvalDescritiva = respAvalDescritivaList.get(respAvalDescritivaList.size() - 1);
        assertThat(testRespAvalDescritiva.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testRespAvalDescritiva.getResposta()).isEqualTo(UPDATED_RESPOSTA);
    }

    @Test
    @Transactional
    public void updateNonExistingRespAvalDescritiva() throws Exception {
        int databaseSizeBeforeUpdate = respAvalDescritivaRepository.findAll().size();

        // Create the RespAvalDescritiva

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespAvalDescritivaMockMvc.perform(put("/api/resp-aval-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalDescritiva)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalDescritiva in the database
        List<RespAvalDescritiva> respAvalDescritivaList = respAvalDescritivaRepository.findAll();
        assertThat(respAvalDescritivaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespAvalDescritiva() throws Exception {
        // Initialize the database
        respAvalDescritivaService.save(respAvalDescritiva);

        int databaseSizeBeforeDelete = respAvalDescritivaRepository.findAll().size();

        // Get the respAvalDescritiva
        restRespAvalDescritivaMockMvc.perform(delete("/api/resp-aval-descritivas/{id}", respAvalDescritiva.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespAvalDescritiva> respAvalDescritivaList = respAvalDescritivaRepository.findAll();
        assertThat(respAvalDescritivaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespAvalDescritiva.class);
        RespAvalDescritiva respAvalDescritiva1 = new RespAvalDescritiva();
        respAvalDescritiva1.setId(1L);
        RespAvalDescritiva respAvalDescritiva2 = new RespAvalDescritiva();
        respAvalDescritiva2.setId(respAvalDescritiva1.getId());
        assertThat(respAvalDescritiva1).isEqualTo(respAvalDescritiva2);
        respAvalDescritiva2.setId(2L);
        assertThat(respAvalDescritiva1).isNotEqualTo(respAvalDescritiva2);
        respAvalDescritiva1.setId(null);
        assertThat(respAvalDescritiva1).isNotEqualTo(respAvalDescritiva2);
    }
}
