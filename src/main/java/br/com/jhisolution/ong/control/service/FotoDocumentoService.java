package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.FotoDocumento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FotoDocumento.
 */
public interface FotoDocumentoService {

    /**
     * Save a fotoDocumento.
     *
     * @param fotoDocumento the entity to save
     * @return the persisted entity
     */
    FotoDocumento save(FotoDocumento fotoDocumento);

    /**
     * Get all the fotoDocumentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FotoDocumento> findAll(Pageable pageable);
    /**
     * Get all the FotoDocumentoDTO where Documento is null.
     *
     * @return the list of entities
     */
    List<FotoDocumento> findAllWhereDocumentoIsNull();


    /**
     * Get the "id" fotoDocumento.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FotoDocumento> findOne(Long id);

    /**
     * Delete the "id" fotoDocumento.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
