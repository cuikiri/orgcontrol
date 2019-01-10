package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.AcompanhamentoEscolarAluno;
import br.com.jhisolution.ong.control.service.AcompanhamentoEscolarAlunoService;
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
 * REST controller for managing AcompanhamentoEscolarAluno.
 */
@RestController
@RequestMapping("/api")
public class AcompanhamentoEscolarAlunoResource {

    private final Logger log = LoggerFactory.getLogger(AcompanhamentoEscolarAlunoResource.class);

    private static final String ENTITY_NAME = "acompanhamentoEscolarAluno";

    private final AcompanhamentoEscolarAlunoService acompanhamentoEscolarAlunoService;

    public AcompanhamentoEscolarAlunoResource(AcompanhamentoEscolarAlunoService acompanhamentoEscolarAlunoService) {
        this.acompanhamentoEscolarAlunoService = acompanhamentoEscolarAlunoService;
    }

    /**
     * POST  /acompanhamento-escolar-alunos : Create a new acompanhamentoEscolarAluno.
     *
     * @param acompanhamentoEscolarAluno the acompanhamentoEscolarAluno to create
     * @return the ResponseEntity with status 201 (Created) and with body the new acompanhamentoEscolarAluno, or with status 400 (Bad Request) if the acompanhamentoEscolarAluno has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acompanhamento-escolar-alunos")
    @Timed
    public ResponseEntity<AcompanhamentoEscolarAluno> createAcompanhamentoEscolarAluno(@Valid @RequestBody AcompanhamentoEscolarAluno acompanhamentoEscolarAluno) throws URISyntaxException {
        log.debug("REST request to save AcompanhamentoEscolarAluno : {}", acompanhamentoEscolarAluno);
        if (acompanhamentoEscolarAluno.getId() != null) {
            throw new BadRequestAlertException("A new acompanhamentoEscolarAluno cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcompanhamentoEscolarAluno result = acompanhamentoEscolarAlunoService.save(acompanhamentoEscolarAluno);
        return ResponseEntity.created(new URI("/api/acompanhamento-escolar-alunos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acompanhamento-escolar-alunos : Updates an existing acompanhamentoEscolarAluno.
     *
     * @param acompanhamentoEscolarAluno the acompanhamentoEscolarAluno to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated acompanhamentoEscolarAluno,
     * or with status 400 (Bad Request) if the acompanhamentoEscolarAluno is not valid,
     * or with status 500 (Internal Server Error) if the acompanhamentoEscolarAluno couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acompanhamento-escolar-alunos")
    @Timed
    public ResponseEntity<AcompanhamentoEscolarAluno> updateAcompanhamentoEscolarAluno(@Valid @RequestBody AcompanhamentoEscolarAluno acompanhamentoEscolarAluno) throws URISyntaxException {
        log.debug("REST request to update AcompanhamentoEscolarAluno : {}", acompanhamentoEscolarAluno);
        if (acompanhamentoEscolarAluno.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcompanhamentoEscolarAluno result = acompanhamentoEscolarAlunoService.save(acompanhamentoEscolarAluno);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, acompanhamentoEscolarAluno.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acompanhamento-escolar-alunos : get all the acompanhamentoEscolarAlunos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of acompanhamentoEscolarAlunos in body
     */
    @GetMapping("/acompanhamento-escolar-alunos")
    @Timed
    public ResponseEntity<List<AcompanhamentoEscolarAluno>> getAllAcompanhamentoEscolarAlunos(Pageable pageable) {
        log.debug("REST request to get a page of AcompanhamentoEscolarAlunos");
        Page<AcompanhamentoEscolarAluno> page = acompanhamentoEscolarAlunoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acompanhamento-escolar-alunos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /acompanhamento-escolar-alunos/:id : get the "id" acompanhamentoEscolarAluno.
     *
     * @param id the id of the acompanhamentoEscolarAluno to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the acompanhamentoEscolarAluno, or with status 404 (Not Found)
     */
    @GetMapping("/acompanhamento-escolar-alunos/{id}")
    @Timed
    public ResponseEntity<AcompanhamentoEscolarAluno> getAcompanhamentoEscolarAluno(@PathVariable Long id) {
        log.debug("REST request to get AcompanhamentoEscolarAluno : {}", id);
        Optional<AcompanhamentoEscolarAluno> acompanhamentoEscolarAluno = acompanhamentoEscolarAlunoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acompanhamentoEscolarAluno);
    }

    /**
     * DELETE  /acompanhamento-escolar-alunos/:id : delete the "id" acompanhamentoEscolarAluno.
     *
     * @param id the id of the acompanhamentoEscolarAluno to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acompanhamento-escolar-alunos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcompanhamentoEscolarAluno(@PathVariable Long id) {
        log.debug("REST request to delete AcompanhamentoEscolarAluno : {}", id);
        acompanhamentoEscolarAlunoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
