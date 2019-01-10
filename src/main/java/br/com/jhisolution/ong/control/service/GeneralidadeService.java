package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Generalidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Generalidade.
 */
public interface GeneralidadeService {

    /**
     * Save a generalidade.
     *
     * @param generalidade the entity to save
     * @return the persisted entity
     */
    Generalidade save(Generalidade generalidade);

    /**
     * Get all the generalidades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Generalidade> findAll(Pageable pageable);


    /**
     * Get the "id" generalidade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Generalidade> findOne(Long id);

    /**
     * Delete the "id" generalidade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
