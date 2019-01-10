package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoAcompanhamentoAluno.
 */
public interface TipoAcompanhamentoAlunoService {

    /**
     * Save a tipoAcompanhamentoAluno.
     *
     * @param tipoAcompanhamentoAluno the entity to save
     * @return the persisted entity
     */
    TipoAcompanhamentoAluno save(TipoAcompanhamentoAluno tipoAcompanhamentoAluno);

    /**
     * Get all the tipoAcompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoAcompanhamentoAluno> findAll(Pageable pageable);
    /**
     * Get all the TipoAcompanhamentoAlunoDTO where AcompanhamentoAluno is null.
     *
     * @return the list of entities
     */
    List<TipoAcompanhamentoAluno> findAllWhereAcompanhamentoAlunoIsNull();


    /**
     * Get the "id" tipoAcompanhamentoAluno.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoAcompanhamentoAluno> findOne(Long id);

    /**
     * Delete the "id" tipoAcompanhamentoAluno.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
