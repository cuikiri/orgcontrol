package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.MateriaService;
import br.com.jhisolution.ong.control.domain.Materia;
import br.com.jhisolution.ong.control.repository.MateriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Materia.
 */
@Service
@Transactional
public class MateriaServiceImpl implements MateriaService {

    private final Logger log = LoggerFactory.getLogger(MateriaServiceImpl.class);

    private final MateriaRepository materiaRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    /**
     * Save a materia.
     *
     * @param materia the entity to save
     * @return the persisted entity
     */
    @Override
    public Materia save(Materia materia) {
        log.debug("Request to save Materia : {}", materia);
        return materiaRepository.save(materia);
    }

    /**
     * Get all the materias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Materia> findAll(Pageable pageable) {
        log.debug("Request to get all Materias");
        return materiaRepository.findAll(pageable);
    }



    /**
     *  get all the materias where HorarioMateria is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Materia> findAllWhereHorarioMateriaIsNull() {
        log.debug("Request to get all materias where HorarioMateria is null");
        return StreamSupport
            .stream(materiaRepository.findAll().spliterator(), false)
            .filter(materia -> materia.getHorarioMateria() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the materias where Diario is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Materia> findAllWhereDiarioIsNull() {
        log.debug("Request to get all materias where Diario is null");
        return StreamSupport
            .stream(materiaRepository.findAll().spliterator(), false)
            .filter(materia -> materia.getDiario() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one materia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Materia> findOne(Long id) {
        log.debug("Request to get Materia : {}", id);
        return materiaRepository.findById(id);
    }

    /**
     * Delete the materia by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Materia : {}", id);
        materiaRepository.deleteById(id);
    }
}
