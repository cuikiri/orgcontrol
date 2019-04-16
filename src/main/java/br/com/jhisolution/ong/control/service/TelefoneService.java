package br.com.jhisolution.ong.control.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.domain.Telefone;

/**
 * Service Interface for managing Telefone.
 */
public interface TelefoneService {

    /**
     * Save a telefone.
     *
     * @param telefone the entity to save
     * @return the persisted entity
     */
    Telefone save(Telefone telefone);

    /**
     * Get all the telefones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Telefone> findAll(Pageable pageable);

    /**
     * Get all the telefones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Telefone> findAllByPessoa(Pageable pageable, Pessoa pessoa);

    /**
     * Get the "id" telefone.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Telefone> findOne(Long id);

    /**
     * Delete the "id" telefone.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
