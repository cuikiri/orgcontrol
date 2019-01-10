package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.TipoBiotipo;
import br.com.jhisolution.ong.control.service.TipoBiotipoService;
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
 * REST controller for managing TipoBiotipo.
 */
@RestController
@RequestMapping("/api")
public class TipoBiotipoResource {

    private final Logger log = LoggerFactory.getLogger(TipoBiotipoResource.class);

    private static final String ENTITY_NAME = "tipoBiotipo";

    private final TipoBiotipoService tipoBiotipoService;

    public TipoBiotipoResource(TipoBiotipoService tipoBiotipoService) {
        this.tipoBiotipoService = tipoBiotipoService;
    }

    /**
     * POST  /tipo-biotipos : Create a new tipoBiotipo.
     *
     * @param tipoBiotipo the tipoBiotipo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoBiotipo, or with status 400 (Bad Request) if the tipoBiotipo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-biotipos")
    @Timed
    public ResponseEntity<TipoBiotipo> createTipoBiotipo(@Valid @RequestBody TipoBiotipo tipoBiotipo) throws URISyntaxException {
        log.debug("REST request to save TipoBiotipo : {}", tipoBiotipo);
        if (tipoBiotipo.getId() != null) {
            throw new BadRequestAlertException("A new tipoBiotipo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoBiotipo result = tipoBiotipoService.save(tipoBiotipo);
        return ResponseEntity.created(new URI("/api/tipo-biotipos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-biotipos : Updates an existing tipoBiotipo.
     *
     * @param tipoBiotipo the tipoBiotipo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoBiotipo,
     * or with status 400 (Bad Request) if the tipoBiotipo is not valid,
     * or with status 500 (Internal Server Error) if the tipoBiotipo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-biotipos")
    @Timed
    public ResponseEntity<TipoBiotipo> updateTipoBiotipo(@Valid @RequestBody TipoBiotipo tipoBiotipo) throws URISyntaxException {
        log.debug("REST request to update TipoBiotipo : {}", tipoBiotipo);
        if (tipoBiotipo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoBiotipo result = tipoBiotipoService.save(tipoBiotipo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoBiotipo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-biotipos : get all the tipoBiotipos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of tipoBiotipos in body
     */
    @GetMapping("/tipo-biotipos")
    @Timed
    public ResponseEntity<List<TipoBiotipo>> getAllTipoBiotipos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("biotipo-is-null".equals(filter)) {
            log.debug("REST request to get all TipoBiotipos where biotipo is null");
            return new ResponseEntity<>(tipoBiotipoService.findAllWhereBiotipoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TipoBiotipos");
        Page<TipoBiotipo> page = tipoBiotipoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-biotipos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-biotipos/:id : get the "id" tipoBiotipo.
     *
     * @param id the id of the tipoBiotipo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoBiotipo, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-biotipos/{id}")
    @Timed
    public ResponseEntity<TipoBiotipo> getTipoBiotipo(@PathVariable Long id) {
        log.debug("REST request to get TipoBiotipo : {}", id);
        Optional<TipoBiotipo> tipoBiotipo = tipoBiotipoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoBiotipo);
    }

    /**
     * DELETE  /tipo-biotipos/:id : delete the "id" tipoBiotipo.
     *
     * @param id the id of the tipoBiotipo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-biotipos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoBiotipo(@PathVariable Long id) {
        log.debug("REST request to delete TipoBiotipo : {}", id);
        tipoBiotipoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
