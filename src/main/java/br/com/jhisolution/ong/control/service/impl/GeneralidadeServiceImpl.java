package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.GeneralidadeService;
import br.com.jhisolution.ong.control.domain.Generalidade;
import br.com.jhisolution.ong.control.repository.GeneralidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Generalidade.
 */
@Service
@Transactional
public class GeneralidadeServiceImpl implements GeneralidadeService {

    private final Logger log = LoggerFactory.getLogger(GeneralidadeServiceImpl.class);

    private final GeneralidadeRepository generalidadeRepository;

    public GeneralidadeServiceImpl(GeneralidadeRepository generalidadeRepository) {
        this.generalidadeRepository = generalidadeRepository;
    }

    /**
     * Save a generalidade.
     *
     * @param generalidade the entity to save
     * @return the persisted entity
     */
    @Override
    public Generalidade save(Generalidade generalidade) {
        log.debug("Request to save Generalidade : {}", generalidade);
        return generalidadeRepository.save(generalidade);
    }

    /**
     * Get all the generalidades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Generalidade> findAll(Pageable pageable) {
        log.debug("Request to get all Generalidades");
        return generalidadeRepository.findAll(pageable);
    }


    /**
     * Get one generalidade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Generalidade> findOne(Long id) {
        log.debug("Request to get Generalidade : {}", id);
        return generalidadeRepository.findById(id);
    }

    /**
     * Delete the generalidade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Generalidade : {}", id);
        generalidadeRepository.deleteById(id);
    }
}
