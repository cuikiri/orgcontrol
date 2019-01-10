package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Turma;
import br.com.jhisolution.ong.control.service.TurmaService;
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
 * REST controller for managing Turma.
 */
@RestController
@RequestMapping("/api")
public class TurmaResource {

    private final Logger log = LoggerFactory.getLogger(TurmaResource.class);

    private static final String ENTITY_NAME = "turma";

    private final TurmaService turmaService;

    public TurmaResource(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    /**
     * POST  /turmas : Create a new turma.
     *
     * @param turma the turma to create
     * @return the ResponseEntity with status 201 (Created) and with body the new turma, or with status 400 (Bad Request) if the turma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/turmas")
    @Timed
    public ResponseEntity<Turma> createTurma(@Valid @RequestBody Turma turma) throws URISyntaxException {
        log.debug("REST request to save Turma : {}", turma);
        if (turma.getId() != null) {
            throw new BadRequestAlertException("A new turma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Turma result = turmaService.save(turma);
        return ResponseEntity.created(new URI("/api/turmas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /turmas : Updates an existing turma.
     *
     * @param turma the turma to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated turma,
     * or with status 400 (Bad Request) if the turma is not valid,
     * or with status 500 (Internal Server Error) if the turma couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/turmas")
    @Timed
    public ResponseEntity<Turma> updateTurma(@Valid @RequestBody Turma turma) throws URISyntaxException {
        log.debug("REST request to update Turma : {}", turma);
        if (turma.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Turma result = turmaService.save(turma);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, turma.getId().toString()))
            .body(result);
    }

    /**
     * GET  /turmas : get all the turmas.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of turmas in body
     */
    @GetMapping("/turmas")
    @Timed
    public ResponseEntity<List<Turma>> getAllTurmas(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Turmas");
        Page<Turma> page;
        if (eagerload) {
            page = turmaService.findAllWithEagerRelationships(pageable);
        } else {
            page = turmaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/turmas?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /turmas/:id : get the "id" turma.
     *
     * @param id the id of the turma to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the turma, or with status 404 (Not Found)
     */
    @GetMapping("/turmas/{id}")
    @Timed
    public ResponseEntity<Turma> getTurma(@PathVariable Long id) {
        log.debug("REST request to get Turma : {}", id);
        Optional<Turma> turma = turmaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(turma);
    }

    /**
     * DELETE  /turmas/:id : delete the "id" turma.
     *
     * @param id the id of the turma to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/turmas/{id}")
    @Timed
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        log.debug("REST request to delete Turma : {}", id);
        turmaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
