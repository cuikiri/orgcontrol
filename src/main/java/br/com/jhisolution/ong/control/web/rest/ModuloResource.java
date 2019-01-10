package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Modulo;
import br.com.jhisolution.ong.control.service.ModuloService;
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
 * REST controller for managing Modulo.
 */
@RestController
@RequestMapping("/api")
public class ModuloResource {

    private final Logger log = LoggerFactory.getLogger(ModuloResource.class);

    private static final String ENTITY_NAME = "modulo";

    private final ModuloService moduloService;

    public ModuloResource(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    /**
     * POST  /modulos : Create a new modulo.
     *
     * @param modulo the modulo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new modulo, or with status 400 (Bad Request) if the modulo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/modulos")
    @Timed
    public ResponseEntity<Modulo> createModulo(@Valid @RequestBody Modulo modulo) throws URISyntaxException {
        log.debug("REST request to save Modulo : {}", modulo);
        if (modulo.getId() != null) {
            throw new BadRequestAlertException("A new modulo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Modulo result = moduloService.save(modulo);
        return ResponseEntity.created(new URI("/api/modulos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /modulos : Updates an existing modulo.
     *
     * @param modulo the modulo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated modulo,
     * or with status 400 (Bad Request) if the modulo is not valid,
     * or with status 500 (Internal Server Error) if the modulo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/modulos")
    @Timed
    public ResponseEntity<Modulo> updateModulo(@Valid @RequestBody Modulo modulo) throws URISyntaxException {
        log.debug("REST request to update Modulo : {}", modulo);
        if (modulo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Modulo result = moduloService.save(modulo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, modulo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /modulos : get all the modulos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of modulos in body
     */
    @GetMapping("/modulos")
    @Timed
    public ResponseEntity<List<Modulo>> getAllModulos(Pageable pageable) {
        log.debug("REST request to get a page of Modulos");
        Page<Modulo> page = moduloService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/modulos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /modulos/:id : get the "id" modulo.
     *
     * @param id the id of the modulo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the modulo, or with status 404 (Not Found)
     */
    @GetMapping("/modulos/{id}")
    @Timed
    public ResponseEntity<Modulo> getModulo(@PathVariable Long id) {
        log.debug("REST request to get Modulo : {}", id);
        Optional<Modulo> modulo = moduloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modulo);
    }

    /**
     * DELETE  /modulos/:id : delete the "id" modulo.
     *
     * @param id the id of the modulo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/modulos/{id}")
    @Timed
    public ResponseEntity<Void> deleteModulo(@PathVariable Long id) {
        log.debug("REST request to delete Modulo : {}", id);
        moduloService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
