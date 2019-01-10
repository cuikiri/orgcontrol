package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ConteudoProgramatico;
import br.com.jhisolution.ong.control.repository.ConteudoProgramaticoRepository;
import br.com.jhisolution.ong.control.service.ConteudoProgramaticoService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConteudoProgramaticoResource REST controller.
 *
 * @see ConteudoProgramaticoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ConteudoProgramaticoResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private ConteudoProgramaticoRepository conteudoProgramaticoRepository;

    @Autowired
    private ConteudoProgramaticoService conteudoProgramaticoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConteudoProgramaticoMockMvc;

    private ConteudoProgramatico conteudoProgramatico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConteudoProgramaticoResource conteudoProgramaticoResource = new ConteudoProgramaticoResource(conteudoProgramaticoService);
        this.restConteudoProgramaticoMockMvc = MockMvcBuilders.standaloneSetup(conteudoProgramaticoResource)
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
    public static ConteudoProgramatico createEntity(EntityManager em) {
        ConteudoProgramatico conteudoProgramatico = new ConteudoProgramatico()
            .data(DEFAULT_DATA)
            .descricao(DEFAULT_DESCRICAO);
        return conteudoProgramatico;
    }

    @Before
    public void initTest() {
        conteudoProgramatico = createEntity(em);
    }

    @Test
    @Transactional
    public void createConteudoProgramatico() throws Exception {
        int databaseSizeBeforeCreate = conteudoProgramaticoRepository.findAll().size();

        // Create the ConteudoProgramatico
        restConteudoProgramaticoMockMvc.perform(post("/api/conteudo-programaticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoProgramatico)))
            .andExpect(status().isCreated());

        // Validate the ConteudoProgramatico in the database
        List<ConteudoProgramatico> conteudoProgramaticoList = conteudoProgramaticoRepository.findAll();
        assertThat(conteudoProgramaticoList).hasSize(databaseSizeBeforeCreate + 1);
        ConteudoProgramatico testConteudoProgramatico = conteudoProgramaticoList.get(conteudoProgramaticoList.size() - 1);
        assertThat(testConteudoProgramatico.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testConteudoProgramatico.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createConteudoProgramaticoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conteudoProgramaticoRepository.findAll().size();

        // Create the ConteudoProgramatico with an existing ID
        conteudoProgramatico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConteudoProgramaticoMockMvc.perform(post("/api/conteudo-programaticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoProgramatico)))
            .andExpect(status().isBadRequest());

        // Validate the ConteudoProgramatico in the database
        List<ConteudoProgramatico> conteudoProgramaticoList = conteudoProgramaticoRepository.findAll();
        assertThat(conteudoProgramaticoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = conteudoProgramaticoRepository.findAll().size();
        // set the field null
        conteudoProgramatico.setData(null);

        // Create the ConteudoProgramatico, which fails.

        restConteudoProgramaticoMockMvc.perform(post("/api/conteudo-programaticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoProgramatico)))
            .andExpect(status().isBadRequest());

        List<ConteudoProgramatico> conteudoProgramaticoList = conteudoProgramaticoRepository.findAll();
        assertThat(conteudoProgramaticoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = conteudoProgramaticoRepository.findAll().size();
        // set the field null
        conteudoProgramatico.setDescricao(null);

        // Create the ConteudoProgramatico, which fails.

        restConteudoProgramaticoMockMvc.perform(post("/api/conteudo-programaticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoProgramatico)))
            .andExpect(status().isBadRequest());

        List<ConteudoProgramatico> conteudoProgramaticoList = conteudoProgramaticoRepository.findAll();
        assertThat(conteudoProgramaticoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConteudoProgramaticos() throws Exception {
        // Initialize the database
        conteudoProgramaticoRepository.saveAndFlush(conteudoProgramatico);

        // Get all the conteudoProgramaticoList
        restConteudoProgramaticoMockMvc.perform(get("/api/conteudo-programaticos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conteudoProgramatico.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }
    
    @Test
    @Transactional
    public void getConteudoProgramatico() throws Exception {
        // Initialize the database
        conteudoProgramaticoRepository.saveAndFlush(conteudoProgramatico);

        // Get the conteudoProgramatico
        restConteudoProgramaticoMockMvc.perform(get("/api/conteudo-programaticos/{id}", conteudoProgramatico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conteudoProgramatico.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConteudoProgramatico() throws Exception {
        // Get the conteudoProgramatico
        restConteudoProgramaticoMockMvc.perform(get("/api/conteudo-programaticos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConteudoProgramatico() throws Exception {
        // Initialize the database
        conteudoProgramaticoService.save(conteudoProgramatico);

        int databaseSizeBeforeUpdate = conteudoProgramaticoRepository.findAll().size();

        // Update the conteudoProgramatico
        ConteudoProgramatico updatedConteudoProgramatico = conteudoProgramaticoRepository.findById(conteudoProgramatico.getId()).get();
        // Disconnect from session so that the updates on updatedConteudoProgramatico are not directly saved in db
        em.detach(updatedConteudoProgramatico);
        updatedConteudoProgramatico
            .data(UPDATED_DATA)
            .descricao(UPDATED_DESCRICAO);

        restConteudoProgramaticoMockMvc.perform(put("/api/conteudo-programaticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConteudoProgramatico)))
            .andExpect(status().isOk());

        // Validate the ConteudoProgramatico in the database
        List<ConteudoProgramatico> conteudoProgramaticoList = conteudoProgramaticoRepository.findAll();
        assertThat(conteudoProgramaticoList).hasSize(databaseSizeBeforeUpdate);
        ConteudoProgramatico testConteudoProgramatico = conteudoProgramaticoList.get(conteudoProgramaticoList.size() - 1);
        assertThat(testConteudoProgramatico.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testConteudoProgramatico.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingConteudoProgramatico() throws Exception {
        int databaseSizeBeforeUpdate = conteudoProgramaticoRepository.findAll().size();

        // Create the ConteudoProgramatico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConteudoProgramaticoMockMvc.perform(put("/api/conteudo-programaticos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoProgramatico)))
            .andExpect(status().isBadRequest());

        // Validate the ConteudoProgramatico in the database
        List<ConteudoProgramatico> conteudoProgramaticoList = conteudoProgramaticoRepository.findAll();
        assertThat(conteudoProgramaticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConteudoProgramatico() throws Exception {
        // Initialize the database
        conteudoProgramaticoService.save(conteudoProgramatico);

        int databaseSizeBeforeDelete = conteudoProgramaticoRepository.findAll().size();

        // Get the conteudoProgramatico
        restConteudoProgramaticoMockMvc.perform(delete("/api/conteudo-programaticos/{id}", conteudoProgramatico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConteudoProgramatico> conteudoProgramaticoList = conteudoProgramaticoRepository.findAll();
        assertThat(conteudoProgramaticoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConteudoProgramatico.class);
        ConteudoProgramatico conteudoProgramatico1 = new ConteudoProgramatico();
        conteudoProgramatico1.setId(1L);
        ConteudoProgramatico conteudoProgramatico2 = new ConteudoProgramatico();
        conteudoProgramatico2.setId(conteudoProgramatico1.getId());
        assertThat(conteudoProgramatico1).isEqualTo(conteudoProgramatico2);
        conteudoProgramatico2.setId(2L);
        assertThat(conteudoProgramatico1).isNotEqualTo(conteudoProgramatico2);
        conteudoProgramatico1.setId(null);
        assertThat(conteudoProgramatico1).isNotEqualTo(conteudoProgramatico2);
    }
}
