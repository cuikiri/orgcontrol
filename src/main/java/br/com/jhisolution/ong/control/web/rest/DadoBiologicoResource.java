package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.DadoBiologico;
import br.com.jhisolution.ong.control.service.DadoBiologicoService;
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
 * REST controller for managing DadoBiologico.
 */
@RestController
@RequestMapping("/api")
public class DadoBiologicoResource {

    private final Logger log = LoggerFactory.getLogger(DadoBiologicoResource.class);

    private static final String ENTITY_NAME = "dadoBiologico";

    private final DadoBiologicoService dadoBiologicoService;

    public DadoBiologicoResource(DadoBiologicoService dadoBiologicoService) {
        this.dadoBiologicoService = dadoBiologicoService;
    }

    /**
     * POST  /dado-biologicos : Create a new dadoBiologico.
     *
     * @param dadoBiologico the dadoBiologico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dadoBiologico, or with status 400 (Bad Request) if the dadoBiologico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dado-biologicos")
    @Timed
    public ResponseEntity<DadoBiologico> createDadoBiologico(@Valid @RequestBody DadoBiologico dadoBiologico) throws URISyntaxException {
        log.debug("REST request to save DadoBiologico : {}", dadoBiologico);
        if (dadoBiologico.getId() != null) {
            throw new BadRequestAlertException("A new dadoBiologico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DadoBiologico result = dadoBiologicoService.save(dadoBiologico);
        return ResponseEntity.created(new URI("/api/dado-biologicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dado-biologicos : Updates an existing dadoBiologico.
     *
     * @param dadoBiologico the dadoBiologico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dadoBiologico,
     * or with status 400 (Bad Request) if the dadoBiologico is not valid,
     * or with status 500 (Internal Server Error) if the dadoBiologico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dado-biologicos")
    @Timed
    public ResponseEntity<DadoBiologico> updateDadoBiologico(@Valid @RequestBody DadoBiologico dadoBiologico) throws URISyntaxException {
        log.debug("REST request to update DadoBiologico : {}", dadoBiologico);
        if (dadoBiologico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DadoBiologico result = dadoBiologicoService.save(dadoBiologico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dadoBiologico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dado-biologicos : get all the dadoBiologicos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of dadoBiologicos in body
     */
    @GetMapping("/dado-biologicos")
    @Timed
    public ResponseEntity<List<DadoBiologico>> getAllDadoBiologicos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("dadosmedico-is-null".equals(filter)) {
            log.debug("REST request to get all DadoBiologicos where dadosMedico is null");
            return new ResponseEntity<>(dadoBiologicoService.findAllWhereDadosMedicoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of DadoBiologicos");
        Page<DadoBiologico> page = dadoBiologicoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dado-biologicos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dado-biologicos/:id : get the "id" dadoBiologico.
     *
     * @param id the id of the dadoBiologico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dadoBiologico, or with status 404 (Not Found)
     */
    @GetMapping("/dado-biologicos/{id}")
    @Timed
    public ResponseEntity<DadoBiologico> getDadoBiologico(@PathVariable Long id) {
        log.debug("REST request to get DadoBiologico : {}", id);
        Optional<DadoBiologico> dadoBiologico = dadoBiologicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dadoBiologico);
    }

    /**
     * DELETE  /dado-biologicos/:id : delete the "id" dadoBiologico.
     *
     * @param id the id of the dadoBiologico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dado-biologicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDadoBiologico(@PathVariable Long id) {
        log.debug("REST request to delete DadoBiologico : {}", id);
        dadoBiologicoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
