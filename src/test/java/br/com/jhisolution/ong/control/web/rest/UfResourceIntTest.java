package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Uf;
import br.com.jhisolution.ong.control.repository.UfRepository;
import br.com.jhisolution.ong.control.service.UfService;
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

import br.com.jhisolution.ong.control.domain.enumeration.Regiao;
/**
 * Test class for the UfResource REST controller.
 *
 * @see UfResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class UfResourceIntTest {

    private static final String DEFAULT_NOME = "AA";
    private static final String UPDATED_NOME = "BB";

    private static final String DEFAULT_ESTDO = "AAAAAAAAAA";
    private static final String UPDATED_ESTDO = "BBBBBBBBBB";

    private static final Regiao DEFAULT_REGIAO = Regiao.CENTRAL;
    private static final Regiao UPDATED_REGIAO = Regiao.LESTE;

    @Autowired
    private UfRepository ufRepository;

    @Autowired
    private UfService ufService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUfMockMvc;

    private Uf uf;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UfResource ufResource = new UfResource(ufService);
        this.restUfMockMvc = MockMvcBuilders.standaloneSetup(ufResource)
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
    public static Uf createEntity(EntityManager em) {
        Uf uf = new Uf()
            .nome(DEFAULT_NOME)
            .estdo(DEFAULT_ESTDO)
            .regiao(DEFAULT_REGIAO);
        return uf;
    }

    @Before
    public void initTest() {
        uf = createEntity(em);
    }

    @Test
    @Transactional
    public void createUf() throws Exception {
        int databaseSizeBeforeCreate = ufRepository.findAll().size();

        // Create the Uf
        restUfMockMvc.perform(post("/api/ufs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isCreated());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeCreate + 1);
        Uf testUf = ufList.get(ufList.size() - 1);
        assertThat(testUf.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testUf.getEstdo()).isEqualTo(DEFAULT_ESTDO);
        assertThat(testUf.getRegiao()).isEqualTo(DEFAULT_REGIAO);
    }

    @Test
    @Transactional
    public void createUfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ufRepository.findAll().size();

        // Create the Uf with an existing ID
        uf.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUfMockMvc.perform(post("/api/ufs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isBadRequest());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = ufRepository.findAll().size();
        // set the field null
        uf.setNome(null);

        // Create the Uf, which fails.

        restUfMockMvc.perform(post("/api/ufs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isBadRequest());

        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstdoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ufRepository.findAll().size();
        // set the field null
        uf.setEstdo(null);

        // Create the Uf, which fails.

        restUfMockMvc.perform(post("/api/ufs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isBadRequest());

        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegiaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ufRepository.findAll().size();
        // set the field null
        uf.setRegiao(null);

        // Create the Uf, which fails.

        restUfMockMvc.perform(post("/api/ufs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isBadRequest());

        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUfs() throws Exception {
        // Initialize the database
        ufRepository.saveAndFlush(uf);

        // Get all the ufList
        restUfMockMvc.perform(get("/api/ufs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uf.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].estdo").value(hasItem(DEFAULT_ESTDO.toString())))
            .andExpect(jsonPath("$.[*].regiao").value(hasItem(DEFAULT_REGIAO.toString())));
    }
    
    @Test
    @Transactional
    public void getUf() throws Exception {
        // Initialize the database
        ufRepository.saveAndFlush(uf);

        // Get the uf
        restUfMockMvc.perform(get("/api/ufs/{id}", uf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uf.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.estdo").value(DEFAULT_ESTDO.toString()))
            .andExpect(jsonPath("$.regiao").value(DEFAULT_REGIAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUf() throws Exception {
        // Get the uf
        restUfMockMvc.perform(get("/api/ufs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUf() throws Exception {
        // Initialize the database
        ufService.save(uf);

        int databaseSizeBeforeUpdate = ufRepository.findAll().size();

        // Update the uf
        Uf updatedUf = ufRepository.findById(uf.getId()).get();
        // Disconnect from session so that the updates on updatedUf are not directly saved in db
        em.detach(updatedUf);
        updatedUf
            .nome(UPDATED_NOME)
            .estdo(UPDATED_ESTDO)
            .regiao(UPDATED_REGIAO);

        restUfMockMvc.perform(put("/api/ufs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUf)))
            .andExpect(status().isOk());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeUpdate);
        Uf testUf = ufList.get(ufList.size() - 1);
        assertThat(testUf.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testUf.getEstdo()).isEqualTo(UPDATED_ESTDO);
        assertThat(testUf.getRegiao()).isEqualTo(UPDATED_REGIAO);
    }

    @Test
    @Transactional
    public void updateNonExistingUf() throws Exception {
        int databaseSizeBeforeUpdate = ufRepository.findAll().size();

        // Create the Uf

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUfMockMvc.perform(put("/api/ufs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isBadRequest());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUf() throws Exception {
        // Initialize the database
        ufService.save(uf);

        int databaseSizeBeforeDelete = ufRepository.findAll().size();

        // Get the uf
        restUfMockMvc.perform(delete("/api/ufs/{id}", uf.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Uf.class);
        Uf uf1 = new Uf();
        uf1.setId(1L);
        Uf uf2 = new Uf();
        uf2.setId(uf1.getId());
        assertThat(uf1).isEqualTo(uf2);
        uf2.setId(2L);
        assertThat(uf1).isNotEqualTo(uf2);
        uf1.setId(null);
        assertThat(uf1).isNotEqualTo(uf2);
    }
}
