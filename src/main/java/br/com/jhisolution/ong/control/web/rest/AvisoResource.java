package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Aviso;
import br.com.jhisolution.ong.control.service.AvisoService;
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
 * REST controller for managing Aviso.
 */
@RestController
@RequestMapping("/api")
public class AvisoResource {

    private final Logger log = LoggerFactory.getLogger(AvisoResource.class);

    private static final String ENTITY_NAME = "aviso";

    private final AvisoService avisoService;

    public AvisoResource(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    /**
     * POST  /avisos : Create a new aviso.
     *
     * @param aviso the aviso to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aviso, or with status 400 (Bad Request) if the aviso has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/avisos")
    @Timed
    public ResponseEntity<Aviso> createAviso(@Valid @RequestBody Aviso aviso) throws URISyntaxException {
        log.debug("REST request to save Aviso : {}", aviso);
        if (aviso.getId() != null) {
            throw new BadRequestAlertException("A new aviso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Aviso result = avisoService.save(aviso);
        return ResponseEntity.created(new URI("/api/avisos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /avisos : Updates an existing aviso.
     *
     * @param aviso the aviso to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aviso,
     * or with status 400 (Bad Request) if the aviso is not valid,
     * or with status 500 (Internal Server Error) if the aviso couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/avisos")
    @Timed
    public ResponseEntity<Aviso> updateAviso(@Valid @RequestBody Aviso aviso) throws URISyntaxException {
        log.debug("REST request to update Aviso : {}", aviso);
        if (aviso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Aviso result = avisoService.save(aviso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aviso.getId().toString()))
            .body(result);
    }

    /**
     * GET  /avisos : get all the avisos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of avisos in body
     */
    @GetMapping("/avisos")
    @Timed
    public ResponseEntity<List<Aviso>> getAllAvisos(Pageable pageable) {
        log.debug("REST request to get a page of Avisos");
        Page<Aviso> page = avisoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/avisos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /avisos/:id : get the "id" aviso.
     *
     * @param id the id of the aviso to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aviso, or with status 404 (Not Found)
     */
    @GetMapping("/avisos/{id}")
    @Timed
    public ResponseEntity<Aviso> getAviso(@PathVariable Long id) {
        log.debug("REST request to get Aviso : {}", id);
        Optional<Aviso> aviso = avisoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aviso);
    }

    /**
     * DELETE  /avisos/:id : delete the "id" aviso.
     *
     * @param id the id of the aviso to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/avisos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAviso(@PathVariable Long id) {
        log.debug("REST request to delete Aviso : {}", id);
        avisoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
