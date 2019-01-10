package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Locomocao;
import br.com.jhisolution.ong.control.repository.LocomocaoRepository;
import br.com.jhisolution.ong.control.service.LocomocaoService;
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
 * Test class for the LocomocaoResource REST controller.
 *
 * @see LocomocaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class LocomocaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private LocomocaoRepository locomocaoRepository;

    @Autowired
    private LocomocaoService locomocaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLocomocaoMockMvc;

    private Locomocao locomocao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocomocaoResource locomocaoResource = new LocomocaoResource(locomocaoService);
        this.restLocomocaoMockMvc = MockMvcBuilders.standaloneSetup(locomocaoResource)
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
    public static Locomocao createEntity(EntityManager em) {
        Locomocao locomocao = new Locomocao()
            .nome(DEFAULT_NOME);
        return locomocao;
    }

    @Before
    public void initTest() {
        locomocao = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocomocao() throws Exception {
        int databaseSizeBeforeCreate = locomocaoRepository.findAll().size();

        // Create the Locomocao
        restLocomocaoMockMvc.perform(post("/api/locomocaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locomocao)))
            .andExpect(status().isCreated());

        // Validate the Locomocao in the database
        List<Locomocao> locomocaoList = locomocaoRepository.findAll();
        assertThat(locomocaoList).hasSize(databaseSizeBeforeCreate + 1);
        Locomocao testLocomocao = locomocaoList.get(locomocaoList.size() - 1);
        assertThat(testLocomocao.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createLocomocaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locomocaoRepository.findAll().size();

        // Create the Locomocao with an existing ID
        locomocao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocomocaoMockMvc.perform(post("/api/locomocaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locomocao)))
            .andExpect(status().isBadRequest());

        // Validate the Locomocao in the database
        List<Locomocao> locomocaoList = locomocaoRepository.findAll();
        assertThat(locomocaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = locomocaoRepository.findAll().size();
        // set the field null
        locomocao.setNome(null);

        // Create the Locomocao, which fails.

        restLocomocaoMockMvc.perform(post("/api/locomocaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locomocao)))
            .andExpect(status().isBadRequest());

        List<Locomocao> locomocaoList = locomocaoRepository.findAll();
        assertThat(locomocaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLocomocaos() throws Exception {
        // Initialize the database
        locomocaoRepository.saveAndFlush(locomocao);

        // Get all the locomocaoList
        restLocomocaoMockMvc.perform(get("/api/locomocaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locomocao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getLocomocao() throws Exception {
        // Initialize the database
        locomocaoRepository.saveAndFlush(locomocao);

        // Get the locomocao
        restLocomocaoMockMvc.perform(get("/api/locomocaos/{id}", locomocao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locomocao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLocomocao() throws Exception {
        // Get the locomocao
        restLocomocaoMockMvc.perform(get("/api/locomocaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocomocao() throws Exception {
        // Initialize the database
        locomocaoService.save(locomocao);

        int databaseSizeBeforeUpdate = locomocaoRepository.findAll().size();

        // Update the locomocao
        Locomocao updatedLocomocao = locomocaoRepository.findById(locomocao.getId()).get();
        // Disconnect from session so that the updates on updatedLocomocao are not directly saved in db
        em.detach(updatedLocomocao);
        updatedLocomocao
            .nome(UPDATED_NOME);

        restLocomocaoMockMvc.perform(put("/api/locomocaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocomocao)))
            .andExpect(status().isOk());

        // Validate the Locomocao in the database
        List<Locomocao> locomocaoList = locomocaoRepository.findAll();
        assertThat(locomocaoList).hasSize(databaseSizeBeforeUpdate);
        Locomocao testLocomocao = locomocaoList.get(locomocaoList.size() - 1);
        assertThat(testLocomocao.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingLocomocao() throws Exception {
        int databaseSizeBeforeUpdate = locomocaoRepository.findAll().size();

        // Create the Locomocao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocomocaoMockMvc.perform(put("/api/locomocaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locomocao)))
            .andExpect(status().isBadRequest());

        // Validate the Locomocao in the database
        List<Locomocao> locomocaoList = locomocaoRepository.findAll();
        assertThat(locomocaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocomocao() throws Exception {
        // Initialize the database
        locomocaoService.save(locomocao);

        int databaseSizeBeforeDelete = locomocaoRepository.findAll().size();

        // Get the locomocao
        restLocomocaoMockMvc.perform(delete("/api/locomocaos/{id}", locomocao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Locomocao> locomocaoList = locomocaoRepository.findAll();
        assertThat(locomocaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Locomocao.class);
        Locomocao locomocao1 = new Locomocao();
        locomocao1.setId(1L);
        Locomocao locomocao2 = new Locomocao();
        locomocao2.setId(locomocao1.getId());
        assertThat(locomocao1).isEqualTo(locomocao2);
        locomocao2.setId(2L);
        assertThat(locomocao1).isNotEqualTo(locomocao2);
        locomocao1.setId(null);
        assertThat(locomocao1).isNotEqualTo(locomocao2);
    }
}
