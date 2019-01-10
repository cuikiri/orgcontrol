package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespAvalOptativa;
import br.com.jhisolution.ong.control.service.RespAvalOptativaService;
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
 * REST controller for managing RespAvalOptativa.
 */
@RestController
@RequestMapping("/api")
public class RespAvalOptativaResource {

    private final Logger log = LoggerFactory.getLogger(RespAvalOptativaResource.class);

    private static final String ENTITY_NAME = "respAvalOptativa";

    private final RespAvalOptativaService respAvalOptativaService;

    public RespAvalOptativaResource(RespAvalOptativaService respAvalOptativaService) {
        this.respAvalOptativaService = respAvalOptativaService;
    }

    /**
     * POST  /resp-aval-optativas : Create a new respAvalOptativa.
     *
     * @param respAvalOptativa the respAvalOptativa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respAvalOptativa, or with status 400 (Bad Request) if the respAvalOptativa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resp-aval-optativas")
    @Timed
    public ResponseEntity<RespAvalOptativa> createRespAvalOptativa(@Valid @RequestBody RespAvalOptativa respAvalOptativa) throws URISyntaxException {
        log.debug("REST request to save RespAvalOptativa : {}", respAvalOptativa);
        if (respAvalOptativa.getId() != null) {
            throw new BadRequestAlertException("A new respAvalOptativa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespAvalOptativa result = respAvalOptativaService.save(respAvalOptativa);
        return ResponseEntity.created(new URI("/api/resp-aval-optativas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resp-aval-optativas : Updates an existing respAvalOptativa.
     *
     * @param respAvalOptativa the respAvalOptativa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respAvalOptativa,
     * or with status 400 (Bad Request) if the respAvalOptativa is not valid,
     * or with status 500 (Internal Server Error) if the respAvalOptativa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resp-aval-optativas")
    @Timed
    public ResponseEntity<RespAvalOptativa> updateRespAvalOptativa(@Valid @RequestBody RespAvalOptativa respAvalOptativa) throws URISyntaxException {
        log.debug("REST request to update RespAvalOptativa : {}", respAvalOptativa);
        if (respAvalOptativa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespAvalOptativa result = respAvalOptativaService.save(respAvalOptativa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respAvalOptativa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resp-aval-optativas : get all the respAvalOptativas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of respAvalOptativas in body
     */
    @GetMapping("/resp-aval-optativas")
    @Timed
    public ResponseEntity<List<RespAvalOptativa>> getAllRespAvalOptativas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("itemavaliacao-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalOptativas where itemAvaliacao is null");
            return new ResponseEntity<>(respAvalOptativaService.findAllWhereItemAvaliacaoIsNull(),
                    HttpStatus.OK);
        }
        if ("respostaavaliacao-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalOptativas where respostaAvaliacao is null");
            return new ResponseEntity<>(respAvalOptativaService.findAllWhereRespostaAvaliacaoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RespAvalOptativas");
        Page<RespAvalOptativa> page = respAvalOptativaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resp-aval-optativas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resp-aval-optativas/:id : get the "id" respAvalOptativa.
     *
     * @param id the id of the respAvalOptativa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respAvalOptativa, or with status 404 (Not Found)
     */
    @GetMapping("/resp-aval-optativas/{id}")
    @Timed
    public ResponseEntity<RespAvalOptativa> getRespAvalOptativa(@PathVariable Long id) {
        log.debug("REST request to get RespAvalOptativa : {}", id);
        Optional<RespAvalOptativa> respAvalOptativa = respAvalOptativaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respAvalOptativa);
    }

    /**
     * DELETE  /resp-aval-optativas/:id : delete the "id" respAvalOptativa.
     *
     * @param id the id of the respAvalOptativa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resp-aval-optativas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespAvalOptativa(@PathVariable Long id) {
        log.debug("REST request to delete RespAvalOptativa : {}", id);
        respAvalOptativaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
