package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ObservacaoProfessor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ObservacaoProfessor.
 */
public interface ObservacaoProfessorService {

    /**
     * Save a observacaoProfessor.
     *
     * @param observacaoProfessor the entity to save
     * @return the persisted entity
     */
    ObservacaoProfessor save(ObservacaoProfessor observacaoProfessor);

    /**
     * Get all the observacaoProfessors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ObservacaoProfessor> findAll(Pageable pageable);
    /**
     * Get all the ObservacaoProfessorDTO where Diario is null.
     *
     * @return the list of entities
     */
    List<ObservacaoProfessor> findAllWhereDiarioIsNull();


    /**
     * Get the "id" observacaoProfessor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ObservacaoProfessor> findOne(Long id);

    /**
     * Delete the "id" observacaoProfessor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
