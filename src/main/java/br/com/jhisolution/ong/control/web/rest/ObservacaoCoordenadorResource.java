package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ObservacaoCoordenador;
import br.com.jhisolution.ong.control.service.ObservacaoCoordenadorService;
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
 * REST controller for managing ObservacaoCoordenador.
 */
@RestController
@RequestMapping("/api")
public class ObservacaoCoordenadorResource {

    private final Logger log = LoggerFactory.getLogger(ObservacaoCoordenadorResource.class);

    private static final String ENTITY_NAME = "observacaoCoordenador";

    private final ObservacaoCoordenadorService observacaoCoordenadorService;

    public ObservacaoCoordenadorResource(ObservacaoCoordenadorService observacaoCoordenadorService) {
        this.observacaoCoordenadorService = observacaoCoordenadorService;
    }

    /**
     * POST  /observacao-coordenadors : Create a new observacaoCoordenador.
     *
     * @param observacaoCoordenador the observacaoCoordenador to create
     * @return the ResponseEntity with status 201 (Created) and with body the new observacaoCoordenador, or with status 400 (Bad Request) if the observacaoCoordenador has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/observacao-coordenadors")
    @Timed
    public ResponseEntity<ObservacaoCoordenador> createObservacaoCoordenador(@Valid @RequestBody ObservacaoCoordenador observacaoCoordenador) throws URISyntaxException {
        log.debug("REST request to save ObservacaoCoordenador : {}", observacaoCoordenador);
        if (observacaoCoordenador.getId() != null) {
            throw new BadRequestAlertException("A new observacaoCoordenador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObservacaoCoordenador result = observacaoCoordenadorService.save(observacaoCoordenador);
        return ResponseEntity.created(new URI("/api/observacao-coordenadors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /observacao-coordenadors : Updates an existing observacaoCoordenador.
     *
     * @param observacaoCoordenador the observacaoCoordenador to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated observacaoCoordenador,
     * or with status 400 (Bad Request) if the observacaoCoordenador is not valid,
     * or with status 500 (Internal Server Error) if the observacaoCoordenador couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/observacao-coordenadors")
    @Timed
    public ResponseEntity<ObservacaoCoordenador> updateObservacaoCoordenador(@Valid @RequestBody ObservacaoCoordenador observacaoCoordenador) throws URISyntaxException {
        log.debug("REST request to update ObservacaoCoordenador : {}", observacaoCoordenador);
        if (observacaoCoordenador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObservacaoCoordenador result = observacaoCoordenadorService.save(observacaoCoordenador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, observacaoCoordenador.getId().toString()))
            .body(result);
    }

    /**
     * GET  /observacao-coordenadors : get all the observacaoCoordenadors.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of observacaoCoordenadors in body
     */
    @GetMapping("/observacao-coordenadors")
    @Timed
    public ResponseEntity<List<ObservacaoCoordenador>> getAllObservacaoCoordenadors(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("diario-is-null".equals(filter)) {
            log.debug("REST request to get all ObservacaoCoordenadors where diario is null");
            return new ResponseEntity<>(observacaoCoordenadorService.findAllWhereDiarioIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ObservacaoCoordenadors");
        Page<ObservacaoCoordenador> page = observacaoCoordenadorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/observacao-coordenadors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /observacao-coordenadors/:id : get the "id" observacaoCoordenador.
     *
     * @param id the id of the observacaoCoordenador to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the observacaoCoordenador, or with status 404 (Not Found)
     */
    @GetMapping("/observacao-coordenadors/{id}")
    @Timed
    public ResponseEntity<ObservacaoCoordenador> getObservacaoCoordenador(@PathVariable Long id) {
        log.debug("REST request to get ObservacaoCoordenador : {}", id);
        Optional<ObservacaoCoordenador> observacaoCoordenador = observacaoCoordenadorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(observacaoCoordenador);
    }

    /**
     * DELETE  /observacao-coordenadors/:id : delete the "id" observacaoCoordenador.
     *
     * @param id the id of the observacaoCoordenador to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/observacao-coordenadors/{id}")
    @Timed
    public ResponseEntity<Void> deleteObservacaoCoordenador(@PathVariable Long id) {
        log.debug("REST request to delete ObservacaoCoordenador : {}", id);
        observacaoCoordenadorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
