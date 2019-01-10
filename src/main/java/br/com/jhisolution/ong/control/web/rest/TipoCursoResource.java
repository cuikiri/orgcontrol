package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoCurso;
import br.com.jhisolution.ong.control.service.TipoCursoService;
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
 * REST controller for managing TipoCurso.
 */
@RestController
@RequestMapping("/api")
public class TipoCursoResource {

    private final Logger log = LoggerFactory.getLogger(TipoCursoResource.class);

    private static final String ENTITY_NAME = "tipoCurso";

    private final TipoCursoService tipoCursoService;

    public TipoCursoResource(TipoCursoService tipoCursoService) {
        this.tipoCursoService = tipoCursoService;
    }

    /**
     * POST  /tipo-cursos : Create a new tipoCurso.
     *
     * @param tipoCurso the tipoCurso to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoCurso, or with status 400 (Bad Request) if the tipoCurso has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-cursos")
    @Timed
    public ResponseEntity<TipoCurso> createTipoCurso(@Valid @RequestBody TipoCurso tipoCurso) throws URISyntaxException {
        log.debug("REST request to save TipoCurso : {}", tipoCurso);
        if (tipoCurso.getId() != null) {
            throw new BadRequestAlertException("A new tipoCurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoCurso result = tipoCursoService.save(tipoCurso);
        return ResponseEntity.created(new URI("/api/tipo-cursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-cursos : Updates an existing tipoCurso.
     *
     * @param tipoCurso the tipoCurso to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoCurso,
     * or with status 400 (Bad Request) if the tipoCurso is not valid,
     * or with status 500 (Internal Server Error) if the tipoCurso couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-cursos")
    @Timed
    public ResponseEntity<TipoCurso> updateTipoCurso(@Valid @RequestBody TipoCurso tipoCurso) throws URISyntaxException {
        log.debug("REST request to update TipoCurso : {}", tipoCurso);
        if (tipoCurso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoCurso result = tipoCursoService.save(tipoCurso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoCurso.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-cursos : get all the tipoCursos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tipoCursos in body
     */
    @GetMapping("/tipo-cursos")
    @Timed
    public ResponseEntity<List<TipoCurso>> getAllTipoCursos(Pageable pageable) {
        log.debug("REST request to get a page of TipoCursos");
        Page<TipoCurso> page = tipoCursoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-cursos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-cursos/:id : get the "id" tipoCurso.
     *
     * @param id the id of the tipoCurso to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoCurso, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-cursos/{id}")
    @Timed
    public ResponseEntity<TipoCurso> getTipoCurso(@PathVariable Long id) {
        log.debug("REST request to get TipoCurso : {}", id);
        Optional<TipoCurso> tipoCurso = tipoCursoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoCurso);
    }

    /**
     * DELETE  /tipo-cursos/:id : delete the "id" tipoCurso.
     *
     * @param id the id of the tipoCurso to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-cursos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoCurso(@PathVariable Long id) {
        log.debug("REST request to delete TipoCurso : {}", id);
        tipoCursoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
