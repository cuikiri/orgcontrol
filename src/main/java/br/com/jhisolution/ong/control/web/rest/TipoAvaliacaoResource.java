package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoAvaliacao;
import br.com.jhisolution.ong.control.service.TipoAvaliacaoService;
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
 * REST controller for managing TipoAvaliacao.
 */
@RestController
@RequestMapping("/api")
public class TipoAvaliacaoResource {

    private final Logger log = LoggerFactory.getLogger(TipoAvaliacaoResource.class);

    private static final String ENTITY_NAME = "tipoAvaliacao";

    private final TipoAvaliacaoService tipoAvaliacaoService;

    public TipoAvaliacaoResource(TipoAvaliacaoService tipoAvaliacaoService) {
        this.tipoAvaliacaoService = tipoAvaliacaoService;
    }

    /**
     * POST  /tipo-avaliacaos : Create a new tipoAvaliacao.
     *
     * @param tipoAvaliacao the tipoAvaliacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoAvaliacao, or with status 400 (Bad Request) if the tipoAvaliacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-avaliacaos")
    @Timed
    public ResponseEntity<TipoAvaliacao> createTipoAvaliacao(@Valid @RequestBody TipoAvaliacao tipoAvaliacao) throws URISyntaxException {
        log.debug("REST request to save TipoAvaliacao : {}", tipoAvaliacao);
        if (tipoAvaliacao.getId() != null) {
            throw new BadRequestAlertException("A new tipoAvaliacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoAvaliacao result = tipoAvaliacaoService.save(tipoAvaliacao);
        return ResponseEntity.created(new URI("/api/tipo-avaliacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-avaliacaos : Updates an existing tipoAvaliacao.
     *
     * @param tipoAvaliacao the tipoAvaliacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoAvaliacao,
     * or with status 400 (Bad Request) if the tipoAvaliacao is not valid,
     * or with status 500 (Internal Server Error) if the tipoAvaliacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-avaliacaos")
    @Timed
    public ResponseEntity<TipoAvaliacao> updateTipoAvaliacao(@Valid @RequestBody TipoAvaliacao tipoAvaliacao) throws URISyntaxException {
        log.debug("REST request to update TipoAvaliacao : {}", tipoAvaliacao);
        if (tipoAvaliacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoAvaliacao result = tipoAvaliacaoService.save(tipoAvaliacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoAvaliacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-avaliacaos : get all the tipoAvaliacaos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoAvaliacaos in body
     */
    @GetMapping("/tipo-avaliacaos")
    @Timed
    public ResponseEntity<List<TipoAvaliacao>> getAllTipoAvaliacaos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("avaliacao-is-null".equals(filter)) {
            log.debug("REST request to get all TipoAvaliacaos where avaliacao is null");
            return new ResponseEntity<>(tipoAvaliacaoService.findAllWhereAvaliacaoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoAvaliacaos");
        Page<TipoAvaliacao> page = tipoAvaliacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-avaliacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-avaliacaos/:id : get the "id" tipoAvaliacao.
     *
     * @param id the id of the tipoAvaliacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoAvaliacao, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-avaliacaos/{id}")
    @Timed
    public ResponseEntity<TipoAvaliacao> getTipoAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get TipoAvaliacao : {}", id);
        Optional<TipoAvaliacao> tipoAvaliacao = tipoAvaliacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoAvaliacao);
    }

    /**
     * DELETE  /tipo-avaliacaos/:id : delete the "id" tipoAvaliacao.
     *
     * @param id the id of the tipoAvaliacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete TipoAvaliacao : {}", id);
        tipoAvaliacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
