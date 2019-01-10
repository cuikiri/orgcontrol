package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Colaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Colaborador.
 */
public interface ColaboradorService {

    /**
     * Save a colaborador.
     *
     * @param colaborador the entity to save
     * @return the persisted entity
     */
    Colaborador save(Colaborador colaborador);

    /**
     * Get all the colaboradors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Colaborador> findAll(Pageable pageable);
    /**
     * Get all the ColaboradorDTO where Candidato is null.
     *
     * @return the list of entities
     */
    List<Colaborador> findAllWhereCandidatoIsNull();
    /**
     * Get all the ColaboradorDTO where Diario is null.
     *
     * @return the list of entities
     */
    List<Colaborador> findAllWhereDiarioIsNull();


    /**
     * Get the "id" colaborador.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Colaborador> findOne(Long id);

    /**
     * Delete the "id" colaborador.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
