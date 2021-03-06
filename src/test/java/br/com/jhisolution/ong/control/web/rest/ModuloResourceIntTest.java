package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Modulo;
import br.com.jhisolution.ong.control.repository.ModuloRepository;
import br.com.jhisolution.ong.control.service.ModuloService;
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
 * Test class for the ModuloResource REST controller.
 *
 * @see ModuloResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ModuloResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restModuloMockMvc;

    private Modulo modulo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModuloResource moduloResource = new ModuloResource(moduloService);
        this.restModuloMockMvc = MockMvcBuilders.standaloneSetup(moduloResource)
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
    public static Modulo createEntity(EntityManager em) {
        Modulo modulo = new Modulo()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return modulo;
    }

    @Before
    public void initTest() {
        modulo = createEntity(em);
    }

    @Test
    @Transactional
    public void createModulo() throws Exception {
        int databaseSizeBeforeCreate = moduloRepository.findAll().size();

        // Create the Modulo
        restModuloMockMvc.perform(post("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isCreated());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeCreate + 1);
        Modulo testModulo = moduloList.get(moduloList.size() - 1);
        assertThat(testModulo.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testModulo.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createModuloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = moduloRepository.findAll().size();

        // Create the Modulo with an existing ID
        modulo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModuloMockMvc.perform(post("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isBadRequest());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = moduloRepository.findAll().size();
        // set the field null
        modulo.setNome(null);

        // Create the Modulo, which fails.

        restModuloMockMvc.perform(post("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isBadRequest());

        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModulos() throws Exception {
        // Initialize the database
        moduloRepository.saveAndFlush(modulo);

        // Get all the moduloList
        restModuloMockMvc.perform(get("/api/modulos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modulo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getModulo() throws Exception {
        // Initialize the database
        moduloRepository.saveAndFlush(modulo);

        // Get the modulo
        restModuloMockMvc.perform(get("/api/modulos/{id}", modulo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(modulo.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingModulo() throws Exception {
        // Get the modulo
        restModuloMockMvc.perform(get("/api/modulos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModulo() throws Exception {
        // Initialize the database
        moduloService.save(modulo);

        int databaseSizeBeforeUpdate = moduloRepository.findAll().size();

        // Update the modulo
        Modulo updatedModulo = moduloRepository.findById(modulo.getId()).get();
        // Disconnect from session so that the updates on updatedModulo are not directly saved in db
        em.detach(updatedModulo);
        updatedModulo
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restModuloMockMvc.perform(put("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedModulo)))
            .andExpect(status().isOk());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeUpdate);
        Modulo testModulo = moduloList.get(moduloList.size() - 1);
        assertThat(testModulo.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testModulo.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingModulo() throws Exception {
        int databaseSizeBeforeUpdate = moduloRepository.findAll().size();

        // Create the Modulo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModuloMockMvc.perform(put("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isBadRequest());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModulo() throws Exception {
        // Initialize the database
        moduloService.save(modulo);

        int databaseSizeBeforeDelete = moduloRepository.findAll().size();

        // Get the modulo
        restModuloMockMvc.perform(delete("/api/modulos/{id}", modulo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modulo.class);
        Modulo modulo1 = new Modulo();
        modulo1.setId(1L);
        Modulo modulo2 = new Modulo();
        modulo2.setId(modulo1.getId());
        assertThat(modulo1).isEqualTo(modulo2);
        modulo2.setId(2L);
        assertThat(modulo1).isNotEqualTo(modulo2);
        modulo1.setId(null);
        assertThat(modulo1).isNotEqualTo(modulo2);
    }
}
