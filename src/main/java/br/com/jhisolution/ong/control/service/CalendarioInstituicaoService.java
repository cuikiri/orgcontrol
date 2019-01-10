package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.CalendarioInstituicao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CalendarioInstituicao.
 */
public interface CalendarioInstituicaoService {

    /**
     * Save a calendarioInstituicao.
     *
     * @param calendarioInstituicao the entity to save
     * @return the persisted entity
     */
    CalendarioInstituicao save(CalendarioInstituicao calendarioInstituicao);

    /**
     * Get all the calendarioInstituicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CalendarioInstituicao> findAll(Pageable pageable);


    /**
     * Get the "id" calendarioInstituicao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CalendarioInstituicao> findOne(Long id);

    /**
     * Delete the "id" calendarioInstituicao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
