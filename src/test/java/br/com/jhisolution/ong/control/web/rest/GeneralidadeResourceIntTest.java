package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Generalidade;
import br.com.jhisolution.ong.control.repository.GeneralidadeRepository;
import br.com.jhisolution.ong.control.service.GeneralidadeService;
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
 * Test class for the GeneralidadeResource REST controller.
 *
 * @see GeneralidadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class GeneralidadeResourceIntTest {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private GeneralidadeRepository generalidadeRepository;

    @Autowired
    private GeneralidadeService generalidadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGeneralidadeMockMvc;

    private Generalidade generalidade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeneralidadeResource generalidadeResource = new GeneralidadeResource(generalidadeService);
        this.restGeneralidadeMockMvc = MockMvcBuilders.standaloneSetup(generalidadeResource)
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
    public static Generalidade createEntity(EntityManager em) {
        Generalidade generalidade = new Generalidade()
            .codigo(DEFAULT_CODIGO)
            .nome(DEFAULT_NOME);
        return generalidade;
    }

    @Before
    public void initTest() {
        generalidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeneralidade() throws Exception {
        int databaseSizeBeforeCreate = generalidadeRepository.findAll().size();

        // Create the Generalidade
        restGeneralidadeMockMvc.perform(post("/api/generalidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalidade)))
            .andExpect(status().isCreated());

        // Validate the Generalidade in the database
        List<Generalidade> generalidadeList = generalidadeRepository.findAll();
        assertThat(generalidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Generalidade testGeneralidade = generalidadeList.get(generalidadeList.size() - 1);
        assertThat(testGeneralidade.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testGeneralidade.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createGeneralidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = generalidadeRepository.findAll().size();

        // Create the Generalidade with an existing ID
        generalidade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeneralidadeMockMvc.perform(post("/api/generalidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalidade)))
            .andExpect(status().isBadRequest());

        // Validate the Generalidade in the database
        List<Generalidade> generalidadeList = generalidadeRepository.findAll();
        assertThat(generalidadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalidadeRepository.findAll().size();
        // set the field null
        generalidade.setCodigo(null);

        // Create the Generalidade, which fails.

        restGeneralidadeMockMvc.perform(post("/api/generalidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalidade)))
            .andExpect(status().isBadRequest());

        List<Generalidade> generalidadeList = generalidadeRepository.findAll();
        assertThat(generalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeneralidades() throws Exception {
        // Initialize the database
        generalidadeRepository.saveAndFlush(generalidade);

        // Get all the generalidadeList
        restGeneralidadeMockMvc.perform(get("/api/generalidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(generalidade.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getGeneralidade() throws Exception {
        // Initialize the database
        generalidadeRepository.saveAndFlush(generalidade);

        // Get the generalidade
        restGeneralidadeMockMvc.perform(get("/api/generalidades/{id}", generalidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(generalidade.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGeneralidade() throws Exception {
        // Get the generalidade
        restGeneralidadeMockMvc.perform(get("/api/generalidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeneralidade() throws Exception {
        // Initialize the database
        generalidadeService.save(generalidade);

        int databaseSizeBeforeUpdate = generalidadeRepository.findAll().size();

        // Update the generalidade
        Generalidade updatedGeneralidade = generalidadeRepository.findById(generalidade.getId()).get();
        // Disconnect from session so that the updates on updatedGeneralidade are not directly saved in db
        em.detach(updatedGeneralidade);
        updatedGeneralidade
            .codigo(UPDATED_CODIGO)
            .nome(UPDATED_NOME);

        restGeneralidadeMockMvc.perform(put("/api/generalidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGeneralidade)))
            .andExpect(status().isOk());

        // Validate the Generalidade in the database
        List<Generalidade> generalidadeList = generalidadeRepository.findAll();
        assertThat(generalidadeList).hasSize(databaseSizeBeforeUpdate);
        Generalidade testGeneralidade = generalidadeList.get(generalidadeList.size() - 1);
        assertThat(testGeneralidade.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testGeneralidade.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingGeneralidade() throws Exception {
        int databaseSizeBeforeUpdate = generalidadeRepository.findAll().size();

        // Create the Generalidade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeneralidadeMockMvc.perform(put("/api/generalidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalidade)))
            .andExpect(status().isBadRequest());

        // Validate the Generalidade in the database
        List<Generalidade> generalidadeList = generalidadeRepository.findAll();
        assertThat(generalidadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeneralidade() throws Exception {
        // Initialize the database
        generalidadeService.save(generalidade);

        int databaseSizeBeforeDelete = generalidadeRepository.findAll().size();

        // Get the generalidade
        restGeneralidadeMockMvc.perform(delete("/api/generalidades/{id}", generalidade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Generalidade> generalidadeList = generalidadeRepository.findAll();
        assertThat(generalidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Generalidade.class);
        Generalidade generalidade1 = new Generalidade();
        generalidade1.setId(1L);
        Generalidade generalidade2 = new Generalidade();
        generalidade2.setId(generalidade1.getId());
        assertThat(generalidade1).isEqualTo(generalidade2);
        generalidade2.setId(2L);
        assertThat(generalidade1).isNotEqualTo(generalidade2);
        generalidade1.setId(null);
        assertThat(generalidade1).isNotEqualTo(generalidade2);
    }
}
