package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoadoBiologicoService;
import br.com.jhisolution.ong.control.domain.TipoadoBiologico;
import br.com.jhisolution.ong.control.repository.TipoadoBiologicoRepository;
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
 * Service Implementation for managing TipoadoBiologico.
 */
@Service
@Transactional
public class TipoadoBiologicoServiceImpl implements TipoadoBiologicoService {

    private final Logger log = LoggerFactory.getLogger(TipoadoBiologicoServiceImpl.class);

    private final TipoadoBiologicoRepository tipoadoBiologicoRepository;

    public TipoadoBiologicoServiceImpl(TipoadoBiologicoRepository tipoadoBiologicoRepository) {
        this.tipoadoBiologicoRepository = tipoadoBiologicoRepository;
    }

    /**
     * Save a tipoadoBiologico.
     *
     * @param tipoadoBiologico the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoadoBiologico save(TipoadoBiologico tipoadoBiologico) {
        log.debug("Request to save TipoadoBiologico : {}", tipoadoBiologico);
        return tipoadoBiologicoRepository.save(tipoadoBiologico);
    }

    /**
     * Get all the tipoadoBiologicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoadoBiologico> findAll(Pageable pageable) {
        log.debug("Request to get all TipoadoBiologicos");
        return tipoadoBiologicoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoadoBiologicos where DadoBiologico is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoadoBiologico> findAllWhereDadoBiologicoIsNull() {
        log.debug("Request to get all tipoadoBiologicos where DadoBiologico is null");
        return StreamSupport
            .stream(tipoadoBiologicoRepository.findAll().spliterator(), false)
            .filter(tipoadoBiologico -> tipoadoBiologico.getDadoBiologico() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoadoBiologico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoadoBiologico> findOne(Long id) {
        log.debug("Request to get TipoadoBiologico : {}", id);
        return tipoadoBiologicoRepository.findById(id);
    }

    /**
     * Delete the tipoadoBiologico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoadoBiologico : {}", id);
        tipoadoBiologicoRepository.deleteById(id);
    }
}
