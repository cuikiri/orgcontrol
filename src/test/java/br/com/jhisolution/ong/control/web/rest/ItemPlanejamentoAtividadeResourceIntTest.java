package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoAtividade;
import br.com.jhisolution.ong.control.repository.ItemPlanejamentoAtividadeRepository;
import br.com.jhisolution.ong.control.service.ItemPlanejamentoAtividadeService;
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
 * Test class for the ItemPlanejamentoAtividadeResource REST controller.
 *
 * @see ItemPlanejamentoAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ItemPlanejamentoAtividadeResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private ItemPlanejamentoAtividadeRepository itemPlanejamentoAtividadeRepository;

    @Autowired
    private ItemPlanejamentoAtividadeService itemPlanejamentoAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemPlanejamentoAtividadeMockMvc;

    private ItemPlanejamentoAtividade itemPlanejamentoAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemPlanejamentoAtividadeResource itemPlanejamentoAtividadeResource = new ItemPlanejamentoAtividadeResource(itemPlanejamentoAtividadeService);
        this.restItemPlanejamentoAtividadeMockMvc = MockMvcBuilders.standaloneSetup(itemPlanejamentoAtividadeResource)
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
    public static ItemPlanejamentoAtividade createEntity(EntityManager em) {
        ItemPlanejamentoAtividade itemPlanejamentoAtividade = new ItemPlanejamentoAtividade()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO);
        return itemPlanejamentoAtividade;
    }

    @Before
    public void initTest() {
        itemPlanejamentoAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemPlanejamentoAtividade() throws Exception {
        int databaseSizeBeforeCreate = itemPlanejamentoAtividadeRepository.findAll().size();

        // Create the ItemPlanejamentoAtividade
        restItemPlanejamentoAtividadeMockMvc.perform(post("/api/item-planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoAtividade)))
            .andExpect(status().isCreated());

        // Validate the ItemPlanejamentoAtividade in the database
        List<ItemPlanejamentoAtividade> itemPlanejamentoAtividadeList = itemPlanejamentoAtividadeRepository.findAll();
        assertThat(itemPlanejamentoAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemPlanejamentoAtividade testItemPlanejamentoAtividade = itemPlanejamentoAtividadeList.get(itemPlanejamentoAtividadeList.size() - 1);
        assertThat(testItemPlanejamentoAtividade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testItemPlanejamentoAtividade.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createItemPlanejamentoAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemPlanejamentoAtividadeRepository.findAll().size();

        // Create the ItemPlanejamentoAtividade with an existing ID
        itemPlanejamentoAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemPlanejamentoAtividadeMockMvc.perform(post("/api/item-planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPlanejamentoAtividade in the database
        List<ItemPlanejamentoAtividade> itemPlanejamentoAtividadeList = itemPlanejamentoAtividadeRepository.findAll();
        assertThat(itemPlanejamentoAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPlanejamentoAtividadeRepository.findAll().size();
        // set the field null
        itemPlanejamentoAtividade.setNome(null);

        // Create the ItemPlanejamentoAtividade, which fails.

        restItemPlanejamentoAtividadeMockMvc.perform(post("/api/item-planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<ItemPlanejamentoAtividade> itemPlanejamentoAtividadeList = itemPlanejamentoAtividadeRepository.findAll();
        assertThat(itemPlanejamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPlanejamentoAtividadeRepository.findAll().size();
        // set the field null
        itemPlanejamentoAtividade.setDescricao(null);

        // Create the ItemPlanejamentoAtividade, which fails.

        restItemPlanejamentoAtividadeMockMvc.perform(post("/api/item-planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<ItemPlanejamentoAtividade> itemPlanejamentoAtividadeList = itemPlanejamentoAtividadeRepository.findAll();
        assertThat(itemPlanejamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemPlanejamentoAtividades() throws Exception {
        // Initialize the database
        itemPlanejamentoAtividadeRepository.saveAndFlush(itemPlanejamentoAtividade);

        // Get all the itemPlanejamentoAtividadeList
        restItemPlanejamentoAtividadeMockMvc.perform(get("/api/item-planejamento-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemPlanejamentoAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }
    
    @Test
    @Transactional
    public void getItemPlanejamentoAtividade() throws Exception {
        // Initialize the database
        itemPlanejamentoAtividadeRepository.saveAndFlush(itemPlanejamentoAtividade);

        // Get the itemPlanejamentoAtividade
        restItemPlanejamentoAtividadeMockMvc.perform(get("/api/item-planejamento-atividades/{id}", itemPlanejamentoAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemPlanejamentoAtividade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemPlanejamentoAtividade() throws Exception {
        // Get the itemPlanejamentoAtividade
        restItemPlanejamentoAtividadeMockMvc.perform(get("/api/item-planejamento-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemPlanejamentoAtividade() throws Exception {
        // Initialize the database
        itemPlanejamentoAtividadeService.save(itemPlanejamentoAtividade);

        int databaseSizeBeforeUpdate = itemPlanejamentoAtividadeRepository.findAll().size();

        // Update the itemPlanejamentoAtividade
        ItemPlanejamentoAtividade updatedItemPlanejamentoAtividade = itemPlanejamentoAtividadeRepository.findById(itemPlanejamentoAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedItemPlanejamentoAtividade are not directly saved in db
        em.detach(updatedItemPlanejamentoAtividade);
        updatedItemPlanejamentoAtividade
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO);

        restItemPlanejamentoAtividadeMockMvc.perform(put("/api/item-planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemPlanejamentoAtividade)))
            .andExpect(status().isOk());

        // Validate the ItemPlanejamentoAtividade in the database
        List<ItemPlanejamentoAtividade> itemPlanejamentoAtividadeList = itemPlanejamentoAtividadeRepository.findAll();
        assertThat(itemPlanejamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
        ItemPlanejamentoAtividade testItemPlanejamentoAtividade = itemPlanejamentoAtividadeList.get(itemPlanejamentoAtividadeList.size() - 1);
        assertThat(testItemPlanejamentoAtividade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testItemPlanejamentoAtividade.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingItemPlanejamentoAtividade() throws Exception {
        int databaseSizeBeforeUpdate = itemPlanejamentoAtividadeRepository.findAll().size();

        // Create the ItemPlanejamentoAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemPlanejamentoAtividadeMockMvc.perform(put("/api/item-planejamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemPlanejamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPlanejamentoAtividade in the database
        List<ItemPlanejamentoAtividade> itemPlanejamentoAtividadeList = itemPlanejamentoAtividadeRepository.findAll();
        assertThat(itemPlanejamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemPlanejamentoAtividade() throws Exception {
        // Initialize the database
        itemPlanejamentoAtividadeService.save(itemPlanejamentoAtividade);

        int databaseSizeBeforeDelete = itemPlanejamentoAtividadeRepository.findAll().size();

        // Get the itemPlanejamentoAtividade
        restItemPlanejamentoAtividadeMockMvc.perform(delete("/api/item-planejamento-atividades/{id}", itemPlanejamentoAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemPlanejamentoAtividade> itemPlanejamentoAtividadeList = itemPlanejamentoAtividadeRepository.findAll();
        assertThat(itemPlanejamentoAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPlanejamentoAtividade.class);
        ItemPlanejamentoAtividade itemPlanejamentoAtividade1 = new ItemPlanejamentoAtividade();
        itemPlanejamentoAtividade1.setId(1L);
        ItemPlanejamentoAtividade itemPlanejamentoAtividade2 = new ItemPlanejamentoAtividade();
        itemPlanejamentoAtividade2.setId(itemPlanejamentoAtividade1.getId());
        assertThat(itemPlanejamentoAtividade1).isEqualTo(itemPlanejamentoAtividade2);
        itemPlanejamentoAtividade2.setId(2L);
        assertThat(itemPlanejamentoAtividade1).isNotEqualTo(itemPlanejamentoAtividade2);
        itemPlanejamentoAtividade1.setId(null);
        assertThat(itemPlanejamentoAtividade1).isNotEqualTo(itemPlanejamentoAtividade2);
    }
}
