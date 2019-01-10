package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ItemPlanejamentoEnsinoService;
import br.com.jhisolution.ong.control.domain.ItemPlanejamentoEnsino;
import br.com.jhisolution.ong.control.repository.ItemPlanejamentoEnsinoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ItemPlanejamentoEnsino.
 */
@Service
@Transactional
public class ItemPlanejamentoEnsinoServiceImpl implements ItemPlanejamentoEnsinoService {

    private final Logger log = LoggerFactory.getLogger(ItemPlanejamentoEnsinoServiceImpl.class);

    private final ItemPlanejamentoEnsinoRepository itemPlanejamentoEnsinoRepository;

    public ItemPlanejamentoEnsinoServiceImpl(ItemPlanejamentoEnsinoRepository itemPlanejamentoEnsinoRepository) {
        this.itemPlanejamentoEnsinoRepository = itemPlanejamentoEnsinoRepository;
    }

    /**
     * Save a itemPlanejamentoEnsino.
     *
     * @param itemPlanejamentoEnsino the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemPlanejamentoEnsino save(ItemPlanejamentoEnsino itemPlanejamentoEnsino) {
        log.debug("Request to save ItemPlanejamentoEnsino : {}", itemPlanejamentoEnsino);
        return itemPlanejamentoEnsinoRepository.save(itemPlanejamentoEnsino);
    }

    /**
     * Get all the itemPlanejamentoEnsinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemPlanejamentoEnsino> findAll(Pageable pageable) {
        log.debug("Request to get all ItemPlanejamentoEnsinos");
        return itemPlanejamentoEnsinoRepository.findAll(pageable);
    }


    /**
     * Get one itemPlanejamentoEnsino by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemPlanejamentoEnsino> findOne(Long id) {
        log.debug("Request to get ItemPlanejamentoEnsino : {}", id);
        return itemPlanejamentoEnsinoRepository.findById(id);
    }

    /**
     * Delete the itemPlanejamentoEnsino by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemPlanejamentoEnsino : {}", id);
        itemPlanejamentoEnsinoRepository.deleteById(id);
    }
}
