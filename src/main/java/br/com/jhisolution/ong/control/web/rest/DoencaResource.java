package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Doenca;
import br.com.jhisolution.ong.control.service.DoencaService;
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
 * REST controller for managing Doenca.
 */
@RestController
@RequestMapping("/api")
public class DoencaResource {

    private final Logger log = LoggerFactory.getLogger(DoencaResource.class);

    private static final String ENTITY_NAME = "doenca";

    private final DoencaService doencaService;

    public DoencaResource(DoencaService doencaService) {
        this.doencaService = doencaService;
    }

    /**
     * POST  /doencas : Create a new doenca.
     *
     * @param doenca the doenca to create
     * @return the ResponseEntity with status 201 (Created) and with body the new doenca, or with status 400 (Bad Request) if the doenca has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/doencas")
    @Timed
    public ResponseEntity<Doenca> createDoenca(@Valid @RequestBody Doenca doenca) throws URISyntaxException {
        log.debug("REST request to save Doenca : {}", doenca);
        if (doenca.getId() != null) {
            throw new BadRequestAlertException("A new doenca cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Doenca result = doencaService.save(doenca);
        return ResponseEntity.created(new URI("/api/doencas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /doencas : Updates an existing doenca.
     *
     * @param doenca the doenca to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated doenca,
     * or with status 400 (Bad Request) if the doenca is not valid,
     * or with status 500 (Internal Server Error) if the doenca couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/doencas")
    @Timed
    public ResponseEntity<Doenca> updateDoenca(@Valid @RequestBody Doenca doenca) throws URISyntaxException {
        log.debug("REST request to update Doenca : {}", doenca);
        if (doenca.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Doenca result = doencaService.save(doenca);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, doenca.getId().toString()))
            .body(result);
    }

    /**
     * GET  /doencas : get all the doencas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of doencas in body
     */
    @GetMapping("/doencas")
    @Timed
    public ResponseEntity<List<Doenca>> getAllDoencas(Pageable pageable) {
        log.debug("REST request to get a page of Doencas");
        Page<Doenca> page = doencaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/doencas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /doencas/:id : get the "id" doenca.
     *
     * @param id the id of the doenca to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the doenca, or with status 404 (Not Found)
     */
    @GetMapping("/doencas/{id}")
    @Timed
    public ResponseEntity<Doenca> getDoenca(@PathVariable Long id) {
        log.debug("REST request to get Doenca : {}", id);
        Optional<Doenca> doenca = doencaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doenca);
    }

    /**
     * DELETE  /doencas/:id : delete the "id" doenca.
     *
     * @param id the id of the doenca to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/doencas/{id}")
    @Timed
    public ResponseEntity<Void> deleteDoenca(@PathVariable Long id) {
        log.debug("REST request to delete Doenca : {}", id);
        doencaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
