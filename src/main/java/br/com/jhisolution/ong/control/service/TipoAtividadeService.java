package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoAtividade.
 */
public interface TipoAtividadeService {

    /**
     * Save a tipoAtividade.
     *
     * @param tipoAtividade the entity to save
     * @return the persisted entity
     */
    TipoAtividade save(TipoAtividade tipoAtividade);

    /**
     * Get all the tipoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoAtividade> findAll(Pageable pageable);
    /**
     * Get all the TipoAtividadeDTO where Atividade is null.
     *
     * @return the list of entities
     */
    List<TipoAtividade> findAllWhereAtividadeIsNull();


    /**
     * Get the "id" tipoAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoAtividade> findOne(Long id);

    /**
     * Delete the "id" tipoAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
