package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.DiaNaoUtilService;
import br.com.jhisolution.ong.control.domain.DiaNaoUtil;
import br.com.jhisolution.ong.control.repository.DiaNaoUtilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DiaNaoUtil.
 */
@Service
@Transactional
public class DiaNaoUtilServiceImpl implements DiaNaoUtilService {

    private final Logger log = LoggerFactory.getLogger(DiaNaoUtilServiceImpl.class);

    private final DiaNaoUtilRepository diaNaoUtilRepository;

    public DiaNaoUtilServiceImpl(DiaNaoUtilRepository diaNaoUtilRepository) {
        this.diaNaoUtilRepository = diaNaoUtilRepository;
    }

    /**
     * Save a diaNaoUtil.
     *
     * @param diaNaoUtil the entity to save
     * @return the persisted entity
     */
    @Override
    public DiaNaoUtil save(DiaNaoUtil diaNaoUtil) {
        log.debug("Request to save DiaNaoUtil : {}", diaNaoUtil);
        return diaNaoUtilRepository.save(diaNaoUtil);
    }

    /**
     * Get all the diaNaoUtils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DiaNaoUtil> findAll(Pageable pageable) {
        log.debug("Request to get all DiaNaoUtils");
        return diaNaoUtilRepository.findAll(pageable);
    }


    /**
     * Get one diaNaoUtil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DiaNaoUtil> findOne(Long id) {
        log.debug("Request to get DiaNaoUtil : {}", id);
        return diaNaoUtilRepository.findById(id);
    }

    /**
     * Delete the diaNaoUtil by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DiaNaoUtil : {}", id);
        diaNaoUtilRepository.deleteById(id);
    }
}
