package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.AcompanhamentoAluno;
import br.com.jhisolution.ong.control.service.AcompanhamentoAlunoService;
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
 * REST controller for managing AcompanhamentoAluno.
 */
@RestController
@RequestMapping("/api")
public class AcompanhamentoAlunoResource {

    private final Logger log = LoggerFactory.getLogger(AcompanhamentoAlunoResource.class);

    private static final String ENTITY_NAME = "acompanhamentoAluno";

    private final AcompanhamentoAlunoService acompanhamentoAlunoService;

    public AcompanhamentoAlunoResource(AcompanhamentoAlunoService acompanhamentoAlunoService) {
        this.acompanhamentoAlunoService = acompanhamentoAlunoService;
    }

    /**
     * POST  /acompanhamento-alunos : Create a new acompanhamentoAluno.
     *
     * @param acompanhamentoAluno the acompanhamentoAluno to create
     * @return the ResponseEntity with status 201 (Created) and with body the new acompanhamentoAluno, or with status 400 (Bad Request) if the acompanhamentoAluno has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acompanhamento-alunos")
    @Timed
    public ResponseEntity<AcompanhamentoAluno> createAcompanhamentoAluno(@Valid @RequestBody AcompanhamentoAluno acompanhamentoAluno) throws URISyntaxException {
        log.debug("REST request to save AcompanhamentoAluno : {}", acompanhamentoAluno);
        if (acompanhamentoAluno.getId() != null) {
            throw new BadRequestAlertException("A new acompanhamentoAluno cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcompanhamentoAluno result = acompanhamentoAlunoService.save(acompanhamentoAluno);
        return ResponseEntity.created(new URI("/api/acompanhamento-alunos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acompanhamento-alunos : Updates an existing acompanhamentoAluno.
     *
     * @param acompanhamentoAluno the acompanhamentoAluno to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated acompanhamentoAluno,
     * or with status 400 (Bad Request) if the acompanhamentoAluno is not valid,
     * or with status 500 (Internal Server Error) if the acompanhamentoAluno couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acompanhamento-alunos")
    @Timed
    public ResponseEntity<AcompanhamentoAluno> updateAcompanhamentoAluno(@Valid @RequestBody AcompanhamentoAluno acompanhamentoAluno) throws URISyntaxException {
        log.debug("REST request to update AcompanhamentoAluno : {}", acompanhamentoAluno);
        if (acompanhamentoAluno.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcompanhamentoAluno result = acompanhamentoAlunoService.save(acompanhamentoAluno);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, acompanhamentoAluno.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acompanhamento-alunos : get all the acompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of acompanhamentoAlunos in body
     */
    @GetMapping("/acompanhamento-alunos")
    @Timed
    public ResponseEntity<List<AcompanhamentoAluno>> getAllAcompanhamentoAlunos(Pageable pageable) {
        log.debug("REST request to get a page of AcompanhamentoAlunos");
        Page<AcompanhamentoAluno> page = acompanhamentoAlunoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acompanhamento-alunos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /acompanhamento-alunos/:id : get the "id" acompanhamentoAluno.
     *
     * @param id the id of the acompanhamentoAluno to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the acompanhamentoAluno, or with status 404 (Not Found)
     */
    @GetMapping("/acompanhamento-alunos/{id}")
    @Timed
    public ResponseEntity<AcompanhamentoAluno> getAcompanhamentoAluno(@PathVariable Long id) {
        log.debug("REST request to get AcompanhamentoAluno : {}", id);
        Optional<AcompanhamentoAluno> acompanhamentoAluno = acompanhamentoAlunoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acompanhamentoAluno);
    }

    /**
     * DELETE  /acompanhamento-alunos/:id : delete the "id" acompanhamentoAluno.
     *
     * @param id the id of the acompanhamentoAluno to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acompanhamento-alunos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcompanhamentoAluno(@PathVariable Long id) {
        log.debug("REST request to delete AcompanhamentoAluno : {}", id);
        acompanhamentoAlunoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
