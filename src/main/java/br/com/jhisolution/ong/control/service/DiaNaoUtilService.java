package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.DiaNaoUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DiaNaoUtil.
 */
public interface DiaNaoUtilService {

    /**
     * Save a diaNaoUtil.
     *
     * @param diaNaoUtil the entity to save
     * @return the persisted entity
     */
    DiaNaoUtil save(DiaNaoUtil diaNaoUtil);

    /**
     * Get all the diaNaoUtils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DiaNaoUtil> findAll(Pageable pageable);


    /**
     * Get the "id" diaNaoUtil.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DiaNaoUtil> findOne(Long id);

    /**
     * Delete the "id" diaNaoUtil.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
