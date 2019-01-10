package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Advertencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Advertencia.
 */
public interface AdvertenciaService {

    /**
     * Save a advertencia.
     *
     * @param advertencia the entity to save
     * @return the persisted entity
     */
    Advertencia save(Advertencia advertencia);

    /**
     * Get all the advertencias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Advertencia> findAll(Pageable pageable);


    /**
     * Get the "id" advertencia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Advertencia> findOne(Long id);

    /**
     * Delete the "id" advertencia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
