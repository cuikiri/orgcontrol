package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.DiaSemanaService;
import br.com.jhisolution.ong.control.domain.DiaSemana;
import br.com.jhisolution.ong.control.repository.DiaSemanaRepository;
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
 * Service Implementation for managing DiaSemana.
 */
@Service
@Transactional
public class DiaSemanaServiceImpl implements DiaSemanaService {

    private final Logger log = LoggerFactory.getLogger(DiaSemanaServiceImpl.class);

    private final DiaSemanaRepository diaSemanaRepository;

    public DiaSemanaServiceImpl(DiaSemanaRepository diaSemanaRepository) {
        this.diaSemanaRepository = diaSemanaRepository;
    }

    /**
     * Save a diaSemana.
     *
     * @param diaSemana the entity to save
     * @return the persisted entity
     */
    @Override
    public DiaSemana save(DiaSemana diaSemana) {
        log.debug("Request to save DiaSemana : {}", diaSemana);
        return diaSemanaRepository.save(diaSemana);
    }

    /**
     * Get all the diaSemanas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DiaSemana> findAll(Pageable pageable) {
        log.debug("Request to get all DiaSemanas");
        return diaSemanaRepository.findAll(pageable);
    }



    /**
     *  get all the diaSemanas where HorarioMateria is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<DiaSemana> findAllWhereHorarioMateriaIsNull() {
        log.debug("Request to get all diaSemanas where HorarioMateria is null");
        return StreamSupport
            .stream(diaSemanaRepository.findAll().spliterator(), false)
            .filter(diaSemana -> diaSemana.getHorarioMateria() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one diaSemana by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DiaSemana> findOne(Long id) {
        log.debug("Request to get DiaSemana : {}", id);
        return diaSemanaRepository.findById(id);
    }

    /**
     * Delete the diaSemana by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DiaSemana : {}", id);
        diaSemanaRepository.deleteById(id);
    }
}
