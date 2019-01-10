package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Conceito;
import br.com.jhisolution.ong.control.service.ConceitoService;
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
 * REST controller for managing Conceito.
 */
@RestController
@RequestMapping("/api")
public class ConceitoResource {

    private final Logger log = LoggerFactory.getLogger(ConceitoResource.class);

    private static final String ENTITY_NAME = "conceito";

    private final ConceitoService conceitoService;

    public ConceitoResource(ConceitoService conceitoService) {
        this.conceitoService = conceitoService;
    }

    /**
     * POST  /conceitos : Create a new conceito.
     *
     * @param conceito the conceito to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conceito, or with status 400 (Bad Request) if the conceito has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conceitos")
    @Timed
    public ResponseEntity<Conceito> createConceito(@Valid @RequestBody Conceito conceito) throws URISyntaxException {
        log.debug("REST request to save Conceito : {}", conceito);
        if (conceito.getId() != null) {
            throw new BadRequestAlertException("A new conceito cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Conceito result = conceitoService.save(conceito);
        return ResponseEntity.created(new URI("/api/conceitos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conceitos : Updates an existing conceito.
     *
     * @param conceito the conceito to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conceito,
     * or with status 400 (Bad Request) if the conceito is not valid,
     * or with status 500 (Internal Server Error) if the conceito couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conceitos")
    @Timed
    public ResponseEntity<Conceito> updateConceito(@Valid @RequestBody Conceito conceito) throws URISyntaxException {
        log.debug("REST request to update Conceito : {}", conceito);
        if (conceito.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Conceito result = conceitoService.save(conceito);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conceito.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conceitos : get all the conceitos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of conceitos in body
     */
    @GetMapping("/conceitos")
    @Timed
    public ResponseEntity<List<Conceito>> getAllConceitos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("fechamentobimestre-is-null".equals(filter)) {
            log.debug("REST request to get all Conceitos where fechamentoBimestre is null");
            return new ResponseEntity<>(conceitoService.findAllWhereFechamentoBimestreIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Conceitos");
        Page<Conceito> page = conceitoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conceitos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /conceitos/:id : get the "id" conceito.
     *
     * @param id the id of the conceito to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conceito, or with status 404 (Not Found)
     */
    @GetMapping("/conceitos/{id}")
    @Timed
    public ResponseEntity<Conceito> getConceito(@PathVariable Long id) {
        log.debug("REST request to get Conceito : {}", id);
        Optional<Conceito> conceito = conceitoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conceito);
    }

    /**
     * DELETE  /conceitos/:id : delete the "id" conceito.
     *
     * @param id the id of the conceito to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conceitos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConceito(@PathVariable Long id) {
        log.debug("REST request to delete Conceito : {}", id);
        conceitoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
