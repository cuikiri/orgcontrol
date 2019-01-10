package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.BlocoService;
import br.com.jhisolution.ong.control.domain.Bloco;
import br.com.jhisolution.ong.control.repository.BlocoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Bloco.
 */
@Service
@Transactional
public class BlocoServiceImpl implements BlocoService {

    private final Logger log = LoggerFactory.getLogger(BlocoServiceImpl.class);

    private final BlocoRepository blocoRepository;

    public BlocoServiceImpl(BlocoRepository blocoRepository) {
        this.blocoRepository = blocoRepository;
    }

    /**
     * Save a bloco.
     *
     * @param bloco the entity to save
     * @return the persisted entity
     */
    @Override
    public Bloco save(Bloco bloco) {
        log.debug("Request to save Bloco : {}", bloco);
        return blocoRepository.save(bloco);
    }

    /**
     * Get all the blocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bloco> findAll(Pageable pageable) {
        log.debug("Request to get all Blocos");
        return blocoRepository.findAll(pageable);
    }


    /**
     * Get one bloco by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Bloco> findOne(Long id) {
        log.debug("Request to get Bloco : {}", id);
        return blocoRepository.findById(id);
    }

    /**
     * Delete the bloco by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bloco : {}", id);
        blocoRepository.deleteById(id);
    }
}
