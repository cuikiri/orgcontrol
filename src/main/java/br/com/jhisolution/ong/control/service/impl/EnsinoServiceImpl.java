package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.EnsinoService;
import br.com.jhisolution.ong.control.domain.Ensino;
import br.com.jhisolution.ong.control.repository.EnsinoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Ensino.
 */
@Service
@Transactional
public class EnsinoServiceImpl implements EnsinoService {

    private final Logger log = LoggerFactory.getLogger(EnsinoServiceImpl.class);

    private final EnsinoRepository ensinoRepository;

    public EnsinoServiceImpl(EnsinoRepository ensinoRepository) {
        this.ensinoRepository = ensinoRepository;
    }

    /**
     * Save a ensino.
     *
     * @param ensino the entity to save
     * @return the persisted entity
     */
    @Override
    public Ensino save(Ensino ensino) {
        log.debug("Request to save Ensino : {}", ensino);
        return ensinoRepository.save(ensino);
    }

    /**
     * Get all the ensinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Ensino> findAll(Pageable pageable) {
        log.debug("Request to get all Ensinos");
        return ensinoRepository.findAll(pageable);
    }


    /**
     * Get one ensino by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Ensino> findOne(Long id) {
        log.debug("Request to get Ensino : {}", id);
        return ensinoRepository.findById(id);
    }

    /**
     * Delete the ensino by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ensino : {}", id);
        ensinoRepository.deleteById(id);
    }
}
