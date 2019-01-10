package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.PeriodoAtividade;
import br.com.jhisolution.ong.control.service.PeriodoAtividadeService;
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
 * REST controller for managing PeriodoAtividade.
 */
@RestController
@RequestMapping("/api")
public class PeriodoAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(PeriodoAtividadeResource.class);

    private static final String ENTITY_NAME = "periodoAtividade";

    private final PeriodoAtividadeService periodoAtividadeService;

    public PeriodoAtividadeResource(PeriodoAtividadeService periodoAtividadeService) {
        this.periodoAtividadeService = periodoAtividadeService;
    }

    /**
     * POST  /periodo-atividades : Create a new periodoAtividade.
     *
     * @param periodoAtividade the periodoAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new periodoAtividade, or with status 400 (Bad Request) if the periodoAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/periodo-atividades")
    @Timed
    public ResponseEntity<PeriodoAtividade> createPeriodoAtividade(@Valid @RequestBody PeriodoAtividade periodoAtividade) throws URISyntaxException {
        log.debug("REST request to save PeriodoAtividade : {}", periodoAtividade);
        if (periodoAtividade.getId() != null) {
            throw new BadRequestAlertException("A new periodoAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PeriodoAtividade result = periodoAtividadeService.save(periodoAtividade);
        return ResponseEntity.created(new URI("/api/periodo-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /periodo-atividades : Updates an existing periodoAtividade.
     *
     * @param periodoAtividade the periodoAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated periodoAtividade,
     * or with status 400 (Bad Request) if the periodoAtividade is not valid,
     * or with status 500 (Internal Server Error) if the periodoAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/periodo-atividades")
    @Timed
    public ResponseEntity<PeriodoAtividade> updatePeriodoAtividade(@Valid @RequestBody PeriodoAtividade periodoAtividade) throws URISyntaxException {
        log.debug("REST request to update PeriodoAtividade : {}", periodoAtividade);
        if (periodoAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PeriodoAtividade result = periodoAtividadeService.save(periodoAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, periodoAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /periodo-atividades : get all the periodoAtividades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of periodoAtividades in body
     */
    @GetMapping("/periodo-atividades")
    @Timed
    public ResponseEntity<List<PeriodoAtividade>> getAllPeriodoAtividades(Pageable pageable) {
        log.debug("REST request to get a page of PeriodoAtividades");
        Page<PeriodoAtividade> page = periodoAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/periodo-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /periodo-atividades/:id : get the "id" periodoAtividade.
     *
     * @param id the id of the periodoAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the periodoAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/periodo-atividades/{id}")
    @Timed
    public ResponseEntity<PeriodoAtividade> getPeriodoAtividade(@PathVariable Long id) {
        log.debug("REST request to get PeriodoAtividade : {}", id);
        Optional<PeriodoAtividade> periodoAtividade = periodoAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(periodoAtividade);
    }

    /**
     * DELETE  /periodo-atividades/:id : delete the "id" periodoAtividade.
     *
     * @param id the id of the periodoAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/periodo-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deletePeriodoAtividade(@PathVariable Long id) {
        log.debug("REST request to delete PeriodoAtividade : {}", id);
        periodoAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
