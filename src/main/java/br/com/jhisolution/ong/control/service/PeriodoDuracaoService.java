package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.PeriodoDuracao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PeriodoDuracao.
 */
public interface PeriodoDuracaoService {

    /**
     * Save a periodoDuracao.
     *
     * @param periodoDuracao the entity to save
     * @return the persisted entity
     */
    PeriodoDuracao save(PeriodoDuracao periodoDuracao);

    /**
     * Get all the periodoDuracaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PeriodoDuracao> findAll(Pageable pageable);


    /**
     * Get the "id" periodoDuracao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PeriodoDuracao> findOne(Long id);

    /**
     * Delete the "id" periodoDuracao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
