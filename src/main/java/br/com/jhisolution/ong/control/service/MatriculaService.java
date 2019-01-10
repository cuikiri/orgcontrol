package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Matricula;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Matricula.
 */
public interface MatriculaService {

    /**
     * Save a matricula.
     *
     * @param matricula the entity to save
     * @return the persisted entity
     */
    Matricula save(Matricula matricula);

    /**
     * Get all the matriculas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Matricula> findAll(Pageable pageable);


    /**
     * Get the "id" matricula.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Matricula> findOne(Long id);

    /**
     * Delete the "id" matricula.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
