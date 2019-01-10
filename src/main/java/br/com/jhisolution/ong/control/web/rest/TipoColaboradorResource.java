package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoColaborador;
import br.com.jhisolution.ong.control.service.TipoColaboradorService;
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
 * REST controller for managing TipoColaborador.
 */
@RestController
@RequestMapping("/api")
public class TipoColaboradorResource {

    private final Logger log = LoggerFactory.getLogger(TipoColaboradorResource.class);

    private static final String ENTITY_NAME = "tipoColaborador";

    private final TipoColaboradorService tipoColaboradorService;

    public TipoColaboradorResource(TipoColaboradorService tipoColaboradorService) {
        this.tipoColaboradorService = tipoColaboradorService;
    }

    /**
     * POST  /tipo-colaboradors : Create a new tipoColaborador.
     *
     * @param tipoColaborador the tipoColaborador to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoColaborador, or with status 400 (Bad Request) if the tipoColaborador has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-colaboradors")
    @Timed
    public ResponseEntity<TipoColaborador> createTipoColaborador(@Valid @RequestBody TipoColaborador tipoColaborador) throws URISyntaxException {
        log.debug("REST request to save TipoColaborador : {}", tipoColaborador);
        if (tipoColaborador.getId() != null) {
            throw new BadRequestAlertException("A new tipoColaborador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoColaborador result = tipoColaboradorService.save(tipoColaborador);
        return ResponseEntity.created(new URI("/api/tipo-colaboradors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-colaboradors : Updates an existing tipoColaborador.
     *
     * @param tipoColaborador the tipoColaborador to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoColaborador,
     * or with status 400 (Bad Request) if the tipoColaborador is not valid,
     * or with status 500 (Internal Server Error) if the tipoColaborador couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-colaboradors")
    @Timed
    public ResponseEntity<TipoColaborador> updateTipoColaborador(@Valid @RequestBody TipoColaborador tipoColaborador) throws URISyntaxException {
        log.debug("REST request to update TipoColaborador : {}", tipoColaborador);
        if (tipoColaborador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoColaborador result = tipoColaboradorService.save(tipoColaborador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoColaborador.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-colaboradors : get all the tipoColaboradors.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoColaboradors in body
     */
    @GetMapping("/tipo-colaboradors")
    @Timed
    public ResponseEntity<List<TipoColaborador>> getAllTipoColaboradors(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("colaborador-is-null".equals(filter)) {
            log.debug("REST request to get all TipoColaboradors where colaborador is null");
            return new ResponseEntity<>(tipoColaboradorService.findAllWhereColaboradorIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoColaboradors");
        Page<TipoColaborador> page = tipoColaboradorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-colaboradors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-colaboradors/:id : get the "id" tipoColaborador.
     *
     * @param id the id of the tipoColaborador to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoColaborador, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-colaboradors/{id}")
    @Timed
    public ResponseEntity<TipoColaborador> getTipoColaborador(@PathVariable Long id) {
        log.debug("REST request to get TipoColaborador : {}", id);
        Optional<TipoColaborador> tipoColaborador = tipoColaboradorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoColaborador);
    }

    /**
     * DELETE  /tipo-colaboradors/:id : delete the "id" tipoColaborador.
     *
     * @param id the id of the tipoColaborador to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-colaboradors/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoColaborador(@PathVariable Long id) {
        log.debug("REST request to delete TipoColaborador : {}", id);
        tipoColaboradorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
