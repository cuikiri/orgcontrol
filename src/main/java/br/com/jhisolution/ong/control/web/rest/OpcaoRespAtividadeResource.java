package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.OpcaoRespAtividade;
import br.com.jhisolution.ong.control.service.OpcaoRespAtividadeService;
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
 * REST controller for managing OpcaoRespAtividade.
 */
@RestController
@RequestMapping("/api")
public class OpcaoRespAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(OpcaoRespAtividadeResource.class);

    private static final String ENTITY_NAME = "opcaoRespAtividade";

    private final OpcaoRespAtividadeService opcaoRespAtividadeService;

    public OpcaoRespAtividadeResource(OpcaoRespAtividadeService opcaoRespAtividadeService) {
        this.opcaoRespAtividadeService = opcaoRespAtividadeService;
    }

    /**
     * POST  /opcao-resp-atividades : Create a new opcaoRespAtividade.
     *
     * @param opcaoRespAtividade the opcaoRespAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new opcaoRespAtividade, or with status 400 (Bad Request) if the opcaoRespAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/opcao-resp-atividades")
    @Timed
    public ResponseEntity<OpcaoRespAtividade> createOpcaoRespAtividade(@Valid @RequestBody OpcaoRespAtividade opcaoRespAtividade) throws URISyntaxException {
        log.debug("REST request to save OpcaoRespAtividade : {}", opcaoRespAtividade);
        if (opcaoRespAtividade.getId() != null) {
            throw new BadRequestAlertException("A new opcaoRespAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpcaoRespAtividade result = opcaoRespAtividadeService.save(opcaoRespAtividade);
        return ResponseEntity.created(new URI("/api/opcao-resp-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /opcao-resp-atividades : Updates an existing opcaoRespAtividade.
     *
     * @param opcaoRespAtividade the opcaoRespAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated opcaoRespAtividade,
     * or with status 400 (Bad Request) if the opcaoRespAtividade is not valid,
     * or with status 500 (Internal Server Error) if the opcaoRespAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/opcao-resp-atividades")
    @Timed
    public ResponseEntity<OpcaoRespAtividade> updateOpcaoRespAtividade(@Valid @RequestBody OpcaoRespAtividade opcaoRespAtividade) throws URISyntaxException {
        log.debug("REST request to update OpcaoRespAtividade : {}", opcaoRespAtividade);
        if (opcaoRespAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OpcaoRespAtividade result = opcaoRespAtividadeService.save(opcaoRespAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, opcaoRespAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /opcao-resp-atividades : get all the opcaoRespAtividades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of opcaoRespAtividades in body
     */
    @GetMapping("/opcao-resp-atividades")
    @Timed
    public ResponseEntity<List<OpcaoRespAtividade>> getAllOpcaoRespAtividades(Pageable pageable) {
        log.debug("REST request to get a page of OpcaoRespAtividades");
        Page<OpcaoRespAtividade> page = opcaoRespAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/opcao-resp-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /opcao-resp-atividades/:id : get the "id" opcaoRespAtividade.
     *
     * @param id the id of the opcaoRespAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the opcaoRespAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/opcao-resp-atividades/{id}")
    @Timed
    public ResponseEntity<OpcaoRespAtividade> getOpcaoRespAtividade(@PathVariable Long id) {
        log.debug("REST request to get OpcaoRespAtividade : {}", id);
        Optional<OpcaoRespAtividade> opcaoRespAtividade = opcaoRespAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opcaoRespAtividade);
    }

    /**
     * DELETE  /opcao-resp-atividades/:id : delete the "id" opcaoRespAtividade.
     *
     * @param id the id of the opcaoRespAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/opcao-resp-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteOpcaoRespAtividade(@PathVariable Long id) {
        log.debug("REST request to delete OpcaoRespAtividade : {}", id);
        opcaoRespAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
