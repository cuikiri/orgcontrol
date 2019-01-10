package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoParteBloco;
import br.com.jhisolution.ong.control.service.TipoParteBlocoService;
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
 * REST controller for managing TipoParteBloco.
 */
@RestController
@RequestMapping("/api")
public class TipoParteBlocoResource {

    private final Logger log = LoggerFactory.getLogger(TipoParteBlocoResource.class);

    private static final String ENTITY_NAME = "tipoParteBloco";

    private final TipoParteBlocoService tipoParteBlocoService;

    public TipoParteBlocoResource(TipoParteBlocoService tipoParteBlocoService) {
        this.tipoParteBlocoService = tipoParteBlocoService;
    }

    /**
     * POST  /tipo-parte-blocos : Create a new tipoParteBloco.
     *
     * @param tipoParteBloco the tipoParteBloco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoParteBloco, or with status 400 (Bad Request) if the tipoParteBloco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-parte-blocos")
    @Timed
    public ResponseEntity<TipoParteBloco> createTipoParteBloco(@Valid @RequestBody TipoParteBloco tipoParteBloco) throws URISyntaxException {
        log.debug("REST request to save TipoParteBloco : {}", tipoParteBloco);
        if (tipoParteBloco.getId() != null) {
            throw new BadRequestAlertException("A new tipoParteBloco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoParteBloco result = tipoParteBlocoService.save(tipoParteBloco);
        return ResponseEntity.created(new URI("/api/tipo-parte-blocos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-parte-blocos : Updates an existing tipoParteBloco.
     *
     * @param tipoParteBloco the tipoParteBloco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoParteBloco,
     * or with status 400 (Bad Request) if the tipoParteBloco is not valid,
     * or with status 500 (Internal Server Error) if the tipoParteBloco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-parte-blocos")
    @Timed
    public ResponseEntity<TipoParteBloco> updateTipoParteBloco(@Valid @RequestBody TipoParteBloco tipoParteBloco) throws URISyntaxException {
        log.debug("REST request to update TipoParteBloco : {}", tipoParteBloco);
        if (tipoParteBloco.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoParteBloco result = tipoParteBlocoService.save(tipoParteBloco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoParteBloco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-parte-blocos : get all the tipoParteBlocos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoParteBlocos in body
     */
    @GetMapping("/tipo-parte-blocos")
    @Timed
    public ResponseEntity<List<TipoParteBloco>> getAllTipoParteBlocos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("partebloco-is-null".equals(filter)) {
            log.debug("REST request to get all TipoParteBlocos where parteBloco is null");
            return new ResponseEntity<>(tipoParteBlocoService.findAllWhereParteBlocoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoParteBlocos");
        Page<TipoParteBloco> page = tipoParteBlocoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-parte-blocos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-parte-blocos/:id : get the "id" tipoParteBloco.
     *
     * @param id the id of the tipoParteBloco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoParteBloco, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-parte-blocos/{id}")
    @Timed
    public ResponseEntity<TipoParteBloco> getTipoParteBloco(@PathVariable Long id) {
        log.debug("REST request to get TipoParteBloco : {}", id);
        Optional<TipoParteBloco> tipoParteBloco = tipoParteBlocoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoParteBloco);
    }

    /**
     * DELETE  /tipo-parte-blocos/:id : delete the "id" tipoParteBloco.
     *
     * @param id the id of the tipoParteBloco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-parte-blocos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoParteBloco(@PathVariable Long id) {
        log.debug("REST request to delete TipoParteBloco : {}", id);
        tipoParteBlocoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
