package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.CalendarioInstituicao;
import br.com.jhisolution.ong.control.service.CalendarioInstituicaoService;
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
 * REST controller for managing CalendarioInstituicao.
 */
@RestController
@RequestMapping("/api")
public class CalendarioInstituicaoResource {

    private final Logger log = LoggerFactory.getLogger(CalendarioInstituicaoResource.class);

    private static final String ENTITY_NAME = "calendarioInstituicao";

    private final CalendarioInstituicaoService calendarioInstituicaoService;

    public CalendarioInstituicaoResource(CalendarioInstituicaoService calendarioInstituicaoService) {
        this.calendarioInstituicaoService = calendarioInstituicaoService;
    }

    /**
     * POST  /calendario-instituicaos : Create a new calendarioInstituicao.
     *
     * @param calendarioInstituicao the calendarioInstituicao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new calendarioInstituicao, or with status 400 (Bad Request) if the calendarioInstituicao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/calendario-instituicaos")
    @Timed
    public ResponseEntity<CalendarioInstituicao> createCalendarioInstituicao(@Valid @RequestBody CalendarioInstituicao calendarioInstituicao) throws URISyntaxException {
        log.debug("REST request to save CalendarioInstituicao : {}", calendarioInstituicao);
        if (calendarioInstituicao.getId() != null) {
            throw new BadRequestAlertException("A new calendarioInstituicao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CalendarioInstituicao result = calendarioInstituicaoService.save(calendarioInstituicao);
        return ResponseEntity.created(new URI("/api/calendario-instituicaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /calendario-instituicaos : Updates an existing calendarioInstituicao.
     *
     * @param calendarioInstituicao the calendarioInstituicao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated calendarioInstituicao,
     * or with status 400 (Bad Request) if the calendarioInstituicao is not valid,
     * or with status 500 (Internal Server Error) if the calendarioInstituicao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/calendario-instituicaos")
    @Timed
    public ResponseEntity<CalendarioInstituicao> updateCalendarioInstituicao(@Valid @RequestBody CalendarioInstituicao calendarioInstituicao) throws URISyntaxException {
        log.debug("REST request to update CalendarioInstituicao : {}", calendarioInstituicao);
        if (calendarioInstituicao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CalendarioInstituicao result = calendarioInstituicaoService.save(calendarioInstituicao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, calendarioInstituicao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /calendario-instituicaos : get all the calendarioInstituicaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of calendarioInstituicaos in body
     */
    @GetMapping("/calendario-instituicaos")
    @Timed
    public ResponseEntity<List<CalendarioInstituicao>> getAllCalendarioInstituicaos(Pageable pageable) {
        log.debug("REST request to get a page of CalendarioInstituicaos");
        Page<CalendarioInstituicao> page = calendarioInstituicaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/calendario-instituicaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /calendario-instituicaos/:id : get the "id" calendarioInstituicao.
     *
     * @param id the id of the calendarioInstituicao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the calendarioInstituicao, or with status 404 (Not Found)
     */
    @GetMapping("/calendario-instituicaos/{id}")
    @Timed
    public ResponseEntity<CalendarioInstituicao> getCalendarioInstituicao(@PathVariable Long id) {
        log.debug("REST request to get CalendarioInstituicao : {}", id);
        Optional<CalendarioInstituicao> calendarioInstituicao = calendarioInstituicaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(calendarioInstituicao);
    }

    /**
     * DELETE  /calendario-instituicaos/:id : delete the "id" calendarioInstituicao.
     *
     * @param id the id of the calendarioInstituicao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/calendario-instituicaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCalendarioInstituicao(@PathVariable Long id) {
        log.debug("REST request to delete CalendarioInstituicao : {}", id);
        calendarioInstituicaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
