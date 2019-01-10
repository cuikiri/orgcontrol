package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.PlanejamentoInstituicao;
import br.com.jhisolution.ong.control.service.PlanejamentoInstituicaoService;
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
 * REST controller for managing PlanejamentoInstituicao.
 */
@RestController
@RequestMapping("/api")
public class PlanejamentoInstituicaoResource {

    private final Logger log = LoggerFactory.getLogger(PlanejamentoInstituicaoResource.class);

    private static final String ENTITY_NAME = "planejamentoInstituicao";

    private final PlanejamentoInstituicaoService planejamentoInstituicaoService;

    public PlanejamentoInstituicaoResource(PlanejamentoInstituicaoService planejamentoInstituicaoService) {
        this.planejamentoInstituicaoService = planejamentoInstituicaoService;
    }

    /**
     * POST  /planejamento-instituicaos : Create a new planejamentoInstituicao.
     *
     * @param planejamentoInstituicao the planejamentoInstituicao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planejamentoInstituicao, or with status 400 (Bad Request) if the planejamentoInstituicao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/planejamento-instituicaos")
    @Timed
    public ResponseEntity<PlanejamentoInstituicao> createPlanejamentoInstituicao(@Valid @RequestBody PlanejamentoInstituicao planejamentoInstituicao) throws URISyntaxException {
        log.debug("REST request to save PlanejamentoInstituicao : {}", planejamentoInstituicao);
        if (planejamentoInstituicao.getId() != null) {
            throw new BadRequestAlertException("A new planejamentoInstituicao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanejamentoInstituicao result = planejamentoInstituicaoService.save(planejamentoInstituicao);
        return ResponseEntity.created(new URI("/api/planejamento-instituicaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /planejamento-instituicaos : Updates an existing planejamentoInstituicao.
     *
     * @param planejamentoInstituicao the planejamentoInstituicao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planejamentoInstituicao,
     * or with status 400 (Bad Request) if the planejamentoInstituicao is not valid,
     * or with status 500 (Internal Server Error) if the planejamentoInstituicao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/planejamento-instituicaos")
    @Timed
    public ResponseEntity<PlanejamentoInstituicao> updatePlanejamentoInstituicao(@Valid @RequestBody PlanejamentoInstituicao planejamentoInstituicao) throws URISyntaxException {
        log.debug("REST request to update PlanejamentoInstituicao : {}", planejamentoInstituicao);
        if (planejamentoInstituicao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanejamentoInstituicao result = planejamentoInstituicaoService.save(planejamentoInstituicao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planejamentoInstituicao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /planejamento-instituicaos : get all the planejamentoInstituicaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of planejamentoInstituicaos in body
     */
    @GetMapping("/planejamento-instituicaos")
    @Timed
    public ResponseEntity<List<PlanejamentoInstituicao>> getAllPlanejamentoInstituicaos(Pageable pageable) {
        log.debug("REST request to get a page of PlanejamentoInstituicaos");
        Page<PlanejamentoInstituicao> page = planejamentoInstituicaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/planejamento-instituicaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /planejamento-instituicaos/:id : get the "id" planejamentoInstituicao.
     *
     * @param id the id of the planejamentoInstituicao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planejamentoInstituicao, or with status 404 (Not Found)
     */
    @GetMapping("/planejamento-instituicaos/{id}")
    @Timed
    public ResponseEntity<PlanejamentoInstituicao> getPlanejamentoInstituicao(@PathVariable Long id) {
        log.debug("REST request to get PlanejamentoInstituicao : {}", id);
        Optional<PlanejamentoInstituicao> planejamentoInstituicao = planejamentoInstituicaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planejamentoInstituicao);
    }

    /**
     * DELETE  /planejamento-instituicaos/:id : delete the "id" planejamentoInstituicao.
     *
     * @param id the id of the planejamentoInstituicao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/planejamento-instituicaos/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanejamentoInstituicao(@PathVariable Long id) {
        log.debug("REST request to delete PlanejamentoInstituicao : {}", id);
        planejamentoInstituicaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
