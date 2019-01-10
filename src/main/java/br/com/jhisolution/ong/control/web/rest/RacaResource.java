package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Raca;
import br.com.jhisolution.ong.control.service.RacaService;
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
 * REST controller for managing Raca.
 */
@RestController
@RequestMapping("/api")
public class RacaResource {

    private final Logger log = LoggerFactory.getLogger(RacaResource.class);

    private static final String ENTITY_NAME = "raca";

    private final RacaService racaService;

    public RacaResource(RacaService racaService) {
        this.racaService = racaService;
    }

    /**
     * POST  /racas : Create a new raca.
     *
     * @param raca the raca to create
     * @return the ResponseEntity with status 201 (Created) and with body the new raca, or with status 400 (Bad Request) if the raca has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/racas")
    @Timed
    public ResponseEntity<Raca> createRaca(@Valid @RequestBody Raca raca) throws URISyntaxException {
        log.debug("REST request to save Raca : {}", raca);
        if (raca.getId() != null) {
            throw new BadRequestAlertException("A new raca cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Raca result = racaService.save(raca);
        return ResponseEntity.created(new URI("/api/racas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /racas : Updates an existing raca.
     *
     * @param raca the raca to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated raca,
     * or with status 400 (Bad Request) if the raca is not valid,
     * or with status 500 (Internal Server Error) if the raca couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/racas")
    @Timed
    public ResponseEntity<Raca> updateRaca(@Valid @RequestBody Raca raca) throws URISyntaxException {
        log.debug("REST request to update Raca : {}", raca);
        if (raca.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Raca result = racaService.save(raca);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, raca.getId().toString()))
            .body(result);
    }

    /**
     * GET  /racas : get all the racas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of racas in body
     */
    @GetMapping("/racas")
    @Timed
    public ResponseEntity<List<Raca>> getAllRacas(Pageable pageable) {
        log.debug("REST request to get a page of Racas");
        Page<Raca> page = racaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/racas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /racas/:id : get the "id" raca.
     *
     * @param id the id of the raca to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the raca, or with status 404 (Not Found)
     */
    @GetMapping("/racas/{id}")
    @Timed
    public ResponseEntity<Raca> getRaca(@PathVariable Long id) {
        log.debug("REST request to get Raca : {}", id);
        Optional<Raca> raca = racaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(raca);
    }

    /**
     * DELETE  /racas/:id : delete the "id" raca.
     *
     * @param id the id of the raca to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/racas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRaca(@PathVariable Long id) {
        log.debug("REST request to delete Raca : {}", id);
        racaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
