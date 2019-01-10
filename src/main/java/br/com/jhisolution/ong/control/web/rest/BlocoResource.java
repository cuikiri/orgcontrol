package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Bloco;
import br.com.jhisolution.ong.control.service.BlocoService;
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
 * REST controller for managing Bloco.
 */
@RestController
@RequestMapping("/api")
public class BlocoResource {

    private final Logger log = LoggerFactory.getLogger(BlocoResource.class);

    private static final String ENTITY_NAME = "bloco";

    private final BlocoService blocoService;

    public BlocoResource(BlocoService blocoService) {
        this.blocoService = blocoService;
    }

    /**
     * POST  /blocos : Create a new bloco.
     *
     * @param bloco the bloco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bloco, or with status 400 (Bad Request) if the bloco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/blocos")
    @Timed
    public ResponseEntity<Bloco> createBloco(@Valid @RequestBody Bloco bloco) throws URISyntaxException {
        log.debug("REST request to save Bloco : {}", bloco);
        if (bloco.getId() != null) {
            throw new BadRequestAlertException("A new bloco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bloco result = blocoService.save(bloco);
        return ResponseEntity.created(new URI("/api/blocos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /blocos : Updates an existing bloco.
     *
     * @param bloco the bloco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bloco,
     * or with status 400 (Bad Request) if the bloco is not valid,
     * or with status 500 (Internal Server Error) if the bloco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/blocos")
    @Timed
    public ResponseEntity<Bloco> updateBloco(@Valid @RequestBody Bloco bloco) throws URISyntaxException {
        log.debug("REST request to update Bloco : {}", bloco);
        if (bloco.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bloco result = blocoService.save(bloco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bloco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /blocos : get all the blocos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of blocos in body
     */
    @GetMapping("/blocos")
    @Timed
    public ResponseEntity<List<Bloco>> getAllBlocos(Pageable pageable) {
        log.debug("REST request to get a page of Blocos");
        Page<Bloco> page = blocoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/blocos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /blocos/:id : get the "id" bloco.
     *
     * @param id the id of the bloco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bloco, or with status 404 (Not Found)
     */
    @GetMapping("/blocos/{id}")
    @Timed
    public ResponseEntity<Bloco> getBloco(@PathVariable Long id) {
        log.debug("REST request to get Bloco : {}", id);
        Optional<Bloco> bloco = blocoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bloco);
    }

    /**
     * DELETE  /blocos/:id : delete the "id" bloco.
     *
     * @param id the id of the bloco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/blocos/{id}")
    @Timed
    public ResponseEntity<Void> deleteBloco(@PathVariable Long id) {
        log.debug("REST request to delete Bloco : {}", id);
        blocoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
