package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ExameMedico;
import br.com.jhisolution.ong.control.service.ExameMedicoService;
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
 * REST controller for managing ExameMedico.
 */
@RestController
@RequestMapping("/api")
public class ExameMedicoResource {

    private final Logger log = LoggerFactory.getLogger(ExameMedicoResource.class);

    private static final String ENTITY_NAME = "exameMedico";

    private final ExameMedicoService exameMedicoService;

    public ExameMedicoResource(ExameMedicoService exameMedicoService) {
        this.exameMedicoService = exameMedicoService;
    }

    /**
     * POST  /exame-medicos : Create a new exameMedico.
     *
     * @param exameMedico the exameMedico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new exameMedico, or with status 400 (Bad Request) if the exameMedico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/exame-medicos")
    @Timed
    public ResponseEntity<ExameMedico> createExameMedico(@Valid @RequestBody ExameMedico exameMedico) throws URISyntaxException {
        log.debug("REST request to save ExameMedico : {}", exameMedico);
        if (exameMedico.getId() != null) {
            throw new BadRequestAlertException("A new exameMedico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExameMedico result = exameMedicoService.save(exameMedico);
        return ResponseEntity.created(new URI("/api/exame-medicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /exame-medicos : Updates an existing exameMedico.
     *
     * @param exameMedico the exameMedico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated exameMedico,
     * or with status 400 (Bad Request) if the exameMedico is not valid,
     * or with status 500 (Internal Server Error) if the exameMedico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/exame-medicos")
    @Timed
    public ResponseEntity<ExameMedico> updateExameMedico(@Valid @RequestBody ExameMedico exameMedico) throws URISyntaxException {
        log.debug("REST request to update ExameMedico : {}", exameMedico);
        if (exameMedico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExameMedico result = exameMedicoService.save(exameMedico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, exameMedico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /exame-medicos : get all the exameMedicos.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of exameMedicos in body
     */
    @GetMapping("/exame-medicos")
    @Timed
    public ResponseEntity<List<ExameMedico>> getAllExameMedicos(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of ExameMedicos");
        Page<ExameMedico> page;
        if (eagerload) {
            page = exameMedicoService.findAllWithEagerRelationships(pageable);
        } else {
            page = exameMedicoService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/exame-medicos?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /exame-medicos/:id : get the "id" exameMedico.
     *
     * @param id the id of the exameMedico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the exameMedico, or with status 404 (Not Found)
     */
    @GetMapping("/exame-medicos/{id}")
    @Timed
    public ResponseEntity<ExameMedico> getExameMedico(@PathVariable Long id) {
        log.debug("REST request to get ExameMedico : {}", id);
        Optional<ExameMedico> exameMedico = exameMedicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exameMedico);
    }

    /**
     * DELETE  /exame-medicos/:id : delete the "id" exameMedico.
     *
     * @param id the id of the exameMedico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/exame-medicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteExameMedico(@PathVariable Long id) {
        log.debug("REST request to delete ExameMedico : {}", id);
        exameMedicoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
