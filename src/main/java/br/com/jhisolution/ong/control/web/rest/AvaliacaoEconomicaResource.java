package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.AvaliacaoEconomica;
import br.com.jhisolution.ong.control.service.AvaliacaoEconomicaService;
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
 * REST controller for managing AvaliacaoEconomica.
 */
@RestController
@RequestMapping("/api")
public class AvaliacaoEconomicaResource {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoEconomicaResource.class);

    private static final String ENTITY_NAME = "avaliacaoEconomica";

    private final AvaliacaoEconomicaService avaliacaoEconomicaService;

    public AvaliacaoEconomicaResource(AvaliacaoEconomicaService avaliacaoEconomicaService) {
        this.avaliacaoEconomicaService = avaliacaoEconomicaService;
    }

    /**
     * POST  /avaliacao-economicas : Create a new avaliacaoEconomica.
     *
     * @param avaliacaoEconomica the avaliacaoEconomica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new avaliacaoEconomica, or with status 400 (Bad Request) if the avaliacaoEconomica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/avaliacao-economicas")
    @Timed
    public ResponseEntity<AvaliacaoEconomica> createAvaliacaoEconomica(@Valid @RequestBody AvaliacaoEconomica avaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to save AvaliacaoEconomica : {}", avaliacaoEconomica);
        if (avaliacaoEconomica.getId() != null) {
            throw new BadRequestAlertException("A new avaliacaoEconomica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvaliacaoEconomica result = avaliacaoEconomicaService.save(avaliacaoEconomica);
        return ResponseEntity.created(new URI("/api/avaliacao-economicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /avaliacao-economicas : Updates an existing avaliacaoEconomica.
     *
     * @param avaliacaoEconomica the avaliacaoEconomica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated avaliacaoEconomica,
     * or with status 400 (Bad Request) if the avaliacaoEconomica is not valid,
     * or with status 500 (Internal Server Error) if the avaliacaoEconomica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/avaliacao-economicas")
    @Timed
    public ResponseEntity<AvaliacaoEconomica> updateAvaliacaoEconomica(@Valid @RequestBody AvaliacaoEconomica avaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to update AvaliacaoEconomica : {}", avaliacaoEconomica);
        if (avaliacaoEconomica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvaliacaoEconomica result = avaliacaoEconomicaService.save(avaliacaoEconomica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, avaliacaoEconomica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /avaliacao-economicas : get all the avaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of avaliacaoEconomicas in body
     */
    @GetMapping("/avaliacao-economicas")
    @Timed
    public ResponseEntity<List<AvaliacaoEconomica>> getAllAvaliacaoEconomicas(Pageable pageable) {
        log.debug("REST request to get a page of AvaliacaoEconomicas");
        Page<AvaliacaoEconomica> page = avaliacaoEconomicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/avaliacao-economicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /avaliacao-economicas/:id : get the "id" avaliacaoEconomica.
     *
     * @param id the id of the avaliacaoEconomica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the avaliacaoEconomica, or with status 404 (Not Found)
     */
    @GetMapping("/avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<AvaliacaoEconomica> getAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to get AvaliacaoEconomica : {}", id);
        Optional<AvaliacaoEconomica> avaliacaoEconomica = avaliacaoEconomicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avaliacaoEconomica);
    }

    /**
     * DELETE  /avaliacao-economicas/:id : delete the "id" avaliacaoEconomica.
     *
     * @param id the id of the avaliacaoEconomica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to delete AvaliacaoEconomica : {}", id);
        avaliacaoEconomicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
