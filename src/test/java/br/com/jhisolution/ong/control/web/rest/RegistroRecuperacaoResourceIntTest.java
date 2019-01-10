package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.RegistroRecuperacao;
import br.com.jhisolution.ong.control.repository.RegistroRecuperacaoRepository;
import br.com.jhisolution.ong.control.service.RegistroRecuperacaoService;
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
 * Test class for the RegistroRecuperacaoResource REST controller.
 *
 * @see RegistroRecuperacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class RegistroRecuperacaoResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private RegistroRecuperacaoRepository registroRecuperacaoRepository;

    @Autowired
    private RegistroRecuperacaoService registroRecuperacaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRegistroRecuperacaoMockMvc;

    private RegistroRecuperacao registroRecuperacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RegistroRecuperacaoResource registroRecuperacaoResource = new RegistroRecuperacaoResource(registroRecuperacaoService);
        this.restRegistroRecuperacaoMockMvc = MockMvcBuilders.standaloneSetup(registroRecuperacaoResource)
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
    public static RegistroRecuperacao createEntity(EntityManager em) {
        RegistroRecuperacao registroRecuperacao = new RegistroRecuperacao()
            .data(DEFAULT_DATA)
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return registroRecuperacao;
    }

    @Before
    public void initTest() {
        registroRecuperacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegistroRecuperacao() throws Exception {
        int databaseSizeBeforeCreate = registroRecuperacaoRepository.findAll().size();

        // Create the RegistroRecuperacao
        restRegistroRecuperacaoMockMvc.perform(post("/api/registro-recuperacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroRecuperacao)))
            .andExpect(status().isCreated());

        // Validate the RegistroRecuperacao in the database
        List<RegistroRecuperacao> registroRecuperacaoList = registroRecuperacaoRepository.findAll();
        assertThat(registroRecuperacaoList).hasSize(databaseSizeBeforeCreate + 1);
        RegistroRecuperacao testRegistroRecuperacao = registroRecuperacaoList.get(registroRecuperacaoList.size() - 1);
        assertThat(testRegistroRecuperacao.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testRegistroRecuperacao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testRegistroRecuperacao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createRegistroRecuperacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registroRecuperacaoRepository.findAll().size();

        // Create the RegistroRecuperacao with an existing ID
        registroRecuperacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegistroRecuperacaoMockMvc.perform(post("/api/registro-recuperacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroRecuperacao)))
            .andExpect(status().isBadRequest());

        // Validate the RegistroRecuperacao in the database
        List<RegistroRecuperacao> registroRecuperacaoList = registroRecuperacaoRepository.findAll();
        assertThat(registroRecuperacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = registroRecuperacaoRepository.findAll().size();
        // set the field null
        registroRecuperacao.setData(null);

        // Create the RegistroRecuperacao, which fails.

        restRegistroRecuperacaoMockMvc.perform(post("/api/registro-recuperacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroRecuperacao)))
            .andExpect(status().isBadRequest());

        List<RegistroRecuperacao> registroRecuperacaoList = registroRecuperacaoRepository.findAll();
        assertThat(registroRecuperacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRegistroRecuperacaos() throws Exception {
        // Initialize the database
        registroRecuperacaoRepository.saveAndFlush(registroRecuperacao);

        // Get all the registroRecuperacaoList
        restRegistroRecuperacaoMockMvc.perform(get("/api/registro-recuperacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registroRecuperacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getRegistroRecuperacao() throws Exception {
        // Initialize the database
        registroRecuperacaoRepository.saveAndFlush(registroRecuperacao);

        // Get the registroRecuperacao
        restRegistroRecuperacaoMockMvc.perform(get("/api/registro-recuperacaos/{id}", registroRecuperacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(registroRecuperacao.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRegistroRecuperacao() throws Exception {
        // Get the registroRecuperacao
        restRegistroRecuperacaoMockMvc.perform(get("/api/registro-recuperacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegistroRecuperacao() throws Exception {
        // Initialize the database
        registroRecuperacaoService.save(registroRecuperacao);

        int databaseSizeBeforeUpdate = registroRecuperacaoRepository.findAll().size();

        // Update the registroRecuperacao
        RegistroRecuperacao updatedRegistroRecuperacao = registroRecuperacaoRepository.findById(registroRecuperacao.getId()).get();
        // Disconnect from session so that the updates on updatedRegistroRecuperacao are not directly saved in db
        em.detach(updatedRegistroRecuperacao);
        updatedRegistroRecuperacao
            .data(UPDATED_DATA)
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restRegistroRecuperacaoMockMvc.perform(put("/api/registro-recuperacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRegistroRecuperacao)))
            .andExpect(status().isOk());

        // Validate the RegistroRecuperacao in the database
        List<RegistroRecuperacao> registroRecuperacaoList = registroRecuperacaoRepository.findAll();
        assertThat(registroRecuperacaoList).hasSize(databaseSizeBeforeUpdate);
        RegistroRecuperacao testRegistroRecuperacao = registroRecuperacaoList.get(registroRecuperacaoList.size() - 1);
        assertThat(testRegistroRecuperacao.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testRegistroRecuperacao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testRegistroRecuperacao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingRegistroRecuperacao() throws Exception {
        int databaseSizeBeforeUpdate = registroRecuperacaoRepository.findAll().size();

        // Create the RegistroRecuperacao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegistroRecuperacaoMockMvc.perform(put("/api/registro-recuperacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroRecuperacao)))
            .andExpect(status().isBadRequest());

        // Validate the RegistroRecuperacao in the database
        List<RegistroRecuperacao> registroRecuperacaoList = registroRecuperacaoRepository.findAll();
        assertThat(registroRecuperacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegistroRecuperacao() throws Exception {
        // Initialize the database
        registroRecuperacaoService.save(registroRecuperacao);

        int databaseSizeBeforeDelete = registroRecuperacaoRepository.findAll().size();

        // Get the registroRecuperacao
        restRegistroRecuperacaoMockMvc.perform(delete("/api/registro-recuperacaos/{id}", registroRecuperacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RegistroRecuperacao> registroRecuperacaoList = registroRecuperacaoRepository.findAll();
        assertThat(registroRecuperacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegistroRecuperacao.class);
        RegistroRecuperacao registroRecuperacao1 = new RegistroRecuperacao();
        registroRecuperacao1.setId(1L);
        RegistroRecuperacao registroRecuperacao2 = new RegistroRecuperacao();
        registroRecuperacao2.setId(registroRecuperacao1.getId());
        assertThat(registroRecuperacao1).isEqualTo(registroRecuperacao2);
        registroRecuperacao2.setId(2L);
        assertThat(registroRecuperacao1).isNotEqualTo(registroRecuperacao2);
        registroRecuperacao1.setId(null);
        assertThat(registroRecuperacao1).isNotEqualTo(registroRecuperacao2);
    }
}
