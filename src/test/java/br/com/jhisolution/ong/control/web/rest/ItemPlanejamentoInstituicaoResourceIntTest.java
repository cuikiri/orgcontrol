package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoInstituicao;
import br.com.jhisolution.ong.control.repository.ItemPlanejamentoInstituicaoRepository;
import br.com.jhisolution.ong.control.service.ItemPlanejamentoInstituicaoService;
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
 * Test class for the ItemPlanejamentoInstituicaoResource REST controller.
 *
 * @see ItemPlanejamentoInstituicaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ItemPlanejamentoInstituicaoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private ItemPlanejamentoInstituicaoRepository itemPlanejamentoInstituicaoRepository;

    @Autowired
    private ItemPlanejamentoInstituicaoService itemPlanejamentoInstituicaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemPlanejamentoInstituicaoMockMvc;

    private ItemPlanejamentoInstituicao itemPlanejamentoInstituicao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemPlanejamentoInstituicaoResource itemPlanejamentoInstituicaoResource = new ItemPlanejamentoInstituicaoResource(itemPlanejamentoInstituicaoService);
        this.restItemPlanejamentoInstituicaoMockMvc = MockMvcBuilders.standaloneSetup(itemPlanejamentoInstituicaoResource)
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
    public static ItemPlanejamentoInstituicao createEntity(EntityManager em) {
        ItemPlanejamentoInstituicao itemPlanejamentoInstituicao = new ItemPlanejamentoInstituicao()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO);
        return itemPlanejamentoInstituicao;
    }

    @Before
    public void initTest() {
        itemPlanejamentoInstituicao = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemPlanejamentoInstituicao() throws Exception {
        int databaseSizeBeforeCreate = itemPlanejamentoInstituicaoRepository.findAll().size();

        // Create the ItemPlanejamentoInstituicao
        restItemPlanejamentoInstituicaoMockMvc.perform(post("/api/item-planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoInstituicao)))
            .andExpect(status().isCreated());

        // Validate the ItemPlanejamentoInstituicao in the database
        List<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicaoList = itemPlanejamentoInstituicaoRepository.findAll();
        assertThat(itemPlanejamentoInstituicaoList).hasSize(databaseSizeBeforeCreate + 1);
        ItemPlanejamentoInstituicao testItemPlanejamentoInstituicao = itemPlanejamentoInstituicaoList.get(itemPlanejamentoInstituicaoList.size() - 1);
        assertThat(testItemPlanejamentoInstituicao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testItemPlanejamentoInstituicao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createItemPlanejamentoInstituicaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemPlanejamentoInstituicaoRepository.findAll().size();

        // Create the ItemPlanejamentoInstituicao with an existing ID
        itemPlanejamentoInstituicao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemPlanejamentoInstituicaoMockMvc.perform(post("/api/item-planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoInstituicao)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPlanejamentoInstituicao in the database
        List<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicaoList = itemPlanejamentoInstituicaoRepository.findAll();
        assertThat(itemPlanejamentoInstituicaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPlanejamentoInstituicaoRepository.findAll().size();
        // set the field null
        itemPlanejamentoInstituicao.setNome(null);

        // Create the ItemPlanejamentoInstituicao, which fails.

        restItemPlanejamentoInstituicaoMockMvc.perform(post("/api/item-planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoInstituicao)))
            .andExpect(status().isBadRequest());

        List<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicaoList = itemPlanejamentoInstituicaoRepository.findAll();
        assertThat(itemPlanejamentoInstituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPlanejamentoInstituicaoRepository.findAll().size();
        // set the field null
        itemPlanejamentoInstituicao.setDescricao(null);

        // Create the ItemPlanejamentoInstituicao, which fails.

        restItemPlanejamentoInstituicaoMockMvc.perform(post("/api/item-planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoInstituicao)))
            .andExpect(status().isBadRequest());

        List<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicaoList = itemPlanejamentoInstituicaoRepository.findAll();
        assertThat(itemPlanejamentoInstituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemPlanejamentoInstituicaos() throws Exception {
        // Initialize the database
        itemPlanejamentoInstituicaoRepository.saveAndFlush(itemPlanejamentoInstituicao);

        // Get all the itemPlanejamentoInstituicaoList
        restItemPlanejamentoInstituicaoMockMvc.perform(get("/api/item-planejamento-instituicaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemPlanejamentoInstituicao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }
    
    @Test
    @Transactional
    public void getItemPlanejamentoInstituicao() throws Exception {
        // Initialize the database
        itemPlanejamentoInstituicaoRepository.saveAndFlush(itemPlanejamentoInstituicao);

        // Get the itemPlanejamentoInstituicao
        restItemPlanejamentoInstituicaoMockMvc.perform(get("/api/item-planejamento-instituicaos/{id}", itemPlanejamentoInstituicao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemPlanejamentoInstituicao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemPlanejamentoInstituicao() throws Exception {
        // Get the itemPlanejamentoInstituicao
        restItemPlanejamentoInstituicaoMockMvc.perform(get("/api/item-planejamento-instituicaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemPlanejamentoInstituicao() throws Exception {
        // Initialize the database
        itemPlanejamentoInstituicaoService.save(itemPlanejamentoInstituicao);

        int databaseSizeBeforeUpdate = itemPlanejamentoInstituicaoRepository.findAll().size();

        // Update the itemPlanejamentoInstituicao
        ItemPlanejamentoInstituicao updatedItemPlanejamentoInstituicao = itemPlanejamentoInstituicaoRepository.findById(itemPlanejamentoInstituicao.getId()).get();
        // Disconnect from session so that the updates on updatedItemPlanejamentoInstituicao are not directly saved in db
        em.detach(updatedItemPlanejamentoInstituicao);
        updatedItemPlanejamentoInstituicao
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO);

        restItemPlanejamentoInstituicaoMockMvc.perform(put("/api/item-planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemPlanejamentoInstituicao)))
            .andExpect(status().isOk());

        // Validate the ItemPlanejamentoInstituicao in the database
        List<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicaoList = itemPlanejamentoInstituicaoRepository.findAll();
        assertThat(itemPlanejamentoInstituicaoList).hasSize(databaseSizeBeforeUpdate);
        ItemPlanejamentoInstituicao testItemPlanejamentoInstituicao = itemPlanejamentoInstituicaoList.get(itemPlanejamentoInstituicaoList.size() - 1);
        assertThat(testItemPlanejamentoInstituicao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testItemPlanejamentoInstituicao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingItemPlanejamentoInstituicao() throws Exception {
        int databaseSizeBeforeUpdate = itemPlanejamentoInstituicaoRepository.findAll().size();

        // Create the ItemPlanejamentoInstituicao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemPlanejamentoInstituicaoMockMvc.perform(put("/api/item-planejamento-instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoInstituicao)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPlanejamentoInstituicao in the database
        List<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicaoList = itemPlanejamentoInstituicaoRepository.findAll();
        assertThat(itemPlanejamentoInstituicaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemPlanejamentoInstituicao() throws Exception {
        // Initialize the database
        itemPlanejamentoInstituicaoService.save(itemPlanejamentoInstituicao);

        int databaseSizeBeforeDelete = itemPlanejamentoInstituicaoRepository.findAll().size();

        // Get the itemPlanejamentoInstituicao
        restItemPlanejamentoInstituicaoMockMvc.perform(delete("/api/item-planejamento-instituicaos/{id}", itemPlanejamentoInstituicao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicaoList = itemPlanejamentoInstituicaoRepository.findAll();
        assertThat(itemPlanejamentoInstituicaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPlanejamentoInstituicao.class);
        ItemPlanejamentoInstituicao itemPlanejamentoInstituicao1 = new ItemPlanejamentoInstituicao();
        itemPlanejamentoInstituicao1.setId(1L);
        ItemPlanejamentoInstituicao itemPlanejamentoInstituicao2 = new ItemPlanejamentoInstituicao();
        itemPlanejamentoInstituicao2.setId(itemPlanejamentoInstituicao1.getId());
        assertThat(itemPlanejamentoInstituicao1).isEqualTo(itemPlanejamentoInstituicao2);
        itemPlanejamentoInstituicao2.setId(2L);
        assertThat(itemPlanejamentoInstituicao1).isNotEqualTo(itemPlanejamentoInstituicao2);
        itemPlanejamentoInstituicao1.setId(null);
        assertThat(itemPlanejamentoInstituicao1).isNotEqualTo(itemPlanejamentoInstituicao2);
    }
}
