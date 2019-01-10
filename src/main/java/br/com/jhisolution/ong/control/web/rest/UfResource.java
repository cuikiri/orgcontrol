package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Uf;
import br.com.jhisolution.ong.control.service.UfService;
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
 * REST controller for managing Uf.
 */
@RestController
@RequestMapping("/api")
public class UfResource {

    private final Logger log = LoggerFactory.getLogger(UfResource.class);

    private static final String ENTITY_NAME = "uf";

    private final UfService ufService;

    public UfResource(UfService ufService) {
        this.ufService = ufService;
    }

    /**
     * POST  /ufs : Create a new uf.
     *
     * @param uf the uf to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uf, or with status 400 (Bad Request) if the uf has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ufs")
    @Timed
    public ResponseEntity<Uf> createUf(@Valid @RequestBody Uf uf) throws URISyntaxException {
        log.debug("REST request to save Uf : {}", uf);
        if (uf.getId() != null) {
            throw new BadRequestAlertException("A new uf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Uf result = ufService.save(uf);
        return ResponseEntity.created(new URI("/api/ufs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ufs : Updates an existing uf.
     *
     * @param uf the uf to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uf,
     * or with status 400 (Bad Request) if the uf is not valid,
     * or with status 500 (Internal Server Error) if the uf couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ufs")
    @Timed
    public ResponseEntity<Uf> updateUf(@Valid @RequestBody Uf uf) throws URISyntaxException {
        log.debug("REST request to update Uf : {}", uf);
        if (uf.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Uf result = ufService.save(uf);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uf.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ufs : get all the ufs.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of ufs in body
     */
    @GetMapping("/ufs")
    @Timed
    public ResponseEntity<List<Uf>> getAllUfs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("endereco-is-null".equals(filter)) {
            log.debug("REST request to get all Ufs where endereco is null");
            return new ResponseEntity<>(ufService.findAllWhereEnderecoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Ufs");
        Page<Uf> page = ufService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ufs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ufs/:id : get the "id" uf.
     *
     * @param id the id of the uf to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uf, or with status 404 (Not Found)
     */
    @GetMapping("/ufs/{id}")
    @Timed
    public ResponseEntity<Uf> getUf(@PathVariable Long id) {
        log.debug("REST request to get Uf : {}", id);
        Optional<Uf> uf = ufService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uf);
    }

    /**
     * DELETE  /ufs/:id : delete the "id" uf.
     *
     * @param id the id of the uf to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ufs/{id}")
    @Timed
    public ResponseEntity<Void> deleteUf(@PathVariable Long id) {
        log.debug("REST request to delete Uf : {}", id);
        ufService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
