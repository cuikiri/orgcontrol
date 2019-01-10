package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.CaracteristicasPsiquicas;
import br.com.jhisolution.ong.control.repository.CaracteristicasPsiquicasRepository;
import br.com.jhisolution.ong.control.service.CaracteristicasPsiquicasService;
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
 * Test class for the CaracteristicasPsiquicasResource REST controller.
 *
 * @see CaracteristicasPsiquicasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class CaracteristicasPsiquicasResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private CaracteristicasPsiquicasRepository caracteristicasPsiquicasRepository;

    @Autowired
    private CaracteristicasPsiquicasService caracteristicasPsiquicasService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaracteristicasPsiquicasMockMvc;

    private CaracteristicasPsiquicas caracteristicasPsiquicas;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaracteristicasPsiquicasResource caracteristicasPsiquicasResource = new CaracteristicasPsiquicasResource(caracteristicasPsiquicasService);
        this.restCaracteristicasPsiquicasMockMvc = MockMvcBuilders.standaloneSetup(caracteristicasPsiquicasResource)
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
    public static CaracteristicasPsiquicas createEntity(EntityManager em) {
        CaracteristicasPsiquicas caracteristicasPsiquicas = new CaracteristicasPsiquicas()
            .nome(DEFAULT_NOME);
        return caracteristicasPsiquicas;
    }

    @Before
    public void initTest() {
        caracteristicasPsiquicas = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaracteristicasPsiquicas() throws Exception {
        int databaseSizeBeforeCreate = caracteristicasPsiquicasRepository.findAll().size();

        // Create the CaracteristicasPsiquicas
        restCaracteristicasPsiquicasMockMvc.perform(post("/api/caracteristicas-psiquicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caracteristicasPsiquicas)))
            .andExpect(status().isCreated());

        // Validate the CaracteristicasPsiquicas in the database
        List<CaracteristicasPsiquicas> caracteristicasPsiquicasList = caracteristicasPsiquicasRepository.findAll();
        assertThat(caracteristicasPsiquicasList).hasSize(databaseSizeBeforeCreate + 1);
        CaracteristicasPsiquicas testCaracteristicasPsiquicas = caracteristicasPsiquicasList.get(caracteristicasPsiquicasList.size() - 1);
        assertThat(testCaracteristicasPsiquicas.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createCaracteristicasPsiquicasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caracteristicasPsiquicasRepository.findAll().size();

        // Create the CaracteristicasPsiquicas with an existing ID
        caracteristicasPsiquicas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaracteristicasPsiquicasMockMvc.perform(post("/api/caracteristicas-psiquicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caracteristicasPsiquicas)))
            .andExpect(status().isBadRequest());

        // Validate the CaracteristicasPsiquicas in the database
        List<CaracteristicasPsiquicas> caracteristicasPsiquicasList = caracteristicasPsiquicasRepository.findAll();
        assertThat(caracteristicasPsiquicasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = caracteristicasPsiquicasRepository.findAll().size();
        // set the field null
        caracteristicasPsiquicas.setNome(null);

        // Create the CaracteristicasPsiquicas, which fails.

        restCaracteristicasPsiquicasMockMvc.perform(post("/api/caracteristicas-psiquicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caracteristicasPsiquicas)))
            .andExpect(status().isBadRequest());

        List<CaracteristicasPsiquicas> caracteristicasPsiquicasList = caracteristicasPsiquicasRepository.findAll();
        assertThat(caracteristicasPsiquicasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCaracteristicasPsiquicas() throws Exception {
        // Initialize the database
        caracteristicasPsiquicasRepository.saveAndFlush(caracteristicasPsiquicas);

        // Get all the caracteristicasPsiquicasList
        restCaracteristicasPsiquicasMockMvc.perform(get("/api/caracteristicas-psiquicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caracteristicasPsiquicas.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getCaracteristicasPsiquicas() throws Exception {
        // Initialize the database
        caracteristicasPsiquicasRepository.saveAndFlush(caracteristicasPsiquicas);

        // Get the caracteristicasPsiquicas
        restCaracteristicasPsiquicasMockMvc.perform(get("/api/caracteristicas-psiquicas/{id}", caracteristicasPsiquicas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caracteristicasPsiquicas.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCaracteristicasPsiquicas() throws Exception {
        // Get the caracteristicasPsiquicas
        restCaracteristicasPsiquicasMockMvc.perform(get("/api/caracteristicas-psiquicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaracteristicasPsiquicas() throws Exception {
        // Initialize the database
        caracteristicasPsiquicasService.save(caracteristicasPsiquicas);

        int databaseSizeBeforeUpdate = caracteristicasPsiquicasRepository.findAll().size();

        // Update the caracteristicasPsiquicas
        CaracteristicasPsiquicas updatedCaracteristicasPsiquicas = caracteristicasPsiquicasRepository.findById(caracteristicasPsiquicas.getId()).get();
        // Disconnect from session so that the updates on updatedCaracteristicasPsiquicas are not directly saved in db
        em.detach(updatedCaracteristicasPsiquicas);
        updatedCaracteristicasPsiquicas
            .nome(UPDATED_NOME);

        restCaracteristicasPsiquicasMockMvc.perform(put("/api/caracteristicas-psiquicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaracteristicasPsiquicas)))
            .andExpect(status().isOk());

        // Validate the CaracteristicasPsiquicas in the database
        List<CaracteristicasPsiquicas> caracteristicasPsiquicasList = caracteristicasPsiquicasRepository.findAll();
        assertThat(caracteristicasPsiquicasList).hasSize(databaseSizeBeforeUpdate);
        CaracteristicasPsiquicas testCaracteristicasPsiquicas = caracteristicasPsiquicasList.get(caracteristicasPsiquicasList.size() - 1);
        assertThat(testCaracteristicasPsiquicas.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingCaracteristicasPsiquicas() throws Exception {
        int databaseSizeBeforeUpdate = caracteristicasPsiquicasRepository.findAll().size();

        // Create the CaracteristicasPsiquicas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaracteristicasPsiquicasMockMvc.perform(put("/api/caracteristicas-psiquicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caracteristicasPsiquicas)))
            .andExpect(status().isBadRequest());

        // Validate the CaracteristicasPsiquicas in the database
        List<CaracteristicasPsiquicas> caracteristicasPsiquicasList = caracteristicasPsiquicasRepository.findAll();
        assertThat(caracteristicasPsiquicasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaracteristicasPsiquicas() throws Exception {
        // Initialize the database
        caracteristicasPsiquicasService.save(caracteristicasPsiquicas);

        int databaseSizeBeforeDelete = caracteristicasPsiquicasRepository.findAll().size();

        // Get the caracteristicasPsiquicas
        restCaracteristicasPsiquicasMockMvc.perform(delete("/api/caracteristicas-psiquicas/{id}", caracteristicasPsiquicas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaracteristicasPsiquicas> caracteristicasPsiquicasList = caracteristicasPsiquicasRepository.findAll();
        assertThat(caracteristicasPsiquicasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaracteristicasPsiquicas.class);
        CaracteristicasPsiquicas caracteristicasPsiquicas1 = new CaracteristicasPsiquicas();
        caracteristicasPsiquicas1.setId(1L);
        CaracteristicasPsiquicas caracteristicasPsiquicas2 = new CaracteristicasPsiquicas();
        caracteristicasPsiquicas2.setId(caracteristicasPsiquicas1.getId());
        assertThat(caracteristicasPsiquicas1).isEqualTo(caracteristicasPsiquicas2);
        caracteristicasPsiquicas2.setId(2L);
        assertThat(caracteristicasPsiquicas1).isNotEqualTo(caracteristicasPsiquicas2);
        caracteristicasPsiquicas1.setId(null);
        assertThat(caracteristicasPsiquicas1).isNotEqualTo(caracteristicasPsiquicas2);
    }
}
