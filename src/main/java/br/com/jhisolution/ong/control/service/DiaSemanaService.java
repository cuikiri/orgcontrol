package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.DiaSemana;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DiaSemana.
 */
public interface DiaSemanaService {

    /**
     * Save a diaSemana.
     *
     * @param diaSemana the entity to save
     * @return the persisted entity
     */
    DiaSemana save(DiaSemana diaSemana);

    /**
     * Get all the diaSemanas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DiaSemana> findAll(Pageable pageable);
    /**
     * Get all the DiaSemanaDTO where HorarioMateria is null.
     *
     * @return the list of entities
     */
    List<DiaSemana> findAllWhereHorarioMateriaIsNull();


    /**
     * Get the "id" diaSemana.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DiaSemana> findOne(Long id);

    /**
     * Delete the "id" diaSemana.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
