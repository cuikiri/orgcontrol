package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Avaliacao;
import br.com.jhisolution.ong.control.service.AvaliacaoService;
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
 * REST controller for managing Avaliacao.
 */
@RestController
@RequestMapping("/api")
public class AvaliacaoResource {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoResource.class);

    private static final String ENTITY_NAME = "avaliacao";

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoResource(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    /**
     * POST  /avaliacaos : Create a new avaliacao.
     *
     * @param avaliacao the avaliacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new avaliacao, or with status 400 (Bad Request) if the avaliacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/avaliacaos")
    @Timed
    public ResponseEntity<Avaliacao> createAvaliacao(@Valid @RequestBody Avaliacao avaliacao) throws URISyntaxException {
        log.debug("REST request to save Avaliacao : {}", avaliacao);
        if (avaliacao.getId() != null) {
            throw new BadRequestAlertException("A new avaliacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Avaliacao result = avaliacaoService.save(avaliacao);
        return ResponseEntity.created(new URI("/api/avaliacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /avaliacaos : Updates an existing avaliacao.
     *
     * @param avaliacao the avaliacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated avaliacao,
     * or with status 400 (Bad Request) if the avaliacao is not valid,
     * or with status 500 (Internal Server Error) if the avaliacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/avaliacaos")
    @Timed
    public ResponseEntity<Avaliacao> updateAvaliacao(@Valid @RequestBody Avaliacao avaliacao) throws URISyntaxException {
        log.debug("REST request to update Avaliacao : {}", avaliacao);
        if (avaliacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Avaliacao result = avaliacaoService.save(avaliacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, avaliacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /avaliacaos : get all the avaliacaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of avaliacaos in body
     */
    @GetMapping("/avaliacaos")
    @Timed
    public ResponseEntity<List<Avaliacao>> getAllAvaliacaos(Pageable pageable) {
        log.debug("REST request to get a page of Avaliacaos");
        Page<Avaliacao> page = avaliacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/avaliacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /avaliacaos/:id : get the "id" avaliacao.
     *
     * @param id the id of the avaliacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the avaliacao, or with status 404 (Not Found)
     */
    @GetMapping("/avaliacaos/{id}")
    @Timed
    public ResponseEntity<Avaliacao> getAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get Avaliacao : {}", id);
        Optional<Avaliacao> avaliacao = avaliacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avaliacao);
    }

    /**
     * DELETE  /avaliacaos/:id : delete the "id" avaliacao.
     *
     * @param id the id of the avaliacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete Avaliacao : {}", id);
        avaliacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
