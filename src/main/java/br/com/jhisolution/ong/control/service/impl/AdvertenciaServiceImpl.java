package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AdvertenciaService;
import br.com.jhisolution.ong.control.domain.Advertencia;
import br.com.jhisolution.ong.control.repository.AdvertenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Advertencia.
 */
@Service
@Transactional
public class AdvertenciaServiceImpl implements AdvertenciaService {

    private final Logger log = LoggerFactory.getLogger(AdvertenciaServiceImpl.class);

    private final AdvertenciaRepository advertenciaRepository;

    public AdvertenciaServiceImpl(AdvertenciaRepository advertenciaRepository) {
        this.advertenciaRepository = advertenciaRepository;
    }

    /**
     * Save a advertencia.
     *
     * @param advertencia the entity to save
     * @return the persisted entity
     */
    @Override
    public Advertencia save(Advertencia advertencia) {
        log.debug("Request to save Advertencia : {}", advertencia);
        return advertenciaRepository.save(advertencia);
    }

    /**
     * Get all the advertencias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Advertencia> findAll(Pageable pageable) {
        log.debug("Request to get all Advertencias");
        return advertenciaRepository.findAll(pageable);
    }


    /**
     * Get one advertencia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Advertencia> findOne(Long id) {
        log.debug("Request to get Advertencia : {}", id);
        return advertenciaRepository.findById(id);
    }

    /**
     * Delete the advertencia by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Advertencia : {}", id);
        advertenciaRepository.deleteById(id);
    }
}
