package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.DadosMedico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DadosMedico.
 */
public interface DadosMedicoService {

    /**
     * Save a dadosMedico.
     *
     * @param dadosMedico the entity to save
     * @return the persisted entity
     */
    DadosMedico save(DadosMedico dadosMedico);

    /**
     * Get all the dadosMedicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DadosMedico> findAll(Pageable pageable);
    /**
     * Get all the DadosMedicoDTO where Colaborador is null.
     *
     * @return the list of entities
     */
    List<DadosMedico> findAllWhereColaboradorIsNull();
    /**
     * Get all the DadosMedicoDTO where Aluno is null.
     *
     * @return the list of entities
     */
    List<DadosMedico> findAllWhereAlunoIsNull();


    /**
     * Get the "id" dadosMedico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DadosMedico> findOne(Long id);

    /**
     * Delete the "id" dadosMedico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
