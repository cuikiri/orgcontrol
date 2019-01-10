package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ItemAvaliacaoEconomica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ItemAvaliacaoEconomica.
 */
public interface ItemAvaliacaoEconomicaService {

    /**
     * Save a itemAvaliacaoEconomica.
     *
     * @param itemAvaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    ItemAvaliacaoEconomica save(ItemAvaliacaoEconomica itemAvaliacaoEconomica);

    /**
     * Get all the itemAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ItemAvaliacaoEconomica> findAll(Pageable pageable);


    /**
     * Get the "id" itemAvaliacaoEconomica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ItemAvaliacaoEconomica> findOne(Long id);

    /**
     * Delete the "id" itemAvaliacaoEconomica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
