package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Atividade;
import br.com.jhisolution.ong.control.repository.AtividadeRepository;
import br.com.jhisolution.ong.control.service.AtividadeService;
import br.com.jhisolution.ong.control.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AtividadeResource REST controller.
 *
 * @see AtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AtividadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESUMO = "AAAAAAAAAA";
    private static final String UPDATED_RESUMO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Mock
    private AtividadeRepository atividadeRepositoryMock;

    @Mock
    private AtividadeService atividadeServiceMock;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAtividadeMockMvc;

    private Atividade atividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AtividadeResource atividadeResource = new AtividadeResource(atividadeService);
        this.restAtividadeMockMvc = MockMvcBuilders.standaloneSetup(atividadeResource)
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
    public static Atividade createEntity(EntityManager em) {
        Atividade atividade = new Atividade()
            .nome(DEFAULT_NOME)
            .data(DEFAULT_DATA)
            .resumo(DEFAULT_RESUMO)
            .obs(DEFAULT_OBS);
        return atividade;
    }

    @Before
    public void initTest() {
        atividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createAtividade() throws Exception {
        int databaseSizeBeforeCreate = atividadeRepository.findAll().size();

        // Create the Atividade
        restAtividadeMockMvc.perform(post("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isCreated());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeCreate + 1);
        Atividade testAtividade = atividadeList.get(atividadeList.size() - 1);
        assertThat(testAtividade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAtividade.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAtividade.getResumo()).isEqualTo(DEFAULT_RESUMO);
        assertThat(testAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atividadeRepository.findAll().size();

        // Create the Atividade with an existing ID
        atividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtividadeMockMvc.perform(post("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isBadRequest());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = atividadeRepository.findAll().size();
        // set the field null
        atividade.setNome(null);

        // Create the Atividade, which fails.

        restAtividadeMockMvc.perform(post("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isBadRequest());

        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = atividadeRepository.findAll().size();
        // set the field null
        atividade.setData(null);

        // Create the Atividade, which fails.

        restAtividadeMockMvc.perform(post("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isBadRequest());

        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAtividades() throws Exception {
        // Initialize the database
        atividadeRepository.saveAndFlush(atividade);

        // Get all the atividadeList
        restAtividadeMockMvc.perform(get("/api/atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].resumo").value(hasItem(DEFAULT_RESUMO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllAtividadesWithEagerRelationshipsIsEnabled() throws Exception {
        AtividadeResource atividadeResource = new AtividadeResource(atividadeServiceMock);
        when(atividadeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restAtividadeMockMvc = MockMvcBuilders.standaloneSetup(atividadeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAtividadeMockMvc.perform(get("/api/atividades?eagerload=true"))
        .andExpect(status().isOk());

        verify(atividadeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllAtividadesWithEagerRelationshipsIsNotEnabled() throws Exception {
        AtividadeResource atividadeResource = new AtividadeResource(atividadeServiceMock);
            when(atividadeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restAtividadeMockMvc = MockMvcBuilders.standaloneSetup(atividadeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAtividadeMockMvc.perform(get("/api/atividades?eagerload=true"))
        .andExpect(status().isOk());

            verify(atividadeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAtividade() throws Exception {
        // Initialize the database
        atividadeRepository.saveAndFlush(atividade);

        // Get the atividade
        restAtividadeMockMvc.perform(get("/api/atividades/{id}", atividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atividade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.resumo").value(DEFAULT_RESUMO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAtividade() throws Exception {
        // Get the atividade
        restAtividadeMockMvc.perform(get("/api/atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAtividade() throws Exception {
        // Initialize the database
        atividadeService.save(atividade);

        int databaseSizeBeforeUpdate = atividadeRepository.findAll().size();

        // Update the atividade
        Atividade updatedAtividade = atividadeRepository.findById(atividade.getId()).get();
        // Disconnect from session so that the updates on updatedAtividade are not directly saved in db
        em.detach(updatedAtividade);
        updatedAtividade
            .nome(UPDATED_NOME)
            .data(UPDATED_DATA)
            .resumo(UPDATED_RESUMO)
            .obs(UPDATED_OBS);

        restAtividadeMockMvc.perform(put("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAtividade)))
            .andExpect(status().isOk());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeUpdate);
        Atividade testAtividade = atividadeList.get(atividadeList.size() - 1);
        assertThat(testAtividade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAtividade.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAtividade.getResumo()).isEqualTo(UPDATED_RESUMO);
        assertThat(testAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingAtividade() throws Exception {
        int databaseSizeBeforeUpdate = atividadeRepository.findAll().size();

        // Create the Atividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAtividadeMockMvc.perform(put("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isBadRequest());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAtividade() throws Exception {
        // Initialize the database
        atividadeService.save(atividade);

        int databaseSizeBeforeDelete = atividadeRepository.findAll().size();

        // Get the atividade
        restAtividadeMockMvc.perform(delete("/api/atividades/{id}", atividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Atividade.class);
        Atividade atividade1 = new Atividade();
        atividade1.setId(1L);
        Atividade atividade2 = new Atividade();
        atividade2.setId(atividade1.getId());
        assertThat(atividade1).isEqualTo(atividade2);
        atividade2.setId(2L);
        assertThat(atividade1).isNotEqualTo(atividade2);
        atividade1.setId(null);
        assertThat(atividade1).isNotEqualTo(atividade2);
    }
}
