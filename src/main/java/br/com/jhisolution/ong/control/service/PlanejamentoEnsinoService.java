package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.PlanejamentoEnsino;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanejamentoEnsino.
 */
public interface PlanejamentoEnsinoService {

    /**
     * Save a planejamentoEnsino.
     *
     * @param planejamentoEnsino the entity to save
     * @return the persisted entity
     */
    PlanejamentoEnsino save(PlanejamentoEnsino planejamentoEnsino);

    /**
     * Get all the planejamentoEnsinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanejamentoEnsino> findAll(Pageable pageable);


    /**
     * Get the "id" planejamentoEnsino.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanejamentoEnsino> findOne(Long id);

    /**
     * Delete the "id" planejamentoEnsino.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
