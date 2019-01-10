package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.BimestreAcompanhamento;
import br.com.jhisolution.ong.control.service.BimestreAcompanhamentoService;
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
 * REST controller for managing BimestreAcompanhamento.
 */
@RestController
@RequestMapping("/api")
public class BimestreAcompanhamentoResource {

    private final Logger log = LoggerFactory.getLogger(BimestreAcompanhamentoResource.class);

    private static final String ENTITY_NAME = "bimestreAcompanhamento";

    private final BimestreAcompanhamentoService bimestreAcompanhamentoService;

    public BimestreAcompanhamentoResource(BimestreAcompanhamentoService bimestreAcompanhamentoService) {
        this.bimestreAcompanhamentoService = bimestreAcompanhamentoService;
    }

    /**
     * POST  /bimestre-acompanhamentos : Create a new bimestreAcompanhamento.
     *
     * @param bimestreAcompanhamento the bimestreAcompanhamento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bimestreAcompanhamento, or with status 400 (Bad Request) if the bimestreAcompanhamento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bimestre-acompanhamentos")
    @Timed
    public ResponseEntity<BimestreAcompanhamento> createBimestreAcompanhamento(@Valid @RequestBody BimestreAcompanhamento bimestreAcompanhamento) throws URISyntaxException {
        log.debug("REST request to save BimestreAcompanhamento : {}", bimestreAcompanhamento);
        if (bimestreAcompanhamento.getId() != null) {
            throw new BadRequestAlertException("A new bimestreAcompanhamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BimestreAcompanhamento result = bimestreAcompanhamentoService.save(bimestreAcompanhamento);
        return ResponseEntity.created(new URI("/api/bimestre-acompanhamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bimestre-acompanhamentos : Updates an existing bimestreAcompanhamento.
     *
     * @param bimestreAcompanhamento the bimestreAcompanhamento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bimestreAcompanhamento,
     * or with status 400 (Bad Request) if the bimestreAcompanhamento is not valid,
     * or with status 500 (Internal Server Error) if the bimestreAcompanhamento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bimestre-acompanhamentos")
    @Timed
    public ResponseEntity<BimestreAcompanhamento> updateBimestreAcompanhamento(@Valid @RequestBody BimestreAcompanhamento bimestreAcompanhamento) throws URISyntaxException {
        log.debug("REST request to update BimestreAcompanhamento : {}", bimestreAcompanhamento);
        if (bimestreAcompanhamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BimestreAcompanhamento result = bimestreAcompanhamentoService.save(bimestreAcompanhamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bimestreAcompanhamento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bimestre-acompanhamentos : get all the bimestreAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bimestreAcompanhamentos in body
     */
    @GetMapping("/bimestre-acompanhamentos")
    @Timed
    public ResponseEntity<List<BimestreAcompanhamento>> getAllBimestreAcompanhamentos(Pageable pageable) {
        log.debug("REST request to get a page of BimestreAcompanhamentos");
        Page<BimestreAcompanhamento> page = bimestreAcompanhamentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bimestre-acompanhamentos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /bimestre-acompanhamentos/:id : get the "id" bimestreAcompanhamento.
     *
     * @param id the id of the bimestreAcompanhamento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bimestreAcompanhamento, or with status 404 (Not Found)
     */
    @GetMapping("/bimestre-acompanhamentos/{id}")
    @Timed
    public ResponseEntity<BimestreAcompanhamento> getBimestreAcompanhamento(@PathVariable Long id) {
        log.debug("REST request to get BimestreAcompanhamento : {}", id);
        Optional<BimestreAcompanhamento> bimestreAcompanhamento = bimestreAcompanhamentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bimestreAcompanhamento);
    }

    /**
     * DELETE  /bimestre-acompanhamentos/:id : delete the "id" bimestreAcompanhamento.
     *
     * @param id the id of the bimestreAcompanhamento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bimestre-acompanhamentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteBimestreAcompanhamento(@PathVariable Long id) {
        log.debug("REST request to delete BimestreAcompanhamento : {}", id);
        bimestreAcompanhamentoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
