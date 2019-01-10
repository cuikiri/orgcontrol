package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RespostaAtividade;
import br.com.jhisolution.ong.control.repository.RespostaAtividadeRepository;
import br.com.jhisolution.ong.control.service.RespostaAtividadeService;
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
 * Test class for the RespostaAtividadeResource REST controller.
 *
 * @see RespostaAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RespostaAtividadeResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private RespostaAtividadeRepository respostaAtividadeRepository;

    @Autowired
    private RespostaAtividadeService respostaAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRespostaAtividadeMockMvc;

    private RespostaAtividade respostaAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RespostaAtividadeResource respostaAtividadeResource = new RespostaAtividadeResource(respostaAtividadeService);
        this.restRespostaAtividadeMockMvc = MockMvcBuilders.standaloneSetup(respostaAtividadeResource)
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
    public static RespostaAtividade createEntity(EntityManager em) {
        RespostaAtividade respostaAtividade = new RespostaAtividade()
            .data(DEFAULT_DATA)
            .obs(DEFAULT_OBS);
        return respostaAtividade;
    }

    @Before
    public void initTest() {
        respostaAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespostaAtividade() throws Exception {
        int databaseSizeBeforeCreate = respostaAtividadeRepository.findAll().size();

        // Create the RespostaAtividade
        restRespostaAtividadeMockMvc.perform(post("/api/resposta-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAtividade)))
            .andExpect(status().isCreated());

        // Validate the RespostaAtividade in the database
        List<RespostaAtividade> respostaAtividadeList = respostaAtividadeRepository.findAll();
        assertThat(respostaAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        RespostaAtividade testRespostaAtividade = respostaAtividadeList.get(respostaAtividadeList.size() - 1);
        assertThat(testRespostaAtividade.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testRespostaAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createRespostaAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respostaAtividadeRepository.findAll().size();

        // Create the RespostaAtividade with an existing ID
        respostaAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespostaAtividadeMockMvc.perform(post("/api/resposta-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the RespostaAtividade in the database
        List<RespostaAtividade> respostaAtividadeList = respostaAtividadeRepository.findAll();
        assertThat(respostaAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = respostaAtividadeRepository.findAll().size();
        // set the field null
        respostaAtividade.setData(null);

        // Create the RespostaAtividade, which fails.

        restRespostaAtividadeMockMvc.perform(post("/api/resposta-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAtividade)))
            .andExpect(status().isBadRequest());

        List<RespostaAtividade> respostaAtividadeList = respostaAtividadeRepository.findAll();
        assertThat(respostaAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRespostaAtividades() throws Exception {
        // Initialize the database
        respostaAtividadeRepository.saveAndFlush(respostaAtividade);

        // Get all the respostaAtividadeList
        restRespostaAtividadeMockMvc.perform(get("/api/resposta-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respostaAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getRespostaAtividade() throws Exception {
        // Initialize the database
        respostaAtividadeRepository.saveAndFlush(respostaAtividade);

        // Get the respostaAtividade
        restRespostaAtividadeMockMvc.perform(get("/api/resposta-atividades/{id}", respostaAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respostaAtividade.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespostaAtividade() throws Exception {
        // Get the respostaAtividade
        restRespostaAtividadeMockMvc.perform(get("/api/resposta-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespostaAtividade() throws Exception {
        // Initialize the database
        respostaAtividadeService.save(respostaAtividade);

        int databaseSizeBeforeUpdate = respostaAtividadeRepository.findAll().size();

        // Update the respostaAtividade
        RespostaAtividade updatedRespostaAtividade = respostaAtividadeRepository.findById(respostaAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedRespostaAtividade are not directly saved in db
        em.detach(updatedRespostaAtividade);
        updatedRespostaAtividade
            .data(UPDATED_DATA)
            .obs(UPDATED_OBS);

        restRespostaAtividadeMockMvc.perform(put("/api/resposta-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRespostaAtividade)))
            .andExpect(status().isOk());

        // Validate the RespostaAtividade in the database
        List<RespostaAtividade> respostaAtividadeList = respostaAtividadeRepository.findAll();
        assertThat(respostaAtividadeList).hasSize(databaseSizeBeforeUpdate);
        RespostaAtividade testRespostaAtividade = respostaAtividadeList.get(respostaAtividadeList.size() - 1);
        assertThat(testRespostaAtividade.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testRespostaAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingRespostaAtividade() throws Exception {
        int databaseSizeBeforeUpdate = respostaAtividadeRepository.findAll().size();

        // Create the RespostaAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRespostaAtividadeMockMvc.perform(put("/api/resposta-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the RespostaAtividade in the database
        List<RespostaAtividade> respostaAtividadeList = respostaAtividadeRepository.findAll();
        assertThat(respostaAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRespostaAtividade() throws Exception {
        // Initialize the database
        respostaAtividadeService.save(respostaAtividade);

        int databaseSizeBeforeDelete = respostaAtividadeRepository.findAll().size();

        // Get the respostaAtividade
        restRespostaAtividadeMockMvc.perform(delete("/api/resposta-atividades/{id}", respostaAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespostaAtividade> respostaAtividadeList = respostaAtividadeRepository.findAll();
        assertThat(respostaAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RespostaAtividade.class);
        RespostaAtividade respostaAtividade1 = new RespostaAtividade();
        respostaAtividade1.setId(1L);
        RespostaAtividade respostaAtividade2 = new RespostaAtividade();
        respostaAtividade2.setId(respostaAtividade1.getId());
        assertThat(respostaAtividade1).isEqualTo(respostaAtividade2);
        respostaAtividade2.setId(2L);
        assertThat(respostaAtividade1).isNotEqualTo(respostaAtividade2);
        respostaAtividade1.setId(null);
        assertThat(respostaAtividade1).isNotEqualTo(respostaAtividade2);
    }
}
