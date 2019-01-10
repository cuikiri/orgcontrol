package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoAvaliacaoEconomica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoAvaliacaoEconomica.
 */
public interface TipoAvaliacaoEconomicaService {

    /**
     * Save a tipoAvaliacaoEconomica.
     *
     * @param tipoAvaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    TipoAvaliacaoEconomica save(TipoAvaliacaoEconomica tipoAvaliacaoEconomica);

    /**
     * Get all the tipoAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoAvaliacaoEconomica> findAll(Pageable pageable);
    /**
     * Get all the TipoAvaliacaoEconomicaDTO where AvaliacaoEconomica is null.
     *
     * @return the list of entities
     */
    List<TipoAvaliacaoEconomica> findAllWhereAvaliacaoEconomicaIsNull();


    /**
     * Get the "id" tipoAvaliacaoEconomica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoAvaliacaoEconomica> findOne(Long id);

    /**
     * Delete the "id" tipoAvaliacaoEconomica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
