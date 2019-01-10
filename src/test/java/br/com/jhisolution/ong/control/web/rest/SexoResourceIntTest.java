package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Sexo;
import br.com.jhisolution.ong.control.repository.SexoRepository;
import br.com.jhisolution.ong.control.service.SexoService;
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
 * Test class for the SexoResource REST controller.
 *
 * @see SexoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class SexoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private SexoRepository sexoRepository;

    @Autowired
    private SexoService sexoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSexoMockMvc;

    private Sexo sexo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SexoResource sexoResource = new SexoResource(sexoService);
        this.restSexoMockMvc = MockMvcBuilders.standaloneSetup(sexoResource)
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
    public static Sexo createEntity(EntityManager em) {
        Sexo sexo = new Sexo()
            .nome(DEFAULT_NOME);
        return sexo;
    }

    @Before
    public void initTest() {
        sexo = createEntity(em);
    }

    @Test
    @Transactional
    public void createSexo() throws Exception {
        int databaseSizeBeforeCreate = sexoRepository.findAll().size();

        // Create the Sexo
        restSexoMockMvc.perform(post("/api/sexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sexo)))
            .andExpect(status().isCreated());

        // Validate the Sexo in the database
        List<Sexo> sexoList = sexoRepository.findAll();
        assertThat(sexoList).hasSize(databaseSizeBeforeCreate + 1);
        Sexo testSexo = sexoList.get(sexoList.size() - 1);
        assertThat(testSexo.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createSexoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sexoRepository.findAll().size();

        // Create the Sexo with an existing ID
        sexo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSexoMockMvc.perform(post("/api/sexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sexo)))
            .andExpect(status().isBadRequest());

        // Validate the Sexo in the database
        List<Sexo> sexoList = sexoRepository.findAll();
        assertThat(sexoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sexoRepository.findAll().size();
        // set the field null
        sexo.setNome(null);

        // Create the Sexo, which fails.

        restSexoMockMvc.perform(post("/api/sexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sexo)))
            .andExpect(status().isBadRequest());

        List<Sexo> sexoList = sexoRepository.findAll();
        assertThat(sexoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSexos() throws Exception {
        // Initialize the database
        sexoRepository.saveAndFlush(sexo);

        // Get all the sexoList
        restSexoMockMvc.perform(get("/api/sexos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sexo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getSexo() throws Exception {
        // Initialize the database
        sexoRepository.saveAndFlush(sexo);

        // Get the sexo
        restSexoMockMvc.perform(get("/api/sexos/{id}", sexo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sexo.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSexo() throws Exception {
        // Get the sexo
        restSexoMockMvc.perform(get("/api/sexos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSexo() throws Exception {
        // Initialize the database
        sexoService.save(sexo);

        int databaseSizeBeforeUpdate = sexoRepository.findAll().size();

        // Update the sexo
        Sexo updatedSexo = sexoRepository.findById(sexo.getId()).get();
        // Disconnect from session so that the updates on updatedSexo are not directly saved in db
        em.detach(updatedSexo);
        updatedSexo
            .nome(UPDATED_NOME);

        restSexoMockMvc.perform(put("/api/sexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSexo)))
            .andExpect(status().isOk());

        // Validate the Sexo in the database
        List<Sexo> sexoList = sexoRepository.findAll();
        assertThat(sexoList).hasSize(databaseSizeBeforeUpdate);
        Sexo testSexo = sexoList.get(sexoList.size() - 1);
        assertThat(testSexo.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingSexo() throws Exception {
        int databaseSizeBeforeUpdate = sexoRepository.findAll().size();

        // Create the Sexo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSexoMockMvc.perform(put("/api/sexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sexo)))
            .andExpect(status().isBadRequest());

        // Validate the Sexo in the database
        List<Sexo> sexoList = sexoRepository.findAll();
        assertThat(sexoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSexo() throws Exception {
        // Initialize the database
        sexoService.save(sexo);

        int databaseSizeBeforeDelete = sexoRepository.findAll().size();

        // Get the sexo
        restSexoMockMvc.perform(delete("/api/sexos/{id}", sexo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sexo> sexoList = sexoRepository.findAll();
        assertThat(sexoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sexo.class);
        Sexo sexo1 = new Sexo();
        sexo1.setId(1L);
        Sexo sexo2 = new Sexo();
        sexo2.setId(sexo1.getId());
        assertThat(sexo1).isEqualTo(sexo2);
        sexo2.setId(2L);
        assertThat(sexo1).isNotEqualTo(sexo2);
        sexo1.setId(null);
        assertThat(sexo1).isNotEqualTo(sexo2);
    }
}
