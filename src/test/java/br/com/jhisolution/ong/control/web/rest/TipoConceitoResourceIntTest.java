package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.TipoConceito;
import br.com.jhisolution.ong.control.repository.TipoConceitoRepository;
import br.com.jhisolution.ong.control.service.TipoConceitoService;
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
 * Test class for the TipoConceitoResource REST controller.
 *
 * @see TipoConceitoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class TipoConceitoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private TipoConceitoRepository tipoConceitoRepository;

    @Autowired
    private TipoConceitoService tipoConceitoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoConceitoMockMvc;

    private TipoConceito tipoConceito;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoConceitoResource tipoConceitoResource = new TipoConceitoResource(tipoConceitoService);
        this.restTipoConceitoMockMvc = MockMvcBuilders.standaloneSetup(tipoConceitoResource)
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
    public static TipoConceito createEntity(EntityManager em) {
        TipoConceito tipoConceito = new TipoConceito()
            .nome(DEFAULT_NOME)
            .obs(DEFAULT_OBS);
        return tipoConceito;
    }

    @Before
    public void initTest() {
        tipoConceito = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoConceito() throws Exception {
        int databaseSizeBeforeCreate = tipoConceitoRepository.findAll().size();

        // Create the TipoConceito
        restTipoConceitoMockMvc.perform(post("/api/tipo-conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoConceito)))
            .andExpect(status().isCreated());

        // Validate the TipoConceito in the database
        List<TipoConceito> tipoConceitoList = tipoConceitoRepository.findAll();
        assertThat(tipoConceitoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoConceito testTipoConceito = tipoConceitoList.get(tipoConceitoList.size() - 1);
        assertThat(testTipoConceito.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoConceito.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createTipoConceitoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoConceitoRepository.findAll().size();

        // Create the TipoConceito with an existing ID
        tipoConceito.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoConceitoMockMvc.perform(post("/api/tipo-conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoConceito)))
            .andExpect(status().isBadRequest());

        // Validate the TipoConceito in the database
        List<TipoConceito> tipoConceitoList = tipoConceitoRepository.findAll();
        assertThat(tipoConceitoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoConceitoRepository.findAll().size();
        // set the field null
        tipoConceito.setNome(null);

        // Create the TipoConceito, which fails.

        restTipoConceitoMockMvc.perform(post("/api/tipo-conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoConceito)))
            .andExpect(status().isBadRequest());

        List<TipoConceito> tipoConceitoList = tipoConceitoRepository.findAll();
        assertThat(tipoConceitoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoConceitos() throws Exception {
        // Initialize the database
        tipoConceitoRepository.saveAndFlush(tipoConceito);

        // Get all the tipoConceitoList
        restTipoConceitoMockMvc.perform(get("/api/tipo-conceitos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoConceito.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoConceito() throws Exception {
        // Initialize the database
        tipoConceitoRepository.saveAndFlush(tipoConceito);

        // Get the tipoConceito
        restTipoConceitoMockMvc.perform(get("/api/tipo-conceitos/{id}", tipoConceito.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoConceito.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoConceito() throws Exception {
        // Get the tipoConceito
        restTipoConceitoMockMvc.perform(get("/api/tipo-conceitos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoConceito() throws Exception {
        // Initialize the database
        tipoConceitoService.save(tipoConceito);

        int databaseSizeBeforeUpdate = tipoConceitoRepository.findAll().size();

        // Update the tipoConceito
        TipoConceito updatedTipoConceito = tipoConceitoRepository.findById(tipoConceito.getId()).get();
        // Disconnect from session so that the updates on updatedTipoConceito are not directly saved in db
        em.detach(updatedTipoConceito);
        updatedTipoConceito
            .nome(UPDATED_NOME)
            .obs(UPDATED_OBS);

        restTipoConceitoMockMvc.perform(put("/api/tipo-conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoConceito)))
            .andExpect(status().isOk());

        // Validate the TipoConceito in the database
        List<TipoConceito> tipoConceitoList = tipoConceitoRepository.findAll();
        assertThat(tipoConceitoList).hasSize(databaseSizeBeforeUpdate);
        TipoConceito testTipoConceito = tipoConceitoList.get(tipoConceitoList.size() - 1);
        assertThat(testTipoConceito.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoConceito.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoConceito() throws Exception {
        int databaseSizeBeforeUpdate = tipoConceitoRepository.findAll().size();

        // Create the TipoConceito

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoConceitoMockMvc.perform(put("/api/tipo-conceitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoConceito)))
            .andExpect(status().isBadRequest());

        // Validate the TipoConceito in the database
        List<TipoConceito> tipoConceitoList = tipoConceitoRepository.findAll();
        assertThat(tipoConceitoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoConceito() throws Exception {
        // Initialize the database
        tipoConceitoService.save(tipoConceito);

        int databaseSizeBeforeDelete = tipoConceitoRepository.findAll().size();

        // Get the tipoConceito
        restTipoConceitoMockMvc.perform(delete("/api/tipo-conceitos/{id}", tipoConceito.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoConceito> tipoConceitoList = tipoConceitoRepository.findAll();
        assertThat(tipoConceitoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoConceito.class);
        TipoConceito tipoConceito1 = new TipoConceito();
        tipoConceito1.setId(1L);
        TipoConceito tipoConceito2 = new TipoConceito();
        tipoConceito2.setId(tipoConceito1.getId());
        assertThat(tipoConceito1).isEqualTo(tipoConceito2);
        tipoConceito2.setId(2L);
        assertThat(tipoConceito1).isNotEqualTo(tipoConceito2);
        tipoConceito1.setId(null);
        assertThat(tipoConceito1).isNotEqualTo(tipoConceito2);
    }
}
