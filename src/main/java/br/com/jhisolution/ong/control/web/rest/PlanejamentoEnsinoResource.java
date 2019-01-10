package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.PlanejamentoEnsino;
import br.com.jhisolution.ong.control.service.PlanejamentoEnsinoService;
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
 * REST controller for managing PlanejamentoEnsino.
 */
@RestController
@RequestMapping("/api")
public class PlanejamentoEnsinoResource {

    private final Logger log = LoggerFactory.getLogger(PlanejamentoEnsinoResource.class);

    private static final String ENTITY_NAME = "planejamentoEnsino";

    private final PlanejamentoEnsinoService planejamentoEnsinoService;

    public PlanejamentoEnsinoResource(PlanejamentoEnsinoService planejamentoEnsinoService) {
        this.planejamentoEnsinoService = planejamentoEnsinoService;
    }

    /**
     * POST  /planejamento-ensinos : Create a new planejamentoEnsino.
     *
     * @param planejamentoEnsino the planejamentoEnsino to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planejamentoEnsino, or with status 400 (Bad Request) if the planejamentoEnsino has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/planejamento-ensinos")
    @Timed
    public ResponseEntity<PlanejamentoEnsino> createPlanejamentoEnsino(@Valid @RequestBody PlanejamentoEnsino planejamentoEnsino) throws URISyntaxException {
        log.debug("REST request to save PlanejamentoEnsino : {}", planejamentoEnsino);
        if (planejamentoEnsino.getId() != null) {
            throw new BadRequestAlertException("A new planejamentoEnsino cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanejamentoEnsino result = planejamentoEnsinoService.save(planejamentoEnsino);
        return ResponseEntity.created(new URI("/api/planejamento-ensinos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /planejamento-ensinos : Updates an existing planejamentoEnsino.
     *
     * @param planejamentoEnsino the planejamentoEnsino to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planejamentoEnsino,
     * or with status 400 (Bad Request) if the planejamentoEnsino is not valid,
     * or with status 500 (Internal Server Error) if the planejamentoEnsino couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/planejamento-ensinos")
    @Timed
    public ResponseEntity<PlanejamentoEnsino> updatePlanejamentoEnsino(@Valid @RequestBody PlanejamentoEnsino planejamentoEnsino) throws URISyntaxException {
        log.debug("REST request to update PlanejamentoEnsino : {}", planejamentoEnsino);
        if (planejamentoEnsino.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanejamentoEnsino result = planejamentoEnsinoService.save(planejamentoEnsino);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planejamentoEnsino.getId().toString()))
            .body(result);
    }

    /**
     * GET  /planejamento-ensinos : get all the planejamentoEnsinos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of planejamentoEnsinos in body
     */
    @GetMapping("/planejamento-ensinos")
    @Timed
    public ResponseEntity<List<PlanejamentoEnsino>> getAllPlanejamentoEnsinos(Pageable pageable) {
        log.debug("REST request to get a page of PlanejamentoEnsinos");
        Page<PlanejamentoEnsino> page = planejamentoEnsinoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/planejamento-ensinos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /planejamento-ensinos/:id : get the "id" planejamentoEnsino.
     *
     * @param id the id of the planejamentoEnsino to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planejamentoEnsino, or with status 404 (Not Found)
     */
    @GetMapping("/planejamento-ensinos/{id}")
    @Timed
    public ResponseEntity<PlanejamentoEnsino> getPlanejamentoEnsino(@PathVariable Long id) {
        log.debug("REST request to get PlanejamentoEnsino : {}", id);
        Optional<PlanejamentoEnsino> planejamentoEnsino = planejamentoEnsinoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planejamentoEnsino);
    }

    /**
     * DELETE  /planejamento-ensinos/:id : delete the "id" planejamentoEnsino.
     *
     * @param id the id of the planejamentoEnsino to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/planejamento-ensinos/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanejamentoEnsino(@PathVariable Long id) {
        log.debug("REST request to delete PlanejamentoEnsino : {}", id);
        planejamentoEnsinoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
