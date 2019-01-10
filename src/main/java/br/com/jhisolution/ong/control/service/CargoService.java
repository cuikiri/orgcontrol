package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Cargo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Cargo.
 */
public interface CargoService {

    /**
     * Save a cargo.
     *
     * @param cargo the entity to save
     * @return the persisted entity
     */
    Cargo save(Cargo cargo);

    /**
     * Get all the cargos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Cargo> findAll(Pageable pageable);
    /**
     * Get all the CargoDTO where Candidato is null.
     *
     * @return the list of entities
     */
    List<Cargo> findAllWhereCandidatoIsNull();


    /**
     * Get the "id" cargo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Cargo> findOne(Long id);

    /**
     * Delete the "id" cargo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
