package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespAvalDescritiva;
import br.com.jhisolution.ong.control.service.RespAvalDescritivaService;
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
 * REST controller for managing RespAvalDescritiva.
 */
@RestController
@RequestMapping("/api")
public class RespAvalDescritivaResource {

    private final Logger log = LoggerFactory.getLogger(RespAvalDescritivaResource.class);

    private static final String ENTITY_NAME = "respAvalDescritiva";

    private final RespAvalDescritivaService respAvalDescritivaService;

    public RespAvalDescritivaResource(RespAvalDescritivaService respAvalDescritivaService) {
        this.respAvalDescritivaService = respAvalDescritivaService;
    }

    /**
     * POST  /resp-aval-descritivas : Create a new respAvalDescritiva.
     *
     * @param respAvalDescritiva the respAvalDescritiva to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respAvalDescritiva, or with status 400 (Bad Request) if the respAvalDescritiva has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resp-aval-descritivas")
    @Timed
    public ResponseEntity<RespAvalDescritiva> createRespAvalDescritiva(@Valid @RequestBody RespAvalDescritiva respAvalDescritiva) throws URISyntaxException {
        log.debug("REST request to save RespAvalDescritiva : {}", respAvalDescritiva);
        if (respAvalDescritiva.getId() != null) {
            throw new BadRequestAlertException("A new respAvalDescritiva cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespAvalDescritiva result = respAvalDescritivaService.save(respAvalDescritiva);
        return ResponseEntity.created(new URI("/api/resp-aval-descritivas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resp-aval-descritivas : Updates an existing respAvalDescritiva.
     *
     * @param respAvalDescritiva the respAvalDescritiva to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respAvalDescritiva,
     * or with status 400 (Bad Request) if the respAvalDescritiva is not valid,
     * or with status 500 (Internal Server Error) if the respAvalDescritiva couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resp-aval-descritivas")
    @Timed
    public ResponseEntity<RespAvalDescritiva> updateRespAvalDescritiva(@Valid @RequestBody RespAvalDescritiva respAvalDescritiva) throws URISyntaxException {
        log.debug("REST request to update RespAvalDescritiva : {}", respAvalDescritiva);
        if (respAvalDescritiva.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespAvalDescritiva result = respAvalDescritivaService.save(respAvalDescritiva);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respAvalDescritiva.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resp-aval-descritivas : get all the respAvalDescritivas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of respAvalDescritivas in body
     */
    @GetMapping("/resp-aval-descritivas")
    @Timed
    public ResponseEntity<List<RespAvalDescritiva>> getAllRespAvalDescritivas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("itemavaliacao-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalDescritivas where itemAvaliacao is null");
            return new ResponseEntity<>(respAvalDescritivaService.findAllWhereItemAvaliacaoIsNull(),
                    HttpStatus.OK);
        }
        if ("respostaavaliacao-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalDescritivas where respostaAvaliacao is null");
            return new ResponseEntity<>(respAvalDescritivaService.findAllWhereRespostaAvaliacaoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RespAvalDescritivas");
        Page<RespAvalDescritiva> page = respAvalDescritivaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resp-aval-descritivas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resp-aval-descritivas/:id : get the "id" respAvalDescritiva.
     *
     * @param id the id of the respAvalDescritiva to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respAvalDescritiva, or with status 404 (Not Found)
     */
    @GetMapping("/resp-aval-descritivas/{id}")
    @Timed
    public ResponseEntity<RespAvalDescritiva> getRespAvalDescritiva(@PathVariable Long id) {
        log.debug("REST request to get RespAvalDescritiva : {}", id);
        Optional<RespAvalDescritiva> respAvalDescritiva = respAvalDescritivaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respAvalDescritiva);
    }

    /**
     * DELETE  /resp-aval-descritivas/:id : delete the "id" respAvalDescritiva.
     *
     * @param id the id of the respAvalDescritiva to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resp-aval-descritivas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespAvalDescritiva(@PathVariable Long id) {
        log.debug("REST request to delete RespAvalDescritiva : {}", id);
        respAvalDescritivaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
