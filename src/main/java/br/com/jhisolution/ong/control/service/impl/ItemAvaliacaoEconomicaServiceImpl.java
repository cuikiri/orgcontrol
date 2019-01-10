package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ItemAvaliacaoEconomicaService;
import br.com.jhisolution.ong.control.domain.ItemAvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.ItemAvaliacaoEconomicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ItemAvaliacaoEconomica.
 */
@Service
@Transactional
public class ItemAvaliacaoEconomicaServiceImpl implements ItemAvaliacaoEconomicaService {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliacaoEconomicaServiceImpl.class);

    private final ItemAvaliacaoEconomicaRepository itemAvaliacaoEconomicaRepository;

    public ItemAvaliacaoEconomicaServiceImpl(ItemAvaliacaoEconomicaRepository itemAvaliacaoEconomicaRepository) {
        this.itemAvaliacaoEconomicaRepository = itemAvaliacaoEconomicaRepository;
    }

    /**
     * Save a itemAvaliacaoEconomica.
     *
     * @param itemAvaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemAvaliacaoEconomica save(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        log.debug("Request to save ItemAvaliacaoEconomica : {}", itemAvaliacaoEconomica);
        return itemAvaliacaoEconomicaRepository.save(itemAvaliacaoEconomica);
    }

    /**
     * Get all the itemAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemAvaliacaoEconomica> findAll(Pageable pageable) {
        log.debug("Request to get all ItemAvaliacaoEconomicas");
        return itemAvaliacaoEconomicaRepository.findAll(pageable);
    }


    /**
     * Get one itemAvaliacaoEconomica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemAvaliacaoEconomica> findOne(Long id) {
        log.debug("Request to get ItemAvaliacaoEconomica : {}", id);
        return itemAvaliacaoEconomicaRepository.findById(id);
    }

    /**
     * Delete the itemAvaliacaoEconomica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemAvaliacaoEconomica : {}", id);
        itemAvaliacaoEconomicaRepository.deleteById(id);
    }
}
