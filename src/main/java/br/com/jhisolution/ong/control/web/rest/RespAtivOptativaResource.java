package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespAtivOptativa;
import br.com.jhisolution.ong.control.service.RespAtivOptativaService;
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
 * REST controller for managing RespAtivOptativa.
 */
@RestController
@RequestMapping("/api")
public class RespAtivOptativaResource {

    private final Logger log = LoggerFactory.getLogger(RespAtivOptativaResource.class);

    private static final String ENTITY_NAME = "respAtivOptativa";

    private final RespAtivOptativaService respAtivOptativaService;

    public RespAtivOptativaResource(RespAtivOptativaService respAtivOptativaService) {
        this.respAtivOptativaService = respAtivOptativaService;
    }

    /**
     * POST  /resp-ativ-optativas : Create a new respAtivOptativa.
     *
     * @param respAtivOptativa the respAtivOptativa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respAtivOptativa, or with status 400 (Bad Request) if the respAtivOptativa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resp-ativ-optativas")
    @Timed
    public ResponseEntity<RespAtivOptativa> createRespAtivOptativa(@Valid @RequestBody RespAtivOptativa respAtivOptativa) throws URISyntaxException {
        log.debug("REST request to save RespAtivOptativa : {}", respAtivOptativa);
        if (respAtivOptativa.getId() != null) {
            throw new BadRequestAlertException("A new respAtivOptativa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespAtivOptativa result = respAtivOptativaService.save(respAtivOptativa);
        return ResponseEntity.created(new URI("/api/resp-ativ-optativas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resp-ativ-optativas : Updates an existing respAtivOptativa.
     *
     * @param respAtivOptativa the respAtivOptativa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respAtivOptativa,
     * or with status 400 (Bad Request) if the respAtivOptativa is not valid,
     * or with status 500 (Internal Server Error) if the respAtivOptativa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resp-ativ-optativas")
    @Timed
    public ResponseEntity<RespAtivOptativa> updateRespAtivOptativa(@Valid @RequestBody RespAtivOptativa respAtivOptativa) throws URISyntaxException {
        log.debug("REST request to update RespAtivOptativa : {}", respAtivOptativa);
        if (respAtivOptativa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespAtivOptativa result = respAtivOptativaService.save(respAtivOptativa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respAtivOptativa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resp-ativ-optativas : get all the respAtivOptativas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of respAtivOptativas in body
     */
    @GetMapping("/resp-ativ-optativas")
    @Timed
    public ResponseEntity<List<RespAtivOptativa>> getAllRespAtivOptativas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("itemacompanhamentoatividade-is-null".equals(filter)) {
            log.debug("REST request to get all RespAtivOptativas where itemAcompanhamentoAtividade is null");
            return new ResponseEntity<>(respAtivOptativaService.findAllWhereItemAcompanhamentoAtividadeIsNull(),
                    HttpStatus.OK);
        }
        if ("respostaatividade-is-null".equals(filter)) {
            log.debug("REST request to get all RespAtivOptativas where respostaAtividade is null");
            return new ResponseEntity<>(respAtivOptativaService.findAllWhereRespostaAtividadeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RespAtivOptativas");
        Page<RespAtivOptativa> page = respAtivOptativaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resp-ativ-optativas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resp-ativ-optativas/:id : get the "id" respAtivOptativa.
     *
     * @param id the id of the respAtivOptativa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respAtivOptativa, or with status 404 (Not Found)
     */
    @GetMapping("/resp-ativ-optativas/{id}")
    @Timed
    public ResponseEntity<RespAtivOptativa> getRespAtivOptativa(@PathVariable Long id) {
        log.debug("REST request to get RespAtivOptativa : {}", id);
        Optional<RespAtivOptativa> respAtivOptativa = respAtivOptativaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respAtivOptativa);
    }

    /**
     * DELETE  /resp-ativ-optativas/:id : delete the "id" respAtivOptativa.
     *
     * @param id the id of the respAtivOptativa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resp-ativ-optativas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespAtivOptativa(@PathVariable Long id) {
        log.debug("REST request to delete RespAtivOptativa : {}", id);
        respAtivOptativaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
