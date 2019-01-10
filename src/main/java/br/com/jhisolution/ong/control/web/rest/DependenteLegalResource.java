package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.DependenteLegal;
import br.com.jhisolution.ong.control.service.DependenteLegalService;
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
 * REST controller for managing DependenteLegal.
 */
@RestController
@RequestMapping("/api")
public class DependenteLegalResource {

    private final Logger log = LoggerFactory.getLogger(DependenteLegalResource.class);

    private static final String ENTITY_NAME = "dependenteLegal";

    private final DependenteLegalService dependenteLegalService;

    public DependenteLegalResource(DependenteLegalService dependenteLegalService) {
        this.dependenteLegalService = dependenteLegalService;
    }

    /**
     * POST  /dependente-legals : Create a new dependenteLegal.
     *
     * @param dependenteLegal the dependenteLegal to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dependenteLegal, or with status 400 (Bad Request) if the dependenteLegal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dependente-legals")
    @Timed
    public ResponseEntity<DependenteLegal> createDependenteLegal(@Valid @RequestBody DependenteLegal dependenteLegal) throws URISyntaxException {
        log.debug("REST request to save DependenteLegal : {}", dependenteLegal);
        if (dependenteLegal.getId() != null) {
            throw new BadRequestAlertException("A new dependenteLegal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DependenteLegal result = dependenteLegalService.save(dependenteLegal);
        return ResponseEntity.created(new URI("/api/dependente-legals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dependente-legals : Updates an existing dependenteLegal.
     *
     * @param dependenteLegal the dependenteLegal to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dependenteLegal,
     * or with status 400 (Bad Request) if the dependenteLegal is not valid,
     * or with status 500 (Internal Server Error) if the dependenteLegal couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dependente-legals")
    @Timed
    public ResponseEntity<DependenteLegal> updateDependenteLegal(@Valid @RequestBody DependenteLegal dependenteLegal) throws URISyntaxException {
        log.debug("REST request to update DependenteLegal : {}", dependenteLegal);
        if (dependenteLegal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DependenteLegal result = dependenteLegalService.save(dependenteLegal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dependenteLegal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dependente-legals : get all the dependenteLegals.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dependenteLegals in body
     */
    @GetMapping("/dependente-legals")
    @Timed
    public ResponseEntity<List<DependenteLegal>> getAllDependenteLegals(Pageable pageable) {
        log.debug("REST request to get a page of DependenteLegals");
        Page<DependenteLegal> page = dependenteLegalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dependente-legals");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dependente-legals/:id : get the "id" dependenteLegal.
     *
     * @param id the id of the dependenteLegal to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dependenteLegal, or with status 404 (Not Found)
     */
    @GetMapping("/dependente-legals/{id}")
    @Timed
    public ResponseEntity<DependenteLegal> getDependenteLegal(@PathVariable Long id) {
        log.debug("REST request to get DependenteLegal : {}", id);
        Optional<DependenteLegal> dependenteLegal = dependenteLegalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dependenteLegal);
    }

    /**
     * DELETE  /dependente-legals/:id : delete the "id" dependenteLegal.
     *
     * @param id the id of the dependenteLegal to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dependente-legals/{id}")
    @Timed
    public ResponseEntity<Void> deleteDependenteLegal(@PathVariable Long id) {
        log.debug("REST request to delete DependenteLegal : {}", id);
        dependenteLegalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
