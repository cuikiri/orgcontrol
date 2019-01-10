package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RespostaAvaliacaoEconomica;
import br.com.jhisolution.ong.control.service.RespostaAvaliacaoEconomicaService;
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
 * REST controller for managing RespostaAvaliacaoEconomica.
 */
@RestController
@RequestMapping("/api")
public class RespostaAvaliacaoEconomicaResource {

    private final Logger log = LoggerFactory.getLogger(RespostaAvaliacaoEconomicaResource.class);

    private static final String ENTITY_NAME = "respostaAvaliacaoEconomica";

    private final RespostaAvaliacaoEconomicaService respostaAvaliacaoEconomicaService;

    public RespostaAvaliacaoEconomicaResource(RespostaAvaliacaoEconomicaService respostaAvaliacaoEconomicaService) {
        this.respostaAvaliacaoEconomicaService = respostaAvaliacaoEconomicaService;
    }

    /**
     * POST  /resposta-avaliacao-economicas : Create a new respostaAvaliacaoEconomica.
     *
     * @param respostaAvaliacaoEconomica the respostaAvaliacaoEconomica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respostaAvaliacaoEconomica, or with status 400 (Bad Request) if the respostaAvaliacaoEconomica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resposta-avaliacao-economicas")
    @Timed
    public ResponseEntity<RespostaAvaliacaoEconomica> createRespostaAvaliacaoEconomica(@Valid @RequestBody RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to save RespostaAvaliacaoEconomica : {}", respostaAvaliacaoEconomica);
        if (respostaAvaliacaoEconomica.getId() != null) {
            throw new BadRequestAlertException("A new respostaAvaliacaoEconomica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RespostaAvaliacaoEconomica result = respostaAvaliacaoEconomicaService.save(respostaAvaliacaoEconomica);
        return ResponseEntity.created(new URI("/api/resposta-avaliacao-economicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resposta-avaliacao-economicas : Updates an existing respostaAvaliacaoEconomica.
     *
     * @param respostaAvaliacaoEconomica the respostaAvaliacaoEconomica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respostaAvaliacaoEconomica,
     * or with status 400 (Bad Request) if the respostaAvaliacaoEconomica is not valid,
     * or with status 500 (Internal Server Error) if the respostaAvaliacaoEconomica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resposta-avaliacao-economicas")
    @Timed
    public ResponseEntity<RespostaAvaliacaoEconomica> updateRespostaAvaliacaoEconomica(@Valid @RequestBody RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to update RespostaAvaliacaoEconomica : {}", respostaAvaliacaoEconomica);
        if (respostaAvaliacaoEconomica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RespostaAvaliacaoEconomica result = respostaAvaliacaoEconomicaService.save(respostaAvaliacaoEconomica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, respostaAvaliacaoEconomica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resposta-avaliacao-economicas : get all the respostaAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of respostaAvaliacaoEconomicas in body
     */
    @GetMapping("/resposta-avaliacao-economicas")
    @Timed
    public ResponseEntity<List<RespostaAvaliacaoEconomica>> getAllRespostaAvaliacaoEconomicas(Pageable pageable) {
        log.debug("REST request to get a page of RespostaAvaliacaoEconomicas");
        Page<RespostaAvaliacaoEconomica> page = respostaAvaliacaoEconomicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resposta-avaliacao-economicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /resposta-avaliacao-economicas/:id : get the "id" respostaAvaliacaoEconomica.
     *
     * @param id the id of the respostaAvaliacaoEconomica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respostaAvaliacaoEconomica, or with status 404 (Not Found)
     */
    @GetMapping("/resposta-avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<RespostaAvaliacaoEconomica> getRespostaAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to get RespostaAvaliacaoEconomica : {}", id);
        Optional<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomica = respostaAvaliacaoEconomicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respostaAvaliacaoEconomica);
    }

    /**
     * DELETE  /resposta-avaliacao-economicas/:id : delete the "id" respostaAvaliacaoEconomica.
     *
     * @param id the id of the respostaAvaliacaoEconomica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resposta-avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespostaAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to delete RespostaAvaliacaoEconomica : {}", id);
        respostaAvaliacaoEconomicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
