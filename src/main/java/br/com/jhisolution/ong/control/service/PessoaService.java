package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Pessoa.
 */
public interface PessoaService {

    /**
     * Save a pessoa.
     *
     * @param pessoa the entity to save
     * @return the persisted entity
     */
    Pessoa save(Pessoa pessoa);

    /**
     * Get all the pessoas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Pessoa> findAll(Pageable pageable);
    /**
     * Get all the PessoaDTO where Colaborador is null.
     *
     * @return the list of entities
     */
    List<Pessoa> findAllWhereColaboradorIsNull();
    /**
     * Get all the PessoaDTO where Responsavel is null.
     *
     * @return the list of entities
     */
    List<Pessoa> findAllWhereResponsavelIsNull();
    /**
     * Get all the PessoaDTO where Aluno is null.
     *
     * @return the list of entities
     */
    List<Pessoa> findAllWhereAlunoIsNull();
    /**
     * Get all the PessoaDTO where AlunoMae is null.
     *
     * @return the list of entities
     */
    List<Pessoa> findAllWhereAlunoMaeIsNull();
    /**
     * Get all the PessoaDTO where AlunoPai is null.
     *
     * @return the list of entities
     */
    List<Pessoa> findAllWhereAlunoPaiIsNull();

    /**
     * Get all the Pessoa with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Pessoa> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" pessoa.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Pessoa> findOne(Long id);

    /**
     * Delete the "id" pessoa.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
