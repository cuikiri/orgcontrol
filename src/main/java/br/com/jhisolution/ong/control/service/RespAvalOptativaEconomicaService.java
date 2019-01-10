package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespAvalOptativaEconomica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RespAvalOptativaEconomica.
 */
public interface RespAvalOptativaEconomicaService {

    /**
     * Save a respAvalOptativaEconomica.
     *
     * @param respAvalOptativaEconomica the entity to save
     * @return the persisted entity
     */
    RespAvalOptativaEconomica save(RespAvalOptativaEconomica respAvalOptativaEconomica);

    /**
     * Get all the respAvalOptativaEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespAvalOptativaEconomica> findAll(Pageable pageable);
    /**
     * Get all the RespAvalOptativaEconomicaDTO where ItemAvaliacaoEconomica is null.
     *
     * @return the list of entities
     */
    List<RespAvalOptativaEconomica> findAllWhereItemAvaliacaoEconomicaIsNull();
    /**
     * Get all the RespAvalOptativaEconomicaDTO where RespostaAvaliacaoEconomica is null.
     *
     * @return the list of entities
     */
    List<RespAvalOptativaEconomica> findAllWhereRespostaAvaliacaoEconomicaIsNull();


    /**
     * Get the "id" respAvalOptativaEconomica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespAvalOptativaEconomica> findOne(Long id);

    /**
     * Delete the "id" respAvalOptativaEconomica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
