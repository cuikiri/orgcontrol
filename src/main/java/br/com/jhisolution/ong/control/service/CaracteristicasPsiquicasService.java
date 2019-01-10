package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.CaracteristicasPsiquicas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaracteristicasPsiquicas.
 */
public interface CaracteristicasPsiquicasService {

    /**
     * Save a caracteristicasPsiquicas.
     *
     * @param caracteristicasPsiquicas the entity to save
     * @return the persisted entity
     */
    CaracteristicasPsiquicas save(CaracteristicasPsiquicas caracteristicasPsiquicas);

    /**
     * Get all the caracteristicasPsiquicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaracteristicasPsiquicas> findAll(Pageable pageable);


    /**
     * Get the "id" caracteristicasPsiquicas.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaracteristicasPsiquicas> findOne(Long id);

    /**
     * Delete the "id" caracteristicasPsiquicas.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
