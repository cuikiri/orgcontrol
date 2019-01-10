package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ParteBloco;
import br.com.jhisolution.ong.control.service.ParteBlocoService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing ParteBloco.
 */
@RestController
@RequestMapping("/api")
public class ParteBlocoResource {

    private final Logger log = LoggerFactory.getLogger(ParteBlocoResource.class);

    private static final String ENTITY_NAME = "parteBloco";

    private final ParteBlocoService parteBlocoService;

    public ParteBlocoResource(ParteBlocoService parteBlocoService) {
        this.parteBlocoService = parteBlocoService;
    }

    /**
     * POST  /parte-blocos : Create a new parteBloco.
     *
     * @param parteBloco the parteBloco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new parteBloco, or with status 400 (Bad Request) if the parteBloco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parte-blocos")
    @Timed
    public ResponseEntity<ParteBloco> createParteBloco(@Valid @RequestBody ParteBloco parteBloco) throws URISyntaxException {
        log.debug("REST request to save ParteBloco : {}", parteBloco);
        if (parteBloco.getId() != null) {
            throw new BadRequestAlertException("A new parteBloco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParteBloco result = parteBlocoService.save(parteBloco);
        return ResponseEntity.created(new URI("/api/parte-blocos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parte-blocos : Updates an existing parteBloco.
     *
     * @param parteBloco the parteBloco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated parteBloco,
     * or with status 400 (Bad Request) if the parteBloco is not valid,
     * or with status 500 (Internal Server Error) if the parteBloco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parte-blocos")
    @Timed
    public ResponseEntity<ParteBloco> updateParteBloco(@Valid @RequestBody ParteBloco parteBloco) throws URISyntaxException {
        log.debug("REST request to update ParteBloco : {}", parteBloco);
        if (parteBloco.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParteBloco result = parteBlocoService.save(parteBloco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, parteBloco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parte-blocos : get all the parteBlocos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of parteBlocos in body
     */
    @GetMapping("/parte-blocos")
    @Timed
    public ResponseEntity<List<ParteBloco>> getAllParteBlocos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("periodoatividade-is-null".equals(filter)) {
            log.debug("REST request to get all ParteBlocos where periodoAtividade is null");
            return new ResponseEntity<>(parteBlocoService.findAllWherePeriodoAtividadeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ParteBlocos");
        Page<ParteBloco> page = parteBlocoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/parte-blocos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /parte-blocos/:id : get the "id" parteBloco.
     *
     * @param id the id of the parteBloco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the parteBloco, or with status 404 (Not Found)
     */
    @GetMapping("/parte-blocos/{id}")
    @Timed
    public ResponseEntity<ParteBloco> getParteBloco(@PathVariable Long id) {
        log.debug("REST request to get ParteBloco : {}", id);
        Optional<ParteBloco> parteBloco = parteBlocoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parteBloco);
    }

    /**
     * DELETE  /parte-blocos/:id : delete the "id" parteBloco.
     *
     * @param id the id of the parteBloco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parte-blocos/{id}")
    @Timed
    public ResponseEntity<Void> deleteParteBloco(@PathVariable Long id) {
        log.debug("REST request to delete ParteBloco : {}", id);
        parteBlocoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
