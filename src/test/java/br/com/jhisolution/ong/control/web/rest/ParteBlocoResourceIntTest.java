package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ParteBloco;
import br.com.jhisolution.ong.control.repository.ParteBlocoRepository;
import br.com.jhisolution.ong.control.service.ParteBlocoService;
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
 * Test class for the ParteBlocoResource REST controller.
 *
 * @see ParteBlocoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ParteBlocoResourceIntTest {

    private static final String DEFAULT_ABREVIATURA = "AAAAAAAAAA";
    private static final String UPDATED_ABREVIATURA = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANDAR = 1;
    private static final Integer UPDATED_ANDAR = 2;

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ParteBlocoRepository parteBlocoRepository;

    @Autowired
    private ParteBlocoService parteBlocoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restParteBlocoMockMvc;

    private ParteBloco parteBloco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParteBlocoResource parteBlocoResource = new ParteBlocoResource(parteBlocoService);
        this.restParteBlocoMockMvc = MockMvcBuilders.standaloneSetup(parteBlocoResource)
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
    public static ParteBloco createEntity(EntityManager em) {
        ParteBloco parteBloco = new ParteBloco()
            .abreviatura(DEFAULT_ABREVIATURA)
            .nome(DEFAULT_NOME)
            .andar(DEFAULT_ANDAR)
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS);
        return parteBloco;
    }

    @Before
    public void initTest() {
        parteBloco = createEntity(em);
    }

    @Test
    @Transactional
    public void createParteBloco() throws Exception {
        int databaseSizeBeforeCreate = parteBlocoRepository.findAll().size();

        // Create the ParteBloco
        restParteBlocoMockMvc.perform(post("/api/parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parteBloco)))
            .andExpect(status().isCreated());

        // Validate the ParteBloco in the database
        List<ParteBloco> parteBlocoList = parteBlocoRepository.findAll();
        assertThat(parteBlocoList).hasSize(databaseSizeBeforeCreate + 1);
        ParteBloco testParteBloco = parteBlocoList.get(parteBlocoList.size() - 1);
        assertThat(testParteBloco.getAbreviatura()).isEqualTo(DEFAULT_ABREVIATURA);
        assertThat(testParteBloco.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testParteBloco.getAndar()).isEqualTo(DEFAULT_ANDAR);
        assertThat(testParteBloco.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testParteBloco.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createParteBlocoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parteBlocoRepository.findAll().size();

        // Create the ParteBloco with an existing ID
        parteBloco.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParteBlocoMockMvc.perform(post("/api/parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parteBloco)))
            .andExpect(status().isBadRequest());

        // Validate the ParteBloco in the database
        List<ParteBloco> parteBlocoList = parteBlocoRepository.findAll();
        assertThat(parteBlocoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAbreviaturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = parteBlocoRepository.findAll().size();
        // set the field null
        parteBloco.setAbreviatura(null);

        // Create the ParteBloco, which fails.

        restParteBlocoMockMvc.perform(post("/api/parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parteBloco)))
            .andExpect(status().isBadRequest());

        List<ParteBloco> parteBlocoList = parteBlocoRepository.findAll();
        assertThat(parteBlocoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = parteBlocoRepository.findAll().size();
        // set the field null
        parteBloco.setNome(null);

        // Create the ParteBloco, which fails.

        restParteBlocoMockMvc.perform(post("/api/parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parteBloco)))
            .andExpect(status().isBadRequest());

        List<ParteBloco> parteBlocoList = parteBlocoRepository.findAll();
        assertThat(parteBlocoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParteBlocos() throws Exception {
        // Initialize the database
        parteBlocoRepository.saveAndFlush(parteBloco);

        // Get all the parteBlocoList
        restParteBlocoMockMvc.perform(get("/api/parte-blocos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parteBloco.getId().intValue())))
            .andExpect(jsonPath("$.[*].abreviatura").value(hasItem(DEFAULT_ABREVIATURA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].andar").value(hasItem(DEFAULT_ANDAR)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getParteBloco() throws Exception {
        // Initialize the database
        parteBlocoRepository.saveAndFlush(parteBloco);

        // Get the parteBloco
        restParteBlocoMockMvc.perform(get("/api/parte-blocos/{id}", parteBloco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(parteBloco.getId().intValue()))
            .andExpect(jsonPath("$.abreviatura").value(DEFAULT_ABREVIATURA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.andar").value(DEFAULT_ANDAR))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParteBloco() throws Exception {
        // Get the parteBloco
        restParteBlocoMockMvc.perform(get("/api/parte-blocos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParteBloco() throws Exception {
        // Initialize the database
        parteBlocoService.save(parteBloco);

        int databaseSizeBeforeUpdate = parteBlocoRepository.findAll().size();

        // Update the parteBloco
        ParteBloco updatedParteBloco = parteBlocoRepository.findById(parteBloco.getId()).get();
        // Disconnect from session so that the updates on updatedParteBloco are not directly saved in db
        em.detach(updatedParteBloco);
        updatedParteBloco
            .abreviatura(UPDATED_ABREVIATURA)
            .nome(UPDATED_NOME)
            .andar(UPDATED_ANDAR)
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS);

        restParteBlocoMockMvc.perform(put("/api/parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedParteBloco)))
            .andExpect(status().isOk());

        // Validate the ParteBloco in the database
        List<ParteBloco> parteBlocoList = parteBlocoRepository.findAll();
        assertThat(parteBlocoList).hasSize(databaseSizeBeforeUpdate);
        ParteBloco testParteBloco = parteBlocoList.get(parteBlocoList.size() - 1);
        assertThat(testParteBloco.getAbreviatura()).isEqualTo(UPDATED_ABREVIATURA);
        assertThat(testParteBloco.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testParteBloco.getAndar()).isEqualTo(UPDATED_ANDAR);
        assertThat(testParteBloco.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testParteBloco.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingParteBloco() throws Exception {
        int databaseSizeBeforeUpdate = parteBlocoRepository.findAll().size();

        // Create the ParteBloco

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParteBlocoMockMvc.perform(put("/api/parte-blocos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parteBloco)))
            .andExpect(status().isBadRequest());

        // Validate the ParteBloco in the database
        List<ParteBloco> parteBlocoList = parteBlocoRepository.findAll();
        assertThat(parteBlocoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParteBloco() throws Exception {
        // Initialize the database
        parteBlocoService.save(parteBloco);

        int databaseSizeBeforeDelete = parteBlocoRepository.findAll().size();

        // Get the parteBloco
        restParteBlocoMockMvc.perform(delete("/api/parte-blocos/{id}", parteBloco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ParteBloco> parteBlocoList = parteBlocoRepository.findAll();
        assertThat(parteBlocoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParteBloco.class);
        ParteBloco parteBloco1 = new ParteBloco();
        parteBloco1.setId(1L);
        ParteBloco parteBloco2 = new ParteBloco();
        parteBloco2.setId(parteBloco1.getId());
        assertThat(parteBloco1).isEqualTo(parteBloco2);
        parteBloco2.setId(2L);
        assertThat(parteBloco1).isNotEqualTo(parteBloco2);
        parteBloco1.setId(null);
        assertThat(parteBloco1).isNotEqualTo(parteBloco2);
    }
}
