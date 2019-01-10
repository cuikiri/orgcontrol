package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.MotivoDiaNaoUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MotivoDiaNaoUtil.
 */
public interface MotivoDiaNaoUtilService {

    /**
     * Save a motivoDiaNaoUtil.
     *
     * @param motivoDiaNaoUtil the entity to save
     * @return the persisted entity
     */
    MotivoDiaNaoUtil save(MotivoDiaNaoUtil motivoDiaNaoUtil);

    /**
     * Get all the motivoDiaNaoUtils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MotivoDiaNaoUtil> findAll(Pageable pageable);


    /**
     * Get the "id" motivoDiaNaoUtil.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MotivoDiaNaoUtil> findOne(Long id);

    /**
     * Delete the "id" motivoDiaNaoUtil.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
