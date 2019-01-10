package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Biotipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Biotipo.
 */
public interface BiotipoService {

    /**
     * Save a biotipo.
     *
     * @param biotipo the entity to save
     * @return the persisted entity
     */
    Biotipo save(Biotipo biotipo);

    /**
     * Get all the biotipos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Biotipo> findAll(Pageable pageable);


    /**
     * Get the "id" biotipo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Biotipo> findOne(Long id);

    /**
     * Delete the "id" biotipo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
