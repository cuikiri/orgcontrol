package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoConceito;
import br.com.jhisolution.ong.control.service.TipoConceitoService;
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
 * REST controller for managing TipoConceito.
 */
@RestController
@RequestMapping("/api")
public class TipoConceitoResource {

    private final Logger log = LoggerFactory.getLogger(TipoConceitoResource.class);

    private static final String ENTITY_NAME = "tipoConceito";

    private final TipoConceitoService tipoConceitoService;

    public TipoConceitoResource(TipoConceitoService tipoConceitoService) {
        this.tipoConceitoService = tipoConceitoService;
    }

    /**
     * POST  /tipo-conceitos : Create a new tipoConceito.
     *
     * @param tipoConceito the tipoConceito to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoConceito, or with status 400 (Bad Request) if the tipoConceito has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-conceitos")
    @Timed
    public ResponseEntity<TipoConceito> createTipoConceito(@Valid @RequestBody TipoConceito tipoConceito) throws URISyntaxException {
        log.debug("REST request to save TipoConceito : {}", tipoConceito);
        if (tipoConceito.getId() != null) {
            throw new BadRequestAlertException("A new tipoConceito cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoConceito result = tipoConceitoService.save(tipoConceito);
        return ResponseEntity.created(new URI("/api/tipo-conceitos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-conceitos : Updates an existing tipoConceito.
     *
     * @param tipoConceito the tipoConceito to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoConceito,
     * or with status 400 (Bad Request) if the tipoConceito is not valid,
     * or with status 500 (Internal Server Error) if the tipoConceito couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-conceitos")
    @Timed
    public ResponseEntity<TipoConceito> updateTipoConceito(@Valid @RequestBody TipoConceito tipoConceito) throws URISyntaxException {
        log.debug("REST request to update TipoConceito : {}", tipoConceito);
        if (tipoConceito.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoConceito result = tipoConceitoService.save(tipoConceito);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoConceito.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-conceitos : get all the tipoConceitos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoConceitos in body
     */
    @GetMapping("/tipo-conceitos")
    @Timed
    public ResponseEntity<List<TipoConceito>> getAllTipoConceitos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("conceito-is-null".equals(filter)) {
            log.debug("REST request to get all TipoConceitos where conceito is null");
            return new ResponseEntity<>(tipoConceitoService.findAllWhereConceitoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoConceitos");
        Page<TipoConceito> page = tipoConceitoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-conceitos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-conceitos/:id : get the "id" tipoConceito.
     *
     * @param id the id of the tipoConceito to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoConceito, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-conceitos/{id}")
    @Timed
    public ResponseEntity<TipoConceito> getTipoConceito(@PathVariable Long id) {
        log.debug("REST request to get TipoConceito : {}", id);
        Optional<TipoConceito> tipoConceito = tipoConceitoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoConceito);
    }

    /**
     * DELETE  /tipo-conceitos/:id : delete the "id" tipoConceito.
     *
     * @param id the id of the tipoConceito to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-conceitos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoConceito(@PathVariable Long id) {
        log.debug("REST request to delete TipoConceito : {}", id);
        tipoConceitoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
