package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Biotipo;
import br.com.jhisolution.ong.control.service.BiotipoService;
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
 * REST controller for managing Biotipo.
 */
@RestController
@RequestMapping("/api")
public class BiotipoResource {

    private final Logger log = LoggerFactory.getLogger(BiotipoResource.class);

    private static final String ENTITY_NAME = "biotipo";

    private final BiotipoService biotipoService;

    public BiotipoResource(BiotipoService biotipoService) {
        this.biotipoService = biotipoService;
    }

    /**
     * POST  /biotipos : Create a new biotipo.
     *
     * @param biotipo the biotipo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new biotipo, or with status 400 (Bad Request) if the biotipo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/biotipos")
    @Timed
    public ResponseEntity<Biotipo> createBiotipo(@Valid @RequestBody Biotipo biotipo) throws URISyntaxException {
        log.debug("REST request to save Biotipo : {}", biotipo);
        if (biotipo.getId() != null) {
            throw new BadRequestAlertException("A new biotipo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Biotipo result = biotipoService.save(biotipo);
        return ResponseEntity.created(new URI("/api/biotipos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /biotipos : Updates an existing biotipo.
     *
     * @param biotipo the biotipo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated biotipo,
     * or with status 400 (Bad Request) if the biotipo is not valid,
     * or with status 500 (Internal Server Error) if the biotipo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/biotipos")
    @Timed
    public ResponseEntity<Biotipo> updateBiotipo(@Valid @RequestBody Biotipo biotipo) throws URISyntaxException {
        log.debug("REST request to update Biotipo : {}", biotipo);
        if (biotipo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Biotipo result = biotipoService.save(biotipo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, biotipo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /biotipos : get all the biotipos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of biotipos in body
     */
    @GetMapping("/biotipos")
    @Timed
    public ResponseEntity<List<Biotipo>> getAllBiotipos(Pageable pageable) {
        log.debug("REST request to get a page of Biotipos");
        Page<Biotipo> page = biotipoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/biotipos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /biotipos/:id : get the "id" biotipo.
     *
     * @param id the id of the biotipo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the biotipo, or with status 404 (Not Found)
     */
    @GetMapping("/biotipos/{id}")
    @Timed
    public ResponseEntity<Biotipo> getBiotipo(@PathVariable Long id) {
        log.debug("REST request to get Biotipo : {}", id);
        Optional<Biotipo> biotipo = biotipoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(biotipo);
    }

    /**
     * DELETE  /biotipos/:id : delete the "id" biotipo.
     *
     * @param id the id of the biotipo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/biotipos/{id}")
    @Timed
    public ResponseEntity<Void> deleteBiotipo(@PathVariable Long id) {
        log.debug("REST request to delete Biotipo : {}", id);
        biotipoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
