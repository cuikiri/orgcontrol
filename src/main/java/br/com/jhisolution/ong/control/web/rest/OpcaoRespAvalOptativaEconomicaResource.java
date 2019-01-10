package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativaEconomica;
import br.com.jhisolution.ong.control.service.OpcaoRespAvalOptativaEconomicaService;
import br.com.jhisolution.ong.control.web.rest.errors.BadRequestAlertException;
import br.com.jhisolution.ong.control.web.rest.util.HeaderUtil;
import br.com.jhisolution.ong.control.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OpcaoRespAvalOptativaEconomica.
 */
@RestController
@RequestMapping("/api")
public class OpcaoRespAvalOptativaEconomicaResource {

    private final Logger log = LoggerFactory.getLogger(OpcaoRespAvalOptativaEconomicaResource.class);

    private static final String ENTITY_NAME = "opcaoRespAvalOptativaEconomica";

    private final OpcaoRespAvalOptativaEconomicaService opcaoRespAvalOptativaEconomicaService;

    public OpcaoRespAvalOptativaEconomicaResource(OpcaoRespAvalOptativaEconomicaService opcaoRespAvalOptativaEconomicaService) {
        this.opcaoRespAvalOptativaEconomicaService = opcaoRespAvalOptativaEconomicaService;
    }

    /**
     * POST  /opcao-resp-aval-optativa-economicas : Create a new opcaoRespAvalOptativaEconomica.
     *
     * @param opcaoRespAvalOptativaEconomica the opcaoRespAvalOptativaEconomica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new opcaoRespAvalOptativaEconomica, or with status 400 (Bad Request) if the opcaoRespAvalOptativaEconomica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/opcao-resp-aval-optativa-economicas")
    @Timed
    public ResponseEntity<OpcaoRespAvalOptativaEconomica> createOpcaoRespAvalOptativaEconomica(@Valid @RequestBody OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica) throws URISyntaxException {
        log.debug("REST request to save OpcaoRespAvalOptativaEconomica : {}", opcaoRespAvalOptativaEconomica);
        if (opcaoRespAvalOptativaEconomica.getId() != null) {
            throw new BadRequestAlertException("A new opcaoRespAvalOptativaEconomica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpcaoRespAvalOptativaEconomica result = opcaoRespAvalOptativaEconomicaService.save(opcaoRespAvalOptativaEconomica);
        return ResponseEntity.created(new URI("/api/opcao-resp-aval-optativa-economicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /opcao-resp-aval-optativa-economicas : Updates an existing opcaoRespAvalOptativaEconomica.
     *
     * @param opcaoRespAvalOptativaEconomica the opcaoRespAvalOptativaEconomica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated opcaoRespAvalOptativaEconomica,
     * or with status 400 (Bad Request) if the opcaoRespAvalOptativaEconomica is not valid,
     * or with status 500 (Internal Server Error) if the opcaoRespAvalOptativaEconomica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/opcao-resp-aval-optativa-economicas")
    @Timed
    public ResponseEntity<OpcaoRespAvalOptativaEconomica> updateOpcaoRespAvalOptativaEconomica(@Valid @RequestBody OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica) throws URISyntaxException {
        log.debug("REST request to update OpcaoRespAvalOptativaEconomica : {}", opcaoRespAvalOptativaEconomica);
        if (opcaoRespAvalOptativaEconomica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OpcaoRespAvalOptativaEconomica result = opcaoRespAvalOptativaEconomicaService.save(opcaoRespAvalOptativaEconomica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, opcaoRespAvalOptativaEconomica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /opcao-resp-aval-optativa-economicas : get all the opcaoRespAvalOptativaEconomicas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of opcaoRespAvalOptativaEconomicas in body
     */
    @GetMapping("/opcao-resp-aval-optativa-economicas")
    @Timed
    public ResponseEntity<List<OpcaoRespAvalOptativaEconomica>> getAllOpcaoRespAvalOptativaEconomicas(Pageable pageable) {
        log.debug("REST request to get a page of OpcaoRespAvalOptativaEconomicas");
        Page<OpcaoRespAvalOptativaEconomica> page = opcaoRespAvalOptativaEconomicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/opcao-resp-aval-optativa-economicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /opcao-resp-aval-optativa-economicas/:id : get the "id" opcaoRespAvalOptativaEconomica.
     *
     * @param id the id of the opcaoRespAvalOptativaEconomica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the opcaoRespAvalOptativaEconomica, or with status 404 (Not Found)
     */
    @GetMapping("/opcao-resp-aval-optativa-economicas/{id}")
    @Timed
    public ResponseEntity<OpcaoRespAvalOptativaEconomica> getOpcaoRespAvalOptativaEconomica(@PathVariable Long id) {
        log.debug("REST request to get OpcaoRespAvalOptativaEconomica : {}", id);
        Optional<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomica = opcaoRespAvalOptativaEconomicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opcaoRespAvalOptativaEconomica);
    }

    /**
     * DELETE  /opcao-resp-aval-optativa-economicas/:id : delete the "id" opcaoRespAvalOptativaEconomica.
     *
     * @param id the id of the opcaoRespAvalOptativaEconomica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/opcao-resp-aval-optativa-economicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteOpcaoRespAvalOptativaEconomica(@PathVariable Long id) {
        log.debug("REST request to delete OpcaoRespAvalOptativaEconomica : {}", id);
        opcaoRespAvalOptativaEconomicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
