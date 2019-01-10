package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoEnsino;
import br.com.jhisolution.ong.control.repository.ItemPlanejamentoEnsinoRepository;
import br.com.jhisolution.ong.control.service.ItemPlanejamentoEnsinoService;
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
 * Test class for the ItemPlanejamentoEnsinoResource REST controller.
 *
 * @see ItemPlanejamentoEnsinoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ItemPlanejamentoEnsinoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private ItemPlanejamentoEnsinoRepository itemPlanejamentoEnsinoRepository;

    @Autowired
    private ItemPlanejamentoEnsinoService itemPlanejamentoEnsinoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemPlanejamentoEnsinoMockMvc;

    private ItemPlanejamentoEnsino itemPlanejamentoEnsino;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemPlanejamentoEnsinoResource itemPlanejamentoEnsinoResource = new ItemPlanejamentoEnsinoResource(itemPlanejamentoEnsinoService);
        this.restItemPlanejamentoEnsinoMockMvc = MockMvcBuilders.standaloneSetup(itemPlanejamentoEnsinoResource)
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
    public static ItemPlanejamentoEnsino createEntity(EntityManager em) {
        ItemPlanejamentoEnsino itemPlanejamentoEnsino = new ItemPlanejamentoEnsino()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO);
        return itemPlanejamentoEnsino;
    }

    @Before
    public void initTest() {
        itemPlanejamentoEnsino = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemPlanejamentoEnsino() throws Exception {
        int databaseSizeBeforeCreate = itemPlanejamentoEnsinoRepository.findAll().size();

        // Create the ItemPlanejamentoEnsino
        restItemPlanejamentoEnsinoMockMvc.perform(post("/api/item-planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoEnsino)))
            .andExpect(status().isCreated());

        // Validate the ItemPlanejamentoEnsino in the database
        List<ItemPlanejamentoEnsino> itemPlanejamentoEnsinoList = itemPlanejamentoEnsinoRepository.findAll();
        assertThat(itemPlanejamentoEnsinoList).hasSize(databaseSizeBeforeCreate + 1);
        ItemPlanejamentoEnsino testItemPlanejamentoEnsino = itemPlanejamentoEnsinoList.get(itemPlanejamentoEnsinoList.size() - 1);
        assertThat(testItemPlanejamentoEnsino.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testItemPlanejamentoEnsino.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createItemPlanejamentoEnsinoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemPlanejamentoEnsinoRepository.findAll().size();

        // Create the ItemPlanejamentoEnsino with an existing ID
        itemPlanejamentoEnsino.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemPlanejamentoEnsinoMockMvc.perform(post("/api/item-planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoEnsino)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPlanejamentoEnsino in the database
        List<ItemPlanejamentoEnsino> itemPlanejamentoEnsinoList = itemPlanejamentoEnsinoRepository.findAll();
        assertThat(itemPlanejamentoEnsinoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPlanejamentoEnsinoRepository.findAll().size();
        // set the field null
        itemPlanejamentoEnsino.setNome(null);

        // Create the ItemPlanejamentoEnsino, which fails.

        restItemPlanejamentoEnsinoMockMvc.perform(post("/api/item-planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoEnsino)))
            .andExpect(status().isBadRequest());

        List<ItemPlanejamentoEnsino> itemPlanejamentoEnsinoList = itemPlanejamentoEnsinoRepository.findAll();
        assertThat(itemPlanejamentoEnsinoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPlanejamentoEnsinoRepository.findAll().size();
        // set the field null
        itemPlanejamentoEnsino.setDescricao(null);

        // Create the ItemPlanejamentoEnsino, which fails.

        restItemPlanejamentoEnsinoMockMvc.perform(post("/api/item-planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoEnsino)))
            .andExpect(status().isBadRequest());

        List<ItemPlanejamentoEnsino> itemPlanejamentoEnsinoList = itemPlanejamentoEnsinoRepository.findAll();
        assertThat(itemPlanejamentoEnsinoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemPlanejamentoEnsinos() throws Exception {
        // Initialize the database
        itemPlanejamentoEnsinoRepository.saveAndFlush(itemPlanejamentoEnsino);

        // Get all the itemPlanejamentoEnsinoList
        restItemPlanejamentoEnsinoMockMvc.perform(get("/api/item-planejamento-ensinos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemPlanejamentoEnsino.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }
    
    @Test
    @Transactional
    public void getItemPlanejamentoEnsino() throws Exception {
        // Initialize the database
        itemPlanejamentoEnsinoRepository.saveAndFlush(itemPlanejamentoEnsino);

        // Get the itemPlanejamentoEnsino
        restItemPlanejamentoEnsinoMockMvc.perform(get("/api/item-planejamento-ensinos/{id}", itemPlanejamentoEnsino.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemPlanejamentoEnsino.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemPlanejamentoEnsino() throws Exception {
        // Get the itemPlanejamentoEnsino
        restItemPlanejamentoEnsinoMockMvc.perform(get("/api/item-planejamento-ensinos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemPlanejamentoEnsino() throws Exception {
        // Initialize the database
        itemPlanejamentoEnsinoService.save(itemPlanejamentoEnsino);

        int databaseSizeBeforeUpdate = itemPlanejamentoEnsinoRepository.findAll().size();

        // Update the itemPlanejamentoEnsino
        ItemPlanejamentoEnsino updatedItemPlanejamentoEnsino = itemPlanejamentoEnsinoRepository.findById(itemPlanejamentoEnsino.getId()).get();
        // Disconnect from session so that the updates on updatedItemPlanejamentoEnsino are not directly saved in db
        em.detach(updatedItemPlanejamentoEnsino);
        updatedItemPlanejamentoEnsino
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO);

        restItemPlanejamentoEnsinoMockMvc.perform(put("/api/item-planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemPlanejamentoEnsino)))
            .andExpect(status().isOk());

        // Validate the ItemPlanejamentoEnsino in the database
        List<ItemPlanejamentoEnsino> itemPlanejamentoEnsinoList = itemPlanejamentoEnsinoRepository.findAll();
        assertThat(itemPlanejamentoEnsinoList).hasSize(databaseSizeBeforeUpdate);
        ItemPlanejamentoEnsino testItemPlanejamentoEnsino = itemPlanejamentoEnsinoList.get(itemPlanejamentoEnsinoList.size() - 1);
        assertThat(testItemPlanejamentoEnsino.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testItemPlanejamentoEnsino.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingItemPlanejamentoEnsino() throws Exception {
        int databaseSizeBeforeUpdate = itemPlanejamentoEnsinoRepository.findAll().size();

        // Create the ItemPlanejamentoEnsino

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemPlanejamentoEnsinoMockMvc.perform(put("/api/item-planejamento-ensinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoEnsino)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPlanejamentoEnsino in the database
        List<ItemPlanejamentoEnsino> itemPlanejamentoEnsinoList = itemPlanejamentoEnsinoRepository.findAll();
        assertThat(itemPlanejamentoEnsinoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemPlanejamentoEnsino() throws Exception {
        // Initialize the database
        itemPlanejamentoEnsinoService.save(itemPlanejamentoEnsino);

        int databaseSizeBeforeDelete = itemPlanejamentoEnsinoRepository.findAll().size();

        // Get the itemPlanejamentoEnsino
        restItemPlanejamentoEnsinoMockMvc.perform(delete("/api/item-planejamento-ensinos/{id}", itemPlanejamentoEnsino.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemPlanejamentoEnsino> itemPlanejamentoEnsinoList = itemPlanejamentoEnsinoRepository.findAll();
        assertThat(itemPlanejamentoEnsinoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPlanejamentoEnsino.class);
        ItemPlanejamentoEnsino itemPlanejamentoEnsino1 = new ItemPlanejamentoEnsino();
        itemPlanejamentoEnsino1.setId(1L);
        ItemPlanejamentoEnsino itemPlanejamentoEnsino2 = new ItemPlanejamentoEnsino();
        itemPlanejamentoEnsino2.setId(itemPlanejamentoEnsino1.getId());
        assertThat(itemPlanejamentoEnsino1).isEqualTo(itemPlanejamentoEnsino2);
        itemPlanejamentoEnsino2.setId(2L);
        assertThat(itemPlanejamentoEnsino1).isNotEqualTo(itemPlanejamentoEnsino2);
        itemPlanejamentoEnsino1.setId(null);
        assertThat(itemPlanejamentoEnsino1).isNotEqualTo(itemPlanejamentoEnsino2);
    }
}
