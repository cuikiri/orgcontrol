package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.DiarioService;
import br.com.jhisolution.ong.control.domain.Diario;
import br.com.jhisolution.ong.control.repository.DiarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Diario.
 */
@Service
@Transactional
public class DiarioServiceImpl implements DiarioService {

    private final Logger log = LoggerFactory.getLogger(DiarioServiceImpl.class);

    private final DiarioRepository diarioRepository;

    public DiarioServiceImpl(DiarioRepository diarioRepository) {
        this.diarioRepository = diarioRepository;
    }

    /**
     * Save a diario.
     *
     * @param diario the entity to save
     * @return the persisted entity
     */
    @Override
    public Diario save(Diario diario) {
        log.debug("Request to save Diario : {}", diario);
        return diarioRepository.save(diario);
    }

    /**
     * Get all the diarios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Diario> findAll(Pageable pageable) {
        log.debug("Request to get all Diarios");
        return diarioRepository.findAll(pageable);
    }


    /**
     * Get one diario by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Diario> findOne(Long id) {
        log.debug("Request to get Diario : {}", id);
        return diarioRepository.findById(id);
    }

    /**
     * Delete the diario by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Diario : {}", id);
        diarioRepository.deleteById(id);
    }
}
