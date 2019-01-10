package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ConteudoProgramatico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ConteudoProgramatico.
 */
public interface ConteudoProgramaticoService {

    /**
     * Save a conteudoProgramatico.
     *
     * @param conteudoProgramatico the entity to save
     * @return the persisted entity
     */
    ConteudoProgramatico save(ConteudoProgramatico conteudoProgramatico);

    /**
     * Get all the conteudoProgramaticos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConteudoProgramatico> findAll(Pageable pageable);


    /**
     * Get the "id" conteudoProgramatico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConteudoProgramatico> findOne(Long id);

    /**
     * Delete the "id" conteudoProgramatico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
