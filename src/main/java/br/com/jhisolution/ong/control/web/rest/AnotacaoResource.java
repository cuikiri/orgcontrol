package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Anotacao;
import br.com.jhisolution.ong.control.service.AnotacaoService;
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
 * REST controller for managing Anotacao.
 */
@RestController
@RequestMapping("/api")
public class AnotacaoResource {

    private final Logger log = LoggerFactory.getLogger(AnotacaoResource.class);

    private static final String ENTITY_NAME = "anotacao";

    private final AnotacaoService anotacaoService;

    public AnotacaoResource(AnotacaoService anotacaoService) {
        this.anotacaoService = anotacaoService;
    }

    /**
     * POST  /anotacaos : Create a new anotacao.
     *
     * @param anotacao the anotacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new anotacao, or with status 400 (Bad Request) if the anotacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/anotacaos")
    @Timed
    public ResponseEntity<Anotacao> createAnotacao(@Valid @RequestBody Anotacao anotacao) throws URISyntaxException {
        log.debug("REST request to save Anotacao : {}", anotacao);
        if (anotacao.getId() != null) {
            throw new BadRequestAlertException("A new anotacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Anotacao result = anotacaoService.save(anotacao);
        return ResponseEntity.created(new URI("/api/anotacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /anotacaos : Updates an existing anotacao.
     *
     * @param anotacao the anotacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated anotacao,
     * or with status 400 (Bad Request) if the anotacao is not valid,
     * or with status 500 (Internal Server Error) if the anotacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/anotacaos")
    @Timed
    public ResponseEntity<Anotacao> updateAnotacao(@Valid @RequestBody Anotacao anotacao) throws URISyntaxException {
        log.debug("REST request to update Anotacao : {}", anotacao);
        if (anotacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Anotacao result = anotacaoService.save(anotacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, anotacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /anotacaos : get all the anotacaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of anotacaos in body
     */
    @GetMapping("/anotacaos")
    @Timed
    public ResponseEntity<List<Anotacao>> getAllAnotacaos(Pageable pageable) {
        log.debug("REST request to get a page of Anotacaos");
        Page<Anotacao> page = anotacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/anotacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /anotacaos/:id : get the "id" anotacao.
     *
     * @param id the id of the anotacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the anotacao, or with status 404 (Not Found)
     */
    @GetMapping("/anotacaos/{id}")
    @Timed
    public ResponseEntity<Anotacao> getAnotacao(@PathVariable Long id) {
        log.debug("REST request to get Anotacao : {}", id);
        Optional<Anotacao> anotacao = anotacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anotacao);
    }

    /**
     * DELETE  /anotacaos/:id : delete the "id" anotacao.
     *
     * @param id the id of the anotacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/anotacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAnotacao(@PathVariable Long id) {
        log.debug("REST request to delete Anotacao : {}", id);
        anotacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
