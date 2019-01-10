package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.FotoAcompanhamentoAluno;
import br.com.jhisolution.ong.control.service.FotoAcompanhamentoAlunoService;
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
 * REST controller for managing FotoAcompanhamentoAluno.
 */
@RestController
@RequestMapping("/api")
public class FotoAcompanhamentoAlunoResource {

    private final Logger log = LoggerFactory.getLogger(FotoAcompanhamentoAlunoResource.class);

    private static final String ENTITY_NAME = "fotoAcompanhamentoAluno";

    private final FotoAcompanhamentoAlunoService fotoAcompanhamentoAlunoService;

    public FotoAcompanhamentoAlunoResource(FotoAcompanhamentoAlunoService fotoAcompanhamentoAlunoService) {
        this.fotoAcompanhamentoAlunoService = fotoAcompanhamentoAlunoService;
    }

    /**
     * POST  /foto-acompanhamento-alunos : Create a new fotoAcompanhamentoAluno.
     *
     * @param fotoAcompanhamentoAluno the fotoAcompanhamentoAluno to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fotoAcompanhamentoAluno, or with status 400 (Bad Request) if the fotoAcompanhamentoAluno has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/foto-acompanhamento-alunos")
    @Timed
    public ResponseEntity<FotoAcompanhamentoAluno> createFotoAcompanhamentoAluno(@Valid @RequestBody FotoAcompanhamentoAluno fotoAcompanhamentoAluno) throws URISyntaxException {
        log.debug("REST request to save FotoAcompanhamentoAluno : {}", fotoAcompanhamentoAluno);
        if (fotoAcompanhamentoAluno.getId() != null) {
            throw new BadRequestAlertException("A new fotoAcompanhamentoAluno cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FotoAcompanhamentoAluno result = fotoAcompanhamentoAlunoService.save(fotoAcompanhamentoAluno);
        return ResponseEntity.created(new URI("/api/foto-acompanhamento-alunos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /foto-acompanhamento-alunos : Updates an existing fotoAcompanhamentoAluno.
     *
     * @param fotoAcompanhamentoAluno the fotoAcompanhamentoAluno to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fotoAcompanhamentoAluno,
     * or with status 400 (Bad Request) if the fotoAcompanhamentoAluno is not valid,
     * or with status 500 (Internal Server Error) if the fotoAcompanhamentoAluno couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/foto-acompanhamento-alunos")
    @Timed
    public ResponseEntity<FotoAcompanhamentoAluno> updateFotoAcompanhamentoAluno(@Valid @RequestBody FotoAcompanhamentoAluno fotoAcompanhamentoAluno) throws URISyntaxException {
        log.debug("REST request to update FotoAcompanhamentoAluno : {}", fotoAcompanhamentoAluno);
        if (fotoAcompanhamentoAluno.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FotoAcompanhamentoAluno result = fotoAcompanhamentoAlunoService.save(fotoAcompanhamentoAluno);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fotoAcompanhamentoAluno.getId().toString()))
            .body(result);
    }

    /**
     * GET  /foto-acompanhamento-alunos : get all the fotoAcompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of fotoAcompanhamentoAlunos in body
     */
    @GetMapping("/foto-acompanhamento-alunos")
    @Timed
    public ResponseEntity<List<FotoAcompanhamentoAluno>> getAllFotoAcompanhamentoAlunos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("acompanhamentoaluno-is-null".equals(filter)) {
            log.debug("REST request to get all FotoAcompanhamentoAlunos where acompanhamentoAluno is null");
            return new ResponseEntity<>(fotoAcompanhamentoAlunoService.findAllWhereAcompanhamentoAlunoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of FotoAcompanhamentoAlunos");
        Page<FotoAcompanhamentoAluno> page = fotoAcompanhamentoAlunoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/foto-acompanhamento-alunos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /foto-acompanhamento-alunos/:id : get the "id" fotoAcompanhamentoAluno.
     *
     * @param id the id of the fotoAcompanhamentoAluno to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fotoAcompanhamentoAluno, or with status 404 (Not Found)
     */
    @GetMapping("/foto-acompanhamento-alunos/{id}")
    @Timed
    public ResponseEntity<FotoAcompanhamentoAluno> getFotoAcompanhamentoAluno(@PathVariable Long id) {
        log.debug("REST request to get FotoAcompanhamentoAluno : {}", id);
        Optional<FotoAcompanhamentoAluno> fotoAcompanhamentoAluno = fotoAcompanhamentoAlunoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fotoAcompanhamentoAluno);
    }

    /**
     * DELETE  /foto-acompanhamento-alunos/:id : delete the "id" fotoAcompanhamentoAluno.
     *
     * @param id the id of the fotoAcompanhamentoAluno to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/foto-acompanhamento-alunos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFotoAcompanhamentoAluno(@PathVariable Long id) {
        log.debug("REST request to delete FotoAcompanhamentoAluno : {}", id);
        fotoAcompanhamentoAlunoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
