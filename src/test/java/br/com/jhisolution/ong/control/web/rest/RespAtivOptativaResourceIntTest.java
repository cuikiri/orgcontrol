package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespAtivOptativa;
import br.com.jhisolution.ong.control.repository.RespAtivOptativaRepository;
import br.com.jhisolution.ong.control.service.RespAtivOptativaService;
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
 * Test class for the RespAtivOptativaResource REST controller.
 *
 * @see RespAtivOptativaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespAtivOptativaResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RespAtivOptativaRepository respAtivOptativaRepository;

    @Autowired
    private RespAtivOptativaService respAtivOptativaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespAtivOptativaMockMvc;

    private RespAtivOptativa respAtivOptativa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespAtivOptativaResource respAtivOptativaResource = new RespAtivOptativaResource(respAtivOptativaService);
        this.restRespAtivOptativaMockMvc = MockMvcBuilders.standaloneSetup(respAtivOptativaResource)
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
    public static RespAtivOptativa createEntity(EntityManager em) {
        RespAtivOptativa respAtivOptativa = new RespAtivOptativa()
            .data(DEFAULT_DATA);
        return respAtivOptativa;
    }

    @Before
    public void initTest() {
        respAtivOptativa = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespAtivOptativa() throws Exception {
        int databaseSizeBeforeCreate = respAtivOptativaRepository.findAll().size();

        // Create the RespAtivOptativa
        restRespAtivOptativaMockMvc.perform(post("/api/resp-ativ-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivOptativa)))
            .andExpect(status().isCreated());

        // Validate the RespAtivOptativa in the database
        List<RespAtivOptativa> respAtivOptativaList = respAtivOptativaRepository.findAll();
        assertThat(respAtivOptativaList).hasSize(databaseSizeBeforeCreate + 1);
        RespAtivOptativa testRespAtivOptativa = respAtivOptativaList.get(respAtivOptativaList.size() - 1);
        assertThat(testRespAtivOptativa.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createRespAtivOptativaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respAtivOptativaRepository.findAll().size();

        // Create the RespAtivOptativa with an existing ID
        respAtivOptativa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespAtivOptativaMockMvc.perform(post("/api/resp-ativ-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivOptativa)))
            .andExpect(status().isBadRequest());

        // Validate the RespAtivOptativa in the database
        List<RespAtivOptativa> respAtivOptativaList = respAtivOptativaRepository.findAll();
        assertThat(respAtivOptativaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respAtivOptativaRepository.findAll().size();
        // set the field null
        respAtivOptativa.setData(null);

        // Create the RespAtivOptativa, which fails.

        restRespAtivOptativaMockMvc.perform(post("/api/resp-ativ-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivOptativa)))
            .andExpect(status().isBadRequest());

        List<RespAtivOptativa> respAtivOptativaList = respAtivOptativaRepository.findAll();
        assertThat(respAtivOptativaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespAtivOptativas() throws Exception {
        // Initialize the database
        respAtivOptativaRepository.saveAndFlush(respAtivOptativa);

        // Get all the respAtivOptativaList
        restRespAtivOptativaMockMvc.perform(get("/api/resp-ativ-optativas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respAtivOptativa.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getRespAtivOptativa() throws Exception {
        // Initialize the database
        respAtivOptativaRepository.saveAndFlush(respAtivOptativa);

        // Get the respAtivOptativa
        restRespAtivOptativaMockMvc.perform(get("/api/resp-ativ-optativas/{id}", respAtivOptativa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respAtivOptativa.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespAtivOptativa() throws Exception {
        // Get the respAtivOptativa
        restRespAtivOptativaMockMvc.perform(get("/api/resp-ativ-optativas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespAtivOptativa() throws Exception {
        // Initialize the database
        respAtivOptativaService.save(respAtivOptativa);

        int databaseSizeBeforeUpdate = respAtivOptativaRepository.findAll().size();

        // Update the respAtivOptativa
        RespAtivOptativa updatedRespAtivOptativa = respAtivOptativaRepository.findById(respAtivOptativa.getId()).get();
        // Disconnect from session so that the updates on updatedRespAtivOptativa are not directly saved in db
        em.detach(updatedRespAtivOptativa);
        updatedRespAtivOptativa
            .data(UPDATED_DATA);

        restRespAtivOptativaMockMvc.perform(put("/api/resp-ativ-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespAtivOptativa)))
            .andExpect(status().isOk());

        // Validate the RespAtivOptativa in the database
        List<RespAtivOptativa> respAtivOptativaList = respAtivOptativaRepository.findAll();
        assertThat(respAtivOptativaList).hasSize(databaseSizeBeforeUpdate);
        RespAtivOptativa testRespAtivOptativa = respAtivOptativaList.get(respAtivOptativaList.size() - 1);
        assertThat(testRespAtivOptativa.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingRespAtivOptativa() throws Exception {
        int databaseSizeBeforeUpdate = respAtivOptativaRepository.findAll().size();

        // Create the RespAtivOptativa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespAtivOptativaMockMvc.perform(put("/api/resp-ativ-optativas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respAtivOptativa)))
            .andExpect(status().isBadRequest());

        // Validate the RespAtivOptativa in the database
        List<RespAtivOptativa> respAtivOptativaList = respAtivOptativaRepository.findAll();
        assertThat(respAtivOptativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespAtivOptativa() throws Exception {
        // Initialize the database
        respAtivOptativaService.save(respAtivOptativa);

        int databaseSizeBeforeDelete = respAtivOptativaRepository.findAll().size();

        // Get the respAtivOptativa
        restRespAtivOptativaMockMvc.perform(delete("/api/resp-ativ-optativas/{id}", respAtivOptativa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespAtivOptativa> respAtivOptativaList = respAtivOptativaRepository.findAll();
        assertThat(respAtivOptativaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespAtivOptativa.class);
        RespAtivOptativa respAtivOptativa1 = new RespAtivOptativa();
        respAtivOptativa1.setId(1L);
        RespAtivOptativa respAtivOptativa2 = new RespAtivOptativa();
        respAtivOptativa2.setId(respAtivOptativa1.getId());
        assertThat(respAtivOptativa1).isEqualTo(respAtivOptativa2);
        respAtivOptativa2.setId(2L);
        assertThat(respAtivOptativa1).isNotEqualTo(respAtivOptativa2);
        respAtivOptativa1.setId(null);
        assertThat(respAtivOptativa1).isNotEqualTo(respAtivOptativa2);
    }
}
