package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.MateriaAcompanhamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MateriaAcompanhamento.
 */
public interface MateriaAcompanhamentoService {

    /**
     * Save a materiaAcompanhamento.
     *
     * @param materiaAcompanhamento the entity to save
     * @return the persisted entity
     */
    MateriaAcompanhamento save(MateriaAcompanhamento materiaAcompanhamento);

    /**
     * Get all the materiaAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MateriaAcompanhamento> findAll(Pageable pageable);


    /**
     * Get the "id" materiaAcompanhamento.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MateriaAcompanhamento> findOne(Long id);

    /**
     * Delete the "id" materiaAcompanhamento.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
