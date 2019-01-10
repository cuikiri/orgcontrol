package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Generalidade;
import br.com.jhisolution.ong.control.service.GeneralidadeService;
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
 * REST controller for managing Generalidade.
 */
@RestController
@RequestMapping("/api")
public class GeneralidadeResource {

    private final Logger log = LoggerFactory.getLogger(GeneralidadeResource.class);

    private static final String ENTITY_NAME = "generalidade";

    private final GeneralidadeService generalidadeService;

    public GeneralidadeResource(GeneralidadeService generalidadeService) {
        this.generalidadeService = generalidadeService;
    }

    /**
     * POST  /generalidades : Create a new generalidade.
     *
     * @param generalidade the generalidade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new generalidade, or with status 400 (Bad Request) if the generalidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/generalidades")
    @Timed
    public ResponseEntity<Generalidade> createGeneralidade(@Valid @RequestBody Generalidade generalidade) throws URISyntaxException {
        log.debug("REST request to save Generalidade : {}", generalidade);
        if (generalidade.getId() != null) {
            throw new BadRequestAlertException("A new generalidade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Generalidade result = generalidadeService.save(generalidade);
        return ResponseEntity.created(new URI("/api/generalidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /generalidades : Updates an existing generalidade.
     *
     * @param generalidade the generalidade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated generalidade,
     * or with status 400 (Bad Request) if the generalidade is not valid,
     * or with status 500 (Internal Server Error) if the generalidade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/generalidades")
    @Timed
    public ResponseEntity<Generalidade> updateGeneralidade(@Valid @RequestBody Generalidade generalidade) throws URISyntaxException {
        log.debug("REST request to update Generalidade : {}", generalidade);
        if (generalidade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Generalidade result = generalidadeService.save(generalidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, generalidade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /generalidades : get all the generalidades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of generalidades in body
     */
    @GetMapping("/generalidades")
    @Timed
    public ResponseEntity<List<Generalidade>> getAllGeneralidades(Pageable pageable) {
        log.debug("REST request to get a page of Generalidades");
        Page<Generalidade> page = generalidadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/generalidades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /generalidades/:id : get the "id" generalidade.
     *
     * @param id the id of the generalidade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the generalidade, or with status 404 (Not Found)
     */
    @GetMapping("/generalidades/{id}")
    @Timed
    public ResponseEntity<Generalidade> getGeneralidade(@PathVariable Long id) {
        log.debug("REST request to get Generalidade : {}", id);
        Optional<Generalidade> generalidade = generalidadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(generalidade);
    }

    /**
     * DELETE  /generalidades/:id : delete the "id" generalidade.
     *
     * @param id the id of the generalidade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/generalidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteGeneralidade(@PathVariable Long id) {
        log.debug("REST request to delete Generalidade : {}", id);
        generalidadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
