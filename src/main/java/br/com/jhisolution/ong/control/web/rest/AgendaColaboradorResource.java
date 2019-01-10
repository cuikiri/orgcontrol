package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.AgendaColaborador;
import br.com.jhisolution.ong.control.service.AgendaColaboradorService;
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
 * REST controller for managing AgendaColaborador.
 */
@RestController
@RequestMapping("/api")
public class AgendaColaboradorResource {

    private final Logger log = LoggerFactory.getLogger(AgendaColaboradorResource.class);

    private static final String ENTITY_NAME = "agendaColaborador";

    private final AgendaColaboradorService agendaColaboradorService;

    public AgendaColaboradorResource(AgendaColaboradorService agendaColaboradorService) {
        this.agendaColaboradorService = agendaColaboradorService;
    }

    /**
     * POST  /agenda-colaboradors : Create a new agendaColaborador.
     *
     * @param agendaColaborador the agendaColaborador to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agendaColaborador, or with status 400 (Bad Request) if the agendaColaborador has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agenda-colaboradors")
    @Timed
    public ResponseEntity<AgendaColaborador> createAgendaColaborador(@Valid @RequestBody AgendaColaborador agendaColaborador) throws URISyntaxException {
        log.debug("REST request to save AgendaColaborador : {}", agendaColaborador);
        if (agendaColaborador.getId() != null) {
            throw new BadRequestAlertException("A new agendaColaborador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgendaColaborador result = agendaColaboradorService.save(agendaColaborador);
        return ResponseEntity.created(new URI("/api/agenda-colaboradors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agenda-colaboradors : Updates an existing agendaColaborador.
     *
     * @param agendaColaborador the agendaColaborador to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agendaColaborador,
     * or with status 400 (Bad Request) if the agendaColaborador is not valid,
     * or with status 500 (Internal Server Error) if the agendaColaborador couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agenda-colaboradors")
    @Timed
    public ResponseEntity<AgendaColaborador> updateAgendaColaborador(@Valid @RequestBody AgendaColaborador agendaColaborador) throws URISyntaxException {
        log.debug("REST request to update AgendaColaborador : {}", agendaColaborador);
        if (agendaColaborador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgendaColaborador result = agendaColaboradorService.save(agendaColaborador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agendaColaborador.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agenda-colaboradors : get all the agendaColaboradors.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of agendaColaboradors in body
     */
    @GetMapping("/agenda-colaboradors")
    @Timed
    public ResponseEntity<List<AgendaColaborador>> getAllAgendaColaboradors(Pageable pageable) {
        log.debug("REST request to get a page of AgendaColaboradors");
        Page<AgendaColaborador> page = agendaColaboradorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/agenda-colaboradors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /agenda-colaboradors/:id : get the "id" agendaColaborador.
     *
     * @param id the id of the agendaColaborador to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agendaColaborador, or with status 404 (Not Found)
     */
    @GetMapping("/agenda-colaboradors/{id}")
    @Timed
    public ResponseEntity<AgendaColaborador> getAgendaColaborador(@PathVariable Long id) {
        log.debug("REST request to get AgendaColaborador : {}", id);
        Optional<AgendaColaborador> agendaColaborador = agendaColaboradorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agendaColaborador);
    }

    /**
     * DELETE  /agenda-colaboradors/:id : delete the "id" agendaColaborador.
     *
     * @param id the id of the agendaColaborador to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agenda-colaboradors/{id}")
    @Timed
    public ResponseEntity<Void> deleteAgendaColaborador(@PathVariable Long id) {
        log.debug("REST request to delete AgendaColaborador : {}", id);
        agendaColaboradorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
