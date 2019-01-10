package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.BimestreAcompanhamento;
import br.com.jhisolution.ong.control.repository.BimestreAcompanhamentoRepository;
import br.com.jhisolution.ong.control.service.BimestreAcompanhamentoService;
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
 * Test class for the BimestreAcompanhamentoResource REST controller.
 *
 * @see BimestreAcompanhamentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class BimestreAcompanhamentoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private BimestreAcompanhamentoRepository bimestreAcompanhamentoRepository;

    @Autowired
    private BimestreAcompanhamentoService bimestreAcompanhamentoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBimestreAcompanhamentoMockMvc;

    private BimestreAcompanhamento bimestreAcompanhamento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BimestreAcompanhamentoResource bimestreAcompanhamentoResource = new BimestreAcompanhamentoResource(bimestreAcompanhamentoService);
        this.restBimestreAcompanhamentoMockMvc = MockMvcBuilders.standaloneSetup(bimestreAcompanhamentoResource)
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
    public static BimestreAcompanhamento createEntity(EntityManager em) {
        BimestreAcompanhamento bimestreAcompanhamento = new BimestreAcompanhamento()
            .nome(DEFAULT_NOME)
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS);
        return bimestreAcompanhamento;
    }

    @Before
    public void initTest() {
        bimestreAcompanhamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createBimestreAcompanhamento() throws Exception {
        int databaseSizeBeforeCreate = bimestreAcompanhamentoRepository.findAll().size();

        // Create the BimestreAcompanhamento
        restBimestreAcompanhamentoMockMvc.perform(post("/api/bimestre-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestreAcompanhamento)))
            .andExpect(status().isCreated());

        // Validate the BimestreAcompanhamento in the database
        List<BimestreAcompanhamento> bimestreAcompanhamentoList = bimestreAcompanhamentoRepository.findAll();
        assertThat(bimestreAcompanhamentoList).hasSize(databaseSizeBeforeCreate + 1);
        BimestreAcompanhamento testBimestreAcompanhamento = bimestreAcompanhamentoList.get(bimestreAcompanhamentoList.size() - 1);
        assertThat(testBimestreAcompanhamento.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testBimestreAcompanhamento.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testBimestreAcompanhamento.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createBimestreAcompanhamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bimestreAcompanhamentoRepository.findAll().size();

        // Create the BimestreAcompanhamento with an existing ID
        bimestreAcompanhamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBimestreAcompanhamentoMockMvc.perform(post("/api/bimestre-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestreAcompanhamento)))
            .andExpect(status().isBadRequest());

        // Validate the BimestreAcompanhamento in the database
        List<BimestreAcompanhamento> bimestreAcompanhamentoList = bimestreAcompanhamentoRepository.findAll();
        assertThat(bimestreAcompanhamentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreAcompanhamentoRepository.findAll().size();
        // set the field null
        bimestreAcompanhamento.setNome(null);

        // Create the BimestreAcompanhamento, which fails.

        restBimestreAcompanhamentoMockMvc.perform(post("/api/bimestre-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestreAcompanhamento)))
            .andExpect(status().isBadRequest());

        List<BimestreAcompanhamento> bimestreAcompanhamentoList = bimestreAcompanhamentoRepository.findAll();
        assertThat(bimestreAcompanhamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = bimestreAcompanhamentoRepository.findAll().size();
        // set the field null
        bimestreAcompanhamento.setNumero(null);

        // Create the BimestreAcompanhamento, which fails.

        restBimestreAcompanhamentoMockMvc.perform(post("/api/bimestre-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestreAcompanhamento)))
            .andExpect(status().isBadRequest());

        List<BimestreAcompanhamento> bimestreAcompanhamentoList = bimestreAcompanhamentoRepository.findAll();
        assertThat(bimestreAcompanhamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBimestreAcompanhamentos() throws Exception {
        // Initialize the database
        bimestreAcompanhamentoRepository.saveAndFlush(bimestreAcompanhamento);

        // Get all the bimestreAcompanhamentoList
        restBimestreAcompanhamentoMockMvc.perform(get("/api/bimestre-acompanhamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bimestreAcompanhamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getBimestreAcompanhamento() throws Exception {
        // Initialize the database
        bimestreAcompanhamentoRepository.saveAndFlush(bimestreAcompanhamento);

        // Get the bimestreAcompanhamento
        restBimestreAcompanhamentoMockMvc.perform(get("/api/bimestre-acompanhamentos/{id}", bimestreAcompanhamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bimestreAcompanhamento.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBimestreAcompanhamento() throws Exception {
        // Get the bimestreAcompanhamento
        restBimestreAcompanhamentoMockMvc.perform(get("/api/bimestre-acompanhamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBimestreAcompanhamento() throws Exception {
        // Initialize the database
        bimestreAcompanhamentoService.save(bimestreAcompanhamento);

        int databaseSizeBeforeUpdate = bimestreAcompanhamentoRepository.findAll().size();

        // Update the bimestreAcompanhamento
        BimestreAcompanhamento updatedBimestreAcompanhamento = bimestreAcompanhamentoRepository.findById(bimestreAcompanhamento.getId()).get();
        // Disconnect from session so that the updates on updatedBimestreAcompanhamento are not directly saved in db
        em.detach(updatedBimestreAcompanhamento);
        updatedBimestreAcompanhamento
            .nome(UPDATED_NOME)
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS);

        restBimestreAcompanhamentoMockMvc.perform(put("/api/bimestre-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBimestreAcompanhamento)))
            .andExpect(status().isOk());

        // Validate the BimestreAcompanhamento in the database
        List<BimestreAcompanhamento> bimestreAcompanhamentoList = bimestreAcompanhamentoRepository.findAll();
        assertThat(bimestreAcompanhamentoList).hasSize(databaseSizeBeforeUpdate);
        BimestreAcompanhamento testBimestreAcompanhamento = bimestreAcompanhamentoList.get(bimestreAcompanhamentoList.size() - 1);
        assertThat(testBimestreAcompanhamento.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testBimestreAcompanhamento.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testBimestreAcompanhamento.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingBimestreAcompanhamento() throws Exception {
        int databaseSizeBeforeUpdate = bimestreAcompanhamentoRepository.findAll().size();

        // Create the BimestreAcompanhamento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBimestreAcompanhamentoMockMvc.perform(put("/api/bimestre-acompanhamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bimestreAcompanhamento)))
            .andExpect(status().isBadRequest());

        // Validate the BimestreAcompanhamento in the database
        List<BimestreAcompanhamento> bimestreAcompanhamentoList = bimestreAcompanhamentoRepository.findAll();
        assertThat(bimestreAcompanhamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBimestreAcompanhamento() throws Exception {
        // Initialize the database
        bimestreAcompanhamentoService.save(bimestreAcompanhamento);

        int databaseSizeBeforeDelete = bimestreAcompanhamentoRepository.findAll().size();

        // Get the bimestreAcompanhamento
        restBimestreAcompanhamentoMockMvc.perform(delete("/api/bimestre-acompanhamentos/{id}", bimestreAcompanhamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BimestreAcompanhamento> bimestreAcompanhamentoList = bimestreAcompanhamentoRepository.findAll();
        assertThat(bimestreAcompanhamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BimestreAcompanhamento.class);
        BimestreAcompanhamento bimestreAcompanhamento1 = new BimestreAcompanhamento();
        bimestreAcompanhamento1.setId(1L);
        BimestreAcompanhamento bimestreAcompanhamento2 = new BimestreAcompanhamento();
        bimestreAcompanhamento2.setId(bimestreAcompanhamento1.getId());
        assertThat(bimestreAcompanhamento1).isEqualTo(bimestreAcompanhamento2);
        bimestreAcompanhamento2.setId(2L);
        assertThat(bimestreAcompanhamento1).isNotEqualTo(bimestreAcompanhamento2);
        bimestreAcompanhamento1.setId(null);
        assertThat(bimestreAcompanhamento1).isNotEqualTo(bimestreAcompanhamento2);
    }
}
