package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativaEconomica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OpcaoRespAvalOptativaEconomica.
 */
public interface OpcaoRespAvalOptativaEconomicaService {

    /**
     * Save a opcaoRespAvalOptativaEconomica.
     *
     * @param opcaoRespAvalOptativaEconomica the entity to save
     * @return the persisted entity
     */
    OpcaoRespAvalOptativaEconomica save(OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica);

    /**
     * Get all the opcaoRespAvalOptativaEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OpcaoRespAvalOptativaEconomica> findAll(Pageable pageable);


    /**
     * Get the "id" opcaoRespAvalOptativaEconomica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OpcaoRespAvalOptativaEconomica> findOne(Long id);

    /**
     * Delete the "id" opcaoRespAvalOptativaEconomica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
