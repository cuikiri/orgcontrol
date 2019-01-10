package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.ItemAcompanhamentoAtividade;
import br.com.jhisolution.ong.control.repository.ItemAcompanhamentoAtividadeRepository;
import br.com.jhisolution.ong.control.service.ItemAcompanhamentoAtividadeService;
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
 * Test class for the ItemAcompanhamentoAtividadeResource REST controller.
 *
 * @see ItemAcompanhamentoAtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class ItemAcompanhamentoAtividadeResourceIntTest {

    private static final TipoResposta DEFAULT_TIPO_RESPOSTA = TipoResposta.DESCRITIVA;
    private static final TipoResposta UPDATED_TIPO_RESPOSTA = TipoResposta.OPTATIVA;

    private static final String DEFAULT_QUESTAO = "AAAAAAAAAA";
    private static final String UPDATED_QUESTAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private ItemAcompanhamentoAtividadeRepository itemAcompanhamentoAtividadeRepository;

    @Autowired
    private ItemAcompanhamentoAtividadeService itemAcompanhamentoAtividadeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAcompanhamentoAtividadeMockMvc;

    private ItemAcompanhamentoAtividade itemAcompanhamentoAtividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAcompanhamentoAtividadeResource itemAcompanhamentoAtividadeResource = new ItemAcompanhamentoAtividadeResource(itemAcompanhamentoAtividadeService);
        this.restItemAcompanhamentoAtividadeMockMvc = MockMvcBuilders.standaloneSetup(itemAcompanhamentoAtividadeResource)
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
    public static ItemAcompanhamentoAtividade createEntity(EntityManager em) {
        ItemAcompanhamentoAtividade itemAcompanhamentoAtividade = new ItemAcompanhamentoAtividade()
            .tipoResposta(DEFAULT_TIPO_RESPOSTA)
            .questao(DEFAULT_QUESTAO)
            .obs(DEFAULT_OBS);
        return itemAcompanhamentoAtividade;
    }

    @Before
    public void initTest() {
        itemAcompanhamentoAtividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAcompanhamentoAtividade() throws Exception {
        int databaseSizeBeforeCreate = itemAcompanhamentoAtividadeRepository.findAll().size();

        // Create the ItemAcompanhamentoAtividade
        restItemAcompanhamentoAtividadeMockMvc.perform(post("/api/item-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAcompanhamentoAtividade)))
            .andExpect(status().isCreated());

        // Validate the ItemAcompanhamentoAtividade in the database
        List<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividadeList = itemAcompanhamentoAtividadeRepository.findAll();
        assertThat(itemAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAcompanhamentoAtividade testItemAcompanhamentoAtividade = itemAcompanhamentoAtividadeList.get(itemAcompanhamentoAtividadeList.size() - 1);
        assertThat(testItemAcompanhamentoAtividade.getTipoResposta()).isEqualTo(DEFAULT_TIPO_RESPOSTA);
        assertThat(testItemAcompanhamentoAtividade.getQuestao()).isEqualTo(DEFAULT_QUESTAO);
        assertThat(testItemAcompanhamentoAtividade.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createItemAcompanhamentoAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAcompanhamentoAtividadeRepository.findAll().size();

        // Create the ItemAcompanhamentoAtividade with an existing ID
        itemAcompanhamentoAtividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAcompanhamentoAtividadeMockMvc.perform(post("/api/item-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAcompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAcompanhamentoAtividade in the database
        List<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividadeList = itemAcompanhamentoAtividadeRepository.findAll();
        assertThat(itemAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAcompanhamentoAtividadeRepository.findAll().size();
        // set the field null
        itemAcompanhamentoAtividade.setTipoResposta(null);

        // Create the ItemAcompanhamentoAtividade, which fails.

        restItemAcompanhamentoAtividadeMockMvc.perform(post("/api/item-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAcompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividadeList = itemAcompanhamentoAtividadeRepository.findAll();
        assertThat(itemAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuestaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAcompanhamentoAtividadeRepository.findAll().size();
        // set the field null
        itemAcompanhamentoAtividade.setQuestao(null);

        // Create the ItemAcompanhamentoAtividade, which fails.

        restItemAcompanhamentoAtividadeMockMvc.perform(post("/api/item-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAcompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        List<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividadeList = itemAcompanhamentoAtividadeRepository.findAll();
        assertThat(itemAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAcompanhamentoAtividades() throws Exception {
        // Initialize the database
        itemAcompanhamentoAtividadeRepository.saveAndFlush(itemAcompanhamentoAtividade);

        // Get all the itemAcompanhamentoAtividadeList
        restItemAcompanhamentoAtividadeMockMvc.perform(get("/api/item-acompanhamento-atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAcompanhamentoAtividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoResposta").value(hasItem(DEFAULT_TIPO_RESPOSTA.toString())))
            .andExpect(jsonPath("$.[*].questao").value(hasItem(DEFAULT_QUESTAO.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getItemAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        itemAcompanhamentoAtividadeRepository.saveAndFlush(itemAcompanhamentoAtividade);

        // Get the itemAcompanhamentoAtividade
        restItemAcompanhamentoAtividadeMockMvc.perform(get("/api/item-acompanhamento-atividades/{id}", itemAcompanhamentoAtividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAcompanhamentoAtividade.getId().intValue()))
            .andExpect(jsonPath("$.tipoResposta").value(DEFAULT_TIPO_RESPOSTA.toString()))
            .andExpect(jsonPath("$.questao").value(DEFAULT_QUESTAO.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemAcompanhamentoAtividade() throws Exception {
        // Get the itemAcompanhamentoAtividade
        restItemAcompanhamentoAtividadeMockMvc.perform(get("/api/item-acompanhamento-atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        itemAcompanhamentoAtividadeService.save(itemAcompanhamentoAtividade);

        int databaseSizeBeforeUpdate = itemAcompanhamentoAtividadeRepository.findAll().size();

        // Update the itemAcompanhamentoAtividade
        ItemAcompanhamentoAtividade updatedItemAcompanhamentoAtividade = itemAcompanhamentoAtividadeRepository.findById(itemAcompanhamentoAtividade.getId()).get();
        // Disconnect from session so that the updates on updatedItemAcompanhamentoAtividade are not directly saved in db
        em.detach(updatedItemAcompanhamentoAtividade);
        updatedItemAcompanhamentoAtividade
            .tipoResposta(UPDATED_TIPO_RESPOSTA)
            .questao(UPDATED_QUESTAO)
            .obs(UPDATED_OBS);

        restItemAcompanhamentoAtividadeMockMvc.perform(put("/api/item-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemAcompanhamentoAtividade)))
            .andExpect(status().isOk());

        // Validate the ItemAcompanhamentoAtividade in the database
        List<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividadeList = itemAcompanhamentoAtividadeRepository.findAll();
        assertThat(itemAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
        ItemAcompanhamentoAtividade testItemAcompanhamentoAtividade = itemAcompanhamentoAtividadeList.get(itemAcompanhamentoAtividadeList.size() - 1);
        assertThat(testItemAcompanhamentoAtividade.getTipoResposta()).isEqualTo(UPDATED_TIPO_RESPOSTA);
        assertThat(testItemAcompanhamentoAtividade.getQuestao()).isEqualTo(UPDATED_QUESTAO);
        assertThat(testItemAcompanhamentoAtividade.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAcompanhamentoAtividade() throws Exception {
        int databaseSizeBeforeUpdate = itemAcompanhamentoAtividadeRepository.findAll().size();

        // Create the ItemAcompanhamentoAtividade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAcompanhamentoAtividadeMockMvc.perform(put("/api/item-acompanhamento-atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAcompanhamentoAtividade)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAcompanhamentoAtividade in the database
        List<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividadeList = itemAcompanhamentoAtividadeRepository.findAll();
        assertThat(itemAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemAcompanhamentoAtividade() throws Exception {
        // Initialize the database
        itemAcompanhamentoAtividadeService.save(itemAcompanhamentoAtividade);

        int databaseSizeBeforeDelete = itemAcompanhamentoAtividadeRepository.findAll().size();

        // Get the itemAcompanhamentoAtividade
        restItemAcompanhamentoAtividadeMockMvc.perform(delete("/api/item-acompanhamento-atividades/{id}", itemAcompanhamentoAtividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividadeList = itemAcompanhamentoAtividadeRepository.findAll();
        assertThat(itemAcompanhamentoAtividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAcompanhamentoAtividade.class);
        ItemAcompanhamentoAtividade itemAcompanhamentoAtividade1 = new ItemAcompanhamentoAtividade();
        itemAcompanhamentoAtividade1.setId(1L);
        ItemAcompanhamentoAtividade itemAcompanhamentoAtividade2 = new ItemAcompanhamentoAtividade();
        itemAcompanhamentoAtividade2.setId(itemAcompanhamentoAtividade1.getId());
        assertThat(itemAcompanhamentoAtividade1).isEqualTo(itemAcompanhamentoAtividade2);
        itemAcompanhamentoAtividade2.setId(2L);
        assertThat(itemAcompanhamentoAtividade1).isNotEqualTo(itemAcompanhamentoAtividade2);
        itemAcompanhamentoAtividade1.setId(null);
        assertThat(itemAcompanhamentoAtividade1).isNotEqualTo(itemAcompanhamentoAtividade2);
    }
}
