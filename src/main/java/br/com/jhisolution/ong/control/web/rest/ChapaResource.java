package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Chapa;
import br.com.jhisolution.ong.control.service.ChapaService;
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
 * REST controller for managing Chapa.
 */
@RestController
@RequestMapping("/api")
public class ChapaResource {

    private final Logger log = LoggerFactory.getLogger(ChapaResource.class);

    private static final String ENTITY_NAME = "chapa";

    private final ChapaService chapaService;

    public ChapaResource(ChapaService chapaService) {
        this.chapaService = chapaService;
    }

    /**
     * POST  /chapas : Create a new chapa.
     *
     * @param chapa the chapa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chapa, or with status 400 (Bad Request) if the chapa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chapas")
    @Timed
    public ResponseEntity<Chapa> createChapa(@Valid @RequestBody Chapa chapa) throws URISyntaxException {
        log.debug("REST request to save Chapa : {}", chapa);
        if (chapa.getId() != null) {
            throw new BadRequestAlertException("A new chapa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Chapa result = chapaService.save(chapa);
        return ResponseEntity.created(new URI("/api/chapas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chapas : Updates an existing chapa.
     *
     * @param chapa the chapa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chapa,
     * or with status 400 (Bad Request) if the chapa is not valid,
     * or with status 500 (Internal Server Error) if the chapa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chapas")
    @Timed
    public ResponseEntity<Chapa> updateChapa(@Valid @RequestBody Chapa chapa) throws URISyntaxException {
        log.debug("REST request to update Chapa : {}", chapa);
        if (chapa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Chapa result = chapaService.save(chapa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chapa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chapas : get all the chapas.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of chapas in body
     */
    @GetMapping("/chapas")
    @Timed
    public ResponseEntity<List<Chapa>> getAllChapas(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("eleicaoganhadora-is-null".equals(filter)) {
            log.debug("REST request to get all Chapas where eleicaoGanhadora is null");
            return new ResponseEntity<>(chapaService.findAllWhereEleicaoGanhadoraIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Chapas");
        Page<Chapa> page = chapaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chapas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /chapas/:id : get the "id" chapa.
     *
     * @param id the id of the chapa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chapa, or with status 404 (Not Found)
     */
    @GetMapping("/chapas/{id}")
    @Timed
    public ResponseEntity<Chapa> getChapa(@PathVariable Long id) {
        log.debug("REST request to get Chapa : {}", id);
        Optional<Chapa> chapa = chapaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chapa);
    }

    /**
     * DELETE  /chapas/:id : delete the "id" chapa.
     *
     * @param id the id of the chapa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chapas/{id}")
    @Timed
    public ResponseEntity<Void> deleteChapa(@PathVariable Long id) {
        log.debug("REST request to delete Chapa : {}", id);
        chapaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
