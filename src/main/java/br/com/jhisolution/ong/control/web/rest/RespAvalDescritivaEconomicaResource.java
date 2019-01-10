package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespAvalDescritivaEconomica;
import br.com.jhisolution.ong.control.service.RespAvalDescritivaEconomicaService;
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
 * REST controller for managing RespAvalDescritivaEconomica.
 */
@RestController
@RequestMapping("/api")
public class RespAvalDescritivaEconomicaResource {

    private final Logger log = LoggerFactory.getLogger(RespAvalDescritivaEconomicaResource.class);

    private static final String ENTITY_NAME = "respAvalDescritivaEconomica";

    private final RespAvalDescritivaEconomicaService respAvalDescritivaEconomicaService;

    public RespAvalDescritivaEconomicaResource(RespAvalDescritivaEconomicaService respAvalDescritivaEconomicaService) {
        this.respAvalDescritivaEconomicaService = respAvalDescritivaEconomicaService;
    }

    /**
     * POST  /resp-aval-descritiva-economicas : Create a new respAvalDescritivaEconomica.
     *
     * @param respAvalDescritivaEconomica the respAvalDescritivaEconomica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respAvalDescritivaEconomica, or with status 400 (Bad Request) if the respAvalDescritivaEconomica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resp-aval-descritiva-economicas")
    @Timed
    public ResponseEntity<RespAvalDescritivaEconomica> createRespAvalDescritivaEconomica(@Valid @RequestBody RespAvalDescritivaEconomica respAvalDescritivaEconomica) throws URISyntaxException {
        log.debug("REST request to save RespAvalDescritivaEconomica : {}", respAvalDescritivaEconomica);
        if (respAvalDescritivaEconomica.getId() != null) {
            throw new BadRequestAlertException("A new respAvalDescritivaEconomica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespAvalDescritivaEconomica result = respAvalDescritivaEconomicaService.save(respAvalDescritivaEconomica);
        return ResponseEntity.created(new URI("/api/resp-aval-descritiva-economicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resp-aval-descritiva-economicas : Updates an existing respAvalDescritivaEconomica.
     *
     * @param respAvalDescritivaEconomica the respAvalDescritivaEconomica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respAvalDescritivaEconomica,
     * or with status 400 (Bad Request) if the respAvalDescritivaEconomica is not valid,
     * or with status 500 (Internal Server Error) if the respAvalDescritivaEconomica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resp-aval-descritiva-economicas")
    @Timed
    public ResponseEntity<RespAvalDescritivaEconomica> updateRespAvalDescritivaEconomica(@Valid @RequestBody RespAvalDescritivaEconomica respAvalDescritivaEconomica) throws URISyntaxException {
        log.debug("REST request to update RespAvalDescritivaEconomica : {}", respAvalDescritivaEconomica);
        if (respAvalDescritivaEconomica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespAvalDescritivaEconomica result = respAvalDescritivaEconomicaService.save(respAvalDescritivaEconomica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respAvalDescritivaEconomica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resp-aval-descritiva-economicas : get all the respAvalDescritivaEconomicas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of respAvalDescritivaEconomicas in body
     */
    @GetMapping("/resp-aval-descritiva-economicas")
    @Timed
    public ResponseEntity<List<RespAvalDescritivaEconomica>> getAllRespAvalDescritivaEconomicas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("itemavaliacaoeconomica-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalDescritivaEconomicas where itemAvaliacaoEconomica is null");
            return new ResponseEntity<>(respAvalDescritivaEconomicaService.findAllWhereItemAvaliacaoEconomicaIsNull(),
                    HttpStatus.OK);
        }
        if ("respostaavaliacaoeconomica-is-null".equals(filter)) {
            log.debug("REST request to get all RespAvalDescritivaEconomicas where respostaAvaliacaoEconomica is null");
            return new ResponseEntity<>(respAvalDescritivaEconomicaService.findAllWhereRespostaAvaliacaoEconomicaIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RespAvalDescritivaEconomicas");
        Page<RespAvalDescritivaEconomica> page = respAvalDescritivaEconomicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resp-aval-descritiva-economicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resp-aval-descritiva-economicas/:id : get the "id" respAvalDescritivaEconomica.
     *
     * @param id the id of the respAvalDescritivaEconomica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respAvalDescritivaEconomica, or with status 404 (Not Found)
     */
    @GetMapping("/resp-aval-descritiva-economicas/{id}")
    @Timed
    public ResponseEntity<RespAvalDescritivaEconomica> getRespAvalDescritivaEconomica(@PathVariable Long id) {
        log.debug("REST request to get RespAvalDescritivaEconomica : {}", id);
        Optional<RespAvalDescritivaEconomica> respAvalDescritivaEconomica = respAvalDescritivaEconomicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respAvalDescritivaEconomica);
    }

    /**
     * DELETE  /resp-aval-descritiva-economicas/:id : delete the "id" respAvalDescritivaEconomica.
     *
     * @param id the id of the respAvalDescritivaEconomica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resp-aval-descritiva-economicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespAvalDescritivaEconomica(@PathVariable Long id) {
        log.debug("REST request to delete RespAvalDescritivaEconomica : {}", id);
        respAvalDescritivaEconomicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
