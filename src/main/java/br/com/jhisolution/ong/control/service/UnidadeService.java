package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Unidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Unidade.
 */
public interface UnidadeService {

    /**
     * Save a unidade.
     *
     * @param unidade the entity to save
     * @return the persisted entity
     */
    Unidade save(Unidade unidade);

    /**
     * Get all the unidades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Unidade> findAll(Pageable pageable);


    /**
     * Get the "id" unidade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Unidade> findOne(Long id);

    /**
     * Delete the "id" unidade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
