package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ItemAvaliacaoEconomica;
import br.com.jhisolution.ong.control.service.ItemAvaliacaoEconomicaService;
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
 * REST controller for managing ItemAvaliacaoEconomica.
 */
@RestController
@RequestMapping("/api")
public class ItemAvaliacaoEconomicaResource {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliacaoEconomicaResource.class);

    private static final String ENTITY_NAME = "itemAvaliacaoEconomica";

    private final ItemAvaliacaoEconomicaService itemAvaliacaoEconomicaService;

    public ItemAvaliacaoEconomicaResource(ItemAvaliacaoEconomicaService itemAvaliacaoEconomicaService) {
        this.itemAvaliacaoEconomicaService = itemAvaliacaoEconomicaService;
    }

    /**
     * POST  /item-avaliacao-economicas : Create a new itemAvaliacaoEconomica.
     *
     * @param itemAvaliacaoEconomica the itemAvaliacaoEconomica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAvaliacaoEconomica, or with status 400 (Bad Request) if the itemAvaliacaoEconomica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-avaliacao-economicas")
    @Timed
    public ResponseEntity<ItemAvaliacaoEconomica> createItemAvaliacaoEconomica(@Valid @RequestBody ItemAvaliacaoEconomica itemAvaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to save ItemAvaliacaoEconomica : {}", itemAvaliacaoEconomica);
        if (itemAvaliacaoEconomica.getId() != null) {
            throw new BadRequestAlertException("A new itemAvaliacaoEconomica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAvaliacaoEconomica result = itemAvaliacaoEconomicaService.save(itemAvaliacaoEconomica);
        return ResponseEntity.created(new URI("/api/item-avaliacao-economicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-avaliacao-economicas : Updates an existing itemAvaliacaoEconomica.
     *
     * @param itemAvaliacaoEconomica the itemAvaliacaoEconomica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAvaliacaoEconomica,
     * or with status 400 (Bad Request) if the itemAvaliacaoEconomica is not valid,
     * or with status 500 (Internal Server Error) if the itemAvaliacaoEconomica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-avaliacao-economicas")
    @Timed
    public ResponseEntity<ItemAvaliacaoEconomica> updateItemAvaliacaoEconomica(@Valid @RequestBody ItemAvaliacaoEconomica itemAvaliacaoEconomica) throws URISyntaxException {
        log.debug("REST request to update ItemAvaliacaoEconomica : {}", itemAvaliacaoEconomica);
        if (itemAvaliacaoEconomica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAvaliacaoEconomica result = itemAvaliacaoEconomicaService.save(itemAvaliacaoEconomica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAvaliacaoEconomica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-avaliacao-economicas : get all the itemAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of itemAvaliacaoEconomicas in body
     */
    @GetMapping("/item-avaliacao-economicas")
    @Timed
    public ResponseEntity<List<ItemAvaliacaoEconomica>> getAllItemAvaliacaoEconomicas(Pageable pageable) {
        log.debug("REST request to get a page of ItemAvaliacaoEconomicas");
        Page<ItemAvaliacaoEconomica> page = itemAvaliacaoEconomicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/item-avaliacao-economicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /item-avaliacao-economicas/:id : get the "id" itemAvaliacaoEconomica.
     *
     * @param id the id of the itemAvaliacaoEconomica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAvaliacaoEconomica, or with status 404 (Not Found)
     */
    @GetMapping("/item-avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<ItemAvaliacaoEconomica> getItemAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to get ItemAvaliacaoEconomica : {}", id);
        Optional<ItemAvaliacaoEconomica> itemAvaliacaoEconomica = itemAvaliacaoEconomicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAvaliacaoEconomica);
    }

    /**
     * DELETE  /item-avaliacao-economicas/:id : delete the "id" itemAvaliacaoEconomica.
     *
     * @param id the id of the itemAvaliacaoEconomica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-avaliacao-economicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAvaliacaoEconomica(@PathVariable Long id) {
        log.debug("REST request to delete ItemAvaliacaoEconomica : {}", id);
        itemAvaliacaoEconomicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
