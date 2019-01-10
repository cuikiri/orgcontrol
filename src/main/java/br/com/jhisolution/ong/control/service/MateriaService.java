package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Materia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Materia.
 */
public interface MateriaService {

    /**
     * Save a materia.
     *
     * @param materia the entity to save
     * @return the persisted entity
     */
    Materia save(Materia materia);

    /**
     * Get all the materias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Materia> findAll(Pageable pageable);
    /**
     * Get all the MateriaDTO where HorarioMateria is null.
     *
     * @return the list of entities
     */
    List<Materia> findAllWhereHorarioMateriaIsNull();
    /**
     * Get all the MateriaDTO where Diario is null.
     *
     * @return the list of entities
     */
    List<Materia> findAllWhereDiarioIsNull();


    /**
     * Get the "id" materia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Materia> findOne(Long id);

    /**
     * Delete the "id" materia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
