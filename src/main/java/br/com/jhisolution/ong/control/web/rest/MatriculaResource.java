package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Matricula;
import br.com.jhisolution.ong.control.service.MatriculaService;
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
 * REST controller for managing Matricula.
 */
@RestController
@RequestMapping("/api")
public class MatriculaResource {

    private final Logger log = LoggerFactory.getLogger(MatriculaResource.class);

    private static final String ENTITY_NAME = "matricula";

    private final MatriculaService matriculaService;

    public MatriculaResource(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    /**
     * POST  /matriculas : Create a new matricula.
     *
     * @param matricula the matricula to create
     * @return the ResponseEntity with status 201 (Created) and with body the new matricula, or with status 400 (Bad Request) if the matricula has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/matriculas")
    @Timed
    public ResponseEntity<Matricula> createMatricula(@Valid @RequestBody Matricula matricula) throws URISyntaxException {
        log.debug("REST request to save Matricula : {}", matricula);
        if (matricula.getId() != null) {
            throw new BadRequestAlertException("A new matricula cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Matricula result = matriculaService.save(matricula);
        return ResponseEntity.created(new URI("/api/matriculas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /matriculas : Updates an existing matricula.
     *
     * @param matricula the matricula to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated matricula,
     * or with status 400 (Bad Request) if the matricula is not valid,
     * or with status 500 (Internal Server Error) if the matricula couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/matriculas")
    @Timed
    public ResponseEntity<Matricula> updateMatricula(@Valid @RequestBody Matricula matricula) throws URISyntaxException {
        log.debug("REST request to update Matricula : {}", matricula);
        if (matricula.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Matricula result = matriculaService.save(matricula);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, matricula.getId().toString()))
            .body(result);
    }

    /**
     * GET  /matriculas : get all the matriculas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of matriculas in body
     */
    @GetMapping("/matriculas")
    @Timed
    public ResponseEntity<List<Matricula>> getAllMatriculas(Pageable pageable) {
        log.debug("REST request to get a page of Matriculas");
        Page<Matricula> page = matriculaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/matriculas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /matriculas/:id : get the "id" matricula.
     *
     * @param id the id of the matricula to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the matricula, or with status 404 (Not Found)
     */
    @GetMapping("/matriculas/{id}")
    @Timed
    public ResponseEntity<Matricula> getMatricula(@PathVariable Long id) {
        log.debug("REST request to get Matricula : {}", id);
        Optional<Matricula> matricula = matriculaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(matricula);
    }

    /**
     * DELETE  /matriculas/:id : delete the "id" matricula.
     *
     * @param id the id of the matricula to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/matriculas/{id}")
    @Timed
    public ResponseEntity<Void> deleteMatricula(@PathVariable Long id) {
        log.debug("REST request to delete Matricula : {}", id);
        matriculaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
