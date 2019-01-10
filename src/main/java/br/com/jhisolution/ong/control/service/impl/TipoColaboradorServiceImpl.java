package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoColaboradorService;
import br.com.jhisolution.ong.control.domain.TipoColaborador;
import br.com.jhisolution.ong.control.repository.TipoColaboradorRepository;
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
 * Service Implementation for managing TipoColaborador.
 */
@Service
@Transactional
public class TipoColaboradorServiceImpl implements TipoColaboradorService {

    private final Logger log = LoggerFactory.getLogger(TipoColaboradorServiceImpl.class);

    private final TipoColaboradorRepository tipoColaboradorRepository;

    public TipoColaboradorServiceImpl(TipoColaboradorRepository tipoColaboradorRepository) {
        this.tipoColaboradorRepository = tipoColaboradorRepository;
    }

    /**
     * Save a tipoColaborador.
     *
     * @param tipoColaborador the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoColaborador save(TipoColaborador tipoColaborador) {
        log.debug("Request to save TipoColaborador : {}", tipoColaborador);
        return tipoColaboradorRepository.save(tipoColaborador);
    }

    /**
     * Get all the tipoColaboradors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoColaborador> findAll(Pageable pageable) {
        log.debug("Request to get all TipoColaboradors");
        return tipoColaboradorRepository.findAll(pageable);
    }



    /**
     *  get all the tipoColaboradors where Colaborador is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoColaborador> findAllWhereColaboradorIsNull() {
        log.debug("Request to get all tipoColaboradors where Colaborador is null");
        return StreamSupport
            .stream(tipoColaboradorRepository.findAll().spliterator(), false)
            .filter(tipoColaborador -> tipoColaborador.getColaborador() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoColaborador by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoColaborador> findOne(Long id) {
        log.debug("Request to get TipoColaborador : {}", id);
        return tipoColaboradorRepository.findById(id);
    }

    /**
     * Delete the tipoColaborador by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoColaborador : {}", id);
        tipoColaboradorRepository.deleteById(id);
    }
}
