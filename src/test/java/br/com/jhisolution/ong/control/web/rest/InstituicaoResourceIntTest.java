package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Instituicao;
import br.com.jhisolution.ong.control.repository.InstituicaoRepository;
import br.com.jhisolution.ong.control.service.InstituicaoService;
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
 * Test class for the InstituicaoResource REST controller.
 *
 * @see InstituicaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class InstituicaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_RAZAO_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAZAO_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ = "BBBBBBBBBB";

    private static final String DEFAULT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_BLOG = "AAAAAAAAAA";
    private static final String UPDATED_BLOG = "BBBBBBBBBB";

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private InstituicaoService instituicaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInstituicaoMockMvc;

    private Instituicao instituicao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InstituicaoResource instituicaoResource = new InstituicaoResource(instituicaoService);
        this.restInstituicaoMockMvc = MockMvcBuilders.standaloneSetup(instituicaoResource)
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
    public static Instituicao createEntity(EntityManager em) {
        Instituicao instituicao = new Instituicao()
            .nome(DEFAULT_NOME)
            .razaoSocial(DEFAULT_RAZAO_SOCIAL)
            .cnpj(DEFAULT_CNPJ)
            .site(DEFAULT_SITE)
            .blog(DEFAULT_BLOG);
        return instituicao;
    }

    @Before
    public void initTest() {
        instituicao = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstituicao() throws Exception {
        int databaseSizeBeforeCreate = instituicaoRepository.findAll().size();

        // Create the Instituicao
        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isCreated());

        // Validate the Instituicao in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeCreate + 1);
        Instituicao testInstituicao = instituicaoList.get(instituicaoList.size() - 1);
        assertThat(testInstituicao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testInstituicao.getRazaoSocial()).isEqualTo(DEFAULT_RAZAO_SOCIAL);
        assertThat(testInstituicao.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testInstituicao.getSite()).isEqualTo(DEFAULT_SITE);
        assertThat(testInstituicao.getBlog()).isEqualTo(DEFAULT_BLOG);
    }

    @Test
    @Transactional
    public void createInstituicaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = instituicaoRepository.findAll().size();

        // Create the Instituicao with an existing ID
        instituicao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isBadRequest());

        // Validate the Instituicao in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = instituicaoRepository.findAll().size();
        // set the field null
        instituicao.setNome(null);

        // Create the Instituicao, which fails.

        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isBadRequest());

        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstituicaos() throws Exception {
        // Initialize the database
        instituicaoRepository.saveAndFlush(instituicao);

        // Get all the instituicaoList
        restInstituicaoMockMvc.perform(get("/api/instituicaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(instituicao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].razaoSocial").value(hasItem(DEFAULT_RAZAO_SOCIAL.toString())))
            .andExpect(jsonPath("$.[*].cnpj").value(hasItem(DEFAULT_CNPJ.toString())))
            .andExpect(jsonPath("$.[*].site").value(hasItem(DEFAULT_SITE.toString())))
            .andExpect(jsonPath("$.[*].blog").value(hasItem(DEFAULT_BLOG.toString())));
    }
    
    @Test
    @Transactional
    public void getInstituicao() throws Exception {
        // Initialize the database
        instituicaoRepository.saveAndFlush(instituicao);

        // Get the instituicao
        restInstituicaoMockMvc.perform(get("/api/instituicaos/{id}", instituicao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(instituicao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.razaoSocial").value(DEFAULT_RAZAO_SOCIAL.toString()))
            .andExpect(jsonPath("$.cnpj").value(DEFAULT_CNPJ.toString()))
            .andExpect(jsonPath("$.site").value(DEFAULT_SITE.toString()))
            .andExpect(jsonPath("$.blog").value(DEFAULT_BLOG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInstituicao() throws Exception {
        // Get the instituicao
        restInstituicaoMockMvc.perform(get("/api/instituicaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstituicao() throws Exception {
        // Initialize the database
        instituicaoService.save(instituicao);

        int databaseSizeBeforeUpdate = instituicaoRepository.findAll().size();

        // Update the instituicao
        Instituicao updatedInstituicao = instituicaoRepository.findById(instituicao.getId()).get();
        // Disconnect from session so that the updates on updatedInstituicao are not directly saved in db
        em.detach(updatedInstituicao);
        updatedInstituicao
            .nome(UPDATED_NOME)
            .razaoSocial(UPDATED_RAZAO_SOCIAL)
            .cnpj(UPDATED_CNPJ)
            .site(UPDATED_SITE)
            .blog(UPDATED_BLOG);

        restInstituicaoMockMvc.perform(put("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstituicao)))
            .andExpect(status().isOk());

        // Validate the Instituicao in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeUpdate);
        Instituicao testInstituicao = instituicaoList.get(instituicaoList.size() - 1);
        assertThat(testInstituicao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testInstituicao.getRazaoSocial()).isEqualTo(UPDATED_RAZAO_SOCIAL);
        assertThat(testInstituicao.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testInstituicao.getSite()).isEqualTo(UPDATED_SITE);
        assertThat(testInstituicao.getBlog()).isEqualTo(UPDATED_BLOG);
    }

    @Test
    @Transactional
    public void updateNonExistingInstituicao() throws Exception {
        int databaseSizeBeforeUpdate = instituicaoRepository.findAll().size();

        // Create the Instituicao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstituicaoMockMvc.perform(put("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isBadRequest());

        // Validate the Instituicao in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstituicao() throws Exception {
        // Initialize the database
        instituicaoService.save(instituicao);

        int databaseSizeBeforeDelete = instituicaoRepository.findAll().size();

        // Get the instituicao
        restInstituicaoMockMvc.perform(delete("/api/instituicaos/{id}", instituicao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Instituicao.class);
        Instituicao instituicao1 = new Instituicao();
        instituicao1.setId(1L);
        Instituicao instituicao2 = new Instituicao();
        instituicao2.setId(instituicao1.getId());
        assertThat(instituicao1).isEqualTo(instituicao2);
        instituicao2.setId(2L);
        assertThat(instituicao1).isNotEqualTo(instituicao2);
        instituicao1.setId(null);
        assertThat(instituicao1).isNotEqualTo(instituicao2);
    }
}
