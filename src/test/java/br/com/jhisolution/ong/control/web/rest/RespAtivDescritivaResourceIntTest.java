package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespAtivDescritiva;
import br.com.jhisolution.ong.control.repository.RespAtivDescritivaRepository;
import br.com.jhisolution.ong.control.service.RespAtivDescritivaService;
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
 * Test class for the RespAtivDescritivaResource REST controller.
 *
 * @see RespAtivDescritivaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespAtivDescritivaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_RESPOSTA = "BBBBBBBBBB";

    @Autowired
    private RespAtivDescritivaRepository respAtivDescritivaRepository;

    @Autowired
    private RespAtivDescritivaService respAtivDescritivaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespAtivDescritivaMockMvc;

    private RespAtivDescritiva respAtivDescritiva;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespAtivDescritivaResource respAtivDescritivaResource = new RespAtivDescritivaResource(respAtivDescritivaService);
        this.restRespAtivDescritivaMockMvc = MockMvcBuilders.standaloneSetup(respAtivDescritivaResource)
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
    public static RespAtivDescritiva createEntity(EntityManager em) {
        RespAtivDescritiva respAtivDescritiva = new RespAtivDescritiva()
            .data(DEFAULT_DATA)
            .resposta(DEFAULT_RESPOSTA);
        return respAtivDescritiva;
    }

    @Before
    public void initTest() {
        respAtivDescritiva = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespAtivDescritiva() throws Exception {
        int databaseSizeBeforeCreate = respAtivDescritivaRepository.findAll().size();

        // Create the RespAtivDescritiva
        restRespAtivDescritivaMockMvc.perform(post("/api/resp-ativ-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivDescritiva)))
            .andExpect(status().isCreated());

        // Validate the RespAtivDescritiva in the database
        List<RespAtivDescritiva> respAtivDescritivaList = respAtivDescritivaRepository.findAll();
        assertThat(respAtivDescritivaList).hasSize(databaseSizeBeforeCreate + 1);
        RespAtivDescritiva testRespAtivDescritiva = respAtivDescritivaList.get(respAtivDescritivaList.size() - 1);
        assertThat(testRespAtivDescritiva.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testRespAtivDescritiva.getResposta()).isEqualTo(DEFAULT_RESPOSTA);
    }

    @Test
    @Transactional
    public void createRespAtivDescritivaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respAtivDescritivaRepository.findAll().size();

        // Create the RespAtivDescritiva with an existing ID
        respAtivDescritiva.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespAtivDescritivaMockMvc.perform(post("/api/resp-ativ-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivDescritiva)))
            .andExpect(status().isBadRequest());

        // Validate the RespAtivDescritiva in the database
        List<RespAtivDescritiva> respAtivDescritivaList = respAtivDescritivaRepository.findAll();
        assertThat(respAtivDescritivaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAtivDescritivaRepository.findAll().size();
        // set the field null
        respAtivDescritiva.setData(null);

        // Create the RespAtivDescritiva, which fails.

        restRespAtivDescritivaMockMvc.perform(post("/api/resp-ativ-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivDescritiva)))
            .andExpect(status().isBadRequest());

        List<RespAtivDescritiva> respAtivDescritivaList = respAtivDescritivaRepository.findAll();
        assertThat(respAtivDescritivaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAtivDescritivaRepository.findAll().size();
        // set the field null
        respAtivDescritiva.setResposta(null);

        // Create the RespAtivDescritiva, which fails.

        restRespAtivDescritivaMockMvc.perform(post("/api/resp-ativ-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivDescritiva)))
            .andExpect(status().isBadRequest());

        List<RespAtivDescritiva> respAtivDescritivaList = respAtivDescritivaRepository.findAll();
        assertThat(respAtivDescritivaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespAtivDescritivas() throws Exception {
        // Initialize the database
        respAtivDescritivaRepository.saveAndFlush(respAtivDescritiva);

        // Get all the respAtivDescritivaList
        restRespAtivDescritivaMockMvc.perform(get("/api/resp-ativ-descritivas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respAtivDescritiva.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].resposta").value(hasItem(DEFAULT_RESPOSTA.toString())));
    }
    
    @Test
    @Transactional
    public void getRespAtivDescritiva() throws Exception {
        // Initialize the database
        respAtivDescritivaRepository.saveAndFlush(respAtivDescritiva);

        // Get the respAtivDescritiva
        restRespAtivDescritivaMockMvc.perform(get("/api/resp-ativ-descritivas/{id}", respAtivDescritiva.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respAtivDescritiva.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.resposta").value(DEFAULT_RESPOSTA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespAtivDescritiva() throws Exception {
        // Get the respAtivDescritiva
        restRespAtivDescritivaMockMvc.perform(get("/api/resp-ativ-descritivas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespAtivDescritiva() throws Exception {
        // Initialize the database
        respAtivDescritivaService.save(respAtivDescritiva);

        int databaseSizeBeforeUpdate = respAtivDescritivaRepository.findAll().size();

        // Update the respAtivDescritiva
        RespAtivDescritiva updatedRespAtivDescritiva = respAtivDescritivaRepository.findById(respAtivDescritiva.getId()).get();
        // Disconnect from session so that the updates on updatedRespAtivDescritiva are not directly saved in db
        em.detach(updatedRespAtivDescritiva);
        updatedRespAtivDescritiva
            .data(UPDATED_DATA)
            .resposta(UPDATED_RESPOSTA);

        restRespAtivDescritivaMockMvc.perform(put("/api/resp-ativ-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespAtivDescritiva)))
            .andExpect(status().isOk());

        // Validate the RespAtivDescritiva in the database
        List<RespAtivDescritiva> respAtivDescritivaList = respAtivDescritivaRepository.findAll();
        assertThat(respAtivDescritivaList).hasSize(databaseSizeBeforeUpdate);
        RespAtivDescritiva testRespAtivDescritiva = respAtivDescritivaList.get(respAtivDescritivaList.size() - 1);
        assertThat(testRespAtivDescritiva.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testRespAtivDescritiva.getResposta()).isEqualTo(UPDATED_RESPOSTA);
    }

    @Test
    @Transactional
    public void updateNonExistingRespAtivDescritiva() throws Exception {
        int databaseSizeBeforeUpdate = respAtivDescritivaRepository.findAll().size();

        // Create the RespAtivDescritiva

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespAtivDescritivaMockMvc.perform(put("/api/resp-ativ-descritivas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivDescritiva)))
            .andExpect(status().isBadRequest());

        // Validate the RespAtivDescritiva in the database
        List<RespAtivDescritiva> respAtivDescritivaList = respAtivDescritivaRepository.findAll();
        assertThat(respAtivDescritivaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespAtivDescritiva() throws Exception {
        // Initialize the database
        respAtivDescritivaService.save(respAtivDescritiva);

        int databaseSizeBeforeDelete = respAtivDescritivaRepository.findAll().size();

        // Get the respAtivDescritiva
        restRespAtivDescritivaMockMvc.perform(delete("/api/resp-ativ-descritivas/{id}", respAtivDescritiva.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespAtivDescritiva> respAtivDescritivaList = respAtivDescritivaRepository.findAll();
        assertThat(respAtivDescritivaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespAtivDescritiva.class);
        RespAtivDescritiva respAtivDescritiva1 = new RespAtivDescritiva();
        respAtivDescritiva1.setId(1L);
        RespAtivDescritiva respAtivDescritiva2 = new RespAtivDescritiva();
        respAtivDescritiva2.setId(respAtivDescritiva1.getId());
        assertThat(respAtivDescritiva1).isEqualTo(respAtivDescritiva2);
        respAtivDescritiva2.setId(2L);
        assertThat(respAtivDescritiva1).isNotEqualTo(respAtivDescritiva2);
        respAtivDescritiva1.setId(null);
        assertThat(respAtivDescritiva1).isNotEqualTo(respAtivDescritiva2);
    }
}
