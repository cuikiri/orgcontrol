package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ObservacaoCoordenador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ObservacaoCoordenador.
 */
public interface ObservacaoCoordenadorService {

    /**
     * Save a observacaoCoordenador.
     *
     * @param observacaoCoordenador the entity to save
     * @return the persisted entity
     */
    ObservacaoCoordenador save(ObservacaoCoordenador observacaoCoordenador);

    /**
     * Get all the observacaoCoordenadors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ObservacaoCoordenador> findAll(Pageable pageable);
    /**
     * Get all the ObservacaoCoordenadorDTO where Diario is null.
     *
     * @return the list of entities
     */
    List<ObservacaoCoordenador> findAllWhereDiarioIsNull();


    /**
     * Get the "id" observacaoCoordenador.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ObservacaoCoordenador> findOne(Long id);

    /**
     * Delete the "id" observacaoCoordenador.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
