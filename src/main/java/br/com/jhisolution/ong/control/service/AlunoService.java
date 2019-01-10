package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Aluno.
 */
public interface AlunoService {

    /**
     * Save a aluno.
     *
     * @param aluno the entity to save
     * @return the persisted entity
     */
    Aluno save(Aluno aluno);

    /**
     * Get all the alunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Aluno> findAll(Pageable pageable);


    /**
     * Get the "id" aluno.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Aluno> findOne(Long id);

    /**
     * Delete the "id" aluno.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
