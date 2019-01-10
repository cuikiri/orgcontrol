package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.AcompanhamentoAtividade;
import br.com.jhisolution.ong.control.service.AcompanhamentoAtividadeService;
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
 * REST controller for managing AcompanhamentoAtividade.
 */
@RestController
@RequestMapping("/api")
public class AcompanhamentoAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(AcompanhamentoAtividadeResource.class);

    private static final String ENTITY_NAME = "acompanhamentoAtividade";

    private final AcompanhamentoAtividadeService acompanhamentoAtividadeService;

    public AcompanhamentoAtividadeResource(AcompanhamentoAtividadeService acompanhamentoAtividadeService) {
        this.acompanhamentoAtividadeService = acompanhamentoAtividadeService;
    }

    /**
     * POST  /acompanhamento-atividades : Create a new acompanhamentoAtividade.
     *
     * @param acompanhamentoAtividade the acompanhamentoAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new acompanhamentoAtividade, or with status 400 (Bad Request) if the acompanhamentoAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acompanhamento-atividades")
    @Timed
    public ResponseEntity<AcompanhamentoAtividade> createAcompanhamentoAtividade(@Valid @RequestBody AcompanhamentoAtividade acompanhamentoAtividade) throws URISyntaxException {
        log.debug("REST request to save AcompanhamentoAtividade : {}", acompanhamentoAtividade);
        if (acompanhamentoAtividade.getId() != null) {
            throw new BadRequestAlertException("A new acompanhamentoAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcompanhamentoAtividade result = acompanhamentoAtividadeService.save(acompanhamentoAtividade);
        return ResponseEntity.created(new URI("/api/acompanhamento-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acompanhamento-atividades : Updates an existing acompanhamentoAtividade.
     *
     * @param acompanhamentoAtividade the acompanhamentoAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated acompanhamentoAtividade,
     * or with status 400 (Bad Request) if the acompanhamentoAtividade is not valid,
     * or with status 500 (Internal Server Error) if the acompanhamentoAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acompanhamento-atividades")
    @Timed
    public ResponseEntity<AcompanhamentoAtividade> updateAcompanhamentoAtividade(@Valid @RequestBody AcompanhamentoAtividade acompanhamentoAtividade) throws URISyntaxException {
        log.debug("REST request to update AcompanhamentoAtividade : {}", acompanhamentoAtividade);
        if (acompanhamentoAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcompanhamentoAtividade result = acompanhamentoAtividadeService.save(acompanhamentoAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, acompanhamentoAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acompanhamento-atividades : get all the acompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of acompanhamentoAtividades in body
     */
    @GetMapping("/acompanhamento-atividades")
    @Timed
    public ResponseEntity<List<AcompanhamentoAtividade>> getAllAcompanhamentoAtividades(Pageable pageable) {
        log.debug("REST request to get a page of AcompanhamentoAtividades");
        Page<AcompanhamentoAtividade> page = acompanhamentoAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acompanhamento-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /acompanhamento-atividades/:id : get the "id" acompanhamentoAtividade.
     *
     * @param id the id of the acompanhamentoAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the acompanhamentoAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/acompanhamento-atividades/{id}")
    @Timed
    public ResponseEntity<AcompanhamentoAtividade> getAcompanhamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to get AcompanhamentoAtividade : {}", id);
        Optional<AcompanhamentoAtividade> acompanhamentoAtividade = acompanhamentoAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acompanhamentoAtividade);
    }

    /**
     * DELETE  /acompanhamento-atividades/:id : delete the "id" acompanhamentoAtividade.
     *
     * @param id the id of the acompanhamentoAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acompanhamento-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcompanhamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to delete AcompanhamentoAtividade : {}", id);
        acompanhamentoAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
