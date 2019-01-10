package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespAvalOptativa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RespAvalOptativa.
 */
public interface RespAvalOptativaService {

    /**
     * Save a respAvalOptativa.
     *
     * @param respAvalOptativa the entity to save
     * @return the persisted entity
     */
    RespAvalOptativa save(RespAvalOptativa respAvalOptativa);

    /**
     * Get all the respAvalOptativas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespAvalOptativa> findAll(Pageable pageable);
    /**
     * Get all the RespAvalOptativaDTO where ItemAvaliacao is null.
     *
     * @return the list of entities
     */
    List<RespAvalOptativa> findAllWhereItemAvaliacaoIsNull();
    /**
     * Get all the RespAvalOptativaDTO where RespostaAvaliacao is null.
     *
     * @return the list of entities
     */
    List<RespAvalOptativa> findAllWhereRespostaAvaliacaoIsNull();


    /**
     * Get the "id" respAvalOptativa.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespAvalOptativa> findOne(Long id);

    /**
     * Delete the "id" respAvalOptativa.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
