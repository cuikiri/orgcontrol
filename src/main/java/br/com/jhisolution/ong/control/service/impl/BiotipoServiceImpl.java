package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.BiotipoService;
import br.com.jhisolution.ong.control.domain.Biotipo;
import br.com.jhisolution.ong.control.repository.BiotipoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Biotipo.
 */
@Service
@Transactional
public class BiotipoServiceImpl implements BiotipoService {

    private final Logger log = LoggerFactory.getLogger(BiotipoServiceImpl.class);

    private final BiotipoRepository biotipoRepository;

    public BiotipoServiceImpl(BiotipoRepository biotipoRepository) {
        this.biotipoRepository = biotipoRepository;
    }

    /**
     * Save a biotipo.
     *
     * @param biotipo the entity to save
     * @return the persisted entity
     */
    @Override
    public Biotipo save(Biotipo biotipo) {
        log.debug("Request to save Biotipo : {}", biotipo);
        return biotipoRepository.save(biotipo);
    }

    /**
     * Get all the biotipos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Biotipo> findAll(Pageable pageable) {
        log.debug("Request to get all Biotipos");
        return biotipoRepository.findAll(pageable);
    }


    /**
     * Get one biotipo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Biotipo> findOne(Long id) {
        log.debug("Request to get Biotipo : {}", id);
        return biotipoRepository.findById(id);
    }

    /**
     * Delete the biotipo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Biotipo : {}", id);
        biotipoRepository.deleteById(id);
    }
}
