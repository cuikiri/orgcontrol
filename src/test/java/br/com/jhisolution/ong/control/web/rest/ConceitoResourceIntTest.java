package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Conceito;
import br.com.jhisolution.ong.control.repository.ConceitoRepository;
import br.com.jhisolution.ong.control.service.ConceitoService;
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
 * Test class for the ConceitoResource REST controller.
 *
 * @see ConceitoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ConceitoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Float DEFAULT_NOTA = 0F;
    private static final Float UPDATED_NOTA = 1F;

    @Autowired
    private ConceitoRepository conceitoRepository;

    @Autowired
    private ConceitoService conceitoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConceitoMockMvc;

    private Conceito conceito;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConceitoResource conceitoResource = new ConceitoResource(conceitoService);
        this.restConceitoMockMvc = MockMvcBuilders.standaloneSetup(conceitoResource)
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
    public static Conceito createEntity(EntityManager em) {
        Conceito conceito = new Conceito()
            .nome(DEFAULT_NOME)
            .nota(DEFAULT_NOTA);
        return conceito;
    }

    @Before
    public void initTest() {
        conceito = createEntity(em);
    }

    @Test
    @Transactional
    public void createConceito() throws Exception {
        int databaseSizeBeforeCreate = conceitoRepository.findAll().size();

        // Create the Conceito
        restConceitoMockMvc.perform(post("/api/conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceito)))
            .andExpect(status().isCreated());

        // Validate the Conceito in the database
        List<Conceito> conceitoList = conceitoRepository.findAll();
        assertThat(conceitoList).hasSize(databaseSizeBeforeCreate + 1);
        Conceito testConceito = conceitoList.get(conceitoList.size() - 1);
        assertThat(testConceito.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testConceito.getNota()).isEqualTo(DEFAULT_NOTA);
    }

    @Test
    @Transactional
    public void createConceitoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conceitoRepository.findAll().size();

        // Create the Conceito with an existing ID
        conceito.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConceitoMockMvc.perform(post("/api/conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceito)))
            .andExpect(status().isBadRequest());

        // Validate the Conceito in the database
        List<Conceito> conceitoList = conceitoRepository.findAll();
        assertThat(conceitoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = conceitoRepository.findAll().size();
        // set the field null
        conceito.setNome(null);

        // Create the Conceito, which fails.

        restConceitoMockMvc.perform(post("/api/conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceito)))
            .andExpect(status().isBadRequest());

        List<Conceito> conceitoList = conceitoRepository.findAll();
        assertThat(conceitoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConceitos() throws Exception {
        // Initialize the database
        conceitoRepository.saveAndFlush(conceito);

        // Get all the conceitoList
        restConceitoMockMvc.perform(get("/api/conceitos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conceito.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getConceito() throws Exception {
        // Initialize the database
        conceitoRepository.saveAndFlush(conceito);

        // Get the conceito
        restConceitoMockMvc.perform(get("/api/conceitos/{id}", conceito.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conceito.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingConceito() throws Exception {
        // Get the conceito
        restConceitoMockMvc.perform(get("/api/conceitos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConceito() throws Exception {
        // Initialize the database
        conceitoService.save(conceito);

        int databaseSizeBeforeUpdate = conceitoRepository.findAll().size();

        // Update the conceito
        Conceito updatedConceito = conceitoRepository.findById(conceito.getId()).get();
        // Disconnect from session so that the updates on updatedConceito are not directly saved in db
        em.detach(updatedConceito);
        updatedConceito
            .nome(UPDATED_NOME)
            .nota(UPDATED_NOTA);

        restConceitoMockMvc.perform(put("/api/conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConceito)))
            .andExpect(status().isOk());

        // Validate the Conceito in the database
        List<Conceito> conceitoList = conceitoRepository.findAll();
        assertThat(conceitoList).hasSize(databaseSizeBeforeUpdate);
        Conceito testConceito = conceitoList.get(conceitoList.size() - 1);
        assertThat(testConceito.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testConceito.getNota()).isEqualTo(UPDATED_NOTA);
    }

    @Test
    @Transactional
    public void updateNonExistingConceito() throws Exception {
        int databaseSizeBeforeUpdate = conceitoRepository.findAll().size();

        // Create the Conceito

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConceitoMockMvc.perform(put("/api/conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceito)))
            .andExpect(status().isBadRequest());

        // Validate the Conceito in the database
        List<Conceito> conceitoList = conceitoRepository.findAll();
        assertThat(conceitoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConceito() throws Exception {
        // Initialize the database
        conceitoService.save(conceito);

        int databaseSizeBeforeDelete = conceitoRepository.findAll().size();

        // Get the conceito
        restConceitoMockMvc.perform(delete("/api/conceitos/{id}", conceito.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Conceito> conceitoList = conceitoRepository.findAll();
        assertThat(conceitoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Conceito.class);
        Conceito conceito1 = new Conceito();
        conceito1.setId(1L);
        Conceito conceito2 = new Conceito();
        conceito2.setId(conceito1.getId());
        assertThat(conceito1).isEqualTo(conceito2);
        conceito2.setId(2L);
        assertThat(conceito1).isNotEqualTo(conceito2);
        conceito1.setId(null);
        assertThat(conceito1).isNotEqualTo(conceito2);
    }
}
