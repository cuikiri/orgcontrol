package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ObservacaoProfessorService;
import br.com.jhisolution.ong.control.domain.ObservacaoProfessor;
import br.com.jhisolution.ong.control.repository.ObservacaoProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing ObservacaoProfessor.
 */
@Service
@Transactional
public class ObservacaoProfessorServiceImpl implements ObservacaoProfessorService {

    private final Logger log = LoggerFactory.getLogger(ObservacaoProfessorServiceImpl.class);

    private final ObservacaoProfessorRepository observacaoProfessorRepository;

    public ObservacaoProfessorServiceImpl(ObservacaoProfessorRepository observacaoProfessorRepository) {
        this.observacaoProfessorRepository = observacaoProfessorRepository;
    }

    /**
     * Save a observacaoProfessor.
     *
     * @param observacaoProfessor the entity to save
     * @return the persisted entity
     */
    @Override
    public ObservacaoProfessor save(ObservacaoProfessor observacaoProfessor) {
        log.debug("Request to save ObservacaoProfessor : {}", observacaoProfessor);
        return observacaoProfessorRepository.save(observacaoProfessor);
    }

    /**
     * Get all the observacaoProfessors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ObservacaoProfessor> findAll(Pageable pageable) {
        log.debug("Request to get all ObservacaoProfessors");
        return observacaoProfessorRepository.findAll(pageable);
    }



    /**
     *  get all the observacaoProfessors where Diario is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ObservacaoProfessor> findAllWhereDiarioIsNull() {
        log.debug("Request to get all observacaoProfessors where Diario is null");
        return StreamSupport
            .stream(observacaoProfessorRepository.findAll().spliterator(), false)
            .filter(observacaoProfessor -> observacaoProfessor.getDiario() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one observacaoProfessor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ObservacaoProfessor> findOne(Long id) {
        log.debug("Request to get ObservacaoProfessor : {}", id);
        return observacaoProfessorRepository.findById(id);
    }

    /**
     * Delete the observacaoProfessor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObservacaoProfessor : {}", id);
        observacaoProfessorRepository.deleteById(id);
    }
}
