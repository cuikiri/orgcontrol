package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ObservacaoCoordenadorService;
import br.com.jhisolution.ong.control.domain.ObservacaoCoordenador;
import br.com.jhisolution.ong.control.repository.ObservacaoCoordenadorRepository;
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
 * Service Implementation for managing ObservacaoCoordenador.
 */
@Service
@Transactional
public class ObservacaoCoordenadorServiceImpl implements ObservacaoCoordenadorService {

    private final Logger log = LoggerFactory.getLogger(ObservacaoCoordenadorServiceImpl.class);

    private final ObservacaoCoordenadorRepository observacaoCoordenadorRepository;

    public ObservacaoCoordenadorServiceImpl(ObservacaoCoordenadorRepository observacaoCoordenadorRepository) {
        this.observacaoCoordenadorRepository = observacaoCoordenadorRepository;
    }

    /**
     * Save a observacaoCoordenador.
     *
     * @param observacaoCoordenador the entity to save
     * @return the persisted entity
     */
    @Override
    public ObservacaoCoordenador save(ObservacaoCoordenador observacaoCoordenador) {
        log.debug("Request to save ObservacaoCoordenador : {}", observacaoCoordenador);
        return observacaoCoordenadorRepository.save(observacaoCoordenador);
    }

    /**
     * Get all the observacaoCoordenadors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ObservacaoCoordenador> findAll(Pageable pageable) {
        log.debug("Request to get all ObservacaoCoordenadors");
        return observacaoCoordenadorRepository.findAll(pageable);
    }



    /**
     *  get all the observacaoCoordenadors where Diario is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ObservacaoCoordenador> findAllWhereDiarioIsNull() {
        log.debug("Request to get all observacaoCoordenadors where Diario is null");
        return StreamSupport
            .stream(observacaoCoordenadorRepository.findAll().spliterator(), false)
            .filter(observacaoCoordenador -> observacaoCoordenador.getDiario() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one observacaoCoordenador by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ObservacaoCoordenador> findOne(Long id) {
        log.debug("Request to get ObservacaoCoordenador : {}", id);
        return observacaoCoordenadorRepository.findById(id);
    }

    /**
     * Delete the observacaoCoordenador by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObservacaoCoordenador : {}", id);
        observacaoCoordenadorRepository.deleteById(id);
    }
}
