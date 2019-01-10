package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoColaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoColaborador.
 */
public interface TipoColaboradorService {

    /**
     * Save a tipoColaborador.
     *
     * @param tipoColaborador the entity to save
     * @return the persisted entity
     */
    TipoColaborador save(TipoColaborador tipoColaborador);

    /**
     * Get all the tipoColaboradors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoColaborador> findAll(Pageable pageable);
    /**
     * Get all the TipoColaboradorDTO where Colaborador is null.
     *
     * @return the list of entities
     */
    List<TipoColaborador> findAllWhereColaboradorIsNull();


    /**
     * Get the "id" tipoColaborador.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoColaborador> findOne(Long id);

    /**
     * Delete the "id" tipoColaborador.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
