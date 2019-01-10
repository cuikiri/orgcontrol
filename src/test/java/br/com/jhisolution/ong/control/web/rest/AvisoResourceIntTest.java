package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Aviso;
import br.com.jhisolution.ong.control.repository.AvisoRepository;
import br.com.jhisolution.ong.control.service.AvisoService;
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

import br.com.jhisolution.ong.control.domain.enumeration.TipoAviso;
/**
 * Test class for the AvisoResource REST controller.
 *
 * @see AvisoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AvisoResourceIntTest {

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AVISO = "AAAAAAAAAA";
    private static final String UPDATED_AVISO = "BBBBBBBBBB";

    private static final TipoAviso DEFAULT_TIPO = TipoAviso.IMPORTANTE;
    private static final TipoAviso UPDATED_TIPO = TipoAviso.URGENTE;

    @Autowired
    private AvisoRepository avisoRepository;

    @Autowired
    private AvisoService avisoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAvisoMockMvc;

    private Aviso aviso;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AvisoResource avisoResource = new AvisoResource(avisoService);
        this.restAvisoMockMvc = MockMvcBuilders.standaloneSetup(avisoResource)
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
    public static Aviso createEntity(EntityManager em) {
        Aviso aviso = new Aviso()
            .data(DEFAULT_DATA)
            .aviso(DEFAULT_AVISO)
            .tipo(DEFAULT_TIPO);
        return aviso;
    }

    @Before
    public void initTest() {
        aviso = createEntity(em);
    }

    @Test
    @Transactional
    public void createAviso() throws Exception {
        int databaseSizeBeforeCreate = avisoRepository.findAll().size();

        // Create the Aviso
        restAvisoMockMvc.perform(post("/api/avisos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aviso)))
            .andExpect(status().isCreated());

        // Validate the Aviso in the database
        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeCreate + 1);
        Aviso testAviso = avisoList.get(avisoList.size() - 1);
        assertThat(testAviso.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAviso.getAviso()).isEqualTo(DEFAULT_AVISO);
        assertThat(testAviso.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createAvisoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avisoRepository.findAll().size();

        // Create the Aviso with an existing ID
        aviso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvisoMockMvc.perform(post("/api/avisos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aviso)))
            .andExpect(status().isBadRequest());

        // Validate the Aviso in the database
        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = avisoRepository.findAll().size();
        // set the field null
        aviso.setData(null);

        // Create the Aviso, which fails.

        restAvisoMockMvc.perform(post("/api/avisos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aviso)))
            .andExpect(status().isBadRequest());

        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvisoIsRequired() throws Exception {
        int databaseSizeBeforeTest = avisoRepository.findAll().size();
        // set the field null
        aviso.setAviso(null);

        // Create the Aviso, which fails.

        restAvisoMockMvc.perform(post("/api/avisos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aviso)))
            .andExpect(status().isBadRequest());

        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = avisoRepository.findAll().size();
        // set the field null
        aviso.setTipo(null);

        // Create the Aviso, which fails.

        restAvisoMockMvc.perform(post("/api/avisos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aviso)))
            .andExpect(status().isBadRequest());

        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvisos() throws Exception {
        // Initialize the database
        avisoRepository.saveAndFlush(aviso);

        // Get all the avisoList
        restAvisoMockMvc.perform(get("/api/avisos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aviso.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].aviso").value(hasItem(DEFAULT_AVISO.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())));
    }
    
    @Test
    @Transactional
    public void getAviso() throws Exception {
        // Initialize the database
        avisoRepository.saveAndFlush(aviso);

        // Get the aviso
        restAvisoMockMvc.perform(get("/api/avisos/{id}", aviso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aviso.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.aviso").value(DEFAULT_AVISO.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAviso() throws Exception {
        // Get the aviso
        restAvisoMockMvc.perform(get("/api/avisos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAviso() throws Exception {
        // Initialize the database
        avisoService.save(aviso);

        int databaseSizeBeforeUpdate = avisoRepository.findAll().size();

        // Update the aviso
        Aviso updatedAviso = avisoRepository.findById(aviso.getId()).get();
        // Disconnect from session so that the updates on updatedAviso are not directly saved in db
        em.detach(updatedAviso);
        updatedAviso
            .data(UPDATED_DATA)
            .aviso(UPDATED_AVISO)
            .tipo(UPDATED_TIPO);

        restAvisoMockMvc.perform(put("/api/avisos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAviso)))
            .andExpect(status().isOk());

        // Validate the Aviso in the database
        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeUpdate);
        Aviso testAviso = avisoList.get(avisoList.size() - 1);
        assertThat(testAviso.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAviso.getAviso()).isEqualTo(UPDATED_AVISO);
        assertThat(testAviso.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingAviso() throws Exception {
        int databaseSizeBeforeUpdate = avisoRepository.findAll().size();

        // Create the Aviso

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvisoMockMvc.perform(put("/api/avisos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aviso)))
            .andExpect(status().isBadRequest());

        // Validate the Aviso in the database
        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAviso() throws Exception {
        // Initialize the database
        avisoService.save(aviso);

        int databaseSizeBeforeDelete = avisoRepository.findAll().size();

        // Get the aviso
        restAvisoMockMvc.perform(delete("/api/avisos/{id}", aviso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Aviso> avisoList = avisoRepository.findAll();
        assertThat(avisoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aviso.class);
        Aviso aviso1 = new Aviso();
        aviso1.setId(1L);
        Aviso aviso2 = new Aviso();
        aviso2.setId(aviso1.getId());
        assertThat(aviso1).isEqualTo(aviso2);
        aviso2.setId(2L);
        assertThat(aviso1).isNotEqualTo(aviso2);
        aviso1.setId(null);
        assertThat(aviso1).isNotEqualTo(aviso2);
    }
}
