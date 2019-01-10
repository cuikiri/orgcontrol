package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoAtividade;
import br.com.jhisolution.ong.control.service.TipoAtividadeService;
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
 * REST controller for managing TipoAtividade.
 */
@RestController
@RequestMapping("/api")
public class TipoAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(TipoAtividadeResource.class);

    private static final String ENTITY_NAME = "tipoAtividade";

    private final TipoAtividadeService tipoAtividadeService;

    public TipoAtividadeResource(TipoAtividadeService tipoAtividadeService) {
        this.tipoAtividadeService = tipoAtividadeService;
    }

    /**
     * POST  /tipo-atividades : Create a new tipoAtividade.
     *
     * @param tipoAtividade the tipoAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoAtividade, or with status 400 (Bad Request) if the tipoAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-atividades")
    @Timed
    public ResponseEntity<TipoAtividade> createTipoAtividade(@Valid @RequestBody TipoAtividade tipoAtividade) throws URISyntaxException {
        log.debug("REST request to save TipoAtividade : {}", tipoAtividade);
        if (tipoAtividade.getId() != null) {
            throw new BadRequestAlertException("A new tipoAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoAtividade result = tipoAtividadeService.save(tipoAtividade);
        return ResponseEntity.created(new URI("/api/tipo-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-atividades : Updates an existing tipoAtividade.
     *
     * @param tipoAtividade the tipoAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoAtividade,
     * or with status 400 (Bad Request) if the tipoAtividade is not valid,
     * or with status 500 (Internal Server Error) if the tipoAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-atividades")
    @Timed
    public ResponseEntity<TipoAtividade> updateTipoAtividade(@Valid @RequestBody TipoAtividade tipoAtividade) throws URISyntaxException {
        log.debug("REST request to update TipoAtividade : {}", tipoAtividade);
        if (tipoAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoAtividade result = tipoAtividadeService.save(tipoAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-atividades : get all the tipoAtividades.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoAtividades in body
     */
    @GetMapping("/tipo-atividades")
    @Timed
    public ResponseEntity<List<TipoAtividade>> getAllTipoAtividades(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("atividade-is-null".equals(filter)) {
            log.debug("REST request to get all TipoAtividades where atividade is null");
            return new ResponseEntity<>(tipoAtividadeService.findAllWhereAtividadeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoAtividades");
        Page<TipoAtividade> page = tipoAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-atividades/:id : get the "id" tipoAtividade.
     *
     * @param id the id of the tipoAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-atividades/{id}")
    @Timed
    public ResponseEntity<TipoAtividade> getTipoAtividade(@PathVariable Long id) {
        log.debug("REST request to get TipoAtividade : {}", id);
        Optional<TipoAtividade> tipoAtividade = tipoAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoAtividade);
    }

    /**
     * DELETE  /tipo-atividades/:id : delete the "id" tipoAtividade.
     *
     * @param id the id of the tipoAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoAtividade(@PathVariable Long id) {
        log.debug("REST request to delete TipoAtividade : {}", id);
        tipoAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
