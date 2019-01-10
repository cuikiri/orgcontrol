package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.Anotacao;
import br.com.jhisolution.ong.control.repository.AnotacaoRepository;
import br.com.jhisolution.ong.control.service.AnotacaoService;
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
 * Test class for the AnotacaoResource REST controller.
 *
 * @see AnotacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AnotacaoResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private AnotacaoService anotacaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAnotacaoMockMvc;

    private Anotacao anotacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnotacaoResource anotacaoResource = new AnotacaoResource(anotacaoService);
        this.restAnotacaoMockMvc = MockMvcBuilders.standaloneSetup(anotacaoResource)
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
    public static Anotacao createEntity(EntityManager em) {
        Anotacao anotacao = new Anotacao()
            .descricao(DEFAULT_DESCRICAO);
        return anotacao;
    }

    @Before
    public void initTest() {
        anotacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnotacao() throws Exception {
        int databaseSizeBeforeCreate = anotacaoRepository.findAll().size();

        // Create the Anotacao
        restAnotacaoMockMvc.perform(post("/api/anotacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anotacao)))
            .andExpect(status().isCreated());

        // Validate the Anotacao in the database
        List<Anotacao> anotacaoList = anotacaoRepository.findAll();
        assertThat(anotacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Anotacao testAnotacao = anotacaoList.get(anotacaoList.size() - 1);
        assertThat(testAnotacao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createAnotacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = anotacaoRepository.findAll().size();

        // Create the Anotacao with an existing ID
        anotacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnotacaoMockMvc.perform(post("/api/anotacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anotacao)))
            .andExpect(status().isBadRequest());

        // Validate the Anotacao in the database
        List<Anotacao> anotacaoList = anotacaoRepository.findAll();
        assertThat(anotacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAnotacaos() throws Exception {
        // Initialize the database
        anotacaoRepository.saveAndFlush(anotacao);

        // Get all the anotacaoList
        restAnotacaoMockMvc.perform(get("/api/anotacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anotacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }
    
    @Test
    @Transactional
    public void getAnotacao() throws Exception {
        // Initialize the database
        anotacaoRepository.saveAndFlush(anotacao);

        // Get the anotacao
        restAnotacaoMockMvc.perform(get("/api/anotacaos/{id}", anotacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(anotacao.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAnotacao() throws Exception {
        // Get the anotacao
        restAnotacaoMockMvc.perform(get("/api/anotacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnotacao() throws Exception {
        // Initialize the database
        anotacaoService.save(anotacao);

        int databaseSizeBeforeUpdate = anotacaoRepository.findAll().size();

        // Update the anotacao
        Anotacao updatedAnotacao = anotacaoRepository.findById(anotacao.getId()).get();
        // Disconnect from session so that the updates on updatedAnotacao are not directly saved in db
        em.detach(updatedAnotacao);
        updatedAnotacao
            .descricao(UPDATED_DESCRICAO);

        restAnotacaoMockMvc.perform(put("/api/anotacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAnotacao)))
            .andExpect(status().isOk());

        // Validate the Anotacao in the database
        List<Anotacao> anotacaoList = anotacaoRepository.findAll();
        assertThat(anotacaoList).hasSize(databaseSizeBeforeUpdate);
        Anotacao testAnotacao = anotacaoList.get(anotacaoList.size() - 1);
        assertThat(testAnotacao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingAnotacao() throws Exception {
        int databaseSizeBeforeUpdate = anotacaoRepository.findAll().size();

        // Create the Anotacao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnotacaoMockMvc.perform(put("/api/anotacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anotacao)))
            .andExpect(status().isBadRequest());

        // Validate the Anotacao in the database
        List<Anotacao> anotacaoList = anotacaoRepository.findAll();
        assertThat(anotacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnotacao() throws Exception {
        // Initialize the database
        anotacaoService.save(anotacao);

        int databaseSizeBeforeDelete = anotacaoRepository.findAll().size();

        // Get the anotacao
        restAnotacaoMockMvc.perform(delete("/api/anotacaos/{id}", anotacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Anotacao> anotacaoList = anotacaoRepository.findAll();
        assertThat(anotacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Anotacao.class);
        Anotacao anotacao1 = new Anotacao();
        anotacao1.setId(1L);
        Anotacao anotacao2 = new Anotacao();
        anotacao2.setId(anotacao1.getId());
        assertThat(anotacao1).isEqualTo(anotacao2);
        anotacao2.setId(2L);
        assertThat(anotacao1).isNotEqualTo(anotacao2);
        anotacao1.setId(null);
        assertThat(anotacao1).isNotEqualTo(anotacao2);
    }
}
