package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.EstadoCivilService;
import br.com.jhisolution.ong.control.domain.EstadoCivil;
import br.com.jhisolution.ong.control.repository.EstadoCivilRepository;
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
 * Service Implementation for managing EstadoCivil.
 */
@Service
@Transactional
public class EstadoCivilServiceImpl implements EstadoCivilService {

    private final Logger log = LoggerFactory.getLogger(EstadoCivilServiceImpl.class);

    private final EstadoCivilRepository estadoCivilRepository;

    public EstadoCivilServiceImpl(EstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    /**
     * Save a estadoCivil.
     *
     * @param estadoCivil the entity to save
     * @return the persisted entity
     */
    @Override
    public EstadoCivil save(EstadoCivil estadoCivil) {
        log.debug("Request to save EstadoCivil : {}", estadoCivil);
        return estadoCivilRepository.save(estadoCivil);
    }

    /**
     * Get all the estadoCivils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EstadoCivil> findAll(Pageable pageable) {
        log.debug("Request to get all EstadoCivils");
        return estadoCivilRepository.findAll(pageable);
    }



    /**
     *  get all the estadoCivils where Colaborador is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EstadoCivil> findAllWhereColaboradorIsNull() {
        log.debug("Request to get all estadoCivils where Colaborador is null");
        return StreamSupport
            .stream(estadoCivilRepository.findAll().spliterator(), false)
            .filter(estadoCivil -> estadoCivil.getColaborador() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one estadoCivil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadoCivil> findOne(Long id) {
        log.debug("Request to get EstadoCivil : {}", id);
        return estadoCivilRepository.findById(id);
    }

    /**
     * Delete the estadoCivil by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadoCivil : {}", id);
        estadoCivilRepository.deleteById(id);
    }
}
