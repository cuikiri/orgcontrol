package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.MateriaAcompanhamentoService;
import br.com.jhisolution.ong.control.domain.MateriaAcompanhamento;
import br.com.jhisolution.ong.control.repository.MateriaAcompanhamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MateriaAcompanhamento.
 */
@Service
@Transactional
public class MateriaAcompanhamentoServiceImpl implements MateriaAcompanhamentoService {

    private final Logger log = LoggerFactory.getLogger(MateriaAcompanhamentoServiceImpl.class);

    private final MateriaAcompanhamentoRepository materiaAcompanhamentoRepository;

    public MateriaAcompanhamentoServiceImpl(MateriaAcompanhamentoRepository materiaAcompanhamentoRepository) {
        this.materiaAcompanhamentoRepository = materiaAcompanhamentoRepository;
    }

    /**
     * Save a materiaAcompanhamento.
     *
     * @param materiaAcompanhamento the entity to save
     * @return the persisted entity
     */
    @Override
    public MateriaAcompanhamento save(MateriaAcompanhamento materiaAcompanhamento) {
        log.debug("Request to save MateriaAcompanhamento : {}", materiaAcompanhamento);
        return materiaAcompanhamentoRepository.save(materiaAcompanhamento);
    }

    /**
     * Get all the materiaAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MateriaAcompanhamento> findAll(Pageable pageable) {
        log.debug("Request to get all MateriaAcompanhamentos");
        return materiaAcompanhamentoRepository.findAll(pageable);
    }


    /**
     * Get one materiaAcompanhamento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MateriaAcompanhamento> findOne(Long id) {
        log.debug("Request to get MateriaAcompanhamento : {}", id);
        return materiaAcompanhamentoRepository.findById(id);
    }

    /**
     * Delete the materiaAcompanhamento by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MateriaAcompanhamento : {}", id);
        materiaAcompanhamentoRepository.deleteById(id);
    }
}
