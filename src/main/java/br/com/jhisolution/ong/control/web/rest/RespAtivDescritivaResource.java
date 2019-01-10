package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespAtivDescritiva;
import br.com.jhisolution.ong.control.service.RespAtivDescritivaService;
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
 * REST controller for managing RespAtivDescritiva.
 */
@RestController
@RequestMapping("/api")
public class RespAtivDescritivaResource {

    private final Logger log = LoggerFactory.getLogger(RespAtivDescritivaResource.class);

    private static final String ENTITY_NAME = "respAtivDescritiva";

    private final RespAtivDescritivaService respAtivDescritivaService;

    public RespAtivDescritivaResource(RespAtivDescritivaService respAtivDescritivaService) {
        this.respAtivDescritivaService = respAtivDescritivaService;
    }

    /**
     * POST  /resp-ativ-descritivas : Create a new respAtivDescritiva.
     *
     * @param respAtivDescritiva the respAtivDescritiva to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respAtivDescritiva, or with status 400 (Bad Request) if the respAtivDescritiva has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resp-ativ-descritivas")
    @Timed
    public ResponseEntity<RespAtivDescritiva> createRespAtivDescritiva(@Valid @RequestBody RespAtivDescritiva respAtivDescritiva) throws URISyntaxException {
        log.debug("REST request to save RespAtivDescritiva : {}", respAtivDescritiva);
        if (respAtivDescritiva.getId() != null) {
            throw new BadRequestAlertException("A new respAtivDescritiva cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespAtivDescritiva result = respAtivDescritivaService.save(respAtivDescritiva);
        return ResponseEntity.created(new URI("/api/resp-ativ-descritivas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resp-ativ-descritivas : Updates an existing respAtivDescritiva.
     *
     * @param respAtivDescritiva the respAtivDescritiva to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respAtivDescritiva,
     * or with status 400 (Bad Request) if the respAtivDescritiva is not valid,
     * or with status 500 (Internal Server Error) if the respAtivDescritiva couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resp-ativ-descritivas")
    @Timed
    public ResponseEntity<RespAtivDescritiva> updateRespAtivDescritiva(@Valid @RequestBody RespAtivDescritiva respAtivDescritiva) throws URISyntaxException {
        log.debug("REST request to update RespAtivDescritiva : {}", respAtivDescritiva);
        if (respAtivDescritiva.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespAtivDescritiva result = respAtivDescritivaService.save(respAtivDescritiva);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respAtivDescritiva.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resp-ativ-descritivas : get all the respAtivDescritivas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of respAtivDescritivas in body
     */
    @GetMapping("/resp-ativ-descritivas")
    @Timed
    public ResponseEntity<List<RespAtivDescritiva>> getAllRespAtivDescritivas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("itemacompanhamentoatividade-is-null".equals(filter)) {
            log.debug("REST request to get all RespAtivDescritivas where itemAcompanhamentoAtividade is null");
            return new ResponseEntity<>(respAtivDescritivaService.findAllWhereItemAcompanhamentoAtividadeIsNull(),
                    HttpStatus.OK);
        }
        if ("respostaatividade-is-null".equals(filter)) {
            log.debug("REST request to get all RespAtivDescritivas where respostaAtividade is null");
            return new ResponseEntity<>(respAtivDescritivaService.findAllWhereRespostaAtividadeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RespAtivDescritivas");
        Page<RespAtivDescritiva> page = respAtivDescritivaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resp-ativ-descritivas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resp-ativ-descritivas/:id : get the "id" respAtivDescritiva.
     *
     * @param id the id of the respAtivDescritiva to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respAtivDescritiva, or with status 404 (Not Found)
     */
    @GetMapping("/resp-ativ-descritivas/{id}")
    @Timed
    public ResponseEntity<RespAtivDescritiva> getRespAtivDescritiva(@PathVariable Long id) {
        log.debug("REST request to get RespAtivDescritiva : {}", id);
        Optional<RespAtivDescritiva> respAtivDescritiva = respAtivDescritivaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respAtivDescritiva);
    }

    /**
     * DELETE  /resp-ativ-descritivas/:id : delete the "id" respAtivDescritiva.
     *
     * @param id the id of the respAtivDescritiva to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resp-ativ-descritivas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespAtivDescritiva(@PathVariable Long id) {
        log.debug("REST request to delete RespAtivDescritiva : {}", id);
        respAtivDescritivaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
