package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.LocomocaoService;
import br.com.jhisolution.ong.control.domain.Locomocao;
import br.com.jhisolution.ong.control.repository.LocomocaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Locomocao.
 */
@Service
@Transactional
public class LocomocaoServiceImpl implements LocomocaoService {

    private final Logger log = LoggerFactory.getLogger(LocomocaoServiceImpl.class);

    private final LocomocaoRepository locomocaoRepository;

    public LocomocaoServiceImpl(LocomocaoRepository locomocaoRepository) {
        this.locomocaoRepository = locomocaoRepository;
    }

    /**
     * Save a locomocao.
     *
     * @param locomocao the entity to save
     * @return the persisted entity
     */
    @Override
    public Locomocao save(Locomocao locomocao) {
        log.debug("Request to save Locomocao : {}", locomocao);
        return locomocaoRepository.save(locomocao);
    }

    /**
     * Get all the locomocaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Locomocao> findAll(Pageable pageable) {
        log.debug("Request to get all Locomocaos");
        return locomocaoRepository.findAll(pageable);
    }


    /**
     * Get one locomocao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Locomocao> findOne(Long id) {
        log.debug("Request to get Locomocao : {}", id);
        return locomocaoRepository.findById(id);
    }

    /**
     * Delete the locomocao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Locomocao : {}", id);
        locomocaoRepository.deleteById(id);
    }
}
