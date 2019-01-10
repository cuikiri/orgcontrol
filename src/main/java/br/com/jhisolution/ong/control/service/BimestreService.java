package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Bimestre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Bimestre.
 */
public interface BimestreService {

    /**
     * Save a bimestre.
     *
     * @param bimestre the entity to save
     * @return the persisted entity
     */
    Bimestre save(Bimestre bimestre);

    /**
     * Get all the bimestres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Bimestre> findAll(Pageable pageable);


    /**
     * Get the "id" bimestre.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Bimestre> findOne(Long id);

    /**
     * Delete the "id" bimestre.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
