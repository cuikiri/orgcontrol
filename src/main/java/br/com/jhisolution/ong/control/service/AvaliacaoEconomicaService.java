package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.AvaliacaoEconomica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AvaliacaoEconomica.
 */
public interface AvaliacaoEconomicaService {

    /**
     * Save a avaliacaoEconomica.
     *
     * @param avaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    AvaliacaoEconomica save(AvaliacaoEconomica avaliacaoEconomica);

    /**
     * Get all the avaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AvaliacaoEconomica> findAll(Pageable pageable);


    /**
     * Get the "id" avaliacaoEconomica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AvaliacaoEconomica> findOne(Long id);

    /**
     * Delete the "id" avaliacaoEconomica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
