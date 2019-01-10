package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ProblemaFisico;
import br.com.jhisolution.ong.control.service.ProblemaFisicoService;
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
 * REST controller for managing ProblemaFisico.
 */
@RestController
@RequestMapping("/api")
public class ProblemaFisicoResource {

    private final Logger log = LoggerFactory.getLogger(ProblemaFisicoResource.class);

    private static final String ENTITY_NAME = "problemaFisico";

    private final ProblemaFisicoService problemaFisicoService;

    public ProblemaFisicoResource(ProblemaFisicoService problemaFisicoService) {
        this.problemaFisicoService = problemaFisicoService;
    }

    /**
     * POST  /problema-fisicos : Create a new problemaFisico.
     *
     * @param problemaFisico the problemaFisico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new problemaFisico, or with status 400 (Bad Request) if the problemaFisico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/problema-fisicos")
    @Timed
    public ResponseEntity<ProblemaFisico> createProblemaFisico(@Valid @RequestBody ProblemaFisico problemaFisico) throws URISyntaxException {
        log.debug("REST request to save ProblemaFisico : {}", problemaFisico);
        if (problemaFisico.getId() != null) {
            throw new BadRequestAlertException("A new problemaFisico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProblemaFisico result = problemaFisicoService.save(problemaFisico);
        return ResponseEntity.created(new URI("/api/problema-fisicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /problema-fisicos : Updates an existing problemaFisico.
     *
     * @param problemaFisico the problemaFisico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated problemaFisico,
     * or with status 400 (Bad Request) if the problemaFisico is not valid,
     * or with status 500 (Internal Server Error) if the problemaFisico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/problema-fisicos")
    @Timed
    public ResponseEntity<ProblemaFisico> updateProblemaFisico(@Valid @RequestBody ProblemaFisico problemaFisico) throws URISyntaxException {
        log.debug("REST request to update ProblemaFisico : {}", problemaFisico);
        if (problemaFisico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProblemaFisico result = problemaFisicoService.save(problemaFisico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, problemaFisico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /problema-fisicos : get all the problemaFisicos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of problemaFisicos in body
     */
    @GetMapping("/problema-fisicos")
    @Timed
    public ResponseEntity<List<ProblemaFisico>> getAllProblemaFisicos(Pageable pageable) {
        log.debug("REST request to get a page of ProblemaFisicos");
        Page<ProblemaFisico> page = problemaFisicoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/problema-fisicos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /problema-fisicos/:id : get the "id" problemaFisico.
     *
     * @param id the id of the problemaFisico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the problemaFisico, or with status 404 (Not Found)
     */
    @GetMapping("/problema-fisicos/{id}")
    @Timed
    public ResponseEntity<ProblemaFisico> getProblemaFisico(@PathVariable Long id) {
        log.debug("REST request to get ProblemaFisico : {}", id);
        Optional<ProblemaFisico> problemaFisico = problemaFisicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(problemaFisico);
    }

    /**
     * DELETE  /problema-fisicos/:id : delete the "id" problemaFisico.
     *
     * @param id the id of the problemaFisico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/problema-fisicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProblemaFisico(@PathVariable Long id) {
        log.debug("REST request to delete ProblemaFisico : {}", id);
        problemaFisicoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
