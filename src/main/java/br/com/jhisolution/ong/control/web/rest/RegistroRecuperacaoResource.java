package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.RegistroRecuperacao;
import br.com.jhisolution.ong.control.service.RegistroRecuperacaoService;
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
 * REST controller for managing RegistroRecuperacao.
 */
@RestController
@RequestMapping("/api")
public class RegistroRecuperacaoResource {

    private final Logger log = LoggerFactory.getLogger(RegistroRecuperacaoResource.class);

    private static final String ENTITY_NAME = "registroRecuperacao";

    private final RegistroRecuperacaoService registroRecuperacaoService;

    public RegistroRecuperacaoResource(RegistroRecuperacaoService registroRecuperacaoService) {
        this.registroRecuperacaoService = registroRecuperacaoService;
    }

    /**
     * POST  /registro-recuperacaos : Create a new registroRecuperacao.
     *
     * @param registroRecuperacao the registroRecuperacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new registroRecuperacao, or with status 400 (Bad Request) if the registroRecuperacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/registro-recuperacaos")
    @Timed
    public ResponseEntity<RegistroRecuperacao> createRegistroRecuperacao(@Valid @RequestBody RegistroRecuperacao registroRecuperacao) throws URISyntaxException {
        log.debug("REST request to save RegistroRecuperacao : {}", registroRecuperacao);
        if (registroRecuperacao.getId() != null) {
            throw new BadRequestAlertException("A new registroRecuperacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegistroRecuperacao result = registroRecuperacaoService.save(registroRecuperacao);
        return ResponseEntity.created(new URI("/api/registro-recuperacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /registro-recuperacaos : Updates an existing registroRecuperacao.
     *
     * @param registroRecuperacao the registroRecuperacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated registroRecuperacao,
     * or with status 400 (Bad Request) if the registroRecuperacao is not valid,
     * or with status 500 (Internal Server Error) if the registroRecuperacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/registro-recuperacaos")
    @Timed
    public ResponseEntity<RegistroRecuperacao> updateRegistroRecuperacao(@Valid @RequestBody RegistroRecuperacao registroRecuperacao) throws URISyntaxException {
        log.debug("REST request to update RegistroRecuperacao : {}", registroRecuperacao);
        if (registroRecuperacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RegistroRecuperacao result = registroRecuperacaoService.save(registroRecuperacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, registroRecuperacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /registro-recuperacaos : get all the registroRecuperacaos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of registroRecuperacaos in body
     */
    @GetMapping("/registro-recuperacaos")
    @Timed
    public ResponseEntity<List<RegistroRecuperacao>> getAllRegistroRecuperacaos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("diario-is-null".equals(filter)) {
            log.debug("REST request to get all RegistroRecuperacaos where diario is null");
            return new ResponseEntity<>(registroRecuperacaoService.findAllWhereDiarioIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RegistroRecuperacaos");
        Page<RegistroRecuperacao> page = registroRecuperacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/registro-recuperacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /registro-recuperacaos/:id : get the "id" registroRecuperacao.
     *
     * @param id the id of the registroRecuperacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the registroRecuperacao, or with status 404 (Not Found)
     */
    @GetMapping("/registro-recuperacaos/{id}")
    @Timed
    public ResponseEntity<RegistroRecuperacao> getRegistroRecuperacao(@PathVariable Long id) {
        log.debug("REST request to get RegistroRecuperacao : {}", id);
        Optional<RegistroRecuperacao> registroRecuperacao = registroRecuperacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registroRecuperacao);
    }

    /**
     * DELETE  /registro-recuperacaos/:id : delete the "id" registroRecuperacao.
     *
     * @param id the id of the registroRecuperacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/registro-recuperacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteRegistroRecuperacao(@PathVariable Long id) {
        log.debug("REST request to delete RegistroRecuperacao : {}", id);
        registroRecuperacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
