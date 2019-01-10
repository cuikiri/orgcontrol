package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.OpcaoRespAtividade;
import br.com.jhisolution.ong.control.repository.OpcaoRespAtividadeRepository;
import br.com.jhisolution.ong.control.service.OpcaoRespAtividadeService;
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
 * Test class for the OpcaoRespAtividadeResource REST controller.
 *
 * @see OpcaoRespAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class OpcaoRespAtividadeResourceIntTest {

    private static final String DEFAULT_OPCAO = "AAAAAAAAAA";
    private static final String UPDATED_OPCAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private OpcaoRespAtividadeRepository opcaoRespAtividadeRepository;

    @Autowired
    private OpcaoRespAtividadeService opcaoRespAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOpcaoRespAtividadeMockMvc;

    private OpcaoRespAtividade opcaoRespAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OpcaoRespAtividadeResource opcaoRespAtividadeResource = new OpcaoRespAtividadeResource(opcaoRespAtividadeService);
        this.restOpcaoRespAtividadeMockMvc = MockMvcBuilders.standaloneSetup(opcaoRespAtividadeResource)
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
    public static OpcaoRespAtividade createEntity(EntityManager em) {
        OpcaoRespAtividade opcaoRespAtividade = new OpcaoRespAtividade()
            .opcao(DEFAULT_OPCAO)
            .obs(DEFAULT_OBS);
        return opcaoRespAtividade;
    }

    @Before
    public void initTest() {
        opcaoRespAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpcaoRespAtividade() throws Exception {
        int databaseSizeBeforeCreate = opcaoRespAtividadeRepository.findAll().size();

        // Create the OpcaoRespAtividade
        restOpcaoRespAtividadeMockMvc.perform(post("/api/opcao-resp-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAtividade)))
            .andExpect(status().isCreated());

        // Validate the OpcaoRespAtividade in the database
        List<OpcaoRespAtividade> opcaoRespAtividadeList = opcaoRespAtividadeRepository.findAll();
        assertThat(opcaoRespAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        OpcaoRespAtividade testOpcaoRespAtividade = opcaoRespAtividadeList.get(opcaoRespAtividadeList.size() - 1);
        assertThat(testOpcaoRespAtividade.getOpcao()).isEqualTo(DEFAULT_OPCAO);
        assertThat(testOpcaoRespAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createOpcaoRespAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opcaoRespAtividadeRepository.findAll().size();

        // Create the OpcaoRespAtividade with an existing ID
        opcaoRespAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpcaoRespAtividadeMockMvc.perform(post("/api/opcao-resp-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the OpcaoRespAtividade in the database
        List<OpcaoRespAtividade> opcaoRespAtividadeList = opcaoRespAtividadeRepository.findAll();
        assertThat(opcaoRespAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkOpcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = opcaoRespAtividadeRepository.findAll().size();
        // set the field null
        opcaoRespAtividade.setOpcao(null);

        // Create the OpcaoRespAtividade, which fails.

        restOpcaoRespAtividadeMockMvc.perform(post("/api/opcao-resp-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAtividade)))
            .andExpect(status().isBadRequest());

        List<OpcaoRespAtividade> opcaoRespAtividadeList = opcaoRespAtividadeRepository.findAll();
        assertThat(opcaoRespAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOpcaoRespAtividades() throws Exception {
        // Initialize the database
        opcaoRespAtividadeRepository.saveAndFlush(opcaoRespAtividade);

        // Get all the opcaoRespAtividadeList
        restOpcaoRespAtividadeMockMvc.perform(get("/api/opcao-resp-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opcaoRespAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].opcao").value(hasItem(DEFAULT_OPCAO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getOpcaoRespAtividade() throws Exception {
        // Initialize the database
        opcaoRespAtividadeRepository.saveAndFlush(opcaoRespAtividade);

        // Get the opcaoRespAtividade
        restOpcaoRespAtividadeMockMvc.perform(get("/api/opcao-resp-atividades/{id}", opcaoRespAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(opcaoRespAtividade.getId().intValue()))
            .andExpect(jsonPath("$.opcao").value(DEFAULT_OPCAO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOpcaoRespAtividade() throws Exception {
        // Get the opcaoRespAtividade
        restOpcaoRespAtividadeMockMvc.perform(get("/api/opcao-resp-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpcaoRespAtividade() throws Exception {
        // Initialize the database
        opcaoRespAtividadeService.save(opcaoRespAtividade);

        int databaseSizeBeforeUpdate = opcaoRespAtividadeRepository.findAll().size();

        // Update the opcaoRespAtividade
        OpcaoRespAtividade updatedOpcaoRespAtividade = opcaoRespAtividadeRepository.findById(opcaoRespAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedOpcaoRespAtividade are not directly saved in db
        em.detach(updatedOpcaoRespAtividade);
        updatedOpcaoRespAtividade
            .opcao(UPDATED_OPCAO)
            .obs(UPDATED_OBS);

        restOpcaoRespAtividadeMockMvc.perform(put("/api/opcao-resp-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOpcaoRespAtividade)))
            .andExpect(status().isOk());

        // Validate the OpcaoRespAtividade in the database
        List<OpcaoRespAtividade> opcaoRespAtividadeList = opcaoRespAtividadeRepository.findAll();
        assertThat(opcaoRespAtividadeList).hasSize(databaseSizeBeforeUpdate);
        OpcaoRespAtividade testOpcaoRespAtividade = opcaoRespAtividadeList.get(opcaoRespAtividadeList.size() - 1);
        assertThat(testOpcaoRespAtividade.getOpcao()).isEqualTo(UPDATED_OPCAO);
        assertThat(testOpcaoRespAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingOpcaoRespAtividade() throws Exception {
        int databaseSizeBeforeUpdate = opcaoRespAtividadeRepository.findAll().size();

        // Create the OpcaoRespAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcaoRespAtividadeMockMvc.perform(put("/api/opcao-resp-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(opcaoRespAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the OpcaoRespAtividade in the database
        List<OpcaoRespAtividade> opcaoRespAtividadeList = opcaoRespAtividadeRepository.findAll();
        assertThat(opcaoRespAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpcaoRespAtividade() throws Exception {
        // Initialize the database
        opcaoRespAtividadeService.save(opcaoRespAtividade);

        int databaseSizeBeforeDelete = opcaoRespAtividadeRepository.findAll().size();

        // Get the opcaoRespAtividade
        restOpcaoRespAtividadeMockMvc.perform(delete("/api/opcao-resp-atividades/{id}", opcaoRespAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OpcaoRespAtividade> opcaoRespAtividadeList = opcaoRespAtividadeRepository.findAll();
        assertThat(opcaoRespAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcaoRespAtividade.class);
        OpcaoRespAtividade opcaoRespAtividade1 = new OpcaoRespAtividade();
        opcaoRespAtividade1.setId(1L);
        OpcaoRespAtividade opcaoRespAtividade2 = new OpcaoRespAtividade();
        opcaoRespAtividade2.setId(opcaoRespAtividade1.getId());
        assertThat(opcaoRespAtividade1).isEqualTo(opcaoRespAtividade2);
        opcaoRespAtividade2.setId(2L);
        assertThat(opcaoRespAtividade1).isNotEqualTo(opcaoRespAtividade2);
        opcaoRespAtividade1.setId(null);
        assertThat(opcaoRespAtividade1).isNotEqualTo(opcaoRespAtividade2);
    }
}
