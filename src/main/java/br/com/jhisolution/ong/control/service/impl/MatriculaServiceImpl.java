package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.MatriculaService;
import br.com.jhisolution.ong.control.domain.Matricula;
import br.com.jhisolution.ong.control.repository.MatriculaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Matricula.
 */
@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {

    private final Logger log = LoggerFactory.getLogger(MatriculaServiceImpl.class);

    private final MatriculaRepository matriculaRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    /**
     * Save a matricula.
     *
     * @param matricula the entity to save
     * @return the persisted entity
     */
    @Override
    public Matricula save(Matricula matricula) {
        log.debug("Request to save Matricula : {}", matricula);
        return matriculaRepository.save(matricula);
    }

    /**
     * Get all the matriculas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Matricula> findAll(Pageable pageable) {
        log.debug("Request to get all Matriculas");
        return matriculaRepository.findAll(pageable);
    }


    /**
     * Get one matricula by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Matricula> findOne(Long id) {
        log.debug("Request to get Matricula : {}", id);
        return matriculaRepository.findById(id);
    }

    /**
     * Delete the matricula by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Matricula : {}", id);
        matriculaRepository.deleteById(id);
    }
}
