package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ConceitoAcompanhamento;
import br.com.jhisolution.ong.control.service.ConceitoAcompanhamentoService;
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
 * REST controller for managing ConceitoAcompanhamento.
 */
@RestController
@RequestMapping("/api")
public class ConceitoAcompanhamentoResource {

    private final Logger log = LoggerFactory.getLogger(ConceitoAcompanhamentoResource.class);

    private static final String ENTITY_NAME = "conceitoAcompanhamento";

    private final ConceitoAcompanhamentoService conceitoAcompanhamentoService;

    public ConceitoAcompanhamentoResource(ConceitoAcompanhamentoService conceitoAcompanhamentoService) {
        this.conceitoAcompanhamentoService = conceitoAcompanhamentoService;
    }

    /**
     * POST  /conceito-acompanhamentos : Create a new conceitoAcompanhamento.
     *
     * @param conceitoAcompanhamento the conceitoAcompanhamento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conceitoAcompanhamento, or with status 400 (Bad Request) if the conceitoAcompanhamento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conceito-acompanhamentos")
    @Timed
    public ResponseEntity<ConceitoAcompanhamento> createConceitoAcompanhamento(@Valid @RequestBody ConceitoAcompanhamento conceitoAcompanhamento) throws URISyntaxException {
        log.debug("REST request to save ConceitoAcompanhamento : {}", conceitoAcompanhamento);
        if (conceitoAcompanhamento.getId() != null) {
            throw new BadRequestAlertException("A new conceitoAcompanhamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConceitoAcompanhamento result = conceitoAcompanhamentoService.save(conceitoAcompanhamento);
        return ResponseEntity.created(new URI("/api/conceito-acompanhamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conceito-acompanhamentos : Updates an existing conceitoAcompanhamento.
     *
     * @param conceitoAcompanhamento the conceitoAcompanhamento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conceitoAcompanhamento,
     * or with status 400 (Bad Request) if the conceitoAcompanhamento is not valid,
     * or with status 500 (Internal Server Error) if the conceitoAcompanhamento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conceito-acompanhamentos")
    @Timed
    public ResponseEntity<ConceitoAcompanhamento> updateConceitoAcompanhamento(@Valid @RequestBody ConceitoAcompanhamento conceitoAcompanhamento) throws URISyntaxException {
        log.debug("REST request to update ConceitoAcompanhamento : {}", conceitoAcompanhamento);
        if (conceitoAcompanhamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConceitoAcompanhamento result = conceitoAcompanhamentoService.save(conceitoAcompanhamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conceitoAcompanhamento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conceito-acompanhamentos : get all the conceitoAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conceitoAcompanhamentos in body
     */
    @GetMapping("/conceito-acompanhamentos")
    @Timed
    public ResponseEntity<List<ConceitoAcompanhamento>> getAllConceitoAcompanhamentos(Pageable pageable) {
        log.debug("REST request to get a page of ConceitoAcompanhamentos");
        Page<ConceitoAcompanhamento> page = conceitoAcompanhamentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conceito-acompanhamentos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /conceito-acompanhamentos/:id : get the "id" conceitoAcompanhamento.
     *
     * @param id the id of the conceitoAcompanhamento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conceitoAcompanhamento, or with status 404 (Not Found)
     */
    @GetMapping("/conceito-acompanhamentos/{id}")
    @Timed
    public ResponseEntity<ConceitoAcompanhamento> getConceitoAcompanhamento(@PathVariable Long id) {
        log.debug("REST request to get ConceitoAcompanhamento : {}", id);
        Optional<ConceitoAcompanhamento> conceitoAcompanhamento = conceitoAcompanhamentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conceitoAcompanhamento);
    }

    /**
     * DELETE  /conceito-acompanhamentos/:id : delete the "id" conceitoAcompanhamento.
     *
     * @param id the id of the conceitoAcompanhamento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conceito-acompanhamentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConceitoAcompanhamento(@PathVariable Long id) {
        log.debug("REST request to delete ConceitoAcompanhamento : {}", id);
        conceitoAcompanhamentoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
