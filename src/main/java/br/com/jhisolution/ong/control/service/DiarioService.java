package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Diario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Diario.
 */
public interface DiarioService {

    /**
     * Save a diario.
     *
     * @param diario the entity to save
     * @return the persisted entity
     */
    Diario save(Diario diario);

    /**
     * Get all the diarios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Diario> findAll(Pageable pageable);


    /**
     * Get the "id" diario.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Diario> findOne(Long id);

    /**
     * Delete the "id" diario.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
