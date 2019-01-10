package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Uf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Uf.
 */
public interface UfService {

    /**
     * Save a uf.
     *
     * @param uf the entity to save
     * @return the persisted entity
     */
    Uf save(Uf uf);

    /**
     * Get all the ufs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Uf> findAll(Pageable pageable);
    /**
     * Get all the UfDTO where Endereco is null.
     *
     * @return the list of entities
     */
    List<Uf> findAllWhereEnderecoIsNull();


    /**
     * Get the "id" uf.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Uf> findOne(Long id);

    /**
     * Delete the "id" uf.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
