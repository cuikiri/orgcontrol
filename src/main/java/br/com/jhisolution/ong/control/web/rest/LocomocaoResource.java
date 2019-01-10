package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Locomocao;
import br.com.jhisolution.ong.control.service.LocomocaoService;
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
 * REST controller for managing Locomocao.
 */
@RestController
@RequestMapping("/api")
public class LocomocaoResource {

    private final Logger log = LoggerFactory.getLogger(LocomocaoResource.class);

    private static final String ENTITY_NAME = "locomocao";

    private final LocomocaoService locomocaoService;

    public LocomocaoResource(LocomocaoService locomocaoService) {
        this.locomocaoService = locomocaoService;
    }

    /**
     * POST  /locomocaos : Create a new locomocao.
     *
     * @param locomocao the locomocao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new locomocao, or with status 400 (Bad Request) if the locomocao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/locomocaos")
    @Timed
    public ResponseEntity<Locomocao> createLocomocao(@Valid @RequestBody Locomocao locomocao) throws URISyntaxException {
        log.debug("REST request to save Locomocao : {}", locomocao);
        if (locomocao.getId() != null) {
            throw new BadRequestAlertException("A new locomocao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Locomocao result = locomocaoService.save(locomocao);
        return ResponseEntity.created(new URI("/api/locomocaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /locomocaos : Updates an existing locomocao.
     *
     * @param locomocao the locomocao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated locomocao,
     * or with status 400 (Bad Request) if the locomocao is not valid,
     * or with status 500 (Internal Server Error) if the locomocao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/locomocaos")
    @Timed
    public ResponseEntity<Locomocao> updateLocomocao(@Valid @RequestBody Locomocao locomocao) throws URISyntaxException {
        log.debug("REST request to update Locomocao : {}", locomocao);
        if (locomocao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Locomocao result = locomocaoService.save(locomocao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, locomocao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /locomocaos : get all the locomocaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of locomocaos in body
     */
    @GetMapping("/locomocaos")
    @Timed
    public ResponseEntity<List<Locomocao>> getAllLocomocaos(Pageable pageable) {
        log.debug("REST request to get a page of Locomocaos");
        Page<Locomocao> page = locomocaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/locomocaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /locomocaos/:id : get the "id" locomocao.
     *
     * @param id the id of the locomocao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the locomocao, or with status 404 (Not Found)
     */
    @GetMapping("/locomocaos/{id}")
    @Timed
    public ResponseEntity<Locomocao> getLocomocao(@PathVariable Long id) {
        log.debug("REST request to get Locomocao : {}", id);
        Optional<Locomocao> locomocao = locomocaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(locomocao);
    }

    /**
     * DELETE  /locomocaos/:id : delete the "id" locomocao.
     *
     * @param id the id of the locomocao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/locomocaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteLocomocao(@PathVariable Long id) {
        log.debug("REST request to delete Locomocao : {}", id);
        locomocaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
