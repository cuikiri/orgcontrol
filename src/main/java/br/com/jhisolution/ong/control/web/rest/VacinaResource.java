package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Vacina;
import br.com.jhisolution.ong.control.service.VacinaService;
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
 * REST controller for managing Vacina.
 */
@RestController
@RequestMapping("/api")
public class VacinaResource {

    private final Logger log = LoggerFactory.getLogger(VacinaResource.class);

    private static final String ENTITY_NAME = "vacina";

    private final VacinaService vacinaService;

    public VacinaResource(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    /**
     * POST  /vacinas : Create a new vacina.
     *
     * @param vacina the vacina to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vacina, or with status 400 (Bad Request) if the vacina has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vacinas")
    @Timed
    public ResponseEntity<Vacina> createVacina(@Valid @RequestBody Vacina vacina) throws URISyntaxException {
        log.debug("REST request to save Vacina : {}", vacina);
        if (vacina.getId() != null) {
            throw new BadRequestAlertException("A new vacina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vacina result = vacinaService.save(vacina);
        return ResponseEntity.created(new URI("/api/vacinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vacinas : Updates an existing vacina.
     *
     * @param vacina the vacina to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vacina,
     * or with status 400 (Bad Request) if the vacina is not valid,
     * or with status 500 (Internal Server Error) if the vacina couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vacinas")
    @Timed
    public ResponseEntity<Vacina> updateVacina(@Valid @RequestBody Vacina vacina) throws URISyntaxException {
        log.debug("REST request to update Vacina : {}", vacina);
        if (vacina.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Vacina result = vacinaService.save(vacina);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vacina.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vacinas : get all the vacinas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vacinas in body
     */
    @GetMapping("/vacinas")
    @Timed
    public ResponseEntity<List<Vacina>> getAllVacinas(Pageable pageable) {
        log.debug("REST request to get a page of Vacinas");
        Page<Vacina> page = vacinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vacinas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vacinas/:id : get the "id" vacina.
     *
     * @param id the id of the vacina to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vacina, or with status 404 (Not Found)
     */
    @GetMapping("/vacinas/{id}")
    @Timed
    public ResponseEntity<Vacina> getVacina(@PathVariable Long id) {
        log.debug("REST request to get Vacina : {}", id);
        Optional<Vacina> vacina = vacinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vacina);
    }

    /**
     * DELETE  /vacinas/:id : delete the "id" vacina.
     *
     * @param id the id of the vacina to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vacinas/{id}")
    @Timed
    public ResponseEntity<Void> deleteVacina(@PathVariable Long id) {
        log.debug("REST request to delete Vacina : {}", id);
        vacinaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
