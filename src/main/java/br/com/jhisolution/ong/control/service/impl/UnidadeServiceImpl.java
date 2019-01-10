package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.UnidadeService;
import br.com.jhisolution.ong.control.domain.Unidade;
import br.com.jhisolution.ong.control.repository.UnidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Unidade.
 */
@Service
@Transactional
public class UnidadeServiceImpl implements UnidadeService {

    private final Logger log = LoggerFactory.getLogger(UnidadeServiceImpl.class);

    private final UnidadeRepository unidadeRepository;

    public UnidadeServiceImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    /**
     * Save a unidade.
     *
     * @param unidade the entity to save
     * @return the persisted entity
     */
    @Override
    public Unidade save(Unidade unidade) {
        log.debug("Request to save Unidade : {}", unidade);
        return unidadeRepository.save(unidade);
    }

    /**
     * Get all the unidades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Unidade> findAll(Pageable pageable) {
        log.debug("Request to get all Unidades");
        return unidadeRepository.findAll(pageable);
    }


    /**
     * Get one unidade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Unidade> findOne(Long id) {
        log.debug("Request to get Unidade : {}", id);
        return unidadeRepository.findById(id);
    }

    /**
     * Delete the unidade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Unidade : {}", id);
        unidadeRepository.deleteById(id);
    }
}
