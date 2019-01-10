package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.MateriaAcompanhamento;
import br.com.jhisolution.ong.control.service.MateriaAcompanhamentoService;
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
 * REST controller for managing MateriaAcompanhamento.
 */
@RestController
@RequestMapping("/api")
public class MateriaAcompanhamentoResource {

    private final Logger log = LoggerFactory.getLogger(MateriaAcompanhamentoResource.class);

    private static final String ENTITY_NAME = "materiaAcompanhamento";

    private final MateriaAcompanhamentoService materiaAcompanhamentoService;

    public MateriaAcompanhamentoResource(MateriaAcompanhamentoService materiaAcompanhamentoService) {
        this.materiaAcompanhamentoService = materiaAcompanhamentoService;
    }

    /**
     * POST  /materia-acompanhamentos : Create a new materiaAcompanhamento.
     *
     * @param materiaAcompanhamento the materiaAcompanhamento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new materiaAcompanhamento, or with status 400 (Bad Request) if the materiaAcompanhamento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/materia-acompanhamentos")
    @Timed
    public ResponseEntity<MateriaAcompanhamento> createMateriaAcompanhamento(@Valid @RequestBody MateriaAcompanhamento materiaAcompanhamento) throws URISyntaxException {
        log.debug("REST request to save MateriaAcompanhamento : {}", materiaAcompanhamento);
        if (materiaAcompanhamento.getId() != null) {
            throw new BadRequestAlertException("A new materiaAcompanhamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MateriaAcompanhamento result = materiaAcompanhamentoService.save(materiaAcompanhamento);
        return ResponseEntity.created(new URI("/api/materia-acompanhamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /materia-acompanhamentos : Updates an existing materiaAcompanhamento.
     *
     * @param materiaAcompanhamento the materiaAcompanhamento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated materiaAcompanhamento,
     * or with status 400 (Bad Request) if the materiaAcompanhamento is not valid,
     * or with status 500 (Internal Server Error) if the materiaAcompanhamento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/materia-acompanhamentos")
    @Timed
    public ResponseEntity<MateriaAcompanhamento> updateMateriaAcompanhamento(@Valid @RequestBody MateriaAcompanhamento materiaAcompanhamento) throws URISyntaxException {
        log.debug("REST request to update MateriaAcompanhamento : {}", materiaAcompanhamento);
        if (materiaAcompanhamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MateriaAcompanhamento result = materiaAcompanhamentoService.save(materiaAcompanhamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, materiaAcompanhamento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /materia-acompanhamentos : get all the materiaAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of materiaAcompanhamentos in body
     */
    @GetMapping("/materia-acompanhamentos")
    @Timed
    public ResponseEntity<List<MateriaAcompanhamento>> getAllMateriaAcompanhamentos(Pageable pageable) {
        log.debug("REST request to get a page of MateriaAcompanhamentos");
        Page<MateriaAcompanhamento> page = materiaAcompanhamentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/materia-acompanhamentos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /materia-acompanhamentos/:id : get the "id" materiaAcompanhamento.
     *
     * @param id the id of the materiaAcompanhamento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the materiaAcompanhamento, or with status 404 (Not Found)
     */
    @GetMapping("/materia-acompanhamentos/{id}")
    @Timed
    public ResponseEntity<MateriaAcompanhamento> getMateriaAcompanhamento(@PathVariable Long id) {
        log.debug("REST request to get MateriaAcompanhamento : {}", id);
        Optional<MateriaAcompanhamento> materiaAcompanhamento = materiaAcompanhamentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(materiaAcompanhamento);
    }

    /**
     * DELETE  /materia-acompanhamentos/:id : delete the "id" materiaAcompanhamento.
     *
     * @param id the id of the materiaAcompanhamento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/materia-acompanhamentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMateriaAcompanhamento(@PathVariable Long id) {
        log.debug("REST request to delete MateriaAcompanhamento : {}", id);
        materiaAcompanhamentoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
