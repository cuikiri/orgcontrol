package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.DependenteLegalService;
import br.com.jhisolution.ong.control.domain.DependenteLegal;
import br.com.jhisolution.ong.control.repository.DependenteLegalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DependenteLegal.
 */
@Service
@Transactional
public class DependenteLegalServiceImpl implements DependenteLegalService {

    private final Logger log = LoggerFactory.getLogger(DependenteLegalServiceImpl.class);

    private final DependenteLegalRepository dependenteLegalRepository;

    public DependenteLegalServiceImpl(DependenteLegalRepository dependenteLegalRepository) {
        this.dependenteLegalRepository = dependenteLegalRepository;
    }

    /**
     * Save a dependenteLegal.
     *
     * @param dependenteLegal the entity to save
     * @return the persisted entity
     */
    @Override
    public DependenteLegal save(DependenteLegal dependenteLegal) {
        log.debug("Request to save DependenteLegal : {}", dependenteLegal);
        return dependenteLegalRepository.save(dependenteLegal);
    }

    /**
     * Get all the dependenteLegals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DependenteLegal> findAll(Pageable pageable) {
        log.debug("Request to get all DependenteLegals");
        return dependenteLegalRepository.findAll(pageable);
    }


    /**
     * Get one dependenteLegal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DependenteLegal> findOne(Long id) {
        log.debug("Request to get DependenteLegal : {}", id);
        return dependenteLegalRepository.findById(id);
    }

    /**
     * Delete the dependenteLegal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DependenteLegal : {}", id);
        dependenteLegalRepository.deleteById(id);
    }
}
