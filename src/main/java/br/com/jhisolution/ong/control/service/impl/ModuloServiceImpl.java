package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ModuloService;
import br.com.jhisolution.ong.control.domain.Modulo;
import br.com.jhisolution.ong.control.repository.ModuloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Modulo.
 */
@Service
@Transactional
public class ModuloServiceImpl implements ModuloService {

    private final Logger log = LoggerFactory.getLogger(ModuloServiceImpl.class);

    private final ModuloRepository moduloRepository;

    public ModuloServiceImpl(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    /**
     * Save a modulo.
     *
     * @param modulo the entity to save
     * @return the persisted entity
     */
    @Override
    public Modulo save(Modulo modulo) {
        log.debug("Request to save Modulo : {}", modulo);
        return moduloRepository.save(modulo);
    }

    /**
     * Get all the modulos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Modulo> findAll(Pageable pageable) {
        log.debug("Request to get all Modulos");
        return moduloRepository.findAll(pageable);
    }


    /**
     * Get one modulo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Modulo> findOne(Long id) {
        log.debug("Request to get Modulo : {}", id);
        return moduloRepository.findById(id);
    }

    /**
     * Delete the modulo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modulo : {}", id);
        moduloRepository.deleteById(id);
    }
}
