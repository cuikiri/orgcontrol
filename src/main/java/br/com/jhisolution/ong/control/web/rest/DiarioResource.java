package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Diario;
import br.com.jhisolution.ong.control.service.DiarioService;
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
 * REST controller for managing Diario.
 */
@RestController
@RequestMapping("/api")
public class DiarioResource {

    private final Logger log = LoggerFactory.getLogger(DiarioResource.class);

    private static final String ENTITY_NAME = "diario";

    private final DiarioService diarioService;

    public DiarioResource(DiarioService diarioService) {
        this.diarioService = diarioService;
    }

    /**
     * POST  /diarios : Create a new diario.
     *
     * @param diario the diario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diario, or with status 400 (Bad Request) if the diario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/diarios")
    @Timed
    public ResponseEntity<Diario> createDiario(@Valid @RequestBody Diario diario) throws URISyntaxException {
        log.debug("REST request to save Diario : {}", diario);
        if (diario.getId() != null) {
            throw new BadRequestAlertException("A new diario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Diario result = diarioService.save(diario);
        return ResponseEntity.created(new URI("/api/diarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /diarios : Updates an existing diario.
     *
     * @param diario the diario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diario,
     * or with status 400 (Bad Request) if the diario is not valid,
     * or with status 500 (Internal Server Error) if the diario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/diarios")
    @Timed
    public ResponseEntity<Diario> updateDiario(@Valid @RequestBody Diario diario) throws URISyntaxException {
        log.debug("REST request to update Diario : {}", diario);
        if (diario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Diario result = diarioService.save(diario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diario.getId().toString()))
            .body(result);
    }

    /**
     * GET  /diarios : get all the diarios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of diarios in body
     */
    @GetMapping("/diarios")
    @Timed
    public ResponseEntity<List<Diario>> getAllDiarios(Pageable pageable) {
        log.debug("REST request to get a page of Diarios");
        Page<Diario> page = diarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/diarios");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /diarios/:id : get the "id" diario.
     *
     * @param id the id of the diario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diario, or with status 404 (Not Found)
     */
    @GetMapping("/diarios/{id}")
    @Timed
    public ResponseEntity<Diario> getDiario(@PathVariable Long id) {
        log.debug("REST request to get Diario : {}", id);
        Optional<Diario> diario = diarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diario);
    }

    /**
     * DELETE  /diarios/:id : delete the "id" diario.
     *
     * @param id the id of the diario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/diarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiario(@PathVariable Long id) {
        log.debug("REST request to delete Diario : {}", id);
        diarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
