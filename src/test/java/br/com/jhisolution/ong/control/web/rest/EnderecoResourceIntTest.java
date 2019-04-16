package br.com.jhisolution.ong.control.web.rest;

import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

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

import br.com.jhisolution.ong.control.OrgcontrolApp;
import br.com.jhisolution.ong.control.domain.Endereco;
import br.com.jhisolution.ong.control.domain.enumeration.EnderecoTipo;
import br.com.jhisolution.ong.control.domain.enumeration.Regiao;
import br.com.jhisolution.ong.control.domain.enumeration.TipoBairro;
import br.com.jhisolution.ong.control.domain.enumeration.TipoLogradouro;
import br.com.jhisolution.ong.control.domain.enumeration.TipoResidencia;
import br.com.jhisolution.ong.control.repository.EnderecoRepository;
import br.com.jhisolution.ong.control.repository.UserRepository;
import br.com.jhisolution.ong.control.service.EnderecoService;
import br.com.jhisolution.ong.control.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the EnderecoResource REST controller.
 *
 * @see EnderecoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class EnderecoResourceIntTest {

    private static final TipoResidencia DEFAULT_TIPO_RESIDENCIA = TipoResidencia.CASA;
    private static final TipoResidencia UPDATED_TIPO_RESIDENCIA = TipoResidencia.APARTAMENTO;

    private static final EnderecoTipo DEFAULT_TIPO_ENDERECO = EnderecoTipo.RESIDENCIAL;
    private static final EnderecoTipo UPDATED_TIPO_ENDERECO = EnderecoTipo.COMERCIAL;

    private static final TipoLogradouro DEFAULT_TIPO_LOGRADOURO = TipoLogradouro.RUA;
    private static final TipoLogradouro UPDATED_TIPO_LOGRADOURO = TipoLogradouro.AVENIDA;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO = 99999;
    private static final Integer UPDATED_NUMERO = 99998;

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final TipoBairro DEFAULT_TIPO_BAIROO = TipoBairro.RESIDENCIAL;
    private static final TipoBairro UPDATED_TIPO_BAIROO = TipoBairro.COMERCIAL;

    private static final Regiao DEFAULT_ZONA = Regiao.CENTRAL;
    private static final Regiao UPDATED_ZONA = Regiao.LESTE;

    private static final String DEFAULT_CEP = "AAAAAAAAAA";
    private static final String UPDATED_CEP = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCO = "AAAAAAAAAA";
    private static final String UPDATED_BLOCO = "BBBBBBBBBB";

    private static final String DEFAULT_APTO = "AAAAAAAAAA";
    private static final String UPDATED_APTO = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnderecoMockMvc;

    private Endereco endereco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnderecoResource enderecoResource = new EnderecoResource(enderecoService, userRepository);
        this.restEnderecoMockMvc = MockMvcBuilders.standaloneSetup(enderecoResource)
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
    public static Endereco createEntity(EntityManager em) {
        Endereco endereco = new Endereco()
            .tipoResidencia(DEFAULT_TIPO_RESIDENCIA)
            .tipoEndereco(DEFAULT_TIPO_ENDERECO)
            .tipoLogradouro(DEFAULT_TIPO_LOGRADOURO)
            .nome(DEFAULT_NOME)
            .numero(DEFAULT_NUMERO)
            .bairro(DEFAULT_BAIRRO)
            .tipoBairoo(DEFAULT_TIPO_BAIROO)
            .zona(DEFAULT_ZONA)
            .cep(DEFAULT_CEP)
            .bloco(DEFAULT_BLOCO)
            .apto(DEFAULT_APTO)
            .complemento(DEFAULT_COMPLEMENTO)
            .cidade(DEFAULT_CIDADE);
        return endereco;
    }

    @Before
    public void initTest() {
        endereco = createEntity(em);
    }

    @Test
    @Transactional
    public void createEndereco() throws Exception {
        int databaseSizeBeforeCreate = enderecoRepository.findAll().size();

        // Create the Endereco
        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(endereco)))
            .andExpect(status().isCreated());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeCreate + 1);
        Endereco testEndereco = enderecoList.get(enderecoList.size() - 1);
        assertThat(testEndereco.getTipoResidencia()).isEqualTo(DEFAULT_TIPO_RESIDENCIA);
        assertThat(testEndereco.getTipoEndereco()).isEqualTo(DEFAULT_TIPO_ENDERECO);
        assertThat(testEndereco.getTipoLogradouro()).isEqualTo(DEFAULT_TIPO_LOGRADOURO);
        assertThat(testEndereco.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testEndereco.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testEndereco.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testEndereco.getTipoBairoo()).isEqualTo(DEFAULT_TIPO_BAIROO);
        assertThat(testEndereco.getZona()).isEqualTo(DEFAULT_ZONA);
        assertThat(testEndereco.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testEndereco.getBloco()).isEqualTo(DEFAULT_BLOCO);
        assertThat(testEndereco.getApto()).isEqualTo(DEFAULT_APTO);
        assertThat(testEndereco.getComplemento()).isEqualTo(DEFAULT_COMPLEMENTO);
        assertThat(testEndereco.getCidade()).isEqualTo(DEFAULT_CIDADE);
    }

    @Test
    @Transactional
    public void createEnderecoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enderecoRepository.findAll().size();

        // Create the Endereco with an existing ID
        endereco.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(endereco)))
            .andExpect(status().isBadRequest());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBairroIsRequired() throws Exception {
        int databaseSizeBeforeTest = enderecoRepository.findAll().size();
        // set the field null
        endereco.setBairro(null);

        // Create the Endereco, which fails.

        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(endereco)))
            .andExpect(status().isBadRequest());

        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoBairooIsRequired() throws Exception {
        int databaseSizeBeforeTest = enderecoRepository.findAll().size();
        // set the field null
        endereco.setTipoBairoo(null);

        // Create the Endereco, which fails.

        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(endereco)))
            .andExpect(status().isBadRequest());

        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZonaIsRequired() throws Exception {
        int databaseSizeBeforeTest = enderecoRepository.findAll().size();
        // set the field null
        endereco.setZona(null);

        // Create the Endereco, which fails.

        restEnderecoMockMvc.perform(post("/api/enderecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(endereco)))
            .andExpect(status().isBadRequest());

        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnderecos() throws Exception {
        // Initialize the database
        enderecoRepository.saveAndFlush(endereco);

        // Get all the enderecoList
        restEnderecoMockMvc.perform(get("/api/enderecos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(endereco.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoResidencia").value(hasItem(DEFAULT_TIPO_RESIDENCIA.toString())))
            .andExpect(jsonPath("$.[*].tipoEndereco").value(hasItem(DEFAULT_TIPO_ENDERECO.toString())))
            .andExpect(jsonPath("$.[*].tipoLogradouro").value(hasItem(DEFAULT_TIPO_LOGRADOURO.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO.toString())))
            .andExpect(jsonPath("$.[*].tipoBairoo").value(hasItem(DEFAULT_TIPO_BAIROO.toString())))
            .andExpect(jsonPath("$.[*].zona").value(hasItem(DEFAULT_ZONA.toString())))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP.toString())))
            .andExpect(jsonPath("$.[*].bloco").value(hasItem(DEFAULT_BLOCO.toString())))
            .andExpect(jsonPath("$.[*].apto").value(hasItem(DEFAULT_APTO.toString())))
            .andExpect(jsonPath("$.[*].complemento").value(hasItem(DEFAULT_COMPLEMENTO.toString())))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE.toString())));
    }
    
    @Test
    @Transactional
    public void getEndereco() throws Exception {
        // Initialize the database
        enderecoRepository.saveAndFlush(endereco);

        // Get the endereco
        restEnderecoMockMvc.perform(get("/api/enderecos/{id}", endereco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(endereco.getId().intValue()))
            .andExpect(jsonPath("$.tipoResidencia").value(DEFAULT_TIPO_RESIDENCIA.toString()))
            .andExpect(jsonPath("$.tipoEndereco").value(DEFAULT_TIPO_ENDERECO.toString()))
            .andExpect(jsonPath("$.tipoLogradouro").value(DEFAULT_TIPO_LOGRADOURO.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO.toString()))
            .andExpect(jsonPath("$.tipoBairoo").value(DEFAULT_TIPO_BAIROO.toString()))
            .andExpect(jsonPath("$.zona").value(DEFAULT_ZONA.toString()))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP.toString()))
            .andExpect(jsonPath("$.bloco").value(DEFAULT_BLOCO.toString()))
            .andExpect(jsonPath("$.apto").value(DEFAULT_APTO.toString()))
            .andExpect(jsonPath("$.complemento").value(DEFAULT_COMPLEMENTO.toString()))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEndereco() throws Exception {
        // Get the endereco
        restEnderecoMockMvc.perform(get("/api/enderecos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEndereco() throws Exception {
        // Initialize the database
        enderecoService.save(endereco);

        int databaseSizeBeforeUpdate = enderecoRepository.findAll().size();

        // Update the endereco
        Endereco updatedEndereco = enderecoRepository.findById(endereco.getId()).get();
        // Disconnect from session so that the updates on updatedEndereco are not directly saved in db
        em.detach(updatedEndereco);
        updatedEndereco
            .tipoResidencia(UPDATED_TIPO_RESIDENCIA)
            .tipoEndereco(UPDATED_TIPO_ENDERECO)
            .tipoLogradouro(UPDATED_TIPO_LOGRADOURO)
            .nome(UPDATED_NOME)
            .numero(UPDATED_NUMERO)
            .bairro(UPDATED_BAIRRO)
            .tipoBairoo(UPDATED_TIPO_BAIROO)
            .zona(UPDATED_ZONA)
            .cep(UPDATED_CEP)
            .bloco(UPDATED_BLOCO)
            .apto(UPDATED_APTO)
            .complemento(UPDATED_COMPLEMENTO)
            .cidade(UPDATED_CIDADE);

        restEnderecoMockMvc.perform(put("/api/enderecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEndereco)))
            .andExpect(status().isOk());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeUpdate);
        Endereco testEndereco = enderecoList.get(enderecoList.size() - 1);
        assertThat(testEndereco.getTipoResidencia()).isEqualTo(UPDATED_TIPO_RESIDENCIA);
        assertThat(testEndereco.getTipoEndereco()).isEqualTo(UPDATED_TIPO_ENDERECO);
        assertThat(testEndereco.getTipoLogradouro()).isEqualTo(UPDATED_TIPO_LOGRADOURO);
        assertThat(testEndereco.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testEndereco.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testEndereco.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testEndereco.getTipoBairoo()).isEqualTo(UPDATED_TIPO_BAIROO);
        assertThat(testEndereco.getZona()).isEqualTo(UPDATED_ZONA);
        assertThat(testEndereco.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testEndereco.getBloco()).isEqualTo(UPDATED_BLOCO);
        assertThat(testEndereco.getApto()).isEqualTo(UPDATED_APTO);
        assertThat(testEndereco.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
        assertThat(testEndereco.getCidade()).isEqualTo(UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void updateNonExistingEndereco() throws Exception {
        int databaseSizeBeforeUpdate = enderecoRepository.findAll().size();

        // Create the Endereco

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnderecoMockMvc.perform(put("/api/enderecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(endereco)))
            .andExpect(status().isBadRequest());

        // Validate the Endereco in the database
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEndereco() throws Exception {
        // Initialize the database
        enderecoService.save(endereco);

        int databaseSizeBeforeDelete = enderecoRepository.findAll().size();

        // Get the endereco
        restEnderecoMockMvc.perform(delete("/api/enderecos/{id}", endereco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Endereco> enderecoList = enderecoRepository.findAll();
        assertThat(enderecoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Endereco.class);
        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        Endereco endereco2 = new Endereco();
        endereco2.setId(endereco1.getId());
        assertThat(endereco1).isEqualTo(endereco2);
        endereco2.setId(2L);
        assertThat(endereco1).isNotEqualTo(endereco2);
        endereco1.setId(null);
        assertThat(endereco1).isNotEqualTo(endereco2);
    }
}
