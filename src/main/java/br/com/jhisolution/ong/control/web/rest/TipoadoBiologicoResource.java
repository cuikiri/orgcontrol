package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoadoBiologico;
import br.com.jhisolution.ong.control.service.TipoadoBiologicoService;
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
 * REST controller for managing TipoadoBiologico.
 */
@RestController
@RequestMapping("/api")
public class TipoadoBiologicoResource {

    private final Logger log = LoggerFactory.getLogger(TipoadoBiologicoResource.class);

    private static final String ENTITY_NAME = "tipoadoBiologico";

    private final TipoadoBiologicoService tipoadoBiologicoService;

    public TipoadoBiologicoResource(TipoadoBiologicoService tipoadoBiologicoService) {
        this.tipoadoBiologicoService = tipoadoBiologicoService;
    }

    /**
     * POST  /tipoado-biologicos : Create a new tipoadoBiologico.
     *
     * @param tipoadoBiologico the tipoadoBiologico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoadoBiologico, or with status 400 (Bad Request) if the tipoadoBiologico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipoado-biologicos")
    @Timed
    public ResponseEntity<TipoadoBiologico> createTipoadoBiologico(@Valid @RequestBody TipoadoBiologico tipoadoBiologico) throws URISyntaxException {
        log.debug("REST request to save TipoadoBiologico : {}", tipoadoBiologico);
        if (tipoadoBiologico.getId() != null) {
            throw new BadRequestAlertException("A new tipoadoBiologico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoadoBiologico result = tipoadoBiologicoService.save(tipoadoBiologico);
        return ResponseEntity.created(new URI("/api/tipoado-biologicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipoado-biologicos : Updates an existing tipoadoBiologico.
     *
     * @param tipoadoBiologico the tipoadoBiologico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoadoBiologico,
     * or with status 400 (Bad Request) if the tipoadoBiologico is not valid,
     * or with status 500 (Internal Server Error) if the tipoadoBiologico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipoado-biologicos")
    @Timed
    public ResponseEntity<TipoadoBiologico> updateTipoadoBiologico(@Valid @RequestBody TipoadoBiologico tipoadoBiologico) throws URISyntaxException {
        log.debug("REST request to update TipoadoBiologico : {}", tipoadoBiologico);
        if (tipoadoBiologico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoadoBiologico result = tipoadoBiologicoService.save(tipoadoBiologico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoadoBiologico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipoado-biologicos : get all the tipoadoBiologicos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoadoBiologicos in body
     */
    @GetMapping("/tipoado-biologicos")
    @Timed
    public ResponseEntity<List<TipoadoBiologico>> getAllTipoadoBiologicos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("dadobiologico-is-null".equals(filter)) {
            log.debug("REST request to get all TipoadoBiologicos where dadoBiologico is null");
            return new ResponseEntity<>(tipoadoBiologicoService.findAllWhereDadoBiologicoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoadoBiologicos");
        Page<TipoadoBiologico> page = tipoadoBiologicoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipoado-biologicos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipoado-biologicos/:id : get the "id" tipoadoBiologico.
     *
     * @param id the id of the tipoadoBiologico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoadoBiologico, or with status 404 (Not Found)
     */
    @GetMapping("/tipoado-biologicos/{id}")
    @Timed
    public ResponseEntity<TipoadoBiologico> getTipoadoBiologico(@PathVariable Long id) {
        log.debug("REST request to get TipoadoBiologico : {}", id);
        Optional<TipoadoBiologico> tipoadoBiologico = tipoadoBiologicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoadoBiologico);
    }

    /**
     * DELETE  /tipoado-biologicos/:id : delete the "id" tipoadoBiologico.
     *
     * @param id the id of the tipoadoBiologico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipoado-biologicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoadoBiologico(@PathVariable Long id) {
        log.debug("REST request to delete TipoadoBiologico : {}", id);
        tipoadoBiologicoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
