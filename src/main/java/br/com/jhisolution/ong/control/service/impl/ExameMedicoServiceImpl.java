package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ExameMedicoService;
import br.com.jhisolution.ong.control.domain.ExameMedico;
import br.com.jhisolution.ong.control.repository.ExameMedicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ExameMedico.
 */
@Service
@Transactional
public class ExameMedicoServiceImpl implements ExameMedicoService {

    private final Logger log = LoggerFactory.getLogger(ExameMedicoServiceImpl.class);

    private final ExameMedicoRepository exameMedicoRepository;

    public ExameMedicoServiceImpl(ExameMedicoRepository exameMedicoRepository) {
        this.exameMedicoRepository = exameMedicoRepository;
    }

    /**
     * Save a exameMedico.
     *
     * @param exameMedico the entity to save
     * @return the persisted entity
     */
    @Override
    public ExameMedico save(ExameMedico exameMedico) {
        log.debug("Request to save ExameMedico : {}", exameMedico);
        return exameMedicoRepository.save(exameMedico);
    }

    /**
     * Get all the exameMedicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ExameMedico> findAll(Pageable pageable) {
        log.debug("Request to get all ExameMedicos");
        return exameMedicoRepository.findAll(pageable);
    }

    /**
     * Get all the ExameMedico with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ExameMedico> findAllWithEagerRelationships(Pageable pageable) {
        return exameMedicoRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one exameMedico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExameMedico> findOne(Long id) {
        log.debug("Request to get ExameMedico : {}", id);
        return exameMedicoRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the exameMedico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExameMedico : {}", id);
        exameMedicoRepository.deleteById(id);
    }
}
