package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.DadosMedico;
import br.com.jhisolution.ong.control.service.DadosMedicoService;
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
 * REST controller for managing DadosMedico.
 */
@RestController
@RequestMapping("/api")
public class DadosMedicoResource {

    private final Logger log = LoggerFactory.getLogger(DadosMedicoResource.class);

    private static final String ENTITY_NAME = "dadosMedico";

    private final DadosMedicoService dadosMedicoService;

    public DadosMedicoResource(DadosMedicoService dadosMedicoService) {
        this.dadosMedicoService = dadosMedicoService;
    }

    /**
     * POST  /dados-medicos : Create a new dadosMedico.
     *
     * @param dadosMedico the dadosMedico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dadosMedico, or with status 400 (Bad Request) if the dadosMedico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dados-medicos")
    @Timed
    public ResponseEntity<DadosMedico> createDadosMedico(@Valid @RequestBody DadosMedico dadosMedico) throws URISyntaxException {
        log.debug("REST request to save DadosMedico : {}", dadosMedico);
        if (dadosMedico.getId() != null) {
            throw new BadRequestAlertException("A new dadosMedico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DadosMedico result = dadosMedicoService.save(dadosMedico);
        return ResponseEntity.created(new URI("/api/dados-medicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dados-medicos : Updates an existing dadosMedico.
     *
     * @param dadosMedico the dadosMedico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dadosMedico,
     * or with status 400 (Bad Request) if the dadosMedico is not valid,
     * or with status 500 (Internal Server Error) if the dadosMedico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dados-medicos")
    @Timed
    public ResponseEntity<DadosMedico> updateDadosMedico(@Valid @RequestBody DadosMedico dadosMedico) throws URISyntaxException {
        log.debug("REST request to update DadosMedico : {}", dadosMedico);
        if (dadosMedico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DadosMedico result = dadosMedicoService.save(dadosMedico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dadosMedico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dados-medicos : get all the dadosMedicos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of dadosMedicos in body
     */
    @GetMapping("/dados-medicos")
    @Timed
    public ResponseEntity<List<DadosMedico>> getAllDadosMedicos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("colaborador-is-null".equals(filter)) {
            log.debug("REST request to get all DadosMedicos where colaborador is null");
            return new ResponseEntity<>(dadosMedicoService.findAllWhereColaboradorIsNull(),
                    HttpStatus.OK);
        }
        if ("aluno-is-null".equals(filter)) {
            log.debug("REST request to get all DadosMedicos where aluno is null");
            return new ResponseEntity<>(dadosMedicoService.findAllWhereAlunoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of DadosMedicos");
        Page<DadosMedico> page = dadosMedicoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dados-medicos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dados-medicos/:id : get the "id" dadosMedico.
     *
     * @param id the id of the dadosMedico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dadosMedico, or with status 404 (Not Found)
     */
    @GetMapping("/dados-medicos/{id}")
    @Timed
    public ResponseEntity<DadosMedico> getDadosMedico(@PathVariable Long id) {
        log.debug("REST request to get DadosMedico : {}", id);
        Optional<DadosMedico> dadosMedico = dadosMedicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dadosMedico);
    }

    /**
     * DELETE  /dados-medicos/:id : delete the "id" dadosMedico.
     *
     * @param id the id of the dadosMedico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dados-medicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDadosMedico(@PathVariable Long id) {
        log.debug("REST request to delete DadosMedico : {}", id);
        dadosMedicoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
