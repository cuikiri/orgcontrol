package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.AcompanhamentoEscolarAluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AcompanhamentoEscolarAluno.
 */
public interface AcompanhamentoEscolarAlunoService {

    /**
     * Save a acompanhamentoEscolarAluno.
     *
     * @param acompanhamentoEscolarAluno the entity to save
     * @return the persisted entity
     */
    AcompanhamentoEscolarAluno save(AcompanhamentoEscolarAluno acompanhamentoEscolarAluno);

    /**
     * Get all the acompanhamentoEscolarAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AcompanhamentoEscolarAluno> findAll(Pageable pageable);


    /**
     * Get the "id" acompanhamentoEscolarAluno.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcompanhamentoEscolarAluno> findOne(Long id);

    /**
     * Delete the "id" acompanhamentoEscolarAluno.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
