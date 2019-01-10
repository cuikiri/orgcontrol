package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespAvalOptativa;
import br.com.jhisolution.ong.control.repository.RespAvalOptativaRepository;
import br.com.jhisolution.ong.control.service.RespAvalOptativaService;
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
 * Test class for the RespAvalOptativaResource REST controller.
 *
 * @see RespAvalOptativaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespAvalOptativaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RespAvalOptativaRepository respAvalOptativaRepository;

    @Autowired
    private RespAvalOptativaService respAvalOptativaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespAvalOptativaMockMvc;

    private RespAvalOptativa respAvalOptativa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespAvalOptativaResource respAvalOptativaResource = new RespAvalOptativaResource(respAvalOptativaService);
        this.restRespAvalOptativaMockMvc = MockMvcBuilders.standaloneSetup(respAvalOptativaResource)
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
    public static RespAvalOptativa createEntity(EntityManager em) {
        RespAvalOptativa respAvalOptativa = new RespAvalOptativa()
            .data(DEFAULT_DATA);
        return respAvalOptativa;
    }

    @Before
    public void initTest() {
        respAvalOptativa = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespAvalOptativa() throws Exception {
        int databaseSizeBeforeCreate = respAvalOptativaRepository.findAll().size();

        // Create the RespAvalOptativa
        restRespAvalOptativaMockMvc.perform(post("/api/resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativa)))
            .andExpect(status().isCreated());

        // Validate the RespAvalOptativa in the database
        List<RespAvalOptativa> respAvalOptativaList = respAvalOptativaRepository.findAll();
        assertThat(respAvalOptativaList).hasSize(databaseSizeBeforeCreate + 1);
        RespAvalOptativa testRespAvalOptativa = respAvalOptativaList.get(respAvalOptativaList.size() - 1);
        assertThat(testRespAvalOptativa.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createRespAvalOptativaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respAvalOptativaRepository.findAll().size();

        // Create the RespAvalOptativa with an existing ID
        respAvalOptativa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespAvalOptativaMockMvc.perform(post("/api/resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativa)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalOptativa in the database
        List<RespAvalOptativa> respAvalOptativaList = respAvalOptativaRepository.findAll();
        assertThat(respAvalOptativaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAvalOptativaRepository.findAll().size();
        // set the field null
        respAvalOptativa.setData(null);

        // Create the RespAvalOptativa, which fails.

        restRespAvalOptativaMockMvc.perform(post("/api/resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativa)))
            .andExpect(status().isBadRequest());

        List<RespAvalOptativa> respAvalOptativaList = respAvalOptativaRepository.findAll();
        assertThat(respAvalOptativaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespAvalOptativas() throws Exception {
        // Initialize the database
        respAvalOptativaRepository.saveAndFlush(respAvalOptativa);

        // Get all the respAvalOptativaList
        restRespAvalOptativaMockMvc.perform(get("/api/resp-aval-optativas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respAvalOptativa.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getRespAvalOptativa() throws Exception {
        // Initialize the database
        respAvalOptativaRepository.saveAndFlush(respAvalOptativa);

        // Get the respAvalOptativa
        restRespAvalOptativaMockMvc.perform(get("/api/resp-aval-optativas/{id}", respAvalOptativa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respAvalOptativa.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespAvalOptativa() throws Exception {
        // Get the respAvalOptativa
        restRespAvalOptativaMockMvc.perform(get("/api/resp-aval-optativas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespAvalOptativa() throws Exception {
        // Initialize the database
        respAvalOptativaService.save(respAvalOptativa);

        int databaseSizeBeforeUpdate = respAvalOptativaRepository.findAll().size();

        // Update the respAvalOptativa
        RespAvalOptativa updatedRespAvalOptativa = respAvalOptativaRepository.findById(respAvalOptativa.getId()).get();
        // Disconnect from session so that the updates on updatedRespAvalOptativa are not directly saved in db
        em.detach(updatedRespAvalOptativa);
        updatedRespAvalOptativa
            .data(UPDATED_DATA);

        restRespAvalOptativaMockMvc.perform(put("/api/resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespAvalOptativa)))
            .andExpect(status().isOk());

        // Validate the RespAvalOptativa in the database
        List<RespAvalOptativa> respAvalOptativaList = respAvalOptativaRepository.findAll();
        assertThat(respAvalOptativaList).hasSize(databaseSizeBeforeUpdate);
        RespAvalOptativa testRespAvalOptativa = respAvalOptativaList.get(respAvalOptativaList.size() - 1);
        assertThat(testRespAvalOptativa.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingRespAvalOptativa() throws Exception {
        int databaseSizeBeforeUpdate = respAvalOptativaRepository.findAll().size();

        // Create the RespAvalOptativa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespAvalOptativaMockMvc.perform(put("/api/resp-aval-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAvalOptativa)))
            .andExpect(status().isBadRequest());

        // Validate the RespAvalOptativa in the database
        List<RespAvalOptativa> respAvalOptativaList = respAvalOptativaRepository.findAll();
        assertThat(respAvalOptativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespAvalOptativa() throws Exception {
        // Initialize the database
        respAvalOptativaService.save(respAvalOptativa);

        int databaseSizeBeforeDelete = respAvalOptativaRepository.findAll().size();

        // Get the respAvalOptativa
        restRespAvalOptativaMockMvc.perform(delete("/api/resp-aval-optativas/{id}", respAvalOptativa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespAvalOptativa> respAvalOptativaList = respAvalOptativaRepository.findAll();
        assertThat(respAvalOptativaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespAvalOptativa.class);
        RespAvalOptativa respAvalOptativa1 = new RespAvalOptativa();
        respAvalOptativa1.setId(1L);
        RespAvalOptativa respAvalOptativa2 = new RespAvalOptativa();
        respAvalOptativa2.setId(respAvalOptativa1.getId());
        assertThat(respAvalOptativa1).isEqualTo(respAvalOptativa2);
        respAvalOptativa2.setId(2L);
        assertThat(respAvalOptativa1).isNotEqualTo(respAvalOptativa2);
        respAvalOptativa1.setId(null);
        assertThat(respAvalOptativa1).isNotEqualTo(respAvalOptativa2);
    }
}
