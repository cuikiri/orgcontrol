package br.com.jhisolution.ong.control.web.rest;

import br.com.jhisolution.ong.control.OrgcontrolApp;

import br.com.jhisolution.ong.control.domain.AgendaColaborador;
import br.com.jhisolution.ong.control.repository.AgendaColaboradorRepository;
import br.com.jhisolution.ong.control.service.AgendaColaboradorService;
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
 * Test class for the AgendaColaboradorResource REST controller.
 *
 * @see AgendaColaboradorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgcontrolApp.class)
public class AgendaColaboradorResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_HORA_INICIO = "AAAAA";
    private static final String UPDATED_HORA_INICIO = "BBBBB";

    private static final String DEFAULT_HORA_FIM = "AAAAA";
    private static final String UPDATED_HORA_FIM = "BBBBB";

    private static final String DEFAULT_HORA_ALMOCO_INICIO = "AAAAA";
    private static final String UPDATED_HORA_ALMOCO_INICIO = "BBBBB";

    private static final String DEFAULT_HORA_ALMOCO_FIM = "AAAAA";
    private static final String UPDATED_HORA_ALMOCO_FIM = "BBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private AgendaColaboradorRepository agendaColaboradorRepository;

    @Autowired
    private AgendaColaboradorService agendaColaboradorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAgendaColaboradorMockMvc;

    private AgendaColaborador agendaColaborador;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AgendaColaboradorResource agendaColaboradorResource = new AgendaColaboradorResource(agendaColaboradorService);
        this.restAgendaColaboradorMockMvc = MockMvcBuilders.standaloneSetup(agendaColaboradorResource)
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
    public static AgendaColaborador createEntity(EntityManager em) {
        AgendaColaborador agendaColaborador = new AgendaColaborador()
            .nome(DEFAULT_NOME)
            .horaInicio(DEFAULT_HORA_INICIO)
            .horaFim(DEFAULT_HORA_FIM)
            .horaAlmocoInicio(DEFAULT_HORA_ALMOCO_INICIO)
            .horaAlmocoFim(DEFAULT_HORA_ALMOCO_FIM)
            .obs(DEFAULT_OBS);
        return agendaColaborador;
    }

    @Before
    public void initTest() {
        agendaColaborador = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgendaColaborador() throws Exception {
        int databaseSizeBeforeCreate = agendaColaboradorRepository.findAll().size();

        // Create the AgendaColaborador
        restAgendaColaboradorMockMvc.perform(post("/api/agenda-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agendaColaborador)))
            .andExpect(status().isCreated());

        // Validate the AgendaColaborador in the database
        List<AgendaColaborador> agendaColaboradorList = agendaColaboradorRepository.findAll();
        assertThat(agendaColaboradorList).hasSize(databaseSizeBeforeCreate + 1);
        AgendaColaborador testAgendaColaborador = agendaColaboradorList.get(agendaColaboradorList.size() - 1);
        assertThat(testAgendaColaborador.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAgendaColaborador.getHoraInicio()).isEqualTo(DEFAULT_HORA_INICIO);
        assertThat(testAgendaColaborador.getHoraFim()).isEqualTo(DEFAULT_HORA_FIM);
        assertThat(testAgendaColaborador.getHoraAlmocoInicio()).isEqualTo(DEFAULT_HORA_ALMOCO_INICIO);
        assertThat(testAgendaColaborador.getHoraAlmocoFim()).isEqualTo(DEFAULT_HORA_ALMOCO_FIM);
        assertThat(testAgendaColaborador.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createAgendaColaboradorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agendaColaboradorRepository.findAll().size();

        // Create the AgendaColaborador with an existing ID
        agendaColaborador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgendaColaboradorMockMvc.perform(post("/api/agenda-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agendaColaborador)))
            .andExpect(status().isBadRequest());

        // Validate the AgendaColaborador in the database
        List<AgendaColaborador> agendaColaboradorList = agendaColaboradorRepository.findAll();
        assertThat(agendaColaboradorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAgendaColaboradors() throws Exception {
        // Initialize the database
        agendaColaboradorRepository.saveAndFlush(agendaColaborador);

        // Get all the agendaColaboradorList
        restAgendaColaboradorMockMvc.perform(get("/api/agenda-colaboradors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agendaColaborador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].horaInicio").value(hasItem(DEFAULT_HORA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].horaFim").value(hasItem(DEFAULT_HORA_FIM.toString())))
            .andExpect(jsonPath("$.[*].horaAlmocoInicio").value(hasItem(DEFAULT_HORA_ALMOCO_INICIO.toString())))
            .andExpect(jsonPath("$.[*].horaAlmocoFim").value(hasItem(DEFAULT_HORA_ALMOCO_FIM.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }
    
    @Test
    @Transactional
    public void getAgendaColaborador() throws Exception {
        // Initialize the database
        agendaColaboradorRepository.saveAndFlush(agendaColaborador);

        // Get the agendaColaborador
        restAgendaColaboradorMockMvc.perform(get("/api/agenda-colaboradors/{id}", agendaColaborador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agendaColaborador.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.horaInicio").value(DEFAULT_HORA_INICIO.toString()))
            .andExpect(jsonPath("$.horaFim").value(DEFAULT_HORA_FIM.toString()))
            .andExpect(jsonPath("$.horaAlmocoInicio").value(DEFAULT_HORA_ALMOCO_INICIO.toString()))
            .andExpect(jsonPath("$.horaAlmocoFim").value(DEFAULT_HORA_ALMOCO_FIM.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAgendaColaborador() throws Exception {
        // Get the agendaColaborador
        restAgendaColaboradorMockMvc.perform(get("/api/agenda-colaboradors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgendaColaborador() throws Exception {
        // Initialize the database
        agendaColaboradorService.save(agendaColaborador);

        int databaseSizeBeforeUpdate = agendaColaboradorRepository.findAll().size();

        // Update the agendaColaborador
        AgendaColaborador updatedAgendaColaborador = agendaColaboradorRepository.findById(agendaColaborador.getId()).get();
        // Disconnect from session so that the updates on updatedAgendaColaborador are not directly saved in db
        em.detach(updatedAgendaColaborador);
        updatedAgendaColaborador
            .nome(UPDATED_NOME)
            .horaInicio(UPDATED_HORA_INICIO)
            .horaFim(UPDATED_HORA_FIM)
            .horaAlmocoInicio(UPDATED_HORA_ALMOCO_INICIO)
            .horaAlmocoFim(UPDATED_HORA_ALMOCO_FIM)
            .obs(UPDATED_OBS);

        restAgendaColaboradorMockMvc.perform(put("/api/agenda-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAgendaColaborador)))
            .andExpect(status().isOk());

        // Validate the AgendaColaborador in the database
        List<AgendaColaborador> agendaColaboradorList = agendaColaboradorRepository.findAll();
        assertThat(agendaColaboradorList).hasSize(databaseSizeBeforeUpdate);
        AgendaColaborador testAgendaColaborador = agendaColaboradorList.get(agendaColaboradorList.size() - 1);
        assertThat(testAgendaColaborador.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAgendaColaborador.getHoraInicio()).isEqualTo(UPDATED_HORA_INICIO);
        assertThat(testAgendaColaborador.getHoraFim()).isEqualTo(UPDATED_HORA_FIM);
        assertThat(testAgendaColaborador.getHoraAlmocoInicio()).isEqualTo(UPDATED_HORA_ALMOCO_INICIO);
        assertThat(testAgendaColaborador.getHoraAlmocoFim()).isEqualTo(UPDATED_HORA_ALMOCO_FIM);
        assertThat(testAgendaColaborador.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingAgendaColaborador() throws Exception {
        int databaseSizeBeforeUpdate = agendaColaboradorRepository.findAll().size();

        // Create the AgendaColaborador

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgendaColaboradorMockMvc.perform(put("/api/agenda-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agendaColaborador)))
            .andExpect(status().isBadRequest());

        // Validate the AgendaColaborador in the database
        List<AgendaColaborador> agendaColaboradorList = agendaColaboradorRepository.findAll();
        assertThat(agendaColaboradorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAgendaColaborador() throws Exception {
        // Initialize the database
        agendaColaboradorService.save(agendaColaborador);

        int databaseSizeBeforeDelete = agendaColaboradorRepository.findAll().size();

        // Get the agendaColaborador
        restAgendaColaboradorMockMvc.perform(delete("/api/agenda-colaboradors/{id}", agendaColaborador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AgendaColaborador> agendaColaboradorList = agendaColaboradorRepository.findAll();
        assertThat(agendaColaboradorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgendaColaborador.class);
        AgendaColaborador agendaColaborador1 = new AgendaColaborador();
        agendaColaborador1.setId(1L);
        AgendaColaborador agendaColaborador2 = new AgendaColaborador();
        agendaColaborador2.setId(agendaColaborador1.getId());
        assertThat(agendaColaborador1).isEqualTo(agendaColaborador2);
        agendaColaborador2.setId(2L);
        assertThat(agendaColaborador1).isNotEqualTo(agendaColaborador2);
        agendaColaborador1.setId(null);
        assertThat(agendaColaborador1).isNotEqualTo(agendaColaborador2);
    }
}
