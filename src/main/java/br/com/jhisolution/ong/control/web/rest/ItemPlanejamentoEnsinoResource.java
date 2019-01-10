package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ItemPlanejamentoEnsino;
import br.com.jhisolution.ong.control.service.ItemPlanejamentoEnsinoService;
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
 * REST controller for managing ItemPlanejamentoEnsino.
 */
@RestController
@RequestMapping("/api")
public class ItemPlanejamentoEnsinoResource {

    private final Logger log = LoggerFactory.getLogger(ItemPlanejamentoEnsinoResource.class);

    private static final String ENTITY_NAME = "itemPlanejamentoEnsino";

    private final ItemPlanejamentoEnsinoService itemPlanejamentoEnsinoService;

    public ItemPlanejamentoEnsinoResource(ItemPlanejamentoEnsinoService itemPlanejamentoEnsinoService) {
        this.itemPlanejamentoEnsinoService = itemPlanejamentoEnsinoService;
    }

    /**
     * POST  /item-planejamento-ensinos : Create a new itemPlanejamentoEnsino.
     *
     * @param itemPlanejamentoEnsino the itemPlanejamentoEnsino to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemPlanejamentoEnsino, or with status 400 (Bad Request) if the itemPlanejamentoEnsino has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-planejamento-ensinos")
    @Timed
    public ResponseEntity<ItemPlanejamentoEnsino> createItemPlanejamentoEnsino(@Valid @RequestBody ItemPlanejamentoEnsino itemPlanejamentoEnsino) throws URISyntaxException {
        log.debug("REST request to save ItemPlanejamentoEnsino : {}", itemPlanejamentoEnsino);
        if (itemPlanejamentoEnsino.getId() != null) {
            throw new BadRequestAlertException("A new itemPlanejamentoEnsino cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemPlanejamentoEnsino result = itemPlanejamentoEnsinoService.save(itemPlanejamentoEnsino);
        return ResponseEntity.created(new URI("/api/item-planejamento-ensinos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-planejamento-ensinos : Updates an existing itemPlanejamentoEnsino.
     *
     * @param itemPlanejamentoEnsino the itemPlanejamentoEnsino to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemPlanejamentoEnsino,
     * or with status 400 (Bad Request) if the itemPlanejamentoEnsino is not valid,
     * or with status 500 (Internal Server Error) if the itemPlanejamentoEnsino couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-planejamento-ensinos")
    @Timed
    public ResponseEntity<ItemPlanejamentoEnsino> updateItemPlanejamentoEnsino(@Valid @RequestBody ItemPlanejamentoEnsino itemPlanejamentoEnsino) throws URISyntaxException {
        log.debug("REST request to update ItemPlanejamentoEnsino : {}", itemPlanejamentoEnsino);
        if (itemPlanejamentoEnsino.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemPlanejamentoEnsino result = itemPlanejamentoEnsinoService.save(itemPlanejamentoEnsino);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemPlanejamentoEnsino.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-planejamento-ensinos : get all the itemPlanejamentoEnsinos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of itemPlanejamentoEnsinos in body
     */
    @GetMapping("/item-planejamento-ensinos")
    @Timed
    public ResponseEntity<List<ItemPlanejamentoEnsino>> getAllItemPlanejamentoEnsinos(Pageable pageable) {
        log.debug("REST request to get a page of ItemPlanejamentoEnsinos");
        Page<ItemPlanejamentoEnsino> page = itemPlanejamentoEnsinoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/item-planejamento-ensinos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /item-planejamento-ensinos/:id : get the "id" itemPlanejamentoEnsino.
     *
     * @param id the id of the itemPlanejamentoEnsino to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemPlanejamentoEnsino, or with status 404 (Not Found)
     */
    @GetMapping("/item-planejamento-ensinos/{id}")
    @Timed
    public ResponseEntity<ItemPlanejamentoEnsino> getItemPlanejamentoEnsino(@PathVariable Long id) {
        log.debug("REST request to get ItemPlanejamentoEnsino : {}", id);
        Optional<ItemPlanejamentoEnsino> itemPlanejamentoEnsino = itemPlanejamentoEnsinoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemPlanejamentoEnsino);
    }

    /**
     * DELETE  /item-planejamento-ensinos/:id : delete the "id" itemPlanejamentoEnsino.
     *
     * @param id the id of the itemPlanejamentoEnsino to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-planejamento-ensinos/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemPlanejamentoEnsino(@PathVariable Long id) {
        log.debug("REST request to delete ItemPlanejamentoEnsino : {}", id);
        itemPlanejamentoEnsinoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
