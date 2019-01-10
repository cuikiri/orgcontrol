package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Raca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Raca.
 */
public interface RacaService {

    /**
     * Save a raca.
     *
     * @param raca the entity to save
     * @return the persisted entity
     */
    Raca save(Raca raca);

    /**
     * Get all the racas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Raca> findAll(Pageable pageable);


    /**
     * Get the "id" raca.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Raca> findOne(Long id);

    /**
     * Delete the "id" raca.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
