package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Instituicao;
import br.com.jhisolution.ong.control.service.InstituicaoService;
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
 * REST controller for managing Instituicao.
 */
@RestController
@RequestMapping("/api")
public class InstituicaoResource {

    private final Logger log = LoggerFactory.getLogger(InstituicaoResource.class);

    private static final String ENTITY_NAME = "instituicao";

    private final InstituicaoService instituicaoService;

    public InstituicaoResource(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    /**
     * POST  /instituicaos : Create a new instituicao.
     *
     * @param instituicao the instituicao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instituicao, or with status 400 (Bad Request) if the instituicao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instituicaos")
    @Timed
    public ResponseEntity<Instituicao> createInstituicao(@Valid @RequestBody Instituicao instituicao) throws URISyntaxException {
        log.debug("REST request to save Instituicao : {}", instituicao);
        if (instituicao.getId() != null) {
            throw new BadRequestAlertException("A new instituicao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Instituicao result = instituicaoService.save(instituicao);
        return ResponseEntity.created(new URI("/api/instituicaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instituicaos : Updates an existing instituicao.
     *
     * @param instituicao the instituicao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instituicao,
     * or with status 400 (Bad Request) if the instituicao is not valid,
     * or with status 500 (Internal Server Error) if the instituicao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instituicaos")
    @Timed
    public ResponseEntity<Instituicao> updateInstituicao(@Valid @RequestBody Instituicao instituicao) throws URISyntaxException {
        log.debug("REST request to update Instituicao : {}", instituicao);
        if (instituicao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Instituicao result = instituicaoService.save(instituicao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instituicao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instituicaos : get all the instituicaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of instituicaos in body
     */
    @GetMapping("/instituicaos")
    @Timed
    public ResponseEntity<List<Instituicao>> getAllInstituicaos(Pageable pageable) {
        log.debug("REST request to get a page of Instituicaos");
        Page<Instituicao> page = instituicaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/instituicaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /instituicaos/:id : get the "id" instituicao.
     *
     * @param id the id of the instituicao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instituicao, or with status 404 (Not Found)
     */
    @GetMapping("/instituicaos/{id}")
    @Timed
    public ResponseEntity<Instituicao> getInstituicao(@PathVariable Long id) {
        log.debug("REST request to get Instituicao : {}", id);
        Optional<Instituicao> instituicao = instituicaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instituicao);
    }

    /**
     * DELETE  /instituicaos/:id : delete the "id" instituicao.
     *
     * @param id the id of the instituicao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instituicaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstituicao(@PathVariable Long id) {
        log.debug("REST request to delete Instituicao : {}", id);
        instituicaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
