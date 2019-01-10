package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Bloco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Bloco.
 */
public interface BlocoService {

    /**
     * Save a bloco.
     *
     * @param bloco the entity to save
     * @return the persisted entity
     */
    Bloco save(Bloco bloco);

    /**
     * Get all the blocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Bloco> findAll(Pageable pageable);


    /**
     * Get the "id" bloco.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Bloco> findOne(Long id);

    /**
     * Delete the "id" bloco.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
