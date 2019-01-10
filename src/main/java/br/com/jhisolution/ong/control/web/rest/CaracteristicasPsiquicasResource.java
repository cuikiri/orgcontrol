package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.CaracteristicasPsiquicas;
import br.com.jhisolution.ong.control.service.CaracteristicasPsiquicasService;
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
 * REST controller for managing CaracteristicasPsiquicas.
 */
@RestController
@RequestMapping("/api")
public class CaracteristicasPsiquicasResource {

    private final Logger log = LoggerFactory.getLogger(CaracteristicasPsiquicasResource.class);

    private static final String ENTITY_NAME = "caracteristicasPsiquicas";

    private final CaracteristicasPsiquicasService caracteristicasPsiquicasService;

    public CaracteristicasPsiquicasResource(CaracteristicasPsiquicasService caracteristicasPsiquicasService) {
        this.caracteristicasPsiquicasService = caracteristicasPsiquicasService;
    }

    /**
     * POST  /caracteristicas-psiquicas : Create a new caracteristicasPsiquicas.
     *
     * @param caracteristicasPsiquicas the caracteristicasPsiquicas to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caracteristicasPsiquicas, or with status 400 (Bad Request) if the caracteristicasPsiquicas has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/caracteristicas-psiquicas")
    @Timed
    public ResponseEntity<CaracteristicasPsiquicas> createCaracteristicasPsiquicas(@Valid @RequestBody CaracteristicasPsiquicas caracteristicasPsiquicas) throws URISyntaxException {
        log.debug("REST request to save CaracteristicasPsiquicas : {}", caracteristicasPsiquicas);
        if (caracteristicasPsiquicas.getId() != null) {
            throw new BadRequestAlertException("A new caracteristicasPsiquicas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaracteristicasPsiquicas result = caracteristicasPsiquicasService.save(caracteristicasPsiquicas);
        return ResponseEntity.created(new URI("/api/caracteristicas-psiquicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /caracteristicas-psiquicas : Updates an existing caracteristicasPsiquicas.
     *
     * @param caracteristicasPsiquicas the caracteristicasPsiquicas to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caracteristicasPsiquicas,
     * or with status 400 (Bad Request) if the caracteristicasPsiquicas is not valid,
     * or with status 500 (Internal Server Error) if the caracteristicasPsiquicas couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/caracteristicas-psiquicas")
    @Timed
    public ResponseEntity<CaracteristicasPsiquicas> updateCaracteristicasPsiquicas(@Valid @RequestBody CaracteristicasPsiquicas caracteristicasPsiquicas) throws URISyntaxException {
        log.debug("REST request to update CaracteristicasPsiquicas : {}", caracteristicasPsiquicas);
        if (caracteristicasPsiquicas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaracteristicasPsiquicas result = caracteristicasPsiquicasService.save(caracteristicasPsiquicas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caracteristicasPsiquicas.getId().toString()))
            .body(result);
    }

    /**
     * GET  /caracteristicas-psiquicas : get all the caracteristicasPsiquicas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of caracteristicasPsiquicas in body
     */
    @GetMapping("/caracteristicas-psiquicas")
    @Timed
    public ResponseEntity<List<CaracteristicasPsiquicas>> getAllCaracteristicasPsiquicas(Pageable pageable) {
        log.debug("REST request to get a page of CaracteristicasPsiquicas");
        Page<CaracteristicasPsiquicas> page = caracteristicasPsiquicasService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/caracteristicas-psiquicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /caracteristicas-psiquicas/:id : get the "id" caracteristicasPsiquicas.
     *
     * @param id the id of the caracteristicasPsiquicas to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caracteristicasPsiquicas, or with status 404 (Not Found)
     */
    @GetMapping("/caracteristicas-psiquicas/{id}")
    @Timed
    public ResponseEntity<CaracteristicasPsiquicas> getCaracteristicasPsiquicas(@PathVariable Long id) {
        log.debug("REST request to get CaracteristicasPsiquicas : {}", id);
        Optional<CaracteristicasPsiquicas> caracteristicasPsiquicas = caracteristicasPsiquicasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caracteristicasPsiquicas);
    }

    /**
     * DELETE  /caracteristicas-psiquicas/:id : delete the "id" caracteristicasPsiquicas.
     *
     * @param id the id of the caracteristicasPsiquicas to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/caracteristicas-psiquicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaracteristicasPsiquicas(@PathVariable Long id) {
        log.debug("REST request to delete CaracteristicasPsiquicas : {}", id);
        caracteristicasPsiquicasService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
