package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ParteBloco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ParteBloco.
 */
public interface ParteBlocoService {

    /**
     * Save a parteBloco.
     *
     * @param parteBloco the entity to save
     * @return the persisted entity
     */
    ParteBloco save(ParteBloco parteBloco);

    /**
     * Get all the parteBlocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ParteBloco> findAll(Pageable pageable);
    /**
     * Get all the ParteBlocoDTO where PeriodoAtividade is null.
     *
     * @return the list of entities
     */
    List<ParteBloco> findAllWherePeriodoAtividadeIsNull();


    /**
     * Get the "id" parteBloco.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ParteBloco> findOne(Long id);

    /**
     * Delete the "id" parteBloco.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
