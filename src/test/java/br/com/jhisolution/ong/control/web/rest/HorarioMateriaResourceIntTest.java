package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.HorarioMateria;
import br.com.jhisolution.ong.control.repository.HorarioMateriaRepository;
import br.com.jhisolution.ong.control.service.HorarioMateriaService;
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
 * Test class for the HorarioMateriaResource REST controller.
 *
 * @see HorarioMateriaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class HorarioMateriaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_HORA_INICIO = "AAAAA";
    private static final String UPDATED_HORA_INICIO = "BBBBB";

    private static final String DEFAULT_HORA_FIM = "AAAAA";
    private static final String UPDATED_HORA_FIM = "BBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private HorarioMateriaRepository horarioMateriaRepository;

    @Autowired
    private HorarioMateriaService horarioMateriaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHorarioMateriaMockMvc;

    private HorarioMateria horarioMateria;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HorarioMateriaResource horarioMateriaResource = new HorarioMateriaResource(horarioMateriaService);
        this.restHorarioMateriaMockMvc = MockMvcBuilders.standaloneSetup(horarioMateriaResource)
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
    public static HorarioMateria createEntity(EntityManager em) {
        HorarioMateria horarioMateria = new HorarioMateria()
            .nome(DEFAULT_NOME)
            .horaInicio(DEFAULT_HORA_INICIO)
            .horaFim(DEFAULT_HORA_FIM)
            .obs(DEFAULT_OBS);
        return horarioMateria;
    }

    @Before
    public void initTest() {
        horarioMateria = createEntity(em);
    }

    @Test
    @Transactional
    public void createHorarioMateria() throws Exception {
        int databaseSizeBeforeCreate = horarioMateriaRepository.findAll().size();

        // Create the HorarioMateria
        restHorarioMateriaMockMvc.perform(post("/api/horario-materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horarioMateria)))
            .andExpect(status().isCreated());

        // Validate the HorarioMateria in the database
        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeCreate + 1);
        HorarioMateria testHorarioMateria = horarioMateriaList.get(horarioMateriaList.size() - 1);
        assertThat(testHorarioMateria.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testHorarioMateria.getHoraInicio()).isEqualTo(DEFAULT_HORA_INICIO);
        assertThat(testHorarioMateria.getHoraFim()).isEqualTo(DEFAULT_HORA_FIM);
        assertThat(testHorarioMateria.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createHorarioMateriaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = horarioMateriaRepository.findAll().size();

        // Create the HorarioMateria with an existing ID
        horarioMateria.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHorarioMateriaMockMvc.perform(post("/api/horario-materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horarioMateria)))
            .andExpect(status().isBadRequest());

        // Validate the HorarioMateria in the database
        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = horarioMateriaRepository.findAll().size();
        // set the field null
        horarioMateria.setNome(null);

        // Create the HorarioMateria, which fails.

        restHorarioMateriaMockMvc.perform(post("/api/horario-materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horarioMateria)))
            .andExpect(status().isBadRequest());

        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = horarioMateriaRepository.findAll().size();
        // set the field null
        horarioMateria.setHoraInicio(null);

        // Create the HorarioMateria, which fails.

        restHorarioMateriaMockMvc.perform(post("/api/horario-materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horarioMateria)))
            .andExpect(status().isBadRequest());

        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraFimIsRequired() throws Exception {
        int databaseSizeBeforeTest = horarioMateriaRepository.findAll().size();
        // set the field null
        horarioMateria.setHoraFim(null);

        // Create the HorarioMateria, which fails.

        restHorarioMateriaMockMvc.perform(post("/api/horario-materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horarioMateria)))
            .andExpect(status().isBadRequest());

        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHorarioMaterias() throws Exception {
        // Initialize the database
        horarioMateriaRepository.saveAndFlush(horarioMateria);

        // Get all the horarioMateriaList
        restHorarioMateriaMockMvc.perform(get("/api/horario-materias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(horarioMateria.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].horaInicio").value(hasItem(DEFAULT_HORA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].horaFim").value(hasItem(DEFAULT_HORA_FIM.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getHorarioMateria() throws Exception {
        // Initialize the database
        horarioMateriaRepository.saveAndFlush(horarioMateria);

        // Get the horarioMateria
        restHorarioMateriaMockMvc.perform(get("/api/horario-materias/{id}", horarioMateria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(horarioMateria.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.horaInicio").value(DEFAULT_HORA_INICIO.toString()))
            .andExpect(jsonPath("$.horaFim").value(DEFAULT_HORA_FIM.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHorarioMateria() throws Exception {
        // Get the horarioMateria
        restHorarioMateriaMockMvc.perform(get("/api/horario-materias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHorarioMateria() throws Exception {
        // Initialize the database
        horarioMateriaService.save(horarioMateria);

        int databaseSizeBeforeUpdate = horarioMateriaRepository.findAll().size();

        // Update the horarioMateria
        HorarioMateria updatedHorarioMateria = horarioMateriaRepository.findById(horarioMateria.getId()).get();
        // Disconnect from session so that the updates on updatedHorarioMateria are not directly saved in db
        em.detach(updatedHorarioMateria);
        updatedHorarioMateria
            .nome(UPDATED_NOME)
            .horaInicio(UPDATED_HORA_INICIO)
            .horaFim(UPDATED_HORA_FIM)
            .obs(UPDATED_OBS);

        restHorarioMateriaMockMvc.perform(put("/api/horario-materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHorarioMateria)))
            .andExpect(status().isOk());

        // Validate the HorarioMateria in the database
        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeUpdate);
        HorarioMateria testHorarioMateria = horarioMateriaList.get(horarioMateriaList.size() - 1);
        assertThat(testHorarioMateria.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testHorarioMateria.getHoraInicio()).isEqualTo(UPDATED_HORA_INICIO);
        assertThat(testHorarioMateria.getHoraFim()).isEqualTo(UPDATED_HORA_FIM);
        assertThat(testHorarioMateria.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingHorarioMateria() throws Exception {
        int databaseSizeBeforeUpdate = horarioMateriaRepository.findAll().size();

        // Create the HorarioMateria

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHorarioMateriaMockMvc.perform(put("/api/horario-materias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horarioMateria)))
            .andExpect(status().isBadRequest());

        // Validate the HorarioMateria in the database
        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHorarioMateria() throws Exception {
        // Initialize the database
        horarioMateriaService.save(horarioMateria);

        int databaseSizeBeforeDelete = horarioMateriaRepository.findAll().size();

        // Get the horarioMateria
        restHorarioMateriaMockMvc.perform(delete("/api/horario-materias/{id}", horarioMateria.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HorarioMateria> horarioMateriaList = horarioMateriaRepository.findAll();
        assertThat(horarioMateriaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HorarioMateria.class);
        HorarioMateria horarioMateria1 = new HorarioMateria();
        horarioMateria1.setId(1L);
        HorarioMateria horarioMateria2 = new HorarioMateria();
        horarioMateria2.setId(horarioMateria1.getId());
        assertThat(horarioMateria1).isEqualTo(horarioMateria2);
        horarioMateria2.setId(2L);
        assertThat(horarioMateria1).isNotEqualTo(horarioMateria2);
        horarioMateria1.setId(null);
        assertThat(horarioMateria1).isNotEqualTo(horarioMateria2);
    }
}
