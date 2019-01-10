package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Chapa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Chapa.
 */
public interface ChapaService {

    /**
     * Save a chapa.
     *
     * @param chapa the entity to save
     * @return the persisted entity
     */
    Chapa save(Chapa chapa);

    /**
     * Get all the chapas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Chapa> findAll(Pageable pageable);
    /**
     * Get all the ChapaDTO where EleicaoGanhadora is null.
     *
     * @return the list of entities
     */
    List<Chapa> findAllWhereEleicaoGanhadoraIsNull();


    /**
     * Get the "id" chapa.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Chapa> findOne(Long id);

    /**
     * Delete the "id" chapa.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
