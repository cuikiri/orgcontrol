package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Atividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Atividade.
 */
public interface AtividadeService {

    /**
     * Save a atividade.
     *
     * @param atividade the entity to save
     * @return the persisted entity
     */
    Atividade save(Atividade atividade);

    /**
     * Get all the atividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Atividade> findAll(Pageable pageable);

    /**
     * Get all the Atividade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Atividade> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" atividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Atividade> findOne(Long id);

    /**
     * Delete the "id" atividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
