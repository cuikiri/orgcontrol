package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespostaAvaliacaoEconomica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RespostaAvaliacaoEconomica.
 */
public interface RespostaAvaliacaoEconomicaService {

    /**
     * Save a respostaAvaliacaoEconomica.
     *
     * @param respostaAvaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    RespostaAvaliacaoEconomica save(RespostaAvaliacaoEconomica respostaAvaliacaoEconomica);

    /**
     * Get all the respostaAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespostaAvaliacaoEconomica> findAll(Pageable pageable);


    /**
     * Get the "id" respostaAvaliacaoEconomica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespostaAvaliacaoEconomica> findOne(Long id);

    /**
     * Delete the "id" respostaAvaliacaoEconomica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
