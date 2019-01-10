package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ExameMedico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ExameMedico.
 */
public interface ExameMedicoService {

    /**
     * Save a exameMedico.
     *
     * @param exameMedico the entity to save
     * @return the persisted entity
     */
    ExameMedico save(ExameMedico exameMedico);

    /**
     * Get all the exameMedicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ExameMedico> findAll(Pageable pageable);

    /**
     * Get all the ExameMedico with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<ExameMedico> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" exameMedico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ExameMedico> findOne(Long id);

    /**
     * Delete the "id" exameMedico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
