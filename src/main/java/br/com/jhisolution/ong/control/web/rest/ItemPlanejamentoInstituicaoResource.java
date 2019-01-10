package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.ItemPlanejamentoInstituicao;
import br.com.jhisolution.ong.control.service.ItemPlanejamentoInstituicaoService;
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
 * REST controller for managing ItemPlanejamentoInstituicao.
 */
@RestController
@RequestMapping("/api")
public class ItemPlanejamentoInstituicaoResource {

    private final Logger log = LoggerFactory.getLogger(ItemPlanejamentoInstituicaoResource.class);

    private static final String ENTITY_NAME = "itemPlanejamentoInstituicao";

    private final ItemPlanejamentoInstituicaoService itemPlanejamentoInstituicaoService;

    public ItemPlanejamentoInstituicaoResource(ItemPlanejamentoInstituicaoService itemPlanejamentoInstituicaoService) {
        this.itemPlanejamentoInstituicaoService = itemPlanejamentoInstituicaoService;
    }

    /**
     * POST  /item-planejamento-instituicaos : Create a new itemPlanejamentoInstituicao.
     *
     * @param itemPlanejamentoInstituicao the itemPlanejamentoInstituicao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemPlanejamentoInstituicao, or with status 400 (Bad Request) if the itemPlanejamentoInstituicao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-planejamento-instituicaos")
    @Timed
    public ResponseEntity<ItemPlanejamentoInstituicao> createItemPlanejamentoInstituicao(@Valid @RequestBody ItemPlanejamentoInstituicao itemPlanejamentoInstituicao) throws URISyntaxException {
        log.debug("REST request to save ItemPlanejamentoInstituicao : {}", itemPlanejamentoInstituicao);
        if (itemPlanejamentoInstituicao.getId() != null) {
            throw new BadRequestAlertException("A new itemPlanejamentoInstituicao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemPlanejamentoInstituicao result = itemPlanejamentoInstituicaoService.save(itemPlanejamentoInstituicao);
        return ResponseEntity.created(new URI("/api/item-planejamento-instituicaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-planejamento-instituicaos : Updates an existing itemPlanejamentoInstituicao.
     *
     * @param itemPlanejamentoInstituicao the itemPlanejamentoInstituicao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemPlanejamentoInstituicao,
     * or with status 400 (Bad Request) if the itemPlanejamentoInstituicao is not valid,
     * or with status 500 (Internal Server Error) if the itemPlanejamentoInstituicao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-planejamento-instituicaos")
    @Timed
    public ResponseEntity<ItemPlanejamentoInstituicao> updateItemPlanejamentoInstituicao(@Valid @RequestBody ItemPlanejamentoInstituicao itemPlanejamentoInstituicao) throws URISyntaxException {
        log.debug("REST request to update ItemPlanejamentoInstituicao : {}", itemPlanejamentoInstituicao);
        if (itemPlanejamentoInstituicao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemPlanejamentoInstituicao result = itemPlanejamentoInstituicaoService.save(itemPlanejamentoInstituicao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemPlanejamentoInstituicao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-planejamento-instituicaos : get all the itemPlanejamentoInstituicaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of itemPlanejamentoInstituicaos in body
     */
    @GetMapping("/item-planejamento-instituicaos")
    @Timed
    public ResponseEntity<List<ItemPlanejamentoInstituicao>> getAllItemPlanejamentoInstituicaos(Pageable pageable) {
        log.debug("REST request to get a page of ItemPlanejamentoInstituicaos");
        Page<ItemPlanejamentoInstituicao> page = itemPlanejamentoInstituicaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/item-planejamento-instituicaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /item-planejamento-instituicaos/:id : get the "id" itemPlanejamentoInstituicao.
     *
     * @param id the id of the itemPlanejamentoInstituicao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemPlanejamentoInstituicao, or with status 404 (Not Found)
     */
    @GetMapping("/item-planejamento-instituicaos/{id}")
    @Timed
    public ResponseEntity<ItemPlanejamentoInstituicao> getItemPlanejamentoInstituicao(@PathVariable Long id) {
        log.debug("REST request to get ItemPlanejamentoInstituicao : {}", id);
        Optional<ItemPlanejamentoInstituicao> itemPlanejamentoInstituicao = itemPlanejamentoInstituicaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemPlanejamentoInstituicao);
    }

    /**
     * DELETE  /item-planejamento-instituicaos/:id : delete the "id" itemPlanejamentoInstituicao.
     *
     * @param id the id of the itemPlanejamentoInstituicao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-planejamento-instituicaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemPlanejamentoInstituicao(@PathVariable Long id) {
        log.debug("REST request to delete ItemPlanejamentoInstituicao : {}", id);
        itemPlanejamentoInstituicaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
