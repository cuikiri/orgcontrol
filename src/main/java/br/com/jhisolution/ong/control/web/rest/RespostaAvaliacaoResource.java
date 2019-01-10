package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespostaAvaliacao;
import br.com.jhisolution.ong.control.service.RespostaAvaliacaoService;
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
 * REST controller for managing RespostaAvaliacao.
 */
@RestController
@RequestMapping("/api")
public class RespostaAvaliacaoResource {

    private final Logger log = LoggerFactory.getLogger(RespostaAvaliacaoResource.class);

    private static final String ENTITY_NAME = "respostaAvaliacao";

    private final RespostaAvaliacaoService respostaAvaliacaoService;

    public RespostaAvaliacaoResource(RespostaAvaliacaoService respostaAvaliacaoService) {
        this.respostaAvaliacaoService = respostaAvaliacaoService;
    }

    /**
     * POST  /resposta-avaliacaos : Create a new respostaAvaliacao.
     *
     * @param respostaAvaliacao the respostaAvaliacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respostaAvaliacao, or with status 400 (Bad Request) if the respostaAvaliacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resposta-avaliacaos")
    @Timed
    public ResponseEntity<RespostaAvaliacao> createRespostaAvaliacao(@Valid @RequestBody RespostaAvaliacao respostaAvaliacao) throws URISyntaxException {
        log.debug("REST request to save RespostaAvaliacao : {}", respostaAvaliacao);
        if (respostaAvaliacao.getId() != null) {
            throw new BadRequestAlertException("A new respostaAvaliacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespostaAvaliacao result = respostaAvaliacaoService.save(respostaAvaliacao);
        return ResponseEntity.created(new URI("/api/resposta-avaliacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resposta-avaliacaos : Updates an existing respostaAvaliacao.
     *
     * @param respostaAvaliacao the respostaAvaliacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respostaAvaliacao,
     * or with status 400 (Bad Request) if the respostaAvaliacao is not valid,
     * or with status 500 (Internal Server Error) if the respostaAvaliacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resposta-avaliacaos")
    @Timed
    public ResponseEntity<RespostaAvaliacao> updateRespostaAvaliacao(@Valid @RequestBody RespostaAvaliacao respostaAvaliacao) throws URISyntaxException {
        log.debug("REST request to update RespostaAvaliacao : {}", respostaAvaliacao);
        if (respostaAvaliacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespostaAvaliacao result = respostaAvaliacaoService.save(respostaAvaliacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respostaAvaliacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resposta-avaliacaos : get all the respostaAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of respostaAvaliacaos in body
     */
    @GetMapping("/resposta-avaliacaos")
    @Timed
    public ResponseEntity<List<RespostaAvaliacao>> getAllRespostaAvaliacaos(Pageable pageable) {
        log.debug("REST request to get a page of RespostaAvaliacaos");
        Page<RespostaAvaliacao> page = respostaAvaliacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resposta-avaliacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resposta-avaliacaos/:id : get the "id" respostaAvaliacao.
     *
     * @param id the id of the respostaAvaliacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respostaAvaliacao, or with status 404 (Not Found)
     */
    @GetMapping("/resposta-avaliacaos/{id}")
    @Timed
    public ResponseEntity<RespostaAvaliacao> getRespostaAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get RespostaAvaliacao : {}", id);
        Optional<RespostaAvaliacao> respostaAvaliacao = respostaAvaliacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respostaAvaliacao);
    }

    /**
     * DELETE  /resposta-avaliacaos/:id : delete the "id" respostaAvaliacao.
     *
     * @param id the id of the respostaAvaliacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resposta-avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespostaAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete RespostaAvaliacao : {}", id);
        respostaAvaliacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
