package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Eleicao;
import br.com.jhisolution.ong.control.service.EleicaoService;
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
 * REST controller for managing Eleicao.
 */
@RestController
@RequestMapping("/api")
public class EleicaoResource {

    private final Logger log = LoggerFactory.getLogger(EleicaoResource.class);

    private static final String ENTITY_NAME = "eleicao";

    private final EleicaoService eleicaoService;

    public EleicaoResource(EleicaoService eleicaoService) {
        this.eleicaoService = eleicaoService;
    }

    /**
     * POST  /eleicaos : Create a new eleicao.
     *
     * @param eleicao the eleicao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eleicao, or with status 400 (Bad Request) if the eleicao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eleicaos")
    @Timed
    public ResponseEntity<Eleicao> createEleicao(@Valid @RequestBody Eleicao eleicao) throws URISyntaxException {
        log.debug("REST request to save Eleicao : {}", eleicao);
        if (eleicao.getId() != null) {
            throw new BadRequestAlertException("A new eleicao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Eleicao result = eleicaoService.save(eleicao);
        return ResponseEntity.created(new URI("/api/eleicaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eleicaos : Updates an existing eleicao.
     *
     * @param eleicao the eleicao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eleicao,
     * or with status 400 (Bad Request) if the eleicao is not valid,
     * or with status 500 (Internal Server Error) if the eleicao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eleicaos")
    @Timed
    public ResponseEntity<Eleicao> updateEleicao(@Valid @RequestBody Eleicao eleicao) throws URISyntaxException {
        log.debug("REST request to update Eleicao : {}", eleicao);
        if (eleicao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Eleicao result = eleicaoService.save(eleicao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eleicao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eleicaos : get all the eleicaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eleicaos in body
     */
    @GetMapping("/eleicaos")
    @Timed
    public ResponseEntity<List<Eleicao>> getAllEleicaos(Pageable pageable) {
        log.debug("REST request to get a page of Eleicaos");
        Page<Eleicao> page = eleicaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/eleicaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /eleicaos/:id : get the "id" eleicao.
     *
     * @param id the id of the eleicao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eleicao, or with status 404 (Not Found)
     */
    @GetMapping("/eleicaos/{id}")
    @Timed
    public ResponseEntity<Eleicao> getEleicao(@PathVariable Long id) {
        log.debug("REST request to get Eleicao : {}", id);
        Optional<Eleicao> eleicao = eleicaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eleicao);
    }

    /**
     * DELETE  /eleicaos/:id : delete the "id" eleicao.
     *
     * @param id the id of the eleicao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eleicaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEleicao(@PathVariable Long id) {
        log.debug("REST request to delete Eleicao : {}", id);
        eleicaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
