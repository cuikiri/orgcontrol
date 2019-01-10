package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ObservacaoProfessor;
import br.com.jhisolution.ong.control.service.ObservacaoProfessorService;
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
 * REST controller for managing ObservacaoProfessor.
 */
@RestController
@RequestMapping("/api")
public class ObservacaoProfessorResource {

    private final Logger log = LoggerFactory.getLogger(ObservacaoProfessorResource.class);

    private static final String ENTITY_NAME = "observacaoProfessor";

    private final ObservacaoProfessorService observacaoProfessorService;

    public ObservacaoProfessorResource(ObservacaoProfessorService observacaoProfessorService) {
        this.observacaoProfessorService = observacaoProfessorService;
    }

    /**
     * POST  /observacao-professors : Create a new observacaoProfessor.
     *
     * @param observacaoProfessor the observacaoProfessor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new observacaoProfessor, or with status 400 (Bad Request) if the observacaoProfessor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/observacao-professors")
    @Timed
    public ResponseEntity<ObservacaoProfessor> createObservacaoProfessor(@Valid @RequestBody ObservacaoProfessor observacaoProfessor) throws URISyntaxException {
        log.debug("REST request to save ObservacaoProfessor : {}", observacaoProfessor);
        if (observacaoProfessor.getId() != null) {
            throw new BadRequestAlertException("A new observacaoProfessor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObservacaoProfessor result = observacaoProfessorService.save(observacaoProfessor);
        return ResponseEntity.created(new URI("/api/observacao-professors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /observacao-professors : Updates an existing observacaoProfessor.
     *
     * @param observacaoProfessor the observacaoProfessor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated observacaoProfessor,
     * or with status 400 (Bad Request) if the observacaoProfessor is not valid,
     * or with status 500 (Internal Server Error) if the observacaoProfessor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/observacao-professors")
    @Timed
    public ResponseEntity<ObservacaoProfessor> updateObservacaoProfessor(@Valid @RequestBody ObservacaoProfessor observacaoProfessor) throws URISyntaxException {
        log.debug("REST request to update ObservacaoProfessor : {}", observacaoProfessor);
        if (observacaoProfessor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObservacaoProfessor result = observacaoProfessorService.save(observacaoProfessor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, observacaoProfessor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /observacao-professors : get all the observacaoProfessors.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of observacaoProfessors in body
     */
    @GetMapping("/observacao-professors")
    @Timed
    public ResponseEntity<List<ObservacaoProfessor>> getAllObservacaoProfessors(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("diario-is-null".equals(filter)) {
            log.debug("REST request to get all ObservacaoProfessors where diario is null");
            return new ResponseEntity<>(observacaoProfessorService.findAllWhereDiarioIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ObservacaoProfessors");
        Page<ObservacaoProfessor> page = observacaoProfessorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/observacao-professors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /observacao-professors/:id : get the "id" observacaoProfessor.
     *
     * @param id the id of the observacaoProfessor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the observacaoProfessor, or with status 404 (Not Found)
     */
    @GetMapping("/observacao-professors/{id}")
    @Timed
    public ResponseEntity<ObservacaoProfessor> getObservacaoProfessor(@PathVariable Long id) {
        log.debug("REST request to get ObservacaoProfessor : {}", id);
        Optional<ObservacaoProfessor> observacaoProfessor = observacaoProfessorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(observacaoProfessor);
    }

    /**
     * DELETE  /observacao-professors/:id : delete the "id" observacaoProfessor.
     *
     * @param id the id of the observacaoProfessor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/observacao-professors/{id}")
    @Timed
    public ResponseEntity<Void> deleteObservacaoProfessor(@PathVariable Long id) {
        log.debug("REST request to delete ObservacaoProfessor : {}", id);
        observacaoProfessorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
