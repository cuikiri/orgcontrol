package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Advertencia;
import br.com.jhisolution.ong.control.service.AdvertenciaService;
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
 * REST controller for managing Advertencia.
 */
@RestController
@RequestMapping("/api")
public class AdvertenciaResource {

    private final Logger log = LoggerFactory.getLogger(AdvertenciaResource.class);

    private static final String ENTITY_NAME = "advertencia";

    private final AdvertenciaService advertenciaService;

    public AdvertenciaResource(AdvertenciaService advertenciaService) {
        this.advertenciaService = advertenciaService;
    }

    /**
     * POST  /advertencias : Create a new advertencia.
     *
     * @param advertencia the advertencia to create
     * @return the ResponseEntity with status 201 (Created) and with body the new advertencia, or with status 400 (Bad Request) if the advertencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/advertencias")
    @Timed
    public ResponseEntity<Advertencia> createAdvertencia(@Valid @RequestBody Advertencia advertencia) throws URISyntaxException {
        log.debug("REST request to save Advertencia : {}", advertencia);
        if (advertencia.getId() != null) {
            throw new BadRequestAlertException("A new advertencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Advertencia result = advertenciaService.save(advertencia);
        return ResponseEntity.created(new URI("/api/advertencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /advertencias : Updates an existing advertencia.
     *
     * @param advertencia the advertencia to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated advertencia,
     * or with status 400 (Bad Request) if the advertencia is not valid,
     * or with status 500 (Internal Server Error) if the advertencia couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/advertencias")
    @Timed
    public ResponseEntity<Advertencia> updateAdvertencia(@Valid @RequestBody Advertencia advertencia) throws URISyntaxException {
        log.debug("REST request to update Advertencia : {}", advertencia);
        if (advertencia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Advertencia result = advertenciaService.save(advertencia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, advertencia.getId().toString()))
            .body(result);
    }

    /**
     * GET  /advertencias : get all the advertencias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of advertencias in body
     */
    @GetMapping("/advertencias")
    @Timed
    public ResponseEntity<List<Advertencia>> getAllAdvertencias(Pageable pageable) {
        log.debug("REST request to get a page of Advertencias");
        Page<Advertencia> page = advertenciaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/advertencias");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /advertencias/:id : get the "id" advertencia.
     *
     * @param id the id of the advertencia to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the advertencia, or with status 404 (Not Found)
     */
    @GetMapping("/advertencias/{id}")
    @Timed
    public ResponseEntity<Advertencia> getAdvertencia(@PathVariable Long id) {
        log.debug("REST request to get Advertencia : {}", id);
        Optional<Advertencia> advertencia = advertenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(advertencia);
    }

    /**
     * DELETE  /advertencias/:id : delete the "id" advertencia.
     *
     * @param id the id of the advertencia to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/advertencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdvertencia(@PathVariable Long id) {
        log.debug("REST request to delete Advertencia : {}", id);
        advertenciaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
