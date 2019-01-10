package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Ensino;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Ensino.
 */
public interface EnsinoService {

    /**
     * Save a ensino.
     *
     * @param ensino the entity to save
     * @return the persisted entity
     */
    Ensino save(Ensino ensino);

    /**
     * Get all the ensinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Ensino> findAll(Pageable pageable);


    /**
     * Get the "id" ensino.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Ensino> findOne(Long id);

    /**
     * Delete the "id" ensino.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
