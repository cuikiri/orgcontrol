package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativa;
import br.com.jhisolution.ong.control.service.OpcaoRespAvalOptativaService;
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
 * REST controller for managing OpcaoRespAvalOptativa.
 */
@RestController
@RequestMapping("/api")
public class OpcaoRespAvalOptativaResource {

    private final Logger log = LoggerFactory.getLogger(OpcaoRespAvalOptativaResource.class);

    private static final String ENTITY_NAME = "opcaoRespAvalOptativa";

    private final OpcaoRespAvalOptativaService opcaoRespAvalOptativaService;

    public OpcaoRespAvalOptativaResource(OpcaoRespAvalOptativaService opcaoRespAvalOptativaService) {
        this.opcaoRespAvalOptativaService = opcaoRespAvalOptativaService;
    }

    /**
     * POST  /opcao-resp-aval-optativas : Create a new opcaoRespAvalOptativa.
     *
     * @param opcaoRespAvalOptativa the opcaoRespAvalOptativa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new opcaoRespAvalOptativa, or with status 400 (Bad Request) if the opcaoRespAvalOptativa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/opcao-resp-aval-optativas")
    @Timed
    public ResponseEntity<OpcaoRespAvalOptativa> createOpcaoRespAvalOptativa(@Valid @RequestBody OpcaoRespAvalOptativa opcaoRespAvalOptativa) throws URISyntaxException {
        log.debug("REST request to save OpcaoRespAvalOptativa : {}", opcaoRespAvalOptativa);
        if (opcaoRespAvalOptativa.getId() != null) {
            throw new BadRequestAlertException("A new opcaoRespAvalOptativa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpcaoRespAvalOptativa result = opcaoRespAvalOptativaService.save(opcaoRespAvalOptativa);
        return ResponseEntity.created(new URI("/api/opcao-resp-aval-optativas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /opcao-resp-aval-optativas : Updates an existing opcaoRespAvalOptativa.
     *
     * @param opcaoRespAvalOptativa the opcaoRespAvalOptativa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated opcaoRespAvalOptativa,
     * or with status 400 (Bad Request) if the opcaoRespAvalOptativa is not valid,
     * or with status 500 (Internal Server Error) if the opcaoRespAvalOptativa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/opcao-resp-aval-optativas")
    @Timed
    public ResponseEntity<OpcaoRespAvalOptativa> updateOpcaoRespAvalOptativa(@Valid @RequestBody OpcaoRespAvalOptativa opcaoRespAvalOptativa) throws URISyntaxException {
        log.debug("REST request to update OpcaoRespAvalOptativa : {}", opcaoRespAvalOptativa);
        if (opcaoRespAvalOptativa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OpcaoRespAvalOptativa result = opcaoRespAvalOptativaService.save(opcaoRespAvalOptativa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, opcaoRespAvalOptativa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /opcao-resp-aval-optativas : get all the opcaoRespAvalOptativas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of opcaoRespAvalOptativas in body
     */
    @GetMapping("/opcao-resp-aval-optativas")
    @Timed
    public ResponseEntity<List<OpcaoRespAvalOptativa>> getAllOpcaoRespAvalOptativas(Pageable pageable) {
        log.debug("REST request to get a page of OpcaoRespAvalOptativas");
        Page<OpcaoRespAvalOptativa> page = opcaoRespAvalOptativaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/opcao-resp-aval-optativas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /opcao-resp-aval-optativas/:id : get the "id" opcaoRespAvalOptativa.
     *
     * @param id the id of the opcaoRespAvalOptativa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the opcaoRespAvalOptativa, or with status 404 (Not Found)
     */
    @GetMapping("/opcao-resp-aval-optativas/{id}")
    @Timed
    public ResponseEntity<OpcaoRespAvalOptativa> getOpcaoRespAvalOptativa(@PathVariable Long id) {
        log.debug("REST request to get OpcaoRespAvalOptativa : {}", id);
        Optional<OpcaoRespAvalOptativa> opcaoRespAvalOptativa = opcaoRespAvalOptativaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opcaoRespAvalOptativa);
    }

    /**
     * DELETE  /opcao-resp-aval-optativas/:id : delete the "id" opcaoRespAvalOptativa.
     *
     * @param id the id of the opcaoRespAvalOptativa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/opcao-resp-aval-optativas/{id}")
    @Timed
    public ResponseEntity<Void> deleteOpcaoRespAvalOptativa(@PathVariable Long id) {
        log.debug("REST request to delete OpcaoRespAvalOptativa : {}", id);
        opcaoRespAvalOptativaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
