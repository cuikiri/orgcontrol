package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ConteudoProgramatico;
import br.com.jhisolution.ong.control.service.ConteudoProgramaticoService;
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
 * REST controller for managing ConteudoProgramatico.
 */
@RestController
@RequestMapping("/api")
public class ConteudoProgramaticoResource {

    private final Logger log = LoggerFactory.getLogger(ConteudoProgramaticoResource.class);

    private static final String ENTITY_NAME = "conteudoProgramatico";

    private final ConteudoProgramaticoService conteudoProgramaticoService;

    public ConteudoProgramaticoResource(ConteudoProgramaticoService conteudoProgramaticoService) {
        this.conteudoProgramaticoService = conteudoProgramaticoService;
    }

    /**
     * POST  /conteudo-programaticos : Create a new conteudoProgramatico.
     *
     * @param conteudoProgramatico the conteudoProgramatico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conteudoProgramatico, or with status 400 (Bad Request) if the conteudoProgramatico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conteudo-programaticos")
    @Timed
    public ResponseEntity<ConteudoProgramatico> createConteudoProgramatico(@Valid @RequestBody ConteudoProgramatico conteudoProgramatico) throws URISyntaxException {
        log.debug("REST request to save ConteudoProgramatico : {}", conteudoProgramatico);
        if (conteudoProgramatico.getId() != null) {
            throw new BadRequestAlertException("A new conteudoProgramatico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConteudoProgramatico result = conteudoProgramaticoService.save(conteudoProgramatico);
        return ResponseEntity.created(new URI("/api/conteudo-programaticos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conteudo-programaticos : Updates an existing conteudoProgramatico.
     *
     * @param conteudoProgramatico the conteudoProgramatico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conteudoProgramatico,
     * or with status 400 (Bad Request) if the conteudoProgramatico is not valid,
     * or with status 500 (Internal Server Error) if the conteudoProgramatico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conteudo-programaticos")
    @Timed
    public ResponseEntity<ConteudoProgramatico> updateConteudoProgramatico(@Valid @RequestBody ConteudoProgramatico conteudoProgramatico) throws URISyntaxException {
        log.debug("REST request to update ConteudoProgramatico : {}", conteudoProgramatico);
        if (conteudoProgramatico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConteudoProgramatico result = conteudoProgramaticoService.save(conteudoProgramatico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conteudoProgramatico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conteudo-programaticos : get all the conteudoProgramaticos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conteudoProgramaticos in body
     */
    @GetMapping("/conteudo-programaticos")
    @Timed
    public ResponseEntity<List<ConteudoProgramatico>> getAllConteudoProgramaticos(Pageable pageable) {
        log.debug("REST request to get a page of ConteudoProgramaticos");
        Page<ConteudoProgramatico> page = conteudoProgramaticoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conteudo-programaticos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /conteudo-programaticos/:id : get the "id" conteudoProgramatico.
     *
     * @param id the id of the conteudoProgramatico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conteudoProgramatico, or with status 404 (Not Found)
     */
    @GetMapping("/conteudo-programaticos/{id}")
    @Timed
    public ResponseEntity<ConteudoProgramatico> getConteudoProgramatico(@PathVariable Long id) {
        log.debug("REST request to get ConteudoProgramatico : {}", id);
        Optional<ConteudoProgramatico> conteudoProgramatico = conteudoProgramaticoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conteudoProgramatico);
    }

    /**
     * DELETE  /conteudo-programaticos/:id : delete the "id" conteudoProgramatico.
     *
     * @param id the id of the conteudoProgramatico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conteudo-programaticos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConteudoProgramatico(@PathVariable Long id) {
        log.debug("REST request to delete ConteudoProgramatico : {}", id);
        conteudoProgramaticoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
