package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ItemAcompanhamentoAtividade;
import br.com.jhisolution.ong.control.service.ItemAcompanhamentoAtividadeService;
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
 * REST controller for managing ItemAcompanhamentoAtividade.
 */
@RestController
@RequestMapping("/api")
public class ItemAcompanhamentoAtividadeResource {

    private final Logger log = LoggerFactory.getLogger(ItemAcompanhamentoAtividadeResource.class);

    private static final String ENTITY_NAME = "itemAcompanhamentoAtividade";

    private final ItemAcompanhamentoAtividadeService itemAcompanhamentoAtividadeService;

    public ItemAcompanhamentoAtividadeResource(ItemAcompanhamentoAtividadeService itemAcompanhamentoAtividadeService) {
        this.itemAcompanhamentoAtividadeService = itemAcompanhamentoAtividadeService;
    }

    /**
     * POST  /item-acompanhamento-atividades : Create a new itemAcompanhamentoAtividade.
     *
     * @param itemAcompanhamentoAtividade the itemAcompanhamentoAtividade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAcompanhamentoAtividade, or with status 400 (Bad Request) if the itemAcompanhamentoAtividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-acompanhamento-atividades")
    @Timed
    public ResponseEntity<ItemAcompanhamentoAtividade> createItemAcompanhamentoAtividade(@Valid @RequestBody ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) throws URISyntaxException {
        log.debug("REST request to save ItemAcompanhamentoAtividade : {}", itemAcompanhamentoAtividade);
        if (itemAcompanhamentoAtividade.getId() != null) {
            throw new BadRequestAlertException("A new itemAcompanhamentoAtividade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAcompanhamentoAtividade result = itemAcompanhamentoAtividadeService.save(itemAcompanhamentoAtividade);
        return ResponseEntity.created(new URI("/api/item-acompanhamento-atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-acompanhamento-atividades : Updates an existing itemAcompanhamentoAtividade.
     *
     * @param itemAcompanhamentoAtividade the itemAcompanhamentoAtividade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAcompanhamentoAtividade,
     * or with status 400 (Bad Request) if the itemAcompanhamentoAtividade is not valid,
     * or with status 500 (Internal Server Error) if the itemAcompanhamentoAtividade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-acompanhamento-atividades")
    @Timed
    public ResponseEntity<ItemAcompanhamentoAtividade> updateItemAcompanhamentoAtividade(@Valid @RequestBody ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) throws URISyntaxException {
        log.debug("REST request to update ItemAcompanhamentoAtividade : {}", itemAcompanhamentoAtividade);
        if (itemAcompanhamentoAtividade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAcompanhamentoAtividade result = itemAcompanhamentoAtividadeService.save(itemAcompanhamentoAtividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAcompanhamentoAtividade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-acompanhamento-atividades : get all the itemAcompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of itemAcompanhamentoAtividades in body
     */
    @GetMapping("/item-acompanhamento-atividades")
    @Timed
    public ResponseEntity<List<ItemAcompanhamentoAtividade>> getAllItemAcompanhamentoAtividades(Pageable pageable) {
        log.debug("REST request to get a page of ItemAcompanhamentoAtividades");
        Page<ItemAcompanhamentoAtividade> page = itemAcompanhamentoAtividadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/item-acompanhamento-atividades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /item-acompanhamento-atividades/:id : get the "id" itemAcompanhamentoAtividade.
     *
     * @param id the id of the itemAcompanhamentoAtividade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAcompanhamentoAtividade, or with status 404 (Not Found)
     */
    @GetMapping("/item-acompanhamento-atividades/{id}")
    @Timed
    public ResponseEntity<ItemAcompanhamentoAtividade> getItemAcompanhamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to get ItemAcompanhamentoAtividade : {}", id);
        Optional<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividade = itemAcompanhamentoAtividadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAcompanhamentoAtividade);
    }

    /**
     * DELETE  /item-acompanhamento-atividades/:id : delete the "id" itemAcompanhamentoAtividade.
     *
     * @param id the id of the itemAcompanhamentoAtividade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-acompanhamento-atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAcompanhamentoAtividade(@PathVariable Long id) {
        log.debug("REST request to delete ItemAcompanhamentoAtividade : {}", id);
        itemAcompanhamentoAtividadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
