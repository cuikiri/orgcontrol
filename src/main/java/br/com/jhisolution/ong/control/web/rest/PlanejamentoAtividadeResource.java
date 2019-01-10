package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.PlanejamentoAtividade;
import br.com.jhisolution.ong.control.service.PlanejamentoAtividadeService;
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
 * REST controller for managing PlanejamentoAtividade.
 */
@RestController
@RequestMapping("/api")
public class PlanejamentoAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(PlanejamentoAtividadeResource.class);

    private static final String ENTITY_NAME = "planejamentoAtividade";

    private final PlanejamentoAtividadeService planejamentoAtividadeService;

    public PlanejamentoAtividadeResource(PlanejamentoAtividadeService planejamentoAtividadeService) {
        this.planejamentoAtividadeService = planejamentoAtividadeService;
    }

    /**
     * POST  /planejamento-atividades : Create a new planejamentoAtividade.
     *
     * @param planejamentoAtividade the planejamentoAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planejamentoAtividade, or with status 400 (Bad Request) if the planejamentoAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/planejamento-atividades")
    @Timed
    public ResponseEntity<PlanejamentoAtividade> createPlanejamentoAtividade(@Valid @RequestBody PlanejamentoAtividade planejamentoAtividade) throws URISyntaxException {
        log.debug("REST request to save PlanejamentoAtividade : {}", planejamentoAtividade);
        if (planejamentoAtividade.getId() != null) {
            throw new BadRequestAlertException("A new planejamentoAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanejamentoAtividade result = planejamentoAtividadeService.save(planejamentoAtividade);
        return ResponseEntity.created(new URI("/api/planejamento-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /planejamento-atividades : Updates an existing planejamentoAtividade.
     *
     * @param planejamentoAtividade the planejamentoAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planejamentoAtividade,
     * or with status 400 (Bad Request) if the planejamentoAtividade is not valid,
     * or with status 500 (Internal Server Error) if the planejamentoAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/planejamento-atividades")
    @Timed
    public ResponseEntity<PlanejamentoAtividade> updatePlanejamentoAtividade(@Valid @RequestBody PlanejamentoAtividade planejamentoAtividade) throws URISyntaxException {
        log.debug("REST request to update PlanejamentoAtividade : {}", planejamentoAtividade);
        if (planejamentoAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanejamentoAtividade result = planejamentoAtividadeService.save(planejamentoAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planejamentoAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /planejamento-atividades : get all the planejamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of planejamentoAtividades in body
     */
    @GetMapping("/planejamento-atividades")
    @Timed
    public ResponseEntity<List<PlanejamentoAtividade>> getAllPlanejamentoAtividades(Pageable pageable) {
        log.debug("REST request to get a page of PlanejamentoAtividades");
        Page<PlanejamentoAtividade> page = planejamentoAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/planejamento-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /planejamento-atividades/:id : get the "id" planejamentoAtividade.
     *
     * @param id the id of the planejamentoAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planejamentoAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/planejamento-atividades/{id}")
    @Timed
    public ResponseEntity<PlanejamentoAtividade> getPlanejamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to get PlanejamentoAtividade : {}", id);
        Optional<PlanejamentoAtividade> planejamentoAtividade = planejamentoAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planejamentoAtividade);
    }

    /**
     * DELETE  /planejamento-atividades/:id : delete the "id" planejamentoAtividade.
     *
     * @param id the id of the planejamentoAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/planejamento-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanejamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to delete PlanejamentoAtividade : {}", id);
        planejamentoAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
