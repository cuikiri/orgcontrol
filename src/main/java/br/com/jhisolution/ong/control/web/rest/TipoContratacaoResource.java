package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoContratacao;
import br.com.jhisolution.ong.control.service.TipoContratacaoService;
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
 * REST controller for managing TipoContratacao.
 */
@RestController
@RequestMapping("/api")
public class TipoContratacaoResource {

    private final Logger log = LoggerFactory.getLogger(TipoContratacaoResource.class);

    private static final String ENTITY_NAME = "tipoContratacao";

    private final TipoContratacaoService tipoContratacaoService;

    public TipoContratacaoResource(TipoContratacaoService tipoContratacaoService) {
        this.tipoContratacaoService = tipoContratacaoService;
    }

    /**
     * POST  /tipo-contratacaos : Create a new tipoContratacao.
     *
     * @param tipoContratacao the tipoContratacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoContratacao, or with status 400 (Bad Request) if the tipoContratacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-contratacaos")
    @Timed
    public ResponseEntity<TipoContratacao> createTipoContratacao(@Valid @RequestBody TipoContratacao tipoContratacao) throws URISyntaxException {
        log.debug("REST request to save TipoContratacao : {}", tipoContratacao);
        if (tipoContratacao.getId() != null) {
            throw new BadRequestAlertException("A new tipoContratacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoContratacao result = tipoContratacaoService.save(tipoContratacao);
        return ResponseEntity.created(new URI("/api/tipo-contratacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-contratacaos : Updates an existing tipoContratacao.
     *
     * @param tipoContratacao the tipoContratacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoContratacao,
     * or with status 400 (Bad Request) if the tipoContratacao is not valid,
     * or with status 500 (Internal Server Error) if the tipoContratacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-contratacaos")
    @Timed
    public ResponseEntity<TipoContratacao> updateTipoContratacao(@Valid @RequestBody TipoContratacao tipoContratacao) throws URISyntaxException {
        log.debug("REST request to update TipoContratacao : {}", tipoContratacao);
        if (tipoContratacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoContratacao result = tipoContratacaoService.save(tipoContratacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoContratacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-contratacaos : get all the tipoContratacaos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoContratacaos in body
     */
    @GetMapping("/tipo-contratacaos")
    @Timed
    public ResponseEntity<List<TipoContratacao>> getAllTipoContratacaos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("colaborador-is-null".equals(filter)) {
            log.debug("REST request to get all TipoContratacaos where colaborador is null");
            return new ResponseEntity<>(tipoContratacaoService.findAllWhereColaboradorIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoContratacaos");
        Page<TipoContratacao> page = tipoContratacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-contratacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-contratacaos/:id : get the "id" tipoContratacao.
     *
     * @param id the id of the tipoContratacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoContratacao, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-contratacaos/{id}")
    @Timed
    public ResponseEntity<TipoContratacao> getTipoContratacao(@PathVariable Long id) {
        log.debug("REST request to get TipoContratacao : {}", id);
        Optional<TipoContratacao> tipoContratacao = tipoContratacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoContratacao);
    }

    /**
     * DELETE  /tipo-contratacaos/:id : delete the "id" tipoContratacao.
     *
     * @param id the id of the tipoContratacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-contratacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoContratacao(@PathVariable Long id) {
        log.debug("REST request to delete TipoContratacao : {}", id);
        tipoContratacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
