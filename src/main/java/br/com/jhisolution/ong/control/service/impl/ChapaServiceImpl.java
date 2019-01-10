package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ChapaService;
import br.com.jhisolution.ong.control.domain.Chapa;
import br.com.jhisolution.ong.control.repository.ChapaRepository;
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
 * Service Implementation for managing Chapa.
 */
@Service
@Transactional
public class ChapaServiceImpl implements ChapaService {

    private final Logger log = LoggerFactory.getLogger(ChapaServiceImpl.class);

    private final ChapaRepository chapaRepository;

    public ChapaServiceImpl(ChapaRepository chapaRepository) {
        this.chapaRepository = chapaRepository;
    }

    /**
     * Save a chapa.
     *
     * @param chapa the entity to save
     * @return the persisted entity
     */
    @Override
    public Chapa save(Chapa chapa) {
        log.debug("Request to save Chapa : {}", chapa);
        return chapaRepository.save(chapa);
    }

    /**
     * Get all the chapas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Chapa> findAll(Pageable pageable) {
        log.debug("Request to get all Chapas");
        return chapaRepository.findAll(pageable);
    }



    /**
     *  get all the chapas where EleicaoGanhadora is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Chapa> findAllWhereEleicaoGanhadoraIsNull() {
        log.debug("Request to get all chapas where EleicaoGanhadora is null");
        return StreamSupport
            .stream(chapaRepository.findAll().spliterator(), false)
            .filter(chapa -> chapa.getEleicaoGanhadora() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one chapa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Chapa> findOne(Long id) {
        log.debug("Request to get Chapa : {}", id);
        return chapaRepository.findById(id);
    }

    /**
     * Delete the chapa by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chapa : {}", id);
        chapaRepository.deleteById(id);
    }
}
