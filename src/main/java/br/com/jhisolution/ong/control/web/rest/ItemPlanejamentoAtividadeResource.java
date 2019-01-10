package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ItemPlanejamentoAtividade;
import br.com.jhisolution.ong.control.service.ItemPlanejamentoAtividadeService;
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
 * REST controller for managing ItemPlanejamentoAtividade.
 */
@RestController
@RequestMapping("/api")
public class ItemPlanejamentoAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(ItemPlanejamentoAtividadeResource.class);

    private static final String ENTITY_NAME = "itemPlanejamentoAtividade";

    private final ItemPlanejamentoAtividadeService itemPlanejamentoAtividadeService;

    public ItemPlanejamentoAtividadeResource(ItemPlanejamentoAtividadeService itemPlanejamentoAtividadeService) {
        this.itemPlanejamentoAtividadeService = itemPlanejamentoAtividadeService;
    }

    /**
     * POST  /item-planejamento-atividades : Create a new itemPlanejamentoAtividade.
     *
     * @param itemPlanejamentoAtividade the itemPlanejamentoAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemPlanejamentoAtividade, or with status 400 (Bad Request) if the itemPlanejamentoAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-planejamento-atividades")
    @Timed
    public ResponseEntity<ItemPlanejamentoAtividade> createItemPlanejamentoAtividade(@Valid @RequestBody ItemPlanejamentoAtividade itemPlanejamentoAtividade) throws URISyntaxException {
        log.debug("REST request to save ItemPlanejamentoAtividade : {}", itemPlanejamentoAtividade);
        if (itemPlanejamentoAtividade.getId() != null) {
            throw new BadRequestAlertException("A new itemPlanejamentoAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemPlanejamentoAtividade result = itemPlanejamentoAtividadeService.save(itemPlanejamentoAtividade);
        return ResponseEntity.created(new URI("/api/item-planejamento-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-planejamento-atividades : Updates an existing itemPlanejamentoAtividade.
     *
     * @param itemPlanejamentoAtividade the itemPlanejamentoAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemPlanejamentoAtividade,
     * or with status 400 (Bad Request) if the itemPlanejamentoAtividade is not valid,
     * or with status 500 (Internal Server Error) if the itemPlanejamentoAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-planejamento-atividades")
    @Timed
    public ResponseEntity<ItemPlanejamentoAtividade> updateItemPlanejamentoAtividade(@Valid @RequestBody ItemPlanejamentoAtividade itemPlanejamentoAtividade) throws URISyntaxException {
        log.debug("REST request to update ItemPlanejamentoAtividade : {}", itemPlanejamentoAtividade);
        if (itemPlanejamentoAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemPlanejamentoAtividade result = itemPlanejamentoAtividadeService.save(itemPlanejamentoAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemPlanejamentoAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-planejamento-atividades : get all the itemPlanejamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of itemPlanejamentoAtividades in body
     */
    @GetMapping("/item-planejamento-atividades")
    @Timed
    public ResponseEntity<List<ItemPlanejamentoAtividade>> getAllItemPlanejamentoAtividades(Pageable pageable) {
        log.debug("REST request to get a page of ItemPlanejamentoAtividades");
        Page<ItemPlanejamentoAtividade> page = itemPlanejamentoAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/item-planejamento-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /item-planejamento-atividades/:id : get the "id" itemPlanejamentoAtividade.
     *
     * @param id the id of the itemPlanejamentoAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemPlanejamentoAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/item-planejamento-atividades/{id}")
    @Timed
    public ResponseEntity<ItemPlanejamentoAtividade> getItemPlanejamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to get ItemPlanejamentoAtividade : {}", id);
        Optional<ItemPlanejamentoAtividade> itemPlanejamentoAtividade = itemPlanejamentoAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemPlanejamentoAtividade);
    }

    /**
     * DELETE  /item-planejamento-atividades/:id : delete the "id" itemPlanejamentoAtividade.
     *
     * @param id the id of the itemPlanejamentoAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-planejamento-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemPlanejamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to delete ItemPlanejamentoAtividade : {}", id);
        itemPlanejamentoAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
