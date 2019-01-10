package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.HorarioMateria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing HorarioMateria.
 */
public interface HorarioMateriaService {

    /**
     * Save a horarioMateria.
     *
     * @param horarioMateria the entity to save
     * @return the persisted entity
     */
    HorarioMateria save(HorarioMateria horarioMateria);

    /**
     * Get all the horarioMaterias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HorarioMateria> findAll(Pageable pageable);


    /**
     * Get the "id" horarioMateria.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HorarioMateria> findOne(Long id);

    /**
     * Delete the "id" horarioMateria.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
