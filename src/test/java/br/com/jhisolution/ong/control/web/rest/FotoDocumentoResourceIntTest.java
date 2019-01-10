package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.FotoDocumento;
import br.com.jhisolution.ong.control.repository.FotoDocumentoRepository;
import br.com.jhisolution.ong.control.service.FotoDocumentoService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.jhisolution.ong.control.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FotoDocumentoResource REST controller.
 *
 * @see FotoDocumentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class FotoDocumentoResourceIntTest {

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private FotoDocumentoRepository fotoDocumentoRepository;

    @Autowired
    private FotoDocumentoService fotoDocumentoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFotoDocumentoMockMvc;

    private FotoDocumento fotoDocumento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FotoDocumentoResource fotoDocumentoResource = new FotoDocumentoResource(fotoDocumentoService);
        this.restFotoDocumentoMockMvc = MockMvcBuilders.standaloneSetup(fotoDocumentoResource)
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
    public static FotoDocumento createEntity(EntityManager em) {
        FotoDocumento fotoDocumento = new FotoDocumento()
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE);
        return fotoDocumento;
    }

    @Before
    public void initTest() {
        fotoDocumento = createEntity(em);
    }

    @Test
    @Transactional
    public void createFotoDocumento() throws Exception {
        int databaseSizeBeforeCreate = fotoDocumentoRepository.findAll().size();

        // Create the FotoDocumento
        restFotoDocumentoMockMvc.perform(post("/api/foto-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoDocumento)))
            .andExpect(status().isCreated());

        // Validate the FotoDocumento in the database
        List<FotoDocumento> fotoDocumentoList = fotoDocumentoRepository.findAll();
        assertThat(fotoDocumentoList).hasSize(databaseSizeBeforeCreate + 1);
        FotoDocumento testFotoDocumento = fotoDocumentoList.get(fotoDocumentoList.size() - 1);
        assertThat(testFotoDocumento.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testFotoDocumento.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFotoDocumentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fotoDocumentoRepository.findAll().size();

        // Create the FotoDocumento with an existing ID
        fotoDocumento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFotoDocumentoMockMvc.perform(post("/api/foto-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoDocumento)))
            .andExpect(status().isBadRequest());

        // Validate the FotoDocumento in the database
        List<FotoDocumento> fotoDocumentoList = fotoDocumentoRepository.findAll();
        assertThat(fotoDocumentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFotoDocumentos() throws Exception {
        // Initialize the database
        fotoDocumentoRepository.saveAndFlush(fotoDocumento);

        // Get all the fotoDocumentoList
        restFotoDocumentoMockMvc.perform(get("/api/foto-documentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fotoDocumento.getId().intValue())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));
    }
    
    @Test
    @Transactional
    public void getFotoDocumento() throws Exception {
        // Initialize the database
        fotoDocumentoRepository.saveAndFlush(fotoDocumento);

        // Get the fotoDocumento
        restFotoDocumentoMockMvc.perform(get("/api/foto-documentos/{id}", fotoDocumento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fotoDocumento.getId().intValue()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)));
    }

    @Test
    @Transactional
    public void getNonExistingFotoDocumento() throws Exception {
        // Get the fotoDocumento
        restFotoDocumentoMockMvc.perform(get("/api/foto-documentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFotoDocumento() throws Exception {
        // Initialize the database
        fotoDocumentoService.save(fotoDocumento);

        int databaseSizeBeforeUpdate = fotoDocumentoRepository.findAll().size();

        // Update the fotoDocumento
        FotoDocumento updatedFotoDocumento = fotoDocumentoRepository.findById(fotoDocumento.getId()).get();
        // Disconnect from session so that the updates on updatedFotoDocumento are not directly saved in db
        em.detach(updatedFotoDocumento);
        updatedFotoDocumento
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);

        restFotoDocumentoMockMvc.perform(put("/api/foto-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFotoDocumento)))
            .andExpect(status().isOk());

        // Validate the FotoDocumento in the database
        List<FotoDocumento> fotoDocumentoList = fotoDocumentoRepository.findAll();
        assertThat(fotoDocumentoList).hasSize(databaseSizeBeforeUpdate);
        FotoDocumento testFotoDocumento = fotoDocumentoList.get(fotoDocumentoList.size() - 1);
        assertThat(testFotoDocumento.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testFotoDocumento.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFotoDocumento() throws Exception {
        int databaseSizeBeforeUpdate = fotoDocumentoRepository.findAll().size();

        // Create the FotoDocumento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFotoDocumentoMockMvc.perform(put("/api/foto-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoDocumento)))
            .andExpect(status().isBadRequest());

        // Validate the FotoDocumento in the database
        List<FotoDocumento> fotoDocumentoList = fotoDocumentoRepository.findAll();
        assertThat(fotoDocumentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFotoDocumento() throws Exception {
        // Initialize the database
        fotoDocumentoService.save(fotoDocumento);

        int databaseSizeBeforeDelete = fotoDocumentoRepository.findAll().size();

        // Get the fotoDocumento
        restFotoDocumentoMockMvc.perform(delete("/api/foto-documentos/{id}", fotoDocumento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FotoDocumento> fotoDocumentoList = fotoDocumentoRepository.findAll();
        assertThat(fotoDocumentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FotoDocumento.class);
        FotoDocumento fotoDocumento1 = new FotoDocumento();
        fotoDocumento1.setId(1L);
        FotoDocumento fotoDocumento2 = new FotoDocumento();
        fotoDocumento2.setId(fotoDocumento1.getId());
        assertThat(fotoDocumento1).isEqualTo(fotoDocumento2);
        fotoDocumento2.setId(2L);
        assertThat(fotoDocumento1).isNotEqualTo(fotoDocumento2);
        fotoDocumento1.setId(null);
        assertThat(fotoDocumento1).isNotEqualTo(fotoDocumento2);
    }
}
