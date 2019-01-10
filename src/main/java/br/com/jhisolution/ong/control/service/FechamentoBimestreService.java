package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.FechamentoBimestre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FechamentoBimestre.
 */
public interface FechamentoBimestreService {

    /**
     * Save a fechamentoBimestre.
     *
     * @param fechamentoBimestre the entity to save
     * @return the persisted entity
     */
    FechamentoBimestre save(FechamentoBimestre fechamentoBimestre);

    /**
     * Get all the fechamentoBimestres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FechamentoBimestre> findAll(Pageable pageable);
    /**
     * Get all the FechamentoBimestreDTO where Bimestre is null.
     *
     * @return the list of entities
     */
    List<FechamentoBimestre> findAllWhereBimestreIsNull();

    /**
     * Get all the FechamentoBimestre with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<FechamentoBimestre> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" fechamentoBimestre.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FechamentoBimestre> findOne(Long id);

    /**
     * Delete the "id" fechamentoBimestre.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
