package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.EstadoCivil;
import br.com.jhisolution.ong.control.service.EstadoCivilService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing EstadoCivil.
 */
@RestController
@RequestMapping("/api")
public class EstadoCivilResource {

    private final Logger log = LoggerFactory.getLogger(EstadoCivilResource.class);

    private static final String ENTITY_NAME = "estadoCivil";

    private final EstadoCivilService estadoCivilService;

    public EstadoCivilResource(EstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    /**
     * POST  /estado-civils : Create a new estadoCivil.
     *
     * @param estadoCivil the estadoCivil to create
     * @return the ResponseEntity with status 201 (Created) and with body the new estadoCivil, or with status 400 (Bad Request) if the estadoCivil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/estado-civils")
    @Timed
    public ResponseEntity<EstadoCivil> createEstadoCivil(@Valid @RequestBody EstadoCivil estadoCivil) throws URISyntaxException {
        log.debug("REST request to save EstadoCivil : {}", estadoCivil);
        if (estadoCivil.getId() != null) {
            throw new BadRequestAlertException("A new estadoCivil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadoCivil result = estadoCivilService.save(estadoCivil);
        return ResponseEntity.created(new URI("/api/estado-civils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /estado-civils : Updates an existing estadoCivil.
     *
     * @param estadoCivil the estadoCivil to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated estadoCivil,
     * or with status 400 (Bad Request) if the estadoCivil is not valid,
     * or with status 500 (Internal Server Error) if the estadoCivil couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/estado-civils")
    @Timed
    public ResponseEntity<EstadoCivil> updateEstadoCivil(@Valid @RequestBody EstadoCivil estadoCivil) throws URISyntaxException {
        log.debug("REST request to update EstadoCivil : {}", estadoCivil);
        if (estadoCivil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadoCivil result = estadoCivilService.save(estadoCivil);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estadoCivil.getId().toString()))
            .body(result);
    }

    /**
     * GET  /estado-civils : get all the estadoCivils.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of estadoCivils in body
     */
    @GetMapping("/estado-civils")
    @Timed
    public ResponseEntity<List<EstadoCivil>> getAllEstadoCivils(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("colaborador-is-null".equals(filter)) {
            log.debug("REST request to get all EstadoCivils where colaborador is null");
            return new ResponseEntity<>(estadoCivilService.findAllWhereColaboradorIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of EstadoCivils");
        Page<EstadoCivil> page = estadoCivilService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/estado-civils");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /estado-civils/:id : get the "id" estadoCivil.
     *
     * @param id the id of the estadoCivil to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the estadoCivil, or with status 404 (Not Found)
     */
    @GetMapping("/estado-civils/{id}")
    @Timed
    public ResponseEntity<EstadoCivil> getEstadoCivil(@PathVariable Long id) {
        log.debug("REST request to get EstadoCivil : {}", id);
        Optional<EstadoCivil> estadoCivil = estadoCivilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadoCivil);
    }

    /**
     * DELETE  /estado-civils/:id : delete the "id" estadoCivil.
     *
     * @param id the id of the estadoCivil to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/estado-civils/{id}")
    @Timed
    public ResponseEntity<Void> deleteEstadoCivil(@PathVariable Long id) {
        log.debug("REST request to delete EstadoCivil : {}", id);
        estadoCivilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
