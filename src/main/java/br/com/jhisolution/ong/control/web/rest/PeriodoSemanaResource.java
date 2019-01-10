package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.PeriodoSemana;
import br.com.jhisolution.ong.control.service.PeriodoSemanaService;
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
 * REST controller for managing PeriodoSemana.
 */
@RestController
@RequestMapping("/api")
public class PeriodoSemanaResource {

    private final Logger log = LoggerFactory.getLogger(PeriodoSemanaResource.class);

    private static final String ENTITY_NAME = "periodoSemana";

    private final PeriodoSemanaService periodoSemanaService;

    public PeriodoSemanaResource(PeriodoSemanaService periodoSemanaService) {
        this.periodoSemanaService = periodoSemanaService;
    }

    /**
     * POST  /periodo-semanas : Create a new periodoSemana.
     *
     * @param periodoSemana the periodoSemana to create
     * @return the ResponseEntity with status 201 (Created) and with body the new periodoSemana, or with status 400 (Bad Request) if the periodoSemana has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/periodo-semanas")
    @Timed
    public ResponseEntity<PeriodoSemana> createPeriodoSemana(@Valid @RequestBody PeriodoSemana periodoSemana) throws URISyntaxException {
        log.debug("REST request to save PeriodoSemana : {}", periodoSemana);
        if (periodoSemana.getId() != null) {
            throw new BadRequestAlertException("A new periodoSemana cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PeriodoSemana result = periodoSemanaService.save(periodoSemana);
        return ResponseEntity.created(new URI("/api/periodo-semanas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /periodo-semanas : Updates an existing periodoSemana.
     *
     * @param periodoSemana the periodoSemana to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated periodoSemana,
     * or with status 400 (Bad Request) if the periodoSemana is not valid,
     * or with status 500 (Internal Server Error) if the periodoSemana couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/periodo-semanas")
    @Timed
    public ResponseEntity<PeriodoSemana> updatePeriodoSemana(@Valid @RequestBody PeriodoSemana periodoSemana) throws URISyntaxException {
        log.debug("REST request to update PeriodoSemana : {}", periodoSemana);
        if (periodoSemana.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PeriodoSemana result = periodoSemanaService.save(periodoSemana);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, periodoSemana.getId().toString()))
            .body(result);
    }

    /**
     * GET  /periodo-semanas : get all the periodoSemanas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of periodoSemanas in body
     */
    @GetMapping("/periodo-semanas")
    @Timed
    public ResponseEntity<List<PeriodoSemana>> getAllPeriodoSemanas(Pageable pageable) {
        log.debug("REST request to get a page of PeriodoSemanas");
        Page<PeriodoSemana> page = periodoSemanaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/periodo-semanas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /periodo-semanas/:id : get the "id" periodoSemana.
     *
     * @param id the id of the periodoSemana to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the periodoSemana, or with status 404 (Not Found)
     */
    @GetMapping("/periodo-semanas/{id}")
    @Timed
    public ResponseEntity<PeriodoSemana> getPeriodoSemana(@PathVariable Long id) {
        log.debug("REST request to get PeriodoSemana : {}", id);
        Optional<PeriodoSemana> periodoSemana = periodoSemanaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(periodoSemana);
    }

    /**
     * DELETE  /periodo-semanas/:id : delete the "id" periodoSemana.
     *
     * @param id the id of the periodoSemana to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/periodo-semanas/{id}")
    @Timed
    public ResponseEntity<Void> deletePeriodoSemana(@PathVariable Long id) {
        log.debug("REST request to delete PeriodoSemana : {}", id);
        periodoSemanaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
