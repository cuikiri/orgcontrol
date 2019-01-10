package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.DiaNaoUtil;
import br.com.jhisolution.ong.control.service.DiaNaoUtilService;
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
 * REST controller for managing DiaNaoUtil.
 */
@RestController
@RequestMapping("/api")
public class DiaNaoUtilResource {

    private final Logger log = LoggerFactory.getLogger(DiaNaoUtilResource.class);

    private static final String ENTITY_NAME = "diaNaoUtil";

    private final DiaNaoUtilService diaNaoUtilService;

    public DiaNaoUtilResource(DiaNaoUtilService diaNaoUtilService) {
        this.diaNaoUtilService = diaNaoUtilService;
    }

    /**
     * POST  /dia-nao-utils : Create a new diaNaoUtil.
     *
     * @param diaNaoUtil the diaNaoUtil to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diaNaoUtil, or with status 400 (Bad Request) if the diaNaoUtil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dia-nao-utils")
    @Timed
    public ResponseEntity<DiaNaoUtil> createDiaNaoUtil(@Valid @RequestBody DiaNaoUtil diaNaoUtil) throws URISyntaxException {
        log.debug("REST request to save DiaNaoUtil : {}", diaNaoUtil);
        if (diaNaoUtil.getId() != null) {
            throw new BadRequestAlertException("A new diaNaoUtil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiaNaoUtil result = diaNaoUtilService.save(diaNaoUtil);
        return ResponseEntity.created(new URI("/api/dia-nao-utils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dia-nao-utils : Updates an existing diaNaoUtil.
     *
     * @param diaNaoUtil the diaNaoUtil to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diaNaoUtil,
     * or with status 400 (Bad Request) if the diaNaoUtil is not valid,
     * or with status 500 (Internal Server Error) if the diaNaoUtil couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dia-nao-utils")
    @Timed
    public ResponseEntity<DiaNaoUtil> updateDiaNaoUtil(@Valid @RequestBody DiaNaoUtil diaNaoUtil) throws URISyntaxException {
        log.debug("REST request to update DiaNaoUtil : {}", diaNaoUtil);
        if (diaNaoUtil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiaNaoUtil result = diaNaoUtilService.save(diaNaoUtil);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diaNaoUtil.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dia-nao-utils : get all the diaNaoUtils.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of diaNaoUtils in body
     */
    @GetMapping("/dia-nao-utils")
    @Timed
    public ResponseEntity<List<DiaNaoUtil>> getAllDiaNaoUtils(Pageable pageable) {
        log.debug("REST request to get a page of DiaNaoUtils");
        Page<DiaNaoUtil> page = diaNaoUtilService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dia-nao-utils");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dia-nao-utils/:id : get the "id" diaNaoUtil.
     *
     * @param id the id of the diaNaoUtil to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diaNaoUtil, or with status 404 (Not Found)
     */
    @GetMapping("/dia-nao-utils/{id}")
    @Timed
    public ResponseEntity<DiaNaoUtil> getDiaNaoUtil(@PathVariable Long id) {
        log.debug("REST request to get DiaNaoUtil : {}", id);
        Optional<DiaNaoUtil> diaNaoUtil = diaNaoUtilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diaNaoUtil);
    }

    /**
     * DELETE  /dia-nao-utils/:id : delete the "id" diaNaoUtil.
     *
     * @param id the id of the diaNaoUtil to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dia-nao-utils/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiaNaoUtil(@PathVariable Long id) {
        log.debug("REST request to delete DiaNaoUtil : {}", id);
        diaNaoUtilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
