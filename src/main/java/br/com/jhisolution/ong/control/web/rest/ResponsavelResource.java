package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Responsavel;
import br.com.jhisolution.ong.control.service.ResponsavelService;
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
 * REST controller for managing Responsavel.
 */
@RestController
@RequestMapping("/api")
public class ResponsavelResource {

    private final Logger log = LoggerFactory.getLogger(ResponsavelResource.class);

    private static final String ENTITY_NAME = "responsavel";

    private final ResponsavelService responsavelService;

    public ResponsavelResource(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    /**
     * POST  /responsavels : Create a new responsavel.
     *
     * @param responsavel the responsavel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new responsavel, or with status 400 (Bad Request) if the responsavel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/responsavels")
    @Timed
    public ResponseEntity<Responsavel> createResponsavel(@Valid @RequestBody Responsavel responsavel) throws URISyntaxException {
        log.debug("REST request to save Responsavel : {}", responsavel);
        if (responsavel.getId() != null) {
            throw new BadRequestAlertException("A new responsavel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Responsavel result = responsavelService.save(responsavel);
        return ResponseEntity.created(new URI("/api/responsavels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /responsavels : Updates an existing responsavel.
     *
     * @param responsavel the responsavel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated responsavel,
     * or with status 400 (Bad Request) if the responsavel is not valid,
     * or with status 500 (Internal Server Error) if the responsavel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/responsavels")
    @Timed
    public ResponseEntity<Responsavel> updateResponsavel(@Valid @RequestBody Responsavel responsavel) throws URISyntaxException {
        log.debug("REST request to update Responsavel : {}", responsavel);
        if (responsavel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Responsavel result = responsavelService.save(responsavel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, responsavel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /responsavels : get all the responsavels.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of responsavels in body
     */
    @GetMapping("/responsavels")
    @Timed
    public ResponseEntity<List<Responsavel>> getAllResponsavels(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Responsavels");
        Page<Responsavel> page;
        if (eagerload) {
            page = responsavelService.findAllWithEagerRelationships(pageable);
        } else {
            page = responsavelService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/responsavels?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /responsavels/:id : get the "id" responsavel.
     *
     * @param id the id of the responsavel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the responsavel, or with status 404 (Not Found)
     */
    @GetMapping("/responsavels/{id}")
    @Timed
    public ResponseEntity<Responsavel> getResponsavel(@PathVariable Long id) {
        log.debug("REST request to get Responsavel : {}", id);
        Optional<Responsavel> responsavel = responsavelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(responsavel);
    }

    /**
     * DELETE  /responsavels/:id : delete the "id" responsavel.
     *
     * @param id the id of the responsavel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/responsavels/{id}")
    @Timed
    public ResponseEntity<Void> deleteResponsavel(@PathVariable Long id) {
        log.debug("REST request to delete Responsavel : {}", id);
        responsavelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
