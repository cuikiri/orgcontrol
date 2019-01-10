package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.BimestreService;
import br.com.jhisolution.ong.control.domain.Bimestre;
import br.com.jhisolution.ong.control.repository.BimestreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Bimestre.
 */
@Service
@Transactional
public class BimestreServiceImpl implements BimestreService {

    private final Logger log = LoggerFactory.getLogger(BimestreServiceImpl.class);

    private final BimestreRepository bimestreRepository;

    public BimestreServiceImpl(BimestreRepository bimestreRepository) {
        this.bimestreRepository = bimestreRepository;
    }

    /**
     * Save a bimestre.
     *
     * @param bimestre the entity to save
     * @return the persisted entity
     */
    @Override
    public Bimestre save(Bimestre bimestre) {
        log.debug("Request to save Bimestre : {}", bimestre);
        return bimestreRepository.save(bimestre);
    }

    /**
     * Get all the bimestres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bimestre> findAll(Pageable pageable) {
        log.debug("Request to get all Bimestres");
        return bimestreRepository.findAll(pageable);
    }


    /**
     * Get one bimestre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Bimestre> findOne(Long id) {
        log.debug("Request to get Bimestre : {}", id);
        return bimestreRepository.findById(id);
    }

    /**
     * Delete the bimestre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bimestre : {}", id);
        bimestreRepository.deleteById(id);
    }
}
