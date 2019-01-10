package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoBiotipoService;
import br.com.jhisolution.ong.control.domain.TipoBiotipo;
import br.com.jhisolution.ong.control.repository.TipoBiotipoRepository;
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
 * Service Implementation for managing TipoBiotipo.
 */
@Service
@Transactional
public class TipoBiotipoServiceImpl implements TipoBiotipoService {

    private final Logger log = LoggerFactory.getLogger(TipoBiotipoServiceImpl.class);

    private final TipoBiotipoRepository tipoBiotipoRepository;

    public TipoBiotipoServiceImpl(TipoBiotipoRepository tipoBiotipoRepository) {
        this.tipoBiotipoRepository = tipoBiotipoRepository;
    }

    /**
     * Save a tipoBiotipo.
     *
     * @param tipoBiotipo the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoBiotipo save(TipoBiotipo tipoBiotipo) {
        log.debug("Request to save TipoBiotipo : {}", tipoBiotipo);
        return tipoBiotipoRepository.save(tipoBiotipo);
    }

    /**
     * Get all the tipoBiotipos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoBiotipo> findAll(Pageable pageable) {
        log.debug("Request to get all TipoBiotipos");
        return tipoBiotipoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoBiotipos where Biotipo is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoBiotipo> findAllWhereBiotipoIsNull() {
        log.debug("Request to get all tipoBiotipos where Biotipo is null");
        return StreamSupport
            .stream(tipoBiotipoRepository.findAll().spliterator(), false)
            .filter(tipoBiotipo -> tipoBiotipo.getBiotipo() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoBiotipo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoBiotipo> findOne(Long id) {
        log.debug("Request to get TipoBiotipo : {}", id);
        return tipoBiotipoRepository.findById(id);
    }

    /**
     * Delete the tipoBiotipo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoBiotipo : {}", id);
        tipoBiotipoRepository.deleteById(id);
    }
}
