package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.HorarioMateria;
import br.com.jhisolution.ong.control.service.HorarioMateriaService;
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
 * REST controller for managing HorarioMateria.
 */
@RestController
@RequestMapping("/api")
public class HorarioMateriaResource {

    private final Logger log = LoggerFactory.getLogger(HorarioMateriaResource.class);

    private static final String ENTITY_NAME = "horarioMateria";

    private final HorarioMateriaService horarioMateriaService;

    public HorarioMateriaResource(HorarioMateriaService horarioMateriaService) {
        this.horarioMateriaService = horarioMateriaService;
    }

    /**
     * POST  /horario-materias : Create a new horarioMateria.
     *
     * @param horarioMateria the horarioMateria to create
     * @return the ResponseEntity with status 201 (Created) and with body the new horarioMateria, or with status 400 (Bad Request) if the horarioMateria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/horario-materias")
    @Timed
    public ResponseEntity<HorarioMateria> createHorarioMateria(@Valid @RequestBody HorarioMateria horarioMateria) throws URISyntaxException {
        log.debug("REST request to save HorarioMateria : {}", horarioMateria);
        if (horarioMateria.getId() != null) {
            throw new BadRequestAlertException("A new horarioMateria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HorarioMateria result = horarioMateriaService.save(horarioMateria);
        return ResponseEntity.created(new URI("/api/horario-materias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /horario-materias : Updates an existing horarioMateria.
     *
     * @param horarioMateria the horarioMateria to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated horarioMateria,
     * or with status 400 (Bad Request) if the horarioMateria is not valid,
     * or with status 500 (Internal Server Error) if the horarioMateria couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/horario-materias")
    @Timed
    public ResponseEntity<HorarioMateria> updateHorarioMateria(@Valid @RequestBody HorarioMateria horarioMateria) throws URISyntaxException {
        log.debug("REST request to update HorarioMateria : {}", horarioMateria);
        if (horarioMateria.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HorarioMateria result = horarioMateriaService.save(horarioMateria);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, horarioMateria.getId().toString()))
            .body(result);
    }

    /**
     * GET  /horario-materias : get all the horarioMaterias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of horarioMaterias in body
     */
    @GetMapping("/horario-materias")
    @Timed
    public ResponseEntity<List<HorarioMateria>> getAllHorarioMaterias(Pageable pageable) {
        log.debug("REST request to get a page of HorarioMaterias");
        Page<HorarioMateria> page = horarioMateriaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/horario-materias");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /horario-materias/:id : get the "id" horarioMateria.
     *
     * @param id the id of the horarioMateria to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the horarioMateria, or with status 404 (Not Found)
     */
    @GetMapping("/horario-materias/{id}")
    @Timed
    public ResponseEntity<HorarioMateria> getHorarioMateria(@PathVariable Long id) {
        log.debug("REST request to get HorarioMateria : {}", id);
        Optional<HorarioMateria> horarioMateria = horarioMateriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(horarioMateria);
    }

    /**
     * DELETE  /horario-materias/:id : delete the "id" horarioMateria.
     *
     * @param id the id of the horarioMateria to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/horario-materias/{id}")
    @Timed
    public ResponseEntity<Void> deleteHorarioMateria(@PathVariable Long id) {
        log.debug("REST request to delete HorarioMateria : {}", id);
        horarioMateriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
