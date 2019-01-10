package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.FechamentoBimestre;
import br.com.jhisolution.ong.control.repository.FechamentoBimestreRepository;
import br.com.jhisolution.ong.control.service.FechamentoBimestreService;
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
 * Test class for the FechamentoBimestreResource REST controller.
 *
 * @see FechamentoBimestreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class FechamentoBimestreResourceIntTest {

    private static final Integer DEFAULT_FREQUENCIA = 1;
    private static final Integer UPDATED_FREQUENCIA = 2;

    private static final Integer DEFAULT_AUSENCIA = 1;
    private static final Integer UPDATED_AUSENCIA = 2;

    private static final Integer DEFAULT_TOTAL_ATIVIDADES = 1;
    private static final Integer UPDATED_TOTAL_ATIVIDADES = 2;

    private static final Integer DEFAULT_PORCENTAGEM_FREQUENCIA = 1;
    private static final Integer UPDATED_PORCENTAGEM_FREQUENCIA = 2;

    private static final Integer DEFAULT_PORCENTAGEM_AUSEQUENCIA = 1;
    private static final Integer UPDATED_PORCENTAGEM_AUSEQUENCIA = 2;

    @Autowired
    private FechamentoBimestreRepository fechamentoBimestreRepository;

    @Mock
    private FechamentoBimestreRepository fechamentoBimestreRepositoryMock;

    @Mock
    private FechamentoBimestreService fechamentoBimestreServiceMock;

    @Autowired
    private FechamentoBimestreService fechamentoBimestreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFechamentoBimestreMockMvc;

    private FechamentoBimestre fechamentoBimestre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FechamentoBimestreResource fechamentoBimestreResource = new FechamentoBimestreResource(fechamentoBimestreService);
        this.restFechamentoBimestreMockMvc = MockMvcBuilders.standaloneSetup(fechamentoBimestreResource)
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
    public static FechamentoBimestre createEntity(EntityManager em) {
        FechamentoBimestre fechamentoBimestre = new FechamentoBimestre()
            .frequencia(DEFAULT_FREQUENCIA)
            .ausencia(DEFAULT_AUSENCIA)
            .totalAtividades(DEFAULT_TOTAL_ATIVIDADES)
            .porcentagemFrequencia(DEFAULT_PORCENTAGEM_FREQUENCIA)
            .porcentagemAusequencia(DEFAULT_PORCENTAGEM_AUSEQUENCIA);
        return fechamentoBimestre;
    }

    @Before
    public void initTest() {
        fechamentoBimestre = createEntity(em);
    }

    @Test
    @Transactional
    public void createFechamentoBimestre() throws Exception {
        int databaseSizeBeforeCreate = fechamentoBimestreRepository.findAll().size();

        // Create the FechamentoBimestre
        restFechamentoBimestreMockMvc.perform(post("/api/fechamento-bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fechamentoBimestre)))
            .andExpect(status().isCreated());

        // Validate the FechamentoBimestre in the database
        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeCreate + 1);
        FechamentoBimestre testFechamentoBimestre = fechamentoBimestreList.get(fechamentoBimestreList.size() - 1);
        assertThat(testFechamentoBimestre.getFrequencia()).isEqualTo(DEFAULT_FREQUENCIA);
        assertThat(testFechamentoBimestre.getAusencia()).isEqualTo(DEFAULT_AUSENCIA);
        assertThat(testFechamentoBimestre.getTotalAtividades()).isEqualTo(DEFAULT_TOTAL_ATIVIDADES);
        assertThat(testFechamentoBimestre.getPorcentagemFrequencia()).isEqualTo(DEFAULT_PORCENTAGEM_FREQUENCIA);
        assertThat(testFechamentoBimestre.getPorcentagemAusequencia()).isEqualTo(DEFAULT_PORCENTAGEM_AUSEQUENCIA);
    }

    @Test
    @Transactional
    public void createFechamentoBimestreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fechamentoBimestreRepository.findAll().size();

        // Create the FechamentoBimestre with an existing ID
        fechamentoBimestre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFechamentoBimestreMockMvc.perform(post("/api/fechamento-bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fechamentoBimestre)))
            .andExpect(status().isBadRequest());

        // Validate the FechamentoBimestre in the database
        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFrequenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fechamentoBimestreRepository.findAll().size();
        // set the field null
        fechamentoBimestre.setFrequencia(null);

        // Create the FechamentoBimestre, which fails.

        restFechamentoBimestreMockMvc.perform(post("/api/fechamento-bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fechamentoBimestre)))
            .andExpect(status().isBadRequest());

        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAusenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fechamentoBimestreRepository.findAll().size();
        // set the field null
        fechamentoBimestre.setAusencia(null);

        // Create the FechamentoBimestre, which fails.

        restFechamentoBimestreMockMvc.perform(post("/api/fechamento-bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fechamentoBimestre)))
            .andExpect(status().isBadRequest());

        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalAtividadesIsRequired() throws Exception {
        int databaseSizeBeforeTest = fechamentoBimestreRepository.findAll().size();
        // set the field null
        fechamentoBimestre.setTotalAtividades(null);

        // Create the FechamentoBimestre, which fails.

        restFechamentoBimestreMockMvc.perform(post("/api/fechamento-bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fechamentoBimestre)))
            .andExpect(status().isBadRequest());

        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFechamentoBimestres() throws Exception {
        // Initialize the database
        fechamentoBimestreRepository.saveAndFlush(fechamentoBimestre);

        // Get all the fechamentoBimestreList
        restFechamentoBimestreMockMvc.perform(get("/api/fechamento-bimestres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fechamentoBimestre.getId().intValue())))
            .andExpect(jsonPath("$.[*].frequencia").value(hasItem(DEFAULT_FREQUENCIA)))
            .andExpect(jsonPath("$.[*].ausencia").value(hasItem(DEFAULT_AUSENCIA)))
            .andExpect(jsonPath("$.[*].totalAtividades").value(hasItem(DEFAULT_TOTAL_ATIVIDADES)))
            .andExpect(jsonPath("$.[*].porcentagemFrequencia").value(hasItem(DEFAULT_PORCENTAGEM_FREQUENCIA)))
            .andExpect(jsonPath("$.[*].porcentagemAusequencia").value(hasItem(DEFAULT_PORCENTAGEM_AUSEQUENCIA)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllFechamentoBimestresWithEagerRelationshipsIsEnabled() throws Exception {
        FechamentoBimestreResource fechamentoBimestreResource = new FechamentoBimestreResource(fechamentoBimestreServiceMock);
        when(fechamentoBimestreServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restFechamentoBimestreMockMvc = MockMvcBuilders.standaloneSetup(fechamentoBimestreResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restFechamentoBimestreMockMvc.perform(get("/api/fechamento-bimestres?eagerload=true"))
        .andExpect(status().isOk());

        verify(fechamentoBimestreServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllFechamentoBimestresWithEagerRelationshipsIsNotEnabled() throws Exception {
        FechamentoBimestreResource fechamentoBimestreResource = new FechamentoBimestreResource(fechamentoBimestreServiceMock);
            when(fechamentoBimestreServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restFechamentoBimestreMockMvc = MockMvcBuilders.standaloneSetup(fechamentoBimestreResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restFechamentoBimestreMockMvc.perform(get("/api/fechamento-bimestres?eagerload=true"))
        .andExpect(status().isOk());

            verify(fechamentoBimestreServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getFechamentoBimestre() throws Exception {
        // Initialize the database
        fechamentoBimestreRepository.saveAndFlush(fechamentoBimestre);

        // Get the fechamentoBimestre
        restFechamentoBimestreMockMvc.perform(get("/api/fechamento-bimestres/{id}", fechamentoBimestre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fechamentoBimestre.getId().intValue()))
            .andExpect(jsonPath("$.frequencia").value(DEFAULT_FREQUENCIA))
            .andExpect(jsonPath("$.ausencia").value(DEFAULT_AUSENCIA))
            .andExpect(jsonPath("$.totalAtividades").value(DEFAULT_TOTAL_ATIVIDADES))
            .andExpect(jsonPath("$.porcentagemFrequencia").value(DEFAULT_PORCENTAGEM_FREQUENCIA))
            .andExpect(jsonPath("$.porcentagemAusequencia").value(DEFAULT_PORCENTAGEM_AUSEQUENCIA));
    }

    @Test
    @Transactional
    public void getNonExistingFechamentoBimestre() throws Exception {
        // Get the fechamentoBimestre
        restFechamentoBimestreMockMvc.perform(get("/api/fechamento-bimestres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFechamentoBimestre() throws Exception {
        // Initialize the database
        fechamentoBimestreService.save(fechamentoBimestre);

        int databaseSizeBeforeUpdate = fechamentoBimestreRepository.findAll().size();

        // Update the fechamentoBimestre
        FechamentoBimestre updatedFechamentoBimestre = fechamentoBimestreRepository.findById(fechamentoBimestre.getId()).get();
        // Disconnect from session so that the updates on updatedFechamentoBimestre are not directly saved in db
        em.detach(updatedFechamentoBimestre);
        updatedFechamentoBimestre
            .frequencia(UPDATED_FREQUENCIA)
            .ausencia(UPDATED_AUSENCIA)
            .totalAtividades(UPDATED_TOTAL_ATIVIDADES)
            .porcentagemFrequencia(UPDATED_PORCENTAGEM_FREQUENCIA)
            .porcentagemAusequencia(UPDATED_PORCENTAGEM_AUSEQUENCIA);

        restFechamentoBimestreMockMvc.perform(put("/api/fechamento-bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFechamentoBimestre)))
            .andExpect(status().isOk());

        // Validate the FechamentoBimestre in the database
        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeUpdate);
        FechamentoBimestre testFechamentoBimestre = fechamentoBimestreList.get(fechamentoBimestreList.size() - 1);
        assertThat(testFechamentoBimestre.getFrequencia()).isEqualTo(UPDATED_FREQUENCIA);
        assertThat(testFechamentoBimestre.getAusencia()).isEqualTo(UPDATED_AUSENCIA);
        assertThat(testFechamentoBimestre.getTotalAtividades()).isEqualTo(UPDATED_TOTAL_ATIVIDADES);
        assertThat(testFechamentoBimestre.getPorcentagemFrequencia()).isEqualTo(UPDATED_PORCENTAGEM_FREQUENCIA);
        assertThat(testFechamentoBimestre.getPorcentagemAusequencia()).isEqualTo(UPDATED_PORCENTAGEM_AUSEQUENCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingFechamentoBimestre() throws Exception {
        int databaseSizeBeforeUpdate = fechamentoBimestreRepository.findAll().size();

        // Create the FechamentoBimestre

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFechamentoBimestreMockMvc.perform(put("/api/fechamento-bimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fechamentoBimestre)))
            .andExpect(status().isBadRequest());

        // Validate the FechamentoBimestre in the database
        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFechamentoBimestre() throws Exception {
        // Initialize the database
        fechamentoBimestreService.save(fechamentoBimestre);

        int databaseSizeBeforeDelete = fechamentoBimestreRepository.findAll().size();

        // Get the fechamentoBimestre
        restFechamentoBimestreMockMvc.perform(delete("/api/fechamento-bimestres/{id}", fechamentoBimestre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FechamentoBimestre> fechamentoBimestreList = fechamentoBimestreRepository.findAll();
        assertThat(fechamentoBimestreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FechamentoBimestre.class);
        FechamentoBimestre fechamentoBimestre1 = new FechamentoBimestre();
        fechamentoBimestre1.setId(1L);
        FechamentoBimestre fechamentoBimestre2 = new FechamentoBimestre();
        fechamentoBimestre2.setId(fechamentoBimestre1.getId());
        assertThat(fechamentoBimestre1).isEqualTo(fechamentoBimestre2);
        fechamentoBimestre2.setId(2L);
        assertThat(fechamentoBimestre1).isNotEqualTo(fechamentoBimestre2);
        fechamentoBimestre1.setId(null);
        assertThat(fechamentoBimestre1).isNotEqualTo(fechamentoBimestre2);
    }
}
