package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoUnidade;
import br.com.jhisolution.ong.control.service.TipoUnidadeService;
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
 * REST controller for managing TipoUnidade.
 */
@RestController
@RequestMapping("/api")
public class TipoUnidadeResource {

    private final Logger log = LoggerFactory.getLogger(TipoUnidadeResource.class);

    private static final String ENTITY_NAME = "tipoUnidade";

    private final TipoUnidadeService tipoUnidadeService;

    public TipoUnidadeResource(TipoUnidadeService tipoUnidadeService) {
        this.tipoUnidadeService = tipoUnidadeService;
    }

    /**
     * POST  /tipo-unidades : Create a new tipoUnidade.
     *
     * @param tipoUnidade the tipoUnidade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoUnidade, or with status 400 (Bad Request) if the tipoUnidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-unidades")
    @Timed
    public ResponseEntity<TipoUnidade> createTipoUnidade(@Valid @RequestBody TipoUnidade tipoUnidade) throws URISyntaxException {
        log.debug("REST request to save TipoUnidade : {}", tipoUnidade);
        if (tipoUnidade.getId() != null) {
            throw new BadRequestAlertException("A new tipoUnidade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoUnidade result = tipoUnidadeService.save(tipoUnidade);
        return ResponseEntity.created(new URI("/api/tipo-unidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-unidades : Updates an existing tipoUnidade.
     *
     * @param tipoUnidade the tipoUnidade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoUnidade,
     * or with status 400 (Bad Request) if the tipoUnidade is not valid,
     * or with status 500 (Internal Server Error) if the tipoUnidade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-unidades")
    @Timed
    public ResponseEntity<TipoUnidade> updateTipoUnidade(@Valid @RequestBody TipoUnidade tipoUnidade) throws URISyntaxException {
        log.debug("REST request to update TipoUnidade : {}", tipoUnidade);
        if (tipoUnidade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoUnidade result = tipoUnidadeService.save(tipoUnidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoUnidade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-unidades : get all the tipoUnidades.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoUnidades in body
     */
    @GetMapping("/tipo-unidades")
    @Timed
    public ResponseEntity<List<TipoUnidade>> getAllTipoUnidades(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("unidade-is-null".equals(filter)) {
            log.debug("REST request to get all TipoUnidades where unidade is null");
            return new ResponseEntity<>(tipoUnidadeService.findAllWhereUnidadeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoUnidades");
        Page<TipoUnidade> page = tipoUnidadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-unidades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-unidades/:id : get the "id" tipoUnidade.
     *
     * @param id the id of the tipoUnidade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoUnidade, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-unidades/{id}")
    @Timed
    public ResponseEntity<TipoUnidade> getTipoUnidade(@PathVariable Long id) {
        log.debug("REST request to get TipoUnidade : {}", id);
        Optional<TipoUnidade> tipoUnidade = tipoUnidadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoUnidade);
    }

    /**
     * DELETE  /tipo-unidades/:id : delete the "id" tipoUnidade.
     *
     * @param id the id of the tipoUnidade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-unidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoUnidade(@PathVariable Long id) {
        log.debug("REST request to delete TipoUnidade : {}", id);
        tipoUnidadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
