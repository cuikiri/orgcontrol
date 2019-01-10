package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.DadoBiologicoService;
import br.com.jhisolution.ong.control.domain.DadoBiologico;
import br.com.jhisolution.ong.control.repository.DadoBiologicoRepository;
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
 * Service Implementation for managing DadoBiologico.
 */
@Service
@Transactional
public class DadoBiologicoServiceImpl implements DadoBiologicoService {

    private final Logger log = LoggerFactory.getLogger(DadoBiologicoServiceImpl.class);

    private final DadoBiologicoRepository dadoBiologicoRepository;

    public DadoBiologicoServiceImpl(DadoBiologicoRepository dadoBiologicoRepository) {
        this.dadoBiologicoRepository = dadoBiologicoRepository;
    }

    /**
     * Save a dadoBiologico.
     *
     * @param dadoBiologico the entity to save
     * @return the persisted entity
     */
    @Override
    public DadoBiologico save(DadoBiologico dadoBiologico) {
        log.debug("Request to save DadoBiologico : {}", dadoBiologico);
        return dadoBiologicoRepository.save(dadoBiologico);
    }

    /**
     * Get all the dadoBiologicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DadoBiologico> findAll(Pageable pageable) {
        log.debug("Request to get all DadoBiologicos");
        return dadoBiologicoRepository.findAll(pageable);
    }



    /**
     *  get all the dadoBiologicos where DadosMedico is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<DadoBiologico> findAllWhereDadosMedicoIsNull() {
        log.debug("Request to get all dadoBiologicos where DadosMedico is null");
        return StreamSupport
            .stream(dadoBiologicoRepository.findAll().spliterator(), false)
            .filter(dadoBiologico -> dadoBiologico.getDadosMedico() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one dadoBiologico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DadoBiologico> findOne(Long id) {
        log.debug("Request to get DadoBiologico : {}", id);
        return dadoBiologicoRepository.findById(id);
    }

    /**
     * Delete the dadoBiologico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DadoBiologico : {}", id);
        dadoBiologicoRepository.deleteById(id);
    }
}
