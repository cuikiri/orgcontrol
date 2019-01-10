package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Bimestre;
import br.com.jhisolution.ong.control.service.BimestreService;
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
 * REST controller for managing Bimestre.
 */
@RestController
@RequestMapping("/api")
public class BimestreResource {

    private final Logger log = LoggerFactory.getLogger(BimestreResource.class);

    private static final String ENTITY_NAME = "bimestre";

    private final BimestreService bimestreService;

    public BimestreResource(BimestreService bimestreService) {
        this.bimestreService = bimestreService;
    }

    /**
     * POST  /bimestres : Create a new bimestre.
     *
     * @param bimestre the bimestre to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bimestre, or with status 400 (Bad Request) if the bimestre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bimestres")
    @Timed
    public ResponseEntity<Bimestre> createBimestre(@Valid @RequestBody Bimestre bimestre) throws URISyntaxException {
        log.debug("REST request to save Bimestre : {}", bimestre);
        if (bimestre.getId() != null) {
            throw new BadRequestAlertException("A new bimestre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bimestre result = bimestreService.save(bimestre);
        return ResponseEntity.created(new URI("/api/bimestres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bimestres : Updates an existing bimestre.
     *
     * @param bimestre the bimestre to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bimestre,
     * or with status 400 (Bad Request) if the bimestre is not valid,
     * or with status 500 (Internal Server Error) if the bimestre couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bimestres")
    @Timed
    public ResponseEntity<Bimestre> updateBimestre(@Valid @RequestBody Bimestre bimestre) throws URISyntaxException {
        log.debug("REST request to update Bimestre : {}", bimestre);
        if (bimestre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bimestre result = bimestreService.save(bimestre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bimestre.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bimestres : get all the bimestres.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bimestres in body
     */
    @GetMapping("/bimestres")
    @Timed
    public ResponseEntity<List<Bimestre>> getAllBimestres(Pageable pageable) {
        log.debug("REST request to get a page of Bimestres");
        Page<Bimestre> page = bimestreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bimestres");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /bimestres/:id : get the "id" bimestre.
     *
     * @param id the id of the bimestre to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bimestre, or with status 404 (Not Found)
     */
    @GetMapping("/bimestres/{id}")
    @Timed
    public ResponseEntity<Bimestre> getBimestre(@PathVariable Long id) {
        log.debug("REST request to get Bimestre : {}", id);
        Optional<Bimestre> bimestre = bimestreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bimestre);
    }

    /**
     * DELETE  /bimestres/:id : delete the "id" bimestre.
     *
     * @param id the id of the bimestre to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bimestres/{id}")
    @Timed
    public ResponseEntity<Void> deleteBimestre(@PathVariable Long id) {
        log.debug("REST request to delete Bimestre : {}", id);
        bimestreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
