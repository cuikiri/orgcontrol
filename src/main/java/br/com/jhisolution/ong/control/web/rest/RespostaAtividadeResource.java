package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespostaAtividade;
import br.com.jhisolution.ong.control.service.RespostaAtividadeService;
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
 * REST controller for managing RespostaAtividade.
 */
@RestController
@RequestMapping("/api")
public class RespostaAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(RespostaAtividadeResource.class);

    private static final String ENTITY_NAME = "respostaAtividade";

    private final RespostaAtividadeService respostaAtividadeService;

    public RespostaAtividadeResource(RespostaAtividadeService respostaAtividadeService) {
        this.respostaAtividadeService = respostaAtividadeService;
    }

    /**
     * POST  /resposta-atividades : Create a new respostaAtividade.
     *
     * @param respostaAtividade the respostaAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respostaAtividade, or with status 400 (Bad Request) if the respostaAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resposta-atividades")
    @Timed
    public ResponseEntity<RespostaAtividade> createRespostaAtividade(@Valid @RequestBody RespostaAtividade respostaAtividade) throws URISyntaxException {
        log.debug("REST request to save RespostaAtividade : {}", respostaAtividade);
        if (respostaAtividade.getId() != null) {
            throw new BadRequestAlertException("A new respostaAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespostaAtividade result = respostaAtividadeService.save(respostaAtividade);
        return ResponseEntity.created(new URI("/api/resposta-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resposta-atividades : Updates an existing respostaAtividade.
     *
     * @param respostaAtividade the respostaAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respostaAtividade,
     * or with status 400 (Bad Request) if the respostaAtividade is not valid,
     * or with status 500 (Internal Server Error) if the respostaAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resposta-atividades")
    @Timed
    public ResponseEntity<RespostaAtividade> updateRespostaAtividade(@Valid @RequestBody RespostaAtividade respostaAtividade) throws URISyntaxException {
        log.debug("REST request to update RespostaAtividade : {}", respostaAtividade);
        if (respostaAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespostaAtividade result = respostaAtividadeService.save(respostaAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respostaAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resposta-atividades : get all the respostaAtividades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of respostaAtividades in body
     */
    @GetMapping("/resposta-atividades")
    @Timed
    public ResponseEntity<List<RespostaAtividade>> getAllRespostaAtividades(Pageable pageable) {
        log.debug("REST request to get a page of RespostaAtividades");
        Page<RespostaAtividade> page = respostaAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resposta-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resposta-atividades/:id : get the "id" respostaAtividade.
     *
     * @param id the id of the respostaAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respostaAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/resposta-atividades/{id}")
    @Timed
    public ResponseEntity<RespostaAtividade> getRespostaAtividade(@PathVariable Long id) {
        log.debug("REST request to get RespostaAtividade : {}", id);
        Optional<RespostaAtividade> respostaAtividade = respostaAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respostaAtividade);
    }

    /**
     * DELETE  /resposta-atividades/:id : delete the "id" respostaAtividade.
     *
     * @param id the id of the respostaAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resposta-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespostaAtividade(@PathVariable Long id) {
        log.debug("REST request to delete RespostaAtividade : {}", id);
        respostaAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
