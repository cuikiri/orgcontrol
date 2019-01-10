package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Ensino;
import br.com.jhisolution.ong.control.service.EnsinoService;
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
 * REST controller for managing Ensino.
 */
@RestController
@RequestMapping("/api")
public class EnsinoResource {

    private final Logger log = LoggerFactory.getLogger(EnsinoResource.class);

    private static final String ENTITY_NAME = "ensino";

    private final EnsinoService ensinoService;

    public EnsinoResource(EnsinoService ensinoService) {
        this.ensinoService = ensinoService;
    }

    /**
     * POST  /ensinos : Create a new ensino.
     *
     * @param ensino the ensino to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ensino, or with status 400 (Bad Request) if the ensino has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ensinos")
    @Timed
    public ResponseEntity<Ensino> createEnsino(@Valid @RequestBody Ensino ensino) throws URISyntaxException {
        log.debug("REST request to save Ensino : {}", ensino);
        if (ensino.getId() != null) {
            throw new BadRequestAlertException("A new ensino cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ensino result = ensinoService.save(ensino);
        return ResponseEntity.created(new URI("/api/ensinos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ensinos : Updates an existing ensino.
     *
     * @param ensino the ensino to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ensino,
     * or with status 400 (Bad Request) if the ensino is not valid,
     * or with status 500 (Internal Server Error) if the ensino couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ensinos")
    @Timed
    public ResponseEntity<Ensino> updateEnsino(@Valid @RequestBody Ensino ensino) throws URISyntaxException {
        log.debug("REST request to update Ensino : {}", ensino);
        if (ensino.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ensino result = ensinoService.save(ensino);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ensino.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ensinos : get all the ensinos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ensinos in body
     */
    @GetMapping("/ensinos")
    @Timed
    public ResponseEntity<List<Ensino>> getAllEnsinos(Pageable pageable) {
        log.debug("REST request to get a page of Ensinos");
        Page<Ensino> page = ensinoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ensinos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ensinos/:id : get the "id" ensino.
     *
     * @param id the id of the ensino to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ensino, or with status 404 (Not Found)
     */
    @GetMapping("/ensinos/{id}")
    @Timed
    public ResponseEntity<Ensino> getEnsino(@PathVariable Long id) {
        log.debug("REST request to get Ensino : {}", id);
        Optional<Ensino> ensino = ensinoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ensino);
    }

    /**
     * DELETE  /ensinos/:id : delete the "id" ensino.
     *
     * @param id the id of the ensino to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ensinos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnsino(@PathVariable Long id) {
        log.debug("REST request to delete Ensino : {}", id);
        ensinoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
