package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.PeriodoSemana;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PeriodoSemana.
 */
public interface PeriodoSemanaService {

    /**
     * Save a periodoSemana.
     *
     * @param periodoSemana the entity to save
     * @return the persisted entity
     */
    PeriodoSemana save(PeriodoSemana periodoSemana);

    /**
     * Get all the periodoSemanas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PeriodoSemana> findAll(Pageable pageable);


    /**
     * Get the "id" periodoSemana.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PeriodoSemana> findOne(Long id);

    /**
     * Delete the "id" periodoSemana.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
