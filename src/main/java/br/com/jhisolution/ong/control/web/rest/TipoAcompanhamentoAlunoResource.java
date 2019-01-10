package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAluno;
import br.com.jhisolution.ong.control.service.TipoAcompanhamentoAlunoService;
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
 * REST controller for managing TipoAcompanhamentoAluno.
 */
@RestController
@RequestMapping("/api")
public class TipoAcompanhamentoAlunoResource {

    private final Logger log = LoggerFactory.getLogger(TipoAcompanhamentoAlunoResource.class);

    private static final String ENTITY_NAME = "tipoAcompanhamentoAluno";

    private final TipoAcompanhamentoAlunoService tipoAcompanhamentoAlunoService;

    public TipoAcompanhamentoAlunoResource(TipoAcompanhamentoAlunoService tipoAcompanhamentoAlunoService) {
        this.tipoAcompanhamentoAlunoService = tipoAcompanhamentoAlunoService;
    }

    /**
     * POST  /tipo-acompanhamento-alunos : Create a new tipoAcompanhamentoAluno.
     *
     * @param tipoAcompanhamentoAluno the tipoAcompanhamentoAluno to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoAcompanhamentoAluno, or with status 400 (Bad Request) if the tipoAcompanhamentoAluno has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-acompanhamento-alunos")
    @Timed
    public ResponseEntity<TipoAcompanhamentoAluno> createTipoAcompanhamentoAluno(@Valid @RequestBody TipoAcompanhamentoAluno tipoAcompanhamentoAluno) throws URISyntaxException {
        log.debug("REST request to save TipoAcompanhamentoAluno : {}", tipoAcompanhamentoAluno);
        if (tipoAcompanhamentoAluno.getId() != null) {
            throw new BadRequestAlertException("A new tipoAcompanhamentoAluno cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoAcompanhamentoAluno result = tipoAcompanhamentoAlunoService.save(tipoAcompanhamentoAluno);
        return ResponseEntity.created(new URI("/api/tipo-acompanhamento-alunos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-acompanhamento-alunos : Updates an existing tipoAcompanhamentoAluno.
     *
     * @param tipoAcompanhamentoAluno the tipoAcompanhamentoAluno to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoAcompanhamentoAluno,
     * or with status 400 (Bad Request) if the tipoAcompanhamentoAluno is not valid,
     * or with status 500 (Internal Server Error) if the tipoAcompanhamentoAluno couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-acompanhamento-alunos")
    @Timed
    public ResponseEntity<TipoAcompanhamentoAluno> updateTipoAcompanhamentoAluno(@Valid @RequestBody TipoAcompanhamentoAluno tipoAcompanhamentoAluno) throws URISyntaxException {
        log.debug("REST request to update TipoAcompanhamentoAluno : {}", tipoAcompanhamentoAluno);
        if (tipoAcompanhamentoAluno.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoAcompanhamentoAluno result = tipoAcompanhamentoAlunoService.save(tipoAcompanhamentoAluno);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoAcompanhamentoAluno.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-acompanhamento-alunos : get all the tipoAcompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoAcompanhamentoAlunos in body
     */
    @GetMapping("/tipo-acompanhamento-alunos")
    @Timed
    public ResponseEntity<List<TipoAcompanhamentoAluno>> getAllTipoAcompanhamentoAlunos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("acompanhamentoaluno-is-null".equals(filter)) {
            log.debug("REST request to get all TipoAcompanhamentoAlunos where acompanhamentoAluno is null");
            return new ResponseEntity<>(tipoAcompanhamentoAlunoService.findAllWhereAcompanhamentoAlunoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoAcompanhamentoAlunos");
        Page<TipoAcompanhamentoAluno> page = tipoAcompanhamentoAlunoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-acompanhamento-alunos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-acompanhamento-alunos/:id : get the "id" tipoAcompanhamentoAluno.
     *
     * @param id the id of the tipoAcompanhamentoAluno to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoAcompanhamentoAluno, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-acompanhamento-alunos/{id}")
    @Timed
    public ResponseEntity<TipoAcompanhamentoAluno> getTipoAcompanhamentoAluno(@PathVariable Long id) {
        log.debug("REST request to get TipoAcompanhamentoAluno : {}", id);
        Optional<TipoAcompanhamentoAluno> tipoAcompanhamentoAluno = tipoAcompanhamentoAlunoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoAcompanhamentoAluno);
    }

    /**
     * DELETE  /tipo-acompanhamento-alunos/:id : delete the "id" tipoAcompanhamentoAluno.
     *
     * @param id the id of the tipoAcompanhamentoAluno to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-acompanhamento-alunos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoAcompanhamentoAluno(@PathVariable Long id) {
        log.debug("REST request to delete TipoAcompanhamentoAluno : {}", id);
        tipoAcompanhamentoAlunoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
