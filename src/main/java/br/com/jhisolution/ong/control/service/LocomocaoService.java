package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Locomocao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Locomocao.
 */
public interface LocomocaoService {

    /**
     * Save a locomocao.
     *
     * @param locomocao the entity to save
     * @return the persisted entity
     */
    Locomocao save(Locomocao locomocao);

    /**
     * Get all the locomocaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Locomocao> findAll(Pageable pageable);


    /**
     * Get the "id" locomocao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Locomocao> findOne(Long id);

    /**
     * Delete the "id" locomocao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
