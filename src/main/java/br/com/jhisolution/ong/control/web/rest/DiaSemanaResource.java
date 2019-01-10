package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.DiaSemana;
import br.com.jhisolution.ong.control.service.DiaSemanaService;
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
 * REST controller for managing DiaSemana.
 */
@RestController
@RequestMapping("/api")
public class DiaSemanaResource {

    private final Logger log = LoggerFactory.getLogger(DiaSemanaResource.class);

    private static final String ENTITY_NAME = "diaSemana";

    private final DiaSemanaService diaSemanaService;

    public DiaSemanaResource(DiaSemanaService diaSemanaService) {
        this.diaSemanaService = diaSemanaService;
    }

    /**
     * POST  /dia-semanas : Create a new diaSemana.
     *
     * @param diaSemana the diaSemana to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diaSemana, or with status 400 (Bad Request) if the diaSemana has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dia-semanas")
    @Timed
    public ResponseEntity<DiaSemana> createDiaSemana(@Valid @RequestBody DiaSemana diaSemana) throws URISyntaxException {
        log.debug("REST request to save DiaSemana : {}", diaSemana);
        if (diaSemana.getId() != null) {
            throw new BadRequestAlertException("A new diaSemana cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiaSemana result = diaSemanaService.save(diaSemana);
        return ResponseEntity.created(new URI("/api/dia-semanas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dia-semanas : Updates an existing diaSemana.
     *
     * @param diaSemana the diaSemana to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diaSemana,
     * or with status 400 (Bad Request) if the diaSemana is not valid,
     * or with status 500 (Internal Server Error) if the diaSemana couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dia-semanas")
    @Timed
    public ResponseEntity<DiaSemana> updateDiaSemana(@Valid @RequestBody DiaSemana diaSemana) throws URISyntaxException {
        log.debug("REST request to update DiaSemana : {}", diaSemana);
        if (diaSemana.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiaSemana result = diaSemanaService.save(diaSemana);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diaSemana.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dia-semanas : get all the diaSemanas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of diaSemanas in body
     */
    @GetMapping("/dia-semanas")
    @Timed
    public ResponseEntity<List<DiaSemana>> getAllDiaSemanas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("horariomateria-is-null".equals(filter)) {
            log.debug("REST request to get all DiaSemanas where horarioMateria is null");
            return new ResponseEntity<>(diaSemanaService.findAllWhereHorarioMateriaIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of DiaSemanas");
        Page<DiaSemana> page = diaSemanaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dia-semanas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dia-semanas/:id : get the "id" diaSemana.
     *
     * @param id the id of the diaSemana to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diaSemana, or with status 404 (Not Found)
     */
    @GetMapping("/dia-semanas/{id}")
    @Timed
    public ResponseEntity<DiaSemana> getDiaSemana(@PathVariable Long id) {
        log.debug("REST request to get DiaSemana : {}", id);
        Optional<DiaSemana> diaSemana = diaSemanaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diaSemana);
    }

    /**
     * DELETE  /dia-semanas/:id : delete the "id" diaSemana.
     *
     * @param id the id of the diaSemana to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dia-semanas/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiaSemana(@PathVariable Long id) {
        log.debug("REST request to delete DiaSemana : {}", id);
        diaSemanaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
