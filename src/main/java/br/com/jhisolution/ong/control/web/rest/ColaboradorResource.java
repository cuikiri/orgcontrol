package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Colaborador;
import br.com.jhisolution.ong.control.service.ColaboradorService;
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
 * REST controller for managing Colaborador.
 */
@RestController
@RequestMapping("/api")
public class ColaboradorResource {

    private final Logger log = LoggerFactory.getLogger(ColaboradorResource.class);

    private static final String ENTITY_NAME = "colaborador";

    private final ColaboradorService colaboradorService;

    public ColaboradorResource(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    /**
     * POST  /colaboradors : Create a new colaborador.
     *
     * @param colaborador the colaborador to create
     * @return the ResponseEntity with status 201 (Created) and with body the new colaborador, or with status 400 (Bad Request) if the colaborador has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/colaboradors")
    @Timed
    public ResponseEntity<Colaborador> createColaborador(@Valid @RequestBody Colaborador colaborador) throws URISyntaxException {
        log.debug("REST request to save Colaborador : {}", colaborador);
        if (colaborador.getId() != null) {
            throw new BadRequestAlertException("A new colaborador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Colaborador result = colaboradorService.save(colaborador);
        return ResponseEntity.created(new URI("/api/colaboradors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /colaboradors : Updates an existing colaborador.
     *
     * @param colaborador the colaborador to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated colaborador,
     * or with status 400 (Bad Request) if the colaborador is not valid,
     * or with status 500 (Internal Server Error) if the colaborador couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/colaboradors")
    @Timed
    public ResponseEntity<Colaborador> updateColaborador(@Valid @RequestBody Colaborador colaborador) throws URISyntaxException {
        log.debug("REST request to update Colaborador : {}", colaborador);
        if (colaborador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Colaborador result = colaboradorService.save(colaborador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, colaborador.getId().toString()))
            .body(result);
    }

    /**
     * GET  /colaboradors : get all the colaboradors.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of colaboradors in body
     */
    @GetMapping("/colaboradors")
    @Timed
    public ResponseEntity<List<Colaborador>> getAllColaboradors(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("candidato-is-null".equals(filter)) {
            log.debug("REST request to get all Colaboradors where candidato is null");
            return new ResponseEntity<>(colaboradorService.findAllWhereCandidatoIsNull(),
                    HttpStatus.OK);
        }
        if ("diario-is-null".equals(filter)) {
            log.debug("REST request to get all Colaboradors where diario is null");
            return new ResponseEntity<>(colaboradorService.findAllWhereDiarioIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Colaboradors");
        Page<Colaborador> page = colaboradorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/colaboradors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /colaboradors/:id : get the "id" colaborador.
     *
     * @param id the id of the colaborador to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the colaborador, or with status 404 (Not Found)
     */
    @GetMapping("/colaboradors/{id}")
    @Timed
    public ResponseEntity<Colaborador> getColaborador(@PathVariable Long id) {
        log.debug("REST request to get Colaborador : {}", id);
        Optional<Colaborador> colaborador = colaboradorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(colaborador);
    }

    /**
     * DELETE  /colaboradors/:id : delete the "id" colaborador.
     *
     * @param id the id of the colaborador to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/colaboradors/{id}")
    @Timed
    public ResponseEntity<Void> deleteColaborador(@PathVariable Long id) {
        log.debug("REST request to delete Colaborador : {}", id);
        colaboradorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
