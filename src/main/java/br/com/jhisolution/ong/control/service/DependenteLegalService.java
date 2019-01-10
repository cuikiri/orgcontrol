package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.DependenteLegal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DependenteLegal.
 */
public interface DependenteLegalService {

    /**
     * Save a dependenteLegal.
     *
     * @param dependenteLegal the entity to save
     * @return the persisted entity
     */
    DependenteLegal save(DependenteLegal dependenteLegal);

    /**
     * Get all the dependenteLegals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DependenteLegal> findAll(Pageable pageable);


    /**
     * Get the "id" dependenteLegal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DependenteLegal> findOne(Long id);

    /**
     * Delete the "id" dependenteLegal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
