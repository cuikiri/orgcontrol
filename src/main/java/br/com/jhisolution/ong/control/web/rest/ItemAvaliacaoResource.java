package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ItemAvaliacao;
import br.com.jhisolution.ong.control.service.ItemAvaliacaoService;
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
 * REST controller for managing ItemAvaliacao.
 */
@RestController
@RequestMapping("/api")
public class ItemAvaliacaoResource {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliacaoResource.class);

    private static final String ENTITY_NAME = "itemAvaliacao";

    private final ItemAvaliacaoService itemAvaliacaoService;

    public ItemAvaliacaoResource(ItemAvaliacaoService itemAvaliacaoService) {
        this.itemAvaliacaoService = itemAvaliacaoService;
    }

    /**
     * POST  /item-avaliacaos : Create a new itemAvaliacao.
     *
     * @param itemAvaliacao the itemAvaliacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAvaliacao, or with status 400 (Bad Request) if the itemAvaliacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-avaliacaos")
    @Timed
    public ResponseEntity<ItemAvaliacao> createItemAvaliacao(@Valid @RequestBody ItemAvaliacao itemAvaliacao) throws URISyntaxException {
        log.debug("REST request to save ItemAvaliacao : {}", itemAvaliacao);
        if (itemAvaliacao.getId() != null) {
            throw new BadRequestAlertException("A new itemAvaliacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAvaliacao result = itemAvaliacaoService.save(itemAvaliacao);
        return ResponseEntity.created(new URI("/api/item-avaliacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-avaliacaos : Updates an existing itemAvaliacao.
     *
     * @param itemAvaliacao the itemAvaliacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAvaliacao,
     * or with status 400 (Bad Request) if the itemAvaliacao is not valid,
     * or with status 500 (Internal Server Error) if the itemAvaliacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-avaliacaos")
    @Timed
    public ResponseEntity<ItemAvaliacao> updateItemAvaliacao(@Valid @RequestBody ItemAvaliacao itemAvaliacao) throws URISyntaxException {
        log.debug("REST request to update ItemAvaliacao : {}", itemAvaliacao);
        if (itemAvaliacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAvaliacao result = itemAvaliacaoService.save(itemAvaliacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAvaliacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-avaliacaos : get all the itemAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of itemAvaliacaos in body
     */
    @GetMapping("/item-avaliacaos")
    @Timed
    public ResponseEntity<List<ItemAvaliacao>> getAllItemAvaliacaos(Pageable pageable) {
        log.debug("REST request to get a page of ItemAvaliacaos");
        Page<ItemAvaliacao> page = itemAvaliacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/item-avaliacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /item-avaliacaos/:id : get the "id" itemAvaliacao.
     *
     * @param id the id of the itemAvaliacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAvaliacao, or with status 404 (Not Found)
     */
    @GetMapping("/item-avaliacaos/{id}")
    @Timed
    public ResponseEntity<ItemAvaliacao> getItemAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get ItemAvaliacao : {}", id);
        Optional<ItemAvaliacao> itemAvaliacao = itemAvaliacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAvaliacao);
    }

    /**
     * DELETE  /item-avaliacaos/:id : delete the "id" itemAvaliacao.
     *
     * @param id the id of the itemAvaliacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete ItemAvaliacao : {}", id);
        itemAvaliacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
