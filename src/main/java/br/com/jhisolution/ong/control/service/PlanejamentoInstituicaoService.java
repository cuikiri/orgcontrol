package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.PlanejamentoInstituicao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanejamentoInstituicao.
 */
public interface PlanejamentoInstituicaoService {

    /**
     * Save a planejamentoInstituicao.
     *
     * @param planejamentoInstituicao the entity to save
     * @return the persisted entity
     */
    PlanejamentoInstituicao save(PlanejamentoInstituicao planejamentoInstituicao);

    /**
     * Get all the planejamentoInstituicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanejamentoInstituicao> findAll(Pageable pageable);


    /**
     * Get the "id" planejamentoInstituicao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanejamentoInstituicao> findOne(Long id);

    /**
     * Delete the "id" planejamentoInstituicao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
