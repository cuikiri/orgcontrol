package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.AcompanhamentoAluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AcompanhamentoAluno.
 */
public interface AcompanhamentoAlunoService {

    /**
     * Save a acompanhamentoAluno.
     *
     * @param acompanhamentoAluno the entity to save
     * @return the persisted entity
     */
    AcompanhamentoAluno save(AcompanhamentoAluno acompanhamentoAluno);

    /**
     * Get all the acompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AcompanhamentoAluno> findAll(Pageable pageable);


    /**
     * Get the "id" acompanhamentoAluno.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcompanhamentoAluno> findOne(Long id);

    /**
     * Delete the "id" acompanhamentoAluno.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
