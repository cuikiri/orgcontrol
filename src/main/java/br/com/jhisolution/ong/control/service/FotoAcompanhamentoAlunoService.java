package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.FotoAcompanhamentoAluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FotoAcompanhamentoAluno.
 */
public interface FotoAcompanhamentoAlunoService {

    /**
     * Save a fotoAcompanhamentoAluno.
     *
     * @param fotoAcompanhamentoAluno the entity to save
     * @return the persisted entity
     */
    FotoAcompanhamentoAluno save(FotoAcompanhamentoAluno fotoAcompanhamentoAluno);

    /**
     * Get all the fotoAcompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FotoAcompanhamentoAluno> findAll(Pageable pageable);
    /**
     * Get all the FotoAcompanhamentoAlunoDTO where AcompanhamentoAluno is null.
     *
     * @return the list of entities
     */
    List<FotoAcompanhamentoAluno> findAllWhereAcompanhamentoAlunoIsNull();


    /**
     * Get the "id" fotoAcompanhamentoAluno.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FotoAcompanhamentoAluno> findOne(Long id);

    /**
     * Delete the "id" fotoAcompanhamentoAluno.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
