package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.MotivoDiaNaoUtil;
import br.com.jhisolution.ong.control.service.MotivoDiaNaoUtilService;
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
 * REST controller for managing MotivoDiaNaoUtil.
 */
@RestController
@RequestMapping("/api")
public class MotivoDiaNaoUtilResource {

    private final Logger log = LoggerFactory.getLogger(MotivoDiaNaoUtilResource.class);

    private static final String ENTITY_NAME = "motivoDiaNaoUtil";

    private final MotivoDiaNaoUtilService motivoDiaNaoUtilService;

    public MotivoDiaNaoUtilResource(MotivoDiaNaoUtilService motivoDiaNaoUtilService) {
        this.motivoDiaNaoUtilService = motivoDiaNaoUtilService;
    }

    /**
     * POST  /motivo-dia-nao-utils : Create a new motivoDiaNaoUtil.
     *
     * @param motivoDiaNaoUtil the motivoDiaNaoUtil to create
     * @return the ResponseEntity with status 201 (Created) and with body the new motivoDiaNaoUtil, or with status 400 (Bad Request) if the motivoDiaNaoUtil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/motivo-dia-nao-utils")
    @Timed
    public ResponseEntity<MotivoDiaNaoUtil> createMotivoDiaNaoUtil(@Valid @RequestBody MotivoDiaNaoUtil motivoDiaNaoUtil) throws URISyntaxException {
        log.debug("REST request to save MotivoDiaNaoUtil : {}", motivoDiaNaoUtil);
        if (motivoDiaNaoUtil.getId() != null) {
            throw new BadRequestAlertException("A new motivoDiaNaoUtil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MotivoDiaNaoUtil result = motivoDiaNaoUtilService.save(motivoDiaNaoUtil);
        return ResponseEntity.created(new URI("/api/motivo-dia-nao-utils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /motivo-dia-nao-utils : Updates an existing motivoDiaNaoUtil.
     *
     * @param motivoDiaNaoUtil the motivoDiaNaoUtil to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated motivoDiaNaoUtil,
     * or with status 400 (Bad Request) if the motivoDiaNaoUtil is not valid,
     * or with status 500 (Internal Server Error) if the motivoDiaNaoUtil couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/motivo-dia-nao-utils")
    @Timed
    public ResponseEntity<MotivoDiaNaoUtil> updateMotivoDiaNaoUtil(@Valid @RequestBody MotivoDiaNaoUtil motivoDiaNaoUtil) throws URISyntaxException {
        log.debug("REST request to update MotivoDiaNaoUtil : {}", motivoDiaNaoUtil);
        if (motivoDiaNaoUtil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MotivoDiaNaoUtil result = motivoDiaNaoUtilService.save(motivoDiaNaoUtil);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, motivoDiaNaoUtil.getId().toString()))
            .body(result);
    }

    /**
     * GET  /motivo-dia-nao-utils : get all the motivoDiaNaoUtils.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of motivoDiaNaoUtils in body
     */
    @GetMapping("/motivo-dia-nao-utils")
    @Timed
    public ResponseEntity<List<MotivoDiaNaoUtil>> getAllMotivoDiaNaoUtils(Pageable pageable) {
        log.debug("REST request to get a page of MotivoDiaNaoUtils");
        Page<MotivoDiaNaoUtil> page = motivoDiaNaoUtilService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/motivo-dia-nao-utils");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /motivo-dia-nao-utils/:id : get the "id" motivoDiaNaoUtil.
     *
     * @param id the id of the motivoDiaNaoUtil to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the motivoDiaNaoUtil, or with status 404 (Not Found)
     */
    @GetMapping("/motivo-dia-nao-utils/{id}")
    @Timed
    public ResponseEntity<MotivoDiaNaoUtil> getMotivoDiaNaoUtil(@PathVariable Long id) {
        log.debug("REST request to get MotivoDiaNaoUtil : {}", id);
        Optional<MotivoDiaNaoUtil> motivoDiaNaoUtil = motivoDiaNaoUtilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(motivoDiaNaoUtil);
    }

    /**
     * DELETE  /motivo-dia-nao-utils/:id : delete the "id" motivoDiaNaoUtil.
     *
     * @param id the id of the motivoDiaNaoUtil to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/motivo-dia-nao-utils/{id}")
    @Timed
    public ResponseEntity<Void> deleteMotivoDiaNaoUtil(@PathVariable Long id) {
        log.debug("REST request to delete MotivoDiaNaoUtil : {}", id);
        motivoDiaNaoUtilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
