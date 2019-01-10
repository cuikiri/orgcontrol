package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.PeriodoDuracao;
import br.com.jhisolution.ong.control.service.PeriodoDuracaoService;
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
 * REST controller for managing PeriodoDuracao.
 */
@RestController
@RequestMapping("/api")
public class PeriodoDuracaoResource {

    private final Logger log = LoggerFactory.getLogger(PeriodoDuracaoResource.class);

    private static final String ENTITY_NAME = "periodoDuracao";

    private final PeriodoDuracaoService periodoDuracaoService;

    public PeriodoDuracaoResource(PeriodoDuracaoService periodoDuracaoService) {
        this.periodoDuracaoService = periodoDuracaoService;
    }

    /**
     * POST  /periodo-duracaos : Create a new periodoDuracao.
     *
     * @param periodoDuracao the periodoDuracao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new periodoDuracao, or with status 400 (Bad Request) if the periodoDuracao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/periodo-duracaos")
    @Timed
    public ResponseEntity<PeriodoDuracao> createPeriodoDuracao(@Valid @RequestBody PeriodoDuracao periodoDuracao) throws URISyntaxException {
        log.debug("REST request to save PeriodoDuracao : {}", periodoDuracao);
        if (periodoDuracao.getId() != null) {
            throw new BadRequestAlertException("A new periodoDuracao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PeriodoDuracao result = periodoDuracaoService.save(periodoDuracao);
        return ResponseEntity.created(new URI("/api/periodo-duracaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /periodo-duracaos : Updates an existing periodoDuracao.
     *
     * @param periodoDuracao the periodoDuracao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated periodoDuracao,
     * or with status 400 (Bad Request) if the periodoDuracao is not valid,
     * or with status 500 (Internal Server Error) if the periodoDuracao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/periodo-duracaos")
    @Timed
    public ResponseEntity<PeriodoDuracao> updatePeriodoDuracao(@Valid @RequestBody PeriodoDuracao periodoDuracao) throws URISyntaxException {
        log.debug("REST request to update PeriodoDuracao : {}", periodoDuracao);
        if (periodoDuracao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PeriodoDuracao result = periodoDuracaoService.save(periodoDuracao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, periodoDuracao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /periodo-duracaos : get all the periodoDuracaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of periodoDuracaos in body
     */
    @GetMapping("/periodo-duracaos")
    @Timed
    public ResponseEntity<List<PeriodoDuracao>> getAllPeriodoDuracaos(Pageable pageable) {
        log.debug("REST request to get a page of PeriodoDuracaos");
        Page<PeriodoDuracao> page = periodoDuracaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/periodo-duracaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /periodo-duracaos/:id : get the "id" periodoDuracao.
     *
     * @param id the id of the periodoDuracao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the periodoDuracao, or with status 404 (Not Found)
     */
    @GetMapping("/periodo-duracaos/{id}")
    @Timed
    public ResponseEntity<PeriodoDuracao> getPeriodoDuracao(@PathVariable Long id) {
        log.debug("REST request to get PeriodoDuracao : {}", id);
        Optional<PeriodoDuracao> periodoDuracao = periodoDuracaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(periodoDuracao);
    }

    /**
     * DELETE  /periodo-duracaos/:id : delete the "id" periodoDuracao.
     *
     * @param id the id of the periodoDuracao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/periodo-duracaos/{id}")
    @Timed
    public ResponseEntity<Void> deletePeriodoDuracao(@PathVariable Long id) {
        log.debug("REST request to delete PeriodoDuracao : {}", id);
        periodoDuracaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
