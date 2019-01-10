package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Sexo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Sexo.
 */
public interface SexoService {

    /**
     * Save a sexo.
     *
     * @param sexo the entity to save
     * @return the persisted entity
     */
    Sexo save(Sexo sexo);

    /**
     * Get all the sexos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Sexo> findAll(Pageable pageable);


    /**
     * Get the "id" sexo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Sexo> findOne(Long id);

    /**
     * Delete the "id" sexo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
