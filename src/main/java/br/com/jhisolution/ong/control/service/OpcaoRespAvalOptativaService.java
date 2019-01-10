package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OpcaoRespAvalOptativa.
 */
public interface OpcaoRespAvalOptativaService {

    /**
     * Save a opcaoRespAvalOptativa.
     *
     * @param opcaoRespAvalOptativa the entity to save
     * @return the persisted entity
     */
    OpcaoRespAvalOptativa save(OpcaoRespAvalOptativa opcaoRespAvalOptativa);

    /**
     * Get all the opcaoRespAvalOptativas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OpcaoRespAvalOptativa> findAll(Pageable pageable);


    /**
     * Get the "id" opcaoRespAvalOptativa.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OpcaoRespAvalOptativa> findOne(Long id);

    /**
     * Delete the "id" opcaoRespAvalOptativa.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
