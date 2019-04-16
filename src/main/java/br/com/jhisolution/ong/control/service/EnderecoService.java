package br.com.jhisolution.ong.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jhisolution.ong.control.domain.Endereco;
import br.com.jhisolution.ong.control.domain.Pessoa;

/**
 * Service Interface for managing Endereco.
 */
public interface EnderecoService {

    /**
     * Save a endereco.
     *
     * @param endereco the entity to save
     * @return the persisted entity
     */
    Endereco save(Endereco endereco);

    /**
     * Get all the enderecos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Endereco> findAll(Pageable pageable);
    
    /**
     * Get all the enderecos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Endereco> findAllByPessoa(Pageable pageable, Pessoa pessoa);
    
    
    /**
     * Get all the EnderecoDTO where Unidade is null.
     *
     * @return the list of entities
     */
    List<Endereco> findAllWhereUnidadeIsNull();


    /**
     * Get the "id" endereco.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Endereco> findOne(Long id);

    /**
     * Delete the "id" endereco.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
