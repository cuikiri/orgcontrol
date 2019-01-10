package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAtividade;
import br.com.jhisolution.ong.control.service.TipoAcompanhamentoAtividadeService;
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
 * REST controller for managing TipoAcompanhamentoAtividade.
 */
@RestController
@RequestMapping("/api")
public class TipoAcompanhamentoAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(TipoAcompanhamentoAtividadeResource.class);

    private static final String ENTITY_NAME = "tipoAcompanhamentoAtividade";

    private final TipoAcompanhamentoAtividadeService tipoAcompanhamentoAtividadeService;

    public TipoAcompanhamentoAtividadeResource(TipoAcompanhamentoAtividadeService tipoAcompanhamentoAtividadeService) {
        this.tipoAcompanhamentoAtividadeService = tipoAcompanhamentoAtividadeService;
    }

    /**
     * POST  /tipo-acompanhamento-atividades : Create a new tipoAcompanhamentoAtividade.
     *
     * @param tipoAcompanhamentoAtividade the tipoAcompanhamentoAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoAcompanhamentoAtividade, or with status 400 (Bad Request) if the tipoAcompanhamentoAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-acompanhamento-atividades")
    @Timed
    public ResponseEntity<TipoAcompanhamentoAtividade> createTipoAcompanhamentoAtividade(@Valid @RequestBody TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade) throws URISyntaxException {
        log.debug("REST request to save TipoAcompanhamentoAtividade : {}", tipoAcompanhamentoAtividade);
        if (tipoAcompanhamentoAtividade.getId() != null) {
            throw new BadRequestAlertException("A new tipoAcompanhamentoAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoAcompanhamentoAtividade result = tipoAcompanhamentoAtividadeService.save(tipoAcompanhamentoAtividade);
        return ResponseEntity.created(new URI("/api/tipo-acompanhamento-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-acompanhamento-atividades : Updates an existing tipoAcompanhamentoAtividade.
     *
     * @param tipoAcompanhamentoAtividade the tipoAcompanhamentoAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoAcompanhamentoAtividade,
     * or with status 400 (Bad Request) if the tipoAcompanhamentoAtividade is not valid,
     * or with status 500 (Internal Server Error) if the tipoAcompanhamentoAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-acompanhamento-atividades")
    @Timed
    public ResponseEntity<TipoAcompanhamentoAtividade> updateTipoAcompanhamentoAtividade(@Valid @RequestBody TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade) throws URISyntaxException {
        log.debug("REST request to update TipoAcompanhamentoAtividade : {}", tipoAcompanhamentoAtividade);
        if (tipoAcompanhamentoAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoAcompanhamentoAtividade result = tipoAcompanhamentoAtividadeService.save(tipoAcompanhamentoAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoAcompanhamentoAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-acompanhamento-atividades : get all the tipoAcompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoAcompanhamentoAtividades in body
     */
    @GetMapping("/tipo-acompanhamento-atividades")
    @Timed
    public ResponseEntity<List<TipoAcompanhamentoAtividade>> getAllTipoAcompanhamentoAtividades(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("acompanhamentoatividade-is-null".equals(filter)) {
            log.debug("REST request to get all TipoAcompanhamentoAtividades where acompanhamentoAtividade is null");
            return new ResponseEntity<>(tipoAcompanhamentoAtividadeService.findAllWhereAcompanhamentoAtividadeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoAcompanhamentoAtividades");
        Page<TipoAcompanhamentoAtividade> page = tipoAcompanhamentoAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-acompanhamento-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-acompanhamento-atividades/:id : get the "id" tipoAcompanhamentoAtividade.
     *
     * @param id the id of the tipoAcompanhamentoAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoAcompanhamentoAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-acompanhamento-atividades/{id}")
    @Timed
    public ResponseEntity<TipoAcompanhamentoAtividade> getTipoAcompanhamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to get TipoAcompanhamentoAtividade : {}", id);
        Optional<TipoAcompanhamentoAtividade> tipoAcompanhamentoAtividade = tipoAcompanhamentoAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoAcompanhamentoAtividade);
    }

    /**
     * DELETE  /tipo-acompanhamento-atividades/:id : delete the "id" tipoAcompanhamentoAtividade.
     *
     * @param id the id of the tipoAcompanhamentoAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-acompanhamento-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoAcompanhamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to delete TipoAcompanhamentoAtividade : {}", id);
        tipoAcompanhamentoAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
