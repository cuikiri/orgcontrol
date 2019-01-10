package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TurmaService;
import br.com.jhisolution.ong.control.domain.Turma;
import br.com.jhisolution.ong.control.repository.TurmaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Turma.
 */
@Service
@Transactional
public class TurmaServiceImpl implements TurmaService {

    private final Logger log = LoggerFactory.getLogger(TurmaServiceImpl.class);

    private final TurmaRepository turmaRepository;

    public TurmaServiceImpl(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    /**
     * Save a turma.
     *
     * @param turma the entity to save
     * @return the persisted entity
     */
    @Override
    public Turma save(Turma turma) {
        log.debug("Request to save Turma : {}", turma);
        return turmaRepository.save(turma);
    }

    /**
     * Get all the turmas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Turma> findAll(Pageable pageable) {
        log.debug("Request to get all Turmas");
        return turmaRepository.findAll(pageable);
    }

    /**
     * Get all the Turma with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Turma> findAllWithEagerRelationships(Pageable pageable) {
        return turmaRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one turma by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Turma> findOne(Long id) {
        log.debug("Request to get Turma : {}", id);
        return turmaRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the turma by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Turma : {}", id);
        turmaRepository.deleteById(id);
    }
}
