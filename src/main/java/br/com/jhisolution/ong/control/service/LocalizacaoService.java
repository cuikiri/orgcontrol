package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Localizacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Localizacao.
 */
public interface LocalizacaoService {

    /**
     * Save a localizacao.
     *
     * @param localizacao the entity to save
     * @return the persisted entity
     */
    Localizacao save(Localizacao localizacao);

    /**
     * Get all the localizacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Localizacao> findAll(Pageable pageable);
    /**
     * Get all the LocalizacaoDTO where Endereco is null.
     *
     * @return the list of entities
     */
    List<Localizacao> findAllWhereEnderecoIsNull();
    /**
     * Get all the LocalizacaoDTO where Bloco is null.
     *
     * @return the list of entities
     */
    List<Localizacao> findAllWhereBlocoIsNull();


    /**
     * Get the "id" localizacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Localizacao> findOne(Long id);

    /**
     * Delete the "id" localizacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
