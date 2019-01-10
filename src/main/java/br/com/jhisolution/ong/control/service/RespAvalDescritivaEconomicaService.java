package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespAvalDescritivaEconomica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RespAvalDescritivaEconomica.
 */
public interface RespAvalDescritivaEconomicaService {

    /**
     * Save a respAvalDescritivaEconomica.
     *
     * @param respAvalDescritivaEconomica the entity to save
     * @return the persisted entity
     */
    RespAvalDescritivaEconomica save(RespAvalDescritivaEconomica respAvalDescritivaEconomica);

    /**
     * Get all the respAvalDescritivaEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespAvalDescritivaEconomica> findAll(Pageable pageable);
    /**
     * Get all the RespAvalDescritivaEconomicaDTO where ItemAvaliacaoEconomica is null.
     *
     * @return the list of entities
     */
    List<RespAvalDescritivaEconomica> findAllWhereItemAvaliacaoEconomicaIsNull();
    /**
     * Get all the RespAvalDescritivaEconomicaDTO where RespostaAvaliacaoEconomica is null.
     *
     * @return the list of entities
     */
    List<RespAvalDescritivaEconomica> findAllWhereRespostaAvaliacaoEconomicaIsNull();


    /**
     * Get the "id" respAvalDescritivaEconomica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespAvalDescritivaEconomica> findOne(Long id);

    /**
     * Delete the "id" respAvalDescritivaEconomica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
