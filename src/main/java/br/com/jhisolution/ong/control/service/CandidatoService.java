package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Candidato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Candidato.
 */
public interface CandidatoService {

    /**
     * Save a candidato.
     *
     * @param candidato the entity to save
     * @return the persisted entity
     */
    Candidato save(Candidato candidato);

    /**
     * Get all the candidatoes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Candidato> findAll(Pageable pageable);


    /**
     * Get the "id" candidato.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Candidato> findOne(Long id);

    /**
     * Delete the "id" candidato.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
