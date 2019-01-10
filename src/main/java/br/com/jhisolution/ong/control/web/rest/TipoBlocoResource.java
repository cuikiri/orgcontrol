package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoBloco;
import br.com.jhisolution.ong.control.service.TipoBlocoService;
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
 * REST controller for managing TipoBloco.
 */
@RestController
@RequestMapping("/api")
public class TipoBlocoResource {

    private final Logger log = LoggerFactory.getLogger(TipoBlocoResource.class);

    private static final String ENTITY_NAME = "tipoBloco";

    private final TipoBlocoService tipoBlocoService;

    public TipoBlocoResource(TipoBlocoService tipoBlocoService) {
        this.tipoBlocoService = tipoBlocoService;
    }

    /**
     * POST  /tipo-blocos : Create a new tipoBloco.
     *
     * @param tipoBloco the tipoBloco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoBloco, or with status 400 (Bad Request) if the tipoBloco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-blocos")
    @Timed
    public ResponseEntity<TipoBloco> createTipoBloco(@Valid @RequestBody TipoBloco tipoBloco) throws URISyntaxException {
        log.debug("REST request to save TipoBloco : {}", tipoBloco);
        if (tipoBloco.getId() != null) {
            throw new BadRequestAlertException("A new tipoBloco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoBloco result = tipoBlocoService.save(tipoBloco);
        return ResponseEntity.created(new URI("/api/tipo-blocos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-blocos : Updates an existing tipoBloco.
     *
     * @param tipoBloco the tipoBloco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoBloco,
     * or with status 400 (Bad Request) if the tipoBloco is not valid,
     * or with status 500 (Internal Server Error) if the tipoBloco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-blocos")
    @Timed
    public ResponseEntity<TipoBloco> updateTipoBloco(@Valid @RequestBody TipoBloco tipoBloco) throws URISyntaxException {
        log.debug("REST request to update TipoBloco : {}", tipoBloco);
        if (tipoBloco.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoBloco result = tipoBlocoService.save(tipoBloco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoBloco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-blocos : get all the tipoBlocos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoBlocos in body
     */
    @GetMapping("/tipo-blocos")
    @Timed
    public ResponseEntity<List<TipoBloco>> getAllTipoBlocos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("bloco-is-null".equals(filter)) {
            log.debug("REST request to get all TipoBlocos where bloco is null");
            return new ResponseEntity<>(tipoBlocoService.findAllWhereBlocoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoBlocos");
        Page<TipoBloco> page = tipoBlocoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-blocos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-blocos/:id : get the "id" tipoBloco.
     *
     * @param id the id of the tipoBloco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoBloco, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-blocos/{id}")
    @Timed
    public ResponseEntity<TipoBloco> getTipoBloco(@PathVariable Long id) {
        log.debug("REST request to get TipoBloco : {}", id);
        Optional<TipoBloco> tipoBloco = tipoBlocoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoBloco);
    }

    /**
     * DELETE  /tipo-blocos/:id : delete the "id" tipoBloco.
     *
     * @param id the id of the tipoBloco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-blocos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoBloco(@PathVariable Long id) {
        log.debug("REST request to delete TipoBloco : {}", id);
        tipoBlocoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
