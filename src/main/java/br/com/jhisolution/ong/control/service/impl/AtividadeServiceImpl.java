package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AtividadeService;
import br.com.jhisolution.ong.control.domain.Atividade;
import br.com.jhisolution.ong.control.repository.AtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Atividade.
 */
@Service
@Transactional
public class AtividadeServiceImpl implements AtividadeService {

    private final Logger log = LoggerFactory.getLogger(AtividadeServiceImpl.class);

    private final AtividadeRepository atividadeRepository;

    public AtividadeServiceImpl(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    /**
     * Save a atividade.
     *
     * @param atividade the entity to save
     * @return the persisted entity
     */
    @Override
    public Atividade save(Atividade atividade) {
        log.debug("Request to save Atividade : {}", atividade);
        return atividadeRepository.save(atividade);
    }

    /**
     * Get all the atividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Atividade> findAll(Pageable pageable) {
        log.debug("Request to get all Atividades");
        return atividadeRepository.findAll(pageable);
    }

    /**
     * Get all the Atividade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Atividade> findAllWithEagerRelationships(Pageable pageable) {
        return atividadeRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one atividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Atividade> findOne(Long id) {
        log.debug("Request to get Atividade : {}", id);
        return atividadeRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the atividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Atividade : {}", id);
        atividadeRepository.deleteById(id);
    }
}
