package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ExameMedico;
import br.com.jhisolution.ong.control.repository.ExameMedicoRepository;
import br.com.jhisolution.ong.control.service.ExameMedicoService;
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
import java.util.ArrayList;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExameMedicoResource REST controller.
 *
 * @see ExameMedicoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ExameMedicoResourceIntTest {

    private static final String DEFAULT_NOME_MEDICO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_MEDICO = "BBBBBBBBBB";

    private static final String DEFAULT_CRM_MEDICO = "AAAAAAAAAA";
    private static final String UPDATED_CRM_MEDICO = "BBBBBBBBBB";

    private static final String DEFAULT_ESPECIALIDADE_MEDICO = "AAAAAAAAAA";
    private static final String UPDATED_ESPECIALIDADE_MEDICO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDADE_PACIENTE = 1;
    private static final Integer UPDATED_IDADE_PACIENTE = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ExameMedicoRepository exameMedicoRepository;

    @Mock
    private ExameMedicoRepository exameMedicoRepositoryMock;

    @Mock
    private ExameMedicoService exameMedicoServiceMock;

    @Autowired
    private ExameMedicoService exameMedicoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restExameMedicoMockMvc;

    private ExameMedico exameMedico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExameMedicoResource exameMedicoResource = new ExameMedicoResource(exameMedicoService);
        this.restExameMedicoMockMvc = MockMvcBuilders.standaloneSetup(exameMedicoResource)
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
    public static ExameMedico createEntity(EntityManager em) {
        ExameMedico exameMedico = new ExameMedico()
            .nomeMedico(DEFAULT_NOME_MEDICO)
            .crmMedico(DEFAULT_CRM_MEDICO)
            .especialidadeMedico(DEFAULT_ESPECIALIDADE_MEDICO)
            .idadePaciente(DEFAULT_IDADE_PACIENTE)
            .obs(DEFAULT_OBS);
        return exameMedico;
    }

    @Before
    public void initTest() {
        exameMedico = createEntity(em);
    }

    @Test
    @Transactional
    public void createExameMedico() throws Exception {
        int databaseSizeBeforeCreate = exameMedicoRepository.findAll().size();

        // Create the ExameMedico
        restExameMedicoMockMvc.perform(post("/api/exame-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exameMedico)))
            .andExpect(status().isCreated());

        // Validate the ExameMedico in the database
        List<ExameMedico> exameMedicoList = exameMedicoRepository.findAll();
        assertThat(exameMedicoList).hasSize(databaseSizeBeforeCreate + 1);
        ExameMedico testExameMedico = exameMedicoList.get(exameMedicoList.size() - 1);
        assertThat(testExameMedico.getNomeMedico()).isEqualTo(DEFAULT_NOME_MEDICO);
        assertThat(testExameMedico.getCrmMedico()).isEqualTo(DEFAULT_CRM_MEDICO);
        assertThat(testExameMedico.getEspecialidadeMedico()).isEqualTo(DEFAULT_ESPECIALIDADE_MEDICO);
        assertThat(testExameMedico.getIdadePaciente()).isEqualTo(DEFAULT_IDADE_PACIENTE);
        assertThat(testExameMedico.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createExameMedicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exameMedicoRepository.findAll().size();

        // Create the ExameMedico with an existing ID
        exameMedico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExameMedicoMockMvc.perform(post("/api/exame-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exameMedico)))
            .andExpect(status().isBadRequest());

        // Validate the ExameMedico in the database
        List<ExameMedico> exameMedicoList = exameMedicoRepository.findAll();
        assertThat(exameMedicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeMedicoIsRequired() throws Exception {
        int databaseSizeBeforeTest = exameMedicoRepository.findAll().size();
        // set the field null
        exameMedico.setNomeMedico(null);

        // Create the ExameMedico, which fails.

        restExameMedicoMockMvc.perform(post("/api/exame-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exameMedico)))
            .andExpect(status().isBadRequest());

        List<ExameMedico> exameMedicoList = exameMedicoRepository.findAll();
        assertThat(exameMedicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdadePacienteIsRequired() throws Exception {
        int databaseSizeBeforeTest = exameMedicoRepository.findAll().size();
        // set the field null
        exameMedico.setIdadePaciente(null);

        // Create the ExameMedico, which fails.

        restExameMedicoMockMvc.perform(post("/api/exame-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exameMedico)))
            .andExpect(status().isBadRequest());

        List<ExameMedico> exameMedicoList = exameMedicoRepository.findAll();
        assertThat(exameMedicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExameMedicos() throws Exception {
        // Initialize the database
        exameMedicoRepository.saveAndFlush(exameMedico);

        // Get all the exameMedicoList
        restExameMedicoMockMvc.perform(get("/api/exame-medicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exameMedico.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeMedico").value(hasItem(DEFAULT_NOME_MEDICO.toString())))
            .andExpect(jsonPath("$.[*].crmMedico").value(hasItem(DEFAULT_CRM_MEDICO.toString())))
            .andExpect(jsonPath("$.[*].especialidadeMedico").value(hasItem(DEFAULT_ESPECIALIDADE_MEDICO.toString())))
            .andExpect(jsonPath("$.[*].idadePaciente").value(hasItem(DEFAULT_IDADE_PACIENTE)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllExameMedicosWithEagerRelationshipsIsEnabled() throws Exception {
        ExameMedicoResource exameMedicoResource = new ExameMedicoResource(exameMedicoServiceMock);
        when(exameMedicoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restExameMedicoMockMvc = MockMvcBuilders.standaloneSetup(exameMedicoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restExameMedicoMockMvc.perform(get("/api/exame-medicos?eagerload=true"))
        .andExpect(status().isOk());

        verify(exameMedicoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllExameMedicosWithEagerRelationshipsIsNotEnabled() throws Exception {
        ExameMedicoResource exameMedicoResource = new ExameMedicoResource(exameMedicoServiceMock);
            when(exameMedicoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restExameMedicoMockMvc = MockMvcBuilders.standaloneSetup(exameMedicoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restExameMedicoMockMvc.perform(get("/api/exame-medicos?eagerload=true"))
        .andExpect(status().isOk());

            verify(exameMedicoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getExameMedico() throws Exception {
        // Initialize the database
        exameMedicoRepository.saveAndFlush(exameMedico);

        // Get the exameMedico
        restExameMedicoMockMvc.perform(get("/api/exame-medicos/{id}", exameMedico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exameMedico.getId().intValue()))
            .andExpect(jsonPath("$.nomeMedico").value(DEFAULT_NOME_MEDICO.toString()))
            .andExpect(jsonPath("$.crmMedico").value(DEFAULT_CRM_MEDICO.toString()))
            .andExpect(jsonPath("$.especialidadeMedico").value(DEFAULT_ESPECIALIDADE_MEDICO.toString()))
            .andExpect(jsonPath("$.idadePaciente").value(DEFAULT_IDADE_PACIENTE))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExameMedico() throws Exception {
        // Get the exameMedico
        restExameMedicoMockMvc.perform(get("/api/exame-medicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExameMedico() throws Exception {
        // Initialize the database
        exameMedicoService.save(exameMedico);

        int databaseSizeBeforeUpdate = exameMedicoRepository.findAll().size();

        // Update the exameMedico
        ExameMedico updatedExameMedico = exameMedicoRepository.findById(exameMedico.getId()).get();
        // Disconnect from session so that the updates on updatedExameMedico are not directly saved in db
        em.detach(updatedExameMedico);
        updatedExameMedico
            .nomeMedico(UPDATED_NOME_MEDICO)
            .crmMedico(UPDATED_CRM_MEDICO)
            .especialidadeMedico(UPDATED_ESPECIALIDADE_MEDICO)
            .idadePaciente(UPDATED_IDADE_PACIENTE)
            .obs(UPDATED_OBS);

        restExameMedicoMockMvc.perform(put("/api/exame-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExameMedico)))
            .andExpect(status().isOk());

        // Validate the ExameMedico in the database
        List<ExameMedico> exameMedicoList = exameMedicoRepository.findAll();
        assertThat(exameMedicoList).hasSize(databaseSizeBeforeUpdate);
        ExameMedico testExameMedico = exameMedicoList.get(exameMedicoList.size() - 1);
        assertThat(testExameMedico.getNomeMedico()).isEqualTo(UPDATED_NOME_MEDICO);
        assertThat(testExameMedico.getCrmMedico()).isEqualTo(UPDATED_CRM_MEDICO);
        assertThat(testExameMedico.getEspecialidadeMedico()).isEqualTo(UPDATED_ESPECIALIDADE_MEDICO);
        assertThat(testExameMedico.getIdadePaciente()).isEqualTo(UPDATED_IDADE_PACIENTE);
        assertThat(testExameMedico.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingExameMedico() throws Exception {
        int databaseSizeBeforeUpdate = exameMedicoRepository.findAll().size();

        // Create the ExameMedico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExameMedicoMockMvc.perform(put("/api/exame-medicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exameMedico)))
            .andExpect(status().isBadRequest());

        // Validate the ExameMedico in the database
        List<ExameMedico> exameMedicoList = exameMedicoRepository.findAll();
        assertThat(exameMedicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExameMedico() throws Exception {
        // Initialize the database
        exameMedicoService.save(exameMedico);

        int databaseSizeBeforeDelete = exameMedicoRepository.findAll().size();

        // Get the exameMedico
        restExameMedicoMockMvc.perform(delete("/api/exame-medicos/{id}", exameMedico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExameMedico> exameMedicoList = exameMedicoRepository.findAll();
        assertThat(exameMedicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExameMedico.class);
        ExameMedico exameMedico1 = new ExameMedico();
        exameMedico1.setId(1L);
        ExameMedico exameMedico2 = new ExameMedico();
        exameMedico2.setId(exameMedico1.getId());
        assertThat(exameMedico1).isEqualTo(exameMedico2);
        exameMedico2.setId(2L);
        assertThat(exameMedico1).isNotEqualTo(exameMedico2);
        exameMedico1.setId(null);
        assertThat(exameMedico1).isNotEqualTo(exameMedico2);
    }
}
