package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespostaAvaliacao;
import br.com.jhisolution.ong.control.repository.RespostaAvaliacaoRepository;
import br.com.jhisolution.ong.control.service.RespostaAvaliacaoService;
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
 * Test class for the RespostaAvaliacaoResource REST controller.
 *
 * @see RespostaAvaliacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespostaAvaliacaoResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private RespostaAvaliacaoRepository respostaAvaliacaoRepository;

    @Autowired
    private RespostaAvaliacaoService respostaAvaliacaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespostaAvaliacaoMockMvc;

    private RespostaAvaliacao respostaAvaliacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespostaAvaliacaoResource respostaAvaliacaoResource = new RespostaAvaliacaoResource(respostaAvaliacaoService);
        this.restRespostaAvaliacaoMockMvc = MockMvcBuilders.standaloneSetup(respostaAvaliacaoResource)
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
    public static RespostaAvaliacao createEntity(EntityManager em) {
        RespostaAvaliacao respostaAvaliacao = new RespostaAvaliacao()
            .data(DEFAULT_DATA)
            .obs(DEFAULT_OBS);
        return respostaAvaliacao;
    }

    @Before
    public void initTest() {
        respostaAvaliacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespostaAvaliacao() throws Exception {
        int databaseSizeBeforeCreate = respostaAvaliacaoRepository.findAll().size();

        // Create the RespostaAvaliacao
        restRespostaAvaliacaoMockMvc.perform(post("/api/resposta-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacao)))
            .andExpect(status().isCreated());

        // Validate the RespostaAvaliacao in the database
        List<RespostaAvaliacao> respostaAvaliacaoList = respostaAvaliacaoRepository.findAll();
        assertThat(respostaAvaliacaoList).hasSize(databaseSizeBeforeCreate + 1);
        RespostaAvaliacao testRespostaAvaliacao = respostaAvaliacaoList.get(respostaAvaliacaoList.size() - 1);
        assertThat(testRespostaAvaliacao.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testRespostaAvaliacao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createRespostaAvaliacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respostaAvaliacaoRepository.findAll().size();

        // Create the RespostaAvaliacao with an existing ID
        respostaAvaliacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespostaAvaliacaoMockMvc.perform(post("/api/resposta-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacao)))
            .andExpect(status().isBadRequest());

        // Validate the RespostaAvaliacao in the database
        List<RespostaAvaliacao> respostaAvaliacaoList = respostaAvaliacaoRepository.findAll();
        assertThat(respostaAvaliacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respostaAvaliacaoRepository.findAll().size();
        // set the field null
        respostaAvaliacao.setData(null);

        // Create the RespostaAvaliacao, which fails.

        restRespostaAvaliacaoMockMvc.perform(post("/api/resposta-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacao)))
            .andExpect(status().isBadRequest());

        List<RespostaAvaliacao> respostaAvaliacaoList = respostaAvaliacaoRepository.findAll();
        assertThat(respostaAvaliacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespostaAvaliacaos() throws Exception {
        // Initialize the database
        respostaAvaliacaoRepository.saveAndFlush(respostaAvaliacao);

        // Get all the respostaAvaliacaoList
        restRespostaAvaliacaoMockMvc.perform(get("/api/resposta-avaliacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respostaAvaliacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getRespostaAvaliacao() throws Exception {
        // Initialize the database
        respostaAvaliacaoRepository.saveAndFlush(respostaAvaliacao);

        // Get the respostaAvaliacao
        restRespostaAvaliacaoMockMvc.perform(get("/api/resposta-avaliacaos/{id}", respostaAvaliacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respostaAvaliacao.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespostaAvaliacao() throws Exception {
        // Get the respostaAvaliacao
        restRespostaAvaliacaoMockMvc.perform(get("/api/resposta-avaliacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespostaAvaliacao() throws Exception {
        // Initialize the database
        respostaAvaliacaoService.save(respostaAvaliacao);

        int databaseSizeBeforeUpdate = respostaAvaliacaoRepository.findAll().size();

        // Update the respostaAvaliacao
        RespostaAvaliacao updatedRespostaAvaliacao = respostaAvaliacaoRepository.findById(respostaAvaliacao.getId()).get();
        // Disconnect from session so that the updates on updatedRespostaAvaliacao are not directly saved in db
        em.detach(updatedRespostaAvaliacao);
        updatedRespostaAvaliacao
            .data(UPDATED_DATA)
            .obs(UPDATED_OBS);

        restRespostaAvaliacaoMockMvc.perform(put("/api/resposta-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespostaAvaliacao)))
            .andExpect(status().isOk());

        // Validate the RespostaAvaliacao in the database
        List<RespostaAvaliacao> respostaAvaliacaoList = respostaAvaliacaoRepository.findAll();
        assertThat(respostaAvaliacaoList).hasSize(databaseSizeBeforeUpdate);
        RespostaAvaliacao testRespostaAvaliacao = respostaAvaliacaoList.get(respostaAvaliacaoList.size() - 1);
        assertThat(testRespostaAvaliacao.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testRespostaAvaliacao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingRespostaAvaliacao() throws Exception {
        int databaseSizeBeforeUpdate = respostaAvaliacaoRepository.findAll().size();

        // Create the RespostaAvaliacao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespostaAvaliacaoMockMvc.perform(put("/api/resposta-avaliacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAvaliacao)))
            .andExpect(status().isBadRequest());

        // Validate the RespostaAvaliacao in the database
        List<RespostaAvaliacao> respostaAvaliacaoList = respostaAvaliacaoRepository.findAll();
        assertThat(respostaAvaliacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespostaAvaliacao() throws Exception {
        // Initialize the database
        respostaAvaliacaoService.save(respostaAvaliacao);

        int databaseSizeBeforeDelete = respostaAvaliacaoRepository.findAll().size();

        // Get the respostaAvaliacao
        restRespostaAvaliacaoMockMvc.perform(delete("/api/resposta-avaliacaos/{id}", respostaAvaliacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespostaAvaliacao> respostaAvaliacaoList = respostaAvaliacaoRepository.findAll();
        assertThat(respostaAvaliacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespostaAvaliacao.class);
        RespostaAvaliacao respostaAvaliacao1 = new RespostaAvaliacao();
        respostaAvaliacao1.setId(1L);
        RespostaAvaliacao respostaAvaliacao2 = new RespostaAvaliacao();
        respostaAvaliacao2.setId(respostaAvaliacao1.getId());
        assertThat(respostaAvaliacao1).isEqualTo(respostaAvaliacao2);
        respostaAvaliacao2.setId(2L);
        assertThat(respostaAvaliacao1).isNotEqualTo(respostaAvaliacao2);
        respostaAvaliacao1.setId(null);
        assertThat(respostaAvaliacao1).isNotEqualTo(respostaAvaliacao2);
    }
}
