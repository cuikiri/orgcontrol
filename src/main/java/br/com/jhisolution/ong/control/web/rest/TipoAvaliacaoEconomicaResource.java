package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoAvaliacaoEconomica;
import br.com.jhisolution.ong.control.service.TipoAvaliacaoEconomicaService;
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
 * REST controller for managing TipoAvaliacaoEconomica.
 */
@RestController
@RequestMapping("/api")
public class TipoAvaliacaoEconomicaResource {

    private final Logger log = LoggerFactory.getLogger(TipoAvaliacaoEconomicaResource.class);

    private static final String ENTITY_NAME = "tipoAvaliacaoEconomica";

    private final TipoAvaliacaoEconomicaService tipoAvaliacaoEconomicaService;

    public TipoAvaliacaoEconomicaResource(TipoAvaliacaoEconomicaService tipoAvaliacaoEconomicaService) {
        this.tipoAvaliacaoEconomicaService = tipoAvaliacaoEconomicaService;
    }

    /**
     * POST  /tipo-avaliacao-economicas : Create a new tipoAvaliacaoEconomica.
     *
     * @param tipoAvaliacaoEconomica the tipoAvaliacaoEconomica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoAvaliacaoEconomica, or with status 400 (Bad Request) if the tipoAvaliacaoEconomica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-avaliacao-economicas")
    @Timed
    public ResponseEntity<TipoAvaliacaoEconomica> createTipoAvaliacaoEconomica(@Valid @RequestBody TipoAvaliacaoEconomica tipoAvaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to save TipoAvaliacaoEconomica : {}", tipoAvaliacaoEconomica);
        if (tipoAvaliacaoEconomica.getId() != null) {
            throw new BadRequestAlertException("A new tipoAvaliacaoEconomica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoAvaliacaoEconomica result = tipoAvaliacaoEconomicaService.save(tipoAvaliacaoEconomica);
        return ResponseEntity.created(new URI("/api/tipo-avaliacao-economicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-avaliacao-economicas : Updates an existing tipoAvaliacaoEconomica.
     *
     * @param tipoAvaliacaoEconomica the tipoAvaliacaoEconomica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoAvaliacaoEconomica,
     * or with status 400 (Bad Request) if the tipoAvaliacaoEconomica is not valid,
     * or with status 500 (Internal Server Error) if the tipoAvaliacaoEconomica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-avaliacao-economicas")
    @Timed
    public ResponseEntity<TipoAvaliacaoEconomica> updateTipoAvaliacaoEconomica(@Valid @RequestBody TipoAvaliacaoEconomica tipoAvaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to update TipoAvaliacaoEconomica : {}", tipoAvaliacaoEconomica);
        if (tipoAvaliacaoEconomica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoAvaliacaoEconomica result = tipoAvaliacaoEconomicaService.save(tipoAvaliacaoEconomica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoAvaliacaoEconomica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-avaliacao-economicas : get all the tipoAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoAvaliacaoEconomicas in body
     */
    @GetMapping("/tipo-avaliacao-economicas")
    @Timed
    public ResponseEntity<List<TipoAvaliacaoEconomica>> getAllTipoAvaliacaoEconomicas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("avaliacaoeconomica-is-null".equals(filter)) {
            log.debug("REST request to get all TipoAvaliacaoEconomicas where avaliacaoEconomica is null");
            return new ResponseEntity<>(tipoAvaliacaoEconomicaService.findAllWhereAvaliacaoEconomicaIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoAvaliacaoEconomicas");
        Page<TipoAvaliacaoEconomica> page = tipoAvaliacaoEconomicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-avaliacao-economicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-avaliacao-economicas/:id : get the "id" tipoAvaliacaoEconomica.
     *
     * @param id the id of the tipoAvaliacaoEconomica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoAvaliacaoEconomica, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<TipoAvaliacaoEconomica> getTipoAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to get TipoAvaliacaoEconomica : {}", id);
        Optional<TipoAvaliacaoEconomica> tipoAvaliacaoEconomica = tipoAvaliacaoEconomicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoAvaliacaoEconomica);
    }

    /**
     * DELETE  /tipo-avaliacao-economicas/:id : delete the "id" tipoAvaliacaoEconomica.
     *
     * @param id the id of the tipoAvaliacaoEconomica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to delete TipoAvaliacaoEconomica : {}", id);
        tipoAvaliacaoEconomicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
