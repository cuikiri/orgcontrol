package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RacaService;
import br.com.jhisolution.ong.control.domain.Raca;
import br.com.jhisolution.ong.control.repository.RacaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Raca.
 */
@Service
@Transactional
public class RacaServiceImpl implements RacaService {

    private final Logger log = LoggerFactory.getLogger(RacaServiceImpl.class);

    private final RacaRepository racaRepository;

    public RacaServiceImpl(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    /**
     * Save a raca.
     *
     * @param raca the entity to save
     * @return the persisted entity
     */
    @Override
    public Raca save(Raca raca) {
        log.debug("Request to save Raca : {}", raca);
        return racaRepository.save(raca);
    }

    /**
     * Get all the racas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Raca> findAll(Pageable pageable) {
        log.debug("Request to get all Racas");
        return racaRepository.findAll(pageable);
    }


    /**
     * Get one raca by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Raca> findOne(Long id) {
        log.debug("Request to get Raca : {}", id);
        return racaRepository.findById(id);
    }

    /**
     * Delete the raca by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Raca : {}", id);
        racaRepository.deleteById(id);
    }
}
