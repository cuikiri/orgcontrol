package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.MateriaAcompanhamento;
import br.com.jhisolution.ong.control.repository.MateriaAcompanhamentoRepository;
import br.com.jhisolution.ong.control.service.MateriaAcompanhamentoService;
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
 * Test class for the MateriaAcompanhamentoResource REST controller.
 *
 * @see MateriaAcompanhamentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class MateriaAcompanhamentoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private MateriaAcompanhamentoRepository materiaAcompanhamentoRepository;

    @Autowired
    private MateriaAcompanhamentoService materiaAcompanhamentoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMateriaAcompanhamentoMockMvc;

    private MateriaAcompanhamento materiaAcompanhamento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MateriaAcompanhamentoResource materiaAcompanhamentoResource = new MateriaAcompanhamentoResource(materiaAcompanhamentoService);
        this.restMateriaAcompanhamentoMockMvc = MockMvcBuilders.standaloneSetup(materiaAcompanhamentoResource)
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
    public static MateriaAcompanhamento createEntity(EntityManager em) {
        MateriaAcompanhamento materiaAcompanhamento = new MateriaAcompanhamento()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return materiaAcompanhamento;
    }

    @Before
    public void initTest() {
        materiaAcompanhamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createMateriaAcompanhamento() throws Exception {
        int databaseSizeBeforeCreate = materiaAcompanhamentoRepository.findAll().size();

        // Create the MateriaAcompanhamento
        restMateriaAcompanhamentoMockMvc.perform(post("/api/materia-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materiaAcompanhamento)))
            .andExpect(status().isCreated());

        // Validate the MateriaAcompanhamento in the database
        List<MateriaAcompanhamento> materiaAcompanhamentoList = materiaAcompanhamentoRepository.findAll();
        assertThat(materiaAcompanhamentoList).hasSize(databaseSizeBeforeCreate + 1);
        MateriaAcompanhamento testMateriaAcompanhamento = materiaAcompanhamentoList.get(materiaAcompanhamentoList.size() - 1);
        assertThat(testMateriaAcompanhamento.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testMateriaAcompanhamento.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createMateriaAcompanhamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = materiaAcompanhamentoRepository.findAll().size();

        // Create the MateriaAcompanhamento with an existing ID
        materiaAcompanhamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMateriaAcompanhamentoMockMvc.perform(post("/api/materia-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materiaAcompanhamento)))
            .andExpect(status().isBadRequest());

        // Validate the MateriaAcompanhamento in the database
        List<MateriaAcompanhamento> materiaAcompanhamentoList = materiaAcompanhamentoRepository.findAll();
        assertThat(materiaAcompanhamentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = materiaAcompanhamentoRepository.findAll().size();
        // set the field null
        materiaAcompanhamento.setNome(null);

        // Create the MateriaAcompanhamento, which fails.

        restMateriaAcompanhamentoMockMvc.perform(post("/api/materia-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materiaAcompanhamento)))
            .andExpect(status().isBadRequest());

        List<MateriaAcompanhamento> materiaAcompanhamentoList = materiaAcompanhamentoRepository.findAll();
        assertThat(materiaAcompanhamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMateriaAcompanhamentos() throws Exception {
        // Initialize the database
        materiaAcompanhamentoRepository.saveAndFlush(materiaAcompanhamento);

        // Get all the materiaAcompanhamentoList
        restMateriaAcompanhamentoMockMvc.perform(get("/api/materia-acompanhamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(materiaAcompanhamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getMateriaAcompanhamento() throws Exception {
        // Initialize the database
        materiaAcompanhamentoRepository.saveAndFlush(materiaAcompanhamento);

        // Get the materiaAcompanhamento
        restMateriaAcompanhamentoMockMvc.perform(get("/api/materia-acompanhamentos/{id}", materiaAcompanhamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(materiaAcompanhamento.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMateriaAcompanhamento() throws Exception {
        // Get the materiaAcompanhamento
        restMateriaAcompanhamentoMockMvc.perform(get("/api/materia-acompanhamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMateriaAcompanhamento() throws Exception {
        // Initialize the database
        materiaAcompanhamentoService.save(materiaAcompanhamento);

        int databaseSizeBeforeUpdate = materiaAcompanhamentoRepository.findAll().size();

        // Update the materiaAcompanhamento
        MateriaAcompanhamento updatedMateriaAcompanhamento = materiaAcompanhamentoRepository.findById(materiaAcompanhamento.getId()).get();
        // Disconnect from session so that the updates on updatedMateriaAcompanhamento are not directly saved in db
        em.detach(updatedMateriaAcompanhamento);
        updatedMateriaAcompanhamento
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restMateriaAcompanhamentoMockMvc.perform(put("/api/materia-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMateriaAcompanhamento)))
            .andExpect(status().isOk());

        // Validate the MateriaAcompanhamento in the database
        List<MateriaAcompanhamento> materiaAcompanhamentoList = materiaAcompanhamentoRepository.findAll();
        assertThat(materiaAcompanhamentoList).hasSize(databaseSizeBeforeUpdate);
        MateriaAcompanhamento testMateriaAcompanhamento = materiaAcompanhamentoList.get(materiaAcompanhamentoList.size() - 1);
        assertThat(testMateriaAcompanhamento.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testMateriaAcompanhamento.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingMateriaAcompanhamento() throws Exception {
        int databaseSizeBeforeUpdate = materiaAcompanhamentoRepository.findAll().size();

        // Create the MateriaAcompanhamento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMateriaAcompanhamentoMockMvc.perform(put("/api/materia-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materiaAcompanhamento)))
            .andExpect(status().isBadRequest());

        // Validate the MateriaAcompanhamento in the database
        List<MateriaAcompanhamento> materiaAcompanhamentoList = materiaAcompanhamentoRepository.findAll();
        assertThat(materiaAcompanhamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMateriaAcompanhamento() throws Exception {
        // Initialize the database
        materiaAcompanhamentoService.save(materiaAcompanhamento);

        int databaseSizeBeforeDelete = materiaAcompanhamentoRepository.findAll().size();

        // Get the materiaAcompanhamento
        restMateriaAcompanhamentoMockMvc.perform(delete("/api/materia-acompanhamentos/{id}", materiaAcompanhamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MateriaAcompanhamento> materiaAcompanhamentoList = materiaAcompanhamentoRepository.findAll();
        assertThat(materiaAcompanhamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MateriaAcompanhamento.class);
        MateriaAcompanhamento materiaAcompanhamento1 = new MateriaAcompanhamento();
        materiaAcompanhamento1.setId(1L);
        MateriaAcompanhamento materiaAcompanhamento2 = new MateriaAcompanhamento();
        materiaAcompanhamento2.setId(materiaAcompanhamento1.getId());
        assertThat(materiaAcompanhamento1).isEqualTo(materiaAcompanhamento2);
        materiaAcompanhamento2.setId(2L);
        assertThat(materiaAcompanhamento1).isNotEqualTo(materiaAcompanhamento2);
        materiaAcompanhamento1.setId(null);
        assertThat(materiaAcompanhamento1).isNotEqualTo(materiaAcompanhamento2);
    }
}
