package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Materia;
import br.com.jhisolution.ong.control.repository.MateriaRepository;
import br.com.jhisolution.ong.control.service.MateriaService;
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
 * Test class for the MateriaResource REST controller.
 *
 * @see MateriaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class MateriaResourceIntTest {

    private static final String DEFAULT_ABREVIATURA = "AAAAAAAAAA";
    private static final String UPDATED_ABREVIATURA = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMateriaMockMvc;

    private Materia materia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MateriaResource materiaResource = new MateriaResource(materiaService);
        this.restMateriaMockMvc = MockMvcBuilders.standaloneSetup(materiaResource)
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
    public static Materia createEntity(EntityManager em) {
        Materia materia = new Materia()
            .abreviatura(DEFAULT_ABREVIATURA)
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO)
            .obs(DEFAULT_OBS);
        return materia;
    }

    @Before
    public void initTest() {
        materia = createEntity(em);
    }

    @Test
    @Transactional
    public void createMateria() throws Exception {
        int databaseSizeBeforeCreate = materiaRepository.findAll().size();

        // Create the Materia
        restMateriaMockMvc.perform(post("/api/materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isCreated());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeCreate + 1);
        Materia testMateria = materiaList.get(materiaList.size() - 1);
        assertThat(testMateria.getAbreviatura()).isEqualTo(DEFAULT_ABREVIATURA);
        assertThat(testMateria.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testMateria.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testMateria.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createMateriaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = materiaRepository.findAll().size();

        // Create the Materia with an existing ID
        materia.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMateriaMockMvc.perform(post("/api/materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isBadRequest());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAbreviaturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = materiaRepository.findAll().size();
        // set the field null
        materia.setAbreviatura(null);

        // Create the Materia, which fails.

        restMateriaMockMvc.perform(post("/api/materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isBadRequest());

        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = materiaRepository.findAll().size();
        // set the field null
        materia.setNome(null);

        // Create the Materia, which fails.

        restMateriaMockMvc.perform(post("/api/materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isBadRequest());

        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMaterias() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        // Get all the materiaList
        restMateriaMockMvc.perform(get("/api/materias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(materia.getId().intValue())))
            .andExpect(jsonPath("$.[*].abreviatura").value(hasItem(DEFAULT_ABREVIATURA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getMateria() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        // Get the materia
        restMateriaMockMvc.perform(get("/api/materias/{id}", materia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(materia.getId().intValue()))
            .andExpect(jsonPath("$.abreviatura").value(DEFAULT_ABREVIATURA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMateria() throws Exception {
        // Get the materia
        restMateriaMockMvc.perform(get("/api/materias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMateria() throws Exception {
        // Initialize the database
        materiaService.save(materia);

        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();

        // Update the materia
        Materia updatedMateria = materiaRepository.findById(materia.getId()).get();
        // Disconnect from session so that the updates on updatedMateria are not directly saved in db
        em.detach(updatedMateria);
        updatedMateria
            .abreviatura(UPDATED_ABREVIATURA)
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO)
            .obs(UPDATED_OBS);

        restMateriaMockMvc.perform(put("/api/materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMateria)))
            .andExpect(status().isOk());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
        Materia testMateria = materiaList.get(materiaList.size() - 1);
        assertThat(testMateria.getAbreviatura()).isEqualTo(UPDATED_ABREVIATURA);
        assertThat(testMateria.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testMateria.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testMateria.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingMateria() throws Exception {
        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();

        // Create the Materia

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMateriaMockMvc.perform(put("/api/materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isBadRequest());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMateria() throws Exception {
        // Initialize the database
        materiaService.save(materia);

        int databaseSizeBeforeDelete = materiaRepository.findAll().size();

        // Get the materia
        restMateriaMockMvc.perform(delete("/api/materias/{id}", materia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Materia.class);
        Materia materia1 = new Materia();
        materia1.setId(1L);
        Materia materia2 = new Materia();
        materia2.setId(materia1.getId());
        assertThat(materia1).isEqualTo(materia2);
        materia2.setId(2L);
        assertThat(materia1).isNotEqualTo(materia2);
        materia1.setId(null);
        assertThat(materia1).isNotEqualTo(materia2);
    }
}
