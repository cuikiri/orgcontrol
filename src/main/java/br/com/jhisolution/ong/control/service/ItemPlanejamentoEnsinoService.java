package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoEnsino;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ItemPlanejamentoEnsino.
 */
public interface ItemPlanejamentoEnsinoService {

    /**
     * Save a itemPlanejamentoEnsino.
     *
     * @param itemPlanejamentoEnsino the entity to save
     * @return the persisted entity
     */
    ItemPlanejamentoEnsino save(ItemPlanejamentoEnsino itemPlanejamentoEnsino);

    /**
     * Get all the itemPlanejamentoEnsinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ItemPlanejamentoEnsino> findAll(Pageable pageable);


    /**
     * Get the "id" itemPlanejamentoEnsino.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ItemPlanejamentoEnsino> findOne(Long id);

    /**
     * Delete the "id" itemPlanejamentoEnsino.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
