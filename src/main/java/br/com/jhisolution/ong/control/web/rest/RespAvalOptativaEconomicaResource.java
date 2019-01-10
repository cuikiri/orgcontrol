package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespAvalOptativaEconomica;
import br.com.jhisolution.ong.control.service.RespAvalOptativaEconomicaService;
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
 * REST controller for managing RespAvalOptativaEconomica.
 */
@RestController
@RequestMapping("/api")
public class RespAvalOptativaEconomicaResource {

    private final Logger log = LoggerFactory.getLogger(RespAvalOptativaEconomicaResource.class);

    private static final String ENTITY_NAME = "respAvalOptativaEconomica";

    private final RespAvalOptativaEconomicaService respAvalOptativaEconomicaService;

    public RespAvalOptativaEconomicaResource(RespAvalOptativaEconomicaService respAvalOptativaEconomicaService) {
        this.respAvalOptativaEconomicaService = respAvalOptativaEconomicaService;
    }

    /**
     * POST  /resp-aval-optativa-economicas : Create a new respAvalOptativaEconomica.
     *
     * @param respAvalOptativaEconomica the respAvalOptativaEconomica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respAvalOptativaEconomica, or with status 400 (Bad Request) if the respAvalOptativaEconomica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resp-aval-optativa-economicas")
    @Timed
    public ResponseEntity<RespAvalOptativaEconomica> createRespAvalOptativaEconomica(@Valid @RequestBody RespAvalOptativaEconomica respAvalOptativaEconomica) throws URISyntaxException {
        log.debug("REST request to save RespAvalOptativaEconomica : {}", respAvalOptativaEconomica);
        if (respAvalOptativaEconomica.getId() != null) {
            throw new BadRequestAlertException("A new respAvalOptativaEconomica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespAvalOptativaEconomica result = respAvalOptativaEconomicaService.save(respAvalOptativaEconomica);
        return ResponseEntity.created(new URI("/api/resp-aval-optativa-economicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resp-aval-optativa-economicas : Updates an existing respAvalOptativaEconomica.
     *
     * @param respAvalOptativaEconomica the respAvalOptativaEconomica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respAvalOptativaEconomica,
     * or with status 400 (Bad Request) if the respAvalOptativaEconomica is not valid,
     * or with status 500 (Internal Server Error) if the respAvalOptativaEconomica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resp-aval-optativa-economicas")
    @Timed
    public ResponseEntity<RespAvalOptativaEconomica> updateRespAvalOptativaEconomica(@Valid @RequestBody RespAvalOptativaEconomica respAvalOptativaEconomica) throws URISyntaxException {
        log.debug("REST request to update RespAvalOptativaEconomica : {}", respAvalOptativaEconomica);
        if (respAvalOptativaEconomica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespAvalOptativaEconomica result = respAvalOptativaEconomicaService.save(respAvalOptativaEconomica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respAvalOptativaEconomica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resp-aval-optativa-economicas : get all the respAvalOptativaEconomicas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of respAvalOptativaEconomicas in body
     */
    @GetMapping("/resp-aval-optativa-economicas")
    @Timed
    public ResponseEntity<List<RespAvalOptativaEconomica>> getAllRespAvalOptativaEconomicas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("itemavaliacaoeconomica-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalOptativaEconomicas where itemAvaliacaoEconomica is null");
            return new ResponseEntity<>(respAvalOptativaEconomicaService.findAllWhereItemAvaliacaoEconomicaIsNull(),
                    HttpStatus.OK);
        }
        if ("respostaavaliacaoeconomica-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalOptativaEconomicas where respostaAvaliacaoEconomica is null");
            return new ResponseEntity<>(respAvalOptativaEconomicaService.findAllWhereRespostaAvaliacaoEconomicaIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RespAvalOptativaEconomicas");
        Page<RespAvalOptativaEconomica> page = respAvalOptativaEconomicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resp-aval-optativa-economicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resp-aval-optativa-economicas/:id : get the "id" respAvalOptativaEconomica.
     *
     * @param id the id of the respAvalOptativaEconomica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respAvalOptativaEconomica, or with status 404 (Not Found)
     */
    @GetMapping("/resp-aval-optativa-economicas/{id}")
    @Timed
    public ResponseEntity<RespAvalOptativaEconomica> getRespAvalOptativaEconomica(@PathVariable Long id) {
        log.debug("REST request to get RespAvalOptativaEconomica : {}", id);
        Optional<RespAvalOptativaEconomica> respAvalOptativaEconomica = respAvalOptativaEconomicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respAvalOptativaEconomica);
    }

    /**
     * DELETE  /resp-aval-optativa-economicas/:id : delete the "id" respAvalOptativaEconomica.
     *
     * @param id the id of the respAvalOptativaEconomica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resp-aval-optativa-economicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespAvalOptativaEconomica(@PathVariable Long id) {
        log.debug("REST request to delete RespAvalOptativaEconomica : {}", id);
        respAvalOptativaEconomicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
