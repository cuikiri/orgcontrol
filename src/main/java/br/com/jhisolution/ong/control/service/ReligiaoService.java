package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Religiao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Religiao.
 */
public interface ReligiaoService {

    /**
     * Save a religiao.
     *
     * @param religiao the entity to save
     * @return the persisted entity
     */
    Religiao save(Religiao religiao);

    /**
     * Get all the religiaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Religiao> findAll(Pageable pageable);


    /**
     * Get the "id" religiao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Religiao> findOne(Long id);

    /**
     * Delete the "id" religiao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
