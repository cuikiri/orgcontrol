package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.AgendaColaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AgendaColaborador.
 */
public interface AgendaColaboradorService {

    /**
     * Save a agendaColaborador.
     *
     * @param agendaColaborador the entity to save
     * @return the persisted entity
     */
    AgendaColaborador save(AgendaColaborador agendaColaborador);

    /**
     * Get all the agendaColaboradors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AgendaColaborador> findAll(Pageable pageable);


    /**
     * Get the "id" agendaColaborador.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AgendaColaborador> findOne(Long id);

    /**
     * Delete the "id" agendaColaborador.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
