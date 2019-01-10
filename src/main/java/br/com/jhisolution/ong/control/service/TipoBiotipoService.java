package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoBiotipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoBiotipo.
 */
public interface TipoBiotipoService {

    /**
     * Save a tipoBiotipo.
     *
     * @param tipoBiotipo the entity to save
     * @return the persisted entity
     */
    TipoBiotipo save(TipoBiotipo tipoBiotipo);

    /**
     * Get all the tipoBiotipos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoBiotipo> findAll(Pageable pageable);
    /**
     * Get all the TipoBiotipoDTO where Biotipo is null.
     *
     * @return the list of entities
     */
    List<TipoBiotipo> findAllWhereBiotipoIsNull();


    /**
     * Get the "id" tipoBiotipo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoBiotipo> findOne(Long id);

    /**
     * Delete the "id" tipoBiotipo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
