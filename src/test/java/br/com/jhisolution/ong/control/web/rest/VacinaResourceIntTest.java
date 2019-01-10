package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Vacina;
import br.com.jhisolution.ong.control.repository.VacinaRepository;
import br.com.jhisolution.ong.control.service.VacinaService;
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
 * Test class for the VacinaResource REST controller.
 *
 * @see VacinaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class VacinaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_IDADE = "AAAAA";
    private static final String UPDATED_IDADE = "BBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private VacinaService vacinaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVacinaMockMvc;

    private Vacina vacina;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VacinaResource vacinaResource = new VacinaResource(vacinaService);
        this.restVacinaMockMvc = MockMvcBuilders.standaloneSetup(vacinaResource)
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
    public static Vacina createEntity(EntityManager em) {
        Vacina vacina = new Vacina()
            .nome(DEFAULT_NOME)
            .idade(DEFAULT_IDADE)
            .obs(DEFAULT_OBS);
        return vacina;
    }

    @Before
    public void initTest() {
        vacina = createEntity(em);
    }

    @Test
    @Transactional
    public void createVacina() throws Exception {
        int databaseSizeBeforeCreate = vacinaRepository.findAll().size();

        // Create the Vacina
        restVacinaMockMvc.perform(post("/api/vacinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vacina)))
            .andExpect(status().isCreated());

        // Validate the Vacina in the database
        List<Vacina> vacinaList = vacinaRepository.findAll();
        assertThat(vacinaList).hasSize(databaseSizeBeforeCreate + 1);
        Vacina testVacina = vacinaList.get(vacinaList.size() - 1);
        assertThat(testVacina.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testVacina.getIdade()).isEqualTo(DEFAULT_IDADE);
        assertThat(testVacina.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createVacinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vacinaRepository.findAll().size();

        // Create the Vacina with an existing ID
        vacina.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVacinaMockMvc.perform(post("/api/vacinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vacina)))
            .andExpect(status().isBadRequest());

        // Validate the Vacina in the database
        List<Vacina> vacinaList = vacinaRepository.findAll();
        assertThat(vacinaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = vacinaRepository.findAll().size();
        // set the field null
        vacina.setNome(null);

        // Create the Vacina, which fails.

        restVacinaMockMvc.perform(post("/api/vacinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vacina)))
            .andExpect(status().isBadRequest());

        List<Vacina> vacinaList = vacinaRepository.findAll();
        assertThat(vacinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVacinas() throws Exception {
        // Initialize the database
        vacinaRepository.saveAndFlush(vacina);

        // Get all the vacinaList
        restVacinaMockMvc.perform(get("/api/vacinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vacina.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].idade").value(hasItem(DEFAULT_IDADE.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getVacina() throws Exception {
        // Initialize the database
        vacinaRepository.saveAndFlush(vacina);

        // Get the vacina
        restVacinaMockMvc.perform(get("/api/vacinas/{id}", vacina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vacina.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.idade").value(DEFAULT_IDADE.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVacina() throws Exception {
        // Get the vacina
        restVacinaMockMvc.perform(get("/api/vacinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVacina() throws Exception {
        // Initialize the database
        vacinaService.save(vacina);

        int databaseSizeBeforeUpdate = vacinaRepository.findAll().size();

        // Update the vacina
        Vacina updatedVacina = vacinaRepository.findById(vacina.getId()).get();
        // Disconnect from session so that the updates on updatedVacina are not directly saved in db
        em.detach(updatedVacina);
        updatedVacina
            .nome(UPDATED_NOME)
            .idade(UPDATED_IDADE)
            .obs(UPDATED_OBS);

        restVacinaMockMvc.perform(put("/api/vacinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVacina)))
            .andExpect(status().isOk());

        // Validate the Vacina in the database
        List<Vacina> vacinaList = vacinaRepository.findAll();
        assertThat(vacinaList).hasSize(databaseSizeBeforeUpdate);
        Vacina testVacina = vacinaList.get(vacinaList.size() - 1);
        assertThat(testVacina.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testVacina.getIdade()).isEqualTo(UPDATED_IDADE);
        assertThat(testVacina.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingVacina() throws Exception {
        int databaseSizeBeforeUpdate = vacinaRepository.findAll().size();

        // Create the Vacina

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVacinaMockMvc.perform(put("/api/vacinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vacina)))
            .andExpect(status().isBadRequest());

        // Validate the Vacina in the database
        List<Vacina> vacinaList = vacinaRepository.findAll();
        assertThat(vacinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVacina() throws Exception {
        // Initialize the database
        vacinaService.save(vacina);

        int databaseSizeBeforeDelete = vacinaRepository.findAll().size();

        // Get the vacina
        restVacinaMockMvc.perform(delete("/api/vacinas/{id}", vacina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vacina> vacinaList = vacinaRepository.findAll();
        assertThat(vacinaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vacina.class);
        Vacina vacina1 = new Vacina();
        vacina1.setId(1L);
        Vacina vacina2 = new Vacina();
        vacina2.setId(vacina1.getId());
        assertThat(vacina1).isEqualTo(vacina2);
        vacina2.setId(2L);
        assertThat(vacina1).isNotEqualTo(vacina2);
        vacina1.setId(null);
        assertThat(vacina1).isNotEqualTo(vacina2);
    }
}
