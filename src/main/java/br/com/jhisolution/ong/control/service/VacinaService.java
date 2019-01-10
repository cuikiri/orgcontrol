package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Vacina;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Vacina.
 */
public interface VacinaService {

    /**
     * Save a vacina.
     *
     * @param vacina the entity to save
     * @return the persisted entity
     */
    Vacina save(Vacina vacina);

    /**
     * Get all the vacinas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Vacina> findAll(Pageable pageable);


    /**
     * Get the "id" vacina.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Vacina> findOne(Long id);

    /**
     * Delete the "id" vacina.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
