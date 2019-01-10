package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.PeriodoAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PeriodoAtividade.
 */
public interface PeriodoAtividadeService {

    /**
     * Save a periodoAtividade.
     *
     * @param periodoAtividade the entity to save
     * @return the persisted entity
     */
    PeriodoAtividade save(PeriodoAtividade periodoAtividade);

    /**
     * Get all the periodoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PeriodoAtividade> findAll(Pageable pageable);


    /**
     * Get the "id" periodoAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PeriodoAtividade> findOne(Long id);

    /**
     * Delete the "id" periodoAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
