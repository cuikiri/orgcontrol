package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ProblemaFisico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ProblemaFisico.
 */
public interface ProblemaFisicoService {

    /**
     * Save a problemaFisico.
     *
     * @param problemaFisico the entity to save
     * @return the persisted entity
     */
    ProblemaFisico save(ProblemaFisico problemaFisico);

    /**
     * Get all the problemaFisicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProblemaFisico> findAll(Pageable pageable);


    /**
     * Get the "id" problemaFisico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProblemaFisico> findOne(Long id);

    /**
     * Delete the "id" problemaFisico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
