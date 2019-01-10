package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.FechamentoBimestre;
import br.com.jhisolution.ong.control.service.FechamentoBimestreService;
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
 * REST controller for managing FechamentoBimestre.
 */
@RestController
@RequestMapping("/api")
public class FechamentoBimestreResource {

    private final Logger log = LoggerFactory.getLogger(FechamentoBimestreResource.class);

    private static final String ENTITY_NAME = "fechamentoBimestre";

    private final FechamentoBimestreService fechamentoBimestreService;

    public FechamentoBimestreResource(FechamentoBimestreService fechamentoBimestreService) {
        this.fechamentoBimestreService = fechamentoBimestreService;
    }

    /**
     * POST  /fechamento-bimestres : Create a new fechamentoBimestre.
     *
     * @param fechamentoBimestre the fechamentoBimestre to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fechamentoBimestre, or with status 400 (Bad Request) if the fechamentoBimestre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fechamento-bimestres")
    @Timed
    public ResponseEntity<FechamentoBimestre> createFechamentoBimestre(@Valid @RequestBody FechamentoBimestre fechamentoBimestre) throws URISyntaxException {
        log.debug("REST request to save FechamentoBimestre : {}", fechamentoBimestre);
        if (fechamentoBimestre.getId() != null) {
            throw new BadRequestAlertException("A new fechamentoBimestre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FechamentoBimestre result = fechamentoBimestreService.save(fechamentoBimestre);
        return ResponseEntity.created(new URI("/api/fechamento-bimestres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fechamento-bimestres : Updates an existing fechamentoBimestre.
     *
     * @param fechamentoBimestre the fechamentoBimestre to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fechamentoBimestre,
     * or with status 400 (Bad Request) if the fechamentoBimestre is not valid,
     * or with status 500 (Internal Server Error) if the fechamentoBimestre couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fechamento-bimestres")
    @Timed
    public ResponseEntity<FechamentoBimestre> updateFechamentoBimestre(@Valid @RequestBody FechamentoBimestre fechamentoBimestre) throws URISyntaxException {
        log.debug("REST request to update FechamentoBimestre : {}", fechamentoBimestre);
        if (fechamentoBimestre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FechamentoBimestre result = fechamentoBimestreService.save(fechamentoBimestre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fechamentoBimestre.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fechamento-bimestres : get all the fechamentoBimestres.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of fechamentoBimestres in body
     */
    @GetMapping("/fechamento-bimestres")
    @Timed
    public ResponseEntity<List<FechamentoBimestre>> getAllFechamentoBimestres(Pageable pageable, @RequestParam(required = false) String filter, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("bimestre-is-null".equals(filter)) {
            log.debug("REST request to get all FechamentoBimestres where bimestre is null");
            return new ResponseEntity<>(fechamentoBimestreService.findAllWhereBimestreIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of FechamentoBimestres");
        Page<FechamentoBimestre> page;
        if (eagerload) {
            page = fechamentoBimestreService.findAllWithEagerRelationships(pageable);
        } else {
            page = fechamentoBimestreService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/fechamento-bimestres?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fechamento-bimestres/:id : get the "id" fechamentoBimestre.
     *
     * @param id the id of the fechamentoBimestre to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fechamentoBimestre, or with status 404 (Not Found)
     */
    @GetMapping("/fechamento-bimestres/{id}")
    @Timed
    public ResponseEntity<FechamentoBimestre> getFechamentoBimestre(@PathVariable Long id) {
        log.debug("REST request to get FechamentoBimestre : {}", id);
        Optional<FechamentoBimestre> fechamentoBimestre = fechamentoBimestreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fechamentoBimestre);
    }

    /**
     * DELETE  /fechamento-bimestres/:id : delete the "id" fechamentoBimestre.
     *
     * @param id the id of the fechamentoBimestre to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fechamento-bimestres/{id}")
    @Timed
    public ResponseEntity<Void> deleteFechamentoBimestre(@PathVariable Long id) {
        log.debug("REST request to delete FechamentoBimestre : {}", id);
        fechamentoBimestreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
