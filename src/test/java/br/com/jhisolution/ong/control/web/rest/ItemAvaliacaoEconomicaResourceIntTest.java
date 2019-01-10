package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ItemAvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.ItemAvaliacaoEconomicaRepository;
import br.com.jhisolution.ong.control.service.ItemAvaliacaoEconomicaService;
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

import br.com.jhisolution.ong.control.domain.enumeration.TipoResposta;
/**
 * Test class for the ItemAvaliacaoEconomicaResource REST controller.
 *
 * @see ItemAvaliacaoEconomicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ItemAvaliacaoEconomicaResourceIntTest {

    private static final TipoResposta DEFAULT_TIPO_RESPOSTA = TipoResposta.DESCRITIVA;
    private static final TipoResposta UPDATED_TIPO_RESPOSTA = TipoResposta.OPTATIVA;

    private static final String DEFAULT_QUESTAO = "AAAAAAAAAA";
    private static final String UPDATED_QUESTAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ItemAvaliacaoEconomicaRepository itemAvaliacaoEconomicaRepository;

    @Autowired
    private ItemAvaliacaoEconomicaService itemAvaliacaoEconomicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAvaliacaoEconomicaMockMvc;

    private ItemAvaliacaoEconomica itemAvaliacaoEconomica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAvaliacaoEconomicaResource itemAvaliacaoEconomicaResource = new ItemAvaliacaoEconomicaResource(itemAvaliacaoEconomicaService);
        this.restItemAvaliacaoEconomicaMockMvc = MockMvcBuilders.standaloneSetup(itemAvaliacaoEconomicaResource)
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
    public static ItemAvaliacaoEconomica createEntity(EntityManager em) {
        ItemAvaliacaoEconomica itemAvaliacaoEconomica = new ItemAvaliacaoEconomica()
            .tipoResposta(DEFAULT_TIPO_RESPOSTA)
            .questao(DEFAULT_QUESTAO)
            .obs(DEFAULT_OBS);
        return itemAvaliacaoEconomica;
    }

    @Before
    public void initTest() {
        itemAvaliacaoEconomica = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliacaoEconomicaRepository.findAll().size();

        // Create the ItemAvaliacaoEconomica
        restItemAvaliacaoEconomicaMockMvc.perform(post("/api/item-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoEconomica)))
            .andExpect(status().isCreated());

        // Validate the ItemAvaliacaoEconomica in the database
        List<ItemAvaliacaoEconomica> itemAvaliacaoEconomicaList = itemAvaliacaoEconomicaRepository.findAll();
        assertThat(itemAvaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAvaliacaoEconomica testItemAvaliacaoEconomica = itemAvaliacaoEconomicaList.get(itemAvaliacaoEconomicaList.size() - 1);
        assertThat(testItemAvaliacaoEconomica.getTipoResposta()).isEqualTo(DEFAULT_TIPO_RESPOSTA);
        assertThat(testItemAvaliacaoEconomica.getQuestao()).isEqualTo(DEFAULT_QUESTAO);
        assertThat(testItemAvaliacaoEconomica.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createItemAvaliacaoEconomicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAvaliacaoEconomicaRepository.findAll().size();

        // Create the ItemAvaliacaoEconomica with an existing ID
        itemAvaliacaoEconomica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAvaliacaoEconomicaMockMvc.perform(post("/api/item-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliacaoEconomica in the database
        List<ItemAvaliacaoEconomica> itemAvaliacaoEconomicaList = itemAvaliacaoEconomicaRepository.findAll();
        assertThat(itemAvaliacaoEconomicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoEconomicaRepository.findAll().size();
        // set the field null
        itemAvaliacaoEconomica.setTipoResposta(null);

        // Create the ItemAvaliacaoEconomica, which fails.

        restItemAvaliacaoEconomicaMockMvc.perform(post("/api/item-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacaoEconomica> itemAvaliacaoEconomicaList = itemAvaliacaoEconomicaRepository.findAll();
        assertThat(itemAvaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuestaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAvaliacaoEconomicaRepository.findAll().size();
        // set the field null
        itemAvaliacaoEconomica.setQuestao(null);

        // Create the ItemAvaliacaoEconomica, which fails.

        restItemAvaliacaoEconomicaMockMvc.perform(post("/api/item-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        List<ItemAvaliacaoEconomica> itemAvaliacaoEconomicaList = itemAvaliacaoEconomicaRepository.findAll();
        assertThat(itemAvaliacaoEconomicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAvaliacaoEconomicas() throws Exception {
        // Initialize the database
        itemAvaliacaoEconomicaRepository.saveAndFlush(itemAvaliacaoEconomica);

        // Get all the itemAvaliacaoEconomicaList
        restItemAvaliacaoEconomicaMockMvc.perform(get("/api/item-avaliacao-economicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAvaliacaoEconomica.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoResposta").value(hasItem(DEFAULT_TIPO_RESPOSTA.toString())))
            .andExpect(jsonPath("$.[*].questao").value(hasItem(DEFAULT_QUESTAO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getItemAvaliacaoEconomica() throws Exception {
        // Initialize the database
        itemAvaliacaoEconomicaRepository.saveAndFlush(itemAvaliacaoEconomica);

        // Get the itemAvaliacaoEconomica
        restItemAvaliacaoEconomicaMockMvc.perform(get("/api/item-avaliacao-economicas/{id}", itemAvaliacaoEconomica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAvaliacaoEconomica.getId().intValue()))
            .andExpect(jsonPath("$.tipoResposta").value(DEFAULT_TIPO_RESPOSTA.toString()))
            .andExpect(jsonPath("$.questao").value(DEFAULT_QUESTAO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemAvaliacaoEconomica() throws Exception {
        // Get the itemAvaliacaoEconomica
        restItemAvaliacaoEconomicaMockMvc.perform(get("/api/item-avaliacao-economicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAvaliacaoEconomica() throws Exception {
        // Initialize the database
        itemAvaliacaoEconomicaService.save(itemAvaliacaoEconomica);

        int databaseSizeBeforeUpdate = itemAvaliacaoEconomicaRepository.findAll().size();

        // Update the itemAvaliacaoEconomica
        ItemAvaliacaoEconomica updatedItemAvaliacaoEconomica = itemAvaliacaoEconomicaRepository.findById(itemAvaliacaoEconomica.getId()).get();
        // Disconnect from session so that the updates on updatedItemAvaliacaoEconomica are not directly saved in db
        em.detach(updatedItemAvaliacaoEconomica);
        updatedItemAvaliacaoEconomica
            .tipoResposta(UPDATED_TIPO_RESPOSTA)
            .questao(UPDATED_QUESTAO)
            .obs(UPDATED_OBS);

        restItemAvaliacaoEconomicaMockMvc.perform(put("/api/item-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemAvaliacaoEconomica)))
            .andExpect(status().isOk());

        // Validate the ItemAvaliacaoEconomica in the database
        List<ItemAvaliacaoEconomica> itemAvaliacaoEconomicaList = itemAvaliacaoEconomicaRepository.findAll();
        assertThat(itemAvaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
        ItemAvaliacaoEconomica testItemAvaliacaoEconomica = itemAvaliacaoEconomicaList.get(itemAvaliacaoEconomicaList.size() - 1);
        assertThat(testItemAvaliacaoEconomica.getTipoResposta()).isEqualTo(UPDATED_TIPO_RESPOSTA);
        assertThat(testItemAvaliacaoEconomica.getQuestao()).isEqualTo(UPDATED_QUESTAO);
        assertThat(testItemAvaliacaoEconomica.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAvaliacaoEconomica() throws Exception {
        int databaseSizeBeforeUpdate = itemAvaliacaoEconomicaRepository.findAll().size();

        // Create the ItemAvaliacaoEconomica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAvaliacaoEconomicaMockMvc.perform(put("/api/item-avaliacao-economicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAvaliacaoEconomica)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAvaliacaoEconomica in the database
        List<ItemAvaliacaoEconomica> itemAvaliacaoEconomicaList = itemAvaliacaoEconomicaRepository.findAll();
        assertThat(itemAvaliacaoEconomicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemAvaliacaoEconomica() throws Exception {
        // Initialize the database
        itemAvaliacaoEconomicaService.save(itemAvaliacaoEconomica);

        int databaseSizeBeforeDelete = itemAvaliacaoEconomicaRepository.findAll().size();

        // Get the itemAvaliacaoEconomica
        restItemAvaliacaoEconomicaMockMvc.perform(delete("/api/item-avaliacao-economicas/{id}", itemAvaliacaoEconomica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAvaliacaoEconomica> itemAvaliacaoEconomicaList = itemAvaliacaoEconomicaRepository.findAll();
        assertThat(itemAvaliacaoEconomicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAvaliacaoEconomica.class);
        ItemAvaliacaoEconomica itemAvaliacaoEconomica1 = new ItemAvaliacaoEconomica();
        itemAvaliacaoEconomica1.setId(1L);
        ItemAvaliacaoEconomica itemAvaliacaoEconomica2 = new ItemAvaliacaoEconomica();
        itemAvaliacaoEconomica2.setId(itemAvaliacaoEconomica1.getId());
        assertThat(itemAvaliacaoEconomica1).isEqualTo(itemAvaliacaoEconomica2);
        itemAvaliacaoEconomica2.setId(2L);
        assertThat(itemAvaliacaoEconomica1).isNotEqualTo(itemAvaliacaoEconomica2);
        itemAvaliacaoEconomica1.setId(null);
        assertThat(itemAvaliacaoEconomica1).isNotEqualTo(itemAvaliacaoEconomica2);
    }
}
