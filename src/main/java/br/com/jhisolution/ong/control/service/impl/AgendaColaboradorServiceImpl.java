package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AgendaColaboradorService;
import br.com.jhisolution.ong.control.domain.AgendaColaborador;
import br.com.jhisolution.ong.control.repository.AgendaColaboradorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AgendaColaborador.
 */
@Service
@Transactional
public class AgendaColaboradorServiceImpl implements AgendaColaboradorService {

    private final Logger log = LoggerFactory.getLogger(AgendaColaboradorServiceImpl.class);

    private final AgendaColaboradorRepository agendaColaboradorRepository;

    public AgendaColaboradorServiceImpl(AgendaColaboradorRepository agendaColaboradorRepository) {
        this.agendaColaboradorRepository = agendaColaboradorRepository;
    }

    /**
     * Save a agendaColaborador.
     *
     * @param agendaColaborador the entity to save
     * @return the persisted entity
     */
    @Override
    public AgendaColaborador save(AgendaColaborador agendaColaborador) {
        log.debug("Request to save AgendaColaborador : {}", agendaColaborador);
        return agendaColaboradorRepository.save(agendaColaborador);
    }

    /**
     * Get all the agendaColaboradors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AgendaColaborador> findAll(Pageable pageable) {
        log.debug("Request to get all AgendaColaboradors");
        return agendaColaboradorRepository.findAll(pageable);
    }


    /**
     * Get one agendaColaborador by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AgendaColaborador> findOne(Long id) {
        log.debug("Request to get AgendaColaborador : {}", id);
        return agendaColaboradorRepository.findById(id);
    }

    /**
     * Delete the agendaColaborador by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AgendaColaborador : {}", id);
        agendaColaboradorRepository.deleteById(id);
    }
}
