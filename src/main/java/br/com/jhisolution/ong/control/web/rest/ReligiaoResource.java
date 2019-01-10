package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Religiao;
import br.com.jhisolution.ong.control.service.ReligiaoService;
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
 * REST controller for managing Religiao.
 */
@RestController
@RequestMapping("/api")
public class ReligiaoResource {

    private final Logger log = LoggerFactory.getLogger(ReligiaoResource.class);

    private static final String ENTITY_NAME = "religiao";

    private final ReligiaoService religiaoService;

    public ReligiaoResource(ReligiaoService religiaoService) {
        this.religiaoService = religiaoService;
    }

    /**
     * POST  /religiaos : Create a new religiao.
     *
     * @param religiao the religiao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new religiao, or with status 400 (Bad Request) if the religiao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/religiaos")
    @Timed
    public ResponseEntity<Religiao> createReligiao(@Valid @RequestBody Religiao religiao) throws URISyntaxException {
        log.debug("REST request to save Religiao : {}", religiao);
        if (religiao.getId() != null) {
            throw new BadRequestAlertException("A new religiao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Religiao result = religiaoService.save(religiao);
        return ResponseEntity.created(new URI("/api/religiaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /religiaos : Updates an existing religiao.
     *
     * @param religiao the religiao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated religiao,
     * or with status 400 (Bad Request) if the religiao is not valid,
     * or with status 500 (Internal Server Error) if the religiao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/religiaos")
    @Timed
    public ResponseEntity<Religiao> updateReligiao(@Valid @RequestBody Religiao religiao) throws URISyntaxException {
        log.debug("REST request to update Religiao : {}", religiao);
        if (religiao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Religiao result = religiaoService.save(religiao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, religiao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /religiaos : get all the religiaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of religiaos in body
     */
    @GetMapping("/religiaos")
    @Timed
    public ResponseEntity<List<Religiao>> getAllReligiaos(Pageable pageable) {
        log.debug("REST request to get a page of Religiaos");
        Page<Religiao> page = religiaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/religiaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /religiaos/:id : get the "id" religiao.
     *
     * @param id the id of the religiao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the religiao, or with status 404 (Not Found)
     */
    @GetMapping("/religiaos/{id}")
    @Timed
    public ResponseEntity<Religiao> getReligiao(@PathVariable Long id) {
        log.debug("REST request to get Religiao : {}", id);
        Optional<Religiao> religiao = religiaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(religiao);
    }

    /**
     * DELETE  /religiaos/:id : delete the "id" religiao.
     *
     * @param id the id of the religiao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/religiaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteReligiao(@PathVariable Long id) {
        log.debug("REST request to delete Religiao : {}", id);
        religiaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
