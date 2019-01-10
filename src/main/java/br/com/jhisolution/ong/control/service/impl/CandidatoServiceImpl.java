package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.CandidatoService;
import br.com.jhisolution.ong.control.domain.Candidato;
import br.com.jhisolution.ong.control.repository.CandidatoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Candidato.
 */
@Service
@Transactional
public class CandidatoServiceImpl implements CandidatoService {

    private final Logger log = LoggerFactory.getLogger(CandidatoServiceImpl.class);

    private final CandidatoRepository candidatoRepository;

    public CandidatoServiceImpl(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    /**
     * Save a candidato.
     *
     * @param candidato the entity to save
     * @return the persisted entity
     */
    @Override
    public Candidato save(Candidato candidato) {
        log.debug("Request to save Candidato : {}", candidato);
        return candidatoRepository.save(candidato);
    }

    /**
     * Get all the candidatoes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Candidato> findAll(Pageable pageable) {
        log.debug("Request to get all Candidatoes");
        return candidatoRepository.findAll(pageable);
    }


    /**
     * Get one candidato by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Candidato> findOne(Long id) {
        log.debug("Request to get Candidato : {}", id);
        return candidatoRepository.findById(id);
    }

    /**
     * Delete the candidato by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Candidato : {}", id);
        candidatoRepository.deleteById(id);
    }
}
