package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespAvalDescritiva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RespAvalDescritiva.
 */
public interface RespAvalDescritivaService {

    /**
     * Save a respAvalDescritiva.
     *
     * @param respAvalDescritiva the entity to save
     * @return the persisted entity
     */
    RespAvalDescritiva save(RespAvalDescritiva respAvalDescritiva);

    /**
     * Get all the respAvalDescritivas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespAvalDescritiva> findAll(Pageable pageable);
    /**
     * Get all the RespAvalDescritivaDTO where ItemAvaliacao is null.
     *
     * @return the list of entities
     */
    List<RespAvalDescritiva> findAllWhereItemAvaliacaoIsNull();
    /**
     * Get all the RespAvalDescritivaDTO where RespostaAvaliacao is null.
     *
     * @return the list of entities
     */
    List<RespAvalDescritiva> findAllWhereRespostaAvaliacaoIsNull();


    /**
     * Get the "id" respAvalDescritiva.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespAvalDescritiva> findOne(Long id);

    /**
     * Delete the "id" respAvalDescritiva.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
