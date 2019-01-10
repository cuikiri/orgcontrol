package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.EstadoCivil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EstadoCivil.
 */
public interface EstadoCivilService {

    /**
     * Save a estadoCivil.
     *
     * @param estadoCivil the entity to save
     * @return the persisted entity
     */
    EstadoCivil save(EstadoCivil estadoCivil);

    /**
     * Get all the estadoCivils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EstadoCivil> findAll(Pageable pageable);
    /**
     * Get all the EstadoCivilDTO where Colaborador is null.
     *
     * @return the list of entities
     */
    List<EstadoCivil> findAllWhereColaboradorIsNull();


    /**
     * Get the "id" estadoCivil.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EstadoCivil> findOne(Long id);

    /**
     * Delete the "id" estadoCivil.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
